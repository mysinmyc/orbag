# openapi.api.ViewControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**bind**](ViewControllerApi.md#bind) | **POST** /api/view/bind | 
[**getAvailableViews**](ViewControllerApi.md#getavailableviews) | **POST** /api/view/getAvailable | 


# **bind**
> BindViewResponse bind(bindViewRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = ViewControllerApi();
final bindViewRequest = BindViewRequest(); // BindViewRequest | 

try {
    final result = api_instance.bind(bindViewRequest);
    print(result);
} catch (e) {
    print('Exception when calling ViewControllerApi->bind: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bindViewRequest** | [**BindViewRequest**](BindViewRequest.md)|  | 

### Return type

[**BindViewResponse**](BindViewResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getAvailableViews**
> GetAvailableViewsResponse getAvailableViews(getAvailableViewsRequest)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = ViewControllerApi();
final getAvailableViewsRequest = GetAvailableViewsRequest(); // GetAvailableViewsRequest | 

try {
    final result = api_instance.getAvailableViews(getAvailableViewsRequest);
    print(result);
} catch (e) {
    print('Exception when calling ViewControllerApi->getAvailableViews: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **getAvailableViewsRequest** | [**GetAvailableViewsRequest**](GetAvailableViewsRequest.md)|  | 

### Return type

[**GetAvailableViewsResponse**](GetAvailableViewsResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

