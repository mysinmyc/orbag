import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';

void main() {
  group("LabelUtil", () {
    test("TruncateLabel", () {
      var string =
          "a123456789b123456789c123456789d123456789e123456789f12345678g123456789";

      expect(LabelUtil.truncateLabel(string),
          equals("a123456789b123456789c12345...89f12345678g123456789"));
    });
  });
}
