//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class GetRootRequest {
  /// Returns a new [GetRootRequest] instance.
  GetRootRequest({
    this.ci,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? ci;

  @override
  bool operator ==(Object other) => identical(this, other) || other is GetRootRequest &&
     other.ci == ci;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (ci == null ? 0 : ci!.hashCode);

  @override
  String toString() => 'GetRootRequest[ci=$ci]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.ci != null) {
      json[r'ci'] = this.ci;
    } else {
      json[r'ci'] = null;
    }
    return json;
  }

  /// Returns a new [GetRootRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static GetRootRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "GetRootRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "GetRootRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return GetRootRequest(
        ci: ConfigurationItemReference.fromJson(json[r'ci']),
      );
    }
    return null;
  }

  static List<GetRootRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <GetRootRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = GetRootRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, GetRootRequest> mapFromJson(dynamic json) {
    final map = <String, GetRootRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = GetRootRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of GetRootRequest-objects as value to a dart map
  static Map<String, List<GetRootRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<GetRootRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = GetRootRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

