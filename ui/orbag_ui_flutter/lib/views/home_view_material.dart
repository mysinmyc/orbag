import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu_drawer.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class HomeViewMaterial extends StatelessWidget {
  static const routeName = "/home";

  const HomeViewMaterial({super.key, this.title = "Home"});

  final String title;

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: MyHttpClient.instance.configApi().getConfig(),
        builder: (context, snapshot) => Scaffold(
              appBar: AppBar(
                  title: Text(
                      snapshot.hasData ? snapshot.data!.applicationName! : "")),
              /*drawer: MainMenuDrawer(
                  title:
                      snapshot.hasData ? snapshot.data!.applicationName! : ""),
              */
              body: Row(
                children: [
                  MainMenuDrawer(title: "", showHome: false),
                  Expanded(child: Text(""))
                ],
              ),
            ));
  }
}
