
import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_form.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/action_view.dart';
import 'package:url_launcher/url_launcher.dart';

class ActionUtil {

  static Future<ActionSubmissionResultInfo> submit(BuildContext context, ActionData actionData) async {

      if (actionData.action.quick??false) {
        Completer<ActionSubmissionResultInfo> completer = Completer();
        var request = await MyHttpClient.instance.actionApi.buildExecutionTemplate(BuildActionTemplateRequest(action: actionData.action, sourceCi: actionData.sourceCi,targetCis: actionData.targetCis));
        var response = await MyHttpClient.instance.actionApi.submit(request!);
        if (! response!.requestValid!) {
          return ActionView.show(context, actionData);
        }
        completer.complete(ActionSubmissionResultInfo(actionData, response));
        return completer.future;
      } else {
        return ActionView.show(context, actionData);
      }
  }
}