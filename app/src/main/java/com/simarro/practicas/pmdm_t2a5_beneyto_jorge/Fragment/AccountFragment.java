package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys.AccountListActivity;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters.AccountListAdapter;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Interfaces.AccountListInterface;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import java.util.List;

public class AccountFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lstListado;
    private Cliente cliente;
    private List<Cuenta> listaCuentas;
    private MiBancoOperacional mbo;
    private FloatingActionButton fab;
    private AccountListInterface cuenta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_account, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        this.mbo = MiBancoOperacional.getInstance(this.getContext());
        this.cargarcuenta();
        lstListado = (ListView) getView().findViewById(R.id.listaaccounts);
        lstListado.setAdapter(new AccountListAdapter(this, listaCuentas));
        lstListado.setOnItemClickListener(this);
        this.fab = (FloatingActionButton)getView().findViewById(R.id.fab);
        this.fab.setOnClickListener(this);

    }

    public void cargarcuenta(){
        this.cliente = new Cliente();

        int id = Integer.parseInt(getActivity().getIntent().getStringExtra("cliente"));
        this.cliente.setId(id);
        System.out.println("Id Account: " + this.cliente.toString());
        this.listaCuentas = this.mbo.getCuentas(this.cliente);

        for(int i=0;i<listaCuentas.size();i++){
            System.out.println("\n" + listaCuentas.get(i).toString() + "\n");
        }
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (cuenta != null)
        cuenta.onAccountSelected((Cuenta) lstListado.getAdapter().getItem(position));
    }

    public void setAccountListener(AccountListActivity accountListActivity) {
        this.cuenta = accountListActivity;
    }
}
