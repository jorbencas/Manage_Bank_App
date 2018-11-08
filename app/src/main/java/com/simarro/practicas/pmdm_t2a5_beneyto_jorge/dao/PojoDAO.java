package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.dao;

import java.util.ArrayList;


public interface PojoDAO {

    public long add(Object obj);
    public int update(Object obj);
    public void delete(Object obj);
    public Object search(Object obj);
    public ArrayList getAll();
}
