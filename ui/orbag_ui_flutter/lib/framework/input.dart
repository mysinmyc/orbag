import 'package:json_annotation/json_annotation.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';

part 'input.g.dart';

class InputFieldBase<T> {
  InputFieldBase(
      this.name, this.displayLabel, this.value, this.changed, this.readOnly);
  String name;
  String displayLabel;
  T? value;
  bool changed;
  bool readOnly;
}

@JsonSerializable(explicitToJson: true)
class StringField extends InputFieldBase<String> {
  StringField(super.name, super.displayLabel, super.value, super.changed,
      super.readOnly);
  factory StringField.fromJson(Map<String, dynamic> json) =>
      _$StringFieldFromJson(json);
  Map<String, dynamic> toJson() => _$StringFieldToJson(this);
}

@JsonSerializable(explicitToJson: true)
class BooleanField extends InputFieldBase<bool> {
  BooleanField(super.name, super.displayLabel, super.value, super.changed,
      super.readOnly);
  factory BooleanField.fromJson(Map<String, dynamic> json) =>
      _$BooleanFieldFromJson(json);
  Map<String, dynamic> toJson() => _$BooleanFieldToJson(this);
}

@JsonSerializable(explicitToJson: true)
class NumericField extends InputFieldBase<int> {
  NumericField(super.name, super.displayLabel, super.value, super.changed,
      super.readOnly);
  factory NumericField.fromJson(Map<String, dynamic> json) =>
      _$NumericFieldFromJson(json);
  Map<String, dynamic> toJson() => _$NumericFieldToJson(this);
}

@JsonSerializable(explicitToJson: true)
class ConfigurationItemReferenceField
    extends InputFieldBase<ConfigurationItemReference> {
  ConfigurationItemReferenceField(super.name, super.displayLabel, super.value,
      super.changed, super.readOnly, this.configurationItemType);
  String configurationItemType;
  factory ConfigurationItemReferenceField.fromJson(Map<String, dynamic> json) =>
      _$ConfigurationItemReferenceFieldFromJson(json);
  Map<String, dynamic> toJson() =>
      _$ConfigurationItemReferenceFieldToJson(this);
}

@JsonSerializable(explicitToJson: true)
class EnumField extends InputFieldBase<String> {
  EnumField(super.name, super.displayLabel, super.value, super.changed,
      super.readOnly, this.allowedValues);
  List<String> allowedValues;
  factory EnumField.fromJson(Map<String, dynamic> json) =>
      _$EnumFieldFromJson(json);
  Map<String, dynamic> toJson() => _$EnumFieldToJson(this);
}

@JsonSerializable(explicitToJson: true)
class SerializableFieldGroup {
  SerializableFieldGroup(
      this.stringFields,
      this.booleanFields,
      this.numericFields,
      this.configurationItemReferenceFields,
      this.enumFields);
  List<StringField> stringFields;
  List<BooleanField> booleanFields;
  List<int> numericFields;
  List<ConfigurationItemReferenceField> configurationItemReferenceFields;
  List<EnumField> enumFields;
  factory SerializableFieldGroup.fromJson(Map<String, dynamic> json) =>
      _$SerializableFieldGroupFromJson(json);
  Map<String, dynamic> toJson() => _$SerializableFieldGroupToJson(this);
}

bool isFieldGroupEmpty(SerializableFieldGroup fieldGroup) {
  return fieldGroup.booleanFields.isEmpty &&
      fieldGroup.configurationItemReferenceFields.isEmpty &&
      fieldGroup.enumFields.isEmpty &&
      fieldGroup.numericFields.isEmpty &&
      fieldGroup.stringFields.isEmpty;
}
