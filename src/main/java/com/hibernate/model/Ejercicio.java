package com.hibernate.model;

import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "ejercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEjercicio")
    private int idEjercicio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "video")
    private String video;

    @ManyToOne
    @JoinColumn(name = "id_grupo_muscular")
    private GrupoMuscular id_grupo_muscular;

    @Override
    public String toString() {
        return nombre;
    }
}