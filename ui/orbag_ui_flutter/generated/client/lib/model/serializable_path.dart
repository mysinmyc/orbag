//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializablePath {
  /// Returns a new [SerializablePath] instance.
  SerializablePath({
    this.identifier,
    this.displayLabel,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? identifier;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? displayLabel;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializablePath &&
     other.identifier == identifier &&
     other.displayLabel == displayLabel;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (identifier == null ? 0 : identifier!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode);

  @override
  String toString() => 'SerializablePath[identifier=$identifier, displayLabel=$displayLabel]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.identifier != null) {
      json[r'identifier'] = this.identifier;
    } else {
      json[r'identifier'] = null;
    }
    if (this.displayLabel != null) {
      json[r'displayLabel'] = this.displayLabel;
    } else {
      json[r'displayLabel'] = null;
    }
    return json;
  }

  /// Returns a new [SerializablePath] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializablePath? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializablePath[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializablePath[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializablePath(
        identifier: mapValueOfType<String>(json, r'identifier'),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
      );
    }
    return null;
  }

  static List<SerializablePath> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializablePath>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializablePath.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializablePath> mapFromJson(dynamic json) {
    final map = <String, SerializablePath>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializablePath.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializablePath-objects as value to a dart map
  static Map<String, List<SerializablePath>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializablePath>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializablePath.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

