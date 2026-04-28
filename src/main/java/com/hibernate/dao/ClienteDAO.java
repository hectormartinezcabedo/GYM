package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import com.hibernate.model.Cliente;
import com.hibernate.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO() {
        super(Cliente.class);
    }
    
    public List<Cliente> buscarPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Cliente WHERE nombre LIKE :nombre", Cliente.class)
                .setParameter("nombre", "%" + nombre + "%")
                .list();
        }
    }
}
