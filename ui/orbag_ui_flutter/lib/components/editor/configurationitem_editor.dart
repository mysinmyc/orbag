import 'dart:html';

import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_properties_editor.dart';
import 'package:orbag_ui_flutter/components/table/configurationitem_table.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class ConfigurationItemEditor extends StatefulWidget {
  final ConfigurationItemReference ci;

  const ConfigurationItemEditor(this.ci, {super.key});

  @override
  State<ConfigurationItemEditor> createState() =>
      _ConfigurationItemEditorState();
}

class _ConfigurationItemEditorState extends State<ConfigurationItemEditor>
    with TickerProviderStateMixin {
  Future<GetAvailableViewsResponse?>? _availableViewsFuture;

  @override
  void initState() {
    super.initState();
    _availableViewsFuture = MyHttpClient.instance.viewApi
        .getAvailableViews(GetAvailableViewsRequest(targetCi: widget.ci));
  }

  int _currentTabIndex = 0;

  _changeTabIndex(int value) {
    setState(() {
      _currentTabIndex = value;
    });
  }

  Widget buildView(SerializableView view) {
    return FutureBuilder(
        future: MyHttpClient.instance.viewApi
            .bind(BindViewRequest(targetCi: widget.ci, view: view)),
        builder:
            (BuildContext context, AsyncSnapshot<BindViewResponse?> snapshot) {
          if (snapshot.hasData) {
            return ConfigurationItemTable(snapshot.data!.resultTable!,
                sourceCi: widget.ci);
          } else {
            return Text("Please wait...");
          }
        });
  }

  @override
  Widget build(BuildContext context) {
    final ci = ModalRoute.of(context)!.settings.arguments
        as ConfigurationItemReference;
    return FutureBuilder(
        future: _availableViewsFuture,
        builder: (BuildContext context,
            AsyncSnapshot<GetAvailableViewsResponse?> snapshot) {
          List<Tab> tabs = List.empty(growable: true);
          tabs.add(const Tab(text: "Properties"));

          List<SerializableView> views = List.empty(growable: true);

          if (snapshot.hasData) {
            for (var element in snapshot.data!.availableViews) {
              tabs.add(Tab(text: element.displayLabel!));
              views.add(element);
            }
          }

          TabController tabController = TabController(
              length: tabs.length, vsync: this, initialIndex: _currentTabIndex);
          tabController.addListener(() => _changeTabIndex(tabController.index));

          return Scaffold(
              appBar: AppBar(
                  title: Text(ci.displayLabel!),
                  bottom: TabBar(tabs: tabs, controller: tabController)),
              body: _currentTabIndex == 0
                  ? ConfigurationItemPropertiesEditor(ci)
                  : buildView(views[_currentTabIndex - 1]));
        });
  }
}
