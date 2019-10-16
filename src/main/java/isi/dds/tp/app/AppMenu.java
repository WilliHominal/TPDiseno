package isi.dds.tp.app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.border.LineBorder;

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
	
	private Boolean existeBase = true;
		
	private Color colorBoton = new Color(0, 128, 128);
	private Color colorFondoPantalla = new Color(204,204,204);
	private Color colorFondoTexto = new Color(204, 204, 153); 
	private Color borde = Color.BLACK;
	private Color colorLetra = Color.BLACK;
	private Font letra = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	private Color colorErroneo = new Color(255,102,102);
	
	private Object[] tema = {colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetra, letra, colorErroneo};
	
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
		setSize(1024,600);
		
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
		btnConsultarPoliza.setEnabled(false);
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
		this.ventana = new JFrame();
		ventana.setContentPane(this);
		ventana.setTitle("Menú");
		ventana.pack();
		ventana.setSize(1024,600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
		
		colorBoton = (Color) tema[0];
		colorFondoPantalla = (Color) tema[1];
		colorLetra = (Color) tema[4];
		letra = (Font) tema[5];
		
		setBounds(0,0,1024,600);
		setFont(letra);
		setForeground(colorLetra);
		setBackground(colorFondoPantalla);	
		

		if(!existeBase) {
			configurarBaseDatos.setBackground(colorFondoPantalla);
			configurarBaseDatos.setFont(letra);
			configurarBaseDatos.setForeground(colorErroneo.darker());
		}
		
		ltitulo.setFont(new Font("Open Sans", Font.BOLD, 14));
		
		Font font2 = lgestionClientes.getFont();
		Map<TextAttribute, Object> titulo = new HashMap<>(font2.getAttributes());
		titulo.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		ltitulo.setFont(font2.deriveFont(titulo));
		
		
		ltitulo.setForeground(colorLetra);
		lintegrantes.setFont(letra);
		lintegrantes.setForeground(colorLetra);
		lmilton.setFont(letra);
		lmilton.setForeground(colorLetra);
		lexe.setFont(letra);
		lexe.setForeground(colorLetra);
		lwilly.setFont(letra);
		lwilly.setForeground(colorLetra);
		ljuan.setFont(letra);
		ljuan.setForeground(colorLetra);
		
		lgestionPoliza.setFont(letra);
		lgestionPoliza.setForeground(colorLetra);
		lgestionClientes.setFont(letra);
		lgestionClientes.setForeground(colorLetra);
		lgestionParametros.setFont(letra);
		lgestionParametros.setForeground(colorLetra);
		lreportes.setFont(letra);
		lreportes.setForeground(colorLetra);
		
		Font font = lgestionClientes.getFont();
		Map<TextAttribute, Object> subrayado = new HashMap<>(font.getAttributes());
		subrayado.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);

		btnDarAltaPoliza.setBackground(colorFondoPantalla);
		btnDarAltaPoliza.setFont(font.deriveFont(subrayado));
		btnDarAltaPoliza.setForeground(colorLetra);
		btnDarAltaPoliza.setBorder(new LineBorder(colorFondoPantalla));
		btnConsultarPoliza.setBackground(colorFondoPantalla);
		btnConsultarPoliza.setFont(font.deriveFont(subrayado));
		btnConsultarPoliza.setForeground(colorLetra);
		btnConsultarPoliza.setBorder(new LineBorder(colorFondoPantalla));
		btnGenerarPropuestas.setBackground(colorFondoPantalla);
		btnGenerarPropuestas.setFont(font.deriveFont(subrayado));
		btnGenerarPropuestas.setForeground(colorLetra);
		btnGenerarPropuestas.setBorder(new LineBorder(colorFondoPantalla));
		btnRegistrarPagoPoliza.setBackground(colorFondoPantalla);
		btnRegistrarPagoPoliza.setFont(font.deriveFont(subrayado));
		btnRegistrarPagoPoliza.setForeground(colorLetra);
		btnRegistrarPagoPoliza.setBorder(new LineBorder(colorFondoPantalla));
		btnDarAltaCliente.setBackground(colorFondoPantalla);
		btnDarAltaCliente.setFont(font.deriveFont(subrayado));
		btnDarAltaCliente.setForeground(colorLetra);
		btnDarAltaCliente.setBorder(new LineBorder(colorFondoPantalla));
		btnConsultarCliente.setBackground(colorFondoPantalla);
		btnConsultarCliente.setFont(font.deriveFont(subrayado));
		btnConsultarCliente.setForeground(colorLetra);
		btnConsultarCliente.setBorder(new LineBorder(colorFondoPantalla));
		btnActualizarFactores.setBackground(colorFondoPantalla);
		btnActualizarFactores.setFont(font.deriveFont(subrayado));
		btnActualizarFactores.setForeground(colorLetra);
		btnActualizarFactores.setBorder(new LineBorder(colorFondoPantalla));
		btnGenerarListado.setBackground(colorFondoPantalla);
		btnGenerarListado.setFont(font.deriveFont(subrayado));
		btnGenerarListado.setForeground(colorLetra);
		btnGenerarListado.setBorder(new LineBorder(colorFondoPantalla));
		btnGenerarInforme.setBackground(colorFondoPantalla);
		btnGenerarInforme.setFont(font.deriveFont(subrayado));
		btnGenerarInforme.setForeground(colorLetra);
		btnGenerarInforme.setBorder(new LineBorder(colorFondoPantalla));
		
		btnSalir.setBackground(colorBoton);
		btnSalir.setFont(letra);
		btnSalir.setForeground(colorLetra);
		
		checkCargarBase.setBackground(colorFondoPantalla);
		checkCargarBase.setFont(letra);
		checkCargarBase.setForeground(colorLetra);
		checkCargarBase.setToolTipText("Habilita la recarga de la propia base de datos");
		
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
			    JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
		new CU01_AP1(ventana, tema);
	}
}
