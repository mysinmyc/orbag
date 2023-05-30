//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class ConfigurationItemReference {
  /// Returns a new [ConfigurationItemReference] instance.
  ConfigurationItemReference({
    this.identifier,
    this.configurationItemType,
    this.displayLabel,
    this.configurationItemTypeDisplayLabel,
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
  String? configurationItemType;

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
  String? configurationItemTypeDisplayLabel;

  @override
  bool operator ==(Object other) => identical(this, other) || other is ConfigurationItemReference &&
     other.identifier == identifier &&
     other.configurationItemType == configurationItemType &&
     other.displayLabel == displayLabel &&
     other.configurationItemTypeDisplayLabel == configurationItemTypeDisplayLabel;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (identifier == null ? 0 : identifier!.hashCode) +
    (configurationItemType == null ? 0 : configurationItemType!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode) +
    (configurationItemTypeDisplayLabel == null ? 0 : configurationItemTypeDisplayLabel!.hashCode);

  @override
  String toString() => 'ConfigurationItemReference[identifier=$identifier, configurationItemType=$configurationItemType, displayLabel=$displayLabel, configurationItemTypeDisplayLabel=$configurationItemTypeDisplayLabel]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.identifier != null) {
      json[r'identifier'] = this.identifier;
    } else {
      json[r'identifier'] = null;
    }
    if (this.configurationItemType != null) {
      json[r'configurationItemType'] = this.configurationItemType;
    } else {
      json[r'configurationItemType'] = null;
    }
    if (this.displayLabel != null) {
      json[r'displayLabel'] = this.displayLabel;
    } else {
      json[r'displayLabel'] = null;
    }
    if (this.configurationItemTypeDisplayLabel != null) {
      json[r'configurationItemTypeDisplayLabel'] = this.configurationItemTypeDisplayLabel;
    } else {
      json[r'configurationItemTypeDisplayLabel'] = null;
    }
    return json;
  }

  /// Returns a new [ConfigurationItemReference] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static ConfigurationItemReference? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "ConfigurationItemReference[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "ConfigurationItemReference[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return ConfigurationItemReference(
        identifier: mapValueOfType<String>(json, r'identifier'),
        configurationItemType: mapValueOfType<String>(json, r'configurationItemType'),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
        configurationItemTypeDisplayLabel: mapValueOfType<String>(json, r'configurationItemTypeDisplayLabel'),
      );
    }
    return null;
  }

  static List<ConfigurationItemReference> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <ConfigurationItemReference>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = ConfigurationItemReference.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, ConfigurationItemReference> mapFromJson(dynamic json) {
    final map = <String, ConfigurationItemReference>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = ConfigurationItemReference.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of ConfigurationItemReference-objects as value to a dart map
  static Map<String, List<ConfigurationItemReference>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<ConfigurationItemReference>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = ConfigurationItemReference.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

