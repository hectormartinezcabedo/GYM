package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Cliente;
import com.hibernate.model.Entrenador;
import com.hibernate.util.HibernateUtil;

public class EntrenadorDAO{
	public void insertEntrenador(Entrenador ent) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(ent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void updateEntrenador(Entrenador ent) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(ent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void deleteEntrenador(int id) {
		Transaction transaction = null;
		Entrenador ent = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ent = session.get(Entrenador.class, id);
			session.remove(ent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public Entrenador selectEntrenadorById(int id) {
		Transaction transaction = null;
		Entrenador ent = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ent = session.get(Entrenador.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ent;
	}

	public List<Entrenador> selectAllEntrenadores() {
		Transaction transaction = null;
		List<Entrenador> entrenadores = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			entrenadores = session.createQuery("FROM Entrenador", Entrenador.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return entrenadores;
	}
	
	public int contarEntrenadores() {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    Long total = (Long) session.createQuery(
	        "SELECT COUNT(e) FROM Entrenador e"
	    ).uniqueResult();

	    session.close();
	    return total.intValue();
	}
    
}
