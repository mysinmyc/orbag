class Holder<T> {
  T? _value;

  void set value(T? value) {
    this._value = value;
  }

  T? get value {
    return _value;
  }
}
