import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu_menu.dart';

class HomeViewTraditional extends StatelessWidget {
  const HomeViewTraditional({super.key, this.title = "Home"});

  final String title;

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
        body: Column(children: [
      Row(children: [MainMenu()])
    ]));
  }
}
