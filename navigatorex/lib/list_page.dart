import 'package:flutter/material.dart';
import 'package:navigatorex/ui_utils.dart';

_tile(context, icon, title) => ListTile(
  leading: Icon(icon),
  title: Text(title),
  subtitle: Text('1692140 임재욱'),
  onTap: () => showCustomDialog(context, "10차 과제", title),
);

class _ScaffoldBody extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var list = <Widget>[];
    for (var i = 0; i < 20; i++) {
      list.add(_tile(context, Icons.android_rounded, "아이템 ${i+1}"));
    }
    return ListView(
      children: list,
    );
  }
}

class ListPage extends StatelessWidget {
  static const nav_url = '/list_view';
  static const menu_name = '1692140 ListView';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(menu_name)),
        body: _ScaffoldBody()
    );
  }
}