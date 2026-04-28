package com.hibernate.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idCliente")
    private int idCliente;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="email")
    private String email;
    
    @Column(name="telefono")
    private int telefono;
    
    @Column(name="fecha_alta")
    private LocalDate fecha_alta;
    
    public Cliente(String nombre, String email, int telefono, LocalDate fecha_alta) {
    	this.nombre = nombre;
    	this.email = email;
    	this.telefono = telefono;
    	this.fecha_alta = fecha_alta;
    }

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
    
    
}
