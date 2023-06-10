import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';
import 'package:orbag_ui_flutter/views/edit_view.dart';

class ConfigurationItemLink extends StatelessWidget {
  final ConfigurationItemReference? ci;
  final ValueChanged<ConfigurationItemReference>? onSelectedCi;
  const ConfigurationItemLink(this.ci, {this.onSelectedCi, super.key});

  Widget getLabel() {
    return Align(
        alignment: Alignment.centerLeft,
        child:
            Text(LabelUtil.truncateLabel(ci!.displayLabel ?? "???", size: 20)));
  }

  @override
  Widget build(BuildContext context) {
    if (ci == null) {
      return Text("empty");
    } else {
      return SizedBox(
          width: 200,
          child: Tooltip(
              message: ci!.displayLabel,
              child: TextButton.icon(
                  icon: Icon(
                      onSelectedCi == null ? Icons.open_in_new : Icons.input),
                  label: getLabel(),
                  onPressed: () {
                    if (onSelectedCi == null) {
                      Navigator.pushNamed(context, EditView.routeName,
                          arguments: ci);
                    } else {
                      onSelectedCi!(ci!);
                    }
                  })));
    }
  }
}
