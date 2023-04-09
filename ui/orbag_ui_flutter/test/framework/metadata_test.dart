import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/metadata.dart';

class HasConfigurationItemDescriptors extends CustomMatcher {
  HasConfigurationItemDescriptors(matcher)
      : super("Classmodel with ConfigurationItemDescriptors",
            "configurationItemDescriptors", matcher);
  featureValueOf(actual) => (actual as ClassModel).configurationItemDescriptors;
}

void main() {
  group("metadata", () {
    test("API call", () async {
      ClassModel classModel = await getClassModel();
      print(classModel.toJson());
      expect(classModel,
          HasConfigurationItemDescriptors(hasLength(greaterThan(0))));
    });
/*
    test("Serialization", () {
      ConfigurationItemDescriptor descriptor = ConfigurationItemDescriptor(
          "prova",
          "prova",
          "prova",
          true,
          List.from([
            ConfigurationItemPropertyDescriptor("ciao", "ciao"),
            ConfigurationItemPropertyDescriptor("miao", "miao")
          ]));

      print(descriptor.toJson());
    });*/
  });
}
