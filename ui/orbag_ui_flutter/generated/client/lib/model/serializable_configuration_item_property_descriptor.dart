//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializableConfigurationItemPropertyDescriptor {
  /// Returns a new [SerializableConfigurationItemPropertyDescriptor] instance.
  SerializableConfigurationItemPropertyDescriptor({
    this.name,
    this.displayLabel,
    this.description,
    this.category,
    this.readOnly,
    this.configurationItemReference,
    this.referencedConfigurationItemType,
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
  String? description;

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
  bool? readOnly;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? configurationItemReference;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? referencedConfigurationItemType;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializableConfigurationItemPropertyDescriptor &&
     other.name == name &&
     other.displayLabel == displayLabel &&
     other.description == description &&
     other.category == category &&
     other.readOnly == readOnly &&
     other.configurationItemReference == configurationItemReference &&
     other.referencedConfigurationItemType == referencedConfigurationItemType;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (name == null ? 0 : name!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode) +
    (description == null ? 0 : description!.hashCode) +
    (category == null ? 0 : category!.hashCode) +
    (readOnly == null ? 0 : readOnly!.hashCode) +
    (configurationItemReference == null ? 0 : configurationItemReference!.hashCode) +
    (referencedConfigurationItemType == null ? 0 : referencedConfigurationItemType!.hashCode);

  @override
  String toString() => 'SerializableConfigurationItemPropertyDescriptor[name=$name, displayLabel=$displayLabel, description=$description, category=$category, readOnly=$readOnly, configurationItemReference=$configurationItemReference, referencedConfigurationItemType=$referencedConfigurationItemType]';

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
    if (this.description != null) {
      json[r'description'] = this.description;
    } else {
      json[r'description'] = null;
    }
    if (this.category != null) {
      json[r'category'] = this.category;
    } else {
      json[r'category'] = null;
    }
    if (this.readOnly != null) {
      json[r'readOnly'] = this.readOnly;
    } else {
      json[r'readOnly'] = null;
    }
    if (this.configurationItemReference != null) {
      json[r'configurationItemReference'] = this.configurationItemReference;
    } else {
      json[r'configurationItemReference'] = null;
    }
    if (this.referencedConfigurationItemType != null) {
      json[r'referencedConfigurationItemType'] = this.referencedConfigurationItemType;
    } else {
      json[r'referencedConfigurationItemType'] = null;
    }
    return json;
  }

  /// Returns a new [SerializableConfigurationItemPropertyDescriptor] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializableConfigurationItemPropertyDescriptor? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializableConfigurationItemPropertyDescriptor[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializableConfigurationItemPropertyDescriptor[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializableConfigurationItemPropertyDescriptor(
        name: mapValueOfType<String>(json, r'name'),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
        description: mapValueOfType<String>(json, r'description'),
        category: mapValueOfType<String>(json, r'category'),
        readOnly: mapValueOfType<bool>(json, r'readOnly'),
        configurationItemReference: mapValueOfType<bool>(json, r'configurationItemReference'),
        referencedConfigurationItemType: mapValueOfType<String>(json, r'referencedConfigurationItemType'),
      );
    }
    return null;
  }

  static List<SerializableConfigurationItemPropertyDescriptor> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableConfigurationItemPropertyDescriptor>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableConfigurationItemPropertyDescriptor.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializableConfigurationItemPropertyDescriptor> mapFromJson(dynamic json) {
    final map = <String, SerializableConfigurationItemPropertyDescriptor>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializableConfigurationItemPropertyDescriptor.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializableConfigurationItemPropertyDescriptor-objects as value to a dart map
  static Map<String, List<SerializableConfigurationItemPropertyDescriptor>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializableConfigurationItemPropertyDescriptor>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializableConfigurationItemPropertyDescriptor.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

