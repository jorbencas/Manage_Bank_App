package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import java.text.SimpleDateFormat;

public class DialogoPersonalizadoActivity extends DialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        Movimiento movimiento = (Movimiento) getArguments().getSerializable("movimiento");

        View view = inflater.inflate(R.layout.activity_dialogo_personalizado, null);
        builder.setView(view);

        TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
        TextView importe = (TextView)view.findViewById(R.id.textoDatosDialogo);
        TextView tipo = (TextView)view.findViewById(R.id.tipo);
        TextView cuentaOrigen = (TextView)view.findViewById(R.id.cuenta_origen);
        TextView cuentaDestino = (TextView)view.findViewById(R.id.cuenta_destino);
        TextView fechaOrigen = (TextView) view.findViewById(R.id.fechaoperacion);

        descripcion.setText(movimiento.getDescripcion());
        importe.setText(String.valueOf(movimiento.getImporte()));
        tipo.setText(String.valueOf(movimiento.getTipo()));

        if(movimiento.getCuentaOrigen() == null){
            cuentaOrigen.setText("cunta Origen");
        }else{
            cuentaOrigen.setText(String.valueOf(movimiento.getCuentaOrigen().getNumeroCuenta()));
        }

        if (movimiento.getCuentaDestino() == null){
            cuentaDestino.setText("cunta Destino");
        }else{
            cuentaDestino.setText(String.valueOf(movimiento.getCuentaDestino().getNumeroCuenta()));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(movimiento.getFechaOperacion());
        fechaOrigen.setText(fechaComoCadena);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}
