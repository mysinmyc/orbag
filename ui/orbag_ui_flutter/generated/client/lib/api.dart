//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//
// @dart=2.12

// ignore_for_file: unused_element, unused_import
// ignore_for_file: always_put_required_named_parameters_first
// ignore_for_file: constant_identifier_names
// ignore_for_file: lines_longer_than_80_chars

library openapi.api;

import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart';
import 'package:intl/intl.dart';
import 'package:meta/meta.dart';

part 'api_client.dart';
part 'api_helper.dart';
part 'api_exception.dart';
part 'auth/authentication.dart';
part 'auth/api_key_auth.dart';
part 'auth/oauth.dart';
part 'auth/http_basic_auth.dart';
part 'auth/http_bearer_auth.dart';

part 'api/action_controller_api.dart';
part 'api/authentication_controller_api.dart';
part 'api/config_controller_api.dart';
part 'api/create_controller_api.dart';
part 'api/list_controller_api.dart';
part 'api/metadata_controller_api.dart';
part 'api/reference_controller_api.dart';
part 'api/search_controller_api.dart';
part 'api/update_controller_api.dart';
part 'api/view_controller_api.dart';

part 'model/bind_view_request.dart';
part 'model/bind_view_response.dart';
part 'model/boolean_field.dart';
part 'model/build_action_template_request.dart';
part 'model/config_response.dart';
part 'model/configuration_item_reference.dart';
part 'model/configuration_item_reference_field.dart';
part 'model/create_request.dart';
part 'model/create_response.dart';
part 'model/enum_field.dart';
part 'model/error_payload.dart';
part 'model/get_available_actions_request.dart';
part 'model/get_available_actions_response.dart';
part 'model/get_available_views_request.dart';
part 'model/get_available_views_response.dart';
part 'model/get_class_model_response.dart';
part 'model/list_configuration_item_response.dart';
part 'model/login_request.dart';
part 'model/login_response.dart';
part 'model/numeric_field.dart';
part 'model/search_request.dart';
part 'model/serializable_action.dart';
part 'model/serializable_column.dart';
part 'model/serializable_configuration_item_descriptor.dart';
part 'model/serializable_configuration_item_property_descriptor.dart';
part 'model/serializable_field_group.dart';
part 'model/serializable_row.dart';
part 'model/serializable_table.dart';
part 'model/serializable_view.dart';
part 'model/string_field.dart';
part 'model/submit_action_request.dart';
part 'model/submit_action_response.dart';
part 'model/update_request.dart';
part 'model/validation_error.dart';
part 'model/who_am_i_response.dart';


const _delimiters = {'csv': ',', 'ssv': ' ', 'tsv': '\t', 'pipes': '|'};
const _dateEpochMarker = 'epoch';
final _dateFormatter = DateFormat('yyyy-MM-dd');
final _regList = RegExp(r'^List<(.*)>$');
final _regSet = RegExp(r'^Set<(.*)>$');
final _regMap = RegExp(r'^Map<String,(.*)>$');

ApiClient defaultApiClient = ApiClient();
