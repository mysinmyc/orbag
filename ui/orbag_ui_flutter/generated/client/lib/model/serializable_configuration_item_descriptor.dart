//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializableConfigurationItemDescriptor {
  /// Returns a new [SerializableConfigurationItemDescriptor] instance.
  SerializableConfigurationItemDescriptor({
    this.name,
    this.displayLabel,
    this.category,
    this.allowCreation,
    this.readOnly,
    this.properties = const [],
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
  bool? allowCreation;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? readOnly;

  List<SerializableConfigurationItemPropertyDescriptor> properties;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializableConfigurationItemDescriptor &&
     other.name == name &&
     other.displayLabel == displayLabel &&
     other.category == category &&
     other.allowCreation == allowCreation &&
     other.readOnly == readOnly &&
     other.properties == properties;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (name == null ? 0 : name!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode) +
    (category == null ? 0 : category!.hashCode) +
    (allowCreation == null ? 0 : allowCreation!.hashCode) +
    (readOnly == null ? 0 : readOnly!.hashCode) +
    (properties.hashCode);

  @override
  String toString() => 'SerializableConfigurationItemDescriptor[name=$name, displayLabel=$displayLabel, category=$category, allowCreation=$allowCreation, readOnly=$readOnly, properties=$properties]';

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
    if (this.allowCreation != null) {
      json[r'allowCreation'] = this.allowCreation;
    } else {
      json[r'allowCreation'] = null;
    }
    if (this.readOnly != null) {
      json[r'readOnly'] = this.readOnly;
    } else {
      json[r'readOnly'] = null;
    }
      json[r'properties'] = this.properties;
    return json;
  }

  /// Returns a new [SerializableConfigurationItemDescriptor] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializableConfigurationItemDescriptor? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializableConfigurationItemDescriptor[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializableConfigurationItemDescriptor[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializableConfigurationItemDescriptor(
        name: mapValueOfType<String>(json, r'name'),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
        category: mapValueOfType<String>(json, r'category'),
        allowCreation: mapValueOfType<bool>(json, r'allowCreation'),
        readOnly: mapValueOfType<bool>(json, r'readOnly'),
        properties: SerializableConfigurationItemPropertyDescriptor.listFromJson(json[r'properties']),
      );
    }
    return null;
  }

  static List<SerializableConfigurationItemDescriptor> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableConfigurationItemDescriptor>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableConfigurationItemDescriptor.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializableConfigurationItemDescriptor> mapFromJson(dynamic json) {
    final map = <String, SerializableConfigurationItemDescriptor>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializableConfigurationItemDescriptor.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializableConfigurationItemDescriptor-objects as value to a dart map
  static Map<String, List<SerializableConfigurationItemDescriptor>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializableConfigurationItemDescriptor>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializableConfigurationItemDescriptor.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

