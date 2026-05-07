package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Rutina;
import com.hibernate.util.HibernateUtil;

public class RutinaDAO {

    public List<Rutina> obtenerRutinas() {

        Transaction transaction = null;
        List<Rutina> rutinas = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            rutinas = session.createQuery(
                "from Rutina",
                Rutina.class
            ).list();

            transaction.commit();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return rutinas;
    }
}