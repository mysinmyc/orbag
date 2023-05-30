//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializableFieldGroup {
  /// Returns a new [SerializableFieldGroup] instance.
  SerializableFieldGroup({
    this.booleanFields = const [],
    this.configurationItemReferenceFields = const [],
    this.enumFields = const [],
    this.numericFields = const [],
    this.stringFields = const [],
  });

  List<BooleanField> booleanFields;

  List<ConfigurationItemReferenceField> configurationItemReferenceFields;

  List<EnumField> enumFields;

  List<NumericField> numericFields;

  List<StringField> stringFields;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializableFieldGroup &&
     other.booleanFields == booleanFields &&
     other.configurationItemReferenceFields == configurationItemReferenceFields &&
     other.enumFields == enumFields &&
     other.numericFields == numericFields &&
     other.stringFields == stringFields;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (booleanFields.hashCode) +
    (configurationItemReferenceFields.hashCode) +
    (enumFields.hashCode) +
    (numericFields.hashCode) +
    (stringFields.hashCode);

  @override
  String toString() => 'SerializableFieldGroup[booleanFields=$booleanFields, configurationItemReferenceFields=$configurationItemReferenceFields, enumFields=$enumFields, numericFields=$numericFields, stringFields=$stringFields]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
      json[r'booleanFields'] = this.booleanFields;
      json[r'configurationItemReferenceFields'] = this.configurationItemReferenceFields;
      json[r'enumFields'] = this.enumFields;
      json[r'numericFields'] = this.numericFields;
      json[r'stringFields'] = this.stringFields;
    return json;
  }

  /// Returns a new [SerializableFieldGroup] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializableFieldGroup? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializableFieldGroup[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializableFieldGroup[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializableFieldGroup(
        booleanFields: BooleanField.listFromJson(json[r'booleanFields']),
        configurationItemReferenceFields: ConfigurationItemReferenceField.listFromJson(json[r'configurationItemReferenceFields']),
        enumFields: EnumField.listFromJson(json[r'enumFields']),
        numericFields: NumericField.listFromJson(json[r'numericFields']),
        stringFields: StringField.listFromJson(json[r'stringFields']),
      );
    }
    return null;
  }

  static List<SerializableFieldGroup> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableFieldGroup>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableFieldGroup.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializableFieldGroup> mapFromJson(dynamic json) {
    final map = <String, SerializableFieldGroup>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializableFieldGroup.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializableFieldGroup-objects as value to a dart map
  static Map<String, List<SerializableFieldGroup>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializableFieldGroup>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializableFieldGroup.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

