import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/homeview_material.dart';

class LoginView extends StatefulWidget {
  static const routeName = "/login";

  const LoginView({super.key});

  @override
  _LoginViewState createState() => _LoginViewState();
}

class _LoginViewState extends State<LoginView> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  final TextEditingController userNameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
            width: double.infinity,
            height: double.infinity,
            child: Align(
                alignment: Alignment.center,
                child: Container(
                    alignment: Alignment.center,
                    constraints: BoxConstraints(maxWidth: 400, maxHeight: 300),
                    child: Form(
                        key: _formKey,
                        child: Column(
                          children: [
                            Padding(
                              padding: EdgeInsets.all(15),
                              child: TextField(
                                controller: userNameController,
                                decoration: InputDecoration(labelText: "Login"),
                                autofocus: true,
                              ),
                            ),
                            Padding(
                              padding: EdgeInsets.all(15),
                              child: TextField(
                                obscureText: true,
                                controller: passwordController,
                                decoration:
                                    InputDecoration(labelText: "Password"),
                              ),
                            ),
                            Padding(
                                padding: EdgeInsets.all(15),
                                child: ElevatedButton(
                                  onPressed: () => {
                                    MyHttpClient.instance
                                        .login(userNameController.text,
                                            passwordController.text)
                                        .then((value) => {
                                              Navigator.pushNamed(context,
                                                  HomeViewMaterial.routeName)
                                            })
                                        .catchError((e) => {
                                              ScaffoldMessenger.of(context)
                                                  .showSnackBar(SnackBar(
                                                      backgroundColor:
                                                          Color.fromRGBO(
                                                              230, 0, 0, 80),
                                                      content: Text(
                                                          "Login failed:" +
                                                              e.toString())))
                                            })
                                  },
                                  child: Text("Login"),
                                ))
                          ],
                        ))))));
  }
}
