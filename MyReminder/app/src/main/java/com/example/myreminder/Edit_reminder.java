package com.example.myreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Edit_reminder extends AppCompatDialogFragment {
    RemindersDbAdapter DB;
    EditText reminderNameEdit;
    CheckBox importantEdit;
    Button commitEdit,cancelEdit;
    MainActivity a;
    Reminder r;

    Edit_reminder(RemindersDbAdapter remindersDbAdapter, MainActivity a, Reminder R) {
        DB = remindersDbAdapter;
        this.a = a;
        this.r = R;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newlayout, null);
        view.setBackgroundColor(0xff3d5afe);;

        builder.setView(view)
                .setTitle("Edit Reminder");

        reminderNameEdit = (EditText) view.findViewById(R.id.new_reminder);
        reminderNameEdit.setText(r.getContent());
        importantEdit = (CheckBox) view.findViewById(R.id.newcheckBox);
        System.out.println(r.getImportant());
        if (r.getImportant() == 0)
            importantEdit.setChecked(false);
        else
            importantEdit.setChecked(true);
        commitEdit = (Button) view.findViewById(R.id.newcommit);
        cancelEdit = (Button) view.findViewById(R.id.newcancel);

        AddData();

        return builder.create();
    }

    public void AddData() {

        commitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change it to update
                if (reminderNameEdit.getText().toString().isEmpty()) {
                    Toast.makeText(a,"Can not insert an empty reminder",Toast.LENGTH_SHORT).show();
                    return;
                }
                r.setContent(reminderNameEdit.getText().toString());
                if (importantEdit.isChecked())
                    r.setImportant(1);
                else
                    r.setImportant(0);

                DB.updateReminder(r);
                a.update_mylist();
                dismiss();
            }
        });

        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
