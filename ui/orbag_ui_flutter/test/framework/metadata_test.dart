import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/metadata.dart';

class HasConfigurationItemDescriptors extends CustomMatcher {
  HasConfigurationItemDescriptors(matcher)
      : super("Classmodel with ConfigurationItemDescriptors",
            "configurationItemDescriptors", matcher);
  @override
  featureValueOf(actual) => (actual as ClassModel).configurationItemDescriptors;
}

void main() {
  group("metadata", () {
    /*test("API call", () async {
      ClassModel classModel = await getClassModel();
      expect(classModel,
          HasConfigurationItemDescriptors(hasLength(greaterThan(0))));
    });
    */
    test("Deserialization", () {
      ClassModel classModel = ClassModel.fromJson(jsonDecode("""
{"configurationItemDescriptors":[{"name":"ServerGroup","displayLabel":"ServerGroup","category":"Infrastructure","allowCreation":true,"readOnly":false,"properties":null},{"name":"BreweryMenuItem","displayLabel":"BreweryMenuItem","category":"After work","allowCreation":false,"readOnly":true,"properties":null},{"name":"Server","displayLabel":"Server","category":"Infrastructure","allowCreation":true,"readOnly":false,"properties":null},{"name":"Person","displayLabel":"Person","category":"Party","allowCreation":true,"readOnly":false,"properties":null},{"name":"Brewery","displayLabel":"Brewery","category":"After work","allowCreation":false,"readOnly":true,"properties":null}]}
"""));

      expect(classModel,
          HasConfigurationItemDescriptors(hasLength(greaterThan(0))));
    });
  });
}
