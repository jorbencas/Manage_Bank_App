package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBD;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBancoOperacional;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao.ClienteDAO;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao.CuentaDAO;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Charge;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class AddChargeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    ArrayList<Cuenta>listaCuentasObj=new ArrayList<>();
    private GridView grdOpciones;
    private ArrayAdapter<String> adapatador, adapatadordivisa, adaptadorSpinner;
    ArrayList<String> datos = new ArrayList<String>();

    private RadioButton transfersomeoneAccount, transfermyAccount;
    private EditText setAccount;
    private Spinner cmdopciones,spinnerDentro;
    String[] divisa = new String[]{"€", "$","£","\u20BF"};
    private CheckBox justifyed;
    private Button btncancel, btnok;
    private View itemClick = null;
    //Charge charge = new Charge();
    RadioGroup grupoRadio=null;

    EditText importe=null;

    Cuenta objCuenta=null;
    Cuenta objCuentaRadio=null;
    String strCuentaDestino=null;
    CuentaDAO cuentaDAO=new CuentaDAO();
    int tipoMov=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charge);

        Intent intento=getIntent();
        int idCliente=Integer.parseInt(intento.getStringExtra("id"));


        Cliente cli=new Cliente();
        cli.setId(idCliente);
        this.datos=this.cuentaDAO.getCuentasStr(cli);
        this.listaCuentasObj=this.cuentaDAO.getCuentas(cli);

        grdOpciones = (GridView)findViewById(R.id.GridOpciones);
        adapatador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        grdOpciones.setAdapter(adapatador);
        grdOpciones.setOnItemClickListener(this);

        grupoRadio=findViewById(R.id.radioGroup);
        transfersomeoneAccount = (RadioButton)findViewById(R.id.transfersomeoneAccount);
        transfermyAccount = (RadioButton)findViewById(R.id.transfermyAccount);
        setAccount = (EditText)findViewById(R.id.setAccount);

        spinnerDentro= (Spinner)findViewById(R.id.spinnerDentro);
        adaptadorSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spinnerDentro.setAdapter(adaptadorSpinner);

        cmdopciones = (Spinner)findViewById(R.id.cmdopciones);
        adapatadordivisa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, divisa);
        adapatadordivisa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmdopciones.setAdapter(adapatadordivisa);
        cmdopciones.setOnItemSelectedListener(this);

        importe =findViewById(R.id.importe);
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
               //if(this.comprobarFormulario()) {
                    Movimiento mov=null;
                    Random ran = new Random();
                    String desc="TRANSFERENCIA A " + this.returnSelectedItem();
                    this.strCuentaDestino=this.setAccount.getText().toString();
                    int x = 1 + ran.nextInt((1000 - 1) + 1);

                    if(this.grupoRadio.getCheckedRadioButtonId()==R.id.transfermyAccount){
                        mov=new Movimiento(x,this.tipoMov, Calendar.getInstance().getTime(),desc,Float.parseFloat("-"+this.importe.getText().toString()),this.objCuenta,this.objCuentaRadio);
                        System.out.println("YE" + mov);
                    }else{
                        String banco=this.setAccount.getText().toString().substring(0,4);
                        String sucursal=this.setAccount.getText().toString().substring(4,8);
                        String dc=this.setAccount.getText().toString().substring(8,10);
                        String numCuenta=this.setAccount.getText().toString().substring(10);
                        System.out.println(banco + "/"+sucursal+"/"+dc+"/"+numCuenta);

                        if(MiBD.getInstance(this).existeCuenta(banco,sucursal,dc,numCuenta)){
                            Cuenta c = new Cuenta();
                            c.setBanco(banco);
                            c.setSucursal(sucursal);
                            c.setDc(dc);
                            c.setNumeroCuenta(numCuenta);
                            Cuenta cFull= (Cuenta) this.cuentaDAO.search(c);
                            mov=new Movimiento(x,this.tipoMov, Calendar.getInstance().getTime(),desc,Float.parseFloat("-"+this.importe.getText().toString()),this.objCuenta,cFull);
                            System.out.println(mov);
                        }else{
                            System.out.println("No existe la cuenta!");
                        }

                    }
                    int resInt=this.transferencia(mov);
                    if(resInt==0){
                        Toast.makeText(this,"Realizada con exito la transferencia",Toast.LENGTH_SHORT).show();
                    }else if(resInt==2){
                        Toast.makeText(this,"No tiene suficiente saldo!",Toast.LENGTH_SHORT).show();
                    }else if(resInt==1){
                        Toast.makeText(this,"No se puede enviar a la misma cuenta!!!",Toast.LENGTH_SHORT).show();
                    }
                //}
                break;
            case R.id.btncancel:
                reloadactivity();
                Toast.makeText(this, "Pulsado boton de añadir", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String returnSelectedItem(){
        if(this.transfersomeoneAccount.isSelected()){
            return this.setAccount.getText().toString();
        }else{
            return this.spinnerDentro.getSelectedItem().toString();
        }

    }

    public void reloadactivity(){
        adapatador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        adaptadorSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        grdOpciones.setAdapter(adapatador);
        transfermyAccount.setChecked(Boolean.FALSE);
        transfersomeoneAccount.setChecked(Boolean.FALSE);
        setAccount.setVisibility(View.INVISIBLE);
        spinnerDentro.setSelection(0);
        cmdopciones.setSelection(0);
        justifyed.setChecked(Boolean.FALSE);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.transfersomeoneAccount:

                this.spinnerDentro.setVisibility(View.INVISIBLE);
                    this.setAccount.setVisibility(View.VISIBLE);
                break;
            case R.id.transfermyAccount:

                this.setAccount.setVisibility(View.INVISIBLE);
                this.spinnerDentro.setVisibility(View.VISIBLE);

                this.objCuentaRadio=this.listaCuentasObj.get(this.spinnerDentro.getSelectedItemPosition());

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if(this.itemClick!=null){
           this.itemClick.setBackgroundColor(getResources().getColor(R.color.text_button_color));
       }
       this.itemClick = view;
        this.objCuenta=this.listaCuentasObj.get(position);

       this.itemClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));



       //this.charge.setNumber_account(String.valueOf(parent.getItemAtPosition(position)));
    }

    public String sendjustified(View view){
        if (this.justifyed.isChecked()){
            return  "Se desea enviar justificante";
        }else{
            return "No se desea enviar justificante";
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        char divise = (char) String.valueOf(parent.getItemAtPosition(position)).charAt(0);
        //this.charge.setDivise(divise);
        this.objCuentaRadio=this.listaCuentasObj.get(position);
        System.out.println("SELECCIONADA CUENTA RADIO "+this.objCuentaRadio);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {  }

    public int transferencia(Movimiento movimientoTransferencia){
        float res=movimientoTransferencia.getCuentaOrigen().getSaldoActual() - movimientoTransferencia.getImporte();
        System.out.println("COMPARANDO " + movimientoTransferencia.getCuentaDestino().getNumeroCuenta() + " CON "+movimientoTransferencia.getCuentaOrigen().getNumeroCuenta() );
        if(movimientoTransferencia.getCuentaDestino().getNumeroCuenta().equalsIgnoreCase(movimientoTransferencia.getCuentaOrigen().getNumeroCuenta())){
            return 1;
        }
        if(res>0){
            if(movimientoTransferencia.getCuentaDestino().getBanco().equalsIgnoreCase(movimientoTransferencia.getCuentaOrigen().getBanco())) {
                movimientoTransferencia.getCuentaOrigen().setSaldoActual(movimientoTransferencia.getCuentaOrigen().getSaldoActual() - movimientoTransferencia.getImporte());
                movimientoTransferencia.getCuentaDestino().setSaldoActual(movimientoTransferencia.getCuentaDestino().getSaldoActual() + movimientoTransferencia.getImporte());
                MiBD.getInstance(this).actualizarSaldo(movimientoTransferencia.getCuentaOrigen());
                MiBD.getInstance(this).actualizarSaldo(movimientoTransferencia.getCuentaDestino());
                MiBD.getInstance(this).insercionMovimiento(movimientoTransferencia);
                return 0;
            }else{
                Toast.makeText(this,"Deben ser del mismo banco las cuentas...",Toast.LENGTH_SHORT).show();
            }
        }else{
            return 2;
        }

        return 0;
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
