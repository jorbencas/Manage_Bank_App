package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.CajerosSQLLiteHelper;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.Constantes;

public class CajeroDAO {
    private Context contexto;
    private CajerosSQLLiteHelper dbHelper;
    private SQLiteDatabase db;

    public CajeroDAO(Context context) {
        this.contexto = context;
    }
    public CajeroDAO abrir(){
        dbHelper = new CajerosSQLLiteHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void cerrar() {
        dbHelper.close();
    }
    /**
     * Devuelve un cursor con todas las filas y todas las columnas de la tabla
     */
    public Cursor getCursor() {
        Cursor c = db.query( true, Constantes.CAJEROS_TABLE, Constantes.CAMPOS_CAJEROS, null, null, null, null, null, null);
        return c;
    }

    public long add(ContentValues contentValues){
        if (db == null)
            abrir();
        return db.insert(Constantes.CAJEROS_TABLE, null, contentValues);
    }
    public long update(ContentValues reg) {
        long result = 0;
        if (db == null)
            abrir();
        if (reg.containsKey(Constantes.FIELD_CAJEROS_ID)) {
//
// Obtenemos el id y lo borramos de los valores a actualizar, ya que el id no se actualiza
//
            //reg.getAs
            long id = reg.getAsLong(Constantes.FIELD_CAJEROS_ID);
            reg.remove(Constantes.FIELD_CAJEROS_ID);
//
// Actualizamos el registro con el identificador que hemos extraido
//
            result = db.update(Constantes.CAJEROS_TABLE, reg, "_id=" + id, null);
        }
        return result;
    }
    public long delete(long _id){
        if (db == null)
            abrir();
        return db.delete(Constantes.CAJEROS_TABLE, "_id=" + _id, null);
    }
    public Cursor getAll(){
        return null;
    }
}
