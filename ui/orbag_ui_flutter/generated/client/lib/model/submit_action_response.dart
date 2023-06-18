//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class SubmitActionResponse {
  /// Returns a new [SubmitActionResponse] instance.
  SubmitActionResponse({
    this.executionStatus,
    this.errorMessage,
    this.validationErrors = const [],
    this.requestValid,
    this.consequences,
    this.message,
    this.link,
    this.jobId,
  });

  SubmitActionResponseExecutionStatusEnum? executionStatus;

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

  SubmitActionResponseConsequencesEnum? consequences;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? message;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? link;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? jobId;

  @override
  bool operator ==(Object other) => identical(this, other) || other is SubmitActionResponse &&
     other.executionStatus == executionStatus &&
     other.errorMessage == errorMessage &&
     other.validationErrors == validationErrors &&
     other.requestValid == requestValid &&
     other.consequences == consequences &&
     other.message == message &&
     other.link == link &&
     other.jobId == jobId;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (executionStatus == null ? 0 : executionStatus!.hashCode) +
    (errorMessage == null ? 0 : errorMessage!.hashCode) +
    (validationErrors.hashCode) +
    (requestValid == null ? 0 : requestValid!.hashCode) +
    (consequences == null ? 0 : consequences!.hashCode) +
    (message == null ? 0 : message!.hashCode) +
    (link == null ? 0 : link!.hashCode) +
    (jobId == null ? 0 : jobId!.hashCode);

  @override
  String toString() => 'SubmitActionResponse[executionStatus=$executionStatus, errorMessage=$errorMessage, validationErrors=$validationErrors, requestValid=$requestValid, consequences=$consequences, message=$message, link=$link, jobId=$jobId]';

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
    if (this.consequences != null) {
      json[r'consequences'] = this.consequences;
    } else {
      json[r'consequences'] = null;
    }
    if (this.message != null) {
      json[r'message'] = this.message;
    } else {
      json[r'message'] = null;
    }
    if (this.link != null) {
      json[r'link'] = this.link;
    } else {
      json[r'link'] = null;
    }
    if (this.jobId != null) {
      json[r'jobId'] = this.jobId;
    } else {
      json[r'jobId'] = null;
    }
    return json;
  }

  /// Returns a new [SubmitActionResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static SubmitActionResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "SubmitActionResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "SubmitActionResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return SubmitActionResponse(
        executionStatus: SubmitActionResponseExecutionStatusEnum.fromJson(json[r'executionStatus']),
        errorMessage: mapValueOfType<String>(json, r'errorMessage'),
        validationErrors: ValidationError.listFromJson(json[r'validationErrors']),
        requestValid: mapValueOfType<bool>(json, r'requestValid'),
        consequences: SubmitActionResponseConsequencesEnum.fromJson(json[r'consequences']),
        message: mapValueOfType<String>(json, r'message'),
        link: mapValueOfType<String>(json, r'link'),
        jobId: mapValueOfType<String>(json, r'jobId'),
      );
    }
    return null;
  }

  static List<SubmitActionResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SubmitActionResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SubmitActionResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, SubmitActionResponse> mapFromJson(dynamic json) {
    final map = <String, SubmitActionResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = SubmitActionResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of SubmitActionResponse-objects as value to a dart map
  static Map<String, List<SubmitActionResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<SubmitActionResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = SubmitActionResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}


class SubmitActionResponseExecutionStatusEnum {
  /// Instantiate a new enum with the provided [value].
  const SubmitActionResponseExecutionStatusEnum._(this.value);

  /// The underlying value of this enum member.
  final String value;

  @override
  String toString() => value;

  String toJson() => value;

  static const VALIDATION_FAILED = SubmitActionResponseExecutionStatusEnum._(r'VALIDATION_FAILED');
  static const SUCCEEDED = SubmitActionResponseExecutionStatusEnum._(r'SUCCEEDED');
  static const FAILED = SubmitActionResponseExecutionStatusEnum._(r'FAILED');

  /// List of all possible values in this [enum][SubmitActionResponseExecutionStatusEnum].
  static const values = <SubmitActionResponseExecutionStatusEnum>[
    VALIDATION_FAILED,
    SUCCEEDED,
    FAILED,
  ];

  static SubmitActionResponseExecutionStatusEnum? fromJson(dynamic value) => SubmitActionResponseExecutionStatusEnumTypeTransformer().decode(value);

  static List<SubmitActionResponseExecutionStatusEnum> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SubmitActionResponseExecutionStatusEnum>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SubmitActionResponseExecutionStatusEnum.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }
}

/// Transformation class that can [encode] an instance of [SubmitActionResponseExecutionStatusEnum] to String,
/// and [decode] dynamic data back to [SubmitActionResponseExecutionStatusEnum].
class SubmitActionResponseExecutionStatusEnumTypeTransformer {
  factory SubmitActionResponseExecutionStatusEnumTypeTransformer() => _instance ??= const SubmitActionResponseExecutionStatusEnumTypeTransformer._();

  const SubmitActionResponseExecutionStatusEnumTypeTransformer._();

  String encode(SubmitActionResponseExecutionStatusEnum data) => data.value;

  /// Decodes a [dynamic value][data] to a SubmitActionResponseExecutionStatusEnum.
  ///
  /// If [allowNull] is true and the [dynamic value][data] cannot be decoded successfully,
  /// then null is returned. However, if [allowNull] is false and the [dynamic value][data]
  /// cannot be decoded successfully, then an [UnimplementedError] is thrown.
  ///
  /// The [allowNull] is very handy when an API changes and a new enum value is added or removed,
  /// and users are still using an old app with the old code.
  SubmitActionResponseExecutionStatusEnum? decode(dynamic data, {bool allowNull = true}) {
    if (data != null) {
      switch (data) {
        case r'VALIDATION_FAILED': return SubmitActionResponseExecutionStatusEnum.VALIDATION_FAILED;
        case r'SUCCEEDED': return SubmitActionResponseExecutionStatusEnum.SUCCEEDED;
        case r'FAILED': return SubmitActionResponseExecutionStatusEnum.FAILED;
        default:
          if (!allowNull) {
            throw ArgumentError('Unknown enum value to decode: $data');
          }
      }
    }
    return null;
  }

  /// Singleton [SubmitActionResponseExecutionStatusEnumTypeTransformer] instance.
  static SubmitActionResponseExecutionStatusEnumTypeTransformer? _instance;
}



class SubmitActionResponseConsequencesEnum {
  /// Instantiate a new enum with the provided [value].
  const SubmitActionResponseConsequencesEnum._(this.value);

  /// The underlying value of this enum member.
  final String value;

  @override
  String toString() => value;

  String toJson() => value;

  static const NONE = SubmitActionResponseConsequencesEnum._(r'NONE');
  static const UNDEFINED = SubmitActionResponseConsequencesEnum._(r'UNDEFINED');
  static const DELETED = SubmitActionResponseConsequencesEnum._(r'DELETED');
  static const UPDATED = SubmitActionResponseConsequencesEnum._(r'UPDATED');

  /// List of all possible values in this [enum][SubmitActionResponseConsequencesEnum].
  static const values = <SubmitActionResponseConsequencesEnum>[
    NONE,
    UNDEFINED,
    DELETED,
    UPDATED,
  ];

  static SubmitActionResponseConsequencesEnum? fromJson(dynamic value) => SubmitActionResponseConsequencesEnumTypeTransformer().decode(value);

  static List<SubmitActionResponseConsequencesEnum> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <SubmitActionResponseConsequencesEnum>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = SubmitActionResponseConsequencesEnum.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }
}

/// Transformation class that can [encode] an instance of [SubmitActionResponseConsequencesEnum] to String,
/// and [decode] dynamic data back to [SubmitActionResponseConsequencesEnum].
class SubmitActionResponseConsequencesEnumTypeTransformer {
  factory SubmitActionResponseConsequencesEnumTypeTransformer() => _instance ??= const SubmitActionResponseConsequencesEnumTypeTransformer._();

  const SubmitActionResponseConsequencesEnumTypeTransformer._();

  String encode(SubmitActionResponseConsequencesEnum data) => data.value;

  /// Decodes a [dynamic value][data] to a SubmitActionResponseConsequencesEnum.
  ///
  /// If [allowNull] is true and the [dynamic value][data] cannot be decoded successfully,
  /// then null is returned. However, if [allowNull] is false and the [dynamic value][data]
  /// cannot be decoded successfully, then an [UnimplementedError] is thrown.
  ///
  /// The [allowNull] is very handy when an API changes and a new enum value is added or removed,
  /// and users are still using an old app with the old code.
  SubmitActionResponseConsequencesEnum? decode(dynamic data, {bool allowNull = true}) {
    if (data != null) {
      switch (data) {
        case r'NONE': return SubmitActionResponseConsequencesEnum.NONE;
        case r'UNDEFINED': return SubmitActionResponseConsequencesEnum.UNDEFINED;
        case r'DELETED': return SubmitActionResponseConsequencesEnum.DELETED;
        case r'UPDATED': return SubmitActionResponseConsequencesEnum.UPDATED;
        default:
          if (!allowNull) {
            throw ArgumentError('Unknown enum value to decode: $data');
          }
      }
    }
    return null;
  }

  /// Singleton [SubmitActionResponseConsequencesEnumTypeTransformer] instance.
  static SubmitActionResponseConsequencesEnumTypeTransformer? _instance;
}


