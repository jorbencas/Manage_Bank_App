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
import android.view.View;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

public class DialogoPersonalizadoActivity extends DialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        Movimiento movimiento = (Movimiento) getArguments().getSerializable("movimiento");

        System.out.println("MOVIMIENTO" + movimiento.toString());

        View view = inflater.inflate(R.layout.activity_dialogo_personalizado, null);
        builder.setView(view);

        //TextView id = (TextView) view.findViewById(R.id.textoIdDialogo);
        TextView descripcion = (TextView)view.findViewById(R.id.textoDatosDialogo);
        //id.setText(movimiento.getId());
        descripcion.setText(movimiento.getDescripcion());

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
