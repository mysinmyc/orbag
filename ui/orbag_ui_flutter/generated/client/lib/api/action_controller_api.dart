//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

part of openapi.api;


class ActionControllerApi {
  ActionControllerApi([ApiClient? apiClient]) : apiClient = apiClient ?? defaultApiClient;

  final ApiClient apiClient;

  /// Build action template
  ///
  /// Build the request template to execute specified action
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [BuildActionTemplateRequest] buildActionTemplateRequest (required):
  Future<Response> buildExecutionTemplateWithHttpInfo(BuildActionTemplateRequest buildActionTemplateRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/action/buildExecutionTemplate';

    // ignore: prefer_final_locals
    Object? postBody = buildActionTemplateRequest;

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

  /// Build action template
  ///
  /// Build the request template to execute specified action
  ///
  /// Parameters:
  ///
  /// * [BuildActionTemplateRequest] buildActionTemplateRequest (required):
  Future<SubmitActionRequest?> buildExecutionTemplate(BuildActionTemplateRequest buildActionTemplateRequest,) async {
    final response = await buildExecutionTemplateWithHttpInfo(buildActionTemplateRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'SubmitActionRequest',) as SubmitActionRequest;
    
    }
    return null;
  }

  /// Build available actions list
  ///
  /// Return list of actions that user can executed on specified CIs
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [GetAvailableActionsRequest] getAvailableActionsRequest (required):
  Future<Response> getAvailableWithHttpInfo(GetAvailableActionsRequest getAvailableActionsRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/action/getAvailable';

    // ignore: prefer_final_locals
    Object? postBody = getAvailableActionsRequest;

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

  /// Build available actions list
  ///
  /// Return list of actions that user can executed on specified CIs
  ///
  /// Parameters:
  ///
  /// * [GetAvailableActionsRequest] getAvailableActionsRequest (required):
  Future<GetAvailableActionsResponse?> getAvailable(GetAvailableActionsRequest getAvailableActionsRequest,) async {
    final response = await getAvailableWithHttpInfo(getAvailableActionsRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'GetAvailableActionsResponse',) as GetAvailableActionsResponse;
    
    }
    return null;
  }

  /// Submit the action
  ///
  /// Note: This method returns the HTTP [Response].
  ///
  /// Parameters:
  ///
  /// * [SubmitActionRequest] submitActionRequest (required):
  Future<Response> submitWithHttpInfo(SubmitActionRequest submitActionRequest,) async {
    // ignore: prefer_const_declarations
    final path = r'/api/action/submit';

    // ignore: prefer_final_locals
    Object? postBody = submitActionRequest;

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

  /// Submit the action
  ///
  /// Parameters:
  ///
  /// * [SubmitActionRequest] submitActionRequest (required):
  Future<SubmitActionResponse?> submit(SubmitActionRequest submitActionRequest,) async {
    final response = await submitWithHttpInfo(submitActionRequest,);
    if (response.statusCode >= HttpStatus.badRequest) {
      throw ApiException(response.statusCode, await _decodeBodyBytes(response));
    }
    // When a remote server returns no body with a status of 204, we shall not decode it.
    // At the time of writing this, `dart:convert` will throw an "Unexpected end of input"
    // FormatException when trying to decode an empty string.
    if (response.body.isNotEmpty && response.statusCode != HttpStatus.noContent) {
      return await apiClient.deserializeAsync(await _decodeBodyBytes(response), 'SubmitActionResponse',) as SubmitActionResponse;
    
    }
    return null;
  }
}
