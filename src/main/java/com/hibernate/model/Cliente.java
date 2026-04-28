package com.hibernate.model;

import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    private String nombre;
    private String email;
    private String telefono;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @ManyToMany
    @JoinTable(
        name = "cliente_rutina",
        joinColumns = @JoinColumn(name = "id_cliente"),
        inverseJoinColumns = @JoinColumn(name = "id_rutina")
    )
    private List<Rutina> rutinas = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Progreso> progresos = new ArrayList<>();

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	public List<Progreso> getProgresos() {
		return progresos;
	}

	public void setProgresos(List<Progreso> progresos) {
		this.progresos = progresos;
	}

    
}