package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBD;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cuenta;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Movimiento;

import java.util.ArrayList;
import java.util.Date;


public class MovimientoDAO implements PojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Movimiento c = (Movimiento) obj;
        contentValues.put("tipo", c.getTipo());
        contentValues.put("fechaoperacion", c.getFechaOperacion().getTime());
        contentValues.put("descripcion", c.getDescripcion());
        contentValues.put("importe", c.getImporte());
        contentValues.put("idcuentaorigen", c.getCuentaOrigen().getId());
        contentValues.put("idcuentadestino", c.getCuentaDestino().getId());

        return MiBD.getDB().insert("movimientos", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Movimiento c = (Movimiento) obj;
        contentValues.put("tipo", c.getTipo());
        contentValues.put("fechaoperacion", c.getFechaOperacion().getTime());
        contentValues.put("descripcion", c.getDescripcion());
        contentValues.put("importe", c.getImporte());
        contentValues.put("idcuentaorigen", c.getCuentaOrigen().getId());
        contentValues.put("idcuentadestino", c.getCuentaDestino().getId());

        String condicion = "id=" + String.valueOf(c.getId());

        return MiBD.getDB().update("movimientos", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Movimiento c = (Movimiento) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("movimientos", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Movimiento c = (Movimiento) obj;
        String condicion = "id=" + String.valueOf(c.getId());
        String[] columnas = {
                "id","tipo","fechaoperacion","descripcion","importe","idcuentaorigen", "idcuentadestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setTipo(cursor.getInt(1));
            c.setFechaOperacion(new Date(cursor.getLong(2)));
            c.setDescripcion(cursor.getString(3));
            c.setImporte(cursor.getFloat(4));

            // Asignamos la cuenta de origen
            Cuenta a = new Cuenta();
            a.setId(cursor.getInt(5));
            c.setCuentaOrigen((Cuenta) MiBD.getInstance(null).getCuentaDAO().search(a));

            // Asignamos la cuenta de destino
            int aux = cursor.getInt(6);
            if (aux == -1) {
                a.setId(-1);
            }else {
                a.setId(aux);
                c.setCuentaOrigen((Cuenta) MiBD.getInstance(null).getCuentaDAO().search(a));
            }

            return c;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String[] columnas = {
                "id","tipo","fechaoperacion","descripcion","importe","idcuentaorigen", "idcuentadestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movimiento c = new Movimiento();
                c.setId(cursor.getInt(0));
                c.setTipo(cursor.getInt(1));
                c.setFechaOperacion(new Date(cursor.getLong(2)));
                c.setDescripcion(cursor.getString(3));
                c.setImporte(cursor.getFloat(4));

                // Asignamos la cuenta de origen
                Cuenta a = new Cuenta();
                a.setId(cursor.getInt(5));
                c.setCuentaOrigen((Cuenta) MiBD.getInstance(null).getCuentaDAO().search(a));


                // Asignamos la cuenta de destino
                a = new Cuenta();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setCuentaOrigen(a);
                }else {
                    a.setId(aux);
                    c.setCuentaDestino((Cuenta) MiBD.getInstance(null).getCuentaDAO().search(a));
                }


                listaMovimientos.add(c);

            } while(cursor.moveToNext());
        }
        return listaMovimientos;
    }

    public ArrayList getMovimientos(Cuenta cuenta) {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String condicion = "idcuentaorigen=" + String.valueOf(cuenta.getId());
        String[] columnas = {
                "id","tipo","fechaoperacion","descripcion","importe","idcuentaorigen", "idcuentadestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movimiento c = new Movimiento();
                c.setId(cursor.getInt(0));
                c.setTipo(cursor.getInt(1));
                c.setFechaOperacion(new Date(cursor.getLong(2)));
                c.setDescripcion(cursor.getString(3));
                c.setImporte(cursor.getFloat(4));

                // Asignamos la cuenta de origen
                c.setCuentaOrigen(cuenta);


                // Asignamos la cuenta de destino
                Cuenta a = new Cuenta();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setCuentaDestino(a);
                }else {
                    a.setId(aux);
                    a = (Cuenta) MiBD.getInstance(null).getCuentaDAO().search(a);
                    c.setCuentaDestino(a);
                }

                listaMovimientos.add(c);

            } while(cursor.moveToNext());
        }
        return listaMovimientos;
    }

    public ArrayList getMovimientosTipo(Cuenta cuenta, int tipo) {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String condicion = "idcuentaorigen=" + String.valueOf(cuenta.getId()) + " AND tipo = " + String.valueOf(tipo);
        String[] columnas = {
                "id","tipo","fechaoperacion","descripcion","importe","idcuentaorigen", "idcuentadestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movimiento c = new Movimiento();
                c.setId(cursor.getInt(0));
                c.setTipo(cursor.getInt(1));
                c.setFechaOperacion(new Date(cursor.getLong(2)));
                c.setDescripcion(cursor.getString(3));
                c.setImporte(cursor.getFloat(4));

                // Asignamos la cuenta de origen
                c.setCuentaOrigen(cuenta);


                // Asignamos la cuenta de destino
                Cuenta a = new Cuenta();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setCuentaDestino(a);
                }else {
                    a.setId(aux);
                    a = (Cuenta) MiBD.getInstance(null).getCuentaDAO().search(a);
                    c.setCuentaDestino(a);
                }

                listaMovimientos.add(c);

            } while(cursor.moveToNext());
        }
        return listaMovimientos;
    }
}
