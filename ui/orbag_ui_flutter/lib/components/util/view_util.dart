import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/views/login_view.dart';

typedef ViewBuilder = Widget Function(BuildContext context, Object arguments);

class ViewUtil {
  static Widget checkViewAgs(context, ViewBuilder builder) {
    if (ModalRoute.of(context)!.settings.arguments == null) {
      return Card(
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
            Text("An error occurred"),
            TextButton(
                child: Text("Reload"),
                onPressed: () => Navigator.pushReplacementNamed(
                    context, LoginView.routeName))
          ]));
    } else {
      return builder(context, ModalRoute.of(context)!.settings.arguments!);
    }
  }
}
