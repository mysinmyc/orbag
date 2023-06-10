import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu_drawer.dart';
import 'package:orbag_ui_flutter/components/search_ci.dart';
import 'package:orbag_ui_flutter/components/util/view_util.dart';

class SearchView extends StatelessWidget {
  const SearchView({super.key, this.title = "Search"});

  static const routeName = "/search";

  final String title;

  @override
  Widget build(BuildContext context) {
    return ViewUtil.checkViewAgs(context, (context, arguments) {
      final args = arguments as Map<String, String>;

      return Scaffold(
          appBar: AppBar(title: Text(args['configurationItemType']!)),
          drawer: const MainMenuDrawer(),
          body: SingleChildScrollView(
              child: SearchCi(args['configurationItemType']!)));
    });
  }
}
