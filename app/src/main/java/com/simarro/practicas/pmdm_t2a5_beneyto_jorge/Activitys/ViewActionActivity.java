package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.R;

public class ViewActionActivity extends AppCompatActivity {

    private TextView txtaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_action);
        txtaction = (TextView)findViewById(R.id.txtaction);
        txtaction.setText("Acción: " + this.getIntent().getStringExtra("ACTION"));
    }
}
