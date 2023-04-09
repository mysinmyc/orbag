import 'package:flutter/material.dart';

class SearchCi extends StatefulWidget {
  final String configurationItemType;
  const SearchCi(this.configurationItemType, {super.key});

  @override
  State<SearchCi> createState() => _SearchCiState(this.configurationItemType);
}

class _SearchCiState extends State<SearchCi> {
  final String configurationItemType;
  _SearchCiState(this.configurationItemType);

  @override
  Widget build(BuildContext context) {
    return Text(configurationItemType);
  }
}
