# openapi.api.ListControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**listConfigurationItems**](ListControllerApi.md#listconfigurationitems) | **GET** /api/list/{configurationItemName} | 


# **listConfigurationItems**
> ListConfigurationItemResponse listConfigurationItems(configurationItemName, limit, offset)



List configuration items of the specified type

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = ListControllerApi();
final configurationItemName = configurationItemName_example; // String | 
final limit = 56; // int | 
final offset = 56; // int | 

try {
    final result = api_instance.listConfigurationItems(configurationItemName, limit, offset);
    print(result);
} catch (e) {
    print('Exception when calling ListControllerApi->listConfigurationItems: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **configurationItemName** | **String**|  | 
 **limit** | **int**|  | [optional] [default to 50]
 **offset** | **int**|  | [optional] [default to 0]

### Return type

[**ListConfigurationItemResponse**](ListConfigurationItemResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

