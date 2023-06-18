import 'package:flutter/material.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/fieldgroup_editor.dart';
import 'package:orbag_ui_flutter/components/util/error_message_wrapper.dart';
import 'package:orbag_ui_flutter/components/util/message_util.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/edit_view.dart';

class CreateCi extends StatefulWidget {
  final String configurationItemType;
  final ValueChanged<ConfigurationItemReference>? onCreated;
  const CreateCi(this.configurationItemType, {this.onCreated, super.key});

  @override
  State<CreateCi> createState() => _CreateCiState();
}

class _CreateCiState extends State<CreateCi> {
  Future<CreateRequest?>? _requestTemplate;

  CreateResponse? _response;

  @override
  void initState() {
    super.initState();
    _requestTemplate = MyHttpClient.instance.createApi
        .getCreateTemplate(widget.configurationItemType);
  }

  submitCreate(CreateRequest request) async {
    ErrorMessageWrapper.wrap(
        context,
        MyHttpClient.instance.createApi.create(request).then((response) => {
              if (response!.executionStatus ==
                  CreateResponseExecutionStatusEnum.SUCCEEDED)
                {
                  if (widget.onCreated == null)
                    {
                      Navigator.of(context).pushReplacementNamed(
                          EditView.routeName,
                          arguments: response.configurationItem!)
                    }
                  else
                    {widget.onCreated!(response.configurationItem!)}
                }
              else
                {
                  setState(() {
                    _response = response;
                  })
                  /*MessageUtil.showError(
                      context, "Creation Failed", response.errorMessage)
                      */
                }
            }),
        "Creation failed");
  }

  Widget _buildResultWidget(BuildContext context, CreateResponse response) {
    if (!response.requestValid!) {
      List<Widget> children = [
        ListTile(title: Text("Create validation failed"))
      ];

      children.addAll(response.validationErrors.map((e) => Text(e.error!)));
      return Column(children: children);
    } else {
      return Text("CI created");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      FutureBuilder<CreateRequest?>(
          future: _requestTemplate,
          builder:
              (BuildContext context, AsyncSnapshot<CreateRequest?> snapshot) {
            if (snapshot.hasData) {
              return FieldGroupEditor(
                  snapshot.data!.parameters!,
                  (value) => submitCreate(CreateRequest(
                      configurationItemType: widget.configurationItemType,
                      parameters: value)),
                  saveCaption: "Create",
                  saveVisible: true);
            } else {
              return const Text("Loading...");
            }
          }),
      Conditional.single(
          context: context,
          conditionBuilder: (context) => _response != null,
          widgetBuilder: (context) {
            return _buildResultWidget(context, _response!);
          },
          fallbackBuilder: (context) => RenderUtil.empty())
    ]);
  }
}
