import 'package:flutter/material.dart';

class MyForm extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => MyState();
}

class MyState extends State<MyForm>{
  TextEditingController textEditingController = TextEditingController();
  @override
  Widget build(BuildContext context){
    return Center(child : Column(mainAxisAlignment: MainAxisAlignment.center,
    children: [
      Text(textEditingController.text),
      TextField(controller: textEditingController),
      RaisedButton(child: Text('Change Text!'),
          onPressed: () => setState((){}))
    ]));
  }
}

class MyApp extends StatelessWidget{
  @override
  Widget build(BuildContext context){
    return MaterialApp(
      title : 'test',
      home: Scaffold(
        appBar: AppBar(title: Text('1692140 임재욱')),
        body: MyForm()
      )
    );
  }
}

void main() => runApp(MyApp());