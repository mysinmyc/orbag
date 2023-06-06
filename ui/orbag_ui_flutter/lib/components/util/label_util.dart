import 'package:openapi/api.dart';

class LabelUtil {
  static String getCisLabel(List<ConfigurationItemReference>? cis) {
    if (cis == null || cis.isEmpty) {
      return "No ci";
    }
    if (cis.length == 1) {
      return cis[0].displayLabel!;
    }
    return "${cis.length} cis";
  }

  static String truncateLabel(String? label, {int size = 40}) {
    if (label == null) {
      return "";
    }
    if (label.length <= size) {
      return label;
    }

    int leftSize = size ~/ 2;

    return label.substring(0, leftSize + 1) +
        "..." +
        label.substring(label.length - (size - leftSize - 3) + 1, label.length);
  }
}
