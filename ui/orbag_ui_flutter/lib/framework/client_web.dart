import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:js/js.dart';

@JS('getServerAddress')
external String getServerAddress();

class MyHttpClientImpl extends MyHttpClient {
  MyHttpClientImpl() : super(getServerAddress());
}
