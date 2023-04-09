import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';

part 'reference.g.dart';

@JsonSerializable(explicitToJson: true)
class ConfigurationItemReference {
  ConfigurationItemReference(this.identifier, this.configurationItemType,
      this.displayLabel, this.configurationItemDisplayLabel);
  String identifier;
  String configurationItemType;
  String? displayLabel;
  String? configurationItemDisplayLabel;

  factory ConfigurationItemReference.fromJson(Map<String, dynamic> json) =>
      _$ConfigurationItemReferenceFromJson(json);
  Map<String, dynamic> toJson() => _$ConfigurationItemReferenceToJson(this);
}
