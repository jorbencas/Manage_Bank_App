package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;

import java.util.Locale;

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
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        this.change_lang();
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

    private void change_lang() {
        String lang = prefs.getString("pais", "error");
        if (!lang.equalsIgnoreCase("error")) {
            switch (lang){
                case "es":
                    lang = "es";
                    break;
                case"va":
                    lang = "ca";
                    break;
                default:
                    lang = "en";
                    break;
            };
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);

            Configuration config = new Configuration();
            config.locale = locale;
            getApplicationContext().getResources().updateConfiguration(config, null);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem itemDrawer) {
        switch (itemDrawer.getItemId()){
            case R.id.bAdd:
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                loadchangepassword();
                return true;
            case R.id.bDelete:
                startActivity(new Intent(WelcomeActivity.this, AddChargeActivity.class).putExtra("id",this.cliente));
                return true;
            case R.id.bSettings:
                startActivity(new Intent(WelcomeActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(itemDrawer);
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


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAdd:
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                loadchangepassword();
                break;
            case R.id.bDelete:
                startActivity(new Intent(WelcomeActivity.this, AddChargeActivity.class).putExtra("id",this.cliente));
                break;
            case R.id.bCamera:
                startActivity(new Intent(WelcomeActivity.this, AccountListActivity.class).putExtra("cliente",this.cliente));
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
}
