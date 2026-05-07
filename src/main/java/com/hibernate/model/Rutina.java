package com.hibernate.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hibernate.model.Cliente;


@Entity
@Table(name = "rutina")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rutina")
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    //  RELACIÓN CON DIFICULTAD 
    @ManyToOne
    @JoinColumn(name = "id_dificultad")
    private Dificultad dificultad;

    //  RELACIÓN MUCHOS A MUCHOS CON CLIENTE
    @ManyToMany(mappedBy = "rutinas")
    private List<Cliente> clientes;

    //  RELACIÓN MUCHOS A MUCHOS CON EJERCICIOS
    @ManyToMany
    @JoinTable(
        name = "rutina_ejercicio",
        joinColumns = @JoinColumn(name = "id_rutina"),
        inverseJoinColumns = @JoinColumn(name = "id_ejercicio")
    )
    private List<Ejercicio> ejercicios;
 
    public Rutina() {
    	
    }

    public Rutina(String nombre, String descripcion, Dificultad dificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
    }
    
   

    // 🔧 GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
