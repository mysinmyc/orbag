import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/action/action_executor.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';

class ActionView extends StatelessWidget {
  static const routeName = "/action";

  const ActionView({super.key});

  @override
  Widget build(BuildContext context) {
    ActionData actionData =
        ModalRoute.of(context)!.settings.arguments! as ActionData;
    return Scaffold(
        appBar: AppBar(
            title: Text(
                "Execute ${actionData.action.displayLabel!} on ${LabelUtil.getCisLabel(actionData.targetCis)}")),
        body: ActionExecutor(actionData));
  }

  static show(BuildContext context, ActionData data) {
    Navigator.of(context).pushNamed(routeName, arguments: data);
  }
}