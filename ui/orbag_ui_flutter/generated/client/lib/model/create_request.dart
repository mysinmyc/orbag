//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class CreateRequest {
  /// Returns a new [CreateRequest] instance.
  CreateRequest({
    this.configurationItemType,
    this.parameters,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? configurationItemType;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializableFieldGroup? parameters;

  @override
  bool operator ==(Object other) => identical(this, other) || other is CreateRequest &&
     other.configurationItemType == configurationItemType &&
     other.parameters == parameters;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (configurationItemType == null ? 0 : configurationItemType!.hashCode) +
    (parameters == null ? 0 : parameters!.hashCode);

  @override
  String toString() => 'CreateRequest[configurationItemType=$configurationItemType, parameters=$parameters]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.configurationItemType != null) {
      json[r'configurationItemType'] = this.configurationItemType;
    } else {
      json[r'configurationItemType'] = null;
    }
    if (this.parameters != null) {
      json[r'parameters'] = this.parameters;
    } else {
      json[r'parameters'] = null;
    }
    return json;
  }

  /// Returns a new [CreateRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static CreateRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "CreateRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "CreateRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return CreateRequest(
        configurationItemType: mapValueOfType<String>(json, r'configurationItemType'),
        parameters: SerializableFieldGroup.fromJson(json[r'parameters']),
      );
    }
    return null;
  }

  static List<CreateRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <CreateRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = CreateRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, CreateRequest> mapFromJson(dynamic json) {
    final map = <String, CreateRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = CreateRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of CreateRequest-objects as value to a dart map
  static Map<String, List<CreateRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<CreateRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = CreateRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

