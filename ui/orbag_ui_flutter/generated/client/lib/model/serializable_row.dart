//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializableRow {
  /// Returns a new [SerializableRow] instance.
  SerializableRow({
    this.empty,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? empty;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializableRow &&
     other.empty == empty;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (empty == null ? 0 : empty!.hashCode);

  @override
  String toString() => 'SerializableRow[empty=$empty]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.empty != null) {
      json[r'empty'] = this.empty;
    } else {
      json[r'empty'] = null;
    }
    return json;
  }

  /// Returns a new [SerializableRow] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializableRow? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializableRow[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializableRow[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializableRow(
        empty: mapValueOfType<bool>(json, r'empty'),
      );
    }
    return null;
  }

  static List<SerializableRow> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableRow>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableRow.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializableRow> mapFromJson(dynamic json) {
    final map = <String, SerializableRow>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializableRow.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializableRow-objects as value to a dart map
  static Map<String, List<SerializableRow>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializableRow>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializableRow.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

