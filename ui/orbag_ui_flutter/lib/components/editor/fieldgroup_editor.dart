import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';
import 'package:orbag_ui_flutter/components/search_ci.dart';
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

  const FieldGroupEditor(this.source, this.onSave,
      {this.saveCaption = "Save",
      this.saveVisible = false,
      this.saveIcon = const Icon(Icons.save),
      this.additionalFields,
      this.additionalButtons,
      super.key});

  @override
  State<FieldGroupEditor> createState() => _FieldGroupEditorState();
}

class _FieldGroupEditorState extends State<FieldGroupEditor> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  FocusNode focusNode = FocusNode();

  bool changed = false;
  @override
  void initState() {
    super.initState();
  }

  Widget buildFilters(BuildContext context, SerializableFieldGroup fields) {
    List<Widget> filters = [];

    for (StringField currentRequestField in fields.stringFields) {
      TextFormField currentField = TextFormField(
          readOnly: currentRequestField.readOnly!,
          enabled: !currentRequestField.readOnly!,
          initialValue: currentRequestField.value,
          autofocus: filters.isEmpty,
          textInputAction: TextInputAction.send,
          decoration: InputDecoration(
              border: const OutlineInputBorder(),
              labelText: currentRequestField.displayLabel,
              filled: true),
          onChanged: (value) => {
                currentRequestField.changed = true,
                currentRequestField.value = value
              });
      filters.add(currentField);
    }

    for (NumericField currentRequestField in fields.numericFields) {
      TextFormField currentField = TextFormField(
          readOnly: currentRequestField.readOnly!,
          textInputAction: TextInputAction.send,
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
      filters.add(currentField);
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
      filters.add(currentField);
    }

    for (ConfigurationItemReferenceField currentRequestReferenceField
        in fields.configurationItemReferenceFields) {
      filters.add(SizedBox(
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
              ], padding: const EdgeInsets.only(top: 10, right: 10))))));
    }

    for (EnumField currentRequestEnumField in fields.enumFields) {
      filters.add(InputDecorator(
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
          )));
    }

    if (widget.additionalFields != null) {
      filters.addAll(widget.additionalFields!(
          context, () => setState(() => changed = true)));
    }

    List<Widget> buttons = List.empty(growable: true);
    if (changed || widget.saveVisible) {
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

    return Form(
      key: _formKey,
      child: Column(
          children: RenderUtil.toRows(
                  RenderUtil.padAll(filters),
                  (MediaQuery.of(context).size.width / 600)
                      .clamp(1, 4)
                      .round()) +
              [
                Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children:
                        RenderUtil.padAll(buttons, padding: EdgeInsets.all(40)))
              ]),
      onChanged: () => {setState(() => changed = true)},
    );
  }

  @override
  Widget build(BuildContext context) {
    return buildFilters(context, widget.source);
  }
}
