import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_feedback.dart';
import 'package:orbag_ui_flutter/components/action/action_execution_form.dart';
import 'package:orbag_ui_flutter/components/action/action_util.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_properties_editor.dart';
import 'package:orbag_ui_flutter/components/tree/dependencies_widget.dart';
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

  Future<GetAvailableActionsResponse?>? _availableActionsFuture;

  @override
  void initState() {
    super.initState();
    _availableViewsFuture = MyHttpClient.instance.viewApi
        .getAvailableViews(GetAvailableViewsRequest(targetCi: widget.ci));
    _availableActionsFuture = MyHttpClient.instance.actionApi
        .getAvailable(GetAvailableActionsRequest(targetCis: [widget.ci]));
  }

  int _currentTabIndex = 0;

  _changeTabIndex(int value) {
    setState(() {
      _lastView = null;
      _currentTabIndex = value;
    });
  }

  SerializableView? _lastView;
  Future<BindViewResponse?>? _lastViewBindFuture;

  Widget buildView(SerializableView view) {
    if (_lastView == null || !(_lastView! == view)) {
      _lastViewBindFuture = MyHttpClient.instance.viewApi
          .bind(BindViewRequest(targetCi: widget.ci, view: view));
      _lastView = view;
    }

    return FutureBuilder(
        future: _lastViewBindFuture,
        builder:
            (BuildContext context, AsyncSnapshot<BindViewResponse?> snapshot) {
          if (snapshot.hasData) {
            return ConfigurationItemTable(snapshot.data!.resultTable!,
                sourceCi: widget.ci);
          } else {
            return const Text("Please wait...");
          }
        });
  }

  final Queue<ActionSubmissionResultInfo> _actionResultInfoQueue = Queue();

  _executeAction(BuildContext context, ActionData actionData) async {
    ActionUtil.submit(context, actionData).then((response) {
      setState(() {
        _actionResultInfoQueue.add(response);
      });
    });
  }

  Widget buildActions() {
    return FutureBuilder(
        future: _availableActionsFuture,
        builder: (BuildContext context,
            AsyncSnapshot<GetAvailableActionsResponse?> snapshot) {
          if (snapshot.hasData) {
            return PopupMenuButton(
                onSelected: (value) {
                  _executeAction(context, ActionData(value, [widget.ci]));
                },
                itemBuilder: (context) => snapshot.data!.availableActions
                    .map((e) => PopupMenuItem(
                          value: e,
                          child: Text(e.displayLabel!),
                        ))
                    .toList());
          } else {
            return const Text("");
          }
        });
  }

  Widget _buildTabBody(int index, List<SerializableView> views) {
    switch (index) {
      case 0:
        return ConfigurationItemPropertiesEditor(widget.ci);
      case 1:
        return DependenciesWidget(widget.ci);
      default:
        return buildView(views[_currentTabIndex - 2]);
    }
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: _availableViewsFuture,
        builder: (BuildContext context,
            AsyncSnapshot<GetAvailableViewsResponse?> snapshot) {
          List<Tab> tabs = List.empty(growable: true);
          tabs.add(const Tab(text: "Properties"));

          tabs.add(const Tab(text: "Dependencies"));

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
                title: Text(widget.ci.displayLabel ?? "???"),
                bottom: TabBar(tabs: tabs, controller: tabController),
                actions: [buildActions()],
              ),
              body: Stack(children: [
                SingleChildScrollView(
                    child: _buildTabBody(_currentTabIndex, views)),
                ActionExecutionFeedBack(_actionResultInfoQueue,
                    onDeleted: (context, result) {
                  Navigator.of(context).pop();
                })
              ]));
        });
  }
}
