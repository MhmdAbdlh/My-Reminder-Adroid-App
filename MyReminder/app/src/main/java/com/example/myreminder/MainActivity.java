package com.example.myreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.ReminderList);
        ArrayList <String> myList = new ArrayList <>();
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        myList.add("shj");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myList);
        listView.setAdapter(arrayAdapter);
    }
}
