import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/data.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';

void main() {
  group("Data", () {
    test("Deserialization", () {
      SerializableTable parsed = SerializableTable.fromJson(jsonDecode("""
{
  "columns": [
    {
      "name":"name1",
      "displayLabel":"label1",
      "type": "tipo1"
    },
    {
      "name":"name2",
      "displayLabel":"label12",
      "type": "tipo2"
    }
  ],
  "rows": [
    {
      "row1col1":"row1val1",
      "row1col2":2,
      "row1col3": {
        "identifier": "id1",
        "configurationItemType": "type"
      }
    },
    {
      "row2col1":"row2val2",
      "row2col2":2
    }
  ]
}
"""));
      expect(parsed.columns.length, 2);
      expect(parsed.rows.length, 2);
      expect(parsed.rows[0]["row1col1"], "row1val1");
      expect(parsed.rows[0]["row1col2"], 2);

      ConfigurationItemReference referenceFromRow =
          ConfigurationItemReference.fromJson(
              parsed.rows[0]["row1col3"] as Map<String, dynamic>);
      expect(referenceFromRow.identifier, "id1");
    });
  });
}
