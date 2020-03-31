package com.example.myreminder;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    ListView list;
    RemindersSimpleCursorAdapter myAdapter;
    RemindersDbAdapter reminderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Cursor cursor = reminderAdapter.fetchAllReminders();
        String [] names = new String [] {reminderAdapter.COL_CONTENT,reminderAdapter.COL_IMPORTANT};
        int [] to_ids = new int [] {R.id.reminder, R.id.importance};
        myAdapter = new RemindersSimpleCursorAdapter(this, R.layout.listview_custom,cursor,names,to_ids,0 );
        list = findViewById(R.id.ReminderList);
        //list.setAdapter(myAdapter);

        reminderAdapter = new RemindersDbAdapter(this);
        reminderAdapter.open();
//        registerForContextMenu(list);
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
        //Reminder currentReminder = (Reminder) myAdapter.getItem(info.position);
        switch (item.getItemId()){
            case(R.id.edit_reminder):{
                //openDialog2 for editing
                openDialogedit();
            }
            case(R.id.delete_reminder):{
                reminderAdapter.deleteReminderById(currentReminder.getId());
            }
        }
        update_mylist();
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
                update_mylist();
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        reminderAdapter.close();
        super.onDestroy();
    }

    public void update_mylist(){
        Cursor c = reminderAdapter.fetchAllReminders();
        myAdapter.changeCursor(c);
    }

    public void openDialogedit() {
        Edit_reminder dialog = new Edit_reminder(reminderAdapter);
        dialog.show(getSupportFragmentManager(), "edit Reminder");
    }

    public void openDialogNew() {
        new_reminder dialog = new new_reminder(reminderAdapter);
        dialog.show(getSupportFragmentManager(), "new Reminder");
    }
}
