package com.hibernate.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "rutina_ejercicio")
public class RutinaEjercicio {

    @EmbeddedId
    private RutinaEjercicioId id;

    @ManyToOne
    @MapsId("idRutina")
    @JoinColumn(name = "id_rutina")
    private Rutina rutina;

    @ManyToOne
    @MapsId("idEjercicio")
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio ejercicio;

    private int series;
    private int repeticiones;
    private double pesoRecomendado;

    @Column(name = "tiempo_ejecucion")
    private Time tiempoEjecucion;

    @Column(name = "tiempo_descanso")
    private Time tiempoDescanso;

    private int orden;

	public RutinaEjercicioId getId() {
		return id;
	}

	public void setId(RutinaEjercicioId id) {
		this.id = id;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public double getPesoRecomendado() {
		return pesoRecomendado;
	}

	public void setPesoRecomendado(double pesoRecomendado) {
		this.pesoRecomendado = pesoRecomendado;
	}

	public Time getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(Time tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public Time getTiempoDescanso() {
		return tiempoDescanso;
	}

	public void setTiempoDescanso(Time tiempoDescanso) {
		this.tiempoDescanso = tiempoDescanso;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

    
}
