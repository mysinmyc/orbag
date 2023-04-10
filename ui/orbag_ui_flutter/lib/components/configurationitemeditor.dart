import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/inputpropertyeditor.dart';

import 'package:orbag_ui_flutter/framework/reference.dart';
import 'package:orbag_ui_flutter/framework/update.dart';

class ConfigurationItemEditor extends StatefulWidget {
  final ConfigurationItemReference ci;
  const ConfigurationItemEditor(this.ci, {super.key});

  @override
  State<ConfigurationItemEditor> createState() =>
      _ConfigurationItemEditorState();
}

class _ConfigurationItemEditorState extends State<ConfigurationItemEditor> {
  late Future<UpdateRequest> _updateRequest;

  @override
  void initState() {
    super.initState();
    _updateRequest = getUpdateRequestTemplate(widget.ci);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _updateRequest,
      builder: (BuildContext context, AsyncSnapshot<UpdateRequest> snapshot) {
        if (snapshot.hasData) {
          return InputPropertyEditor(
              snapshot.data!.properties,
              (fields) => {
                    updateConfigurationItem(UpdateRequest(widget.ci, fields))
                        .whenComplete(() => {
                              setState(() {
                                _updateRequest =
                                    getUpdateRequestTemplate(widget.ci);
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
