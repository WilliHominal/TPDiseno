package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import isi.dds.tp.gestor.GestorTema;

public class MenuView2 extends JPanel{
	private static final long serialVersionUID = -8973156545928701791L;
	
	private GestorTema tema = GestorTema.get();
	
	private JLabel ltitulo = new JLabel("GRUPO 5A");
	private JLabel lintegrantes = new JLabel("Integrantes:");
	private JLabel lmilton = new JLabel("- Milton Bernhardt");
	private JLabel lexe = new JLabel("- Exequiel Farías");
	private JLabel lwilly = new JLabel("- Williams Hominal");
	private JLabel ljuan = new JLabel("- Juan Diego Paduli");
	
	private JButton btnSalir = new JButton("SALIR");
	
	private JTextArea configurarBaseDatos = new JTextArea();
	
	private JLabel imagenLabelA, imagenLabelB, imagenLabelC, imagenLabelD;
	
	public MenuView2() {
		inicializarComponentes();
		ubicarComponentes();
	}
	
	private void inicializarComponentes() {
		tema.setTema(this);	
		
		tema.setTemaTitulo(ltitulo);
		tema.setTema(lintegrantes);
		tema.setTema(lmilton);
		tema.setTema(lexe);
		tema.setTema(lwilly);
		tema.setTema(ljuan);
		
		tema.setTema(btnSalir, true);

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
		
		tema.setTema(configurarBaseDatos);
		
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
		
		constraints.insets.set(15, 30, 15, 0);
		constraints.gridy = 18;
		add(btnSalir, constraints);
	}
	
	public void addSalirListener(ActionListener listener) {
		btnSalir.addActionListener(listener);
	}
}

