package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Change_passwordActivity extends AppCompatActivity {

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

    }

    public void Changepassword(View v){
        Intent intent =  new Intent(Change_passwordActivity.this, MainActivity.class);
        if (txtoldpassword.getText().toString().equals(txtlastpassword.getText().toString())){
            Toast.makeText(Change_passwordActivity.this, txtpassword.getText().toString(), Toast.LENGTH_SHORT).show();
            intent.putExtra("ACTION",txtpassword.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(Change_passwordActivity.this, "Error, las contrasenyas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

}
