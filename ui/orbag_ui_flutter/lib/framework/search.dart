import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/framework/data.dart';

import 'package:orbag_ui_flutter/framework/input.dart';

part 'search.g.dart';

@JsonSerializable(explicitToJson: true)
class SearchRequest {
  SearchRequest(this.configurationItemName, this.parameters, this.resultType);
  String configurationItemName;
  SerializableFieldGroup parameters;
  String resultType;

  factory SearchRequest.fromJson(Map<String, dynamic> json) =>
      _$SearchRequestFromJson(json);
  Map<String, dynamic> toJson() => _$SearchRequestToJson(this);
}

Future<SearchRequest> getSearchTemplate(String configurationItemType) async {
  final response = await MyHttpClient.getInstance()
      .get("api/search/template/$configurationItemType");
  if (response.statusCode == 200) {
    return SearchRequest.fromJson(jsonDecode(response.body));
  } else {
    throw Exception("Failed to get search template for $configurationItemType");
  }
}

Future<SerializableTable> executeSearch(SearchRequest request) async {
  final response = await MyHttpClient.getInstance()
      .post("api/search/execute", jsonEncode(request));
  if (response.statusCode == 200) {
    return SerializableTable.fromJson(jsonDecode(response.body));
  } else {
    throw Exception("Failed execute search");
  }
}
