import 'package:flutter/material.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';
import 'package:orbag_ui_flutter/components/tree/mytree.dart';
import 'package:orbag_ui_flutter/components/tree/mytree_item_data.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class DependenciesWidget extends StatefulWidget {
  final ConfigurationItemReference ci;
  const DependenciesWidget(this.ci, {super.key});

  @override
  State<DependenciesWidget> createState() => _DependenciesWidgetState();
}

class Dummy {}

class _DependenciesWidgetState extends State<DependenciesWidget> {
  late List<MyTreeItemData> treeData;

  @override
  void initState() {
    super.initState();
    treeData = [MyTreeItemData(label: widget.ci.displayLabel)];
  }

  Future<List<MyTreeItemData>> _loadChildren(MyTreeItemData parent) async {
    List<MyTreeItemData> result = List.empty(growable: true);
    if (parent.extra == null) {
      GetChildrenResponse? response = await MyHttpClient.instance.treeApi
          .getChildren(GetChildrenRequest(parent: TreeElement(ci: widget.ci)));

      for (TreeElement currentElement in response!.children) {
        result.add(MyTreeItemData(
            extra: currentElement, label: currentElement.displayLabel));
      }
    } else {
      if (parent.extra is Dummy) {
        return [];
      }

      if (parent.extra is TreeElement) {
        TreeElement parentTreeElement = parent.extra as TreeElement;

        if (parentTreeElement.children.isNotEmpty) {
          for (TreeElement currentElement in parentTreeElement.children) {
            result.add(MyTreeItemData(
                extra: currentElement, label: currentElement.displayLabel));
          }
        } else {
          GetChildrenResponse? response = await MyHttpClient.instance.treeApi
              .getChildren(
                  GetChildrenRequest(parent: parent.extra as TreeElement));

          for (TreeElement currentElement in response!.children) {
            result.add(MyTreeItemData(
                extra: currentElement, label: currentElement.displayLabel));
          }
        }
      }
    }
    return result;
  }

  Widget _buildItemWidget(BuildContext context, MyTreeItemData data) {
    if (data.extra != null && data.extra! is TreeElement) {
      TreeElement extra = data.extra! as TreeElement;
      if (extra.ci == null || extra.folder!) {
        return Text(extra.displayLabel ?? data.label ?? "???");
      } else {
        return ConfigurationItemLink(extra.ci,
            showType: true, truncateLabel: false, width: null);
      }
    } else {
      return Text(data.label ?? "???");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MyTree(
        rootItems: treeData,
        itemWidgetBuilder: _buildItemWidget,
        childrenLoader: _loadChildren);
  }
}
