import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_form.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/action_view.dart';

class ActionUtil {
  static Future<ActionSubmissionResultInfo> submit(
      BuildContext context, ActionData actionData) async {
    if (actionData.action.quick ?? false) {
      Completer<ActionSubmissionResultInfo> completer = Completer();

      try {
        var request = await MyHttpClient.instance.actionApi
            .buildExecutionTemplate(BuildActionTemplateRequest(
                action: actionData.action,
                sourceCi: actionData.sourceCi,
                targetCis: actionData.targetCis));

        var response = await MyHttpClient.instance.actionApi.submit(request!);

        if (!response!.requestValid!) {
          return ActionView.show(context, actionData).onError(
              (error, stackTrace) =>
                  ActionSubmissionResultInfo(actionData, null, error: error));
        }

        completer.complete(ActionSubmissionResultInfo(actionData, response));
      } catch (error) {
        completer.complete(
            ActionSubmissionResultInfo(actionData, null, error: error));
      }
      return completer.future;
    } else {
      return ActionView.show(context, actionData);
    }
  }
}
