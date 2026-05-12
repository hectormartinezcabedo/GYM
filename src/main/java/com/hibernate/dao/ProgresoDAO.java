package com.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.model.Progreso;
import com.hibernate.util.HibernateUtil;

public class ProgresoDAO {

    public void insertProgreso(Progreso p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();
    }

    public List<Progreso> selectProgresoByCliente(int idCliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Progreso> lista = session.createQuery(
            "FROM Progreso p WHERE p.cliente.idCliente = :id ORDER BY p.fecha DESC",
            Progreso.class)
            .setParameter("id", idCliente)
            .getResultList();
        session.close();
        return lista;
    }
    
    public List<Progreso> selectProgresoByClienteYEjercicio(int idCliente, int idEjercicio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Progreso> lista = session.createQuery(
            "FROM Progreso p WHERE p.cliente.idCliente = :idCliente " +
            "AND p.ejercicio.idEjercicio = :idEjercicio ORDER BY p.fecha ASC",
            Progreso.class)
            .setParameter("idCliente", idCliente)
            .setParameter("idEjercicio", idEjercicio)
            .getResultList();
        session.close();
        return lista;
    }
}