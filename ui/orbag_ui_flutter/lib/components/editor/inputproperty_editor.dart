import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';
import 'package:orbag_ui_flutter/components/search_ci.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';

typedef AdditionalWidgetBuilder = List<Widget> Function(
    BuildContext buildContext, VoidCallback changeCallBack);

typedef InputPropertyEditorSaveCallback = void Function(
    SerializableFieldGroup value);

class InputPropertyEditor extends StatefulWidget {
  final SerializableFieldGroup source;
  final InputPropertyEditorSaveCallback onSave;
  final String saveCaption;
  final bool saveVisible;
  final Icon saveIcon;
  final AdditionalWidgetBuilder? additionalFields;
  final AdditionalWidgetBuilder? additionalButtons;

  const InputPropertyEditor(this.source, this.onSave,
      {this.saveCaption = "Save",
      this.saveVisible = false,
      this.saveIcon = const Icon(Icons.save),
      this.additionalFields,
      this.additionalButtons,
      super.key});

  @override
  State<InputPropertyEditor> createState() => _InputPropertyEditorState();
}

class _InputPropertyEditorState extends State<InputPropertyEditor> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

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
          initialValue: currentRequestField.value,
          decoration:
              InputDecoration(labelText: currentRequestField.displayLabel),
          onChanged: (value) => {
                currentRequestField.changed = true,
                currentRequestField.value = value
              });
      filters.add(currentField);
    }

    for (NumericField currentRequestField in fields.numericFields) {
      TextFormField currentField = TextFormField(
          readOnly: currentRequestField.readOnly!,
          inputFormatters: <TextInputFormatter>[
            FilteringTextInputFormatter.digitsOnly
          ],
          initialValue: currentRequestField.value?.toString(),
          decoration:
              InputDecoration(labelText: currentRequestField.displayLabel),
          onChanged: (value) => {
                currentRequestField.changed = true,
                currentRequestField.value = int.tryParse(value)
              });
      filters.add(currentField);
    }

    for (BooleanField currentRequestField in fields.booleanFields) {
      Row currentField = Row(children: [
        Text(currentRequestField.displayLabel!),
        Checkbox(
            value: currentRequestField.value ?? false,
            onChanged: (value) => {
                  currentRequestField.changed = value != null,
                  currentRequestField.value = value,
                  setState(() => changed = true)
                }),
      ]);
      filters.add(currentField);
    }

    for (ConfigurationItemReferenceField currentRequestReferenceField
        in fields.configurationItemReferenceFields) {
      filters.add(InputDecorator(
          decoration: InputDecoration(
              labelText: currentRequestReferenceField.displayLabel),
          child: Row(
              children: RenderUtil.padAll([
            ConfigurationItemLink(currentRequestReferenceField.value),
            Conditional.single(
                context: context,
                conditionBuilder: (context) =>
                    !currentRequestReferenceField.readOnly!,
                widgetBuilder: (context) {
                  return ElevatedButton.icon(
                      icon: const Icon(Icons.edit),
                      label: Text(currentRequestReferenceField.value == null
                          ? "Set"
                          : "Change"),
                      onPressed: () => {
                            showDialog(
                                context: context,
                                builder: (context) => Dialog(
                                        child: SearchCi(
                                            currentRequestReferenceField
                                                .configurationItemType!,
                                            onSelectedCi: (value) {
                                      Navigator.of(context).pop(value);
                                      setState(() {
                                        currentRequestReferenceField.value =
                                            value;
                                        currentRequestReferenceField.changed =
                                            true;
                                        changed = true;
                                      });
                                    })))
                          });
                },
                fallbackBuilder: (context) {
                  return Text("");
                })
          ], padding: const EdgeInsets.only(right: 10)))));
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

    if (widget.additionalButtons != null) {
      buttons.addAll(widget.additionalButtons!(
          context, () => setState(() => changed = true)));
    }
    filters.add(Row(
        children:
            RenderUtil.padAll(buttons, padding: EdgeInsets.only(right: 10))));
    return Form(
      key: _formKey,
      child: Column(children: RenderUtil.padAll(filters)),
      onChanged: () => {setState(() => changed = true)},
    );
  }

  @override
  Widget build(BuildContext context) {
    return buildFilters(context, widget.source);
  }
}
