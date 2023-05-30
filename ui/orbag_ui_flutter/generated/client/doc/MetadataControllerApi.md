# openapi.api.MetadataControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getClassMetadata**](MetadataControllerApi.md#getclassmetadata) | **GET** /api/metadata/{configurationItemType} | 
[**getClassModel**](MetadataControllerApi.md#getclassmodel) | **GET** /api/metadata | Get class model


# **getClassMetadata**
> SerializableConfigurationItemDescriptor getClassMetadata(configurationItemType, properties)



### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = MetadataControllerApi();
final configurationItemType = configurationItemType_example; // String | 
final properties = true; // bool | 

try {
    final result = api_instance.getClassMetadata(configurationItemType, properties);
    print(result);
} catch (e) {
    print('Exception when calling MetadataControllerApi->getClassMetadata: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **configurationItemType** | **String**|  | 
 **properties** | **bool**|  | [optional] [default to true]

### Return type

[**SerializableConfigurationItemDescriptor**](SerializableConfigurationItemDescriptor.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getClassModel**
> GetClassModelResponse getClassModel(properties)

Get class model

### Example
```dart
import 'package:openapi/api.dart';
// TODO Configure HTTP Bearer authorization: JWT
// Case 1. Use String Token
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken('YOUR_ACCESS_TOKEN');
// Case 2. Use Function which generate token.
// String yourTokenGeneratorFunction() { ... }
//defaultApiClient.getAuthentication<HttpBearerAuth>('JWT').setAccessToken(yourTokenGeneratorFunction);

final api_instance = MetadataControllerApi();
final properties = true; // bool | 

try {
    final result = api_instance.getClassModel(properties);
    print(result);
} catch (e) {
    print('Exception when calling MetadataControllerApi->getClassModel: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **properties** | **bool**|  | [optional] [default to false]

### Return type

[**GetClassModelResponse**](GetClassModelResponse.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

