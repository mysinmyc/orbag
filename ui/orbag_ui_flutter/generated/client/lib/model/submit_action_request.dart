//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SubmitActionRequest {
  /// Returns a new [SubmitActionRequest] instance.
  SubmitActionRequest({
    this.sourceCi,
    this.targetCis = const [],
    this.action,
    this.parameters,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? sourceCi;

  List<ConfigurationItemReference> targetCis;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializableAction? action;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializableFieldGroup? parameters;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SubmitActionRequest &&
     other.sourceCi == sourceCi &&
     other.targetCis == targetCis &&
     other.action == action &&
     other.parameters == parameters;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (sourceCi == null ? 0 : sourceCi!.hashCode) +
    (targetCis.hashCode) +
    (action == null ? 0 : action!.hashCode) +
    (parameters == null ? 0 : parameters!.hashCode);

  @override
  String toString() => 'SubmitActionRequest[sourceCi=$sourceCi, targetCis=$targetCis, action=$action, parameters=$parameters]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.sourceCi != null) {
      json[r'sourceCi'] = this.sourceCi;
    } else {
      json[r'sourceCi'] = null;
    }
      json[r'targetCis'] = this.targetCis;
    if (this.action != null) {
      json[r'action'] = this.action;
    } else {
      json[r'action'] = null;
    }
    if (this.parameters != null) {
      json[r'parameters'] = this.parameters;
    } else {
      json[r'parameters'] = null;
    }
    return json;
  }

  /// Returns a new [SubmitActionRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SubmitActionRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SubmitActionRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SubmitActionRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SubmitActionRequest(
        sourceCi: ConfigurationItemReference.fromJson(json[r'sourceCi']),
        targetCis: ConfigurationItemReference.listFromJson(json[r'targetCis']),
        action: SerializableAction.fromJson(json[r'action']),
        parameters: SerializableFieldGroup.fromJson(json[r'parameters']),
      );
    }
    return null;
  }

  static List<SubmitActionRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SubmitActionRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SubmitActionRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SubmitActionRequest> mapFromJson(dynamic json) {
    final map = <String, SubmitActionRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SubmitActionRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SubmitActionRequest-objects as value to a dart map
  static Map<String, List<SubmitActionRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SubmitActionRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SubmitActionRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

