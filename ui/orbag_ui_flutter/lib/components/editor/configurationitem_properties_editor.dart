import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/fieldgroup_editor.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class ConfigurationItemPropertiesEditor extends StatefulWidget {
  final ConfigurationItemReference ci;
  const ConfigurationItemPropertiesEditor(this.ci, {super.key});

  @override
  State<ConfigurationItemPropertiesEditor> createState() =>
      _ConfigurationItemPropertiesEditorState();
}

class _ConfigurationItemPropertiesEditorState
    extends State<ConfigurationItemPropertiesEditor> {
  late Future<UpdateRequest?> _updateRequestFuture;

  @override
  void initState() {
    super.initState();
    _updateRequestFuture =
        MyHttpClient.instance.updateApi.buildUpdateTemplate(widget.ci);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _updateRequestFuture,
      builder: (BuildContext context, AsyncSnapshot<UpdateRequest?> snapshot) {
        if (snapshot.hasData) {
          return FieldGroupEditor(
              snapshot.data!.properties!,
              (fields) => MyHttpClient.instance.updateApi
                  .update(UpdateRequest(
                      configurationItem: widget.ci, properties: fields))
                  .whenComplete(() => setState(() {
                        _updateRequestFuture = MyHttpClient.instance.updateApi
                            .buildUpdateTemplate(widget.ci);
                      })));
        } else {
          return const Text("loading...");
        }
      },
    );
  }
}
