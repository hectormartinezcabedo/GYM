package com.hibernate.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.mindrot.jbcrypt.BCrypt;

import com.hibernate.dao.ClienteDAO;
import com.hibernate.dao.EjercicioDAO;
import com.hibernate.dao.EntrenadorDAO;
import com.hibernate.model.Cliente;
import com.hibernate.model.Ejercicio;
import com.hibernate.model.Entrenador;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.UIManager;
import javax.persistence.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import com.hibernate.dao.GrupoMuscularDAO;
import com.hibernate.dao.ProgresoDAO;
import com.hibernate.model.GrupoMuscular;
import com.hibernate.model.Progreso;
import com.hibernate.dao.RutinaDAO;
import com.hibernate.model.Rutina;
import com.hibernate.util.EmailService;

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
			Object[] row = new Object[3];
			row[0] = e.getIdEntrenador();
			row[1] = e.getNombre();
			row[2] = e.getEspecialidad();
			modelEntrenador.addRow(row);
		}

	}

	void cargarPerfilCliente(DefaultTableModel modelCliente) {

		modelCliente.setRowCount(0);

		if (clienteLogueado != null) {

			Object[] row = new Object[5];

			row[0] = clienteLogueado.getIdCliente();
			row[1] = clienteLogueado.getNombre();
			row[2] = clienteLogueado.getEmail();
			row[3] = clienteLogueado.getTelefono();
			row[4] = clienteLogueado.getFecha_alta();

			modelCliente.addRow(row);

			// RELLENAR TEXTFIELDS
			textFieldID.setText(String.valueOf(clienteLogueado.getIdCliente()));
			textFieldNombre.setText(clienteLogueado.getNombre());
			textFieldEmail.setText(clienteLogueado.getEmail());
			textFieldTelefono.setText(String.valueOf(clienteLogueado.getTelefono()));
			textFieldFechaAlta.setText(clienteLogueado.getFecha_alta().toString());

			if (clienteLogueado.getEntrenador() != null) {
				comboEntrenadorCliente.setSelectedItem(clienteLogueado.getEntrenador());
			}
			if (modelRutinas != null) {
				modelRutinas.setRowCount(0);
				for (Rutina r : clienteLogueado.getRutinas()) {
					modelRutinas.addRow(new Object[] { r.getIdRutina(), r.getNombre(), r.getDescripcion(),
							r.getDificultad() != null ? r.getDificultad().toString() : "-" });
				}
			}
		}
	}

	public ImageIcon cargarIcono(String ruta) {
		ImageIcon icon = new ImageIcon(ruta);
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public ChartPanel crearGraficoStats() {

	    ClienteDAO daoCliente = new ClienteDAO();
	    EntrenadorDAO daoEntrenador = new EntrenadorDAO();

	    int totalClientes = daoCliente.contarClientes();
	    int clientesRecientes = daoCliente.contarClientesRecientes();
	    int totalEntrenadores = daoEntrenador.contarEntrenadores();

	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    dataset.addValue(totalClientes, "Datos", "Clientes");
	    dataset.addValue(clientesRecientes, "Datos", "Últimos 30 días");
	    dataset.addValue(totalEntrenadores, "Datos", "Entrenadores");

	    JFreeChart chart = ChartFactory.createLineChart(
	        "Estadísticas del Gimnasio",
	        "Categoría",
	        "Cantidad",
	        dataset
	    );

	    // FONDO
	    chart.setBackgroundPaint(Color.white);

	    // FONDO INTERIOR
	    chart.getCategoryPlot().setBackgroundPaint(new Color(45,45,45));

	    // LINEAS GRID
	    chart.getCategoryPlot().setRangeGridlinePaint(Color.GRAY);

	    // RENDERER PARA LOS PUNTOS
	    LineAndShapeRenderer renderer = new LineAndShapeRenderer();

	    // MOSTRAR LINEA
	    renderer.setSeriesLinesVisible(0, true);

	    // MOSTRAR PUNTOS
	    renderer.setSeriesShapesVisible(0, true);

	    // APLICAR RENDERER
	    chart.getCategoryPlot().setRenderer(renderer);

	    return new ChartPanel(chart);
	}

	void cargarProgreso(DefaultTableModel modelProgreso) {
		if (clienteLogueado == null || modelProgreso == null)
			return;
		modelProgreso.setRowCount(0);
		ProgresoDAO dao = new ProgresoDAO();
		for (Progreso p : dao.selectProgresoByCliente(clienteLogueado.getIdCliente())) {
			modelProgreso.addRow(new Object[] { p.getEjercicio().getNombre(), p.getPesoUtilizado(), p.getRepeticiones(),
					p.getFecha().toString() });
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
	private JPasswordField textFieldContraseña;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldIDentrenador;
	private JTextField textFieldEspecialidad;
	private JTextField textFieldNOMentrenador;
	private Cliente clienteLogueado;
	private JComboBox<Entrenador> comboEntrenadorCliente;
	private DefaultTableModel modelClientesEntrenador;
	private DefaultTableModel modelRutinas;
	private DefaultTableModel modelEjercicios;
	private DefaultTableModel modelProgreso;

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

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.put("control", new Color(40, 40, 40));
			UIManager.put("info", new Color(40, 40, 40));
			UIManager.put("nimbusBase", new Color(18, 30, 49));
			UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
			UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
			UIManager.put("nimbusFocus", new Color(115, 164, 209));
			UIManager.put("nimbusGreen", new Color(176, 179, 50));
			UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
			UIManager.put("nimbusLightBackground", new Color(43, 43, 43));
			UIManager.put("nimbusOrange", new Color(191, 98, 4));
			UIManager.put("nimbusRed", new Color(169, 46, 34));
			UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
			UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
			UIManager.put("text", new Color(230, 230, 230));
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		frame.setBounds(100, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		ClienteDAO daoCliente = new ClienteDAO();
		EntrenadorDAO daoEntrenador = new EntrenadorDAO();
		Color primary = new Color(30, 30, 30); // negro
		Color accent = new Color(0, 150, 136); // verde gym
		Color danger = new Color(220, 53, 69); // rojo

		JTabbedPane tabbedPaneGYM = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGYM.setBounds(20, 10, 1040, 700);
		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(862, 780, 150, 30);
		btnLogout.setVisible(false);
		frame.getContentPane().add(btnLogout);

		tabbedPaneGYM.setBorder(new CompoundBorder());
		JLabel lblUsuarioLogueado = new JLabel();

		lblUsuarioLogueado.setBounds(30, 780, 400, 30);

		frame.getContentPane().add(lblUsuarioLogueado);

		JPanel panelAdmin = new JPanel();
		tabbedPaneGYM.addTab("Admin", cargarIcono("img/9977156.png"), panelAdmin);
		panelAdmin.setLayout(null);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 857, 504);
		panelAdmin.add(tabbedPane_2);

		DefaultTableModel modelAdminCliente = new DefaultTableModel();
		modelAdminCliente.addColumn("ID");
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
		btnInsertar_1.setBackground(accent);
		btnInsertar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int telefono = Integer.parseInt(textField_Telefono.getText());
				Cliente c = new Cliente(textField_Nombre.getText(), textField_Email.getText(), telefono,
						LocalDate.now());
				daoCliente.insertCliente(c);
				modelAdminCliente.setRowCount(0);
				cargarTablaCliente(modelAdminCliente);
				JOptionPane.showMessageDialog(null, "Cliente añadido correctamente");
				textField_Nombre.setText("");
				textField_Email.setText("");
				textField_Telefono.setText("");
				textField_Fecha_Alta.setText("");
			}
		});
		btnInsertar_1.setBounds(242, 439, 82, 27);
		panelGestionClientes.add(btnInsertar_1);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBackground(new Color(100, 149, 237));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int telefono = Integer.parseInt(textField_Telefono.getText());
				int id = Integer.parseInt(textField_ID.getText());

				Cliente c = new Cliente();
				c.setIdCliente(id);
				c.setNombre(textField_Nombre.getText());
				c.setEmail(textField_Email.getText());
				c.setTelefono(telefono);
				c.setFecha_alta(LocalDate.parse(textField_Fecha_Alta.getText()));
				daoCliente.updateCliente(c);
				modelAdminCliente.setRowCount(0);
				cargarTablaCliente(modelAdminCliente);
			}
		});
		btnActualizar.setBounds(396, 439, 94, 27);
		panelGestionClientes.add(btnActualizar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(new Color(205, 92, 92));
		btnBorrar.setBackground(danger);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar este cliente?",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION)
					return;
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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelAdminCliente);
				tableAdminCliente.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(textField.getText()));
			}
		});
		btnBuscar.setBounds(478, 21, 82, 27);
		panelGestionClientes.add(btnBuscar);

		tableAdminCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = tableAdminCliente.getSelectedRow();
				TableModel modelAdminCliente = tableAdminCliente.getModel();
				textField_ID.setText(modelAdminCliente.getValueAt(index, 0).toString());
				textField_Nombre.setText(modelAdminCliente.getValueAt(index, 1).toString());
				textField_Email.setText(modelAdminCliente.getValueAt(index, 2).toString());
				textField_Telefono.setText(modelAdminCliente.getValueAt(index, 3).toString());
				textField_Fecha_Alta.setText(modelAdminCliente.getValueAt(index, 4).toString());
			}
		});

		JPanel panelGestiónEntrenadores = new JPanel();
		tabbedPane_2.addTab("Gestión Entrenadores", cargarIcono("img/939255.png"), panelGestiónEntrenadores);
		JPanel panelStats = new JPanel(new BorderLayout());
		JLabel titulo = new JLabel("Dashboard del Gimnasio", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

		panelStats.add(titulo, BorderLayout.NORTH);
		panelStats.add(crearGraficoStats(), BorderLayout.CENTER);
		tabbedPane_2.addTab("Estadísticas", cargarIcono("img/1011579.png"), panelStats);
		panelGestiónEntrenadores.setLayout(null);

		JButton btnRefresh = new JButton("Actualizar");

		btnRefresh.addActionListener(e -> {
			panelStats.removeAll();

			JLabel t = new JLabel("Dashboard del Gimnasio", JLabel.CENTER);
			t.setFont(new Font("Segoe UI", Font.BOLD, 20));

			panelStats.add(t, BorderLayout.NORTH);
			panelStats.add(crearGraficoStats(), BorderLayout.CENTER);

			panelStats.revalidate();
			panelStats.repaint();
		});

		panelStats.add(btnRefresh, BorderLayout.SOUTH);

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

		JLabel lblPasswordEnt = new JLabel("Contraseña:");
		lblPasswordEnt.setBounds(308, 396, 82, 17);
		panelGestiónEntrenadores.add(lblPasswordEnt);

		JPasswordField txtPasswordEnt = new JPasswordField();
		txtPasswordEnt.setColumns(10);
		txtPasswordEnt.setBounds(401, 394, 114, 21);
		panelGestiónEntrenadores.add(txtPasswordEnt);

		JButton btnInsertar_1_1 = new JButton("Insertar");
		btnInsertar_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// VALIDACIONES
				if (textFieldNOMentrenador.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre es obligatorio");
					return;
				}
				if (new String(txtPasswordEnt.getPassword()).trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "La contraseña es obligatoria");
					return;
				}

				String hash = BCrypt.hashpw(new String(txtPasswordEnt.getPassword()), BCrypt.gensalt());

				Entrenador ent = new Entrenador();
				ent.setNombre(textFieldNOMentrenador.getText().trim());
				ent.setEspecialidad(textFieldEspecialidad.getText().trim());
				ent.setContraseña(hash);
				ent.setRol("ENTRENADOR");
				daoEntrenador.insertEntrenador(ent);
				modelEntrenador.setRowCount(0);
				cargarTablaEntrenador(modelEntrenador);
				txtPasswordEnt.setText("");
				JOptionPane.showMessageDialog(null, "Entrenador añadido");
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
		btnActualizar_1.setBackground(new Color(100, 149, 237));
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
				int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar este entrenador?",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION)
					return;
				daoEntrenador.deleteEntrenador(Integer.parseInt(textFieldIDentrenador.getText()));
				JOptionPane.showMessageDialog(null, "Entrenador eliminado");
				modelEntrenador.setRowCount(0);
				cargarTablaEntrenador(modelEntrenador);
			}
		});
		btnBorrar_1.setBackground(new Color(205, 92, 92));
		btnBorrar_1.setBounds(536, 430, 71, 27);
		panelGestiónEntrenadores.add(btnBorrar_1);
		JPanel panelCliente = new JPanel();
		tabbedPaneGYM.addTab("Clientes", cargarIcono("img/18272841.png"), panelCliente);

		panelCliente.setLayout(null);

		DefaultTableModel modelCliente = new DefaultTableModel();
		modelCliente.addColumn("ID");
		modelCliente.addColumn("Nombre");
		modelCliente.addColumn("Email");
		modelCliente.addColumn("Teléfono");
		modelCliente.addColumn("Fecha Alta");

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 1050, 720);
		panelCliente.add(tabbedPane_1);

		JPanel panelPerfil = new JPanel();
		tabbedPane_1.addTab("Perfil", null, panelPerfil, null);
		panelPerfil.setLayout(null);

		JScrollPane scrollPaneCliente = new JScrollPane();
		scrollPaneCliente.setBounds(265, 42, 317, 92);
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

		textFieldID = new JTextField();
		textFieldID.setBounds(413, 208, 114, 21);
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		panelPerfil.add(textFieldID);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(413, 241, 114, 21);
		textFieldNombre.setColumns(10);
		panelPerfil.add(textFieldNombre);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(413, 274, 114, 21);
		textFieldEmail.setColumns(10);
		panelPerfil.add(textFieldEmail);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(413, 307, 114, 21);
		textFieldTelefono.setColumns(10);
		panelPerfil.add(textFieldTelefono);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(413, 340, 114, 21);
		textFieldFechaAlta.setColumns(10);
		panelPerfil.add(textFieldFechaAlta);

		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(330, 212, 60, 17);
		panelPerfil.add(lblId_1);

		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(330, 245, 60, 17);
		panelPerfil.add(lblNombre_1);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(330, 278, 60, 17);
		panelPerfil.add(lblEmail_1);

		JLabel lblTelfono_1 = new JLabel("Teléfono:");
		lblTelfono_1.setBounds(330, 311, 60, 17);
		panelPerfil.add(lblTelfono_1);

		JLabel lblFechaAlta_1 = new JLabel("Fecha Alta:");
		lblFechaAlta_1.setBounds(330, 344, 75, 17);
		panelPerfil.add(lblFechaAlta_1);

		// --- ASIGNAR ENTRENADOR (panel Perfil del Cliente) ---
		JLabel lblEntrenadorAsignado = new JLabel("Tu entrenador:");
		lblEntrenadorAsignado.setBounds(330, 378, 100, 17);
		panelPerfil.add(lblEntrenadorAsignado);

		comboEntrenadorCliente = new JComboBox<>();
		comboEntrenadorCliente.setBounds(413, 375, 180, 25);
		for (Entrenador ent : daoEntrenador.selectAllEntrenadores()) {
			comboEntrenadorCliente.addItem(ent);
		}
		panelPerfil.add(comboEntrenadorCliente);

		JButton btnAsignarEntrenador = new JButton("Asignar Entrenador");
		btnAsignarEntrenador.setBackground(new Color(0, 150, 136));
		btnAsignarEntrenador.setBounds(330, 415, 160, 27);
		btnAsignarEntrenador.addActionListener(ev -> {
			if (clienteLogueado == null)
				return;
			Entrenador entSeleccionado = (Entrenador) comboEntrenadorCliente.getSelectedItem();
			clienteLogueado.setEntrenador(entSeleccionado);
			daoCliente.updateCliente(clienteLogueado);
			JOptionPane.showMessageDialog(frame, "Entrenador asignado: " + entSeleccionado.getNombre());
		});
		panelPerfil.add(btnAsignarEntrenador);

		JPanel panelRutinas = new JPanel();
		panelRutinas.setLayout(null);

		// --- TABLA RUTINAS ---
		modelRutinas = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelRutinas.addColumn("ID");
		modelRutinas.addColumn("Nombre");
		modelRutinas.addColumn("Descripción");
		modelRutinas.addColumn("Dificultad");

		JTable tableRutinas = new JTable(modelRutinas);
		JScrollPane scrollRutinas = new JScrollPane(tableRutinas);
		scrollRutinas.setBounds(10, 10, 700, 120);
		panelRutinas.add(scrollRutinas);

		// --- FILTRO GRUPO MUSCULAR ---
		JLabel lblGrupo = new JLabel("Grupo muscular:");
		lblGrupo.setBounds(10, 140, 120, 25);
		panelRutinas.add(lblGrupo);

		GrupoMuscularDAO grupoDAO = new GrupoMuscularDAO();
		EjercicioDAO ejercicioDAO = new EjercicioDAO();

		JComboBox<GrupoMuscular> comboGrupo = new JComboBox<>();
		comboGrupo.setBounds(130, 140, 180, 25);
		for (GrupoMuscular g : grupoDAO.obtenerTodos()) {
			comboGrupo.addItem(g);
		}
		panelRutinas.add(comboGrupo);

		// --- TABLA EJERCICIOS ---
		modelEjercicios = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelEjercicios.addColumn("ID");
		modelEjercicios.addColumn("Nombre");
		modelEjercicios.addColumn("Descripción");

		JTable tableEjercicios = new JTable(modelEjercicios);
		JScrollPane scrollEjercicios = new JScrollPane(tableEjercicios);
		scrollEjercicios.setBounds(10, 180, 400, 140);
		panelRutinas.add(scrollEjercicios);

		// PANEL GIF
		JPanel panelGif = new JPanel();
		panelGif.setLayout(null);
		panelGif.setBounds(430, 170, 300, 220);
		panelGif.setBackground(new Color(35,35,35));
		panelGif.setBorder(BorderFactory.createLineBorder(new Color(80,80,80), 2));

		panelRutinas.add(panelGif);

		// LABEL GIF
		JLabel lblGif = new JLabel();
		lblGif.setBounds(10, 10, 240, 190);
		lblGif.setHorizontalAlignment(JLabel.CENTER);
		lblGif.setVerticalAlignment(JLabel.CENTER);

		panelGif.add(lblGif);

		// AL SELECCIONAR EJERCICIO MUESTRA GIF
		tableEjercicios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableEjercicios.getSelectedRow();
				if (fila == -1)
					return;
				// Buscar ejercicio seleccionado
				int idEjercicio = Integer.parseInt(modelEjercicios.getValueAt(fila, 0).toString());
				List<Ejercicio> todos = ejercicioDAO.selectAll();
				for (Ejercicio ej : todos) {
					if (ej.getIdEjercicio() == idEjercicio) {
						try {
							String ruta = ej.getVideo();
							ImageIcon gif = new ImageIcon(getClass().getResource(ruta));
							lblGif.setIcon(gif);
							lblGif.revalidate();
							lblGif.repaint();
						} catch (Exception ex) {
							ex.printStackTrace();
							lblGif.setText("GIF no encontrado");
						}
						break;
					}
				}
			}
		});

		// --- FILTRAR EJERCICIOS POR GRUPO ---
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(320, 140, 80, 25);
		btnFiltrar.addActionListener(ev -> {
			GrupoMuscular grupoSel = (GrupoMuscular) comboGrupo.getSelectedItem();
			modelEjercicios.setRowCount(0);
			lblGif.setIcon(null);
			for (Ejercicio ej : ejercicioDAO.selectByGrupoMuscular(grupoSel.getId_grupo_muscular())) {
				modelEjercicios.addRow(new Object[] { ej.getIdEjercicio(), ej.getNombre(), ej.getDescripcion() });
			}
		});
		panelRutinas.add(btnFiltrar);

		// --- PANEL PROGRESO ---
		JLabel lblTituloProgreso = new JLabel("Mi Progreso:");
		lblTituloProgreso.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTituloProgreso.setBounds(10, 332, 120, 25);
		panelRutinas.add(lblTituloProgreso);

		modelProgreso = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelProgreso.addColumn("Ejercicio");
		modelProgreso.addColumn("Peso (kg)");
		modelProgreso.addColumn("Reps");
		modelProgreso.addColumn("Fecha");

		JTable tableProgreso = new JTable(modelProgreso);
		JScrollPane scrollProgreso = new JScrollPane(tableProgreso);
		scrollProgreso.setBounds(10, 364, 400, 105);
		panelRutinas.add(scrollProgreso);

		// --- GRÁFICO EVOLUCIÓN PROGRESO ---
		JLabel lblGrafico = new JLabel("Evolución (selecciona ejercicio y pulsa Ver):");
		lblGrafico.setBounds(10, 481, 300, 20);
		panelRutinas.add(lblGrafico);

		JPanel panelGrafico = new JPanel(new BorderLayout());
		panelGrafico.setBounds(10, 508, 700, 172);
		panelRutinas.add(panelGrafico);

		JButton btnVerEvolucion = new JButton("Ver evolución");
		btnVerEvolucion.setBounds(267, 479, 150, 25);
		btnVerEvolucion.setBackground(new Color(100, 149, 237));
		btnVerEvolucion.addActionListener(ev -> {

		    int filaEj = tableEjercicios.getSelectedRow();

		    if (filaEj == -1) {
		        JOptionPane.showMessageDialog(frame, "Selecciona un ejercicio");
		        return;
		    }

		    int idEjercicio = Integer.parseInt(
		            modelEjercicios.getValueAt(filaEj, 0).toString());

		    String nombreEjercicio =
		            modelEjercicios.getValueAt(filaEj, 1).toString();

		    ProgresoDAO progresoDAO = new ProgresoDAO();

		    List<Progreso> historial =
		            progresoDAO.selectProgresoByClienteYEjercicio(
		                    clienteLogueado.getIdCliente(),
		                    idEjercicio);

		    if (historial.isEmpty()) {
		        JOptionPane.showMessageDialog(frame,
		                "No hay progreso aún");
		        return;
		    }

		    DefaultCategoryDataset dataset =
		            new DefaultCategoryDataset();

		    for (Progreso p : historial) {

		        dataset.addValue(
		                p.getPesoUtilizado(),
		                "Peso",
		                p.getFecha().toString());
		    }

		    JFreeChart chart = ChartFactory.createLineChart(
		            "Evolución - " + nombreEjercicio,
		            "Fecha",
		            "Peso (kg)",
		            dataset);

		    // FONDO GENERAL
		    chart.setBackgroundPaint(new Color(30,30,30));

		    // PLOT
		    chart.getCategoryPlot().setBackgroundPaint(
		            new Color(45,45,45));

		    // GRID
		    chart.getCategoryPlot().setRangeGridlinePaint(Color.GRAY);

		    // TEXTO
		    chart.getTitle().setPaint(Color.WHITE);

		    chart.getCategoryPlot().getDomainAxis()
		            .setTickLabelPaint(Color.WHITE);

		    chart.getCategoryPlot().getRangeAxis()
		            .setTickLabelPaint(Color.WHITE);

		    chart.getCategoryPlot().getDomainAxis()
		            .setLabelPaint(Color.WHITE);

		    chart.getCategoryPlot().getRangeAxis()
		            .setLabelPaint(Color.WHITE);

		    // RENDERER
		    LineAndShapeRenderer renderer =
		            new LineAndShapeRenderer();

		    // LINEA VISIBLE
		    renderer.setSeriesLinesVisible(0, true);

		    // PUNTOS VISIBLES
		    renderer.setSeriesShapesVisible(0, true);

		    // NUMEROS ENCIMA
		    renderer.setDefaultItemLabelsVisible(true);

		    chart.getCategoryPlot().setRenderer(renderer);

		    panelGrafico.removeAll();

		    ChartPanel cp = new ChartPanel(chart);

		    panelGrafico.add(cp, BorderLayout.CENTER);

		    panelGrafico.revalidate();
		    panelGrafico.repaint();
		});
		panelRutinas.add(btnVerEvolucion);

		// LABEL PESO
		JLabel lblPeso = new JLabel("Peso (kg)");
		lblPeso.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPeso.setBounds(440, 392, 80, 20);
		panelRutinas.add(lblPeso);

		// TEXTFIELD PESO
		JTextField txtPeso = new JTextField();
		txtPeso.setBounds(430, 410, 100, 35);
		txtPeso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelRutinas.add(txtPeso);

		// LABEL REPS
		JLabel lblReps = new JLabel("Repeticiones");
		lblReps.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblReps.setBounds(570, 392, 100, 20);
		panelRutinas.add(lblReps);

		// TEXTFIELD REPS
		JTextField txtReps = new JTextField();
		txtReps.setBounds(560, 410, 100, 35);
		txtReps.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelRutinas.add(txtReps);

		JButton btnGuardarProgreso = new JButton("Guardar progreso");
		btnGuardarProgreso.setBackground(new Color(0, 150, 136));
		btnGuardarProgreso.setBounds(467, 453, 160, 35);
		btnGuardarProgreso.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnGuardarProgreso.addActionListener(ev -> {

			// VALIDACIONES
			if (clienteLogueado == null) {
				JOptionPane.showMessageDialog(frame, "No hay cliente logueado");
				return;
			}
			int filaEj = tableEjercicios.getSelectedRow();
			if (filaEj == -1) {
				JOptionPane.showMessageDialog(frame, "Selecciona un ejercicio");
				return;
			}
			if (txtPeso.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Introduce el peso");
				return;
			}
			if (txtReps.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Introduce las repeticiones");
				return;
			}

			try {
				double peso = Double.parseDouble(txtPeso.getText().trim());
				int reps = Integer.parseInt(txtReps.getText().trim());

				if (peso <= 0 || reps <= 0) {
					JOptionPane.showMessageDialog(frame, "Peso y repeticiones deben ser mayores que 0");
					return;
				}

				int idEjercicio = Integer.parseInt(modelEjercicios.getValueAt(filaEj, 0).toString());
				Ejercicio ejercicioSel = null;
				for (Ejercicio ej : ejercicioDAO.selectAll()) {
					if (ej.getIdEjercicio() == idEjercicio) {
						ejercicioSel = ej;
						break;
					}
				}

				ProgresoDAO progresoDAO = new ProgresoDAO();
				Progreso p = new Progreso();
				p.setCliente(clienteLogueado);
				p.setEjercicio(ejercicioSel);
				p.setPesoUtilizado(peso);
				p.setRepeticiones(reps);
				p.setFecha(LocalDate.now());
				progresoDAO.insertProgreso(p);

				// Recargar tabla progreso
				cargarProgreso(modelProgreso);

				txtPeso.setText("");
				txtReps.setText("");
				JOptionPane.showMessageDialog(frame, "Progreso guardado");

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Peso y repeticiones deben ser números válidos");
			}
		});
		panelRutinas.add(btnGuardarProgreso);

		tabbedPane_1.addTab("Rutinas", null, panelRutinas, null);

		frame.getContentPane().add(tabbedPaneGYM);
		tabbedPaneGYM.setVisible(false);

		JPanel panelEntrenador = new JPanel();
		tabbedPaneGYM.addTab("Entrenador", cargarIcono("img/939255.png"), panelEntrenador);

		panelStats.add(titulo, BorderLayout.NORTH);
		panelStats.add(crearGraficoStats(), BorderLayout.CENTER);
		panelEntrenador.setLayout(null);
		// TABLA CLIENTES
		modelClientesEntrenador = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		modelClientesEntrenador.addColumn("ID");
		modelClientesEntrenador.addColumn("Nombre");

		JTable tablaClientesAsignacion = new JTable(modelClientesEntrenador);

		JScrollPane scrollClientes = new JScrollPane(tablaClientesAsignacion);

		scrollClientes.setBounds(30, 30, 250, 200);

		panelEntrenador.add(scrollClientes);

		// COMBO RUTINAS
		JLabel lblRutina = new JLabel("Rutina:");
		lblRutina.setBounds(330, 40, 80, 25);

		panelEntrenador.add(lblRutina);

		JComboBox<Rutina> comboRutinas = new JComboBox<>();

		comboRutinas.setBounds(400, 40, 200, 25);

		panelEntrenador.add(comboRutinas);

		// CARGAR RUTINAS
		RutinaDAO rutinaDAO = new RutinaDAO();

		for (Rutina r : rutinaDAO.obtenerRutinas()) {

			comboRutinas.addItem(r);
		}

		// BOTON ASIGNAR
		JButton btnAsignarRutina = new JButton("Asignar Rutina");
		btnAsignarRutina.addActionListener(e -> {

			int fila = tablaClientesAsignacion.getSelectedRow();

			if (fila == -1) {
				JOptionPane.showMessageDialog(frame, "Selecciona un cliente");
				return;
			}

			int idCliente = Integer.parseInt(tablaClientesAsignacion.getValueAt(fila, 0).toString());
			Cliente cliente = daoCliente.selectClienteById(idCliente);
			Rutina rutina = (Rutina) comboRutinas.getSelectedItem();

			// Evitar duplicados comprobando por ID
			boolean yaAsignada = cliente.getRutinas().stream().anyMatch(r -> r.getIdRutina() == rutina.getIdRutina());

			if (yaAsignada) {
				JOptionPane.showMessageDialog(frame, "Este cliente ya tiene esa rutina asignada");
				return;
			}

			cliente.getRutinas().add(rutina);
			daoCliente.updateCliente(cliente);

			// ENVIAR EMAIL en hilo separado para no bloquear la UI
			new Thread(() -> {
				EmailService.enviarNotificacionRutina(cliente.getEmail(), cliente.getNombre(), rutina.getNombre());
			}).start();

			JOptionPane.showMessageDialog(frame, "Rutina '" + rutina.getNombre() + "' asignada a " + cliente.getNombre()
					+ "\nEmail enviado a: " + cliente.getEmail());
		});

		btnAsignarRutina.setBounds(400, 100, 180, 30);

		panelEntrenador.add(btnAsignarRutina);

		panelStats.add(btnRefresh, BorderLayout.SOUTH);

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
		btnIniciarSesion.setBackground(new Color(100, 149, 237));
		btnIniciarSesion.setBounds(194, 320, 115, 27);
		panelLogin.add(btnIniciarSesion);

		btnLogout.addActionListener(e -> {

			clienteLogueado = null;

			tabbedPaneGYM.setVisible(false);

			tabbedPaneLogin.setVisible(true);

			btnLogout.setVisible(false);

			textFieldUsuario.setText("");
			textFieldContraseña.setText("");
			lblUsuarioLogueado.setText("");
		});

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(194, 165, 114, 21);
		panelLogin.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JButton btnVerPassword = new JButton("");

		btnVerPassword.setIcon(cargarIcono("img/7609770.png"));

		btnVerPassword.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				textFieldContraseña.setEchoChar((char) 0);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				textFieldContraseña.setEchoChar('•');
			}
		});
		btnVerPassword.setBounds(315, 222, 28, 21);
		panelLogin.add(btnVerPassword);

		textFieldContraseña = new JPasswordField();
		textFieldContraseña.setBounds(194, 222, 114, 21);
		panelLogin.add(textFieldContraseña);
		textFieldContraseña.setColumns(10);

		JLabel lblRolLogin = new JLabel("Rol:");
		lblRolLogin.setBounds(125, 275, 60, 17);

		panelLogin.add(lblRolLogin);

		JComboBox<String> comboRolLogin = new JComboBox<>();

		comboRolLogin.addItem("CLIENTE");
		comboRolLogin.addItem("ENTRENADOR");
		comboRolLogin.addItem("ADMIN");

		comboRolLogin.setBounds(194, 275, 114, 25);

		panelLogin.add(comboRolLogin);

		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(125, 167, 60, 17);
		panelLogin.add(lblUsuario);

		JLabel lblContraseña = new JLabel("CONTRASEÑA");
		lblContraseña.setBounds(104, 224, 72, 17);
		panelLogin.add(lblContraseña);
		btnIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					String rolSeleccionadoLogin = comboRolLogin.getSelectedItem().toString();

					if (rolSeleccionadoLogin.equalsIgnoreCase("ENTRENADOR")) {

						// BUSCAR EN TABLA ENTRENADOR
						Entrenador entrenador = daoEntrenador.selectEntrenadorByNombre(textFieldUsuario.getText());

						if (entrenador == null) {
							JOptionPane.showMessageDialog(frame, "Entrenador no existe");
							return;
						}

						if (entrenador.getContraseña() == null) {
							JOptionPane.showMessageDialog(frame, "Este entrenador no tiene contraseña asignada");
							return;
						}

						boolean esCorrecta = BCrypt.checkpw(textFieldContraseña.getText(), entrenador.getContraseña());

						if (esCorrecta) {
							JOptionPane.showMessageDialog(frame, "Bienvenido " + entrenador.getNombre());
							tabbedPaneLogin.setVisible(false);
							tabbedPaneGYM.setVisible(true);
							btnLogout.setVisible(true);
							lblUsuarioLogueado.setText("Bienvenido " + entrenador.getNombre() + " (ENTRENADOR)");
							tabbedPaneGYM.setEnabledAt(0, true);
							tabbedPaneGYM.setEnabledAt(1, true);
							tabbedPaneGYM.setEnabledAt(2, true);
							tabbedPaneGYM.setSelectedIndex(2);
							tabbedPaneGYM.setEnabledAt(0, false);
							tabbedPaneGYM.setEnabledAt(1, false);
							modelClientesEntrenador.setRowCount(0);
							for (Cliente c : daoCliente.selectClientesByEntrenador(entrenador.getIdEntrenador())) {
								modelClientesEntrenador.addRow(new Object[] { c.getIdCliente(), c.getNombre() });
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Contraseña incorrecta");
						}
						return;
					}

					// BUSCAR EN TABLA CLIENTE (ADMIN o CLIENTE)
					Cliente cliente = daoCliente.selectClienteByNombre(textFieldUsuario.getText());

					if (cliente == null) {
						JOptionPane.showMessageDialog(frame, "Usuario no existe");
						return;
					}

					boolean esCorrecta = BCrypt.checkpw(textFieldContraseña.getText(), cliente.getContraseña());

					if (esCorrecta) {

						String rol = cliente.getRol();

						String rolSeleccionado = comboRolLogin.getSelectedItem().toString();

						if (!rol.equalsIgnoreCase(rolSeleccionado)) {

							JOptionPane.showMessageDialog(frame, "El usuario no pertenece al rol seleccionado");

							return;
						}

						JOptionPane.showMessageDialog(frame, "Bienvenido " + cliente.getNombre());

						tabbedPaneLogin.setVisible(false);

						tabbedPaneGYM.setVisible(true);

						btnLogout.setVisible(true);

						clienteLogueado = cliente;

						cargarPerfilCliente(modelCliente);

						lblUsuarioLogueado.setText("Bienvenido " + cliente.getNombre() + " (" + cliente.getRol() + ")");

						// REACTIVAR TODO
						tabbedPaneGYM.setEnabledAt(0, true);
						tabbedPaneGYM.setEnabledAt(1, true);
						tabbedPaneGYM.setEnabledAt(2, true);

						// ADMIN
						if (rol.equalsIgnoreCase("ADMIN")) {

							tabbedPaneGYM.setSelectedIndex(0);

							tabbedPaneGYM.setEnabledAt(1, false);
							tabbedPaneGYM.setEnabledAt(2, false);
						}

						// CLIENTE
						else if (rol.equalsIgnoreCase("CLIENTE")) {

							tabbedPaneGYM.setSelectedIndex(1);

							tabbedPaneGYM.setEnabledAt(0, false);
							tabbedPaneGYM.setEnabledAt(2, false);
							cargarProgreso(modelProgreso);
						}

						// ENTRENADOR
						else if (rol.equalsIgnoreCase("ENTRENADOR")) {

							tabbedPaneGYM.setSelectedIndex(2);

							tabbedPaneGYM.setEnabledAt(0, false);
							tabbedPaneGYM.setEnabledAt(1, false);

							modelClientesEntrenador.setRowCount(0);
							for (Cliente c : daoCliente.selectClientesByEntrenador(cliente.getIdCliente())) {
								modelClientesEntrenador.addRow(new Object[] { c.getIdCliente(), c.getNombre() });
							}
						}
					} else {

						JOptionPane.showMessageDialog(frame, "Contraseña incorrecta");
					}

				} catch (Exception ex) {

					ex.printStackTrace();

					JOptionPane.showMessageDialog(frame, "Error al iniciar sesión");
				}
			}
		});

		JPanel panelRegister = new JPanel();
		tabbedPaneLogin.addTab("Registrarse", null, panelRegister, null);
		panelRegister.setLayout(null);
		JTextField txtNombreRegistro = new JTextField();
		txtNombreRegistro.setBounds(170, 80, 150, 25);
		panelRegister.add(txtNombreRegistro);

		JTextField txtEmailRegistro = new JTextField();
		txtEmailRegistro.setBounds(170, 130, 150, 25);
		panelRegister.add(txtEmailRegistro);

		JTextField txtTelefonoRegistro = new JTextField();
		txtTelefonoRegistro.setBounds(170, 180, 150, 25);
		panelRegister.add(txtTelefonoRegistro);

		JPasswordField txtPasswordRegistro = new JPasswordField();
		txtPasswordRegistro.setBounds(170, 230, 150, 25);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(90, 280, 80, 25);
		panelRegister.add(lblRol);

		JComboBox<String> comboRolRegistro = new JComboBox<>();

		comboRolRegistro.addItem("CLIENTE");

		comboRolRegistro.setBounds(170, 280, 150, 25);

		panelRegister.add(comboRolRegistro);

		JButton btnVerPasswordRegistro = new JButton("");

		btnVerPasswordRegistro.setIcon(cargarIcono("img/7609770.png"));

		btnVerPasswordRegistro.setBounds(325, 230, 28, 25);

		btnVerPasswordRegistro.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				txtPasswordRegistro.setEchoChar((char) 0);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				txtPasswordRegistro.setEchoChar('•');
			}
		});

		panelRegister.add(btnVerPasswordRegistro);
		panelRegister.add(txtPasswordRegistro);

		JLabel lbl1 = new JLabel("Nombre");
		lbl1.setBounds(90, 80, 80, 25);
		panelRegister.add(lbl1);

		JLabel lbl2 = new JLabel("Email");
		lbl2.setBounds(90, 130, 80, 25);
		panelRegister.add(lbl2);

		JLabel lbl3 = new JLabel("Teléfono");
		lbl3.setBounds(90, 180, 80, 25);
		panelRegister.add(lbl3);

		JLabel lbl4 = new JLabel("Contraseña");
		lbl4.setBounds(90, 230, 80, 25);
		panelRegister.add(lbl4);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(new Color(100, 149, 237));
		btnRegistrarse.setBounds(190, 335, 102, 27);
		panelRegister.add(btnRegistrarse);
		btnRegistrarse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					Cliente nuevo = new Cliente();

					nuevo.setNombre(txtNombreRegistro.getText());

					nuevo.setEmail(txtEmailRegistro.getText());

					nuevo.setTelefono(Integer.parseInt(txtTelefonoRegistro.getText()));

					// FECHA AUTOMÁTICA
					nuevo.setFecha_alta(LocalDate.now());

					// PASSWORD HASH
					String hash = BCrypt.hashpw(new String(txtPasswordRegistro.getPassword()), BCrypt.gensalt());

					nuevo.setContraseña(hash);

					nuevo.setRol(comboRolRegistro.getSelectedItem().toString());

					daoCliente.insertCliente(nuevo);

					JOptionPane.showMessageDialog(frame, "Usuario registrado correctamente");

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(frame, "Error al registrarse");

					ex.printStackTrace();
				}
			}
		});
	}
}
