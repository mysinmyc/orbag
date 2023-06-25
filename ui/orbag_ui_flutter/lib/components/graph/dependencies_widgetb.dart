import 'package:flutter/material.dart';
import 'package:flutter_tree/flutter_tree.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/editor/configurationitem_link.dart';
import 'package:orbag_ui_flutter/components/tree/mytree.dart';
import 'package:orbag_ui_flutter/components/tree/mytree_item_data.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class DependenciesWidgetB extends StatefulWidget {
  final ConfigurationItemReference ci;
  const DependenciesWidgetB(this.ci, {super.key});

  @override
  State<DependenciesWidgetB> createState() => _DependenciesWidgetState();
}

class ExtraCiWithPath {
  bool isFolder;
  ConfigurationItemReference ci;
  SerializablePath path;
  ExtraCiWithPath(this.ci, this.path, this.isFolder);
}

class Dummy {}

class _DependenciesWidgetState extends State<DependenciesWidgetB> {
  late List<MyTreeItemData> treeData;

  @override
  void initState() {
    super.initState();

    treeData = [MyTreeItemData(label: widget.ci.displayLabel)];
  }

  Future<List<MyTreeItemData>> _loadChildren(MyTreeItemData parent) async {
    List<MyTreeItemData> result = List.empty(growable: true);
    if (parent.extra == null) {
      GetAvailablePathsResponse? response = await MyHttpClient.instance.graphApi
          .getAvailablePaths(GetAvailablePathsRequest(rootCis: [widget.ci]));

      for (SerializablePath currentPath in response!.availablePaths) {
        result.add(MyTreeItemData(
            label: currentPath.displayLabel!,
            extra: ExtraCiWithPath(widget.ci, currentPath, true)));
      }
    } else {
      if (parent.extra is Dummy) {
        return [];
      }

      ExtraCiWithPath extraInfo = parent.extra as ExtraCiWithPath;
      GenerateGraphResponse? response = await MyHttpClient.instance.graphApi
          .generate(GenerateGraphRequest(
              rootCis: [extraInfo.ci], path: extraInfo.path));

      for (SerializableRelation relation in response!.graph!.relations) {
        ConfigurationItemReference relatedCi =
            (relation.startingCi!.configurationItemType ==
                        extraInfo.ci.configurationItemType &&
                    relation.startingCi!.identifier == extraInfo.ci.identifier)
                ? relation.endCi!
                : relation.startingCi!;

        result.add(MyTreeItemData(
            extra: ExtraCiWithPath(relatedCi, extraInfo.path, false)));
      }
/*
      if (result.isEmpty) {
        result.add(MyTreeItemData(
            label: "No configuration item found", hasChildren: false));
  
  */
    }
    return result;
  }

  Widget _buildItemWidget(BuildContext context, MyTreeItemData data) {
    if (data.extra != null && data.extra! is ExtraCiWithPath) {
      ExtraCiWithPath extra = data.extra! as ExtraCiWithPath;
      if (extra.isFolder) {
        return Text(data.label ?? "???");
      }
      return ConfigurationItemLink(extra.ci,
          showType: true, truncateLabel: false, width: null);
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
