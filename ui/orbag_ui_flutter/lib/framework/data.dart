import 'package:json_annotation/json_annotation.dart';

part 'data.g.dart';

@JsonSerializable(explicitToJson: true)
class SerializableColumn {
  SerializableColumn(this.name, this.displayLabel, this.type);
  String name;
  String? displayLabel;
  String type;
  factory SerializableColumn.fromJson(Map<String, dynamic> json) =>
      _$SerializableColumnFromJson(json);
  Map<String, dynamic> toJson() => _$SerializableColumnToJson(this);
}

@JsonSerializable(explicitToJson: true)
class SerializableTable {
  SerializableTable(this.columns, this.rows);
  List<SerializableColumn> columns;
  List<Map<String, Object>> rows;
  factory SerializableTable.fromJson(Map<String, dynamic> json) =>
      _$SerializableTableFromJson(json);
  Map<String, dynamic> toJson() => _$SerializableTableToJson(this);
}
