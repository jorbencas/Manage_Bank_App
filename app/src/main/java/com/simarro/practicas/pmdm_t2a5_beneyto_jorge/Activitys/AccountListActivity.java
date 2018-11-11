package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters.AccountListAdapter;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class AccountListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView list;
    private AccountListAdapter<Cuenta> accountadapter;
    private Cliente cliente;
    private List<Cuenta> listaCuentas;
    private MiBancoOperacional mbo;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        this.mbo = MiBancoOperacional.getInstance(this);
        this.cargarcuenta();

        this.list = (ListView)findViewById(R.id.lista);
        this.accountadapter = new AccountListAdapter<Cuenta>(this,listaCuentas);
        this.list.setAdapter(accountadapter);

        this.list.setOnItemClickListener(this);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(AccountListActivity.this,ListTransactionsActivity.class);
        int cuenta_id = this.accountadapter.getItem(position).getId();
        startActivity(intent.putExtra("cuenta",String.valueOf(cuenta_id)));
        Toast.makeText(this, "Pulsado " + this.accountadapter.getItem(position).getId()  , Toast.LENGTH_SHORT).show();
    }

    public void cargarcuenta(){
        this.cliente = new Cliente();
        this.cliente.setId(Integer.parseInt(this.getIntent().getStringExtra("cliente")));
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
}
