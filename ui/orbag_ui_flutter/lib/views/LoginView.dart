import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/mainmenu_drawer.dart';

class LoginView extends StatefulWidget {
  static const routeName = "/login";

  const LoginView({super.key});
  @override
  _LoginViewState createState() => _LoginViewState();
}

class _LoginViewState extends State<LoginView> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Form(
        key: _formKey,
        child: Column(
          children: [
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                decoration: InputDecoration(labelText: "Login"),
              ),
            ),
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                obscureText: true,
                decoration: InputDecoration(labelText: "Password"),
              ),
            ),
            Padding(
                padding: EdgeInsets.symmetric(horizontal: 15),
                child: ElevatedButton(
                  onPressed: () => {
                    _formKey.currentState!.validate();
                    
                  },
                  child: Text("Login"),
                ))
          ],
        ));
  }
}
