package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import isi.dds.tp.gestor.GestorTema;

public class MenuView1 extends JPanel {
	private static final long serialVersionUID = -1359670317490670238L;

	private GestorTema tema = GestorTema.get();
	
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

	public MenuView1() {
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
		tema.setTema(lgestionPoliza);
		tema.setTema(lgestionClientes);
		tema.setTema(lgestionParametros);
		tema.setTema(lreportes);
		tema.setTema(btnDarAltaPoliza, true, new ImageIcon("src\\main\\resources\\icons\\icon_add.png"));
		tema.setTema(btnConsultarPoliza, false, new ImageIcon("src\\main\\resources\\icons\\icon_search.png"));
		tema.setTema(btnGenerarPropuestas, false, new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
		tema.setTema(btnRegistrarPagoPoliza, true, new ImageIcon("src\\main\\resources\\icons\\icon_register.png"));
		tema.setTema(btnDarAltaCliente, true, new ImageIcon("src\\main\\resources\\icons\\icon_add.png"));
		tema.setTema(btnConsultarCliente, true, new ImageIcon("src\\main\\resources\\icons\\icon_search.png"));
		tema.setTema(btnActualizarFactores, true, new ImageIcon("src\\main\\resources\\icons\\icon_update.png"));
		tema.setTema(btnGenerarListado, false, new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
		tema.setTema(btnGenerarInforme, false, new ImageIcon("src\\main\\resources\\icons\\icon_generate.png"));
		tema.setTema(btnSalir, true);
		tema.setTema(checkCargarBase, "Habilita la recarga de la propia base de datos");
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

		constraints.insets.set(15, 30, 15, 0);
		constraints.gridy = 18;
		add(btnSalir, constraints);
	}
	
	public void addListenerBtn_AltaPoliza(ActionListener listener) {
		btnDarAltaPoliza.addActionListener(listener);
	}

	public void addListenerBtn_ConsultarPoliza(ActionListener listener) {
		btnConsultarPoliza.addActionListener(listener);
	}
	
	public void addListenerBtn_GenerarPropuesta(ActionListener listener) {
		btnGenerarPropuestas.addActionListener(listener);
	}
	
	public void addListenerBtn_RegistrarPagoPoliza(ActionListener listener) {
		btnRegistrarPagoPoliza.addActionListener(listener);
	}
	
	public void addListenerBtn_AltaCliente(ActionListener listener) {
		btnDarAltaCliente.addActionListener(listener);
	}
	
	public void addListenerBtn_GenerarListado(ActionListener listener) {
		btnGenerarListado.addActionListener(listener);
	}
	
	public void addListenerBtn_ConsultarCliente(ActionListener listener) {
		btnConsultarCliente.addActionListener(listener);
	}
	
	public void addListenerBtn_ActualizarFactores(ActionListener listener) {
		btnActualizarFactores.addActionListener(listener);
	}
	
	public void addListenerBtn_GenerarInforme(ActionListener listener) {
		btnGenerarInforme.addActionListener(listener);
	}
	
	public void addListenerBtn_Salir(ActionListener listener) {
		btnSalir.addActionListener(listener);
	}
	
	public Boolean cargarBaseDatos() {
		return checkCargarBase.isSelected();
	}	
	
	public void yaCargoBaseDatos() {
		checkCargarBase.setSelected(false);;
	}
}
