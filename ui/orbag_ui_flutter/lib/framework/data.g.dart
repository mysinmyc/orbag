// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'data.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SerializableColumn _$SerializableColumnFromJson(Map<String, dynamic> json) =>
    SerializableColumn(
      json['name'] as String,
      json['displayLabel'] as String?,
      json['type'] as String,
    );

Map<String, dynamic> _$SerializableColumnToJson(SerializableColumn instance) =>
    <String, dynamic>{
      'name': instance.name,
      'displayLabel': instance.displayLabel,
      'type': instance.type,
    };

SerializableTable _$SerializableTableFromJson(Map<String, dynamic> json) =>
    SerializableTable(
      (json['columns'] as List<dynamic>)
          .map((e) => SerializableColumn.fromJson(e as Map<String, dynamic>))
          .toList(),
      (json['rows'] as List<dynamic>)
          .map((e) => e as Map<String, dynamic>)
          .toList(),
    );

Map<String, dynamic> _$SerializableTableToJson(SerializableTable instance) =>
    <String, dynamic>{
      'columns': instance.columns.map((e) => e.toJson()).toList(),
      'rows': instance.rows,
    };
