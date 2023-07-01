//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class ExecuteLaterResponse {
  /// Returns a new [ExecuteLaterResponse] instance.
  ExecuteLaterResponse({
    this.searchId,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? searchId;

  @override
  bool operator ==(Object other) => identical(this, other) || other is ExecuteLaterResponse &&
     other.searchId == searchId;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (searchId == null ? 0 : searchId!.hashCode);

  @override
  String toString() => 'ExecuteLaterResponse[searchId=$searchId]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.searchId != null) {
      json[r'searchId'] = this.searchId;
    } else {
      json[r'searchId'] = null;
    }
    return json;
  }

  /// Returns a new [ExecuteLaterResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static ExecuteLaterResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "ExecuteLaterResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "ExecuteLaterResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return ExecuteLaterResponse(
        searchId: mapValueOfType<String>(json, r'searchId'),
      );
    }
    return null;
  }

  static List<ExecuteLaterResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <ExecuteLaterResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = ExecuteLaterResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, ExecuteLaterResponse> mapFromJson(dynamic json) {
    final map = <String, ExecuteLaterResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = ExecuteLaterResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of ExecuteLaterResponse-objects as value to a dart map
  static Map<String, List<ExecuteLaterResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<ExecuteLaterResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = ExecuteLaterResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

