package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.Constantes;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao.CajeroDAO;

public class CajeroCursorAdapter  extends CursorAdapter {


    private CajeroDAO cajeroDAO = null ;



    public CajeroCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor,0);
        cajeroDAO = new CajeroDAO(context);
        cajeroDAO.abrir();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Obtenemos el inflador
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflamos la vista que vamos a devolver
        View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Como hemos inflado simple_dropdown_item_1line solo tenemos un TextView que lo obtenemos
        TextView tv = (TextView) view ;
        // Obtenemos el indice de la columna
        int i = cursor.getColumnIndex(Constantes.FIELD_DIRECCION);
        // Asignamos el valor
        tv.setText(cursor.getString(i));
    }





}
