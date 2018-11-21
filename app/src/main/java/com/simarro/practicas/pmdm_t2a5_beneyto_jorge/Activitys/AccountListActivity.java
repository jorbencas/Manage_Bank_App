package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment.AccountFragment;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Fragment.TransactionFragment;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Interfaces.AccountListInterface;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;

public class AccountListActivity extends AppCompatActivity implements AccountListInterface {

    private AccountFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        frag = (AccountFragment)getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        frag.setAccountListener(this);
    }

    @Override
    public void onAccountSelected(Cuenta cuenta) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgListadoTransctions) != null);
        if(hayDetalle) {
            System.out.println("Detaille horizontal");
            ((TransactionFragment)getSupportFragmentManager().findFragmentById(R.id.FrgListadoTransctions)).cargarmovimientoapartirdecliente(cuenta);
        }
        else {
            Intent intent = new Intent(AccountListActivity.this,TransactionsListActivity.class);
            int cuenta_id = cuenta.getId();
            startActivity(intent.putExtra("cuenta",String.valueOf(cuenta_id)));
        }
    }
}
