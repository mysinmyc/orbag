import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';
import 'package:orbag_ui_flutter/views/edit_view.dart';

class ConfigurationItemListLink extends StatelessWidget {
  final List<ConfigurationItemReference>? cis;
  final ValueChanged<ConfigurationItemReference>? onSelectedCi;
  final bool showType;
  final bool truncateLabel;
  final double? width;
  const ConfigurationItemListLink(this.cis,
      {this.onSelectedCi,
      super.key,
      this.showType = false,
      this.truncateLabel = true,
      this.width = 200});

  String getCiLabel(ConfigurationItemReference ci) {
    String label = ci.displayLabel ?? "???";
    if (truncateLabel) {
      label = LabelUtil.truncateLabel(label);
    }
    if (showType) {
      label += " (" + (ci.configurationItemTypeDisplayLabel ?? "???") + ")";
    }
    return label;
  }

  Future<void> _showCisDialog(BuildContext context) async {
    await showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          return SimpleDialog(
              children: cis!
                  .map((e) => SimpleDialogOption(
                      onPressed: () {
                        Navigator.of(context).pop();
                        if (onSelectedCi == null) {
                          Navigator.pushNamed(context, EditView.routeName,
                              arguments: e);
                        } else {
                          onSelectedCi!(e);
                        }
                      },
                      child: Text(getCiLabel(e))))
                  .toList());
        });
  }

  @override
  Widget build(BuildContext context) {
    if (cis == null || cis!.length == 0) {
      return Text("Empty");
    } else {
      return SizedBox(
          width: width,
          child: TextButton.icon(
              icon: Icon(Icons.open_in_new),
              label: Text(" " + cis!.length.toString() + " cis"),
              onPressed: () {
                _showCisDialog(context);
              }));
    }
  }
}
