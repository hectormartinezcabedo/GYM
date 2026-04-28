package com.hibernate.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "rutina")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRutina;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_dificultad")
    private Dificultad dificultad;

    @ManyToMany(mappedBy = "rutinas")
    private List<Cliente> clientes = new ArrayList<>();

    @OneToMany(mappedBy = "rutina")
    private List<RutinaEjercicio> ejercicios = new ArrayList<>();

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
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

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<RutinaEjercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<RutinaEjercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

    
}