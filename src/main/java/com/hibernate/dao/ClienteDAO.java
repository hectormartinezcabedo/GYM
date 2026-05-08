package com.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Cliente;
import com.hibernate.util.HibernateUtil;

public class ClienteDAO {

	public void insertCliente(Cliente c) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(c);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void updateCliente(Cliente c) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(c);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void deleteCliente(int id) {
		Transaction transaction = null;
		Cliente c = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			c = session.get(Cliente.class, id);
			session.remove(c);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public Cliente selectClienteById(int id) {
		Transaction transaction = null;
		Cliente c = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			c = session.get(Cliente.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return c;
	}

	public Cliente selectClienteByNombre(String nombre) {

		Transaction tx = null;
		Cliente cliente = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			tx = session.beginTransaction();

			Query<Cliente> query = session.createQuery("FROM Cliente WHERE nombre = :nombre", Cliente.class);

			query.setParameter("nombre", nombre);

			cliente = query.uniqueResult();

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {

				try {

					tx.rollback();

				} catch (Exception ex) {

					ex.printStackTrace();
				}
			}

			e.printStackTrace();
		}

		return cliente;
	}

	public List<Cliente> selectAllClientes() {
		Transaction transaction = null;
		List<Cliente> clientes = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			clientes = session.createQuery("FROM Cliente", Cliente.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return clientes;
	}

	public int contarClientes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long total = (Long) session.createQuery("SELECT COUNT(c) FROM Cliente c").uniqueResult();
		session.close();
		return total.intValue();
	}

	public int contarClientesRecientes() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Query query = session.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.fecha_alta >= :fecha");

		LocalDate fecha = LocalDate.now().minusDays(30);

		query.setParameter("fecha", fecha);

		Long total = (Long) query.uniqueResult();

		session.close();
		return total.intValue();
	}
}
