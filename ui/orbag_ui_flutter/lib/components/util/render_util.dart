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

  static List<Widget> padAll(Iterable<Widget> widgets,
      {padding = defaultPadding}) {
    return widgets.map((e) => RenderUtil.pad(e, padding: padding)).toList();
  }

  static List<Widget> toRows(List<Widget> widgets, int size) {
    List<Widget> result = List.empty(growable: true);

    List<Widget> currentRow = List.empty(growable: true);
    for (Widget currentWidget in widgets) {
      if (currentRow.length == size) {
        result.add(Row(children: currentRow));
        currentRow = List.empty(growable: true);
      }
      currentRow.add(Expanded(child: currentWidget));
    }
    if (currentRow.isNotEmpty) {
      result.add(Row(children: currentRow));
    }
    return result;
  }

  static Widget empty() {
    return const SizedBox.shrink();
  }
}
