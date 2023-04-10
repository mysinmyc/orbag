import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/views/EditView.dart';
import 'package:orbag_ui_flutter/views/homeview_material.dart';
import 'package:orbag_ui_flutter/views/SearchView.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Orbag UI',
        home: const HomeViewMaterial(),
        routes: {
          SearchView.routeName: (context) => const SearchView(),
          EditView.routeName: (context) => const EditView(),
        });
  }
}
