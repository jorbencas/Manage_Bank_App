package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys.TransactionsListActivity;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters.TransferListAdapter;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Interfaces.TransactionListInterface;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import java.util.List;

public class TransactionFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView list;
    private TransferListAdapter<Movimiento> accountadapter;
    private Cuenta cuenta;
    private List<Movimiento> listaMovimiento;
    private MiBancoOperacional mbo;
    private TransactionListInterface movimientos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_transactions_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        this.mbo = MiBancoOperacional.getInstance(this.getActivity());
        this.cargarmovimiento();

        this.list = (ListView) getView().findViewById(R.id.listatransferencias);
        this.accountadapter = new TransferListAdapter<Movimiento>(this,listaMovimiento);
        this.list.setAdapter(accountadapter);
        this.list.setOnItemClickListener(this);
    }

    public void cargarmovimientoapartirdecliente(Cuenta cuenta){
        System.out.println("Id Transferencia: " + getActivity().getIntent().getStringExtra("cuenta"));
        this.cuenta.setId(cuenta.getId());
        this.cuenta = cuenta;
        this.listaMovimiento = this.mbo.getMovimientos(this.cuenta);
    }

    public void cargarmovimiento(){
        this.cuenta = new Cuenta();
        System.out.println("Id Transferencia: " + getActivity().getIntent().getStringExtra("cuenta"));
        int id = Integer.parseInt(getActivity().getIntent().getStringExtra("cuenta"));
        this.cuenta.setId(id);
        this.listaMovimiento = this.mbo.getMovimientos(this.cuenta);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(this.movimientos != null){
            this.movimientos.onTransferSelected((Movimiento) list.getAdapter().getItem(position));
        }
    }

    public void setTransferListener(TransactionsListActivity transactionsListActivity) {
        System.out.println("Hola: " + transactionsListActivity.getComponentName());
        if (transactionsListActivity != null){
            this.movimientos = transactionsListActivity;
        }

    }
}
