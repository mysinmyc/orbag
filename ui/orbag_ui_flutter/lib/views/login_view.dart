import 'package:flutter/material.dart';
import 'package:orbag_ui_flutter/components/util/error_message_wrapper.dart';
import 'package:orbag_ui_flutter/framework/client.dart';
import 'package:orbag_ui_flutter/views/home_view_material.dart';

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
        body: SizedBox(
            width: double.infinity,
            height: double.infinity,
            child: Align(
                alignment: Alignment.center,
                child: Container(
                    alignment: Alignment.center,
                    constraints: const BoxConstraints(maxWidth: 400, maxHeight: 300),
                    child: Form(
                        key: _formKey,
                        child: Column(
                          children: [
                            Padding(
                              padding: const EdgeInsets.all(15),
                              child: TextField(
                                controller: userNameController,
                                decoration: const InputDecoration(labelText: "Login"),
                                autofocus: true,
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.all(15),
                              child: TextField(
                                obscureText: true,
                                controller: passwordController,
                                decoration:
                                    const InputDecoration(labelText: "Password"),
                              ),
                            ),
                            Padding(
                                padding: const EdgeInsets.all(15),
                                child: ElevatedButton(
                                  onPressed: () => {
                                    ErrorMessageWrapper(
                                        context,
                                        MyHttpClient.instance
                                            .login(userNameController.text,
                                                passwordController.text)
                                            .then((value) => {
                                                  Navigator.pushNamed(
                                                      context,
                                                      HomeViewMaterial
                                                          .routeName)
                                                }),
                                        "Login failed")
                                  },
                                  child: const Text("Login"),
                                ))
                          ],
                        ))))));
  }
}
