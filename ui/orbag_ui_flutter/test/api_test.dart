import 'dart:convert';
import 'dart:math';

import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/framework/search.dart';
import 'package:openapi/api.dart';

void main() {
  group("Test", () {
    test("TestApi", () async {
      await MyHttpClient.getInstance().login("it_user", "orbag");

      final api_instance = MyHttpClient.getInstance().metadataApi();

      GetClassModelResponse? response = await api_instance.getClassModel();

      print(response!.toJson());
      expect(response!.configurationItemDescriptors.length, greaterThan(0));
    });
  });
}
