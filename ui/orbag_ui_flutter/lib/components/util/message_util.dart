import 'package:flutter/material.dart';

class MessageUtil {
  static void showError(BuildContext context, String message) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        backgroundColor: const Color.fromRGBO(230, 0, 0, 80),
        content: Text(message)));
  }
}
