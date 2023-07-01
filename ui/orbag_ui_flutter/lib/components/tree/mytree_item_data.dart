class MyTreeItemData {
  final String? label;
  final dynamic extra;
  final bool hasChildren;
  final MyTreeItemData? parentData;
  const MyTreeItemData(
      {this.label, this.extra, this.hasChildren = true, this.parentData});
}
