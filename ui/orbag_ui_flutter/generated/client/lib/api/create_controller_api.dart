//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class CreateControllerApi {
  CreateControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// Execute the configuration item creation
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [CreateRequest] createRequest (required):
  Future<Response> createWithHttpInfo(CreateRequest createRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/create/execute';

    // ignore: prefer_final_locals
    Object? postBody = createRequest;

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

  /// Execute the configuration item creation
  ///
  /// Parameters:
  ///
  /// * [CreateRequest] createRequest (required):
  Future<CreateResponse?> create(CreateRequest createRequest,) async {
    final response = await createWithHttpInfo(createRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'CreateResponse',) as CreateResponse;
    
    }
    return null;
  }

  /// Get configuration item creation template
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [String] configurationItemName (required):
  Future<Response> getCreateTemplateWithHttpInfo(String configurationItemName,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/create/template/{configurationItemName}'
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

  /// Get configuration item creation template
  ///
  /// Parameters:
  ///
  /// * [String] configurationItemName (required):
  Future<CreateRequest?> getCreateTemplate(String configurationItemName,) async {
    final response = await getCreateTemplateWithHttpInfo(configurationItemName,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'CreateRequest',) as CreateRequest;
    
    }
    return null;
  }
}
