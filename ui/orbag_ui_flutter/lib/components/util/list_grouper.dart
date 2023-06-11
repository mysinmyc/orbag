import 'dart:collection';

class ListGrouper<K, V> {
  Map<K, List<V>> groupBy(List<V> list, K Function(V) keyMapper) {
    LinkedHashMap<K, List<V>> result = new LinkedHashMap();

    for (V value in list) {
      K key = keyMapper(value);

      if (result.containsKey(key)) {
        result[key]!.add(value);
      } else {
        result[key] = [value];
      }
    }
    return result;
  }
}
