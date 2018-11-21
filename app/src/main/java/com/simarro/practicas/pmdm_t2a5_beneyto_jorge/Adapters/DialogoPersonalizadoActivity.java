package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;

public class DialogoPersonalizadoActivity extends DialogFragment {

    TextView textoId=null;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        String myTitulo = getArguments().getString("titulo");
        int myvalor = getArguments().getInt("valor");

        System.out.println(myTitulo + myvalor);
        builder.setView(inflater.inflate(R.layout.activity_dialogo_personalizado, null))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
