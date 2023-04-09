// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'search.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SearchRequest _$SearchRequestFromJson(Map<String, dynamic> json) =>
    SearchRequest(
      json['configurationItemName'] as String,
      SerializableFieldGroup.fromJson(
          json['parameters'] as Map<String, dynamic>),
      json['resultType'] as String,
    );

Map<String, dynamic> _$SearchRequestToJson(SearchRequest instance) =>
    <String, dynamic>{
      'configurationItemName': instance.configurationItemName,
      'parameters': instance.parameters.toJson(),
      'resultType': instance.resultType,
    };
