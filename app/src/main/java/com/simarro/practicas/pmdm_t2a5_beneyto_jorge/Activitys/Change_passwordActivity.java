package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;

public class Change_passwordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtlastpassword;
    private EditText txtoldpassword;
    private EditText txtpassword;
    private Button Btnaceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtlastpassword = (TextView)findViewById(R.id.txtlastpassword);
        txtlastpassword.setText(this.getIntent().getStringExtra("PASSWORD"));

        txtoldpassword = (EditText)findViewById(R.id.txtoldpassword);

        txtpassword = (EditText)findViewById(R.id.txtpassword);
        Btnaceptar = (Button)findViewById(R.id.Blnaceptar);

        Btnaceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Change_passwordActivity.this, MainActivity.class);
        MiBancoOperacional mbo = MiBancoOperacional.getInstance(this);
        String id = this.getIntent().getStringExtra("cliente");
        /*String[] partes = cliente.split("\n");
        String[] id = partes[0].split(":");
        System.out.println("ID: " + id[1]);             BAD FORM
        String trys = id[1];
        String re = trys.replaceAll(" 1","1");
        System.out.println("RWE: "+ re);
        Integer newid = Integer.parseInt(re);*/
        System.out.println("NEWID: " + id);
        Cliente a = new Cliente();
        a.setId(Integer.parseInt(id));
        String newpassword = txtoldpassword.getText().toString();
        String last = txtlastpassword.getText().toString();

        if (newpassword.equalsIgnoreCase(last)){

            System.out.println("Cambiamos la password del cliente\n");
            System.out.println("-----------------------------------------\n");
            System.out.println("\n");
            a.setClaveSeguridad(txtpassword.getText().toString());
            int p = mbo.changePassword(a);
            System.out.println("1 = " + p);
            if (p == 1){
                Log.i(a.getClaveSeguridad(), "Hemos obtenido tras cambiar un ");
                System.out.println("\n");
                System.out.println("Password cambiada a 12345.\n");
                System.out.println("\n");

                Toast.makeText(Change_passwordActivity.this, a.getClaveSeguridad(), Toast.LENGTH_SHORT).show();
                intent.putExtra("ACTION",txtpassword.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(Change_passwordActivity.this, "No ha podido cambiar la password.\n" + a.getClaveSeguridad(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Change_passwordActivity.this, "Antigua: " + last + "\t" +"Nueva: " + newpassword, Toast.LENGTH_LONG).show();
            Toast.makeText(Change_passwordActivity.this, "Error, las contrasenyas no coinciden", Toast.LENGTH_SHORT).show();
        }
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
