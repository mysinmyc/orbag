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
    treeData = [
      MyTreeItemData(
          label: (widget.ci.displayLabel ?? "???") +
              " (" +
              (widget.ci.configurationItemTypeDisplayLabel ?? "???") +
              ")",
          icon: Icons.my_location,
          iconColor: Colors.black)
    ];
  }

  IconData getIcon(TreeElement element) {
    if (element.folder!) {
      return Icons.folder_open;
    }
    return element.destination! ? Icons.place : Icons.directions;
  }

  Color? getIconColor(TreeElement element, IconData? data) {
    if (data == null) {
      return null;
    }
    if (data == Icons.place) {
      return Colors.red;
    }
    if (data == Icons.directions) {
      return Colors.green;
    }
    return null;
  }

  List<MyTreeItemData> buildTreeItemData(List<TreeElement> elements,
      {IconData? icon, Color? iconColor}) {
    List<MyTreeItemData> result = List.empty(growable: true);

    if (elements.isEmpty) {
      result.add(MyTreeItemData(
          label: "no relations found",
          icon: Icons.do_not_disturb,
          iconColor: Colors.red,
          hasChildren: false));
    }

    for (TreeElement currentElement in elements) {
      result.add(MyTreeItemData(
          extra: currentElement,
          label: currentElement.displayLabel,
          icon: icon ?? getIcon(currentElement),
          iconColor:
              getIconColor(currentElement, icon ?? getIcon(currentElement)),
          hasChildren: !currentElement.destination!));
    }
    result.sort((a, b) => a.label!.compareTo(b.label!));
    return result;
  }

  Future<List<MyTreeItemData>> _loadChildren(MyTreeItemData parent) async {
    if (parent.extra == null) {
      GetChildrenResponse? response = await MyHttpClient.instance.treeApi
          .getChildren(GetChildrenRequest(parent: TreeElement(ci: widget.ci)));
      return buildTreeItemData(response!.children, icon: Icons.map_outlined);
    } else {
      if (parent.extra is TreeElement) {
        TreeElement parentTreeElement = parent.extra as TreeElement;
        if (parentTreeElement.children.isNotEmpty) {
          return buildTreeItemData(parentTreeElement.children);
        } else {
          GetChildrenResponse? response = await MyHttpClient.instance.treeApi
              .getChildren(
                  GetChildrenRequest(parent: parent.extra as TreeElement));
          return buildTreeItemData(response!.children);
        }
      } else {
        return [];
      }
    }
  }

  Widget _buildItemWidget(BuildContext context, MyTreeItemData data) {
    if (data.extra != null && data.extra! is TreeElement) {
      TreeElement extra = data.extra! as TreeElement;
      if (extra.ci == null || extra.folder!) {
        return MyTree.defaultItemWidgetBuilder(context, data);
      } else {
        return ConfigurationItemLink(
          extra.ci,
          showType: true,
          truncateLabel: false,
          width: null,
          icon: getIcon(extra),
          iconColor: getIconColor(extra, getIcon(extra)),
        );
      }
    } else {
      return MyTree.defaultItemWidgetBuilder(context, data);
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
