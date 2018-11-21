package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class AccountListAdapter extends ArrayAdapter<Cuenta> {
    Activity context;
    private List<Cuenta> cuenta = new ArrayList<Cuenta>();

    public AccountListAdapter(Fragment context, List<Cuenta> objects) {
        super(context.getActivity(), 0, objects);
        this.context = context.getActivity();
        this.cuenta = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView =  inflater.inflate(R.layout.account_list_item, parent, false);

        TextView sucursal = (TextView)listItemView.findViewById(R.id.sucuarsal);
        TextView banco = (TextView)listItemView.findViewById(R.id.banco);
        TextView saldoactual = (TextView)listItemView.findViewById(R.id.saldo);

        sucursal.setText(cuenta.get(position).getSucursal());
        banco.setText(cuenta.get(position).getBanco());
        saldoactual.setText(String.valueOf(cuenta.get(position).getSaldoActual()));

        return listItemView;
    }
}
