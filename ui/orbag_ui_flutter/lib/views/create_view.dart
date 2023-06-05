import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/create_ci.dart';

class CreateViewData {
  final String configurationItemType;
  const CreateViewData(this.configurationItemType);
}

class CreateView extends StatelessWidget {
  const CreateView({super.key, this.title = "Create"});

  static const routeName = "/create";

  final String title;

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as CreateViewData;
    return Scaffold(
        appBar: AppBar(title: Text("Create ${args.configurationItemType}")),
        body: CreateCi(
          args.configurationItemType,
        ));
  }

  static show(
    BuildContext context,
    String configurationItemType,
  ) {
    Navigator.of(context)
        .pushNamed(routeName, arguments: CreateViewData(configurationItemType));
  }
}
