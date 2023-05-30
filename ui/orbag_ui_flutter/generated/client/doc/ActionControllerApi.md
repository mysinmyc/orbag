# openapi.api.ActionControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**buildExecutionTemplate**](ActionControllerApi.md#buildexecutiontemplate) | **POST** /api/action/buildExecutionTemplate | Build action template
[**getAvailable**](ActionControllerApi.md#getavailable) | **POST** /api/action/getAvailable | Build available actions list
[**submit**](ActionControllerApi.md#submit) | **POST** /api/action/submit | Submit the action


# **buildExecutionTemplate**
> SubmitActionRequest buildExecutionTemplate(buildActionTemplateRequest)

Build action template

Build the request template to execute specified action

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = ActionControllerApi();
final buildActionTemplateRequest = BuildActionTemplateRequest(); // BuildActionTemplateRequest | 

try {
    final result = api_instance.buildExecutionTemplate(buildActionTemplateRequest);
    print(result);
} catch (e) {
    print('Exception when calling ActionControllerApi->buildExecutionTemplate: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **buildActionTemplateRequest** | [**BuildActionTemplateRequest**](BuildActionTemplateRequest.md)|  | 

### Return type

[**SubmitActionRequest**](SubmitActionRequest.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getAvailable**
> GetAvailableActionsResponse getAvailable(getAvailableActionsRequest)

Build available actions list

Return list of actions that user can executed on specified CIs

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = ActionControllerApi();
final getAvailableActionsRequest = GetAvailableActionsRequest(); // GetAvailableActionsRequest | 

try {
    final result = api_instance.getAvailable(getAvailableActionsRequest);
    print(result);
} catch (e) {
    print('Exception when calling ActionControllerApi->getAvailable: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **getAvailableActionsRequest** | [**GetAvailableActionsRequest**](GetAvailableActionsRequest.md)|  | 

### Return type

[**GetAvailableActionsResponse**](GetAvailableActionsResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **submit**
> SubmitActionResponse submit(submitActionRequest)

Submit the action

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = ActionControllerApi();
final submitActionRequest = SubmitActionRequest(); // SubmitActionRequest | 

try {
    final result = api_instance.submit(submitActionRequest);
    print(result);
} catch (e) {
    print('Exception when calling ActionControllerApi->submit: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **submitActionRequest** | [**SubmitActionRequest**](SubmitActionRequest.md)|  | 

### Return type

[**SubmitActionResponse**](SubmitActionResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

