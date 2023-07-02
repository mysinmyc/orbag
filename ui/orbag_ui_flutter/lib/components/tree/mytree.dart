import 'package:flutter/widgets.dart';
import 'package:orbag_ui_flutter/components/tree/mytree_item.dart';
import 'package:orbag_ui_flutter/components/tree/mytree_item_data.dart';
import 'package:orbag_ui_flutter/components/util/render_util.dart';

class MyTree extends StatefulWidget {
  final List<MyTreeItemData> rootItems;

  final Widget Function(BuildContext, MyTreeItemData)? itemWidgetBuilder;

  static Widget defaultItemWidgetBuilder(
      BuildContext context, MyTreeItemData data) {
    return Row(
      children: [
        data.icon == null
            ? RenderUtil.empty()
            : Padding(
                padding: EdgeInsets.only(right: 5),
                child: Icon(data.icon!, color: data.iconColor)),
        Text(data.label ?? " ??? ")
      ],
    );
  }

  final Future<List<MyTreeItemData>> Function(MyTreeItemData parent)
      childrenLoader;

  const MyTree({
    Key? key,
    required this.rootItems,
    required this.childrenLoader,
    this.itemWidgetBuilder,
  }) : super(key: key);

  @override
  State<MyTree> createState() => _MyTreeState();
}

class _MyTreeState extends State<MyTree> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
        children:
            widget.rootItems.map((e) => MyTreeItem(widget, e, null)).toList());
  }
}
