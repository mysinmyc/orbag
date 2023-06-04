import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/util/message_util.dart';

class ErrorMessageWrapper<T> {
  ErrorMessageWrapper(
      BuildContext context, Future<T> future, String errorMessage) {
    future.catchError((error, stackTrace) =>
        MessageUtil.showError(context, errorMessage + ": " + error.toString()));
  }
}
