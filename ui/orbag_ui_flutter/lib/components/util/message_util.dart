import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:openapi/api.dart';

class MessageUtil {
  static String _error2String(Object? error) {
    if (error == null) {
      return "";
    }

    Object? errorData = error;
    if (errorData is ApiException && errorData.message != null) {
      try {
        errorData = jsonDecode(errorData.message!);
      } catch (Exception) {
        errorData = (errorData as ApiException).message;
      }
    }
    if (errorData is Map) {
      if (errorData.containsKey("error")) {
        var errorMap = errorData.cast<String, dynamic>();
        return errorMap["error"] ?? "";
      }
    }

    return errorData.toString();
  }

  static Future showError(BuildContext context, String message, Object? error) {
    /*ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        backgroundColor: const Color.fromRGBO(230, 0, 0, 80),
        showCloseIcon: true,
        content: Text(message)));*/
    return showDialog(
        context: context,
        builder: (context) => AlertDialog(
                backgroundColor: Colors.red,
                titleTextStyle: TextStyle(fontSize: 20, color: Colors.white),
                contentTextStyle: TextStyle(color: Colors.white),
                title: Text(message),
                content: Text(_error2String(error)),
                actions: [
                  TextButton(
                    child: const Text('OK'),
                    onPressed: () => Navigator.of(context).pop(),
                  ),
                ]));
  }
}
