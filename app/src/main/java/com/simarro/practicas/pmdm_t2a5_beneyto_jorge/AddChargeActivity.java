package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddChargeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private GridView grdOpciones;
    private ArrayAdapter<String> adapatador, adapatadordivisa;
    String[] datos = new String[]{"XXXX XXXX XX XXXXXXXXXX","XXXX XXXX XX XXXXXXXXXX","XXXX XXXX XX XXXXXXXXXX","XXXX XXXX XX XXXXXXXXXX"};
    private RadioButton transfersomeoneAccount, transfermyAccount;
    private EditText setAccount;
    private Spinner cmdopciones;
    String[] divisa = new String[]{"€", "$"};
    private CheckBox justifyed;
    private Button btncancel, btnok;
    Charge charge = new Charge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charge);

        grdOpciones = (GridView)findViewById(R.id.GridOpciones);
        adapatador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        grdOpciones.setAdapter(adapatador);
        grdOpciones.setOnItemClickListener(this);

        transfersomeoneAccount = (RadioButton)findViewById(R.id.transfersomeoneAccount);
        transfermyAccount = (RadioButton)findViewById(R.id.transfermyAccount);
        setAccount = (EditText)findViewById(R.id.setAccount);

        cmdopciones = (Spinner)findViewById(R.id.cmdopciones);
        adapatadordivisa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, divisa);
        adapatadordivisa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmdopciones.setAdapter(adapatadordivisa);
        cmdopciones.setOnItemSelectedListener(this);

        justifyed = (CheckBox)findViewById(R.id.justifyed);

        btnok = (Button)findViewById(R.id.btnok);
        btncancel = (Button)findViewById(R.id.btncancel);

        btnok.setOnClickListener(this);
        btncancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnok:
                charge.setAccount(setAccount.getText().toString());
                Toast.makeText(this, charge.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btncancel:
                reloadactivity();
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void reloadactivity(){
        new Charge();
        adapatador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        grdOpciones.setAdapter(adapatador);
        transfermyAccount.setChecked(Boolean.FALSE);
        transfersomeoneAccount.setChecked(Boolean.FALSE);
        setAccount.setVisibility(View.INVISIBLE);
        cmdopciones.setSelection(0);
        justifyed.setChecked(Boolean.FALSE);
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
                    charge.setAccount("12345678920147852369");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        charge.setNumber_account(String.valueOf(parent.getItemAtPosition(position)));
    }

    public void sendjustified(View view){
        if (justifyed.isChecked()){
            charge.setJusfityer(Boolean.TRUE);
        }else{
            charge.setJusfityer(Boolean.FALSE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        char divise = (char) String.valueOf(parent.getItemAtPosition(position)).charAt(0);
        charge.setDivise(divise);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { charge.setDivise(' '); }
}
