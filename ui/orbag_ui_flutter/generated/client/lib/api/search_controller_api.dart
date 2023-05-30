//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class SearchControllerApi {
  SearchControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// Performs an HTTP 'POST /api/search/execute' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [SearchRequest] searchRequest (required):
  ///
  /// * [int] limit:
  ///
  /// * [int] offset:
  Future<Response> executeWithHttpInfo(SearchRequest searchRequest, { int? limit, int? offset, }) async {
    // ignore: prefer_const_declarations
    final path = r'/api/search/execute';

    // ignore: prefer_final_locals
    Object? postBody = searchRequest;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    if (limit != null) {
      queryParams.addAll(_queryParams('', 'limit', limit));
    }
    if (offset != null) {
      queryParams.addAll(_queryParams('', 'offset', offset));
    }

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
  /// * [SearchRequest] searchRequest (required):
  ///
  /// * [int] limit:
  ///
  /// * [int] offset:
  Future<SerializableTable?> execute(SearchRequest searchRequest, { int? limit, int? offset, }) async {
    final response = await executeWithHttpInfo(searchRequest,  limit: limit, offset: offset, );
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'SerializableTable',) as SerializableTable;
    
    }
    return null;
  }

  /// Performs an HTTP 'GET /api/search/template/{configurationItemName}' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [String] configurationItemName (required):
  Future<Response> getSearchTemplateWithHttpInfo(String configurationItemName,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/search/template/{configurationItemName}'
      .replaceAll('{configurationItemName}', configurationItemName);

    // ignore: prefer_final_locals
    Object? postBody;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    const contentTypes = <String>[];


    return apiClient.invokeAPI(
      path,
      'GET',
      queryParams,
      postBody,
      headerParams,
      formParams,
      contentTypes.isEmpty ? null : contentTypes.first,
    );
  }

  /// Parameters:
  ///
  /// * [String] configurationItemName (required):
  Future<SearchRequest?> getSearchTemplate(String configurationItemName,) async {
    final response = await getSearchTemplateWithHttpInfo(configurationItemName,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'SearchRequest',) as SearchRequest;
    
    }
    return null;
  }
}
