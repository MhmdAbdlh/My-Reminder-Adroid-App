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

public class new_reminder extends AppCompatDialogFragment {
    RemindersDbAdapter DB;
    EditText reminderNameNew;
    CheckBox importantNew;
    Button commitNew,cancelNew;
    private EditText editTextAlarm;

    new_reminder(EditText reminderNameNew, CheckBox importantNew, Button commitNew, Button cancelNew,RemindersDbAdapter reminderDbHelper) {
        this.reminderNameNew = reminderNameNew;
        this.importantNew = importantNew;
        this.commitNew = commitNew;
        this.cancelNew = cancelNew;
        DB = reminderDbHelper;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newlayout, null);

        builder.setView(view)
                .setTitle("New Reminder");
        editTextAlarm = view.findViewById(R.id.new_reminder);

        commitNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.createReminder(reminderNameNew.getText().toString(), importantNew.isChecked());
            }
        });

        return builder.create();
    }
}
