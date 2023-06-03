import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/configurationitemeditor.dart';
import 'package:orbag_ui_flutter/components/mainmenu_drawer.dart';

class EditView extends StatefulWidget {
  static const routeName = "/edit";

  const EditView({super.key});

  @override
  State<EditView> createState() => _EditViewState();
}

class _EditViewState extends State<EditView>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 1, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    final ci = ModalRoute.of(context)!.settings.arguments
        as ConfigurationItemReference;
    return Scaffold(
        appBar: AppBar(
          title: Text(ci.displayLabel!),
          actions: [IconButton(onPressed: () {}, icon: Icon(Icons.save))],
          bottom: TabBar(
            controller: _tabController,
            tabs: [
              Tab(text: "Properties"),
              Tab(text: "vista1"),
              Tab(text: "vista2")
            ],
          ),
        ),
        body: TabBarView(
            controller: _tabController,
            children: [ConfigurationItemEditor(ci), Text("ciao")]));
  }
}
