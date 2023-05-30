//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class WhoAmIResponse {
  /// Returns a new [WhoAmIResponse] instance.
  WhoAmIResponse({
    this.userName,
    this.authorities = const [],
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? userName;

  List<String> authorities;

  @override
  bool operator ==(Object other) => identical(this, other) || other is WhoAmIResponse &&
     other.userName == userName &&
     other.authorities == authorities;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (userName == null ? 0 : userName!.hashCode) +
    (authorities.hashCode);

  @override
  String toString() => 'WhoAmIResponse[userName=$userName, authorities=$authorities]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.userName != null) {
      json[r'userName'] = this.userName;
    } else {
      json[r'userName'] = null;
    }
      json[r'authorities'] = this.authorities;
    return json;
  }

  /// Returns a new [WhoAmIResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static WhoAmIResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "WhoAmIResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "WhoAmIResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return WhoAmIResponse(
        userName: mapValueOfType<String>(json, r'userName'),
        authorities: json[r'authorities'] is List
            ? (json[r'authorities'] as List).cast<String>()
            : const [],
      );
    }
    return null;
  }

  static List<WhoAmIResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <WhoAmIResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = WhoAmIResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, WhoAmIResponse> mapFromJson(dynamic json) {
    final map = <String, WhoAmIResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = WhoAmIResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of WhoAmIResponse-objects as value to a dart map
  static Map<String, List<WhoAmIResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<WhoAmIResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = WhoAmIResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

