//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class ListControllerApi {
  ListControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// List configuration items of the specified type
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [String] configurationItemName (required):
  ///
  /// * [int] limit:
  ///
  /// * [int] offset:
  Future<Response> listConfigurationItemsWithHttpInfo(String configurationItemName, { int? limit, int? offset, }) async {
    // ignore: prefer_const_declarations
    final path = r'/api/list/{configurationItemName}'
      .replaceAll('{configurationItemName}', configurationItemName);

    // ignore: prefer_final_locals
    Object? postBody;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    if (limit != null) {
      queryParams.addAll(_queryParams('', 'limit', limit));
    }
    if (offset != null) {
      queryParams.addAll(_queryParams('', 'offset', offset));
    }

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

  /// List configuration items of the specified type
  ///
  /// Parameters:
  ///
  /// * [String] configurationItemName (required):
  ///
  /// * [int] limit:
  ///
  /// * [int] offset:
  Future<ListConfigurationItemResponse?> listConfigurationItems(String configurationItemName, { int? limit, int? offset, }) async {
    final response = await listConfigurationItemsWithHttpInfo(configurationItemName,  limit: limit, offset: offset, );
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'ListConfigurationItemResponse',) as ListConfigurationItemResponse;
    
    }
    return null;
  }
}
