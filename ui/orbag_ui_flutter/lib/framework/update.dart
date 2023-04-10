import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

import 'package:orbag_ui_flutter/framework/input.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';

part 'update.g.dart';

@JsonSerializable(explicitToJson: true)
class UpdateRequest {
  UpdateRequest(this.configurationItem, this.properties);
  ConfigurationItemReference configurationItem;
  SerializableFieldGroup properties;

  factory UpdateRequest.fromJson(Map<String, dynamic> json) =>
      _$UpdateRequestFromJson(json);
  Map<String, dynamic> toJson() => _$UpdateRequestToJson(this);
}

Future<UpdateRequest> getUpdateRequestTemplate(
    ConfigurationItemReference configurationItemReference) async {
  final response = await MyHttpClient.getInstance()
      .post("api/update/getTemplate", jsonEncode(configurationItemReference));
  if (response.statusCode == 200) {
    return UpdateRequest.fromJson(jsonDecode(response.body));
  } else {
    throw Exception("Failed to get template");
  }
}

Future<ConfigurationItemReference> updateConfigurationItem(
    UpdateRequest request) async {
  final response = await MyHttpClient.getInstance()
      .post("api/update/execute", jsonEncode(request));
  if (response.statusCode == 200) {
    return ConfigurationItemReference.fromJson(jsonDecode(response.body));
  } else {
    throw Exception("Failed to update ci");
  }
}
