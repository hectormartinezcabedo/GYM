package com.hibernate.model;


import javax.persistence.*;

@Entity
@Table(name = "dificultad")
public class Dificultad {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_dificultad")
	    private int id;
	    
	    @Column(name = "nombre")
	    private String nombre;
	
}
