//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class GetClassModelResponse {
  /// Returns a new [GetClassModelResponse] instance.
  GetClassModelResponse({
    this.configurationItemDescriptors = const [],
  });

  List<SerializableConfigurationItemDescriptor> configurationItemDescriptors;

  @override
  bool operator ==(Object other) => identical(this, other) || other is GetClassModelResponse &&
     other.configurationItemDescriptors == configurationItemDescriptors;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (configurationItemDescriptors.hashCode);

  @override
  String toString() => 'GetClassModelResponse[configurationItemDescriptors=$configurationItemDescriptors]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
      json[r'configurationItemDescriptors'] = this.configurationItemDescriptors;
    return json;
  }

  /// Returns a new [GetClassModelResponse] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static GetClassModelResponse? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "GetClassModelResponse[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "GetClassModelResponse[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return GetClassModelResponse(
        configurationItemDescriptors: SerializableConfigurationItemDescriptor.listFromJson(json[r'configurationItemDescriptors']),
      );
    }
    return null;
  }

  static List<GetClassModelResponse> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <GetClassModelResponse>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = GetClassModelResponse.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, GetClassModelResponse> mapFromJson(dynamic json) {
    final map = <String, GetClassModelResponse>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = GetClassModelResponse.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of GetClassModelResponse-objects as value to a dart map
  static Map<String, List<GetClassModelResponse>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<GetClassModelResponse>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = GetClassModelResponse.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

