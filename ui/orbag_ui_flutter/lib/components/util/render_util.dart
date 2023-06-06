import 'package:flutter/widgets.dart';

class RenderUtil {
  static const EdgeInsetsGeometry defaultPadding = EdgeInsets.all(10);

  static Widget pad(Widget widget, {padding = defaultPadding}) {
    return Padding(padding: padding, child: widget);
  }

  static Widget fixedSize(Widget widget, {double? width, double? height}) {
    return SizedBox(width: width, height: height, child: widget);
  }

  static List<Widget> fixedSizeAll(List<Widget> widgets,
      {double? width, double? height}) {
    return widgets
        .map((e) => fixedSize(e, width: width, height: height))
        .toList();
  }

  static List<Widget> padAll(List<Widget> widgets, {padding = defaultPadding}) {
    return widgets.map((e) => RenderUtil.pad(e, padding: padding)).toList();
  }
}
