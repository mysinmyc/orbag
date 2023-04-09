import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/search.dart';

void main() {
  group("Search", () {
    test("Deserialization", () {
      SearchRequest parsed = SearchRequest.fromJson(jsonDecode("""
{"configurationItemName":"Server","parameters":{"booleanFields":[],"configurationItemReferenceFields":[],"enumFields":[],"numericFields":[],"stringFields":[{"name":"name","displayLabel":"name","value":null,"readOnly":false,"changed":false},{"name":"address","displayLabel":"address","value":null,"readOnly":false,"changed":false}]},"resultType":"HIGHLIGHTED_FIELDS"}
"""));
      expect(parsed.configurationItemName, "Server");
      expect(parsed.parameters.stringFields.length, 2);
    });
  });
}
