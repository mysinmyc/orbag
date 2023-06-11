import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
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

  final passwordFocusNode = FocusNode();

  late Future<ConfigResponse?> _getConfigFuture;

  @override
  void initState() {
    super.initState();
    _getConfigFuture = MyHttpClient.instance.configApi().getConfig();
  }

  void _submitLogin() {
    ErrorMessageWrapper.wrap(
        context,
        MyHttpClient.instance
            .login(userNameController.text, passwordController.text)
            .then((value) => {
                  Navigator.pushReplacementNamed(
                      context, HomeViewMaterial.routeName)
                }),
        "Login failed",
        onClose: () => FocusScope.of(context).requestFocus(passwordFocusNode));
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: _getConfigFuture,
        builder: (context, snapshot) {
          return Scaffold(
              body: SizedBox(
                  width: double.infinity,
                  height: double.infinity,
                  child: Align(
                      alignment: Alignment.center,
                      child: SizedBox(
                          width: 300,
                          child:
                              Column(mainAxisSize: MainAxisSize.min, children: [
                            Container(
                                alignment: Alignment.topCenter,
                                padding: EdgeInsets.all(20),
                                child: Text(
                                    snapshot.hasData
                                        ? snapshot.data!.applicationName!
                                        : "Orbag",
                                    style: Theme.of(context)
                                        .textTheme
                                        .displaySmall)),
                            Text(snapshot.hasData
                                ? snapshot.data!.loginMessage!
                                : "Please login"),
                            Column(children: [
                              Form(
                                  key: _formKey,
                                  child: Column(
                                    children: [
                                      Padding(
                                        padding: const EdgeInsets.all(15),
                                        child: TextField(
                                          controller: userNameController,
                                          decoration: const InputDecoration(
                                              border: OutlineInputBorder(),
                                              labelText: "Login"),
                                          textInputAction: TextInputAction.next,
                                          autofocus: true,
                                        ),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.all(15),
                                        child: TextField(
                                          obscureText: true,
                                          controller: passwordController,
                                          focusNode: passwordFocusNode,
                                          decoration: const InputDecoration(
                                              border: OutlineInputBorder(),
                                              labelText: "Password"),
                                          onSubmitted: (value) =>
                                              _submitLogin(),
                                        ),
                                      ),
                                      Padding(
                                          padding: const EdgeInsets.all(15),
                                          child: MaterialButton(
                                            minWidth: double.infinity,
                                            height: 60,
                                            textColor:
                                                Theme.of(context).cardColor,
                                            color:
                                                Theme.of(context).primaryColor,
                                            onPressed: () => {_submitLogin()},
                                            child: const Text("Login"),
                                          ))
                                    ],
                                  ))
                            ])
                          ])))));
        });
  }
}
