import 'package:flutter/material.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/table/tablesource.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class ConfigurationItemTable extends StatefulWidget {
  final ConfigurationItemReference? sourceCi;
  final SerializableTable table;
  const ConfigurationItemTable(this.table, {super.key, this.sourceCi});

  @override
  State<ConfigurationItemTable> createState() => _ConfigurationItemTableState();
}

class _ConfigurationItemTableState extends State<ConfigurationItemTable> {
  Future<GetAvailableActionsResponse?>? _availableActionsFuture;

  final List<SerializableRow> _selectedRows = List.empty(growable: true);

  refreshActions() {
    setState(() {});
  }

  refreshSelection() {
    setState(() {
      _availableActionsFuture = MyHttpClient.instance.actionApi.getAvailable(
          GetAvailableActionsRequest(
              targetCis: _selectedRows
                  .map((e) => ConfigurationItemReference.fromJson(
                      e.fields["__reference"])!)
                  .toList(),
              sourceCi: widget.sourceCi));
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
                      },
                      child: Text(e.displayLabel!)))
                  .toList());
        });
  }

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
              : currentDataColumn.displayLabel!)));
    }
    var tableSource = SerializableTableSource(result, _selectedRows,
        onSelected: (rows) => {refreshSelection()});

    return PaginatedDataTable(columns: columns, source: tableSource);
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      Conditional.single(
          context: context,
          conditionBuilder: (context) => _selectedRows.isNotEmpty,
          widgetBuilder: (context) => Row(children: [
                Text("Selected ${_selectedRows.length} cis"),
                Padding(padding: EdgeInsets.all(10)),
                FutureBuilder<GetAvailableActionsResponse?>(
                    future: _availableActionsFuture,
                    builder: (BuildContext context,
                        AsyncSnapshot<GetAvailableActionsResponse?> snapshot) {
                      if (snapshot.hasData) {
                        if (snapshot.data!.availableActions.isEmpty) {
                          return Text("No action available");
                        } else {
                          return ElevatedButton(
                              onPressed: () => _showAvailableActions(
                                  snapshot.data!.availableActions),
                              child: Text("Actions..."));
                        }
                      } else {
                        return Text("");
                      }
                    })
              ]),
          fallbackBuilder: (context) => Text("")),
      buildTable(context, widget.table)
    ]);
  }
}
