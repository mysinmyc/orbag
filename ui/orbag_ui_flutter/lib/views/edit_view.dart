import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_editor.dart';

class EditView extends StatelessWidget {
  static const routeName = "/edit";

  @override
  Widget build(BuildContext context) {
    return ConfigurationItemEditor(ModalRoute.of(context)!.settings.arguments!
        as ConfigurationItemReference);
  }
}
