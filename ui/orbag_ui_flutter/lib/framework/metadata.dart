import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

part 'metadata.g.dart';

@JsonSerializable(explicitToJson: true)
class ClassModel {
  ClassModel(this.configurationItemDescriptors);
  List<ConfigurationItemDescriptor> configurationItemDescriptors;
  factory ClassModel.fromJson(Map<String, dynamic> json) =>
      _$ClassModelFromJson(json);
  Map<String, dynamic> toJson() => _$ClassModelToJson(this);
}

@JsonSerializable(explicitToJson: true)
class ConfigurationItemPropertyDescriptor {
  ConfigurationItemPropertyDescriptor(this.name, this.displayLabel);
  String name;
  String displayLabel;
  factory ConfigurationItemPropertyDescriptor.fromJson(
          Map<String, dynamic> json) =>
      _$ConfigurationItemPropertyDescriptorFromJson(json);
  Map<String, dynamic> toJson() =>
      _$ConfigurationItemPropertyDescriptorToJson(this);
}

@JsonSerializable(explicitToJson: true)
class ConfigurationItemDescriptor {
  ConfigurationItemDescriptor(this.name, this.category, this.displayLabel,
      this.allowCreation, this.properties);
  String name;
  String category;
  String displayLabel;
  bool allowCreation;
  List<ConfigurationItemPropertyDescriptor>? properties;
  factory ConfigurationItemDescriptor.fromJson(Map<String, dynamic> json) =>
      _$ConfigurationItemDescriptorFromJson(json);
  Map<String, dynamic> toJson() => _$ConfigurationItemDescriptorToJson(this);
}

Future<ClassModel> getClassModel() async {
  final response = await MyHttpClient.getInstance().get('api/metadata');
  if (response.statusCode == 200) {
    return ClassModel.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Failed to get classModel');
  }
}
