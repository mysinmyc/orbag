import 'package:http/http.dart' as http;
import 'package:openapi/api.dart';

import 'package:orbag_ui_flutter/framework/client_other.dart'
    if (dart.library.io) 'package:orbag_ui_flutter/framework/client_linux.dart'
    if (dart.library.html) 'package:orbag_ui_flutter/framework/client_web.dart';

class MyHttpClient {
  static final MyHttpClient _instance = MyHttpClientImpl();

  String serverAddress;

  MyHttpClient(this.serverAddress);

  static MyHttpClient get instance {
    return _instance;
  }

  String? _authenticationToken;

  Authentication? _authentication() {
    if (_authenticationToken == null) {
      return null;
    } else {
      HttpBearerAuth authentication = HttpBearerAuth();
      authentication.accessToken = _authenticationToken;
      return authentication;
    }
  }

  ApiClient _apiClient() {
    return ApiClient(
        basePath: serverAddress, authentication: _authentication());
  }

  AuthenticationControllerApi authenticationApi() {
    return AuthenticationControllerApi(_apiClient());
  }

  MetadataControllerApi get metadataApi {
    return MetadataControllerApi(_apiClient());
  }

  SearchControllerApi get searchApi {
    return SearchControllerApi(_apiClient());
  }

  UpdateControllerApi get updateApi {
    return UpdateControllerApi(_apiClient());
  }

  Future<LoginResponse> login(String userName, String password) async {
    LoginResponse? response = await authenticationApi().login(LoginRequest(
        userName: userName, password: password, persistent: false));
    _authenticationToken = response!.token;
    return response;
  }

  Future<http.Response> get(String relativePath) async {
    return await http.get(Uri.parse('$serverAddress/$relativePath'),
        headers: <String, String>{
          'authorization': "Bearer $_authenticationToken"
        });
  }

  Future<http.Response> post(String relativePath, Object body) async {
    return await http.post(Uri.parse('$serverAddress/$relativePath'),
        body: body,
        headers: <String, String>{
          'authorization': "Bearer _authenticationToken",
          'content-type': 'application/json'
        });
  }
}
