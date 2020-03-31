package com.example.myreminder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    EditText reminderNameNew,reminderNameEdit;
    CheckBox importantNew,importantEdit;
    Button commitNew,commitEdit,cancelNew,cancelEdit;
    RemindersDbAdapter reminderDbHelper;
    ListView listView;
    RemindersDbAdapter reminderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.ReminderList);
        reminderAdapter = new RemindersDbAdapter(this);
        reminderAdapter.open();
        Cursor cursor = reminderAdapter.fetchAllReminders();
        String [] names = new String [] {reminderAdapter.COL_CONTENT,reminderAdapter.COL_IMPORTANT};
        int [] to_ids = new int [] {R.id.reminder, R.id.importance};
        RemindersSimpleCursorAdapter myAdapter = new RemindersSimpleCursorAdapter(this, R.layout.listview_custom,cursor,names,to_ids,0 );

        ArrayList <String> myList = new ArrayList <>();
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        myList.add("reminder1");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myList);
        list.setAdapter(myAdapter);
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case(R.id.edit_reminder):{
                //openDialog2 for editing
                openDialogEdit();
            }
            case(R.id.delete_reminder):{
                //reminderAdapter.deleteReminderById(item.);

            }

        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drop_down_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit: {
                this.finish();
                System.exit(0);
                return true;
            }
            case R.id.new_reminder: {
                openDialogNew();
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void openDialogEdit() {
        Edit_reminder dialog = new Edit_reminder();
        dialog.show(getSupportFragmentManager(), "edit Reminder");
    }

    public void openDialogNew() {
        new_reminder dialog = new new_reminder(reminderAdapter);
        dialog.show(getSupportFragmentManager(), "new Reminder");
    }
}
