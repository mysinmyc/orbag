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
}
