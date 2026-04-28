package com.hibernate.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntrenador;

    private String nombre;

    @ManyToMany
    @JoinTable(
        name = "entrenador_especialidad",
        joinColumns = @JoinColumn(name = "id_entrenador"),
        inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades = new ArrayList<>();

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

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

    
}
