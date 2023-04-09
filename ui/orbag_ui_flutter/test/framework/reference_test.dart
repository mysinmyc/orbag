import 'dart:convert';
import 'dart:math';

import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';

void main() {
  group("Reference", () {
    test("Deserialization", () {
      ConfigurationItemReference parsed =
          ConfigurationItemReference.fromJson(jsonDecode("""
{
  "identifier":"5",
  "configurationItemType":"Prova",
  "displayLabel":"ciao"
}
"""));
      expect(parsed.identifier, "5");
      expect(parsed.configurationItemType, "Prova");
      expect(parsed.displayLabel, "ciao");
    });
  });
}
