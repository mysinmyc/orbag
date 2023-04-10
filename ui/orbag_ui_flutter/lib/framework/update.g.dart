// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'update.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UpdateRequest _$UpdateRequestFromJson(Map<String, dynamic> json) =>
    UpdateRequest(
      ConfigurationItemReference.fromJson(
          json['configurationItem'] as Map<String, dynamic>),
      SerializableFieldGroup.fromJson(
          json['properties'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$UpdateRequestToJson(UpdateRequest instance) =>
    <String, dynamic>{
      'configurationItem': instance.configurationItem.toJson(),
      'properties': instance.properties.toJson(),
    };
