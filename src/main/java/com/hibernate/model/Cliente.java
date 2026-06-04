package com.hibernate.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad que representa un cliente del gimnasio.
 * <p>
 * Almacena la información personal del cliente, sus credenciales
 * de acceso, el rol asignado dentro de la aplicación, las rutinas
 * asociadas y el entrenador responsable de su seguimiento.
 * </p>
 *
 * @author Héctor
 * @version 1.0
 */
@Entity
@Table(name = "cliente")
public class Cliente {

	/**
	 * Identificador único del cliente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idCliente")
	private int idCliente;

	/**
	 * Nombre completo del cliente.
	 */
	@Column(name = "nombre")
	private String nombre;

	/**
	 * Dirección de correo electrónico del cliente.
	 */
	@Column(name = "email")
	private String email;

	/**
	 * Número de teléfono del cliente.
	 */
	@Column(name = "telefono")
	private String telefono;

	/**
	 * Fecha en la que el cliente se registró en el gimnasio.
	 */
	@Column(name = "fecha_alta")
	private LocalDate fecha_alta;

	/**
	 * Contraseña cifrada utilizada para la autenticación.
	 */
	@Column(name = "contraseña")
	private String contraseña;

	/**
	 * Rol asignado al usuario dentro del sistema.
	 * Ejemplos: ADMIN, CLIENTE.
	 */
	@Column(name = "rol")
	private String rol;

	/**
	 * Lista de rutinas asignadas al cliente.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	    name = "cliente_rutina",
	    joinColumns = @JoinColumn(name = "id_cliente"),
	    inverseJoinColumns = @JoinColumn(name = "id_rutina")
	)
	private List<Rutina> rutinas = new ArrayList<>();

	/**
	 * Entrenador responsable del cliente.
	 */
	@ManyToOne
	@JoinColumn(name = "id_entrenador")
	private Entrenador entrenador;

	/**
	 * Constructor vacío requerido por Hibernate.
	 */
	public Cliente() {

	}

	/**
	 * Constructor con los datos básicos del cliente.
	 *
	 * @param nombre Nombre del cliente.
	 * @param email Correo electrónico del cliente.
	 * @param telefono Teléfono del cliente.
	 * @param fecha_alta Fecha de alta del cliente.
	 */
	public Cliente(String nombre, String email, String telefono, LocalDate fecha_alta) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.fecha_alta = fecha_alta;
	}

	/**
	 * Obtiene el identificador del cliente.
	 *
	 * @return ID del cliente.
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * Establece el identificador del cliente.
	 *
	 * @param idCliente Nuevo ID del cliente.
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Obtiene el nombre del cliente.
	 *
	 * @return Nombre del cliente.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del cliente.
	 *
	 * @param nombre Nuevo nombre del cliente.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el correo electrónico del cliente.
	 *
	 * @return Correo electrónico.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el correo electrónico del cliente.
	 *
	 * @param email Nuevo correo electrónico.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene el teléfono del cliente.
	 *
	 * @return Teléfono del cliente.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Establece el teléfono del cliente.
	 *
	 * @param telefono Nuevo teléfono.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Obtiene la fecha de alta del cliente.
	 *
	 * @return Fecha de alta.
	 */
	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	/**
	 * Establece la fecha de alta del cliente.
	 *
	 * @param fecha_alta Nueva fecha de alta.
	 */
	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	/**
	 * Establece la contraseña del cliente.
	 *
	 * @param contraseña Contraseña cifrada.
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	/**
	 * Obtiene la contraseña del cliente.
	 *
	 * @return Contraseña cifrada.
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * Obtiene el rol del cliente.
	 *
	 * @return Rol asignado.
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Establece el rol del cliente.
	 *
	 * @param rol Nuevo rol.
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Obtiene las rutinas asignadas al cliente.
	 *
	 * @return Lista de rutinas.
	 */
	public List<Rutina> getRutinas() {
		return rutinas;
	}

	/**
	 * Establece las rutinas asignadas al cliente.
	 *
	 * @param rutinas Nueva lista de rutinas.
	 */
	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	/**
	 * Obtiene el entrenador asignado al cliente.
	 *
	 * @return Entrenador responsable.
	 */
	public Entrenador getEntrenador() {
		return entrenador;
	}

	/**
	 * Asigna un entrenador al cliente.
	 *
	 * @param entrenador Entrenador responsable.
	 */
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

}
