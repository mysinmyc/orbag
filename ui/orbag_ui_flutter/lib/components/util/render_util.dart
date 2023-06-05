import 'package:flutter/widgets.dart';

class RenderUtil {
  static const EdgeInsetsGeometry defaultPadding = EdgeInsets.all(10);

  static Widget pad(Widget widget, {padding = defaultPadding}) {
    return Padding(padding: padding, child: widget);
  }

  static List<Widget> padAll(List<Widget> widgets, {padding = defaultPadding}) {
    return widgets.map((e) => RenderUtil.pad(e, padding: padding)).toList();
  }
}
