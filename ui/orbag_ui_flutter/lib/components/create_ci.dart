import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/inputproperty_editor.dart';
import 'package:orbag_ui_flutter/components/util/error_message_wrapper.dart';
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

  @override
  void initState() {
    super.initState();
    _requestTemplate = MyHttpClient.instance.createApi
        .getCreateTemplate(widget.configurationItemType);
  }

  submitCreate(CreateRequest request) async {
    ErrorMessageWrapper(
        context,
        MyHttpClient.instance.createApi.create(request).then((value) => {
              if (widget.onCreated == null)
                {
                  Navigator.of(context).pushReplacementNamed(EditView.routeName,
                      arguments: value)
                }
              else
                {widget.onCreated!(value!)}
            }),
        "Creation failed");
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<CreateRequest?>(
        future: _requestTemplate,
        builder:
            (BuildContext context, AsyncSnapshot<CreateRequest?> snapshot) {
          if (snapshot.hasData) {
            return InputPropertyEditor(
                snapshot.data!.parameters!,
                (value) => submitCreate(CreateRequest(
                    configurationItemType: widget.configurationItemType,
                    parameters: value)),
                saveCaption: "Create");
          } else {
            return const Text("Loading...");
          }
        });
  }
}
