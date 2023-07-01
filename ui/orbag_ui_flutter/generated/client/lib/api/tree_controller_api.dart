//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class TreeControllerApi {
  TreeControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// Performs an HTTP 'POST /api/tree/getChildren' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [GetChildrenRequest] getChildrenRequest (required):
  Future<Response> getChildrenWithHttpInfo(GetChildrenRequest getChildrenRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/tree/getChildren';

    // ignore: prefer_final_locals
    Object? postBody = getChildrenRequest;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    const contentTypes = <String>['application/json'];


    return apiClient.invokeAPI(
      path,
      'POST',
      queryParams,
      postBody,
      headerParams,
      formParams,
      contentTypes.isEmpty ? null : contentTypes.first,
    );
  }

  /// Parameters:
  ///
  /// * [GetChildrenRequest] getChildrenRequest (required):
  Future<GetChildrenResponse?> getChildren(GetChildrenRequest getChildrenRequest,) async {
    final response = await getChildrenWithHttpInfo(getChildrenRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'GetChildrenResponse',) as GetChildrenResponse;
    
    }
    return null;
  }

  /// Performs an HTTP 'POST /api/tree/getRoot' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [GetRootRequest] getRootRequest (required):
  Future<Response> getRootWithHttpInfo(GetRootRequest getRootRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/tree/getRoot';

    // ignore: prefer_final_locals
    Object? postBody = getRootRequest;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    const contentTypes = <String>['application/json'];


    return apiClient.invokeAPI(
      path,
      'POST',
      queryParams,
      postBody,
      headerParams,
      formParams,
      contentTypes.isEmpty ? null : contentTypes.first,
    );
  }

  /// Parameters:
  ///
  /// * [GetRootRequest] getRootRequest (required):
  Future<GetRootResponse?> getRoot(GetRootRequest getRootRequest,) async {
    final response = await getRootWithHttpInfo(getRootRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'GetRootResponse',) as GetRootResponse;
    
    }
    return null;
  }
}
