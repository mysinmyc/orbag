import 'package:http/http.dart' as http;

import 'package:orbag_ui_flutter/framework/client_other.dart'
    if (dart.library.io) 'package:orbag_ui_flutter/framework/client_linux.dart'
    if (dart.library.html) 'package:orbag_ui_flutter/framework/client_web.dart';

class MyHttpClient {
  var serverAddress = "";

  static MyHttpClient instance = MyHttpClientImpl();

  static MyHttpClient getInstance() {
    return instance;
  }

  Future<http.Response> get(String relativePath) async {
    return await http.get(Uri.parse('$serverAddress/$relativePath'),
        headers: <String, String>{
          'authorization': "Basic aXRfdXNlcjpvcmJhZw=="
        });
  }

  Future<http.Response> post(String relativePath, Object body) async {
    return await http.post(Uri.parse('$serverAddress/$relativePath'),
        body: body,
        headers: <String, String>{
          'authorization': "Basic aXRfdXNlcjpvcmJhZw==",
          'content-type': 'application/json'
        });
  }
}
