package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        if (txtnombre.length() < 8 && txtpassword.length() < 8 || txtnombre.length() == 0 ){
            Toast.makeText(MainActivity.this, "Error de autenticación"/*"@string/authentication"*/, Toast.LENGTH_SHORT).show();
        }else{
            intent.putExtra("NOMBRE",txtnombre.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, SlashActivity.class);
        startActivity(intent);
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
