
import 'package:flutter_test/flutter_test.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:openapi/api.dart';

void main() {
  group("Test", () {
    test("TestApi", () async {
      await MyHttpClient.instance.login("it_user", "orbag");

      var apiInstance = MyHttpClient.instance.metadataApi;

      GetClassModelResponse? response = await apiInstance.getClassModel();

      expect(response!.configurationItemDescriptors.length, greaterThan(0));
    });
  });
}
