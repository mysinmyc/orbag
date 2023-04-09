// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'reference.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ConfigurationItemReference _$ConfigurationItemReferenceFromJson(
        Map<String, dynamic> json) =>
    ConfigurationItemReference(
      json['identifier'] as String,
      json['configurationItemType'] as String,
      json['displayLabel'] as String?,
      json['configurationItemDisplayLabel'] as String?,
    );

Map<String, dynamic> _$ConfigurationItemReferenceToJson(
        ConfigurationItemReference instance) =>
    <String, dynamic>{
      'identifier': instance.identifier,
      'configurationItemType': instance.configurationItemType,
      'displayLabel': instance.displayLabel,
      'configurationItemDisplayLabel': instance.configurationItemDisplayLabel,
    };
