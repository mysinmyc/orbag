import 'dart:async';

import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_form.dart';
import 'package:orbag_ui_flutter/components/util/label_util.dart';
import 'package:orbag_ui_flutter/components/util/view_util.dart';

class ActionView extends StatelessWidget {
  static const routeName = "/action";

  const ActionView({super.key});

  @override
  Widget build(BuildContext context) {
    return ViewUtil.checkViewAgs(context, (context, arguments) {
      ActionData actionData = arguments as ActionData;
      return Scaffold(
          appBar: AppBar(
              title: Text(
                  "Execute ${actionData.action.displayLabel!} on ${LabelUtil.getCisLabel(actionData.targetCis)}")),
          body: ActionExecutionForm(actionData, (actionSubmissionResultInfo) {
            Navigator.of(context).pop(actionSubmissionResultInfo);
          }, (error) {
            Navigator.of(context).pop(
                ActionSubmissionResultInfo(actionData, null, error: error));
          }));
    });
  }

  static Future<ActionSubmissionResultInfo> show(
      BuildContext context, ActionData data) {
    Completer<ActionSubmissionResultInfo> completer = Completer();
    Navigator.of(context).pushNamed(routeName, arguments: data).then((result) {
      completer.complete(result as ActionSubmissionResultInfo);
    }).onError((error, stackTrace) {
      completer.completeError(error!, stackTrace);
    });
    return completer.future;
  }
}
