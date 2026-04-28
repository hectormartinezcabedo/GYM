package com.hibernate.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "progreso")
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProgreso;

    private double pesoUtilizado;
    private int repeticiones;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio ejercicio;

	public int getIdProgreso() {
		return idProgreso;
	}

	public void setIdProgreso(int idProgreso) {
		this.idProgreso = idProgreso;
	}

	public double getPesoUtilizado() {
		return pesoUtilizado;
	}

	public void setPesoUtilizado(double pesoUtilizado) {
		this.pesoUtilizado = pesoUtilizado;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

    
}