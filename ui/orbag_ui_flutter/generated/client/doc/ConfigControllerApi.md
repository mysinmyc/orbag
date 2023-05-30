# openapi.api.ConfigControllerApi

## Load the API package
```dart
import 'package:openapi/api.dart';
```

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getConfig**](ConfigControllerApi.md#getconfig) | **GET** /public/config.json | Get application configurations


# **getConfig**
> ConfigResponse getConfig()

Get application configurations

### Example
```dart
import 'package:openapi/api.dart';

final api_instance = ConfigControllerApi();

try {
    final result = api_instance.getConfig();
    print(result);
} catch (e) {
    print('Exception when calling ConfigControllerApi->getConfig: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ConfigResponse**](ConfigResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

