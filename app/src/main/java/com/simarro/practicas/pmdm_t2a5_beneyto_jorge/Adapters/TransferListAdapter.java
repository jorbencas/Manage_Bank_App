package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TransferListAdapter<T> extends ArrayAdapter<T> {
    Activity context;
    private List<Movimiento> movimiento = new ArrayList<Movimiento>();

    public TransferListAdapter(Fragment context, List<T> objects) {
        super(context.getActivity(), 0, objects);
        this.context = context.getActivity();
        this.movimiento = movimiento;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.transactions_list_item, parent, false);
        }

        //Obteniendo instancias de los text views
        //ImageView category = (ImageView)listItemView.findViewById(R.id.category);
        TextView tipo = (TextView)listItemView.findViewById(R.id.tipo);
        //TextView cuenta_origen = (TextView)listItemView.findViewById(R.id.cuenta_origen);
        TextView cuenta_destino = (TextView)listItemView.findViewById(R.id.cuenta_destino);
        TextView importe = (TextView)listItemView.findViewById(R.id.importe);
        TextView fechaOperacion = (TextView)listItemView.findViewById(R.id.fechaOperacion);

        //Obteniendo instancia de la Tarea en la posición actual
        Movimiento item = (Movimiento) getItem(position);
        System.out.println( "Tipo: " + item.getTipo());
        tipo.setText(String.valueOf(item.getTipo()));
        //System.out.println( "Cuenta Origen: " + item.getTipo());
        //cuenta_origen.setText(String.valueOf(item.getCuentaOrigen().getDc()));
        System.out.println("Cuenta Destino: " + item.getCuentaDestino().getNumeroCuenta());
        cuenta_destino.setText(String.valueOf(item.getCuentaDestino().getNumeroCuenta()));
        System.out.println("Importe: " + item.getImporte());
        importe.setText(String.valueOf(item.getImporte()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(item.getFechaOperacion());
        System.out.println("Fecha Origen: " + fechaComoCadena);
        fechaOperacion.setText(fechaComoCadena);

        if (item.getImporte()<0){
            listItemView.setBackgroundColor(Color.parseColor("#F78181"));
        }else{
            listItemView.setBackgroundColor(Color.parseColor("#A9F5A9"));
        }


        return listItemView;
    }
}
