import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';
import 'package:orbag_ui_flutter/components/search_ci.dart';
import 'package:orbag_ui_flutter/components/util/list_grouper.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';

typedef AdditionalWidgetBuilder = List<Widget> Function(
    BuildContext buildContext, VoidCallback changeCallBack);

typedef FieldGroupEditorSaveCallback = void Function(
    SerializableFieldGroup value);

class FieldGroupEditor extends StatefulWidget {
  final SerializableFieldGroup source;
  final FieldGroupEditorSaveCallback onSave;
  final String saveCaption;
  final bool saveVisible;
  final Icon saveIcon;
  final AdditionalWidgetBuilder? additionalFields;
  final AdditionalWidgetBuilder? additionalButtons;
  final bool enabled;
  const FieldGroupEditor(this.source, this.onSave,
      {this.saveCaption = "Save",
      this.saveVisible = false,
      this.saveIcon = const Icon(Icons.save),
      this.additionalFields,
      this.additionalButtons,
      this.enabled = true,
      super.key});

  @override
  State<FieldGroupEditor> createState() => _FieldGroupEditorState();
}

class WidgetFieldBuildInfo {
  final String? fieldCategory;
  final String? fieldDisplayLabel;
  final Widget widget;

  String get category {
    return fieldCategory ?? "";
  }

  String get displayLabel {
    return fieldDisplayLabel ?? "";
  }

  bool _isBaseCategory() {
    return category.toLowerCase() == "base properties";
  }

  const WidgetFieldBuildInfo(
      this.fieldCategory, this.fieldDisplayLabel, this.widget);

  int compareTo(WidgetFieldBuildInfo b) {
    int result;

    if (_isBaseCategory()) {
      result = b._isBaseCategory() ? 0 : -1;
    } else {
      result = category.toLowerCase().compareTo(b.category.toLowerCase());
    }
    if (result == 0) {
      return displayLabel.toLowerCase().compareTo(b.displayLabel.toLowerCase());
    }
    return result;
  }
}

class _FieldGroupEditorState extends State<FieldGroupEditor> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  FocusNode focusNode = FocusNode();

  bool changed = false;
  @override
  void initState() {
    super.initState();
  }

  List<WidgetFieldBuildInfo> _buildFieldWidgets(SerializableFieldGroup fields) {
    List<WidgetFieldBuildInfo> filters = List.empty(growable: true);

    for (StringField currentRequestField in fields.stringFields) {
      TextFormField currentField = TextFormField(
          readOnly: currentRequestField.readOnly!,
          enabled: widget.enabled && !currentRequestField.readOnly!,
          initialValue: currentRequestField.value,
          autofocus: filters.isEmpty,
          textInputAction: TextInputAction.send,
          /*
          onFieldSubmitted: (value) {
            _formKey.currentState!.save();
            widget.onSave(fields);
            changed = false;
          },
          */
          decoration: InputDecoration(
              border: const OutlineInputBorder(),
              labelText: currentRequestField.displayLabel,
              filled: true),
          onChanged: (value) => {
                currentRequestField.changed = true,
                currentRequestField.value = value
              });
      filters.add(WidgetFieldBuildInfo(currentRequestField.category ?? "",
          currentRequestField.displayLabel!, currentField));
    }

    for (NumericField currentRequestField in fields.numericFields) {
      TextFormField currentField = TextFormField(
          readOnly: currentRequestField.readOnly!,
          textInputAction: TextInputAction.send,
          enabled: widget.enabled && !currentRequestField.readOnly!,
          autofocus: filters.isEmpty,
          inputFormatters: <TextInputFormatter>[
            FilteringTextInputFormatter.digitsOnly
          ],
          initialValue: currentRequestField.value?.toString(),
          decoration: InputDecoration(
              border: const OutlineInputBorder(),
              labelText: currentRequestField.displayLabel),
          onChanged: (value) => {
                currentRequestField.changed = true,
                currentRequestField.value = int.tryParse(value)
              });
      filters.add(WidgetFieldBuildInfo(currentRequestField.category!,
          currentRequestField.displayLabel!, currentField));
    }

    for (BooleanField currentRequestField in fields.booleanFields) {
      Row currentField = Row(children: [
        Text(currentRequestField.displayLabel!),
        Switch(
            value: currentRequestField.value ?? false,
            onChanged: (value) => {
                  currentRequestField.changed = true,
                  currentRequestField.value = value,
                  setState(() => changed = true)
                }),
      ]);
      filters.add(WidgetFieldBuildInfo(currentRequestField.category,
          currentRequestField.displayLabel!, currentField));
    }

    for (ConfigurationItemReferenceField currentRequestReferenceField
        in fields.configurationItemReferenceFields) {
      Widget currentField = SizedBox(
          height: 80,
          child: InputDecorator(
              decoration: InputDecoration(
                  labelText: currentRequestReferenceField.displayLabel),
              expands: true,
              child: Row(
                  children: RenderUtil.padAll([
                Conditional.single(
                    context: context,
                    conditionBuilder: (context) =>
                        !currentRequestReferenceField.readOnly!,
                    widgetBuilder: (context) {
                      return IconButton(
                          icon: const Icon(Icons.edit),
                          onPressed: () => {
                                showDialog(
                                    context: context,
                                    builder: (context) => Dialog(
                                        child: Container(
                                            padding: EdgeInsets.only(
                                                top: 40, bottom: 40),
                                            child: SingleChildScrollView(
                                                child: SearchCi(
                                                    currentRequestReferenceField
                                                        .configurationItemType!,
                                                    onSelectedCi: (value) {
                                              Navigator.of(context).pop(value);
                                              setState(() {
                                                currentRequestReferenceField
                                                    .value = value;
                                                currentRequestReferenceField
                                                    .changed = true;
                                                changed = true;
                                              });
                                            })))))
                              });
                    },
                    fallbackBuilder: (context) {
                      return Text("");
                    }),
                ConfigurationItemLink(currentRequestReferenceField.value)
              ], padding: const EdgeInsets.only(top: 10, right: 10)))));
      filters.add(WidgetFieldBuildInfo(currentRequestReferenceField.category!,
          currentRequestReferenceField.displayLabel!, currentField));
    }

    for (EnumField currentRequestEnumField in fields.enumFields) {
      Widget currentField = InputDecorator(
          decoration:
              InputDecoration(labelText: currentRequestEnumField.displayLabel),
          child: DropdownButtonFormField<String>(
            value: currentRequestEnumField.value,
            items: currentRequestEnumField.allowedValues
                .map((e) => DropdownMenuItem(value: e, child: Text(e)))
                .toList(),
            onChanged: (String? newValue) => {
              currentRequestEnumField.changed = true,
              currentRequestEnumField.value = newValue
            },
          ));
      filters.add(WidgetFieldBuildInfo(currentRequestEnumField.category,
          currentRequestEnumField.displayLabel!, currentField));
    }
    return filters;
  }

  Widget buildFilters(BuildContext context, SerializableFieldGroup fields) {
    List<WidgetFieldBuildInfo> widgetForFields = _buildFieldWidgets(fields);
    widgetForFields.sort((a, b) => a.compareTo(b));
    List<Widget> filters = [];

    Map<String, List<WidgetFieldBuildInfo>> byCategories =
        new ListGrouper<String, WidgetFieldBuildInfo>()
            .groupBy(widgetForFields, (e) => e.category);

    for (var entry in byCategories.entries) {
      if (entry.key.isNotEmpty && byCategories.keys.length > 1) {
        filters.add(ListTile(title: Text(entry.key)));
      }
      filters.addAll(RenderUtil.toRows(
          RenderUtil.padAll(entry.value.map((e) => e.widget)),
          (MediaQuery.of(context).size.width / 600).clamp(1, 4).round()));
    }

    if (widget.additionalFields != null) {
      filters.addAll(widget.additionalFields!(
          context, () => setState(() => changed = true)));
    }

    List<Widget> buttons = List.empty(growable: true);
    if (widget.enabled) {
      if ((changed || widget.saveVisible)) {
        buttons.add(
          ElevatedButton.icon(
              onPressed: () {
                _formKey.currentState!.save();
                widget.onSave(fields);
                changed = false;
              },
              icon: widget.saveIcon,
              label: Text(widget.saveCaption)),
        );
      }

      //filters=RenderUtil.fixedSizeAll(filters, width: 400, height: 70));

      if (widget.additionalButtons != null) {
        buttons.addAll(widget.additionalButtons!(
            context, () => setState(() => changed = true)));
      }
    }

    return Padding(
        padding: EdgeInsets.only(left: 20, right: 20),
        child: Form(
          key: _formKey,
          child: Column(
              children: filters +
                  [
                    Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: RenderUtil.padAll(buttons,
                            padding: EdgeInsets.all(40)))
                  ]),
          onChanged: () => {setState(() => changed = true)},
        ));
  }

  @override
  Widget build(BuildContext context) {
    return buildFilters(context, widget.source);
  }
}
