package com.example.myreminder;

//package com.mine.trial1;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

//import android.support.v4.content.ContextCompat;
//import android.support.v4.widget.SimpleCursorAdapter;



/**
 * Created by engMa_000 on 2017-04-03.
 */

public class RemindersSimpleCursorAdapter extends SimpleCursorAdapter {

    public RemindersSimpleCursorAdapter(Context context, int layout, Cursor c, String[]
            from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
    //to use a viewholder, you must override the following two methods and define a ViewHolder class
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.colImp = cursor.getColumnIndexOrThrow(RemindersDbAdapter.COL_IMPORTANT);
            holder.listTab = view.findViewById(R.id.importance);
            holder.colContent = cursor.getColumnIndexOrThrow(RemindersDbAdapter.COL_CONTENT);
            holder.contentTab = view.findViewById(R.id.reminder);
            view.setTag(holder);
        }

        String data = cursor.getString(holder.colContent);
        ((TextView)holder.contentTab).setText(data);
        if (cursor.getInt(holder.colImp) > 0) {
            holder.listTab.setBackgroundColor(ContextCompat.getColor(context, R.color.orange));
        } else {
            holder.listTab.setBackgroundColor(ContextCompat.getColor(context,R.color.green));
        }

    }

    @Override
    public Object getItem(int position) {
        Cursor c = getCursor();
        c.move(position);
        int id = c.getInt(0);
        int imp = c.getInt(1);
        String content = c.getString(2);
        Reminder to_ret = new Reminder(id,content,imp);
        return to_ret;
    }

    static class ViewHolder {
        //store the column index
        int colImp;
        //store the view
        View listTab;

        int colContent;
        View contentTab;
    }

}


