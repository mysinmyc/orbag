import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/framework/reference.dart';
import 'package:orbag_ui_flutter/views/EditView.dart';

class ConfigurationItemLink extends StatelessWidget {
  final ConfigurationItemReference ci;
  const ConfigurationItemLink(this.ci, {super.key});

  Widget getLabel() {
    return Text(ci.displayLabel == null ? "???" : ci.displayLabel!);
  }

  @override
  Widget build(BuildContext context) {
    return ElevatedButton.icon(
        icon: const Icon(Icons.edit),
        label: getLabel(),
        onPressed: () {
          Navigator.pushNamed(context, EditView.routeName, arguments: ci);
        });
  }
}
