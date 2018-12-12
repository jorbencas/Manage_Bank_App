package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

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

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Charge;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBD;

public class AddChargeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private GridView grdOpciones;
    private ArrayAdapter<String> adapatador, adapatadordivisa;
    String[] datos = new String[]{"AL86751639367318444714198669","AL89515635252277023782748302","AL39153296222641598198140883","AL47907501989147671525950076"};
    private RadioButton transfersomeoneAccount, transfermyAccount;
    private EditText setAccount;
    private Spinner cmdopciones;
    String[] divisa = new String[]{"€", "$","£","\u20BF"};
    private CheckBox justifyed;
    private Button btncancel, btnok;
    private View itemClick = null;
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
                    this.setAccount.setVisibility(View.VISIBLE);
                break;
            case R.id.transfermyAccount:
                if (checked)
                    this.setAccount.setVisibility(View.INVISIBLE);
                    this.charge.setAccount("12345678920147852369");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if(this.itemClick!=null){
           this.itemClick.setBackgroundColor(getResources().getColor(R.color.text_button_color));
       }
       this.itemClick = view;
       this.itemClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
       this.charge.setNumber_account(String.valueOf(parent.getItemAtPosition(position)));
    }

    public void sendjustified(View view){
        if (this.justifyed.isChecked()){
            this.charge.setJusfityer(Boolean.TRUE);
        }else{
            this.charge.setJusfityer(Boolean.FALSE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        char divise = (char) String.valueOf(parent.getItemAtPosition(position)).charAt(0);
        this.charge.setDivise(divise);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { charge.setDivise(' '); }

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
