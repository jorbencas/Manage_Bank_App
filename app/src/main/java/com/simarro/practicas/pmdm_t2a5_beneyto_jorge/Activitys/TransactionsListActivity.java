package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters.DialogoPersonalizadoActivity;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters.TransferListAdapter;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment.AccountFragment;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment.TransactionFragment;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Interfaces.TransactionListInterface;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import java.util.List;

public class TransactionsListActivity extends AppCompatActivity implements TransactionListInterface {

    private TransactionFragment frag;
    private String descripcion = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);
        this.frag = (TransactionFragment)getSupportFragmentManager().findFragmentById(R.id.FrgListadoTransctions);
        this.frag.setTransferListener(this);

    }


    public void onTransferSelected(Movimiento movimiento){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        DialogoPersonalizadoActivity dialogo = newInstance(movimiento);
        dialogo.show(fragmentManager, "tagAlerta");
    }

    static DialogoPersonalizadoActivity newInstance(Movimiento movimiento) {
        DialogoPersonalizadoActivity f = new DialogoPersonalizadoActivity();
        Movimiento movement = new Movimiento();
        movement = movimiento;
        Bundle mov = new Bundle();
        mov.putSerializable("movimiento",movement);
        f.setArguments(mov);

        return f;
    }


}
