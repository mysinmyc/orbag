# openapi.api.CreateControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create**](CreateControllerApi.md#create) | **POST** /api/create/execute | Execute the configuration item creation
[**getCreateTemplate**](CreateControllerApi.md#getcreatetemplate) | **GET** /api/create/template/{configurationItemName} | Get configuration item creation template


# **create**
> ConfigurationItemReference create(createRequest)

Execute the configuration item creation

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = CreateControllerApi();
final createRequest = CreateRequest(); // CreateRequest | 

try {
    final result = api_instance.create(createRequest);
    print(result);
} catch (e) {
    print('Exception when calling CreateControllerApi->create: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **createRequest** | [**CreateRequest**](CreateRequest.md)|  | 

### Return type

[**ConfigurationItemReference**](ConfigurationItemReference.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getCreateTemplate**
> CreateRequest getCreateTemplate(configurationItemName)

Get configuration item creation template

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = CreateControllerApi();
final configurationItemName = configurationItemName_example; // String | 

try {
    final result = api_instance.getCreateTemplate(configurationItemName);
    print(result);
} catch (e) {
    print('Exception when calling CreateControllerApi->getCreateTemplate: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **configurationItemName** | **String**|  | 

### Return type

[**CreateRequest**](CreateRequest.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

