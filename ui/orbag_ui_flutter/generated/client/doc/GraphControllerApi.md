# openapi.api.GraphControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**generate**](GraphControllerApi.md#generate) | **POST** /api/graph/generate | 
[**getAvailablePaths**](GraphControllerApi.md#getavailablepaths) | **POST** /api/graph/getAvailablePaths | 


# **generate**
> GenerateGraphResponse generate(generateGraphRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = GraphControllerApi();
final generateGraphRequest = GenerateGraphRequest(); // GenerateGraphRequest | 

try {
    final result = api_instance.generate(generateGraphRequest);
    print(result);
} catch (e) {
    print('Exception when calling GraphControllerApi->generate: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **generateGraphRequest** | [**GenerateGraphRequest**](GenerateGraphRequest.md)|  | 

### Return type

[**GenerateGraphResponse**](GenerateGraphResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getAvailablePaths**
> GetAvailablePathsResponse getAvailablePaths(getAvailablePathsRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = GraphControllerApi();
final getAvailablePathsRequest = GetAvailablePathsRequest(); // GetAvailablePathsRequest | 

try {
    final result = api_instance.getAvailablePaths(getAvailablePathsRequest);
    print(result);
} catch (e) {
    print('Exception when calling GraphControllerApi->getAvailablePaths: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **getAvailablePathsRequest** | [**GetAvailablePathsRequest**](GetAvailablePathsRequest.md)|  | 

### Return type

[**GetAvailablePathsResponse**](GetAvailablePathsResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

