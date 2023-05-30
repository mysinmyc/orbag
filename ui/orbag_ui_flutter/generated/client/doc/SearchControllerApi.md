# openapi.api.SearchControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**execute**](SearchControllerApi.md#execute) | **POST** /api/search/execute | 
[**getSearchTemplate**](SearchControllerApi.md#getsearchtemplate) | **GET** /api/search/template/{configurationItemName} | 


# **execute**
> SerializableTable execute(searchRequest, limit, offset)



### Example
```dart
import 'package:openapi/api.dart';

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

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getSearchTemplate**
> SearchRequest getSearchTemplate(configurationItemName)



### Example
```dart
import 'package:openapi/api.dart';

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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

