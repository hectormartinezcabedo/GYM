package com.hibernate.dao;

import com.hibernate.model.Ejercicio;

public class EjercicioDAO extends GenericDAO<Ejercicio> {

    public EjercicioDAO() {
        super(Ejercicio.class);
    }
}