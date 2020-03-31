package com.example.myreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Edit_reminder extends AppCompatDialogFragment {
    private EditText editTextAlarm;

    Edit_reminder() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit, null);

        builder.setView(view)
                .setTitle("Edit Reminder");
        editTextAlarm = view.findViewById(R.id.edit_reminder);
        return builder.create();
    }
}
