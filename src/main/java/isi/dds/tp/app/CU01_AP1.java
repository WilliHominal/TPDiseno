package isi.dds.tp.app;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import isi.dds.tp.app.CU01_DH.DeclararHijoAbierto;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.*;


@SuppressWarnings("serial")
public class CU01_AP1 extends JPanel {
		
	private JFrame ventana;
	private Object[] tema;
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetraBloqueado, colorLetra, colorErroneo;
	private Font letra;
	
	public Poliza poliza = new Poliza();
	public Cliente cliente = null;
	
	private Boolean estaDeclarandoHijo = false;
	private Boolean mouseActivo = false;
	
	private HijoDeclarado hijo = null;
	
	
	private JLabel lnumeroCliente = new JLabel("N\u00famero cliente:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel ldocumento = new JLabel("Documento:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombres = new JLabel("Nombres:");
	private JLabel lcalle = new JLabel("Calle:");
	private JLabel lnumeroDom = new JLabel("N\u00famero domicilio:");
	private JLabel ldepartamento = new JLabel("  Departamento:");
	private JLabel lprovincia = new JLabel("Provincia*:");
	private JLabel lciudad = new JLabel("Ciudad*:");
	private JLabel lmarca = new JLabel("Marca veh\u00edculo*:");
	private JLabel lmodelo = new JLabel("Modelo veh\u00edculo:*");
	private JLabel lanio = new JLabel("A\u00f1o modelo:*");
	private JLabel lmotor = new JLabel("Motor*:");
	private JLabel lchasis = new JLabel("Chasis*:");
	private JLabel lpatente = new JLabel("Patente*:");
	private JLabel lsumaAseg = new JLabel("Suma asegurada:");
	private JLabel lmoneda = new JLabel("$");
	private JLabel lkm = new JLabel("Km realizados por a\u00f1o:");
	private JLabel lsiniestros = new JLabel("N\u00famero de siniestros en el \u00faltimo año*:");
	private JLabel lgarage = new JLabel("¿Se guarda en garage?");
	private JLabel lalarma = new JLabel("¿Tiene alarma?");
	private JLabel lrastreo = new JLabel("¿Posee dispositivo de rastreo vehicular?");
	private JLabel ltuercas = new JLabel("¿Posee tuercas antirrobo?");
	private JLabel lcantidadHijos = new JLabel("Cantidad de hijos entre 18 y 30 a\u00f1os:");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatios)");
		
	private JTextField campoNumeroCliente = new JTextField(10);
	private JTextField campoTipoDocumento = new JTextField(15);
	private JTextField campoNumeroDocumento = new JTextField(8);
	private JTextField campoApellido = new JTextField(15);
	private JTextField campoNombres = new JTextField(15);
	private JTextField campoCalle = new JTextField(15);
	private JTextField campoNumeroDomicilio = new JTextField(8);
	private JTextField campoDepartamento = new JTextField(3);
	private JTextField campoMotor = new JTextField(15);
	private JTextField campoChasis = new JTextField(8);
	private JTextField campoPatente = new JTextField(7);
	private JTextField campoSumaAsegurada = new JTextField(15);
	private JTextField campoNumerosSiniestros = new JTextField(10); 
	
	private JButton btnBuscarCliente = new JButton("BUSCAR CLIENTES");
	private JButton btnAltaCliente = new JButton("DAR ALTA CLIENTES");
	private JButton btnAgregarHijo = new JButton("Agregar datos hijo");
	private JButton btnQuitarHijo = new JButton("Quitar datos hijo");
	private JButton btnConfirmarDatos = new JButton("Confirmar datos");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JComboBox<Provincia> seleccionProvincia = new JComboBox<Provincia>();
	private JComboBox<Ciudad> seleccionCiudad = new JComboBox<Ciudad>();
	private JComboBox<Marca> seleccionMarca = new JComboBox<Marca>();
	private JComboBox<Modelo> seleccionModelo = new JComboBox<Modelo>();
	private JComboBox<AnioModelo> seleccionAnio = new JComboBox<AnioModelo>();
	private JComboBox<String> seleccionKm = new JComboBox<String>();

	private ButtonGroup garage = new ButtonGroup(), alarma = new ButtonGroup(), rastreo = new ButtonGroup(), tuercas = new ButtonGroup(); 
	private JRadioButton rbtnGarageSi = new JRadioButton("SI");
	private JRadioButton rbtnGarageNo = new JRadioButton("NO");
	private JRadioButton rbtnAlarmaSi = new JRadioButton("SI");
	private JRadioButton rbtnAlarmaNo = new JRadioButton("NO");
	private JRadioButton rbtnRastreoSi = new JRadioButton("SI");
	private JRadioButton rbtnRastreoNo = new JRadioButton("NO");
	private JRadioButton rbtnTuercasSi = new JRadioButton("SI");
	private JRadioButton rbtnTuercasNo = new JRadioButton("NO");
	
	private JTable tablaHijos = new JTable(6, 4);
	private JScrollPane tablaHijosScroll = new JScrollPane(tablaHijos,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private Object[][] datosTabla = {{""},{""},{""},{""}};

	
	public CU01_AP1(JFrame ventana, Object[] tema) {
		this.ventana = ventana;
		this.tema = tema;
			
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		
		comportamientoMouse();
		comportamientoBotones();
		comportamientoComboBox();		
		comportamientoCampos();
		ventana.setBounds(0,0,1024,600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: INGRESAR DATOS");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}

	private void ubicarComponentes() {
		
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//FILA 1
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 5, 5, 15);
		add(btnBuscarCliente, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnumeroCliente, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(campoNumeroCliente, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ltipoDocumento, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(campoTipoDocumento, constraints);
		
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ldocumento, constraints);
		
		constraints.gridx = 7;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(campoNumeroDocumento, constraints);
		
		//FILA 2
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 5, 5, 15);
		add(btnAltaCliente, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lapellido, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(campoApellido, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnombres, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(campoNombres, constraints);
		
		//FILA 3
		constraints.gridy = 2;
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lcalle, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(campoCalle, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnumeroDom, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(campoNumeroDomicilio, constraints);
		
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ldepartamento, constraints);
		
		constraints.gridx = 7;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(campoDepartamento, constraints);
		
		//FILA 4
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.gridwidth = 15;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 0, 5, 0);
		add(new JLabel("___________________________________________________________________________________________________________________________________"), constraints);
		
		//FILA 5
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 5, 5, 5);
		add(lprovincia, constraints);
		constraints.insets.set(5, 75, 5, 5);
		add(seleccionProvincia, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lciudad, constraints);
		constraints.insets.set(5, 355, 5, 5);
		add(seleccionCiudad, constraints);
		
		//FILA 6
		constraints.gridy = 5;
		constraints.insets.set(5, 5, 5, 5);
		add(lmarca, constraints);
		constraints.insets.set(5, 111, 5, 5);
		add(seleccionMarca, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lmodelo, constraints);
		constraints.insets.set(5, 410, 5, 5);
		add(seleccionModelo, constraints);
		constraints.insets.set(5, 575, 5, 5);
		add(lanio, constraints);
		constraints.insets.set(5, 664, 5, 5);
		add(seleccionAnio, constraints);

		//FILA 7
		constraints.gridy = 6;
		constraints.insets.set(5, 5, 5, 5);
		add(lmotor, constraints);
		constraints.insets.set(5, 60, 5, 5);
		add(campoMotor, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lchasis, constraints);
		constraints.insets.set(5, 349, 5, 5);
		add(campoChasis, constraints);
		constraints.insets.set(5, 575, 5, 5);
		add(lpatente, constraints);
		constraints.insets.set(5, 638, 5, 5);
		add(campoPatente, constraints);
		
		//FILA 8
		constraints.gridy = 7;
		constraints.insets.set(5, 5, 5, 5);
		add(lsumaAseg, constraints);
		constraints.insets.set(5, 113, 5, 5);
		add(campoSumaAsegurada, constraints);
		constraints.insets.set(5, 298, 5, 5);
		add(lmoneda, constraints);

		//FILA 9
		constraints.gridy = 8;
		constraints.insets.set(5, 5, 5, 5);
		add(lkm, constraints);
		constraints.insets.set(5, 145, 5, 5);
		add(seleccionKm, constraints);
		
		//FILA 10
		constraints.gridy = 9;
		constraints.insets.set(5, 5, 5, 5);
		add(lsiniestros, constraints);
		constraints.insets.set(5, 247, 5, 5);
		add(campoNumerosSiniestros, constraints);
	
		//FILA 11
		constraints.gridy = 10;
		constraints.insets.set(5, 5, 5, 5);
		add(lgarage, constraints);
		constraints.insets.set(5, 140, 0, 5);
		add(rbtnGarageSi, constraints);
		constraints.insets.set(5, 175, 0, 5);
		add(rbtnGarageNo, constraints);
		
		constraints.insets.set(5, 250, 0, 5);
		add(lalarma, constraints);
		constraints.insets.set(5, 340, 0, 5);
		add(rbtnAlarmaSi, constraints);
		constraints.insets.set(5, 375, 0, 5);
		add(rbtnAlarmaNo, constraints);
		
		//FILA 11
		constraints.gridy = 11;
		constraints.insets.set(2, 5, 5, 5);
		add(lrastreo, constraints);
		constraints.insets.set(2, 245, 3, 5);
		add(rbtnRastreoSi, constraints);
		constraints.insets.set(2, 280, 3, 5);
		add(rbtnRastreoNo, constraints);
		
		constraints.insets.set(2, 355, 5, 5);
		add(ltuercas, constraints);
		constraints.insets.set(2, 515, 3, 5);
		add(rbtnTuercasSi, constraints);
		constraints.insets.set(2, 550, 3, 5);
		add(rbtnTuercasNo, constraints);
		
		//FILA 12
		constraints.gridy = 12;
		constraints.insets.set(5, 5, 2, 5);
		add(lcantidadHijos, constraints);
		
		//FILA 13
		constraints.gridy = 13;
		constraints.insets.set(3, 5, 2, 5);
		add(tablaHijosScroll, constraints);
		
		constraints.insets.set(0, 425, 40, 5);
		add(btnAgregarHijo, constraints);
		
		constraints.insets.set(40, 425, 2, 5);
		add(btnQuitarHijo, constraints);
		
		
		//FILA 14
		constraints.gridy = 14;
		constraints.insets.set(3, 5, 0, 5);
		add(ldatosObligatorios, constraints);
		
		constraints.insets.set(0, 655, 0, 5);
		add(btnConfirmarDatos, constraints);
		
		constraints.insets.set(0, 830, 0, 5);
		add(btnCancelar, constraints);

	}
		
	private void inicializarTema() {	
		
		colorBoton = (Color) tema[0];
		colorFondoPantalla = (Color) tema[1];
		colorFondoTexto = (Color)tema[2];
		borde = (Color)tema[3];
		colorLetraBloqueado = (Color) tema[4];
		colorLetra = (Color) tema[5];
		letra = (Font) tema[6];
		colorErroneo = (Color) tema[7];
		
		setBounds(0,0,1024,600);
		setFont(letra);
		setBackground(colorFondoPantalla);
		
		UIManager.put( "ComboBox.disabledBackground", colorFondoPantalla );
		UIManager.put( "ComboBox.disabledForeground", colorLetraBloqueado);
		
		lnumeroCliente.setFont(letra);
		ltipoDocumento.setFont(letra);
		ldocumento.setFont(letra);
		lapellido .setFont(letra);
		lnombres.setFont(letra);
		lcalle.setFont(letra);
		lnumeroDom.setFont(letra);
		ldepartamento.setFont(letra);
		lprovincia.setFont(letra);
		lciudad.setFont(letra);
		lmarca.setFont(letra);
		lmodelo.setFont(letra);
		lanio.setFont(letra);
		lmotor.setFont(letra);
		lchasis.setFont(letra);
		lpatente.setFont(letra);
		lsumaAseg.setFont(letra);
		lmoneda.setFont(letra);
		lkm.setFont(letra);
		lsiniestros.setFont(letra);
		lgarage.setFont(letra);
		lalarma.setFont(letra);
		lrastreo.setFont(letra);
		ltuercas.setFont(letra);
		lcantidadHijos.setFont(letra);
		ldatosObligatorios.setFont(new Font("Open Sans", Font.ITALIC, 9));
			
		campoNumeroCliente.setFont(letra);
		campoNumeroCliente.setBackground(colorFondoTexto);
		campoNumeroCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNumeroCliente.setDisabledTextColor(colorLetraBloqueado);
		campoTipoDocumento.setFont(letra);
		campoTipoDocumento.setBackground(colorFondoTexto);
		campoTipoDocumento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoTipoDocumento.setDisabledTextColor(colorLetraBloqueado);
		campoNumeroDocumento.setFont(letra);
		campoNumeroDocumento.setBackground(colorFondoTexto);
		campoNumeroDocumento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNumeroDocumento.setDisabledTextColor(colorLetraBloqueado);
		campoApellido.setFont(letra);
		campoApellido.setBackground(colorFondoTexto);
		campoApellido.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoApellido.setDisabledTextColor(colorLetraBloqueado);
		campoNombres.setFont(letra);
		campoNombres.setBackground(colorFondoTexto);
		campoNombres.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNombres.setDisabledTextColor(colorLetraBloqueado);
		campoCalle.setFont(letra);
		campoCalle.setBackground(colorFondoTexto);
		campoCalle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoCalle.setDisabledTextColor(colorLetraBloqueado);
		campoNumeroDomicilio.setFont(letra);
		campoNumeroDomicilio.setBackground(colorFondoTexto);
		campoNumeroDomicilio.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNumeroDomicilio.setDisabledTextColor(colorLetraBloqueado);
		campoDepartamento.setFont(letra);
		campoDepartamento.setBackground(colorFondoTexto);
		campoDepartamento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoDepartamento.setDisabledTextColor(colorLetraBloqueado);
		campoSumaAsegurada.setFont(letra);
		campoSumaAsegurada.setBackground(colorFondoPantalla);
		campoSumaAsegurada.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoSumaAsegurada.setDisabledTextColor(colorLetraBloqueado);
		campoNumerosSiniestros.setFont(letra);
		campoNumerosSiniestros.setBackground(colorFondoPantalla);
		campoNumerosSiniestros.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNumerosSiniestros.setDisabledTextColor(colorLetraBloqueado);
		
		campoMotor.setFont(letra);
		campoMotor.setBackground(colorFondoPantalla);
		campoMotor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoChasis.setFont(letra);
		campoChasis.setBackground(colorFondoPantalla);
		campoChasis.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoPatente.setFont(letra);
		campoPatente.setBackground(colorFondoPantalla);
		campoPatente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		btnBuscarCliente.setBackground(colorBoton);
		btnBuscarCliente.setFont(letra);
		btnAltaCliente.setBackground(colorBoton);
		btnAltaCliente.setFont(letra);
		btnAgregarHijo.setBackground(colorBoton);
		btnAgregarHijo.setFont(letra);
		btnQuitarHijo.setBackground(colorBoton);
		btnQuitarHijo.setFont(letra);
		btnConfirmarDatos.setBackground(colorBoton);
		btnConfirmarDatos.setFont(letra);
		btnCancelar.setBackground(colorBoton);
		btnCancelar.setFont(letra);
		
		seleccionProvincia.setBackground(colorFondoTexto);
		seleccionProvincia.setFont(letra);
		seleccionProvincia.setForeground(colorLetra);
		seleccionCiudad.setBackground(colorFondoTexto);
		seleccionCiudad.setFont(letra);
		seleccionCiudad.setForeground(colorLetra);
		seleccionMarca.setBackground(colorFondoTexto);
		seleccionMarca.setFont(letra);
		seleccionMarca.setForeground(colorLetra);
		seleccionModelo.setBackground(colorFondoTexto);
		seleccionModelo.setFont(letra);
		seleccionModelo.setForeground(colorLetra);
		seleccionAnio.setBackground(colorFondoTexto);
		seleccionAnio.setFont(letra);
		seleccionAnio.setForeground(colorLetra);
		seleccionKm.setBackground(colorFondoTexto);
		seleccionKm.setFont(letra);
		seleccionKm.setForeground(colorLetra);
		
		rbtnGarageSi.setBackground(colorFondoPantalla);
		rbtnGarageSi.setFont(letra);
		rbtnGarageNo.setBackground(colorFondoPantalla);
		rbtnGarageNo.setFont(letra);
		rbtnAlarmaSi.setBackground(colorFondoPantalla);
		rbtnAlarmaSi.setFont(letra);
		rbtnAlarmaNo.setBackground(colorFondoPantalla);
		rbtnAlarmaNo.setFont(letra);
		rbtnRastreoSi.setBackground(colorFondoPantalla);
		rbtnRastreoSi.setFont(letra);
		rbtnRastreoNo.setBackground(colorFondoPantalla);
		rbtnRastreoNo.setFont(letra);
		rbtnTuercasSi.setBackground(colorFondoPantalla);
		rbtnTuercasSi.setFont(letra);
		rbtnTuercasNo.setBackground(colorFondoPantalla);
		rbtnTuercasNo.setFont(letra);
		
		tablaHijos.setBackground(colorFondoTexto);
		tablaHijos.setFont(letra);
		tablaHijos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tablaHijos.setForeground(colorLetra);
		tablaHijosScroll.getViewport().setBackground(colorFondoTexto);
		//tablaHijosScroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

	}
	
	private void inicializarComponentes() {
		
		//deshabilitar componentes
		campoNumeroCliente.setEnabled(false);
		campoTipoDocumento.setEnabled(false);
		campoNumeroDocumento.setEnabled(false);
		campoApellido.setEnabled(false);
		campoNombres.setEnabled(false);
		campoCalle.setEnabled(false);
		campoNumeroDomicilio.setEnabled(false);
		campoDepartamento.setEnabled(false);
		campoSumaAsegurada.setEnabled(false);
		campoSumaAsegurada.setHorizontalAlignment(SwingConstants.RIGHT);
		campoNumerosSiniestros.setEnabled(false);
		campoMotor.setEnabled(false);
		campoChasis.setEnabled(false);
		campoPatente.setEnabled(false);
		
		seleccionProvincia.setEnabled(false);
		seleccionCiudad.setEnabled(false);
		seleccionMarca.setEnabled(false);
		seleccionModelo.setEnabled(false);
		seleccionAnio.setEnabled(false);
		seleccionKm.setEnabled(false);
		
		rbtnGarageSi.setEnabled(false);
		rbtnGarageNo.setEnabled(false);
		rbtnAlarmaSi.setEnabled(false);
		rbtnAlarmaNo.setEnabled(false);
		rbtnRastreoNo.setEnabled(false);
		rbtnRastreoSi.setEnabled(false);
		rbtnTuercasSi.setEnabled(false);
		rbtnTuercasNo.setEnabled(false);
		
		tablaHijos.setEnabled(false);
		
		btnQuitarHijo.setEnabled(false);
		btnConfirmarDatos.setEnabled(false);
		btnAgregarHijo.setEnabled(false);
		
		garage.add(rbtnGarageSi);
		garage.add(rbtnGarageNo);
		rbtnGarageNo.setSelected(true);
		alarma.add(rbtnAlarmaSi);
		alarma.add(rbtnAlarmaNo);
		rbtnAlarmaNo.setSelected(true);
		rastreo.add(rbtnRastreoSi);
		rastreo.add(rbtnRastreoNo);
		rbtnRastreoNo.setSelected(true);
		tuercas.add(rbtnTuercasSi);
		tuercas.add(rbtnTuercasNo);
		rbtnTuercasNo.setSelected(true);
		
		//dar cierto tamaño
		seleccionProvincia.setPreferredSize(new Dimension(199, 25));
		seleccionCiudad.setPreferredSize(new Dimension(199, 25));
		seleccionMarca.setPreferredSize(new Dimension(163, 25));
		seleccionModelo.setPreferredSize(new Dimension(145, 25));
		seleccionAnio.setPreferredSize(new Dimension(130, 25));
		seleccionKm.setPreferredSize(new Dimension(220, 25));
		
		btnBuscarCliente.setPreferredSize(new Dimension(160, 25));
		btnAltaCliente.setPreferredSize(new Dimension(160, 25));
		btnAgregarHijo.setPreferredSize(new Dimension(160, 25));
		btnQuitarHijo.setPreferredSize(new Dimension(160, 25));
		btnConfirmarDatos.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		

		//definir tabla
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, 6) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		tablaHijos.setModel(tableModel);
		
		tablaHijos.setFillsViewportHeight(true);
		tablaHijos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaHijos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(3).setCellRenderer(centrado);
		
		tablaHijos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaHijos.getColumnModel().getColumn(1).setPreferredWidth(155);
		tablaHijos.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablaHijos.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		tablaHijos.getColumnModel().getColumn(0).setHeaderValue("Hijo");
		tablaHijos.getColumnModel().getColumn(1).setHeaderValue("Fecha nacimiento");
		tablaHijos.getColumnModel().getColumn(2).setHeaderValue("Sexo");
		tablaHijos.getColumnModel().getColumn(3).setHeaderValue("Estado civil");
		
		tablaHijosScroll.setPreferredSize(new Dimension(400, 100));
		
	}
	
	public void comportamientoBotones() {
		btnBuscarCliente.addActionListener(a -> {
			try {
				//GestorCliente.get().getClientes() o consultaClientes
				cliente = GestorCliente.get().getCliente(123456l);
				obteniendoCliente(cliente);
				btnBuscarCliente.setEnabled(false);
				btnAltaCliente.setEnabled(false);
						
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		});
		
		btnAltaCliente.addActionListener(a -> {
			try {				
				//GestorCliente.get().getClientes() o consultaClientes
				cliente = GestorCliente.get().getCliente(123456l);								
				obteniendoCliente(cliente);			
				btnBuscarCliente.setEnabled(false);
				btnAltaCliente.setEnabled(false);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                        "Error.", JOptionPane.ERROR_MESSAGE);    
			}
		});
		
		btnAgregarHijo.addActionListener(a -> {
			try {				
				
				componentesParaPoliza(false);
				mouseActivo = true;
				estaDeclarandoHijo = true;
				new CU01_DH(tema);
				
	
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnQuitarHijo.addActionListener(a -> {
			try {			
			
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});

		 
		btnConfirmarDatos.addActionListener(a -> {
			try {		
				if(!condicionesGenerarPoliza()) {
					return;
				}
				
				if(JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
					
					HibernateUtil.shutdown();
					
					poliza.setCiudad(seleccionCiudad.getItemAt(seleccionCiudad.getSelectedIndex()));
					poliza.setAnioModelo(seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex()));
					poliza.setMotor(campoMotor.getText());
					poliza.setChasis(campoChasis.getText());
					poliza.setPatente(campoPatente.getText());
					poliza.setSumaAsegurada(Float.parseFloat(campoSumaAsegurada.getText()));
					poliza.setKmRealizadosPorAnio(seleccionKm.getItemAt(seleccionKm.getSelectedIndex()));

					
					if(rbtnGarageSi.isSelected()) {
						poliza.setGuardaGarage(true);
					}else {
						poliza.setGuardaGarage(false);
					}
					
					if(rbtnAlarmaSi.isSelected()) {
						poliza.setTieneAlarma(true);
					}else {
						poliza.setTieneAlarma(false);
					}
					
					if(rbtnRastreoSi.isSelected()) {
						poliza.setTieneRastreoVehicular(true);
					}else {
						poliza.setTieneRastreoVehicular(false);
					}
					
					if(rbtnTuercasSi.isSelected()) {
						poliza.setTieneTuercasAntirobo(true);
					}else {
						poliza.setTieneTuercasAntirobo(false);
					}
					
					//this.setVisible(false);
					//ventana.remove(this);
					ventana.setContentPane(new CU01_AP2(ventana, tema, this));
				}
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.remove(this);				
				ventana.setContentPane(new CU01_AP1(ventana, tema));
				
				HibernateUtil.shutdown();
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	public void comportamientoComboBox() {
		seleccionProvincia.addActionListener (a -> {
			Provincia provincia = seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex());
			
			seleccionCiudad.removeAllItems();
			
			Iterator<Ciudad> iteratorCiudad = provincia.getCiudades().iterator();
			while(iteratorCiudad.hasNext()){
				seleccionCiudad.addItem(iteratorCiudad.next());
			}
		});		
		
		seleccionMarca.addActionListener (a -> {
			if(seleccionMarca.getSelectedIndex()!=0) {
				//para cuando es seleccionado luego de clickear el confirmar datos
				seleccionMarca.setForeground(colorLetra);
				
				List <Modelo> modelos = seleccionMarca.getItemAt(seleccionMarca.getSelectedIndex()).getModelos();
				
				seleccionModelo.setEnabled(false);
				seleccionModelo.removeAllItems();

				//TODO fijarse se poner el seleccionar modelo
				//cmbModelo.addItem(new Modelo("Seleccionar modelo"));
				
				//TODO ordernar
				//Collections.sort(modelos);
				
				Iterator<Modelo> iteratorModelo = modelos.iterator();
				while(iteratorModelo.hasNext()){
					seleccionModelo.addItem(iteratorModelo.next());
				}				
				
				seleccionAnio.setEnabled(false);	
				campoSumaAsegurada.setText("");
				seleccionAnio.removeAllItems();
									
				Iterator<AnioModelo> iteratorAnioModelo = seleccionModelo.getItemAt(0).getAnios().iterator();
				while(iteratorAnioModelo.hasNext()){
					seleccionAnio.addItem(iteratorAnioModelo.next());
				}
				
				seleccionModelo.setEnabled(true);
				seleccionAnio.setEnabled(true);
				campoSumaAsegurada.setText(seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex()).getSumaAsegurada().toString());
				//cmbModelo.setSelectedIndex(0);
			}
			else {
				seleccionModelo.setEnabled(false);
				seleccionAnio.setEnabled(false);
				seleccionModelo.removeAllItems();
				campoSumaAsegurada.setText("");	
				seleccionAnio.removeAllItems();
				

			}			
		});
		
		seleccionModelo.addActionListener (a -> {
			if(seleccionModelo.isEnabled()) {
				
					Modelo modelo = seleccionModelo.getItemAt(seleccionModelo.getSelectedIndex());
					
					seleccionAnio.setEnabled(false);	
					campoSumaAsegurada.setText("");
					seleccionAnio.removeAllItems();
										
					Iterator<AnioModelo> iteratorAnioModelo = modelo.getAnios().iterator();
					while(iteratorAnioModelo.hasNext()){
						seleccionAnio.addItem(iteratorAnioModelo.next());
					}
					
					seleccionAnio.setEnabled(true);
					campoSumaAsegurada.setText(seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex()).getSumaAsegurada().toString());
			}
			else {
				seleccionAnio.setEnabled(false);
			}

		});			
		
		seleccionAnio.addActionListener (a -> {
			if(seleccionAnio.isEnabled()) {
				campoSumaAsegurada.setText(seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex()).getSumaAsegurada().toString());
			}
			else {
				campoSumaAsegurada.setText("");
			}
		});
		
		seleccionKm.addActionListener (a -> {
			seleccionKm.setForeground(colorLetra);			
		});
	}

	public void comportamientoCampos() {
		
		campoMotor.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if( ( Character.isLetter(caracter) || Character.isDigit(caracter) ) && campoMotor.getText().length() < 17 ){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
			}
		}); 

	    campoChasis.addKeyListener(new KeyAdapter(){ 
	    	public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if( ( Character.isLetter(caracter) || Character.isDigit(caracter) ) && campoChasis.getText().length() < 8 ){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    });

	    campoPatente.addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if( ( Character.isLetter(caracter) || Character.isDigit(caracter) ) && campoPatente.getText().length() < 7 ){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    }); 

	}
	
	public void comportamientoMouse() {
        addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent event) {
	        	if(mouseActivo) {
		        	if(estaDeclarandoHijo) {
		        		componentesParaPoliza(!estaDeclarandoHijo);
		        		if(!DeclararHijoAbierto.declararHijoAbierto) {
		        			estaDeclarandoHijo = false;
		        			
		        			if(DeclararHijoAbierto.hijoDeclarado) {
			        			hijo = DeclararHijoAbierto.hijo;
			        			hijo.setPoliza(poliza);
			        			poliza.getHijosDeclarado().add(hijo);
			        			DeclararHijoAbierto.hijo = null;
		        			}
		        			
		        		}
		        		return;
		        	}
		        	else {
		        		componentesParaPoliza(!estaDeclarandoHijo);
		        		return;
		        	}
	        	}
	        }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void obteniendoCliente(Cliente cliente){
		
		//cambiar
		//SETEAR CAMPOS CLIENTES
		campoNumeroCliente.setText(cliente.getNumeroCliente().toString());
		campoTipoDocumento.setText(cliente.getTipoDocumento().toString());
		campoNumeroDocumento.setText(cliente.getNumeroDocumento().toString());
		campoApellido.setText(cliente.getApellido());
		campoNombres.setText(cliente.getNombre());
		campoCalle.setText(cliente.getCalle());
		campoNumeroDomicilio.setText(cliente.getNumeroCalle().toString());
		campoDepartamento.setText(cliente.getDepartamento());
		
		campoNumerosSiniestros.setText("CONSULTAR siniestros");
		
		campoMotor.setEnabled(true);
		campoChasis.setEnabled(true);
		campoPatente.setEnabled(true);
		seleccionProvincia.setEnabled(true);
		seleccionCiudad.setEnabled(true);
		seleccionMarca.setEnabled(true);
		seleccionKm.setEnabled(true);
		rbtnGarageSi.setEnabled(true);
		rbtnGarageNo.setEnabled(true);
		rbtnAlarmaSi.setEnabled(true);
		rbtnAlarmaNo.setEnabled(true);
		rbtnRastreoNo.setEnabled(true);
		rbtnRastreoSi.setEnabled(true);
		rbtnTuercasSi.setEnabled(true);
		rbtnTuercasNo.setEnabled(true);
		tablaHijos.setEnabled(true);
		btnAgregarHijo.setEnabled(true);
		
		campoNumeroCliente.setBackground(colorFondoPantalla);
		campoTipoDocumento.setBackground(colorFondoPantalla);
		campoNumeroDocumento.setBackground(colorFondoPantalla);
		campoApellido.setBackground(colorFondoPantalla);
		campoNombres.setBackground(colorFondoPantalla);
		campoCalle.setBackground(colorFondoPantalla);
		campoNumeroDomicilio.setBackground(colorFondoPantalla);
		campoDepartamento.setBackground(colorFondoPantalla);
				
		campoMotor.setBackground(colorFondoTexto);
		campoChasis.setBackground(colorFondoTexto);
		campoPatente.setBackground(colorFondoTexto);
		//tsumaAsegurada.setBackground(colorFondoTexto);
		
		campoMotor.setBorder(new LineBorder(borde));
		campoChasis.setBorder(new LineBorder(borde));
		campoPatente.setBorder(new LineBorder(borde));
		tablaHijos.setBorder(new LineBorder(borde));

		
		//TODO determinar que provincias elegir primero
		ArrayList<Provincia> provincias = (ArrayList<Provincia>) GestorDomicilio.get().getProvincias(100);
		Iterator<Provincia> iteradorProvincias = provincias.iterator();
		while(iteradorProvincias.hasNext()){
			seleccionProvincia.addItem(iteradorProvincias.next());
		}
				
		Iterator<Ciudad> iteratorCiudad = provincias.get(0).getCiudades().iterator();
		while(iteratorCiudad.hasNext()){
			seleccionCiudad.addItem(iteratorCiudad.next());
		}
		
		ArrayList<Marca> marcas = (ArrayList<Marca>) GestorParametrosVehiculo.get().getMarcas();
		seleccionMarca.addItem(new Marca("Seleccionar marca"));
		Iterator<Marca> marcasIterator = marcas.iterator();
		while(marcasIterator.hasNext()){
			seleccionMarca.addItem(marcasIterator.next());
		}
		
		
		seleccionKm.setModel(new DefaultComboBoxModel(new String[] {"Selecionar kilometraje",
				"0 - 9.999", "10.000 - 19.999", "20.000 - 29.999", "30.000 - 39.999", "40.000 - 49.999",
				"50.000 - 59.999", "60.000 - 69.999", "70.000 - 79.999", "80.000 - 89.999", "90.000 - 99.999",
				"100.00 - 109.999", "110.000 - 119.999", "120.000 - 129.999", "130.000 - 139.999", "140.000 - 149.999",
				"150.000 - 159.999", "160.000 - 169.999", "170.000 - 179.999", "180.000 - 189.999", "190.000 - 199.999",
				"200.000 - 209.999", "210.000 - 219.999", "220.000 - 229.999", "230.000 - 239.999", "240.000 - 249.999",
				"250.000 - 259.999", "260.000 - 269.999", "270.000 - 279.999", "280.000 - 289.999", "290.000 - 299.999",
				"Mayor a 300.000 km"
		}));
		

		//TODO QUTIARRRRR
		btnConfirmarDatos.setEnabled(true);
			
		
	}

	@SuppressWarnings("unused")
	private Boolean condicionesGenerarPoliza() {
		String patenteLargo = "", patenteFormato6 = "", patenteFormato7 = "";
		String chasisBlanco = "", chasisLargo = "", chasisFormato = "";
		String motorBlanco = "", motorLargo = "", motorFormato = "";
		String marcaSelecciono = "", kmSelecciono = "";
		
		Boolean valido = true;
		
		int errorNumero = 1;
	
		if (seleccionMarca.getSelectedIndex() == 0) {
			seleccionMarca.setForeground(colorErroneo);
			marcaSelecciono = errorNumero+") No se ha seleccionado un valor del campo marca.\n";
			errorNumero++;
			valido = false;
		}
		
		String textoMotor = campoMotor.getText();
		String textoChasis = campoChasis.getText();
		String textoPatente = campoPatente.getText();
		
		if(textoMotor.isBlank()) {
			campoMotor.setBackground(colorErroneo);
			motorBlanco = errorNumero+") No se ha introducido un número de motor\n";
			errorNumero++;
			valido = false;
		}
		else {
			if(textoMotor.length() == 17) {
				for(int i = 10; i < 17; i++) {
					if(Character.isLetter(textoMotor.charAt(i))) {
						campoMotor.setBackground(colorErroneo);
						motorFormato = errorNumero+") Formato de motor incorrecto. El formato de un número de chasis es CCCCCCCCCC9999999, donde\n"
								+ "las C debe indican que debe escribirse un dígito o una letra los 9 indican que deb escrirse un dígito.\n";
						errorNumero++;
						valido = false;
						i = 17;
					}
					else {
						campoMotor.setBackground(colorFondoTexto);
					}
				}
						
			}
			else {
				campoMotor.setBackground(colorErroneo);
				motorLargo = errorNumero+") La definición de un número de motor debe ser de longitud 17.\n";
				errorNumero++;
				valido = false;
			}	
		}
		
		if(textoChasis.isBlank()) {
			campoChasis.setBackground(colorErroneo);
			chasisBlanco = errorNumero+") No se ha introducido un número de chasis.\n";
			errorNumero++;
			valido = false;
		}
		else{
			if(textoChasis.length() == 8) {

				for(int i = 1; i < 8; i++) {
					
					if(!Character.isDigit(textoChasis.charAt(i))) {
						campoChasis.setBackground(colorErroneo);
						chasisFormato = errorNumero+") Formato de chasis incorrecto. El formato de un número de chasis es C9999999, donde C indica\n"
								+ "que debe escribirse un dígito o una letra y los 9 indican quedebe escribirse un dígito.\n";
						errorNumero++;
						valido = false;
						i = 8;
					}
					else {
						campoChasis.setBackground(colorFondoTexto);
					}
				}
				
				
			}
			else {
				campoChasis.setBackground(colorErroneo);
				chasisLargo = errorNumero+") La definición de un número de chasis debe ser de longitud 8.\n";
				errorNumero++;
				valido = false;
			}			
		}
		
		if(!textoPatente.isBlank()) {
			switch (textoPatente.length()) {
			
			//para patente longitud 6
	        case 6:
	        	for(int i = 0; i < 6; i++) {
	        		
		        	switch(i) {
		        		case 0:
		        		case 1:
		        		case 2:
		    				if(!Character.isLetter(textoPatente.charAt(i))) {
		    					campoPatente.setBackground(colorErroneo);
		    					patenteFormato6 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					i = 6;
		    				}
		    				else {
		    					campoPatente.setBackground(colorFondoTexto);
		    				}
		        		break;
		        		
		        		case 3:
		        		case 4:
		        		case 5:
		    				if(!Character.isDigit(textoPatente.charAt(i))) {
		    					campoPatente.setBackground(colorErroneo);
		    					patenteFormato6 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					i = 6;
		    				}
		    				else {
		    					campoPatente.setBackground(colorFondoTexto);
		    				}
		    		
		        		break;
		        	}
	        		
	        	}
	        	
	        break;     

	        //para patente longitud 7
	        case 7:
	        	for(int j = 0; j < 7; j++) {
	        		
		        	switch(j) {
		        		case 0:
		        		case 1:
		        		case 5:
		        		case 6:
		    				if(Character.isDigit(textoPatente.charAt(j))) {
		    					campoPatente.setBackground(colorErroneo);
		    					patenteFormato7 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					j = 7;
		    				}else {
		    					campoPatente.setBackground(colorFondoTexto);
		    				}
		        		break;
		        
		        		case 2:
		        		case 3:
		        		case 4:
		    				if(Character.isLetter(textoPatente.charAt(j))) {
		    					campoPatente.setBackground(colorErroneo);
		    					patenteFormato7 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";	
		    					errorNumero++;
		    					valido = false;
		    					j = 7;
		    				}else {
		    					campoPatente.setBackground(colorFondoTexto);
		    				}
		        		break;
		        	}
	        		
	        	}
	        break;

	        default:
	        	campoPatente.setBackground(colorErroneo);
				patenteLargo = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
				errorNumero++;
				valido = false;
			break;

			}		
		}
		else {
			campoPatente.setBackground(colorFondoTexto);
		}
		
		if (seleccionKm.getSelectedIndex() == 0) {
			seleccionKm.setForeground(colorErroneo);
			kmSelecciono = errorNumero+") No se ha seleccionado un valor del campo km realizados por año.\n";
			valido = false;
		}

		String mensajeError = marcaSelecciono + motorBlanco + motorFormato + motorLargo + chasisBlanco + chasisFormato + chasisLargo + patenteFormato6 + patenteFormato7 + patenteLargo + kmSelecciono;
		
		if(!valido) {
			JOptionPane.showConfirmDialog(ventana, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		return valido;
	}

	public void componentesParaPoliza(Boolean val) {
		seleccionProvincia.setEnabled(val);
		seleccionCiudad.setEnabled(val);
		seleccionMarca.setEnabled(val);
		if(seleccionMarca.getSelectedIndex()!=0) {
			seleccionModelo.setEnabled(val);
			seleccionAnio.setEnabled(val);
		}

		seleccionKm.setEnabled(val);
		
		campoMotor.setEnabled(val);
		campoChasis.setEnabled(val);		
		campoPatente.setEnabled(val);
		
		if(!val) {
			campoMotor.setBackground(colorFondoPantalla);
			campoChasis.setBackground(colorFondoPantalla);
			campoPatente.setBackground(colorFondoPantalla);
		}
		else {
			campoMotor.setBackground(colorFondoTexto);
			campoChasis.setBackground(colorFondoTexto);
			campoPatente.setBackground(colorFondoTexto);
		}
		
		rbtnGarageSi.setEnabled(val);
		rbtnGarageNo.setEnabled(val);
		rbtnAlarmaSi.setEnabled(val);
		rbtnAlarmaNo.setEnabled(val);
		rbtnRastreoNo.setEnabled(val);
		rbtnRastreoSi.setEnabled(val);
		rbtnTuercasSi.setEnabled(val);
		rbtnTuercasNo.setEnabled(val);
		
		tablaHijos.setEnabled(val);
		
		//si se agregan hijos
		//btnQuitarHijo.setEnabled(val);
		
		btnConfirmarDatos.setEnabled(val);
		btnAgregarHijo.setEnabled(val);
		
		mouseActivo = !val;
	}
}


