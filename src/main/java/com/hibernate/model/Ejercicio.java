package com.hibernate.model;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idEjercicio")
    private int idEjercicio;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="video")
    private String video;

    @ManyToOne
    @JoinColumn(name = "id_grupo_muscular")
    private GrupoMuscular id_grupo_muscular;
    
    public Ejercicio() {
        
    }
    
    public Ejercicio(String nombre, String descripcion, String video, GrupoMuscular id_grupo_muscular) {
    	this.nombre = nombre;
    	this.descripcion = descripcion;
    	this.video = video;
    	this.id_grupo_muscular = id_grupo_muscular;
    }


	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public GrupoMuscular getGrupoMuscular() {
		return id_grupo_muscular;
	}

	public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
		this.id_grupo_muscular = grupoMuscular;
	}

	

    
}
