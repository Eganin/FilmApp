package com.example.moviesapp.utils;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.moviesapp.data.DB.DatabaseHandler;

public class MyDialogFragment extends DialogFragment {
    private static final String title="Удаление истории запросов";
    private static  final String message="Вы точно хотите очистить историю?";
    private static final String button1String ="Да";
    private static  final String button2String="Нет";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // создание диалогового окна
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Идет удаление...",
                        Toast.LENGTH_LONG).show();

                DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
                databaseHandler.deleteAllUserInfo();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Удаление отменено", Toast.LENGTH_LONG)
                        .show();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }


}