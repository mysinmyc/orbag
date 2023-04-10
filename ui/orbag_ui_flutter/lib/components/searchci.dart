import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/framework/data.dart';
import 'package:orbag_ui_flutter/framework/input.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';
import 'package:orbag_ui_flutter/framework/search.dart';

class SerializableTableSource extends DataTableSource {
  SerializableTable table;
  SerializableTableSource(this.table);

  Widget buildCellContent(SerializableColumn column, Object? value) {
    if (value == null) {
      return const Text("");
    }
    if (column.type == "Reference") {
      ConfigurationItemReference reference =
          value as ConfigurationItemReference;
      return ElevatedButton(
          child: Text(reference.displayLabel!), onPressed: () {});
    } else {
      return Text(value.toString());
    }
  }

  @override
  DataRow? getRow(int index) {
    Map<String, Object?> currentDataRow = table.rows[index];

    List<DataCell> cells = [];
    for (SerializableColumn currentDataColumn in table.columns) {
      cells.add(DataCell(buildCellContent(
          currentDataColumn, currentDataRow[currentDataColumn.name])));
    }
    return DataRow(cells: cells);
  }

  @override
  bool get isRowCountApproximate => false;

  @override
  int get rowCount => table.rows.length;

  @override
  int get selectedRowCount => 0;
}

class SearchCi extends StatefulWidget {
  final String configurationItemType;
  const SearchCi(this.configurationItemType, {super.key});

  @override
  State<SearchCi> createState() => _SearchCiState();
}

class _SearchCiState extends State<SearchCi> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  Future<SearchRequest>? _requestTemplate;
  Future<SerializableTable>? _searchResult;

  @override
  void initState() {
    super.initState();
    _requestTemplate = getSearchTemplate(widget.configurationItemType);
  }

  submitSearch(SearchRequest request) async {
    setState(() {
      _searchResult = null;
    });

    setState(() {
      _searchResult = executeSearch(request);
    });
  }

  Widget buildFilters(BuildContext context, SearchRequest searchRequest) {
    List<Widget> filters = [];

    for (StringField currentRequestField
        in searchRequest.parameters.stringFields) {
      TextFormField currentField = TextFormField(
          decoration:
              InputDecoration(hintText: currentRequestField.displayLabel),
          onSaved: (newValue) => {
                currentRequestField.changed = true,
                currentRequestField.value = newValue
              });
      filters.add(currentField);
    }

    filters.add(DropdownButtonFormField(
      items: const [
        DropdownMenuItem(
            value: "ROW_REFERENCE", child: Text("Don't show any field")),
        DropdownMenuItem(
            value: "HIGHLIGHTED_FIELDS",
            child: Text("Show highlighted fields")),
        DropdownMenuItem(value: "ALL_FIELDS", child: Text("Show all fields"))
      ],
      value: searchRequest.resultType,
      onChanged: (newValue) => {},
      onSaved: (value) => {
        if (value != null) {searchRequest.resultType = value}
      },
    ));
    filters.add(Padding(
      padding: const EdgeInsets.all(8),
      child: ElevatedButton(
          onPressed: () {
            _formKey.currentState!.save();
            submitSearch(searchRequest);
          },
          child: const Text("Search")),
    ));

    return Form(key: _formKey, child: Column(children: filters));
  }

  Widget buildResults(BuildContext context, SerializableTable result) {
    if (result.columns.isEmpty || result.rows.isEmpty) {
      return const Text("no item found");
    }
    List<DataColumn> columns = [];

    for (SerializableColumn currentDataColumn in result.columns) {
      columns.add(DataColumn(
          label: Text(currentDataColumn.displayLabel == null
              ? ""
              : currentDataColumn.displayLabel!)));
    }

    //return DataTable(columns: columns, rows: rows);

    return PaginatedDataTable(
        columns: columns, source: SerializableTableSource(result));
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      FutureBuilder<SearchRequest>(
          future: _requestTemplate,
          builder:
              (BuildContext context, AsyncSnapshot<SearchRequest> snapshot) {
            if (snapshot.hasData) {
              return buildFilters(context, snapshot.requireData);
            } else {
              return const Text("Loading...");
            }
          }),
      FutureBuilder(
          future: _searchResult,
          builder: (BuildContext context,
              AsyncSnapshot<SerializableTable> snapshot) {
            if (snapshot.hasData) {
              return buildResults(context, snapshot.requireData);
            } else {
              return const Text("waiting for results");
            }
          })
    ]);
  }
}
