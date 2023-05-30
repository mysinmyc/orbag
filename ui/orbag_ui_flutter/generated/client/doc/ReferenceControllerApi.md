# openapi.api.ReferenceControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getConfigurationItem**](ReferenceControllerApi.md#getconfigurationitem) | **GET** /api/reference/{configurationItemType}/{configurationItemId} | 


# **getConfigurationItem**
> ConfigurationItemReference getConfigurationItem(configurationItemType, configurationItemId)



### Example
```dart
import 'package:openapi/api.dart';

final api_instance = ReferenceControllerApi();
final configurationItemType = configurationItemType_example; // String | 
final configurationItemId = configurationItemId_example; // String | 

try {
    final result = api_instance.getConfigurationItem(configurationItemType, configurationItemId);
    print(result);
} catch (e) {
    print('Exception when calling ReferenceControllerApi->getConfigurationItem: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **configurationItemType** | **String**|  | 
 **configurationItemId** | **String**|  | 

### Return type

[**ConfigurationItemReference**](ConfigurationItemReference.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

