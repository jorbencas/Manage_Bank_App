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
        /*
        this.descripcion = movimiento.getDescripcion();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(this.descripcion).setTitle("Descripción");

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
        });
        AlertDialog dialog = builder.create();
        dialog.show();*/



        FragmentManager fragmentManager = this.getSupportFragmentManager();
        DialogoPersonalizadoActivity dialogo = newInstance("jerk",1488);
        dialogo.show(fragmentManager, "tagAlerta");

    }

    static DialogoPersonalizadoActivity newInstance(String nombre,int num) {
        DialogoPersonalizadoActivity f = new DialogoPersonalizadoActivity();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("titulo", nombre);
        args.putInt("valor", num);
        f.setArguments(args);

        return f;
    }


}
