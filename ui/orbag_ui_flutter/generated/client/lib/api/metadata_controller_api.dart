//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class MetadataControllerApi {
  MetadataControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// Performs an HTTP 'GET /api/metadata/{configurationItemType}' operation and returns the [Response].
  /// Parameters:
  ///
  /// * [String] configurationItemType (required):
  ///
  /// * [bool] properties:
  Future<Response> getClassMetadataWithHttpInfo(String configurationItemType, { bool? properties, }) async {
    // ignore: prefer_const_declarations
    final path = r'/api/metadata/{configurationItemType}'
      .replaceAll('{configurationItemType}', configurationItemType);

    // ignore: prefer_final_locals
    Object? postBody;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    if (properties != null) {
      queryParams.addAll(_queryParams('', 'properties', properties));
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

  /// Parameters:
  ///
  /// * [String] configurationItemType (required):
  ///
  /// * [bool] properties:
  Future<SerializableConfigurationItemDescriptor?> getClassMetadata(String configurationItemType, { bool? properties, }) async {
    final response = await getClassMetadataWithHttpInfo(configurationItemType,  properties: properties, );
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'SerializableConfigurationItemDescriptor',) as SerializableConfigurationItemDescriptor;
    
    }
    return null;
  }

  /// Get class model
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [bool] properties:
  Future<Response> getClassModelWithHttpInfo({ bool? properties, }) async {
    // ignore: prefer_const_declarations
    final path = r'/api/metadata';

    // ignore: prefer_final_locals
    Object? postBody;

    final queryParams = <QueryParam>[];
    final headerParams = <String, String>{};
    final formParams = <String, String>{};

    if (properties != null) {
      queryParams.addAll(_queryParams('', 'properties', properties));
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

  /// Get class model
  ///
  /// Parameters:
  ///
  /// * [bool] properties:
  Future<GetClassModelResponse?> getClassModel({ bool? properties, }) async {
    final response = await getClassModelWithHttpInfo( properties: properties, );
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'GetClassModelResponse',) as GetClassModelResponse;
    
    }
    return null;
  }
}
