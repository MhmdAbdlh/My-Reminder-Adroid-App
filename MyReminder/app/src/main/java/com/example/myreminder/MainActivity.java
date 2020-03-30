package com.example.myreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                PopupMenu popup = new PopupMenu(MainActivity.this, view);
//                popup.getMenuInflater().inflate(R.menu.menu,popup.getMenu());
//                popup.show();
                openDialog();
            }
        });


    }

    public void openDialog() {
        Edit_dialog dialog = new Edit_dialog();
        dialog.show(getSupportFragmentManager(), "edit Reminder");
    }
}
