import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/create_ci.dart';
import 'package:orbag_ui_flutter/components/editor/inputproperty_editor.dart';
import 'package:orbag_ui_flutter/components/table/configurationitem_table.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class SearchCi extends StatefulWidget {
  final String configurationItemType;

  final ValueChanged<ConfigurationItemReference>? onSelectedCi;

  const SearchCi(this.configurationItemType, {super.key, this.onSelectedCi});

  @override
  State<SearchCi> createState() => _SearchCiState();
}

class _SearchCiState extends State<SearchCi> {
  Future<SearchRequest?>? _requestTemplateFuture;
  Future<SerializableTable?>? _searchResultFuture;
  Future<SerializableConfigurationItemDescriptor?>?
      _configurationItemDescriptorFuture;

  @override
  void initState() {
    super.initState();
    _requestTemplateFuture = MyHttpClient.instance.searchApi
        .getSearchTemplate(widget.configurationItemType);
    _configurationItemDescriptorFuture = MyHttpClient.instance.metadataApi
        .getClassMetadata(widget.configurationItemType);
  }

  _submitSearch(SearchRequest request) async {
    setState(() {
      _searchResultFuture = null;
    });

    setState(() {
      _searchResultFuture = MyHttpClient.instance.searchApi.execute(request);
    });
  }

  _createNewCi(
      SerializableConfigurationItemDescriptor configurationItemDescriptor,
      SearchRequest request) {
    showDialog(
        context: context,
        builder: (context) => Dialog(
                child: CreateCi(widget.configurationItemType, onCreated: (ci) {
              Navigator.of(context).pop(ci);
            }))).then((value) => {
          if (widget.onSelectedCi == null)
            {_submitSearch(request)}
          else
            {widget.onSelectedCi!(value)}
        });
  }

  Widget buildFilters(BuildContext context, SearchRequest searchRequest) {
    return InputPropertyEditor(searchRequest.parameters!, (value) {
      searchRequest.parameters = value;
      _submitSearch(searchRequest);
    },
        saveCaption: "Search",
        saveIcon: const Icon(Icons.search),
        saveVisible: true,
        additionalFields: (context, changeCallBack) => [
              DropdownButtonFormField(
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
                  value: widget.onSelectedCi == null
                      ? searchRequest.resultType
                      : SearchRequestResultTypeEnum.ROW_REFERENCE,
                  onChanged: (newValue) => {},
                  onSaved: (value) => {
                        if (value != null) {searchRequest.resultType = value}
                      })
            ],
        additionalButtons: (context, changeCallBack) => [
              FutureBuilder(
                  future: _configurationItemDescriptorFuture,
                  builder: (context, snapshot) {
                    if (snapshot.hasData && snapshot.data!.allowCreation!) {
                      return ElevatedButton.icon(
                          icon: Icon(Icons.add),
                          label: Text("New"),
                          onPressed: () {
                            _createNewCi(snapshot.data!, searchRequest);
                          });
                    } else {
                      return Text("");
                    }
                  })
            ]);
  }

  Widget buildResults(BuildContext context, SerializableTable result) {
    if (result.columns.isEmpty || result.rows.isEmpty) {
      return const Text("no item found");
    }
    return ConfigurationItemTable(result, onSelectedCi: widget.onSelectedCi);
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      FutureBuilder<SearchRequest?>(
          future: _requestTemplateFuture,
          builder:
              (BuildContext context, AsyncSnapshot<SearchRequest?> snapshot) {
            if (snapshot.hasData) {
              return buildFilters(context, snapshot.requireData!);
            } else {
              return const Text("Loading...");
            }
          }),
      FutureBuilder(
          future: _searchResultFuture,
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
