package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.bd.MiBD;
import com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo.Cliente;

import java.util.ArrayList;


public class ClienteDAO implements PojoDAO{

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Cliente c = (Cliente) obj;
        contentValues.put("nif" , c.getNif());
        contentValues.put("nombre", c.getNombre());
        contentValues.put("apellidos", c.getApellidos());
        contentValues.put("claveSeguridad", c.getClaveSeguridad());
        contentValues.put("email", c.getEmail());
        return MiBD.getDB().insert("clientes", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        Cliente c = (Cliente) obj;
        contentValues.put("nif" , c.getNif());
        contentValues.put("nombre", c.getNombre());
        contentValues.put("apellidos", c.getApellidos());
        contentValues.put("claveSeguridad", c.getClaveSeguridad());
        contentValues.put("email", c.getEmail());

        String condicion = "id=" + String.valueOf(c.getId());

        int resultado = MiBD.getDB().update("clientes", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        Cliente c = (Cliente) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el cliente indicado en el campo de texto
        MiBD.getDB().delete("clientes", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Cliente c = (Cliente) obj;
        String condicion = "";
        if(TextUtils.isEmpty(c.getNif())){
            condicion = "id=" + String.valueOf(c.getId());
        }else{
            condicion = "nif=" + "'" + c.getNif() + "'";
        }

        String[] columnas = {
                "id","nif","nombre","apellidos","claveseguridad","email"
        };
        Cursor cursor = MiBD.getDB().query("clientes", columnas, condicion, null, null, null, null);
        Cliente nuevoCliente = null;
        if (cursor.moveToFirst()) {
            nuevoCliente = new Cliente();
            nuevoCliente.setId(cursor.getInt(0));
            nuevoCliente.setNif(cursor.getString(1));
            nuevoCliente.setNombre(cursor.getString(2));
            nuevoCliente.setApellidos(cursor.getString(3));
            nuevoCliente.setClaveSeguridad(cursor.getString(4));
            nuevoCliente.setEmail(cursor.getString(5));

            // Obtenemos la lista de cuentas que tiene el cliente
            //c.setListaCuentas(MiBD.getInstance(null).getCuentaDAO().getCuentas(c));


        }
        return nuevoCliente;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        String[] columnas = {
                "id","nif","nombre","apellidos","claveseguridad","email"
        };
        Cursor cursor = MiBD.getDB().query("clientes", columnas, null, null, null, null, null);
        CuentaDAO cuentaDAO = new CuentaDAO();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Cliente c = new Cliente();
                c.setId(cursor.getInt(0));
                c.setNif(cursor.getString(1));
                c.setNombre(cursor.getString(2));
                c.setApellidos(cursor.getString(3));
                c.setClaveSeguridad(cursor.getString(4));
                c.setEmail(cursor.getString(5));
                //c.setListaCuentas(MiBD.getInstance(null).getCuentaDAO().getCuentas(c));
                listaClientes.add(c);

            } while(cursor.moveToNext());
        }
        return listaClientes;
    }

}
