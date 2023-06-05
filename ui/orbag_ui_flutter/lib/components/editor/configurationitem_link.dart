import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/views/edit_view.dart';

class ConfigurationItemLink extends StatelessWidget {
  final ConfigurationItemReference? ci;
  final ValueChanged<ConfigurationItemReference>? onSelectedCi;
  const ConfigurationItemLink(this.ci, {this.onSelectedCi, super.key});

  Widget getLabel() {
    return Text(ci!.displayLabel! == null ? "???" : ci!.displayLabel!);
  }

  @override
  Widget build(BuildContext context) {
    if (ci == null) {
      return Text("empty");
    } else {
      return OutlinedButton.icon(
          icon: Icon(onSelectedCi == null ? Icons.open_in_new : Icons.input),
          label: getLabel(),
          onPressed: () {
            if (onSelectedCi == null) {
              Navigator.pushNamed(context, EditView.routeName, arguments: ci);
            } else {
              onSelectedCi!(ci!);
            }
          });
    }
  }
}
