package com.hibernate.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "dificultad")
public class Dificultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDificultad;

    private String nombre;

    @OneToMany(mappedBy = "dificultad")
    private List<Rutina> rutinas = new ArrayList<>();

	public int getIdDificultad() {
		return idDificultad;
	}

	public void setIdDificultad(int idDificultad) {
		this.idDificultad = idDificultad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

    
}
