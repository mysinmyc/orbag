import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/framework/data.dart';
import 'package:orbag_ui_flutter/framework/input.dart';
import 'package:orbag_ui_flutter/framework/search.dart';

Widget buildFilters(
    BuildContext context, SearchRequest searchRequest, _SearchCiState parent) {
  List<Widget> filters = [];

  for (StringField currentRequestField
      in searchRequest.parameters.stringFields) {
    TextFormField currentField = TextFormField(
        decoration: InputDecoration(hintText: currentRequestField.displayLabel),
        onSaved: (newValue) => {
              currentRequestField.changed = true,
              currentRequestField.value = newValue
            });
    filters.add(currentField);
  }

  filters.add(ElevatedButton(
      onPressed: () {
        parent._formKey.currentState!.save();
        parent.submitSearch(searchRequest);
      },
      child: const Text("Search")));

  return Form(key: parent._formKey, child: Column(children: filters));
}

Widget buildResults(
    BuildContext context, SerializableTable result, _SearchCiState parent) {
  if (result.columns.isEmpty || result.rows.isEmpty) {
    return const Text("no item found");
  }
  List<DataColumn> columns = [];

  for (SerializableColumn currentDataColumn in result.columns) {
    columns.add(DataColumn(
        label: Text(currentDataColumn.displayLabel == null
            ? currentDataColumn.name
            : currentDataColumn.displayLabel!)));
  }

  List<DataRow> rows = [];
  for (Map<String, Object> currentDataRow in result.rows) {
    List<DataCell> cells = [];
    for (SerializableColumn currentDataColumn in result.columns) {
      Object? currentValue = currentDataRow[currentDataColumn.name];
      cells.add(
          DataCell(Text(currentValue == null ? "" : currentValue.toString())));
    }
    rows.add(DataRow(cells: cells));
  }

  return DataTable(columns: columns, rows: rows);
}

class SearchCi extends StatefulWidget {
  final String configurationItemType;
  const SearchCi(this.configurationItemType, {super.key});

  @override
  State<SearchCi> createState() => _SearchCiState(this.configurationItemType);
}

class _SearchCiState extends State<SearchCi> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  Future<SearchRequest>? _requestTemplate;
  Future<SerializableTable>? _searchResult;
  String configurationItemType;
  _SearchCiState(this.configurationItemType) {
    _requestTemplate = getSearchTemplate(configurationItemType);
  }

  submitSearch(SearchRequest request) async {
    setState(() {
      _searchResult = executeSearch(request);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      FutureBuilder<SearchRequest>(
          future: _requestTemplate,
          builder:
              (BuildContext context, AsyncSnapshot<SearchRequest> snapshot) {
            if (snapshot.hasData) {
              return buildFilters(context, snapshot.requireData, this);
            } else {
              return const Text("Loading...");
            }
          }),
      FutureBuilder(
          future: _searchResult,
          builder: (BuildContext context,
              AsyncSnapshot<SerializableTable> snapshot) {
            if (snapshot.hasData) {
              return buildResults(context, snapshot.requireData, this);
            } else {
              return const Text("waiting for results");
            }
          })
    ]);
  }
}
