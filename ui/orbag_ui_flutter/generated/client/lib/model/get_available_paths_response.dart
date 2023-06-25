//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class GetAvailablePathsResponse {
  /// Returns a new [GetAvailablePathsResponse] instance.
  GetAvailablePathsResponse({
    this.availablePaths = const [],
  });

  List<SerializablePath> availablePaths;

  @override
  bool operator ==(Object other) => identical(this, other) || other is GetAvailablePathsResponse &&
     other.availablePaths == availablePaths;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (availablePaths.hashCode);

  @override
  String toString() => 'GetAvailablePathsResponse[availablePaths=$availablePaths]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
      json[r'availablePaths'] = this.availablePaths;
    return json;
  }

  /// Returns a new [GetAvailablePathsResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static GetAvailablePathsResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "GetAvailablePathsResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "GetAvailablePathsResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return GetAvailablePathsResponse(
        availablePaths: SerializablePath.listFromJson(json[r'availablePaths']),
      );
    }
    return null;
  }

  static List<GetAvailablePathsResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <GetAvailablePathsResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = GetAvailablePathsResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, GetAvailablePathsResponse> mapFromJson(dynamic json) {
    final map = <String, GetAvailablePathsResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = GetAvailablePathsResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of GetAvailablePathsResponse-objects as value to a dart map
  static Map<String, List<GetAvailablePathsResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<GetAvailablePathsResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = GetAvailablePathsResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

