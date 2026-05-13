package com.hibernate.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validaciones {

    // NOMBRE
    public static boolean validarNombre(String nombre) {

        return nombre != null
                && nombre.trim().length() >= 3
                && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    // EMAIL
    public static boolean validarEmail(String email) {

        return email != null
                && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // TELEFONO ESPAÑOL
    public static boolean validarTelefono(String telefono) {

        return telefono.matches("[6789]\\d{8}");
    }

    // PASSWORD SEGURA
    public static boolean validarPassword(String password) {

        return password != null
                && password.length() >= 6
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*\\d.*");
    }

    // FECHA
    public static boolean validarFecha(String fecha) {

        try {

            LocalDate.parse(fecha);

            return true;

        } catch (DateTimeParseException e) {

            return false;
        }
    }

    // PESO
    public static boolean validarPeso(String peso) {

        try {

            double p = Double.parseDouble(peso);

            return p > 0 && p < 500;

        } catch (Exception e) {

            return false;
        }
    }

    // REPS
    public static boolean validarReps(String reps) {

        try {

            int r = Integer.parseInt(reps);

            return r > 0 && r < 1000;

        } catch (Exception e) {

            return false;
        }
    }
}