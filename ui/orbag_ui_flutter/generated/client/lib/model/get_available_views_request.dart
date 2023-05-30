//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class GetAvailableViewsRequest {
  /// Returns a new [GetAvailableViewsRequest] instance.
  GetAvailableViewsRequest({
    this.targetCi,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? targetCi;

  @override
  bool operator ==(Object other) => identical(this, other) || other is GetAvailableViewsRequest &&
     other.targetCi == targetCi;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (targetCi == null ? 0 : targetCi!.hashCode);

  @override
  String toString() => 'GetAvailableViewsRequest[targetCi=$targetCi]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.targetCi != null) {
      json[r'targetCi'] = this.targetCi;
    } else {
      json[r'targetCi'] = null;
    }
    return json;
  }

  /// Returns a new [GetAvailableViewsRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static GetAvailableViewsRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "GetAvailableViewsRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "GetAvailableViewsRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return GetAvailableViewsRequest(
        targetCi: ConfigurationItemReference.fromJson(json[r'targetCi']),
      );
    }
    return null;
  }

  static List<GetAvailableViewsRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <GetAvailableViewsRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = GetAvailableViewsRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, GetAvailableViewsRequest> mapFromJson(dynamic json) {
    final map = <String, GetAvailableViewsRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = GetAvailableViewsRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of GetAvailableViewsRequest-objects as value to a dart map
  static Map<String, List<GetAvailableViewsRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<GetAvailableViewsRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = GetAvailableViewsRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

