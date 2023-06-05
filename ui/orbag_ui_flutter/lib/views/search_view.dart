import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu_drawer.dart';
import 'package:orbag_ui_flutter/components/search_ci.dart';

class SearchView extends StatelessWidget {
  const SearchView({super.key, this.title = "Search"});

  static const routeName = "/search";

  final String title;

  @override
  Widget build(BuildContext context) {
    final args =
        ModalRoute.of(context)!.settings.arguments as Map<String, String>;
    return Scaffold(
        appBar: AppBar(title: Text(args['configurationItemType']!)),
        drawer: const MainMenuDrawer(),
        body: SingleChildScrollView(
            child: SearchCi(args['configurationItemType']!)));
  }
}
