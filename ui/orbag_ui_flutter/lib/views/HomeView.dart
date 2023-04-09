import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu.dart';

class HomeView extends StatelessWidget {
  const HomeView({super.key, this.title = "Home"});

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Column(children: [
      Row(children: const [MainMenu()])
    ]));
  }
}
