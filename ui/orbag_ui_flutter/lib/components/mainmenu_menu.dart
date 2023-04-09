import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/framework/metadata.dart';

MenuBar buildMenuBarFromClassModel(
    BuildContext context, ClassModel classModel) {
  Map<String, SubmenuButton> submenus = {};

  for (ConfigurationItemDescriptor currentClass
      in classModel.configurationItemDescriptors) {
    var currentSubMenu = submenus[currentClass.category];
    if (currentSubMenu == null) {
      currentSubMenu = SubmenuButton(
          menuChildren: [], child: MenuAcceleratorLabel(currentClass.category));
      submenus[currentClass.category] = currentSubMenu;
    }
    currentSubMenu.menuChildren.add(MenuItemButton(
        child: MenuAcceleratorLabel(currentClass.displayLabel),
        onPressed: () => {
              Navigator.pushNamed(context, "/search",
                  arguments: {"configurationItemType": currentClass.name})
            }));
  }

  return MenuBar(children: List.of(submenus.values));
}

class MainMenu extends StatefulWidget {
  const MainMenu({super.key});

  @override
  State<MainMenu> createState() => _MainMenuState();
}

class _MainMenuState extends State<MainMenu> {
  final Future<ClassModel> _classModelFuture = getClassModel();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<ClassModel>(
        future: _classModelFuture,
        builder: (BuildContext context, AsyncSnapshot<ClassModel> snapshot) {
          if (snapshot.hasData) {
            return Expanded(
                child:
                    buildMenuBarFromClassModel(context, snapshot.requireData));
          } else {
            return const Text("Loading...");
          }
        });
  }
}
