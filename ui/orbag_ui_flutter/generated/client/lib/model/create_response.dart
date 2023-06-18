//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class CreateResponse {
  /// Returns a new [CreateResponse] instance.
  CreateResponse({
    this.executionStatus,
    this.errorMessage,
    this.validationErrors = const [],
    this.requestValid,
    this.configurationItem,
  });

  CreateResponseExecutionStatusEnum? executionStatus;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? errorMessage;

  List<ValidationError> validationErrors;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? requestValid;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? configurationItem;

  @override
  bool operator ==(Object other) => identical(this, other) || other is CreateResponse &&
     other.executionStatus == executionStatus &&
     other.errorMessage == errorMessage &&
     other.validationErrors == validationErrors &&
     other.requestValid == requestValid &&
     other.configurationItem == configurationItem;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (executionStatus == null ? 0 : executionStatus!.hashCode) +
    (errorMessage == null ? 0 : errorMessage!.hashCode) +
    (validationErrors.hashCode) +
    (requestValid == null ? 0 : requestValid!.hashCode) +
    (configurationItem == null ? 0 : configurationItem!.hashCode);

  @override
  String toString() => 'CreateResponse[executionStatus=$executionStatus, errorMessage=$errorMessage, validationErrors=$validationErrors, requestValid=$requestValid, configurationItem=$configurationItem]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.executionStatus != null) {
      json[r'executionStatus'] = this.executionStatus;
    } else {
      json[r'executionStatus'] = null;
    }
    if (this.errorMessage != null) {
      json[r'errorMessage'] = this.errorMessage;
    } else {
      json[r'errorMessage'] = null;
    }
      json[r'validationErrors'] = this.validationErrors;
    if (this.requestValid != null) {
      json[r'requestValid'] = this.requestValid;
    } else {
      json[r'requestValid'] = null;
    }
    if (this.configurationItem != null) {
      json[r'configurationItem'] = this.configurationItem;
    } else {
      json[r'configurationItem'] = null;
    }
    return json;
  }

  /// Returns a new [CreateResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static CreateResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "CreateResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "CreateResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return CreateResponse(
        executionStatus: CreateResponseExecutionStatusEnum.fromJson(json[r'executionStatus']),
        errorMessage: mapValueOfType<String>(json, r'errorMessage'),
        validationErrors: ValidationError.listFromJson(json[r'validationErrors']),
        requestValid: mapValueOfType<bool>(json, r'requestValid'),
        configurationItem: ConfigurationItemReference.fromJson(json[r'configurationItem']),
      );
    }
    return null;
  }

  static List<CreateResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <CreateResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = CreateResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, CreateResponse> mapFromJson(dynamic json) {
    final map = <String, CreateResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = CreateResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of CreateResponse-objects as value to a dart map
  static Map<String, List<CreateResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<CreateResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = CreateResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}


class CreateResponseExecutionStatusEnum {
  /// Instantiate a new enum with the provided [value].
  const CreateResponseExecutionStatusEnum._(this.value);

  /// The underlying value of this enum member.
  final String value;

  @override
  String toString() => value;

  String toJson() => value;

  static const VALIDATION_FAILED = CreateResponseExecutionStatusEnum._(r'VALIDATION_FAILED');
  static const SUCCEEDED = CreateResponseExecutionStatusEnum._(r'SUCCEEDED');
  static const FAILED = CreateResponseExecutionStatusEnum._(r'FAILED');

  /// List of all possible values in this [enum][CreateResponseExecutionStatusEnum].
  static const values = <CreateResponseExecutionStatusEnum>[
    VALIDATION_FAILED,
    SUCCEEDED,
    FAILED,
  ];

  static CreateResponseExecutionStatusEnum? fromJson(dynamic value) => CreateResponseExecutionStatusEnumTypeTransformer().decode(value);

  static List<CreateResponseExecutionStatusEnum> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <CreateResponseExecutionStatusEnum>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = CreateResponseExecutionStatusEnum.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }
}

/// Transformation class that can [encode] an instance of [CreateResponseExecutionStatusEnum] to String,
/// and [decode] dynamic data back to [CreateResponseExecutionStatusEnum].
class CreateResponseExecutionStatusEnumTypeTransformer {
  factory CreateResponseExecutionStatusEnumTypeTransformer() => _instance ??= const CreateResponseExecutionStatusEnumTypeTransformer._();

  const CreateResponseExecutionStatusEnumTypeTransformer._();

  String encode(CreateResponseExecutionStatusEnum data) => data.value;

  /// Decodes a [dynamic value][data] to a CreateResponseExecutionStatusEnum.
  ///
  /// If [allowNull] is true and the [dynamic value][data] cannot be decoded successfully,
  /// then null is returned. However, if [allowNull] is false and the [dynamic value][data]
  /// cannot be decoded successfully, then an [UnimplementedError] is thrown.
  ///
  /// The [allowNull] is very handy when an API changes and a new enum value is added or removed,
  /// and users are still using an old app with the old code.
  CreateResponseExecutionStatusEnum? decode(dynamic data, {bool allowNull = true}) {
    if (data != null) {
      switch (data) {
        case r'VALIDATION_FAILED': return CreateResponseExecutionStatusEnum.VALIDATION_FAILED;
        case r'SUCCEEDED': return CreateResponseExecutionStatusEnum.SUCCEEDED;
        case r'FAILED': return CreateResponseExecutionStatusEnum.FAILED;
        default:
          if (!allowNull) {
            throw ArgumentError('Unknown enum value to decode: $data');
          }
      }
    }
    return null;
  }

  /// Singleton [CreateResponseExecutionStatusEnumTypeTransformer] instance.
  static CreateResponseExecutionStatusEnumTypeTransformer? _instance;
}


