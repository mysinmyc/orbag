//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class GetChildrenResponse {
  /// Returns a new [GetChildrenResponse] instance.
  GetChildrenResponse({
    this.children = const [],
  });

  List<TreeElement> children;

  @override
  bool operator ==(Object other) => identical(this, other) || other is GetChildrenResponse &&
     other.children == children;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (children.hashCode);

  @override
  String toString() => 'GetChildrenResponse[children=$children]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
      json[r'children'] = this.children;
    return json;
  }

  /// Returns a new [GetChildrenResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static GetChildrenResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "GetChildrenResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "GetChildrenResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return GetChildrenResponse(
        children: TreeElement.listFromJson(json[r'children']),
      );
    }
    return null;
  }

  static List<GetChildrenResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <GetChildrenResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = GetChildrenResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, GetChildrenResponse> mapFromJson(dynamic json) {
    final map = <String, GetChildrenResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = GetChildrenResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of GetChildrenResponse-objects as value to a dart map
  static Map<String, List<GetChildrenResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<GetChildrenResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = GetChildrenResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

