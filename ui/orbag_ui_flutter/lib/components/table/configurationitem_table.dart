import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_feedback.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_form.dart';
import 'package:orbag_ui_flutter/components/action/action_util.dart';
import 'package:orbag_ui_flutter/components/table/tablesource.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/action_view.dart';
import 'package:url_launcher/url_launcher.dart';

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

  @override
  void initState() {
    super.initState();
  }

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

  final Queue<ActionSubmissionResultInfo> _actionResultInfoQueue = Queue();

  _executeAction(BuildContext context, ActionData actionData) async {
    ActionUtil.submit(context, actionData).then((response) {
        setState(() {
          _actionResultInfoQueue.add(response);
        });
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
                        _executeAction(
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

  SerializableColumn? _sortColumn;
  int? _sortColumnIndex;
  bool _sortAscending = true;


  _sortBy(SerializableColumn column, int columnIndex, bool asceding) {
    setState(() {
      _sortColumn = column;
      _sortColumnIndex = columnIndex;
      _sortAscending = asceding;
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

  final GlobalKey<PaginatedDataTableState> _tableKey =
      GlobalKey<PaginatedDataTableState>();

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

    if (_sortColumn != null) {
      result.rows.sort((a, b) {
        if (_sortAscending) {
          return getComparableValue(_sortColumn!, a)
              .compareTo(getComparableValue(_sortColumn!, b));
        } else {
          return getComparableValue(_sortColumn!, b)
              .compareTo(getComparableValue(_sortColumn!, a));
        }
      });
    }

    var tableSource = SerializableTableSource(result, _selectedRows,
        onSelected: (rows) => {refreshSelection()},
        onSelectCi: widget.onSelectedCi);

    var table = PaginatedDataTable(
        columns: columns,
        source: tableSource,
        sortColumnIndex: _sortColumnIndex,
        sortAscending: _sortAscending,
        key: _tableKey);

    if (_tableKey.currentState != null) {
      _tableKey.currentState!.pageTo(0);
    }
    return table;
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      Container(height: 60, child:
      Conditional.single(
          context: context,
          conditionBuilder: (context) => _selectedRows.isNotEmpty,
          widgetBuilder: (context) => RenderUtil.pad(Row(children: [
                Text(
                    "Selected ${LabelUtil.truncateLabel(LabelUtil.getCisLabel(selectedCis))}"),
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
          fallbackBuilder: (context) => const Text(""))),
      buildTable(context, widget.table),
      ActionExecutionFeedBack(_actionResultInfoQueue)
    ]);
  }
}
