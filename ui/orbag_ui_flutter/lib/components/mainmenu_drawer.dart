import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/home_view_material.dart';
import 'package:orbag_ui_flutter/views/login_view.dart';
import 'package:orbag_ui_flutter/views/search_view.dart';

Drawer buildDrawerFromClassModel(BuildContext context, String title,
    GetClassModelResponse classModel, showHome) {
  Map<String, ExpansionTile> submenus = {};

  List<SerializableConfigurationItemDescriptor> descriptors =
      classModel.configurationItemDescriptors;

  descriptors.sort((a, b) {
    int result = a.category!.compareTo(b.category!);
    if (result == 0) {
      result = (a.displayLabel ?? a.name ?? "???")
          .compareTo((b.displayLabel ?? b.name ?? "???"));
    }
    return result;
  });
  for (SerializableConfigurationItemDescriptor currentClass in descriptors) {
    var currentSubMenu = submenus[currentClass.category];
    if (currentSubMenu == null) {
      currentSubMenu =
          ExpansionTile(title: Text(currentClass.category!), children: []);
      submenus[currentClass.category!] = currentSubMenu;
    }
    currentSubMenu.children.add(ListTile(
        title: Text(currentClass.displayLabel!),
        onTap: () => {
              Navigator.pushReplacementNamed(context, SearchView.routeName,
                  arguments: {"configurationItemType": currentClass.name!})
            }));
  }

  List<Widget> children = List.empty(growable: true);

  if (title.isNotEmpty) {
    children.add(DrawerHeader(
      //decoration: BoxDecoration(color: Theme.of(context).primaryColor),
      child: Text(title),
    ));
  }

  if (showHome) {
    children.add(ListTile(
        title: Text("Home"),
        onTap: () => {
              Navigator.pushReplacementNamed(
                  context, HomeViewMaterial.routeName)
            }));
  }

  children.addAll(submenus.values);

  return Drawer(
      child: ListView(
          children: (children +
              [
                ListTile(
                    title: Text("Logout"),
                    onTap: () => {
                          Navigator.pushReplacementNamed(
                              context, LoginView.routeName)
                        })
              ])));
}

class MainMenuDrawer extends StatefulWidget {
  final String title;
  final bool showHome;
  const MainMenuDrawer({this.title = "", this.showHome = true, super.key});

  @override
  State<MainMenuDrawer> createState() => _MainMenuState();
}

class _MainMenuState extends State<MainMenuDrawer> {
  final Future<GetClassModelResponse?> _classModelFuture =
      MyHttpClient.instance.metadataApi.getClassModel();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<GetClassModelResponse?>(
        future: _classModelFuture,
        builder: (BuildContext context,
            AsyncSnapshot<GetClassModelResponse?> snapshot) {
          if (snapshot.hasData) {
            return buildDrawerFromClassModel(
                context, widget.title, snapshot.requireData!, widget.showHome);
          } else {
            return const Text("Loading...");
          }
        });
  }
}
