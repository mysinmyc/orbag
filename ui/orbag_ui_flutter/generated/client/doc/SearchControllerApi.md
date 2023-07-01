# openapi.api.SearchControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**execute**](SearchControllerApi.md#execute) | **POST** /api/search/execute | 
[**executeLater**](SearchControllerApi.md#executelater) | **POST** /api/search/executeLater | 
[**exportTsv**](SearchControllerApi.md#exporttsv) | **GET** /api/search/execute/{searchId}.tsv | 
[**getSearchTemplate**](SearchControllerApi.md#getsearchtemplate) | **GET** /api/search/template/{configurationItemName} | 


# **execute**
> SerializableTable execute(searchRequest, limit, offset)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = SearchControllerApi();
final searchRequest = SearchRequest(); // SearchRequest | 
final limit = 56; // int | 
final offset = 56; // int | 

try {
    final result = api_instance.execute(searchRequest, limit, offset);
    print(result);
} catch (e) {
    print('Exception when calling SearchControllerApi->execute: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **searchRequest** | [**SearchRequest**](SearchRequest.md)|  | 
 **limit** | **int**|  | [optional] [default to 50]
 **offset** | **int**|  | [optional] [default to 0]

### Return type

[**SerializableTable**](SerializableTable.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **executeLater**
> ExecuteLaterResponse executeLater(searchRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = SearchControllerApi();
final searchRequest = SearchRequest(); // SearchRequest | 

try {
    final result = api_instance.executeLater(searchRequest);
    print(result);
} catch (e) {
    print('Exception when calling SearchControllerApi->executeLater: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **searchRequest** | [**SearchRequest**](SearchRequest.md)|  | 

### Return type

[**ExecuteLaterResponse**](ExecuteLaterResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **exportTsv**
> exportTsv(searchId)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = SearchControllerApi();
final searchId = searchId_example; // String | 

try {
    api_instance.exportTsv(searchId);
} catch (e) {
    print('Exception when calling SearchControllerApi->exportTsv: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **searchId** | **String**|  | 

### Return type

void (empty response body)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getSearchTemplate**
> SearchRequest getSearchTemplate(configurationItemName)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = SearchControllerApi();
final configurationItemName = configurationItemName_example; // String | 

try {
    final result = api_instance.getSearchTemplate(configurationItemName);
    print(result);
} catch (e) {
    print('Exception when calling SearchControllerApi->getSearchTemplate: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **configurationItemName** | **String**|  | 

### Return type

[**SearchRequest**](SearchRequest.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

