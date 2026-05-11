package com.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import com.hibernate.model.Ejercicio;
import com.hibernate.util.HibernateUtil;

public class EjercicioDAO {

    public List<Ejercicio> selectByGrupoMuscular(int idGrupo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ejercicio> lista = session.createQuery(
            "FROM Ejercicio e WHERE e.id_grupo_muscular.id_grupo_muscular = :id",
            Ejercicio.class)
            .setParameter("id", idGrupo)
            .getResultList();
        session.close();
        return lista;
    }

    public List<Ejercicio> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ejercicio> lista = session.createQuery(
            "FROM Ejercicio", Ejercicio.class)
            .getResultList();
        session.close();
        return lista;
    }
}