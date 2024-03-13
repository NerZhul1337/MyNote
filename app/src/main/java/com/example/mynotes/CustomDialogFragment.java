package com.example.mynotes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;


public class CustomDialogFragment extends DialogFragment{
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Диалоговое окно").setMessage("Заполните пожалуйста все поля.\n" +
                "Заполнять можно как на русском, так и на английском языках.\n" +
                "Для закрытия нажмите вне границ этого окна.").create();
    }
}
