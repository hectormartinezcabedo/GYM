package com.hibernate.model;


import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idEntrenador")
    private int idEntrenador;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="especialidad")
    private String especialidad;
    
    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "rol")
    private String rol;
    
    public Entrenador() {
    	
    }

    public Entrenador(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

	public int getIdEntrenador() {
		return idEntrenador;
	}

	public void setIdEntrenador(int idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
	    return nombre;
	}

    
}
