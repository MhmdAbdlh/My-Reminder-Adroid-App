package com.example.myreminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.icu.text.CaseMap;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class new_reminder extends AppCompatDialogFragment{
    RemindersDbAdapter DB;
    EditText reminderNameNew;
    CheckBox importantNew;
    Button commitNew,cancelNew;
    private EditText editTextAlarm;

    new_reminder(RemindersDbAdapter remindersDbAdapter) {
        DB = remindersDbAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        System.out.println("here");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        System.out.println("here");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newlayout, null);
        System.out.println("here");
        builder.setView(view)
                .setTitle("New Reminder");
        editTextAlarm = view.findViewById(R.id.new_reminder);
        System.out.println("here");
        reminderNameNew = (EditText) view.findViewById(R.id.new_reminder);
        importantNew = (CheckBox) view.findViewById(R.id.newcheckBox);
        commitNew = (Button) view.findViewById(R.id.newcommit);
        cancelNew = (Button) view.findViewById(R.id.newcancel);

        AddData();
        System.out.println("here");
        return builder.create();
    }

    public void AddData() {
        commitNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.createReminder(reminderNameNew.getText().toString(), importantNew.isChecked());
                onDestroy();
            }
        });
        cancelNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }
}
