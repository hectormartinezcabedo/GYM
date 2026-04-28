package com.hibernate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RutinaEjercicioId implements Serializable {

    private int idRutina;
    private int idEjercicio;

    public RutinaEjercicioId() {}

    // equals y hashCode OBLIGATORIOS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RutinaEjercicioId)) return false;
        RutinaEjercicioId that = (RutinaEjercicioId) o;
        return idRutina == that.idRutina &&
               idEjercicio == that.idEjercicio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRutina, idEjercicio);
    }

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

    
}
