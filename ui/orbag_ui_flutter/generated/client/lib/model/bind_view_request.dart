//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class BindViewRequest {
  /// Returns a new [BindViewRequest] instance.
  BindViewRequest({
    this.targetCi,
    this.view,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? targetCi;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializableView? view;

  @override
  bool operator ==(Object other) => identical(this, other) || other is BindViewRequest &&
     other.targetCi == targetCi &&
     other.view == view;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (targetCi == null ? 0 : targetCi!.hashCode) +
    (view == null ? 0 : view!.hashCode);

  @override
  String toString() => 'BindViewRequest[targetCi=$targetCi, view=$view]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.targetCi != null) {
      json[r'targetCi'] = this.targetCi;
    } else {
      json[r'targetCi'] = null;
    }
    if (this.view != null) {
      json[r'view'] = this.view;
    } else {
      json[r'view'] = null;
    }
    return json;
  }

  /// Returns a new [BindViewRequest] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static BindViewRequest? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "BindViewRequest[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "BindViewRequest[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return BindViewRequest(
        targetCi: ConfigurationItemReference.fromJson(json[r'targetCi']),
        view: SerializableView.fromJson(json[r'view']),
      );
    }
    return null;
  }

  static List<BindViewRequest> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <BindViewRequest>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = BindViewRequest.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, BindViewRequest> mapFromJson(dynamic json) {
    final map = <String, BindViewRequest>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = BindViewRequest.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of BindViewRequest-objects as value to a dart map
  static Map<String, List<BindViewRequest>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<BindViewRequest>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = BindViewRequest.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

