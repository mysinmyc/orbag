import 'package:flutter/material.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/action/action_executor.dart';
import 'package:orbag_ui_flutter/components/table/tablesource.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/action_view.dart';

class ConfigurationItemTable extends StatefulWidget {
  final ConfigurationItemReference? sourceCi;
  final SerializableTable table;
  final ValueChanged<ConfigurationItemReference>? onSelectedCi;
  const ConfigurationItemTable(this.table,
      {super.key, this.onSelectedCi, this.sourceCi});

  @override
  State<ConfigurationItemTable> createState() => _ConfigurationItemTableState();
}

class _ConfigurationItemTableState extends State<ConfigurationItemTable> {
  Future<GetAvailableActionsResponse?>? _availableActionsFuture;

  final List<SerializableRow> _selectedRows = List.empty(growable: true);

  List<ConfigurationItemReference> get selectedCis {
    return _selectedRows
        .map((e) =>
            ConfigurationItemReference.fromJson(e.fields["__reference"])!)
        .toList();
  }

  refreshSelection() {
    setState(() {
      _availableActionsFuture = MyHttpClient.instance.actionApi.getAvailable(
          GetAvailableActionsRequest(
              targetCis: selectedCis, sourceCi: widget.sourceCi));
    });
  }

  Future<void> _showAvailableActions(List<SerializableAction> actions) async {
    await showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          return SimpleDialog(
              title: const Text('Actions...'),
              children: actions
                  .map((e) => SimpleDialogOption(
                      onPressed: () {
                        Navigator.of(context).pop();
                        ActionView.show(
                            context,
                            ActionData(e, selectedCis,
                                sourceCi: widget.sourceCi));
                        _selectedRows.clear();
                        refreshSelection();
                      },
                      child: Text(e.displayLabel!)))
                  .toList());
        });
  }

  SerializableColumn? _sort_column;
  int? _sort_columnIndex;
  bool _sort_asceding = true;

  _sortBy(SerializableColumn column, int _columnIndex, bool asceding) {
    setState(() {
      _sort_column = column;
      _sort_columnIndex = _columnIndex;
      _sort_asceding = asceding;
    });
  }

  String getComparableValue(SerializableColumn column, SerializableRow row) {
    Object? value = row.fields[column.name];
    if (value == null) {
      return "";
    }
    if (column.type == SerializableColumnTypeEnum.reference) {
      return (ConfigurationItemReference.fromJson(value)!.displayLabel ?? "")
          .toLowerCase();
    }

    return value.toString().toLowerCase();
  }

  GlobalKey<PaginatedDataTableState> _tableKey =
      new GlobalKey<PaginatedDataTableState>();

  Widget buildTable(BuildContext context, SerializableTable result) {
    if (result.columns.isEmpty || result.rows.isEmpty) {
      return const Text("no item found");
    }
    result.columns.sort((a, b) => a.name!.compareTo(b.name!));
    List<DataColumn> columns = [];
    for (SerializableColumn currentDataColumn in result.columns) {
      columns.add(DataColumn(
          label: Text(currentDataColumn.displayLabel == null
              ? ""
              : currentDataColumn.displayLabel!),
          onSort: ((columnIndex, ascending) =>
              {_sortBy(currentDataColumn, columnIndex, ascending)})));
    }

    if (_sort_column != null) {
      result.rows.sort((a, b) {
        if (_sort_asceding) {
          return getComparableValue(_sort_column!, a)
              .compareTo(getComparableValue(_sort_column!, b));
        } else {
          return getComparableValue(_sort_column!, b)
              .compareTo(getComparableValue(_sort_column!, a));
        }
      });
    }

    var tableSource = SerializableTableSource(result, _selectedRows,
        onSelected: (rows) => {refreshSelection()},
        onSelectCi: widget.onSelectedCi);

    var table = PaginatedDataTable(
        columns: columns,
        source: tableSource,
        sortColumnIndex: _sort_columnIndex,
        sortAscending: _sort_asceding,
        key: _tableKey);

    if (_tableKey.currentState != null) {
      _tableKey.currentState!.pageTo(0);
    }
    return table;
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      Conditional.single(
          context: context,
          conditionBuilder: (context) => _selectedRows.isNotEmpty,
          widgetBuilder: (context) => RenderUtil.pad(Row(children: [
                Text("Selected ${LabelUtil.getCisLabel(selectedCis)}"),
                const Padding(padding: EdgeInsets.all(10)),
                FutureBuilder<GetAvailableActionsResponse?>(
                    future: _availableActionsFuture,
                    builder: (BuildContext context,
                        AsyncSnapshot<GetAvailableActionsResponse?> snapshot) {
                      if (snapshot.hasData) {
                        if (snapshot.data!.availableActions.isEmpty) {
                          return const Text("No action available");
                        } else {
                          return ElevatedButton(
                              onPressed: () => _showAvailableActions(
                                  snapshot.data!.availableActions),
                              child: const Text("Actions..."));
                        }
                      } else {
                        return const Text("");
                      }
                    })
              ])),
          fallbackBuilder: (context) => const Text("")),
      buildTable(context, widget.table)
    ]);
  }
}
