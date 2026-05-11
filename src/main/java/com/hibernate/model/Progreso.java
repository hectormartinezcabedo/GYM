package com.hibernate.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "progreso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progreso")
    private int idProgreso;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio ejercicio;

    @Column(name = "peso_utilizado")
    private double pesoUtilizado;

    @Column(name = "repeticiones")
    private int repeticiones;

    @Column(name = "fecha")
    private LocalDate fecha;
}