//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class ConfigurationItemReferenceField {
  /// Returns a new [ConfigurationItemReferenceField] instance.
  ConfigurationItemReferenceField({
    this.name,
    this.displayLabel,
    this.category,
    this.value,
    this.readOnly,
    this.changed,
    this.configurationItemType,
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
  String? category;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? value;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? readOnly;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? changed;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? configurationItemType;

  @override
  bool operator ==(Object other) => identical(this, other) || other is ConfigurationItemReferenceField &&
     other.name == name &&
     other.displayLabel == displayLabel &&
     other.category == category &&
     other.value == value &&
     other.readOnly == readOnly &&
     other.changed == changed &&
     other.configurationItemType == configurationItemType;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (name == null ? 0 : name!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode) +
    (category == null ? 0 : category!.hashCode) +
    (value == null ? 0 : value!.hashCode) +
    (readOnly == null ? 0 : readOnly!.hashCode) +
    (changed == null ? 0 : changed!.hashCode) +
    (configurationItemType == null ? 0 : configurationItemType!.hashCode);

  @override
  String toString() => 'ConfigurationItemReferenceField[name=$name, displayLabel=$displayLabel, category=$category, value=$value, readOnly=$readOnly, changed=$changed, configurationItemType=$configurationItemType]';

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
    if (this.category != null) {
      json[r'category'] = this.category;
    } else {
      json[r'category'] = null;
    }
    if (this.value != null) {
      json[r'value'] = this.value;
    } else {
      json[r'value'] = null;
    }
    if (this.readOnly != null) {
      json[r'readOnly'] = this.readOnly;
    } else {
      json[r'readOnly'] = null;
    }
    if (this.changed != null) {
      json[r'changed'] = this.changed;
    } else {
      json[r'changed'] = null;
    }
    if (this.configurationItemType != null) {
      json[r'configurationItemType'] = this.configurationItemType;
    } else {
      json[r'configurationItemType'] = null;
    }
    return json;
  }

  /// Returns a new [ConfigurationItemReferenceField] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static ConfigurationItemReferenceField? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "ConfigurationItemReferenceField[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "ConfigurationItemReferenceField[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return ConfigurationItemReferenceField(
        name: mapValueOfType<String>(json, r'name'),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
        category: mapValueOfType<String>(json, r'category'),
        value: ConfigurationItemReference.fromJson(json[r'value']),
        readOnly: mapValueOfType<bool>(json, r'readOnly'),
        changed: mapValueOfType<bool>(json, r'changed'),
        configurationItemType: mapValueOfType<String>(json, r'configurationItemType'),
      );
    }
    return null;
  }

  static List<ConfigurationItemReferenceField> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <ConfigurationItemReferenceField>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = ConfigurationItemReferenceField.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, ConfigurationItemReferenceField> mapFromJson(dynamic json) {
    final map = <String, ConfigurationItemReferenceField>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = ConfigurationItemReferenceField.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of ConfigurationItemReferenceField-objects as value to a dart map
  static Map<String, List<ConfigurationItemReferenceField>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<ConfigurationItemReferenceField>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = ConfigurationItemReferenceField.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

