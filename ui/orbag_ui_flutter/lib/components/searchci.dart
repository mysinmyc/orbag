import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/configurationitemlink.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class SerializableTableSource extends DataTableSource {
  SerializableTable table;
  SerializableTableSource(this.table);

  Widget buildCellContent(SerializableColumn column, Object? value) {
    if (value == null) {
      return const Text("");
    }
    if (column.type == SerializableColumnTypeEnum.reference) {
      var reference = ConfigurationItemReference.fromJson(value)!;
      return ConfigurationItemLink(reference);
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
  Future<SearchRequest?>? _requestTemplate;
  Future<SerializableTable?>? _searchResult;

  @override
  void initState() {
    super.initState();
    _requestTemplate = MyHttpClient.instance.searchApi
        .getSearchTemplate(widget.configurationItemType);
  }

  submitSearch(SearchRequest request) async {
    setState(() {
      _searchResult = null;
    });

    setState(() {
      _searchResult = MyHttpClient.instance.searchApi.execute(request);
    });
  }

  Widget buildFilters(BuildContext context, SearchRequest searchRequest) {
    List<Widget> filters = [];

    for (StringField currentRequestField
        in searchRequest.parameters!.stringFields) {
      TextFormField currentField = TextFormField(
          decoration:
              InputDecoration(hintText: currentRequestField.displayLabel),
          onChanged: (newValue) => {
                currentRequestField.changed = true,
                currentRequestField.value = newValue
              });
      filters.add(currentField);
    }

    filters.add(DropdownButtonFormField(
      items: const [
        DropdownMenuItem(
            value: SearchRequestResultTypeEnum.ROW_REFERENCE,
            child: Text("Don't show any field")),
        DropdownMenuItem(
            value: SearchRequestResultTypeEnum.HIGHLIGHTED_FIELDS,
            child: Text("Show highlighted fields")),
        DropdownMenuItem(
            value: SearchRequestResultTypeEnum.ALL_FIELDS,
            child: Text("Show all fields"))
      ],
      value: searchRequest.resultType,
      onChanged: (newValue) => {},
      onSaved: (value) => {
        if (value != null) {searchRequest.resultType = value}
      },
    ));
    filters.add(Padding(
      padding: const EdgeInsets.all(8),
      child: ElevatedButton.icon(
          onPressed: () {
            _formKey.currentState!.save();
            submitSearch(searchRequest);
          },
          icon: const Icon(Icons.search),
          label: const Text("Search")),
    ));

    return Form(key: _formKey, child: Column(children: filters));
  }

  Widget buildResults(BuildContext context, SerializableTable result) {
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

    //return DataTable(columns: columns, rows: rows);

    return PaginatedDataTable(
        columns: columns, source: SerializableTableSource(result));
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      FutureBuilder<SearchRequest?>(
          future: _requestTemplate,
          builder:
              (BuildContext context, AsyncSnapshot<SearchRequest?> snapshot) {
            if (snapshot.hasData) {
              return buildFilters(context, snapshot.requireData!);
            } else {
              return const Text("Loading...");
            }
          }),
      FutureBuilder(
          future: _searchResult,
          builder: (BuildContext context,
              AsyncSnapshot<SerializableTable?> snapshot) {
            if (snapshot.hasData) {
              return buildResults(context, snapshot.requireData!);
            } else {
              return const Text("waiting for results");
            }
          })
    ]);
  }
}
