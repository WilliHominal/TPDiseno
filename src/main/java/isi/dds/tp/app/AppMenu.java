package isi.dds.tp.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.CargarBase;
import isi.dds.tp.hibernate.HibernateUtil;

@SuppressWarnings("serial")
public class AppMenu extends JPanel {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AppMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame ventana;
	private GestorTema tema = GestorTema.get();

	public String titulo = "Menú";
	
	private Boolean existeBase = true;
	
	private JLabel ltitulo = new JLabel("GRUPO 5A");
	private JLabel lintegrantes = new JLabel("Integrantes:");
	private JLabel lmilton = new JLabel("- Milton Bernhardt");
	private JLabel lexe = new JLabel("- Exequiel Farías");
	private JLabel lwilly = new JLabel("- Williams Hominal");
	private JLabel ljuan = new JLabel("- Juan Diego Paduli");
	
	private JLabel lgestionPoliza = new JLabel("Gestión de póliza:");
	private JLabel lgestionClientes = new JLabel("Gestión de clientes:");
	private JLabel lgestionParametros = new JLabel("Gestión de parametros:");
	private JLabel lreportes = new JLabel("Reportes:");
	
	private JButton btnDarAltaPoliza = new JButton("Dar alta de póliza");
	private JButton btnConsultarPoliza = new JButton("Consultar póliza");
	private JButton btnGenerarPropuestas = new JButton("Generar propuestas de renovación");
	private JButton btnRegistrarPagoPoliza = new JButton("Registrar pago de póliza");
	private JButton btnDarAltaCliente = new JButton("Dar alta de cliente");
	private JButton btnConsultarCliente = new JButton("Consultar cliente");
	private JButton btnActualizarFactores = new JButton("Actualizar factores de características");
	private JButton btnGenerarListado = new JButton("Generar listado de propuestas de renovación");
	private JButton btnGenerarInforme = new JButton("Generar informe de resultado mensual");
	private JButton btnSalir = new JButton("SALIR");
	
	private JCheckBox checkCargarBase = new JCheckBox("Recargar base");
	
	private JTextArea configurarBaseDatos = new JTextArea();
	
	private JLabel imagenLabelA, imagenLabelB, imagenLabelC, imagenLabelD;
	
	private JScrollPane scroll;
	
	public AppMenu() {	
		existeBase = conectarBaseDatos();
		
		inicializarComponentes();
		ubicarComponentes();
		comportamiento();
		
		ventana.setVisible(true);
	}
	
	private void inicializarComponentes() {
		this.ventana = new JFrame();
		ventana.pack();
		ventana.setSize(1024,600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		
		tema.panel(this);	
		
		tema.labelTituloSubrayada(ltitulo);
		tema.label(lintegrantes);
		tema.label(lmilton);
		tema.label(lexe);
		tema.label(lwilly);
		tema.label(ljuan);
		tema.label(lgestionPoliza);
		tema.label(lgestionClientes);
		tema.label(lgestionParametros);
		tema.label(lreportes);
		
		if(!existeBase) {
			ventana.setTitle("CONFIGURAR BASE DE DATOS");
			
			configurarBaseDatos = new JTextArea(
					  "Se debe configurar  la base de datos para utilizar la aplicación.\n\n"
					+ "1) Como gestor de la base de datos, se debe utilizar POSTGRESQL, y como administrador de dicha base se debe\n"
					+ "utilizar PGADMIN.\n\n"
					+ "2.A) Se debe configurar POSTGRESQL de modo que el 'user' sea \"postgres\" y la 'password' sea \"12345\". Esto se\n"
					+ "hace al instalar dicho gestor.\n\n"
					+ "2.B) En caso de tener un 'user'/'password' configurados distintos y no poder cambiarlos, se deben modificar los\n"
					+ "archivos \"hibernateValidate.cfg.xml\" y \"hibernateCreate.cfg.xml\", ubicados en (dentro del directorio del proyecto)\n"
					+ "en los paths \"/src/main/resources\" (imágen A) y sobreescribir los  campos \"hibernate.connection.password\" y\n"
					+ "\"hibernate.connection.username\" con el 'user'/'password' que se poseen configurados. (imágen B)\n\n"
					+ "3) En PGADMIN se debe crear una 'database', cuyo nombre sea \"grupo5a\". Para crear la 'database'\n"
					+ "clickear en 'Servers' -> 'PostgresSQL11' -> 'Database' -> click derecho en 'Database' -> 'Create' -> 'Database...',\n"
					+ "(imágen C) luego ahí configurar como se dice en (2)(imágen D).\n");
			configurarBaseDatos.setEditable(false);
			
			tema.textArea(configurarBaseDatos);
			
			BufferedImage imagenA, imagenB, imagenC, imagenD;
			try {
				imagenA = ImageIO.read(new File("src\\main\\resources\\images\\imageA.jpeg"));
				imagenLabelA = new JLabel(new ImageIcon(imagenA));
				imagenB = ImageIO.read(new File("src\\main\\resources\\images\\imageB.jpeg"));
				imagenLabelB = new JLabel(new ImageIcon(imagenB));
				imagenC = ImageIO.read(new File("src\\main\\resources\\images\\imageC.jpeg"));
				imagenLabelC = new JLabel(new ImageIcon(imagenC));
				imagenD = ImageIO.read(new File("src\\main\\resources\\images\\imageD.png"));
				imagenLabelD = new JLabel(new ImageIcon(imagenD));
			} catch (IOException e) { e.printStackTrace(); }
		}
		else {
			ventana.setTitle(titulo);
			
			tema.botonSubrayado(btnDarAltaPoliza, existeBase, new ImageIcon("src\\main\\resources\\icons\\icon_add.png"));
			tema.botonSubrayado(btnConsultarPoliza, false, new ImageIcon("src\\main\\resources\\icons\\icon_search.png"));
			tema.botonSubrayado(btnGenerarPropuestas, false, new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
			tema.botonSubrayado(btnRegistrarPagoPoliza, false, new ImageIcon("src\\main\\resources\\icons\\icon_register.png"));
			tema.botonSubrayado(btnDarAltaCliente, existeBase, new ImageIcon("src\\main\\resources\\icons\\icon_add.png"));
			tema.botonSubrayado(btnConsultarCliente, existeBase, new ImageIcon("src\\main\\resources\\icons\\icon_search.png"));
			tema.botonSubrayado(btnActualizarFactores, false, new ImageIcon("src\\main\\resources\\icons\\icon_update.png"));
			tema.botonSubrayado(btnGenerarListado, false, new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
			tema.botonSubrayado(btnGenerarInforme, false, new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
			tema.boton(btnSalir, existeBase);

		}
		
		tema.check(checkCargarBase, "Habilita la recarga de la propia base de datos");
	}	
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 5;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets.set(15, 5, 0, 5);
		add(ltitulo, constraints);
		constraints.insets.set(45, 5, 0, 5);
		add(lintegrantes, constraints);
		constraints.insets.set(65, 5, 0, 5);
		add(lmilton, constraints);
		constraints.insets.set(85, 5, 0, 5);
		add(lexe, constraints);
		constraints.insets.set(105, 5, 0, 5);
		add(lwilly, constraints);
		constraints.insets.set(125, 5, 0, 5);
		add(ljuan, constraints);
		
		if(!existeBase) {
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			constraints.gridheight = 5;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.insets.set(15, 5, 0, 10);
			add(configurarBaseDatos, constraints);
	
			constraints.gridy = 6;
			constraints.gridheight = 3;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets.set(0, 10, 10, 10);
			add(imagenLabelA, constraints);
			
			constraints.gridy = 9;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets.set(10, 10, 10, 10);
			add(imagenLabelB, constraints);
			
			constraints.gridy = 12;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets.set(10, 10, 10, 10);
			add(imagenLabelC, constraints);
			
			constraints.gridy = 15;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets.set(10, 10, 0, 10);
			add(imagenLabelD, constraints);
			
			scroll = new JScrollPane(this);
			scroll.getVerticalScrollBar().setUnitIncrement(30);
			ventana.add(scroll, BorderLayout.CENTER);
		}
		else {
			ventana.setContentPane(this);
			
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.insets.set(5, 0, 0, 0);
			add(lgestionPoliza, constraints);
			constraints.gridy = 1;
			constraints.gridx = 2;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets.set(0, 30, 5, 0);
			add(btnDarAltaPoliza, constraints);
			constraints.gridy = 2;
			constraints.insets.set(5, 30, 5, 0);
			add(btnConsultarPoliza, constraints);
			constraints.gridy = 3;
			add(btnGenerarPropuestas, constraints);
			constraints.gridy = 4;
			constraints.insets.set(5, 30, 10, 0);
			add(btnRegistrarPagoPoliza, constraints);
			
			constraints.gridx = 1;
			constraints.gridy = 5;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.insets.set(5, 0, 0, 0);
			add(lgestionClientes, constraints);
			constraints.gridx = 2;
			constraints.gridy = 6;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets.set(0, 30, 5, 0);
			add(btnDarAltaCliente, constraints);
			constraints.gridy = 7;
			constraints.insets.set(5, 30, 10, 0);
			add(btnConsultarCliente, constraints);
			
			constraints.gridx = 1;
			constraints.gridy = 8;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.insets.set(5, 210, 0, 0);
			add(lgestionParametros, constraints);
			constraints.gridx = 2;
			constraints.gridy = 9;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets.set(0, 30, 10, 0);
			add(btnActualizarFactores, constraints);
			
			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.insets.set(5, 0, 0, 0);
			add(lreportes, constraints);
			constraints.gridx = 2;
			constraints.gridy = 11;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets.set(0, 30, 5, 50);
			add(btnGenerarListado, constraints);
			constraints.gridy = 12;
			constraints.insets.set(3, 30, 10, 0);
			add(btnGenerarInforme, constraints);
			
			constraints.gridx = 1;
			constraints.gridy = 13;
			constraints.gridwidth = 2;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets.set(15, 25, 5, 0);
			add(checkCargarBase, constraints);
		}

		constraints.insets.set(15, 30, 15, 0);
		constraints.gridy = 18;
		add(btnSalir, constraints);
	}
		
	
	private void comportamiento() {
		
		btnDarAltaPoliza.addActionListener(a -> {
			darAltaPoliza();
		});
		
		btnConsultarPoliza.addActionListener(a -> {
			JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		
		btnGenerarPropuestas.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		
		btnRegistrarPagoPoliza.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		
		btnDarAltaCliente.addActionListener(a -> {
			altaCliente();
		});
	
		btnConsultarCliente.addActionListener(a -> {
			consultarCliente();
		});
	
		btnActualizarFactores.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
	
		btnGenerarListado.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
	
		btnGenerarInforme.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		
		btnSalir.addActionListener(a -> {
			try {			
				ventana.setVisible(false);	
				HibernateUtil.shutdown();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		ventana.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				ventana.setVisible(false);
				HibernateUtil.shutdown();
		    }
		});
		
	}

	private Boolean conectarBaseDatos() {
		if(HibernateUtil.getSessionFactoryValidate() != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void recargarBaseDatos() {
		HibernateUtil.shutdown();
		CargarBase.load();
	}
	
	private void darAltaPoliza() {
		try {			
			if(checkCargarBase.isSelected()) {
				recargarBaseDatos();
				checkCargarBase.setSelected(false);
			}
			new CU01_AltaPoliza1(ventana);
		}catch(Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void consultarCliente() {
		try {			
			if(checkCargarBase.isSelected()) {
				recargarBaseDatos();
				checkCargarBase.setSelected(false);
			}
			new CU17_BuscarCliente(ventana);
		}catch(Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void altaCliente() {
		try {			
			if(checkCargarBase.isSelected()) {
				recargarBaseDatos();
				checkCargarBase.setSelected(false);
			}
			new CU04_AltaCliente(ventana);
		}catch(Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
