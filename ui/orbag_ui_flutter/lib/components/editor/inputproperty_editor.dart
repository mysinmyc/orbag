import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';

typedef InputPropertyEditorSaveCallback = void Function(
    SerializableFieldGroup value);

class InputPropertyEditor extends StatefulWidget {
  final SerializableFieldGroup source;
  final InputPropertyEditorSaveCallback onSave;
  final String saveCaption;
  const InputPropertyEditor(this.source, this.onSave,
      {this.saveCaption = "Save", super.key});

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

    for (ConfigurationItemReferenceField currentRequestReferenceField
        in fields.configurationItemReferenceFields) {
      if (currentRequestReferenceField.value == null) {
        filters.add(Text("no ${currentRequestReferenceField.displayLabel}"));
      } else {
        filters.add(InputDecorator(
            decoration: InputDecoration(
                labelText: currentRequestReferenceField.displayLabel),
            child: ConfigurationItemLink(currentRequestReferenceField.value!)));
      }
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
    if (changed) {
      filters.add(Padding(
        padding: const EdgeInsets.all(8),
        child: ElevatedButton.icon(
            onPressed: () {
              _formKey.currentState!.save();
              widget.onSave(fields);
              changed = false;
            },
            icon: const Icon(Icons.save),
            label: Text(widget.saveCaption)),
      ));
    }
    return Form(
      key: _formKey,
      child: Column(children: filters),
      onChanged: () => {setState(() => changed = true)},
    );
  }

  @override
  Widget build(BuildContext context) {
    return buildFilters(context, widget.source);
  }
}
