import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/framework/metadata.dart';
import 'package:orbag_ui_flutter/views/SearchView.dart';

Drawer buildDrawerFromClassModel(BuildContext context, ClassModel classModel) {
  Map<String, ExpansionTile> submenus = {};

  for (ConfigurationItemDescriptor currentClass
      in classModel.configurationItemDescriptors) {
    var currentSubMenu = submenus[currentClass.category];
    if (currentSubMenu == null) {
      currentSubMenu =
          ExpansionTile(title: Text(currentClass.category), children: []);
      submenus[currentClass.category] = currentSubMenu;
    }
    currentSubMenu.children.add(ListTile(
        title: Text(currentClass.displayLabel),
        onTap: () => {
              Navigator.pushNamed(context, SearchView.routeName,
                  arguments: {"configurationItemType": currentClass.name})
            }));
  }

  List<Widget> children = [
    const DrawerHeader(
      decoration: BoxDecoration(
        color: Colors.blue,
      ),
      child: Text('orbaG'),
    ),
  ];

  children.addAll(submenus.values);

  return Drawer(child: ListView(children: children));
}

class MainMenuDrawer extends StatefulWidget {
  const MainMenuDrawer({super.key});

  @override
  State<MainMenuDrawer> createState() => _MainMenuState();
}

class _MainMenuState extends State<MainMenuDrawer> {
  final Future<ClassModel> _classModelFuture = getClassModel();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<ClassModel>(
        future: _classModelFuture,
        builder: (BuildContext context, AsyncSnapshot<ClassModel> snapshot) {
          if (snapshot.hasData) {
            return buildDrawerFromClassModel(context, snapshot.requireData);
          } else {
            return const Text("Loading...");
          }
        });
  }
}
