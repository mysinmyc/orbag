import 'package:flutter/material.dart';

class MyTreeItemData {
  final String? label;
  final IconData? icon;
  final Color? iconColor;
  final dynamic extra;
  final bool hasChildren;
  final MyTreeItemData? parentData;
  const MyTreeItemData(
      {this.label,
      this.extra,
      this.hasChildren = true,
      this.parentData,
      this.icon,
      this.iconColor});
}
