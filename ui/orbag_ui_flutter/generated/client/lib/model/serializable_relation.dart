//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializableRelation {
  /// Returns a new [SerializableRelation] instance.
  SerializableRelation({
    this.name,
    this.displayLabel,
    this.startingCi,
    this.endCi,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? name;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? displayLabel;

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
  ConfigurationItemReference? endCi;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializableRelation &&
     other.name == name &&
     other.displayLabel == displayLabel &&
     other.startingCi == startingCi &&
     other.endCi == endCi;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (name == null ? 0 : name!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode) +
    (startingCi == null ? 0 : startingCi!.hashCode) +
    (endCi == null ? 0 : endCi!.hashCode);

  @override
  String toString() => 'SerializableRelation[name=$name, displayLabel=$displayLabel, startingCi=$startingCi, endCi=$endCi]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.name != null) {
      json[r'name'] = this.name;
    } else {
      json[r'name'] = null;
    }
    if (this.displayLabel != null) {
      json[r'displayLabel'] = this.displayLabel;
    } else {
      json[r'displayLabel'] = null;
    }
    if (this.startingCi != null) {
      json[r'startingCi'] = this.startingCi;
    } else {
      json[r'startingCi'] = null;
    }
    if (this.endCi != null) {
      json[r'endCi'] = this.endCi;
    } else {
      json[r'endCi'] = null;
    }
    return json;
  }

  /// Returns a new [SerializableRelation] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializableRelation? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializableRelation[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializableRelation[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializableRelation(
        name: mapValueOfType<String>(json, r'name'),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
        startingCi: ConfigurationItemReference.fromJson(json[r'startingCi']),
        endCi: ConfigurationItemReference.fromJson(json[r'endCi']),
      );
    }
    return null;
  }

  static List<SerializableRelation> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableRelation>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableRelation.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializableRelation> mapFromJson(dynamic json) {
    final map = <String, SerializableRelation>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializableRelation.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializableRelation-objects as value to a dart map
  static Map<String, List<SerializableRelation>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializableRelation>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializableRelation.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

