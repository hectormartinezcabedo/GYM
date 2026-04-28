package com.hibernate.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecialidad;

    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "especialidades")
    private List<Entrenador> entrenadores = new ArrayList<>();

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Entrenador> getEntrenadores() {
		return entrenadores;
	}

	public void setEntrenadores(List<Entrenador> entrenadores) {
		this.entrenadores = entrenadores;
	}

    
}
