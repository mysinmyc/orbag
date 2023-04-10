import 'package:json_annotation/json_annotation.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';

part 'data.g.dart';

fixValues(SerializableTable table) {
  for (SerializableColumn currentColumn in table.columns) {
    if (currentColumn.type == "Reference") {
      for (Map<String, Object?> currentRow in table.rows) {
        Object? currentValue = currentRow[currentColumn.name];
        if (currentValue != null) {
          currentRow[currentColumn.name] = ConfigurationItemReference.fromJson(
              currentValue as Map<String, dynamic>);
        }
      }
    }
  }
}

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
  List<Map<String, Object?>> rows;
  factory SerializableTable.fromJson(Map<String, dynamic> json) {
    {
      SerializableTable result = _$SerializableTableFromJson(json);
      fixValues(result);
      return result;
    }
  }
  Map<String, dynamic> toJson() => _$SerializableTableToJson(this);
}
