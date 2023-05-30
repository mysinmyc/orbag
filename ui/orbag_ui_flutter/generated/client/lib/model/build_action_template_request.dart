//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class BuildActionTemplateRequest {
  /// Returns a new [BuildActionTemplateRequest] instance.
  BuildActionTemplateRequest({
    this.sourceCi,
    this.targetCis = const [],
    this.action,
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

  @override
  bool operator ==(Object other) => identical(this, other) || other is BuildActionTemplateRequest &&
     other.sourceCi == sourceCi &&
     other.targetCis == targetCis &&
     other.action == action;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (sourceCi == null ? 0 : sourceCi!.hashCode) +
    (targetCis.hashCode) +
    (action == null ? 0 : action!.hashCode);

  @override
  String toString() => 'BuildActionTemplateRequest[sourceCi=$sourceCi, targetCis=$targetCis, action=$action]';

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
    return json;
  }

  /// Returns a new [BuildActionTemplateRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static BuildActionTemplateRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "BuildActionTemplateRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "BuildActionTemplateRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return BuildActionTemplateRequest(
        sourceCi: ConfigurationItemReference.fromJson(json[r'sourceCi']),
        targetCis: ConfigurationItemReference.listFromJson(json[r'targetCis']),
        action: SerializableAction.fromJson(json[r'action']),
      );
    }
    return null;
  }

  static List<BuildActionTemplateRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <BuildActionTemplateRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = BuildActionTemplateRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, BuildActionTemplateRequest> mapFromJson(dynamic json) {
    final map = <String, BuildActionTemplateRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = BuildActionTemplateRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of BuildActionTemplateRequest-objects as value to a dart map
  static Map<String, List<BuildActionTemplateRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<BuildActionTemplateRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = BuildActionTemplateRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

