import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

MenuBar buildMenuBarFromClassModel(
    BuildContext context, GetClassModelResponse classModel) {
  Map<String, SubmenuButton> submenus = {};

  for (SerializableConfigurationItemDescriptor currentClass
      in classModel.configurationItemDescriptors) {
    var currentSubMenu = submenus[currentClass.category];
    if (currentSubMenu == null) {
      currentSubMenu = SubmenuButton(
          menuChildren: const [],
          child: MenuAcceleratorLabel(currentClass.category!));
      submenus[currentClass.category!] = currentSubMenu;
    }
    currentSubMenu.menuChildren.add(MenuItemButton(
        child: MenuAcceleratorLabel(currentClass.displayLabel!),
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
  final Future<GetClassModelResponse?> _classModelFuture =
      MyHttpClient.instance.metadataApi.getClassModel();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<GetClassModelResponse?>(
        future: _classModelFuture,
        builder: (BuildContext context,
            AsyncSnapshot<GetClassModelResponse?> snapshot) {
          if (snapshot.hasData) {
            return Expanded(
                child:
                    buildMenuBarFromClassModel(context, snapshot.requireData!));
          } else {
            return const Text("Loading...");
          }
        });
  }
}
