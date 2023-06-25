import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';
import 'package:orbag_ui_flutter/views/edit_view.dart';

class ConfigurationItemLink extends StatelessWidget {
  final ConfigurationItemReference? ci;
  final ValueChanged<ConfigurationItemReference>? onSelectedCi;
  final bool showType;
  final bool truncateLabel;
  final double? width;
  const ConfigurationItemLink(this.ci,
      {this.onSelectedCi,
      super.key,
      this.showType = false,
      this.truncateLabel = true,
      this.width = 200});

  String getLabel() {
    String label = ci!.displayLabel ?? "???";
    if (truncateLabel) {
      label = LabelUtil.truncateLabel(label);
    }
    if (showType) {
      label += " (" + (ci!.configurationItemTypeDisplayLabel ?? "???") + ")";
    }
    return label;
  }

  Widget getLabelWidget() {
    return Align(alignment: Alignment.centerLeft, child: Text(getLabel()));
  }

  @override
  Widget build(BuildContext context) {
    if (ci == null) {
      return Text("empty");
    } else {
      return SizedBox(
          width: width,
          child: Tooltip(
              message: ci!.displayLabel ?? "???",
              child: TextButton.icon(
                  icon: Icon(
                      onSelectedCi == null ? Icons.open_in_new : Icons.input),
                  label: getLabelWidget(),
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
