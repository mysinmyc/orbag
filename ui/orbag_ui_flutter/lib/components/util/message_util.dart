import 'package:flutter/material.dart';

class MessageUtil {
  static Future showError(BuildContext context, String message, Object? error) {
    /*ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        backgroundColor: const Color.fromRGBO(230, 0, 0, 80),
        showCloseIcon: true,
        content: Text(message)));*/
    return showDialog(
        context: context,
        builder: (context) => AlertDialog(title: Text(message), actions: [
              TextButton(
                child: const Text('OK'),
                onPressed: () => Navigator.of(context).pop(),
              ),
            ]));
  }
}
