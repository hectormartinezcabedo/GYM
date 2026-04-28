package com.hibernate.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEjercicio;

    private String nombre;
    private String descripcion;
    private String video;

    @ManyToOne
    @JoinColumn(name = "id_grupo_muscular")
    private GrupoMuscular grupoMuscular;

    @OneToMany(mappedBy = "ejercicio")
    private List<RutinaEjercicio> rutinas = new ArrayList<>();

    @OneToMany(mappedBy = "ejercicio")
    private List<Progreso> progresos = new ArrayList<>();

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
		return grupoMuscular;
	}

	public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}

	public List<RutinaEjercicio> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<RutinaEjercicio> rutinas) {
		this.rutinas = rutinas;
	}

	public List<Progreso> getProgresos() {
		return progresos;
	}

	public void setProgresos(List<Progreso> progresos) {
		this.progresos = progresos;
	}

    
}
