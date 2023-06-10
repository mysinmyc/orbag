import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/util/message_util.dart';

class ErrorMessageWrapper {
  static wrap(BuildContext context, Future future, String errorMessage,
      {VoidCallback? onClose = null}) {
    future.catchError((error, stackTrace) {
      MessageUtil.showError(context, errorMessage, error).then((value) {
        if (onClose != null) {
          onClose();
        }
      });
    });
  }
}
