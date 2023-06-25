import 'package:flutter/material.dart';
import 'package:flutter_tree/flutter_tree.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/framework/client.dart';

class DependenciesWidget extends StatefulWidget {
  final ConfigurationItemReference ci;
  const DependenciesWidget(this.ci, {super.key});

  @override
  State<DependenciesWidget> createState() => _DependenciesWidgetState();
}

class ExtraCiWithPath {
  ConfigurationItemReference ci;
  SerializablePath path;
  ExtraCiWithPath(this.ci, this.path);
}

class Dummy {}

class _DependenciesWidgetState extends State<DependenciesWidget> {
  late List<TreeNodeData> treeData;

  @override
  void initState() {
    super.initState();

    treeData = [
      TreeNodeData(
          title: widget.ci.displayLabel!,
          expaned: false,
          checked: false,
          children: [])
    ];
  }

  Future<List<TreeNodeData>> _load(TreeNodeData parent) async {
    List<TreeNodeData> result = List.empty(growable: true);
    if (parent.extra == null) {
      GetAvailablePathsResponse? response = await MyHttpClient.instance.graphApi
          .getAvailablePaths(GetAvailablePathsRequest(rootCis: [widget.ci]));

      for (SerializablePath currentPath in response!.availablePaths) {
        result.add(TreeNodeData(
            title: currentPath.displayLabel!,
            expaned: false,
            checked: false,
            children: [],
            extra: ExtraCiWithPath(widget.ci, currentPath)));
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
        ConfigurationItemReference relatedCi = relation.startingCi == widget.ci
            ? relation.endCi!
            : relation.startingCi!;

        result.add(TreeNodeData(
            title: relatedCi.displayLabel! +
                " (" +
                relatedCi.configurationItemTypeDisplayLabel! +
                ")",
            expaned: false,
            checked: false,
            children: [],
            extra: ExtraCiWithPath(relatedCi, extraInfo.path)));
      }

      if (result.isEmpty) {
        result.add(TreeNodeData(
            title: "No configuration item found",
            checked: false,
            expaned: true,
            children: [],
            extra: Dummy()));
      }
    }
    return result;
  }

  @override
  Widget build(BuildContext context) {
    return TreeView(data: treeData, lazy: true, load: _load);
  }
}
