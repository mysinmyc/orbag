import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/table/configurationitem_table.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/create_view.dart';

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
  Future<SerializableConfigurationItemDescriptor?>?
      _configurationItemDescriptor;

  @override
  void initState() {
    super.initState();
    _requestTemplate = MyHttpClient.instance.searchApi
        .getSearchTemplate(widget.configurationItemType);
    _configurationItemDescriptor = MyHttpClient.instance.metadataApi
        .getClassMetadata(widget.configurationItemType);
  }

  submitSearch(SearchRequest request) async {
    setState(() {
      _searchResult = null;
    });

    setState(() {
      _searchResult = MyHttpClient.instance.searchApi.execute(request);
    });
  }

  refreshActions() {
    setState(() {});
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
    filters.add(Row(children: [
      Padding(
          padding: const EdgeInsets.all(8),
          child: ElevatedButton.icon(
              onPressed: () {
                _formKey.currentState!.save();
                submitSearch(searchRequest);
              },
              icon: const Icon(Icons.search),
              label: const Text("Search"))),
      Padding(
          padding: const EdgeInsets.all(8),
          child: FutureBuilder(
              future: _configurationItemDescriptor,
              builder: (context, snapshot) {
                if (snapshot.hasData && snapshot.data!.allowCreation!) {
                  return ElevatedButton.icon(
                      onPressed: () {
                        Navigator.of(context)
                            .pushNamed(CreateView.routeName, arguments: {
                          "configurationItemType": widget.configurationItemType
                        });
                      },
                      icon: Icon(Icons.create),
                      label: Text("New"));
                } else {
                  return Text("");
                }
              }))
    ]));

    return Form(key: _formKey, child: Column(children: filters));
  }

  Widget buildResults(BuildContext context, SerializableTable result) {
    if (result.columns.isEmpty || result.rows.isEmpty) {
      return const Text("no item found");
    }
    return ConfigurationItemTable(result);
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
