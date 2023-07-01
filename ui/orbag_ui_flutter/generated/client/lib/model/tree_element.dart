//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;

class TreeElement {
  /// Returns a new [TreeElement] instance.
  TreeElement({
    this.displayLabel,
    this.ci,
    this.path,
    this.previousSteps = const [],
    this.children = const [],
    this.folder,
  });

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  String? displayLabel;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  ConfigurationItemReference? ci;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  SerializablePath? path;

  List<ConfigurationItemReference> previousSteps;

  List<TreeElement> children;

  ///
  /// Please note: This property should have been non-nullable! Since the specification file
  /// does not include a default value (using the "default:" property), however, the generated
  /// source code must fall back to having a nullable type.
  /// Consider adding a "default:" property in the specification file to hide this note.
  ///
  bool? folder;

  @override
  bool operator ==(Object other) => identical(this, other) || other is TreeElement &&
     other.displayLabel == displayLabel &&
     other.ci == ci &&
     other.path == path &&
     other.previousSteps == previousSteps &&
     other.children == children &&
     other.folder == folder;

  @override
  int get hashCode =>
    // ignore: unnecessary_parenthesis
    (displayLabel == null ? 0 : displayLabel!.hashCode) +
    (ci == null ? 0 : ci!.hashCode) +
    (path == null ? 0 : path!.hashCode) +
    (previousSteps.hashCode) +
    (children.hashCode) +
    (folder == null ? 0 : folder!.hashCode);

  @override
  String toString() => 'TreeElement[displayLabel=$displayLabel, ci=$ci, path=$path, previousSteps=$previousSteps, children=$children, folder=$folder]';

  Map<String, dynamic> toJson() {
    final json = <String, dynamic>{};
    if (this.displayLabel != null) {
      json[r'displayLabel'] = this.displayLabel;
    } else {
      json[r'displayLabel'] = null;
    }
    if (this.ci != null) {
      json[r'ci'] = this.ci;
    } else {
      json[r'ci'] = null;
    }
    if (this.path != null) {
      json[r'path'] = this.path;
    } else {
      json[r'path'] = null;
    }
      json[r'previousSteps'] = this.previousSteps;
      json[r'children'] = this.children;
    if (this.folder != null) {
      json[r'folder'] = this.folder;
    } else {
      json[r'folder'] = null;
    }
    return json;
  }

  /// Returns a new [TreeElement] instance and imports its values from
  /// [value] if it's a [Map], null otherwise.
  // ignore: prefer_constructors_over_static_methods
  static TreeElement? fromJson(dynamic value) {
    if (value is Map) {
      final json = value.cast<String, dynamic>();

      // Ensure that the map contains the required keys.
      // Note 1: the values aren't checked for validity beyond being non-null.
      // Note 2: this code is stripped in release mode!
      assert(() {
        requiredKeys.forEach((key) {
          assert(json.containsKey(key), 'Required key "TreeElement[$key]" is missing from JSON.');
          assert(json[key] != null, 'Required key "TreeElement[$key]" has a null value in JSON.');
        });
        return true;
      }());

      return TreeElement(
        displayLabel: mapValueOfType<String>(json, r'displayLabel'),
        ci: ConfigurationItemReference.fromJson(json[r'ci']),
        path: SerializablePath.fromJson(json[r'path']),
        previousSteps: ConfigurationItemReference.listFromJson(json[r'previousSteps']),
        children: TreeElement.listFromJson(json[r'children']),
        folder: mapValueOfType<bool>(json, r'folder'),
      );
    }
    return null;
  }

  static List<TreeElement> listFromJson(dynamic json, {bool growable = false,}) {
    final result = <TreeElement>[];
    if (json is List && json.isNotEmpty) {
      for (final row in json) {
        final value = TreeElement.fromJson(row);
        if (value != null) {
          result.add(value);
        }
      }
    }
    return result.toList(growable: growable);
  }

  static Map<String, TreeElement> mapFromJson(dynamic json) {
    final map = <String, TreeElement>{};
    if (json is Map && json.isNotEmpty) {
      json = json.cast<String, dynamic>(); // ignore: parameter_assignments
      for (final entry in json.entries) {
        final value = TreeElement.fromJson(entry.value);
        if (value != null) {
          map[entry.key] = value;
        }
      }
    }
    return map;
  }

  // maps a json object with a list of TreeElement-objects as value to a dart map
  static Map<String, List<TreeElement>> mapListFromJson(dynamic json, {bool growable = false,}) {
    final map = <String, List<TreeElement>>{};
    if (json is Map && json.isNotEmpty) {
      // ignore: parameter_assignments
      json = json.cast<String, dynamic>();
      for (final entry in json.entries) {
        map[entry.key] = TreeElement.listFromJson(entry.value, growable: growable,);
      }
    }
    return map;
  }

  /// The list of required keys that must be present in a JSON.
  static const requiredKeys = <String>{
  };
}

