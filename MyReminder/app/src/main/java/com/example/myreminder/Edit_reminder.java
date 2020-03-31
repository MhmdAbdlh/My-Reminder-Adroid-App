package com.example.myreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Edit_reminder extends AppCompatDialogFragment {
    RemindersDbAdapter DB;
    EditText reminderNameEdit;
    CheckBox importantEdit;
    Button commitEdit,cancelEdit;
    private EditText editTextAlarm;

    Edit_reminder(RemindersDbAdapter remindersDbAdapter) {
        DB = remindersDbAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newlayout, null);

        builder.setView(view)
                .setTitle("Edit Reminder");
        editTextAlarm = view.findViewById(R.id.new_reminder);

        reminderNameEdit = (EditText) view.findViewById(R.id.new_reminder);
        importantEdit = (CheckBox) view.findViewById(R.id.newcheckBox);
        commitEdit = (Button) view.findViewById(R.id.newcommit);
        cancelEdit = (Button) view.findViewById(R.id.newcancel);

        AddData();

        return builder.create();
    }

    public void AddData() {

        commitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.createReminder(reminderNameEdit.getText().toString(), importantEdit.isChecked());
                onDestroy();
            }
        });

        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }
}
