package com.hibernate.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.mindrot.jbcrypt.BCrypt;

import com.hibernate.dao.ClienteDAO;
import com.hibernate.dao.EntrenadorDAO;
import com.hibernate.model.Cliente;
import com.hibernate.model.Entrenador;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class GestionGimnasio {
	
	void cargarTablaCliente(DefaultTableModel modelCliente) {
		ClienteDAO dao = new ClienteDAO();
		
		List<Cliente> listClientes = new ArrayList();
		listClientes = dao.selectAllClientes();
			for (Cliente c : listClientes) {
				Object[] row = new Object[5];
				row[0] = c.getIdCliente();
				row[1] = c.getNombre();
				row[2] = c.getEmail();
				row[3] = c.getTelefono();
				row[4] = c.getFecha_alta();
				modelCliente.addRow(row);
			}
		
	}
	
	void cargarTablaEntrenador(DefaultTableModel modelEntrenador) {
		EntrenadorDAO dao = new EntrenadorDAO();
		
		List<Entrenador> listEntrenadores = new ArrayList();
		listEntrenadores = dao.selectAllEntrenadores();
			for (Entrenador e : listEntrenadores) {
				Object[] row = new Object[2];
				row[0] = e.getIdEntrenador();
				row[1] = e.getNombre();
				modelEntrenador.addRow(row);
			}
		
	}

	private JFrame frame;
	private JTable tableAdminCliente;
	private JTable tableCliente;
	private JTable tableEntrenador;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JTextField textFieldFechaAlta;
	private JTextField textFieldID;
	private JTextField textField_ID;
	private JTextField textField_Nombre;
	private JTextField textField_Email;
	private JTextField textField_Telefono;
	private JTextField textField_Fecha_Alta;
	private JTable tableAdminEntrenadores;
	private JTextField textFieldUsuario;
	private JTextField textFieldContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionGimnasio window = new GestionGimnasio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionGimnasio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 927, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ClienteDAO daoCliente = new ClienteDAO();
		EntrenadorDAO daoEntrenador = new EntrenadorDAO();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(499, 275, 428, 291);
		tabbedPane.setBorder(new CompoundBorder());
		
		JPanel panelAdmin = new JPanel();
		tabbedPane.addTab("Admin", null, panelAdmin, null);
		panelAdmin.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 922, 537);
		panelAdmin.add(tabbedPane_2);
		
		JPanel panelGestionClientes = new JPanel();
		tabbedPane_2.addTab("Gestión Clientes", null, panelGestionClientes, null);
		panelGestionClientes.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 49, 278, 143);
		panelGestionClientes.add(scrollPane);
		
		DefaultTableModel modelAdminCliente = new DefaultTableModel();
		modelAdminCliente.addColumn("ID Cliente");
		modelAdminCliente.addColumn("Nombre");
		modelAdminCliente.addColumn("Email");
		modelAdminCliente.addColumn("Teléfono");
		modelAdminCliente.addColumn("Fecha Alta");
		
		List<Cliente> listClientesAdmin = new ArrayList();
		listClientesAdmin = daoCliente.selectAllClientes();
			for (Cliente c : listClientesAdmin) {
				Object[] row = new Object[5];
				row[0] = c.getIdCliente();
				row[1] = c.getNombre();
				row[2] = c.getEmail();
				row[3] = c.getTelefono();
				row[4] = c.getFecha_alta();
				modelAdminCliente.addRow(row);
			}
			
			DefaultTableModel modelEntrenador = new DefaultTableModel();
			modelEntrenador.addColumn("ID Cliente");
			modelEntrenador.addColumn("Nombre");
			
			List<Entrenador> listEntrenadores = new ArrayList();
			listEntrenadores = daoEntrenador.selectAllEntrenadores();
				for (Entrenador e : listEntrenadores) {
					Object[] row = new Object[2];
					row[0] = e.getIdEntrenador();
					row[1] = e.getNombre();
					modelEntrenador.addRow(row);
				}
		
		tableAdminCliente = new JTable(modelAdminCliente);
		scrollPane.setViewportView(tableAdminCliente);
		
		textField_ID = new JTextField();
		textField_ID.setEditable(false);
		textField_ID.setBounds(127, 224, 114, 21);
		panelGestionClientes.add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(127, 257, 114, 21);
		panelGestionClientes.add(textField_Nombre);
		textField_Nombre.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		textField_Email.setBounds(127, 290, 114, 21);
		panelGestionClientes.add(textField_Email);
		
		textField_Telefono = new JTextField();
		textField_Telefono.setColumns(10);
		textField_Telefono.setBounds(127, 323, 114, 21);
		panelGestionClientes.add(textField_Telefono);
		
		textField_Fecha_Alta = new JTextField();
		textField_Fecha_Alta.setColumns(10);
		textField_Fecha_Alta.setBounds(127, 356, 114, 21);
		panelGestionClientes.add(textField_Fecha_Alta);
		
		JButton btnInsertar_1 = new JButton("Insertar");
		btnInsertar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int telefono= Integer.parseInt(textField_Telefono.getText());
				Cliente c = new Cliente(textField_Nombre.getText(),textField_Email.getText(),telefono,textField_Fecha_Alta.getText());
				daoCliente.insertCliente(c);
				modelAdminCliente.setRowCount(0);
				cargarTablaCliente(modelAdminCliente);
				JOptionPane.showMessageDialog(null, "Cliente añadido correctamente");
			}
		});
		btnInsertar_1.setBounds(12, 408, 82, 27);
		panelGestionClientes.add(btnInsertar_1);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int telefono = Integer.parseInt(textField_Telefono.getText());
				int id = Integer.parseInt(textField_ID.getText());
				
				Cliente c = new Cliente();
				c.setIdCliente(id);
				c.setNombre(textField_Nombre.getText());
				c.setEmail(textField_Email.getText());
				c.setTelefono(telefono);
				c.setFecha_alta(textField_Fecha_Alta.getText());
				daoCliente.updateCliente(c);
				modelAdminCliente.setRowCount(0);
				cargarTablaCliente(modelAdminCliente);
			}
		});
		btnActualizar.setBounds(106, 408, 94, 27);
		panelGestionClientes.add(btnActualizar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daoCliente.deleteCliente(Integer.parseInt(textField_ID.getText()));
				JOptionPane.showMessageDialog(null, "Cliente eliminado");
				modelAdminCliente.setRowCount(0);
				cargarTablaCliente(modelAdminCliente);
			}
		});
		btnBorrar.setBounds(219, 408, 71, 27);
		panelGestionClientes.add(btnBorrar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(34, 226, 60, 17);
		panelGestionClientes.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(34, 259, 60, 17);
		panelGestionClientes.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(34, 292, 60, 17);
		panelGestionClientes.add(lblEmail);
		
		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setBounds(34, 325, 60, 17);
		panelGestionClientes.add(lblTelfono);
		
		JLabel lblFechaAlta = new JLabel("Fecha Alta:");
		lblFechaAlta.setBounds(34, 358, 75, 17);
		panelGestionClientes.add(lblFechaAlta);
		
		JPanel panelGestiónEntrenadores = new JPanel();
		tabbedPane_2.addTab("Gestión Entrenadores", null, panelGestiónEntrenadores, null);
		panelGestiónEntrenadores.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(75, 39, 288, 148);
		panelGestiónEntrenadores.add(scrollPane_1);
		
		tableAdminEntrenadores = new JTable();
		scrollPane_1.setViewportView(tableAdminEntrenadores);
		JPanel panelCliente = new JPanel();
		tabbedPane.addTab("Cliente", panelCliente);
		panelCliente.setLayout(null);
		
		DefaultTableModel modelCliente = new DefaultTableModel();
		modelCliente.addColumn("ID Cliente");
		modelCliente.addColumn("Nombre");
		modelCliente.addColumn("Email");
		modelCliente.addColumn("Teléfono");
		modelCliente.addColumn("Fecha Alta");
		
		List<Cliente> listClientes = new ArrayList();
		listClientes = daoCliente.selectAllClientes();
			for (Cliente c : listClientes) {
				Object[] row = new Object[5];
				row[0] = c.getIdCliente();
				row[1] = c.getNombre();
				row[2] = c.getEmail();
				row[3] = c.getTelefono();
				row[4] = c.getFecha_alta();
				modelCliente.addRow(row);
			}
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 922, 537);
		panelCliente.add(tabbedPane_1);
		
		JPanel panelPerfil = new JPanel();
		tabbedPane_1.addTab("Perfil", null, panelPerfil, null);
		panelPerfil.setLayout(null);
		
		JScrollPane scrollPaneCliente = new JScrollPane();
		scrollPaneCliente.setBounds(37, 43, 257, 181);
		panelPerfil.add(scrollPaneCliente);
		tableCliente = new JTable(modelCliente);
		scrollPaneCliente.setViewportView(tableCliente);
		
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = tableCliente.getSelectedRow();
				TableModel modelCliente = tableCliente.getModel();
				textFieldID.setText(modelCliente.getValueAt(index, 0).toString());
				textFieldNombre.setText(modelCliente.getValueAt(index, 1).toString());
				textFieldEmail.setText(modelCliente.getValueAt(index, 2).toString());
				textFieldTelefono.setText(modelCliente.getValueAt(index, 3).toString());
				textFieldFechaAlta.setText(modelCliente.getValueAt(index, 4).toString());
			}
		});
		
		tableAdminCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = tableAdminCliente.getSelectedRow();
				TableModel modelAdminCliente = tableCliente.getModel();
				textField_ID.setText(modelAdminCliente.getValueAt(index, 0).toString());
				textField_Nombre.setText(modelAdminCliente.getValueAt(index, 1).toString());
				textField_Email.setText(modelAdminCliente.getValueAt(index, 2).toString());
				textField_Telefono.setText(modelAdminCliente.getValueAt(index, 3).toString());
				textField_Fecha_Alta.setText(modelAdminCliente.getValueAt(index, 4).toString());
			}
		});
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(94, 397, 114, 21);
		panelPerfil.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(91, 368, 114, 21);
		panelPerfil.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(89, 342, 114, 21);
		panelPerfil.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(90, 313, 114, 21);
		panelPerfil.add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(89, 267, 114, 21);
		panelPerfil.add(textFieldID);
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		
		JPanel panelEjercicios = new JPanel();
		tabbedPane_1.addTab("Ejercicios", null, panelEjercicios, null);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelEntrenador = new JPanel();
		tabbedPane.addTab("Entrenador", null, panelEntrenador, null);
		panelEntrenador.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(69, 63, 161, 111);
		panelEntrenador.add(scrollPane_2);
		
		tableEntrenador = new JTable(modelEntrenador);
		scrollPane_2.setViewportView(tableEntrenador);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Cliente cliente = new Cliente();
					cliente = daoCliente.selectClienteByNombre(textFieldUsuario.getText());
					 boolean esCorrecta = BCrypt.checkpw(textFieldContraseña.getText(), cliente.getContraseña());
					if(cliente.getNombre().equals(textFieldUsuario.getText()) && esCorrecta) {
						JOptionPane.showMessageDialog(frame, "Bienvenido "+cliente.getNombre(), "Informacion",
						JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(frame, "Error en el usuario o la contraseña", "Warning",
						JOptionPane.INFORMATION_MESSAGE);
					}

				}catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "No existe el usuario", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		btnIniciarSesion.setBounds(118, 110, 115, 27);
		frame.getContentPane().add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hash = BCrypt.hashpw(textFieldContraseña.getText(), BCrypt.gensalt());
			}
		});
		btnRegistrarse.setBounds(118, 162, 115, 27);
		frame.getContentPane().add(btnRegistrarse);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(118, 240, 114, 21);
		frame.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setBounds(118, 288, 114, 21);
		frame.getContentPane().add(textFieldContraseña);
		textFieldContraseña.setColumns(10);
	}
}
