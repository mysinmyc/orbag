import 'dart:convert';
import 'dart:math';

import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:openapi/api.dart';

void main() {
  group("Test", () {
    test("TestApi", () async {
      await MyHttpClient.instance.login("it_user", "orbag");

      var api_instance = MyHttpClient.instance.metadataApi;

      GetClassModelResponse? response = await api_instance.getClassModel();

      expect(response!.configurationItemDescriptors.length, greaterThan(0));
    });
  });
}
