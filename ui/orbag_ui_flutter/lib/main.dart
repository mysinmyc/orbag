import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/views/HomeView.dart';
import 'package:orbag_ui_flutter/views/SearchView.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: 'Orbag UI', home: const HomeView(), routes: {
      SearchView.routeName: (context) => const SearchView(),
    });
  }
}
