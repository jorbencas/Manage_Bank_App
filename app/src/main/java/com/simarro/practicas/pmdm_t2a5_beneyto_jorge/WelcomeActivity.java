package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtsaludo;
    private TextView textpassword;
    private ImageButton bAdd;
    private ImageButton bDelete;
    private ImageButton bCamera;
    private ImageButton bGallery;
    private ImageButton bCheckbox;
    private ImageButton bStar;
    private String cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtsaludo = (TextView)findViewById(R.id.txtwelcome);
        txtsaludo.setText("Bienvenido " + this.getIntent().getStringExtra("NOMBRE"));

        textpassword = (TextView)findViewById(R.id.textpassword);
        textpassword.setText("Contrasenya " + this.getIntent().getStringExtra("PASSWORD"));

        cliente = this.getIntent().getStringExtra("cliente");

        bAdd = (ImageButton)findViewById(R.id.bAdd);
        bDelete = (ImageButton)findViewById(R.id.bDelete);
        bCamera = (ImageButton)findViewById(R.id.bCamera);
        bGallery = (ImageButton)findViewById(R.id.bGallery);
        bCheckbox = (ImageButton)findViewById(R.id.bCheckbox);
        bStar = (ImageButton)findViewById(R.id.bStar);

        bAdd.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bCamera.setOnClickListener(this);
        bGallery.setOnClickListener(this);
        bCheckbox.setOnClickListener(this);
        bStar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAdd:
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                loadchangepassword();
                break;
            case R.id.bDelete:
                startActivity(new Intent(WelcomeActivity.this, AddChargeActivity.class));
                break;
            case R.id.bCamera:
                Toast.makeText(this, "Pulsado boton de camara", Toast.LENGTH_SHORT).show();
                changeview("Camara");
                break;
            case R.id.bGallery:
                Toast.makeText(this, "Pulsado boton de galleria", Toast.LENGTH_SHORT).show();
                changeview("Galleria");
                break;
            case R.id.bCheckbox:
                Toast.makeText(this, "Pulsado boton de checkbox", Toast.LENGTH_SHORT).show();
                changeview("Tarea");
                break;
            case R.id.bStar:
                Toast.makeText(this, "Pulsado boton de estrella" , Toast.LENGTH_SHORT).show();
                changeview("Estrella");
                break;
        }
    }

    private void changeview(String name){
        Intent intent =  new Intent(WelcomeActivity.this, ViewActionActivity.class);
        intent.putExtra("ACTION",name);
        startActivity(intent);
    }

    private void loadchangepassword(){
        Intent intent = new Intent(WelcomeActivity.this, Change_passwordActivity.class);
        intent.putExtra("PASSWORD",this.getIntent().getStringExtra("PASSWORD"));
        intent.putExtra("cliente",this.cliente);
        startActivity(intent);
    }

}
