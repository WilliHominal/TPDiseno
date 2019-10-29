package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Provincia;

public class CU01View1 extends JPanel {
	private static final long serialVersionUID = 4544933098405881656L;

	private GestorTema tema = GestorTema.get();

	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel ldocumento = new JLabel("Documento:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombres = new JLabel("Nombres:");
	private JLabel lcalle = new JLabel("Calle:");
	private JLabel lnumeroDom = new JLabel("Número domicilio:");
	private JLabel lpiso = new JLabel("Piso:");
	private JLabel ldepartamento = new JLabel("Departamento:");
	private JLabel lprovincia = new JLabel("Provincia*:");
	private JLabel lciudad = new JLabel("Ciudad*:");
	private JLabel lmarca = new JLabel("Marca vehículo*:");
	private JLabel lmodelo = new JLabel("Modelo vehículo:*");
	private JLabel lanio = new JLabel("Año modelo:*");
	private JLabel lmotor = new JLabel("Motor*:");
	private JLabel lchasis = new JLabel("Chasis*:");
	private JLabel lpatente = new JLabel("Patente*:");
	private JLabel lsumaAseg = new JLabel("Suma asegurada:");
	private JLabel lmoneda = new JLabel("$");
	private JLabel lkm = new JLabel("Km realizados por año:");
	private JLabel lsiniestros = new JLabel("Número de siniestros en el último año*:");
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
	private JTextField campoNumeroCalle = new JTextField(8);
	private JTextField campoPiso = new JTextField(2);
	private JTextField campoDepartamento = new JTextField(2);
	private JTextField campoMotor = new JTextField(15);
	private JTextField campoChasis = new JTextField(8);
	private JTextField campoPatente = new JTextField(7);
	private JTextField campoSumaAsegurada = new JTextField(15);
	private JTextField campoNumerosSiniestros = new JTextField(10); 
	
	private JButton btnBuscarCliente = new JButton("BUSCAR CLIENTES");
	private JButton btnAltaCliente = new JButton("ALTA CLIENTES");
	private JButton btnAgregarHijo = new JButton("Agregar datos hijo");
	private JButton btnQuitarHijo = new JButton("Quitar datos hijo");
	private JButton btnConfirmarDatos = new JButton("CONFIRMAR DATOS");
	private JButton btnCancelar = new JButton("CANCELAR");
	
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
	
	private JTable tablaHijos = new JTable();
	private JScrollPane tablaHijosScroll = new JScrollPane(tablaHijos);
	private Object[][] datosTabla = {{""},{""},{""},{""}};
	private DefaultTableModel model;
	
	public CU01View1() {	
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		cargarTabla(0);
		addListenerCampoMotor();
		addListenerCampoChasis();
		addListenerCampoPatente();
		addListenerSeleccionKm();
	}

	private void inicializarComponentes() {
		campoSumaAsegurada.setHorizontalAlignment(SwingConstants.RIGHT);
				
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
		
		rbtnGarageSi.setEnabled(false);
		rbtnGarageNo.setEnabled(false);
		rbtnAlarmaSi.setEnabled(false);
		rbtnAlarmaNo.setEnabled(false);
		rbtnRastreoNo.setEnabled(false);
		rbtnRastreoSi.setEnabled(false);
		rbtnTuercasSi.setEnabled(false);
		rbtnTuercasNo.setEnabled(false);
		
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
		btnConfirmarDatos.setPreferredSize(new Dimension(170, 25));
		btnCancelar.setPreferredSize(new Dimension(170, 25));
				
		tablaHijosScroll.setPreferredSize(new Dimension(350, 100));
	}
	
	private void ubicarComponentes() {	
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
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
		constraints.insets.set(5, 0, 5, 50);
		add(campoNumeroCalle, constraints);
		
		constraints.gridwidth = 2;
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 100, 5, 0);
		add(lpiso, constraints);
		
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 130, 5, 0);
		add(campoPiso, constraints);
		
		constraints.gridwidth = 1;
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(ldepartamento, constraints);

		constraints.gridx = 7;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 10, 5, 0);
		add(campoDepartamento, constraints);
		
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.gridwidth = 15;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 0, 5, 0);
		add(new JLabel("___________________________________________________________________________________________________________________________________"), constraints);
		
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

		constraints.gridy = 7;
		constraints.insets.set(5, 5, 5, 5);
		add(lsumaAseg, constraints);
		constraints.insets.set(5, 113, 5, 5);
		add(campoSumaAsegurada, constraints);
		constraints.insets.set(5, 268, 5, 5);
		add(lmoneda, constraints);

		constraints.gridy = 8;
		constraints.insets.set(5, 5, 5, 5);
		add(lkm, constraints);
		constraints.insets.set(5, 145, 5, 5);
		add(seleccionKm, constraints);

		constraints.gridy = 9;
		constraints.insets.set(5, 5, 5, 5);
		add(lsiniestros, constraints);
		constraints.insets.set(5, 225, 5, 5);
		add(campoNumerosSiniestros, constraints);

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
		
		constraints.gridy = 12;
		constraints.insets.set(5, 5, 2, 5);
		add(lcantidadHijos, constraints);
		
		constraints.gridy = 13;
		constraints.insets.set(3, 5, 2, 5);
		add(tablaHijosScroll, constraints);
		
		constraints.insets.set(0, 375, 40, 5);
		add(btnAgregarHijo, constraints);
		
		constraints.insets.set(40, 375, 2, 5);
		add(btnQuitarHijo, constraints);
		
		constraints.gridy = 14;
		constraints.insets.set(3, 5, 0, 5);
		add(ldatosObligatorios, constraints);
		
		constraints.insets.set(0, 655, 0, 5);
		add(btnConfirmarDatos, constraints);
		
		constraints.insets.set(0, 830, 0, 5);
		add(btnCancelar, constraints);

	}
		
	private void inicializarTema() {	
		tema.setTema(this);
		tema.setTema(lnumeroCliente);
		tema.setTema(ltipoDocumento);
		tema.setTema(ldocumento);
		tema.setTema(lapellido);
		tema.setTema(lnombres);
		tema.setTema(lcalle);
		tema.setTema(lnumeroDom);
		tema.setTema(lpiso);
		tema.setTema(ldepartamento);
		tema.setTema(lprovincia);
		tema.setTema(lciudad);
		tema.setTema(lmarca);
		tema.setTema(lmodelo);
		tema.setTema(lanio);
		tema.setTema(lmotor);
		tema.setTema(lchasis);
		tema.setTema(lpatente);
		tema.setTema(lsumaAseg);
		tema.setTema(lmoneda);
		tema.setTema(lkm);
		tema.setTema(lsiniestros);
		tema.setTema(lgarage);
		tema.setTema(lalarma);
		tema.setTema(lrastreo);
		tema.setTema(ltuercas);
		tema.setTema(lcantidadHijos);
		tema.setTema(ldatosObligatorios);
		tema.setTemaLabelchica(ldatosObligatorios);
		tema.setTema(campoNumeroCliente, false);
		tema.setTema(campoTipoDocumento, false);
		tema.setTema(campoNumeroDocumento, false);
		tema.setTema(campoApellido, false);
		tema.setTema(campoNombres, false);
		tema.setTema(campoCalle, false);
		tema.setTema(campoNumeroCalle, false);
		tema.setTema(campoDepartamento, false);
		tema.setTema(campoPiso, false);
		tema.setTema(campoSumaAsegurada, false);
		tema.setTema(campoNumerosSiniestros, false);
		tema.setTema(campoMotor, false);
		campoMotor.setToolTipText("CCCCCCCCCC9999999");
		tema.setTema(campoChasis, false);
		campoChasis.setToolTipText("C9999999");
		tema.setTema(campoPatente, false);
		campoPatente.setToolTipText("LLL999 / LL9999LL");
		tema.setTema(btnBuscarCliente, true);
		tema.setTema(btnAltaCliente, true);
		tema.setTema(btnAgregarHijo, false);
		tema.setTema(btnQuitarHijo, false);
		tema.setTema(btnConfirmarDatos, false);
		tema.setTema(btnCancelar, true);
		tema.setTema(seleccionProvincia, false);
		tema.setTema(seleccionCiudad, false);
		tema.setTema(seleccionMarca, false);
		tema.setTema(seleccionModelo, false);
		tema.setTema(seleccionAnio, false);
		tema.setTema(seleccionKm, false);
		tema.setTema(rbtnGarageSi);
		tema.setTema(rbtnGarageNo);
		tema.setTema(rbtnAlarmaSi);
		tema.setTema(rbtnAlarmaNo);
		tema.setTema(rbtnRastreoSi);
		tema.setTema(rbtnRastreoNo);
		tema.setTema(rbtnTuercasSi);
		tema.setTema(rbtnTuercasNo);
		tema.setTema(tablaHijos, false);
		tema.setTema(tablaHijosScroll, false);
	}

	public void componentesAlObtenerCliente() {
		rbtnGarageSi.setEnabled(true);
		rbtnGarageNo.setEnabled(true);
		rbtnAlarmaSi.setEnabled(true);
		rbtnAlarmaNo.setEnabled(true);
		rbtnRastreoNo.setEnabled(true);
		rbtnRastreoSi.setEnabled(true);
		rbtnTuercasSi.setEnabled(true);
		rbtnTuercasNo.setEnabled(true);
		
		tema.setTema(btnAgregarHijo, true);
		tema.setTema(btnConfirmarDatos, true);
		
		tema.setTema(seleccionProvincia, true);
		tema.setTema(seleccionCiudad, true);
		tema.setTema(seleccionMarca, true);
		tema.setTema(seleccionKm, true);
		tema.setTema(seleccionModelo, false);
		tema.setTema(seleccionAnio, false);
		
		tema.setTema(campoMotor, true);
		tema.setTema(campoChasis, true);
		tema.setTema(campoPatente, true);
		tema.setTema(tablaHijos, true);
		tema.setTema(tablaHijosScroll, true);
		tema.setTema(tablaHijosScroll, false);
	}

	/**
	 * Éste método inhabilita o habilita los componetes de este frame de
	 * acuerdo a sí está o no declarandose un hijo.
	 * @param val
	 */
	public void componentesAlDeclararHijos(Boolean val, Integer canHijos) {
		if(seleccionMarca.getSelectedIndex()!=0) {
			tema.setTema(seleccionModelo, val);
			tema.setTema(seleccionAnio, val);
		}else {
			tema.setTema(seleccionModelo, false);
			tema.setTema(seleccionAnio, false);
		}
			
		tema.setTema(seleccionKm, val);
		tema.setTema(seleccionProvincia, val);
		tema.setTema(seleccionCiudad, val);
		tema.setTema(seleccionMarca, val);

		tema.setTema(campoMotor, val);
		tema.setTema(campoChasis, val);
		tema.setTema(campoPatente, val);
		
		tema.setTema(tablaHijos, val);
		tema.setTema(tablaHijosScroll, val);
		
		rbtnGarageSi.setEnabled(val);
		rbtnGarageNo.setEnabled(val);
		rbtnAlarmaSi.setEnabled(val);
		rbtnAlarmaNo.setEnabled(val);
		rbtnRastreoNo.setEnabled(val);
		rbtnRastreoSi.setEnabled(val);
		rbtnTuercasSi.setEnabled(val);
		rbtnTuercasNo.setEnabled(val);
		
		tema.setTema(btnBuscarCliente, val);
		tema.setTema(btnAltaCliente, val);
		tema.setTema(btnConfirmarDatos, val);
		tema.setTema(btnCancelar, val);
		tema.setTema(btnAgregarHijo, val);
		
		if(canHijos == 0) {
			tema.setTema(btnQuitarHijo, false);
		}
		else {
			tema.setTema(btnQuitarHijo, true);
		}
	}

	public void noValido(Boolean marca, Boolean motor, Boolean chasis, Boolean patente, Boolean km) {
		if(marca) {
			tema.erroneo(seleccionMarca);
		}
		if(motor) {
			tema.erroneo(campoMotor);	
		}
		if(chasis) {
			tema.erroneo(campoChasis);
		}
		if(patente) {
			tema.erroneo(campoPatente);
		}
		if(km) {
			tema.erroneo(seleccionKm);
		}
	}
	
	/**
	 * Recarga el modelo de la tabla cada vez que se elimina o agrega un hijo,
	 * de modo que haya una fila por cada hijo declarado. Luego carga los hijos
	 * actualmente declarados.
	 * @param hijosDeclarado
	 */
	public void cargarTabla(Integer cantHijos) {
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, cantHijos) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaHijos.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaHijos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaHijos.getColumnModel().getColumn(3).setCellRenderer(centrado);
		
		tablaHijos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaHijos.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaHijos.getColumnModel().getColumn(2).setPreferredWidth(85);
		tablaHijos.getColumnModel().getColumn(3).setPreferredWidth(85);
		
		tablaHijos.getColumnModel().getColumn(0).setHeaderValue("Hijo");
		tablaHijos.getColumnModel().getColumn(1).setHeaderValue("Fecha nacimiento");
		tablaHijos.getColumnModel().getColumn(2).setHeaderValue("Sexo");
		tablaHijos.getColumnModel().getColumn(3).setHeaderValue("Estado civil");
	}
	
	public void cargarHijosTabla(Integer fila, String fechaNac, String sexo, String estadoCivil) {
		model.setValueAt(fila+1, fila, 0);
		model.setValueAt(fechaNac, fila, 1);
		model.setValueAt(sexo, fila, 2);
		model.setValueAt(estadoCivil, fila, 3);
	}
	
	public void addListenerBtn_BuscarCliente(ActionListener listener) {
		btnBuscarCliente.addActionListener(listener);
	}

	public void addListenerBtn_AltaCliente(ActionListener listener) {
		btnAltaCliente.addActionListener(listener);
	}
	
	public void addListenerBtn_AgregarHijo(ActionListener listener) {
		btnAgregarHijo.addActionListener(listener);
	}
	
	public void addListenerBtn_QuitarHijo(ActionListener listener) {
		btnQuitarHijo.addActionListener(listener);
	}
	
	public void addListenerBtn_ConfirmarDatos(ActionListener listener) {
		btnConfirmarDatos.addActionListener(listener);
	}
	
	public void addListenerBtn_Cancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerCampoMotor() {
		campoMotor.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				tema.setTema(campoMotor, true);
				char caracter = e.getKeyChar();
				if( ( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
						&& campoMotor.getText().length() < 17 ){
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
			}
		}); 
	}
	
	public void addListenerCampoChasis() {
	    campoChasis.addKeyListener(new KeyAdapter(){ 
	    	public void keyTyped(KeyEvent e){
				tema.setTema(campoChasis, true);
				char caracter = e.getKeyChar();
				if(( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
						&& campoChasis.getText().length() < 8 ){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    });
	}
	
	public void addListenerCampoPatente() {
	    campoPatente.addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e){
				tema.setTema(campoPatente, true);
				char caracter = e.getKeyChar();
				if( ( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
						&& campoPatente.getText().length() < 7 ){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    }); 
	}
	
	public void addListenerSeleccionProvincia(ActionListener listener) {
		seleccionProvincia.addActionListener (listener);		
	}
	
	public void addListenerSeleccionMarca(ActionListener listener) {
		seleccionMarca.addActionListener (listener);
		seleccionMarca.addActionListener (a -> {
			tema.setTema(seleccionMarca, true);	
		});
	}
	
	public void addListenerSeleccionModelo(ActionListener listener) {
		seleccionModelo.addActionListener (listener);
	}
	
	public void addListenerSeleccionAnioModelo(ActionListener listener) {
		seleccionAnio.addActionListener (listener);
	}
	
	public void addListenerSeleccionKm() {
		seleccionKm.addActionListener (a -> {
			tema.setTema(seleccionKm, true);	
		});
	}
	
	public void addMarca(Marca marca) {
		seleccionMarca.addItem(marca);
	}
	
	public void addModelo(Modelo modelo) {
		seleccionModelo.addItem(modelo);
	}
	
	public void addAnioModelo(AnioModelo anioModelo) {
		seleccionAnio.addItem(anioModelo);
	}
	
	public void addProvincia(Provincia provincia) {
		seleccionProvincia.addItem(provincia);
	}
	
	public void addCiudad(Ciudad ciudad, Boolean borrarLista) {
		if(borrarLista) {
			seleccionCiudad.removeAllItems();
		}
		seleccionCiudad.addItem(ciudad);
	}
	
	public void addKmsAnio() {
		seleccionKm.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar kilometraje",
				"0 - 9.999", "10.000 - 19.999", "20.000 - 29.999", "30.000 - 39.999", "40.000 - 49.999",
				"50.000 - 59.999", "60.000 - 69.999", "70.000 - 79.999", "80.000 - 89.999", "90.000 - 99.999",
				"100.00 - 109.999", "110.000 - 119.999", "120.000 - 129.999", "130.000 - 139.999", "140.000 - 149.999",
				"150.000 - 159.999", "160.000 - 169.999", "170.000 - 179.999", "180.000 - 189.999", "190.000 - 199.999",
				"200.000 - 209.999", "210.000 - 219.999", "220.000 - 229.999", "230.000 - 239.999", "240.000 - 249.999",
				"250.000 - 259.999", "260.000 - 269.999", "270.000 - 279.999", "280.000 - 289.999", "290.000 - 299.999",
				"Más de 300.000 km"
		}));
	}
	
	public void setNumeroCliente(String numeroCliente) {
		this.campoNumeroCliente.setText(numeroCliente);
	}

	public void setApellido(String apellido) {
		this.campoApellido.setText(apellido);
	}

	public void setNombre(String nombre) {
		this.campoNombres.setText(nombre);
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.campoTipoDocumento.setText(tipoDocumento);
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.campoNumeroDocumento.setText(numeroDocumento);
	}

	public void setCalle(String calle) {
		this.campoCalle.setText(calle);
	}

	public void setNumeroCalle(String numeroCalle) {
		this.campoNumeroCalle.setText(numeroCalle);
	}

	public void setPiso(String piso) {
		this.campoPiso.setText(piso);
	}

	public void setDepartamento(String departamento) {
		this.campoDepartamento.setText(departamento);
	}
	
	public void setProvinciaInicio(Provincia provincia) {
		seleccionProvincia.setSelectedItem(provincia);	
	}
	
	public void setCiudadInicio(Ciudad ciudad) {
		seleccionCiudad.setSelectedItem(ciudad);	
	}
	
	public void setSumaAsegurada(String sumaAsegurada) {
		campoSumaAsegurada.setText(sumaAsegurada);
	}
	
	public Provincia getProvincia() {
		return seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex());
	}
	
	public Ciudad getCiudad() {
		return seleccionCiudad.getItemAt(seleccionCiudad.getSelectedIndex());
	}
	
	public Marca getMarca() {
		return seleccionMarca.getItemAt(seleccionMarca.getSelectedIndex());
	}
	
	public Modelo getModelo() {
		return seleccionModelo.getItemAt(seleccionModelo.getSelectedIndex());
	}
	
	public AnioModelo getAnioModelo() {
		return seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex());
	}
	
	public String getMotor() {
		return campoMotor.getText();
	}

	public String getChasis() {
		return campoChasis.getText();
	}

	public String getPatente() {
		return campoPatente.getText();
	}
	
	public String getSumaAsegurada() {
		return campoSumaAsegurada.getText();
	}
	
	public String getNumeroSiniestros() {
		return campoNumerosSiniestros.getText();
	}

	public String getKmAnio() {
		return seleccionKm.getItemAt(seleccionKm.getSelectedIndex());
	}
	
	public Boolean getGarage() {
		return rbtnGarageSi.isSelected();
	}

	public Boolean getAlarma() {
		return rbtnAlarmaSi.isSelected();
	}
	
	public Boolean getRastreo() {
		return rbtnRastreoSi.isSelected();
	}
	
	public Boolean getTuercasAntirrobo() {
		return rbtnTuercasSi.isSelected();
	}
	
	public Integer getFilaSeleccionada() {
		return tablaHijos.getSelectedRow();
	}
	
	public void habilitarSeleccionModelo(Boolean habilitar) {
		tema.setTema(seleccionModelo, habilitar);
		if(!habilitar) {
			seleccionModelo.removeAllItems();
		}
	}
	
	public void habilitarSeleccionAnioModelo(Boolean habilitar) {
		tema.setTema(seleccionAnio, habilitar);
		if(!habilitar) {
			seleccionAnio.removeAllItems();
			campoSumaAsegurada.setText("");	
		}
	}

	public Boolean habilitadaSeleccionModelo() {
		return seleccionModelo.isEnabled();
	}
	
	public Boolean habilitadaSeleccionAnioModelo() {
		return seleccionAnio.isEnabled();
	}

}


