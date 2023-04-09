// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'metadata.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ClassModel _$ClassModelFromJson(Map<String, dynamic> json) => ClassModel(
      (json['configurationItemDescriptors'] as List<dynamic>)
          .map((e) =>
              ConfigurationItemDescriptor.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$ClassModelToJson(ClassModel instance) =>
    <String, dynamic>{
      'configurationItemDescriptors':
          instance.configurationItemDescriptors.map((e) => e.toJson()).toList(),
    };

ConfigurationItemPropertyDescriptor
    _$ConfigurationItemPropertyDescriptorFromJson(Map<String, dynamic> json) =>
        ConfigurationItemPropertyDescriptor(
          json['name'] as String,
          json['displayLabel'] as String,
        );

Map<String, dynamic> _$ConfigurationItemPropertyDescriptorToJson(
        ConfigurationItemPropertyDescriptor instance) =>
    <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
    };

ConfigurationItemDescriptor _$ConfigurationItemDescriptorFromJson(
        Map<String, dynamic> json) =>
    ConfigurationItemDescriptor(
      json['name'] as String,
      json['category'] as String,
      json['displayLabel'] as String,
      json['allowCreation'] as bool,
      (json['properties'] as List<dynamic>?)
          ?.map((e) => ConfigurationItemPropertyDescriptor.fromJson(
              e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$ConfigurationItemDescriptorToJson(
        ConfigurationItemDescriptor instance) =>
    <String, dynamic>{
      'name': instance.name,
      'category': instance.category,
      'displayLabel': instance.displayLabel,
      'allowCreation': instance.allowCreation,
      'properties': instance.properties?.map((e) => e.toJson()).toList(),
    };
