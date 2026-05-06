package com.hibernate.main;

import java.awt.EventQueue;
import java.awt.Image;

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

import javax.swing.ImageIcon;
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

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;

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
	
	public ImageIcon cargarIcono(String ruta) {
	    ImageIcon icon = new ImageIcon(ruta);
	    Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
	    return new ImageIcon(img);
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldIDentrenador;
	private JTextField textFieldEspecialidad;
	private JTextField textFieldNOMentrenador;

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
		
		JTabbedPane tabbedPaneGYM = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGYM.setBounds(34, 12, 862, 542);
		tabbedPaneGYM.setBorder(new CompoundBorder());
		
		JPanel panelAdmin = new JPanel();
		tabbedPaneGYM.addTab("Admin", cargarIcono("img/9977156.png"), panelAdmin);
		panelAdmin.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 922, 537);
		panelAdmin.add(tabbedPane_2);
		
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
			modelEntrenador.addColumn("ID Entrenador");
			modelEntrenador.addColumn("Nombre");
			modelEntrenador.addColumn("Especialidad");
			
			List<Entrenador> listEntrenadores = new ArrayList();
			listEntrenadores = daoEntrenador.selectAllEntrenadores();
				for (Entrenador e : listEntrenadores) {
					Object[] row = new Object[2];
					row[0] = e.getIdEntrenador();
					row[1] = e.getNombre();
					modelEntrenador.addRow(row);
				}
		
		JPanel panelGestionClientes = new JPanel();
		tabbedPane_2.addTab("Gestión Clientes", cargarIcono("img/18272841.png"), panelGestionClientes);
		panelGestionClientes.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 65, 384, 192);
		panelGestionClientes.add(scrollPane);
		
		tableAdminCliente = new JTable(modelAdminCliente);
		scrollPane.setViewportView(tableAdminCliente);
		
		textField_ID = new JTextField();
		textField_ID.setEditable(false);
		textField_ID.setBounds(421, 269, 114, 21);
		panelGestionClientes.add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(421, 302, 114, 21);
		panelGestionClientes.add(textField_Nombre);
		textField_Nombre.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		textField_Email.setBounds(421, 335, 114, 21);
		panelGestionClientes.add(textField_Email);
		
		textField_Telefono = new JTextField();
		textField_Telefono.setColumns(10);
		textField_Telefono.setBounds(421, 368, 114, 21);
		panelGestionClientes.add(textField_Telefono);
		
		textField_Fecha_Alta = new JTextField();
		textField_Fecha_Alta.setColumns(10);
		textField_Fecha_Alta.setBounds(421, 401, 114, 21);
		panelGestionClientes.add(textField_Fecha_Alta);
		
		JButton btnInsertar_1 = new JButton("Insertar");
		btnInsertar_1.setBackground(new Color(102, 205, 170));
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
		btnInsertar_1.setBounds(242, 439, 82, 27);
		panelGestionClientes.add(btnInsertar_1);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBackground(new Color(70, 130, 180));
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
		btnActualizar.setBounds(396, 439, 94, 27);
		panelGestionClientes.add(btnActualizar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(new Color(147, 112, 219));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daoCliente.deleteCliente(Integer.parseInt(textField_ID.getText()));
				JOptionPane.showMessageDialog(null, "Cliente eliminado");
				modelAdminCliente.setRowCount(0);
				cargarTablaCliente(modelAdminCliente);
			}
		});
		btnBorrar.setBounds(555, 439, 71, 27);
		panelGestionClientes.add(btnBorrar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(328, 271, 60, 17);
		panelGestionClientes.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(328, 304, 60, 17);
		panelGestionClientes.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(328, 337, 60, 17);
		panelGestionClientes.add(lblEmail);
		
		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setBounds(328, 370, 60, 17);
		panelGestionClientes.add(lblTelfono);
		
		JLabel lblFechaAlta = new JLabel("Fecha Alta:");
		lblFechaAlta.setBounds(328, 403, 75, 17);
		panelGestionClientes.add(lblFechaAlta);
		
		JLabel lblBuscarCliente = new JLabel("Buscar cliente");
		lblBuscarCliente.setBounds(264, 26, 84, 17);
		panelGestionClientes.add(lblBuscarCliente);
		
		textField = new JTextField();
		textField.setBounds(352, 24, 114, 21);
		panelGestionClientes.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(478, 21, 82, 27);
		panelGestionClientes.add(btnBuscar);
		
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
		
		
		
		JPanel panelGestiónEntrenadores = new JPanel();
		tabbedPane_2.addTab("Gestión Entrenadores", cargarIcono("img/939255.png"), panelGestiónEntrenadores);
		panelGestiónEntrenadores.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(223, 91, 384, 192);
		panelGestiónEntrenadores.add(scrollPane_1);
		
		tableAdminEntrenadores = new JTable(modelEntrenador);
		scrollPane_1.setViewportView(tableAdminEntrenadores);
		
		JLabel lblBuscarEntrenador = new JLabel("Buscar entrenador");
		lblBuscarEntrenador.setBounds(243, 46, 114, 17);
		panelGestiónEntrenadores.add(lblBuscarEntrenador);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(367, 44, 114, 21);
		panelGestiónEntrenadores.add(textField_1);
		
		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.setBounds(493, 41, 82, 27);
		panelGestiónEntrenadores.add(btnBuscar_1);
		
		textFieldIDentrenador = new JTextField();
		textFieldIDentrenador.setEditable(false);
		textFieldIDentrenador.setColumns(10);
		textFieldIDentrenador.setBounds(401, 295, 114, 21);
		panelGestiónEntrenadores.add(textFieldIDentrenador);
		
		JLabel lblId_2 = new JLabel("ID:");
		lblId_2.setBounds(308, 297, 60, 17);
		panelGestiónEntrenadores.add(lblId_2);
		
		JLabel lblNombre_2 = new JLabel("Nombre:");
		lblNombre_2.setBounds(308, 330, 60, 17);
		panelGestiónEntrenadores.add(lblNombre_2);
		
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(308, 363, 82, 17);
		panelGestiónEntrenadores.add(lblEspecialidad);
		
		JButton btnInsertar_1_1 = new JButton("Insertar");
		btnInsertar_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnInsertar_1_1.setBackground(new Color(102, 205, 170));
		btnInsertar_1_1.setBounds(223, 430, 82, 27);
		panelGestiónEntrenadores.add(btnInsertar_1_1);
		
		JButton btnActualizar_1 = new JButton("Actualizar");
		btnActualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textFieldIDentrenador.getText());
				Entrenador ent = new Entrenador();
				ent.setIdEntrenador(id);
				ent.setNombre(textFieldNOMentrenador.getText());
				ent.setEspecialidad(textFieldEspecialidad.getText());
				daoEntrenador.updateEntrenador(ent);
				modelEntrenador.setRowCount(0);
				cargarTablaEntrenador(modelEntrenador);
			}
		});
		btnActualizar_1.setBackground(new Color(70, 130, 180));
		btnActualizar_1.setBounds(377, 430, 94, 27);
		panelGestiónEntrenadores.add(btnActualizar_1);
		
		textFieldEspecialidad = new JTextField();
		textFieldEspecialidad.setColumns(10);
		textFieldEspecialidad.setBounds(401, 361, 114, 21);
		panelGestiónEntrenadores.add(textFieldEspecialidad);
		
		textFieldNOMentrenador = new JTextField();
		textFieldNOMentrenador.setColumns(10);
		textFieldNOMentrenador.setBounds(401, 328, 114, 21);
		panelGestiónEntrenadores.add(textFieldNOMentrenador);
		
		JButton btnBorrar_1 = new JButton("Borrar");
		btnBorrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daoEntrenador.deleteEntrenador(Integer.parseInt(textFieldIDentrenador.getText()));
				JOptionPane.showMessageDialog(null, "Entrenador eliminado");
				modelEntrenador.setRowCount(0);
				cargarTablaEntrenador(modelEntrenador);
			}
		});
		btnBorrar_1.setBackground(new Color(147, 112, 219));
		btnBorrar_1.setBounds(536, 430, 71, 27);
		panelGestiónEntrenadores.add(btnBorrar_1);
		JPanel panelCliente = new JPanel();
		tabbedPaneGYM.addTab("Clientes", cargarIcono("img/18272841.png"), panelCliente);
		
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
		scrollPaneCliente.setBounds(265, 42, 317, 217);
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
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(413, 418, 114, 21);
		panelPerfil.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(413, 385, 114, 21);
		panelPerfil.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(413, 352, 114, 21);
		panelPerfil.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(413, 319, 114, 21);
		panelPerfil.add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(413, 286, 114, 21);
		panelPerfil.add(textFieldID);
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(330, 290, 60, 17);
		panelPerfil.add(lblId_1);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(330, 323, 60, 17);
		panelPerfil.add(lblNombre_1);
		
		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(330, 356, 60, 17);
		panelPerfil.add(lblEmail_1);
		
		JLabel lblTelfono_1 = new JLabel("Teléfono:");
		lblTelfono_1.setBounds(330, 389, 60, 17);
		panelPerfil.add(lblTelfono_1);
		
		JLabel lblFechaAlta_1 = new JLabel("Fecha Alta:");
		lblFechaAlta_1.setBounds(330, 422, 75, 17);
		panelPerfil.add(lblFechaAlta_1);
		
		JPanel panelRutinas = new JPanel();
		tabbedPane_1.addTab("Rutinas", null, panelRutinas, null);
		frame.getContentPane().add(tabbedPaneGYM);
		
		JPanel panelEntrenador = new JPanel();
		tabbedPaneGYM.addTab("Entrenador", cargarIcono("img/939255.png"), panelEntrenador);
		panelEntrenador.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(69, 63, 161, 111);
		panelEntrenador.add(scrollPane_2);
		
		tableEntrenador = new JTable(modelEntrenador);
		scrollPane_2.setViewportView(tableEntrenador);
		
		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneLogin.setBounds(172, 42, 511, 475);
		frame.getContentPane().add(tabbedPaneLogin);
		
		JPanel panelLogin = new JPanel();
		tabbedPaneLogin.addTab("Iniciar Sesión", null, panelLogin, null);
		panelLogin.setLayout(null);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBounds(194, 271, 115, 27);
		panelLogin.add(btnIniciarSesion);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(194, 165, 114, 21);
		panelLogin.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setBounds(194, 222, 114, 21);
		panelLogin.add(textFieldContraseña);
		textFieldContraseña.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(125, 167, 60, 17);
		panelLogin.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(104, 224, 72, 17);
		panelLogin.add(lblContrasea);
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
		
		JPanel panelRegister = new JPanel();
		tabbedPaneLogin.addTab("Registrarse", null, panelRegister, null);
		panelRegister.setLayout(null);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(183, 352, 102, 27);
		panelRegister.add(btnRegistrarse);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hash = BCrypt.hashpw(textFieldContraseña.getText(), BCrypt.gensalt());
			}
		});
	}
}
