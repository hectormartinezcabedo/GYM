package com.hibernate.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "grupo_muscular")
public class GrupoMuscular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGrupoMuscular;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "grupoMuscular")
    private List<Ejercicio> ejercicios = new ArrayList<>();

	public int getIdGrupoMuscular() {
		return idGrupoMuscular;
	}

	public void setIdGrupoMuscular(int idGrupoMuscular) {
		this.idGrupoMuscular = idGrupoMuscular;
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

	public List<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

    
}
