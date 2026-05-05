package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo_muscular")
public class GrupoMuscular {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="id_grupo_muscular")
	    private int id_grupo_muscular;

	    @Column(name="nombre")
	    private String nombre;
	    
	    @Column(name="descripcion")
	    private String descripcion;
	    
	    public GrupoMuscular() {
	    	
	    }
	    
	    public GrupoMuscular(String nombre, String descripcion) {
	    	this.nombre = nombre;
	    	this.descripcion = descripcion;
	    }

		public int getId_grupo_muscular() {
			return id_grupo_muscular;
		}

		public void setId_grupo_muscular(int id_grupo_muscular) {
			this.id_grupo_muscular = id_grupo_muscular;
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
	    
	    

}
