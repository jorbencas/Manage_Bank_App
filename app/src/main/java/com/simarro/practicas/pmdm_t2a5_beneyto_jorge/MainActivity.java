package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;

public class MainActivity extends AppCompatActivity  {

    private EditText txtnombre;
    private EditText txtpassword;
    private Button Btnaceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtnombre = (EditText)findViewById(R.id.txtnombre);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        Btnaceptar = (Button)findViewById(R.id.Blnaceptar);

    }

    public void bacerclic(View v){
        Intent intent =  new Intent(MainActivity.this, WelcomeActivity.class);
        MiBancoOperacional mbo = MiBancoOperacional.getInstance(this);

        /*if ( txtnombre.length() == 0 || (txtnombre.length() < 8 && txtpassword.length() < 8) ){
             AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage(R.string.authentication)
                        .setTitle("Error de credenciales");

                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

        }else{*/

            // Introducimos los datos como si fuera la pantalla inicial
            Log.i(this.getComponentName().getClassName(), "Creando el cliente a");
            Cliente a = new Cliente();
            a.setNif(txtnombre.getText().toString());
            a.setClaveSeguridad(txtpassword.getText().toString());

            // logueamos al cliente
            a = mbo.login(a);

        if(a==null){

            System.out.println("No ha podido loguearse con 1234 como password.\n");
            System.out.println("\n");
        }else{
            System.out.println("Datos del cliente logueado\n");
            System.out.println("-----------------------------------------\n");
            Log.e(a.toString()+"\n" + a.getClaveSeguridad(), "Cliente");
            System.out.println("\n");

            intent.putExtra("NOMBRE",txtnombre.getText().toString());
            intent.putExtra("PASSWORD",txtpassword.getText().toString());
            System.out.println(String.valueOf(a.getId()));
            intent.putExtra("cliente",String.valueOf(a.getId()));
            startActivity(intent);
        }


        //}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent intent = new Intent(MainActivity.this, SlashActivity.class);
        startActivity(intent);*/
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
