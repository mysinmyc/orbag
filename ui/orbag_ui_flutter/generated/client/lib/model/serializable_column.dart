//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SerializableColumn {
  /// Returns a new [SerializableColumn] instance.
  SerializableColumn({
    this.name,
    this.type,
    this.displayLabel,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? name;

  SerializableColumnTypeEnum? type;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? displayLabel;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SerializableColumn &&
     other.name == name &&
     other.type == type &&
     other.displayLabel == displayLabel;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (name == null ? 0 : name!.hashCode) +
    (type == null ? 0 : type!.hashCode) +
    (displayLabel == null ? 0 : displayLabel!.hashCode);

  @override
  String toString() => 'SerializableColumn[name=$name, type=$type, displayLabel=$displayLabel]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.name != null) {
      json[r'name'] = this.name;
    } else {
      json[r'name'] = null;
    }
    if (this.type != null) {
      json[r'type'] = this.type;
    } else {
      json[r'type'] = null;
    }
    if (this.displayLabel != null) {
      json[r'displayLabel'] = this.displayLabel;
    } else {
      json[r'displayLabel'] = null;
    }
    return json;
  }

  /// Returns a new [SerializableColumn] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SerializableColumn? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SerializableColumn[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SerializableColumn[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SerializableColumn(
        name: mapValueOfType<String>(json, r'name'),
        type: SerializableColumnTypeEnum.fromJson(json[r'type']),
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
      );
    }
    return null;
  }

  static List<SerializableColumn> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableColumn>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableColumn.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SerializableColumn> mapFromJson(dynamic json) {
    final map = <String, SerializableColumn>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SerializableColumn.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SerializableColumn-objects as value to a dart map
  static Map<String, List<SerializableColumn>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SerializableColumn>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SerializableColumn.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}


class SerializableColumnTypeEnum {
  /// Instantiate a new enum with the provided [value].
  const SerializableColumnTypeEnum._(this.value);

  /// The underlying value of this enum member.
  final String value;

  @override
  String toString() => value;

  String toJson() => value;

  static const primitive = SerializableColumnTypeEnum._(r'Primitive');
  static const reference = SerializableColumnTypeEnum._(r'Reference');

  /// List of all possible values in this [enum][SerializableColumnTypeEnum].
  static const values = <SerializableColumnTypeEnum>[
    primitive,
    reference,
  ];

  static SerializableColumnTypeEnum? fromJson(dynamic value) => SerializableColumnTypeEnumTypeTransformer().decode(value);

  static List<SerializableColumnTypeEnum> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SerializableColumnTypeEnum>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SerializableColumnTypeEnum.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }
}

/// Transformation class that can [encode] an instance of [SerializableColumnTypeEnum] to String,
/// and [decode] dynamic data back to [SerializableColumnTypeEnum].
class SerializableColumnTypeEnumTypeTransformer {
  factory SerializableColumnTypeEnumTypeTransformer() => _instance ??= const SerializableColumnTypeEnumTypeTransformer._();

  const SerializableColumnTypeEnumTypeTransformer._();

  String encode(SerializableColumnTypeEnum data) => data.value;

  /// Decodes a [dynamic value][data] to a SerializableColumnTypeEnum.
  ///
  /// If [allowNull] is true and the [dynamic value][data] cannot be decoded successfully,
  /// then null is returned. However, if [allowNull] is false and the [dynamic value][data]
  /// cannot be decoded successfully, then an [UnimplementedError] is thrown.
  ///
  /// The [allowNull] is very handy when an API changes and a new enum value is added or removed,
  /// and users are still using an old app with the old code.
  SerializableColumnTypeEnum? decode(dynamic data, {bool allowNull = true}) {
    if (data != null) {
      switch (data) {
        case r'Primitive': return SerializableColumnTypeEnum.primitive;
        case r'Reference': return SerializableColumnTypeEnum.reference;
        default:
          if (!allowNull) {
            throw ArgumentError('Unknown enum value to decode: $data');
          }
      }
    }
    return null;
  }

  /// Singleton [SerializableColumnTypeEnumTypeTransformer] instance.
  static SerializableColumnTypeEnumTypeTransformer? _instance;
}


