// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'input.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

StringField _$StringFieldFromJson(Map<String, dynamic> json) => StringField(
      json['name'] as String,
      json['displayLabel'] as String,
      json['type'] as String,
      json['value'] as String,
      json['changed'] as bool,
      json['readOnly'] as bool,
    );

Map<String, dynamic> _$StringFieldToJson(StringField instance) =>
    <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
      'type': instance.type,
      'value': instance.value,
      'changed': instance.changed,
      'readOnly': instance.readOnly,
    };

BooleanField _$BooleanFieldFromJson(Map<String, dynamic> json) => BooleanField(
      json['name'] as String,
      json['displayLabel'] as String,
      json['type'] as String,
      json['value'] as bool,
      json['changed'] as bool,
      json['readOnly'] as bool,
    );

Map<String, dynamic> _$BooleanFieldToJson(BooleanField instance) =>
    <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
      'type': instance.type,
      'value': instance.value,
      'changed': instance.changed,
      'readOnly': instance.readOnly,
    };

NumericField _$NumericFieldFromJson(Map<String, dynamic> json) => NumericField(
      json['name'] as String,
      json['displayLabel'] as String,
      json['type'] as String,
      json['value'] as int,
      json['changed'] as bool,
      json['readOnly'] as bool,
    );

Map<String, dynamic> _$NumericFieldToJson(NumericField instance) =>
    <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
      'type': instance.type,
      'value': instance.value,
      'changed': instance.changed,
      'readOnly': instance.readOnly,
    };

ConfigurationItemReferenceField _$ConfigurationItemReferenceFieldFromJson(
        Map<String, dynamic> json) =>
    ConfigurationItemReferenceField(
      json['name'] as String,
      json['displayLabel'] as String,
      json['type'] as String,
      ConfigurationItemReference.fromJson(
          json['value'] as Map<String, dynamic>),
      json['changed'] as bool,
      json['readOnly'] as bool,
      json['configurationItemType'] as String,
    );

Map<String, dynamic> _$ConfigurationItemReferenceFieldToJson(
        ConfigurationItemReferenceField instance) =>
    <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
      'type': instance.type,
      'value': instance.value.toJson(),
      'changed': instance.changed,
      'readOnly': instance.readOnly,
      'configurationItemType': instance.configurationItemType,
    };

EnumField _$EnumFieldFromJson(Map<String, dynamic> json) => EnumField(
      json['name'] as String,
      json['displayLabel'] as String,
      json['type'] as String,
      json['value'] as String,
      json['changed'] as bool,
      json['readOnly'] as bool,
      (json['allowedValues'] as List<dynamic>).map((e) => e as String).toList(),
    );

Map<String, dynamic> _$EnumFieldToJson(EnumField instance) => <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
      'type': instance.type,
      'value': instance.value,
      'changed': instance.changed,
      'readOnly': instance.readOnly,
      'allowedValues': instance.allowedValues,
    };

SerializableFieldGroup _$SerializableFieldGroupFromJson(
        Map<String, dynamic> json) =>
    SerializableFieldGroup(
      (json['stringFields'] as List<dynamic>)
          .map((e) => StringField.fromJson(e as Map<String, dynamic>))
          .toList(),
      (json['booleanFields'] as List<dynamic>)
          .map((e) => BooleanField.fromJson(e as Map<String, dynamic>))
          .toList(),
      (json['numericFields'] as List<dynamic>).map((e) => e as int).toList(),
      (json['configurationItemReferenceFields'] as List<dynamic>)
          .map((e) => ConfigurationItemReferenceField.fromJson(
              e as Map<String, dynamic>))
          .toList(),
      (json['enumFields'] as List<dynamic>)
          .map((e) => EnumField.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$SerializableFieldGroupToJson(
        SerializableFieldGroup instance) =>
    <String, dynamic>{
      'stringFields': instance.stringFields.map((e) => e.toJson()).toList(),
      'booleanFields': instance.booleanFields.map((e) => e.toJson()).toList(),
      'numericFields': instance.numericFields,
      'configurationItemReferenceFields': instance
          .configurationItemReferenceFields
          .map((e) => e.toJson())
          .toList(),
      'enumFields': instance.enumFields.map((e) => e.toJson()).toList(),
    };
