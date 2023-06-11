import 'package:flutter/material.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/fieldgroup_editor.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class ActionData {
  final SerializableAction action;
  final List<ConfigurationItemReference> targetCis;
  final ConfigurationItemReference? sourceCi;
  const ActionData(this.action, this.targetCis, {this.sourceCi});
}

class ActionExecutor extends StatefulWidget {
  final ActionData actionData;
  const ActionExecutor(this.actionData, {super.key});

  @override
  State<ActionExecutor> createState() => _ActionExecutorState();
}

class _ActionExecutorState extends State<ActionExecutor> {
  late Future<SubmitActionRequest?> _submitActionRequestFuture;
  Future<SubmitActionResponse?>? _SubmitActionResponseFuture;
  @override
  void initState() {
    super.initState();
    _submitActionRequestFuture = MyHttpClient.instance.actionApi
        .buildExecutionTemplate(BuildActionTemplateRequest(
            sourceCi: widget.actionData.sourceCi,
            targetCis: widget.actionData.targetCis,
            action: widget.actionData.action));
  }

  SubmitActionResponse? result;

  void executeAction(SerializableFieldGroup parameters) {
    _SubmitActionResponseFuture = MyHttpClient.instance.actionApi
        .submit(SubmitActionRequest(
            sourceCi: widget.actionData.sourceCi,
            targetCis: widget.actionData.targetCis,
            action: widget.actionData.action,
            parameters: parameters))
        .then((value) {
      setState(() => result = value);
    });
  }

  Widget _buildResultWidget(
      BuildContext context, SubmitActionResponse response) {
    if (!response.requestValid!) {
      List<Widget> children = [
        ListTile(title: Text("Request validation failed"))
      ];

      children.addAll(response.validationErrors.map((e) => Text(e.error!)));
      return Column(children: children);
    } else {
      return Text("Action submitted");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      Conditional.single(
          context: context,
          conditionBuilder: (context) =>
              result == null || !(result!.requestValid!),
          widgetBuilder: (context) => FutureBuilder(
              future: _submitActionRequestFuture,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return FieldGroupEditor(snapshot.data!.parameters!,
                      (value) => executeAction(value),
                      saveCaption: "Execute",
                      saveVisible: true,
                      saveIcon: const Icon(Icons.play_circle));
                } else {
                  return Text("Wait");
                }
              }),
          fallbackBuilder: (context) => Text("")),
      Conditional.single(
          context: context,
          conditionBuilder: (context) => result != null,
          widgetBuilder: (context) {
            return _buildResultWidget(context, result!);
          },
          fallbackBuilder: (context) => Text(""))
    ]);
  }
}
