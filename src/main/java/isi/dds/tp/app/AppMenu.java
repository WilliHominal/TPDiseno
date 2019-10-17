package isi.dds.tp.app;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

	private Boolean existeBase = true;
	
	private JLabel ltitulo = new JLabel("GRUPO 5A");
	private JLabel lintegrantes = new JLabel("Integrantes:");
	private JLabel lmilton = new JLabel("- Milton Bernhardt");
	private JLabel lexe = new JLabel("- Exequiel Farías");
	private JLabel lwilly = new JLabel("- Williams Hominal");
	private JLabel ljuan = new JLabel("- Juan Pablo Padulli");
	
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
	
	public AppMenu() {	
		
		if(!conectarBaseDatos()) {
			existeBase = false;
		}
		
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		
		comportamiento();
		
		ventana.setVisible(true);
	}
	
	private void inicializarComponentes() {
		this.ventana = new JFrame();
		ventana.setContentPane(this);
		ventana.setTitle("Menú");
		ventana.pack();
		ventana.setSize(1024,600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		
		btnDarAltaPoliza.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_add.png"));
		btnConsultarPoliza.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_search.png"));
		btnGenerarPropuestas.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
		btnRegistrarPagoPoliza.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_register.png"));
		btnDarAltaCliente.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_add.png"));
		btnConsultarCliente.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_search.png"));
		btnActualizarFactores.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_update.png"));
		btnGenerarListado.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
		btnGenerarInforme.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
		/*
		btnConsultarPoliza.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnGenerarPropuestas.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnRegistrarPagoPoliza.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnDarAltaCliente.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnConsultarCliente.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnActualizarFactores.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnGenerarListado.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));
		btnGenerarInforme.setIcon(new ImageIcon("src\\main\\resources\\icons\\icon_unavailable.png"));*/
		
		btnDarAltaPoliza.setEnabled(existeBase);
		btnConsultarPoliza.setEnabled(existeBase);
		btnGenerarPropuestas.setEnabled(false);
		btnRegistrarPagoPoliza.setEnabled(false);
		btnDarAltaCliente.setEnabled(false);
		btnConsultarCliente.setEnabled(false);
		btnActualizarFactores.setEnabled(false);
		btnGenerarListado.setEnabled(false);
		btnGenerarInforme.setEnabled(false);
		checkCargarBase.setEnabled(existeBase);
		
		if(!existeBase) {
			configurarBaseDatos = new JTextArea(
					  "Se  debe  configurar  la base de datos\n"
					+ "para utilizar la aplicación.\n"
					+ "Como gestor de la base de  datos, se\n"
					+ "debe utilizar  POSTGRESQL, y como\n"
					+ "administrador de dicha  base  se debe\n"
					+ "utilizar PGADMIN. Se debe configurar\n"
					+ "PostgreSQL de modo que el user sea\n"
					+ "\"postgres\" y la password sea \"12345\".\n"
					+ "Luego  se  debe  crear  una  database\n"
					+ "cuyo nombre sea \"grupo5a\".\n"
					+ "En  caso  de  tener  un user/password\n"
					+ "distintos  y  no  poder  cambiarlos se \n"
					+ "deben modificar los archivos \"hibernate\n"
					+ "Validate.cfg.xml\" y \"hibernateCreate.\n"
					+ "cfg.xml\",ubicados en los paths \"/src/main/\n"
					+ "resources\" y sobreescribir los  campos\n"
					+ "\"hibernate.connection.password\"  y\n"
					+ "\"hibernate.connection.username\"  con\n"
					+ "el user/password que se posean\n"
					+ "configurados.");
			configurarBaseDatos.setEditable(false);
		}
		
	}

	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 5;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets.set(5, 5, 0, 5);
		add(ltitulo, constraints);
		constraints.insets.set(30, 5, 0, 5);
		add(lintegrantes, constraints);
		constraints.insets.set(50, 5, 0, 5);
		add(lmilton, constraints);
		constraints.insets.set(70, 5, 0, 5);
		add(lexe, constraints);
		constraints.insets.set(90, 5, 0, 5);
		add(lwilly, constraints);
		constraints.insets.set(110, 5, 0, 5);
		add(ljuan, constraints);
		
		if(!existeBase) {
			constraints.gridx = 3;
			constraints.gridy = 4;
			constraints.gridwidth = 1;
			constraints.gridheight = 13;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.insets.set(5, 5, 0, 5);
			add(configurarBaseDatos, constraints);
		}


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
		
		constraints.gridy = 14;
		add(btnSalir, constraints);
	}
		
	private void inicializarTema() {
		tema.panel(this);		

		if(!existeBase) {
			tema.textArea(configurarBaseDatos);
		}

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
		
		tema.botonSubrayado(btnDarAltaPoliza);
		tema.botonSubrayado(btnConsultarPoliza);
		tema.botonSubrayado(btnGenerarPropuestas);
		tema.botonSubrayado(btnRegistrarPagoPoliza);
		tema.botonSubrayado(btnDarAltaCliente);
		tema.botonSubrayado(btnConsultarCliente);
		tema.botonSubrayado(btnActualizarFactores);
		tema.botonSubrayado(btnGenerarListado);
		tema.botonSubrayado(btnGenerarInforme);
		tema.boton(btnSalir);
		
		tema.check(checkCargarBase, "Habilita la recarga de la propia base de datos");
	}

	private void comportamiento() {
		
		btnDarAltaPoliza.addActionListener(a -> {
			try {		
				if(checkCargarBase.isSelected()) {
					recargarBaseDatos();
					checkCargarBase.setSelected(false);
				}
				darAltaPoliza();
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnConsultarPoliza.addActionListener(a -> {
			try {			
				if(checkCargarBase.isSelected()) {
					recargarBaseDatos();
					checkCargarBase.setSelected(false);
				}
				consultarCliente();
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnGenerarPropuestas.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		
		btnRegistrarPagoPoliza.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		
		btnDarAltaCliente.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
	
		btnConsultarCliente.addActionListener(a -> {
		    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
		
	}
	
	private void consultarCliente() {
		new CU17_BuscarCliente(ventana);
	}

	public Boolean conectarBaseDatos() {
		if(HibernateUtil.getSessionFactoryValidate() != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void recargarBaseDatos() {
		HibernateUtil.shutdown();
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF); 
		CargarBase.load();
	}
	
		
	public void darAltaPoliza() {
		new CU01_AP1(ventana);
	}
}
