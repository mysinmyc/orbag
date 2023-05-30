//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SearchRequest {
  /// Returns a new [SearchRequest] instance.
  SearchRequest({
    this.configurationItemName,
    this.parameters,
    this.resultType,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? configurationItemName;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializableFieldGroup? parameters;

  SearchRequestResultTypeEnum? resultType;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SearchRequest &&
     other.configurationItemName == configurationItemName &&
     other.parameters == parameters &&
     other.resultType == resultType;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (configurationItemName == null ? 0 : configurationItemName!.hashCode) +
    (parameters == null ? 0 : parameters!.hashCode) +
    (resultType == null ? 0 : resultType!.hashCode);

  @override
  String toString() => 'SearchRequest[configurationItemName=$configurationItemName, parameters=$parameters, resultType=$resultType]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.configurationItemName != null) {
      json[r'configurationItemName'] = this.configurationItemName;
    } else {
      json[r'configurationItemName'] = null;
    }
    if (this.parameters != null) {
      json[r'parameters'] = this.parameters;
    } else {
      json[r'parameters'] = null;
    }
    if (this.resultType != null) {
      json[r'resultType'] = this.resultType;
    } else {
      json[r'resultType'] = null;
    }
    return json;
  }

  /// Returns a new [SearchRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SearchRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SearchRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SearchRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SearchRequest(
        configurationItemName: mapValueOfType<String>(json, r'configurationItemName'),
        parameters: SerializableFieldGroup.fromJson(json[r'parameters']),
        resultType: SearchRequestResultTypeEnum.fromJson(json[r'resultType']),
      );
    }
    return null;
  }

  static List<SearchRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SearchRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SearchRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SearchRequest> mapFromJson(dynamic json) {
    final map = <String, SearchRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SearchRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SearchRequest-objects as value to a dart map
  static Map<String, List<SearchRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SearchRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SearchRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}


class SearchRequestResultTypeEnum {
  /// Instantiate a new enum with the provided [value].
  const SearchRequestResultTypeEnum._(this.value);

  /// The underlying value of this enum member.
  final String value;

  @override
  String toString() => value;

  String toJson() => value;

  static const ROW_REFERENCE = SearchRequestResultTypeEnum._(r'ROW_REFERENCE');
  static const HIGHLIGHTED_FIELDS = SearchRequestResultTypeEnum._(r'HIGHLIGHTED_FIELDS');
  static const ALL_FIELDS = SearchRequestResultTypeEnum._(r'ALL_FIELDS');

  /// List of all possible values in this [enum][SearchRequestResultTypeEnum].
  static const values = <SearchRequestResultTypeEnum>[
    ROW_REFERENCE,
    HIGHLIGHTED_FIELDS,
    ALL_FIELDS,
  ];

  static SearchRequestResultTypeEnum? fromJson(dynamic value) => SearchRequestResultTypeEnumTypeTransformer().decode(value);

  static List<SearchRequestResultTypeEnum> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SearchRequestResultTypeEnum>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SearchRequestResultTypeEnum.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }
}

/// Transformation class that can [encode] an instance of [SearchRequestResultTypeEnum] to String,
/// and [decode] dynamic data back to [SearchRequestResultTypeEnum].
class SearchRequestResultTypeEnumTypeTransformer {
  factory SearchRequestResultTypeEnumTypeTransformer() => _instance ??= const SearchRequestResultTypeEnumTypeTransformer._();

  const SearchRequestResultTypeEnumTypeTransformer._();

  String encode(SearchRequestResultTypeEnum data) => data.value;

  /// Decodes a [dynamic value][data] to a SearchRequestResultTypeEnum.
  ///
  /// If [allowNull] is true and the [dynamic value][data] cannot be decoded successfully,
  /// then null is returned. However, if [allowNull] is false and the [dynamic value][data]
  /// cannot be decoded successfully, then an [UnimplementedError] is thrown.
  ///
  /// The [allowNull] is very handy when an API changes and a new enum value is added or removed,
  /// and users are still using an old app with the old code.
  SearchRequestResultTypeEnum? decode(dynamic data, {bool allowNull = true}) {
    if (data != null) {
      switch (data) {
        case r'ROW_REFERENCE': return SearchRequestResultTypeEnum.ROW_REFERENCE;
        case r'HIGHLIGHTED_FIELDS': return SearchRequestResultTypeEnum.HIGHLIGHTED_FIELDS;
        case r'ALL_FIELDS': return SearchRequestResultTypeEnum.ALL_FIELDS;
        default:
          if (!allowNull) {
            throw ArgumentError('Unknown enum value to decode: $data');
          }
      }
    }
    return null;
  }

  /// Singleton [SearchRequestResultTypeEnumTypeTransformer] instance.
  static SearchRequestResultTypeEnumTypeTransformer? _instance;
}


