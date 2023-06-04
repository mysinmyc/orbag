import 'package:flutter/material.dart';

class ErrorMessageWrapper<T> {
  ErrorMessageWrapper(
      BuildContext context, Future<T> future, String errorMessage) {
    future.catchError((error, stackTrace) => {
          ScaffoldMessenger.of(context).showSnackBar(SnackBar(
              backgroundColor: Color.fromRGBO(230, 0, 0, 80),
              content: Text(errorMessage + ":" + error.toString())))
        });
  }
}
