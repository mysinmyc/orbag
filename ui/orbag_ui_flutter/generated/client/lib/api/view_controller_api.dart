//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class ViewControllerApi {
  ViewControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// Performs an HTTP 'POST /api/view/bind' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [BindViewRequest] bindViewRequest (required):
  Future<Response> bindWithHttpInfo(BindViewRequest bindViewRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/view/bind';

    // ignore: prefer_final_locals
    Object? postBody = bindViewRequest;

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
  /// * [BindViewRequest] bindViewRequest (required):
  Future<BindViewResponse?> bind(BindViewRequest bindViewRequest,) async {
    final response = await bindWithHttpInfo(bindViewRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'BindViewResponse',) as BindViewResponse;
    
    }
    return null;
  }

  /// Performs an HTTP 'POST /api/view/getAvailable' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [GetAvailableViewsRequest] getAvailableViewsRequest (required):
  Future<Response> getAvailableViewsWithHttpInfo(GetAvailableViewsRequest getAvailableViewsRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/view/getAvailable';

    // ignore: prefer_final_locals
    Object? postBody = getAvailableViewsRequest;

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
  /// * [GetAvailableViewsRequest] getAvailableViewsRequest (required):
  Future<GetAvailableViewsResponse?> getAvailableViews(GetAvailableViewsRequest getAvailableViewsRequest,) async {
    final response = await getAvailableViewsWithHttpInfo(getAvailableViewsRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'GetAvailableViewsResponse',) as GetAvailableViewsResponse;
    
    }
    return null;
  }
}
