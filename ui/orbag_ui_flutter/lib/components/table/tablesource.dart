import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';

class SerializableTableSource extends DataTableSource {
  ValueChanged<List<SerializableRow>>? onSelected;
  ValueChanged<ConfigurationItemReference>? onSelectCi;
  List<SerializableRow> selectedRows;
  SerializableTable table;
  SerializableTableSource(this.table, this.selectedRows,
      {this.onSelected, this.onSelectCi});

  Widget buildCellContent(SerializableColumn column, Object? value) {
    if (value == null) {
      return const Text("");
    }
    if (column.type == SerializableColumnTypeEnum.reference) {
      var reference = ConfigurationItemReference.fromJson(value)!;

      if (column.name == '__reference' && onSelectCi != null) {
        return ConfigurationItemLink(reference, onSelectedCi: onSelectCi);
      } else {
        return ConfigurationItemLink(reference);
      }
    } else {
      return Text(value.toString());
    }
  }

  @override
  DataRow? getRow(int index) {
    SerializableRow currentDataRow = table.rows[index];

    List<DataCell> cells = [];
    for (SerializableColumn currentDataColumn in table.columns) {
      cells.add(DataCell(buildCellContent(
          currentDataColumn, currentDataRow.fields[currentDataColumn.name])));
    }
    return DataRow(
        cells: cells,
        selected: selectedRows.contains(currentDataRow),
        onSelectChanged: onSelectCi == null
            ? (value) => {
                  if (value!)
                    {selectedRows.add(currentDataRow)}
                  else
                    {selectedRows.remove(currentDataRow)},
                  notifyListeners(),
                  if (onSelected != null) {onSelected!(selectedRows)}
                }
            : null);
  }

  @override
  bool get isRowCountApproximate => false;

  @override
  int get rowCount => table.rows.length;

  @override
  int get selectedRowCount => selectedRows.length;
}
