import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/views/action_view.dart';
import 'package:orbag_ui_flutter/views/create_view.dart';
import 'package:orbag_ui_flutter/views/edit_view.dart';
import 'package:orbag_ui_flutter/views/login_view.dart';
import 'package:orbag_ui_flutter/views/home_view_material.dart';
import 'package:orbag_ui_flutter/views/search_view.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: 'Orbag UI', home: const LoginView(), routes: {
      HomeViewMaterial.routeName: (context) => const HomeViewMaterial(),
      LoginView.routeName: (context) => const LoginView(),
      SearchView.routeName: (context) => const SearchView(),
      EditView.routeName: (context) => const EditView(),
      CreateView.routeName: (context) => const CreateView(),
      ActionView.routeName: (context) => const ActionView(),
    });
  }
}
