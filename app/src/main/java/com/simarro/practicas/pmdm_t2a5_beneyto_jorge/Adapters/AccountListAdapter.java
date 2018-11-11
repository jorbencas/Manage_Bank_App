package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;

import java.util.List;

public class AccountListAdapter<T> extends ArrayAdapter<T> {

    public AccountListAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.account_list_item, parent, false);
        }

        //Obteniendo instancias de los text views
        //ImageView category = (ImageView)listItemView.findViewById(R.id.category);
        TextView sucursal = (TextView)listItemView.findViewById(R.id.sucuarsal);
        TextView banco = (TextView)listItemView.findViewById(R.id.banco);
        TextView saldoactual = (TextView)listItemView.findViewById(R.id.saldo);

        //Obteniendo instancia de la Tarea en la posición actual
        Cuenta item = (Cuenta) getItem(position);
        System.out.println( "Banco: " + item);
        sucursal.setText(item.getSucursal());
        System.out.println("Sucursal: " + item.getSucursal());
        banco.setText(item.getBanco());
        System.out.println("Saldo Actual: " + item.getSaldoActual());
        saldoactual.setText(String.valueOf(item.getSaldoActual()));

        return listItemView;
    }
}
