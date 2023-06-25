import 'dart:js_interop';

import 'package:flutter/material.dart';
import 'package:flutter_conditional_rendering/flutter_conditional_rendering.dart';
import 'package:flutter_tree/flutter_tree.dart';
import 'package:openapi/api.dart';
import 'package:orbag_ui_flutter/components/tree/mytree.dart';
import 'package:orbag_ui_flutter/components/tree/mytree_item_data.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';

class MyTreeItem extends StatefulWidget {
  final MyTreeItem? parent;
  final MyTreeItemData data;
  final MyTree tree;

  const MyTreeItem(this.tree, this.data, this.parent);

  @override
  State<MyTreeItem> createState() => _MyTreeItemState();
}

class _MyTreeItemState extends State<MyTreeItem> {
  bool expanded = false;
  late bool hasChildren;
  Future<List<MyTreeItemData>>? _loadChildrenDataFuture;

  @override
  void initState() {
    super.initState();
    hasChildren = widget.data.hasChildren;
  }

  Widget _buildTitleWidgetFromData() {
    return Container(
        height: 60,
        alignment: Alignment.centerLeft,
        child: (widget.tree.itemWidgetBuilder == null)
            ? Text(widget.data.label ?? " ??? ")
            : widget.tree.itemWidgetBuilder!(context, widget.data));
  }

  void _expandAndCollapse() {
    setState(() {
      expanded = !expanded;
      if (expanded && _loadChildrenDataFuture == null) {
        _loadChildrenDataFuture =
            widget.tree.childrenLoader(widget.data).then((value) {
          setState(() {
            hasChildren = value.isNotEmpty;
          });
          return value;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          SizedBox(
              width: 50,
              height: 60,
              child: hasChildren
                  ? IconButton(
                      onPressed: _expandAndCollapse,
                      icon: Icon(expanded
                          ? Icons.keyboard_arrow_down
                          : Icons.keyboard_arrow_right))
                  : RenderUtil.empty()),
          Expanded(
              child: Conditional.single(
                  context: context,
                  conditionBuilder: (context) => expanded,
                  fallbackBuilder: (context) => _buildTitleWidgetFromData(),
                  widgetBuilder: (context) => FutureBuilder(
                      future: _loadChildrenDataFuture,
                      builder: (context, snapshot) {
                        return Column(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: [_buildTitleWidgetFromData()] +
                                (snapshot.data ?? [])
                                    .map((e) =>
                                        MyTreeItem(widget.tree, e, widget))
                                    .toList());
                      })))
        ]);
  }
}
