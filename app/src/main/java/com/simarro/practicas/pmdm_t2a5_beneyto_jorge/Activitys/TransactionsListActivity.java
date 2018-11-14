package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters.TransferListAdapter;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import java.util.List;

public class TransactionsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private TransferListAdapter<Movimiento> accountadapter;
    private Cuenta cuenta;
    private List<Movimiento> listaMovimiento;
    private MiBancoOperacional mbo;
    private String descripcion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        this.mbo = MiBancoOperacional.getInstance(this);
        this.cargarmovimiento();

        this.list = (ListView)findViewById(R.id.lista);
        this.accountadapter = new TransferListAdapter<Movimiento>(this,listaMovimiento);
        this.list.setAdapter(accountadapter);
        this.list.setOnItemClickListener(this);
    }

    public void cargarmovimiento(){
        this.cuenta = new Cuenta();
        this.cuenta.setId(Integer.parseInt(this.getIntent().getStringExtra("cuenta")));
        this.listaMovimiento = this.mbo.getMovimientos(this.cuenta);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        for (int i = 0; i < this.listaMovimiento.size(); i++ ){
            if(this.listaMovimiento.get(i).getId() == this.accountadapter.getItem(position).getId() ){
                this.descripcion = this.listaMovimiento.get(i).getDescripcion();
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionsListActivity.this);
        builder.setMessage(this.descripcion).setTitle("Descripción");

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
