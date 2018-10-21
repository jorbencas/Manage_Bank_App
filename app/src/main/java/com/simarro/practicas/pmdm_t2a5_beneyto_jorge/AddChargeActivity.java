package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddChargeActivity extends AppCompatActivity implements View.OnClickListener {


    private GridView grdOpciones;
    private ArrayAdapter<String> adapatador;
    String[] datos = new String[]{"XXXX XXXX XX XXXXXXXXXX","XXXX XXXX XX XXXXXXXXXX","XXXX XXXX XX XXXXXXXXXX","XXXX XXXX XX XXXXXXXXXX"};
    private RadioButton transfersomeoneAccount;
    private RadioButton transfermyAccount;
    private EditText setAccount;
    private Spinner cmdopciones;
    private ArrayAdapter<String> adapatadordivisa;
    String[] divisa = new String[]{"€", "$"};
    private Button btncancel;
    private Button btnok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charge);

        grdOpciones = (GridView) findViewById(R.id.GridOpciones);


        grdOpciones = (GridView)findViewById(R.id.GridOpciones);
        adapatador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        grdOpciones.setAdapter(adapatador);

        transfersomeoneAccount = (RadioButton)findViewById(R.id.transfersomeoneAccount);
        transfermyAccount = (RadioButton)findViewById(R.id.transfermyAccount);
        setAccount = (EditText)findViewById(R.id.setAccount);

        cmdopciones = (Spinner)findViewById(R.id.cmdopciones);
        adapatadordivisa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, divisa);
        adapatadordivisa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmdopciones.setAdapter(adapatadordivisa);

        btnok = (Button)findViewById(R.id.btnok);
        btncancel = (Button)findViewById(R.id.btncancel);

        btnok.setOnClickListener(this);
        btncancel.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnok:
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(AddChargeActivity.this, GridActivity.class));
                break;
            case R.id.btncancel:
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.transfersomeoneAccount:
                if (checked)
                    setAccount.setVisibility(View.VISIBLE);
                break;
            case R.id.transfermyAccount:
                if (checked)
                    setAccount.setVisibility(View.INVISIBLE);
                break;
        }
    }

}
