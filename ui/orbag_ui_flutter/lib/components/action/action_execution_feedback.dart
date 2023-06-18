import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_form.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';
import 'package:url_launcher/url_launcher.dart';

typedef SubmitActionResponseConsequencesCallback<T> = void Function(
    BuildContext context, T value);

class ActionExecutionFeedBack extends StatelessWidget {
  final SubmitActionResponseConsequencesCallback<ActionSubmissionResultInfo>?
      onDeleted;
  final SubmitActionResponseConsequencesCallback<ActionSubmissionResultInfo>?
      onUpdated;
  final Queue<ActionSubmissionResultInfo> data;
  const ActionExecutionFeedBack(this.data,
      {this.onDeleted, this.onUpdated, super.key});

  Widget _buildErrorSnackBar(
      BuildContext context, ActionSubmissionResultInfo info) {
    List<Widget> content = List.empty(growable: true);

    content.add(Expanded(
        child: Text(info.actionData.action.displayLabel! +
            " failed: " +
            info.errorMessage)));

    return Container(height: 80, child: Row(children: content));
  }

  Widget _buildSnackBarContent(
      BuildContext context, ActionSubmissionResultInfo info) {
    List<Widget> content = List.empty(growable: true);

    content.add(Expanded(
        child: Text(info.response!.message == null
            ? info.actionData.action.displayLabel! + " executed"
            : info.response!.message!)));

    if (info.response!.link != null) {
      content.add(Padding(
          padding: EdgeInsets.only(right: 20),
          child: ElevatedButton.icon(
              label: Text("Go to website"),
              icon: Icon(Icons.web),
              onPressed: () {
                launchUrl(Uri.parse(info.response!.link!));
              })));
    }

    if (info.response!.jobId != null) {
      content.add(ElevatedButton.icon(
          label: Text("Job status"),
          icon: Icon(Icons.schedule),
          onPressed: () {}));
    }

    return Container(height: 80, child: Row(children: content));
  }

  bool _processQuickResult(
      BuildContext context, ActionSubmissionResultInfo info) {
    if (!info.actionData.action.quick!) {
      return false;
    }
    if (info.response?.link != null) {
      launchUrl(Uri.parse(info.response!.link!));
      return true;
    }
    return false;
  }

  void _processConsequences(
      BuildContext context, ActionSubmissionResultInfo info) {
    switch (info.response!.consequences) {
      case SubmitActionResponseConsequencesEnum.DELETED:
        if (onDeleted != null) {
          onDeleted!(context, info);
        }
        break;
      case SubmitActionResponseConsequencesEnum.UPDATED:
        if (onUpdated != null) {
          onUpdated!(context, info);
        }
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    if (data.isNotEmpty) {
      ActionSubmissionResultInfo info = data.removeLast();
      SchedulerBinding.instance.addPostFrameCallback((timeStamp) {
        if (!_processQuickResult(context, info)) {
          ScaffoldMessenger.of(context)
            ..clearSnackBars()
            ..showSnackBar(SnackBar(
                behavior: SnackBarBehavior.floating,
                backgroundColor:
                    info.succeded ? Theme.of(context).primaryColor : Colors.red,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(24),
                ),
                duration: Duration(seconds: 30),
                showCloseIcon: true,
                content: info.succeded
                    ? _buildSnackBarContent(context, info)
                    : _buildErrorSnackBar(context, info)));
        }
        _processConsequences(context, info);
      });
    }

    return RenderUtil.empty();
  }
}
