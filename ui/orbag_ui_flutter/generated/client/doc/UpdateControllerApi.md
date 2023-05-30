# openapi.api.UpdateControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**buildUpdateTemplate**](UpdateControllerApi.md#buildupdatetemplate) | **POST** /api/update/buildUpdateTemplate | 
[**update**](UpdateControllerApi.md#update) | **POST** /api/update/execute | 


# **buildUpdateTemplate**
> UpdateRequest buildUpdateTemplate(configurationItemReference)



### Example
```dart
import 'package:openapi/api.dart';

final api_instance = UpdateControllerApi();
final configurationItemReference = ConfigurationItemReference(); // ConfigurationItemReference | 

try {
    final result = api_instance.buildUpdateTemplate(configurationItemReference);
    print(result);
} catch (e) {
    print('Exception when calling UpdateControllerApi->buildUpdateTemplate: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **configurationItemReference** | [**ConfigurationItemReference**](ConfigurationItemReference.md)|  | 

### Return type

[**UpdateRequest**](UpdateRequest.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **update**
> ConfigurationItemReference update(updateRequest)



### Example
```dart
import 'package:openapi/api.dart';

final api_instance = UpdateControllerApi();
final updateRequest = UpdateRequest(); // UpdateRequest | 

try {
    final result = api_instance.update(updateRequest);
    print(result);
} catch (e) {
    print('Exception when calling UpdateControllerApi->update: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **updateRequest** | [**UpdateRequest**](UpdateRequest.md)|  | 

### Return type

[**ConfigurationItemReference**](ConfigurationItemReference.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

