# openapi.api.TreeControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getChildren**](TreeControllerApi.md#getchildren) | **POST** /api/tree/getChildren | 
[**getRoot**](TreeControllerApi.md#getroot) | **POST** /api/tree/getRoot | 


# **getChildren**
> GetChildrenResponse getChildren(getChildrenRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = TreeControllerApi();
final getChildrenRequest = GetChildrenRequest(); // GetChildrenRequest | 

try {
    final result = api_instance.getChildren(getChildrenRequest);
    print(result);
} catch (e) {
    print('Exception when calling TreeControllerApi->getChildren: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **getChildrenRequest** | [**GetChildrenRequest**](GetChildrenRequest.md)|  | 

### Return type

[**GetChildrenResponse**](GetChildrenResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getRoot**
> GetRootResponse getRoot(getRootRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = TreeControllerApi();
final getRootRequest = GetRootRequest(); // GetRootRequest | 

try {
    final result = api_instance.getRoot(getRootRequest);
    print(result);
} catch (e) {
    print('Exception when calling TreeControllerApi->getRoot: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **getRootRequest** | [**GetRootRequest**](GetRootRequest.md)|  | 

### Return type

[**GetRootResponse**](GetRootResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

