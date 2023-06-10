import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_editor.dart';
import 'package:orbag_ui_flutter/components/util/view_util.dart';

class EditView extends StatelessWidget {
  static const routeName = "/edit";

  const EditView({super.key});

  @override
  Widget build(BuildContext context) {
    return ViewUtil.checkViewAgs(
        context,
        (context, arguments) =>
            ConfigurationItemEditor(arguments as ConfigurationItemReference));
  }
}
