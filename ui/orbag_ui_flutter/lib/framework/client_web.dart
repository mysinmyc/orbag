import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:js/js.dart';

@JS('getServerAddress')
external String getServerAddress();

@JS('setCookie')
external void setCookie(String name, String value, int expireSeconds);

class MyHttpClientImpl extends MyHttpClient {
  MyHttpClientImpl() : super(getServerAddress(), setCookieFunction: setCookie);
}
