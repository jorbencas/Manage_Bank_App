package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView txtsaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtsaludo = (TextView)findViewById(R.id.txtwelcome);

        String mensagerecibido = this.getIntent().getStringExtra("NOMBRE");

        txtsaludo.setText("Bienvenido " + mensagerecibido);

    }
}
