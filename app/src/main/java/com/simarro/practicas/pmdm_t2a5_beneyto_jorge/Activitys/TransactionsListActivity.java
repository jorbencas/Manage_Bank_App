package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment.DialogoPersonalizadoActivity;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment.TransactionFragment;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Interfaces.TransactionListInterface;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;


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
        DialogoPersonalizadoActivity dialogo = new DialogoPersonalizadoActivity();
        Bundle mov = new Bundle();
        mov.putSerializable("movimiento",movimiento);
        dialogo.setArguments(mov);
        dialogo.show(fragmentManager, "tagAlerta");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
