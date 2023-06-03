import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/inputpropertyeditor.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class ConfigurationItemEditor extends StatefulWidget {
  final ConfigurationItemReference ci;
  const ConfigurationItemEditor(this.ci, {super.key});

  @override
  State<ConfigurationItemEditor> createState() =>
      _ConfigurationItemEditorState();
}

class _ConfigurationItemEditorState extends State<ConfigurationItemEditor> {
  late Future<UpdateRequest?> _updateRequest;

  @override
  void initState() {
    super.initState();
    _updateRequest =
        MyHttpClient.instance.updateApi.buildUpdateTemplate(widget.ci);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _updateRequest,
      builder: (BuildContext context, AsyncSnapshot<UpdateRequest?> snapshot) {
        if (snapshot.hasData) {
          return InputPropertyEditor(
              snapshot.data!.properties!,
              (fields) => {
                    MyHttpClient.instance.updateApi
                        .update(UpdateRequest(
                            configurationItem: widget.ci, properties: fields))
                        .whenComplete(() => {
                              setState(() {
                                _updateRequest = MyHttpClient.instance.updateApi
                                    .buildUpdateTemplate(widget.ci);
                              })
                            })
                  });
        } else {
          return const Text("loading...");
        }
      },
    );
  }
}
