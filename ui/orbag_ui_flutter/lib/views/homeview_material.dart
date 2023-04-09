import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu_drawer.dart';

class HomeViewMaterial extends StatelessWidget {
  const HomeViewMaterial({super.key, this.title = "Home"});

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("orbaG")),
      drawer: const MainMenuDrawer(),
    );
  }
}
