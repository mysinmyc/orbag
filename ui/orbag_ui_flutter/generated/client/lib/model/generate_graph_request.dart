//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class GenerateGraphRequest {
  /// Returns a new [GenerateGraphRequest] instance.
  GenerateGraphRequest({
    this.startingCi,
    this.path,
    this.previousSteps = const [],
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? startingCi;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializablePath? path;

  List<ConfigurationItemReference> previousSteps;

  @override
  bool operator ==(Object other) => identical(this, other) || other is GenerateGraphRequest &&
     other.startingCi == startingCi &&
     other.path == path &&
     other.previousSteps == previousSteps;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (startingCi == null ? 0 : startingCi!.hashCode) +
    (path == null ? 0 : path!.hashCode) +
    (previousSteps.hashCode);

  @override
  String toString() => 'GenerateGraphRequest[startingCi=$startingCi, path=$path, previousSteps=$previousSteps]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.startingCi != null) {
      json[r'startingCi'] = this.startingCi;
    } else {
      json[r'startingCi'] = null;
    }
    if (this.path != null) {
      json[r'path'] = this.path;
    } else {
      json[r'path'] = null;
    }
      json[r'previousSteps'] = this.previousSteps;
    return json;
  }

  /// Returns a new [GenerateGraphRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static GenerateGraphRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "GenerateGraphRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "GenerateGraphRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return GenerateGraphRequest(
        startingCi: ConfigurationItemReference.fromJson(json[r'startingCi']),
        path: SerializablePath.fromJson(json[r'path']),
        previousSteps: ConfigurationItemReference.listFromJson(json[r'previousSteps']),
      );
    }
    return null;
  }

  static List<GenerateGraphRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <GenerateGraphRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = GenerateGraphRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, GenerateGraphRequest> mapFromJson(dynamic json) {
    final map = <String, GenerateGraphRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = GenerateGraphRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of GenerateGraphRequest-objects as value to a dart map
  static Map<String, List<GenerateGraphRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<GenerateGraphRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = GenerateGraphRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

