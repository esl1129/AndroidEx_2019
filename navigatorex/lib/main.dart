import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:navigatorex/list_page.dart';



class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      title: 'Navigator Example',
      home: ListPage(),
    );
  }
}

void main() => runApp(MyApp());