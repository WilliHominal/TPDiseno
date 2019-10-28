package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import isi.dds.tp.controller.CU17_controller;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;

@SuppressWarnings("serial")
public class CU01_AltaPoliza1 extends JPanel {
	
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					new CU01_AltaPoliza1(frame);
					GestorTema.get().setTema(frame, "Dar de alta póliza: INGRESAR DATOS");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame ventana;
	private Menu menu;
	private GestorTema tema = GestorTema.get();
	
	private String tituloAnterior = "";
	private Boolean primerCliente = true;
	public Poliza poliza = new Poliza();
	
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
	private JTextField campoNumeroDomicilio = new JTextField(8);
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
	
	public CU01_AltaPoliza1(JFrame ventana) {
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			menu = (Menu) ventana.getContentPane();
		}catch(Exception ex) {
		    menu = null;
		}
		ventana.setContentPane(this);
		
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		
		comportamientoBotones();
		comportamientoComboBox();		
		comportamientoCampos();
	}

	private void inicializarComponentes() {
		poliza.setHijosDeclarado(new ArrayList<HijoDeclarado>());
				
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
		
		cargarTabla(new ArrayList<HijoDeclarado>());
		
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
		add(campoNumeroDomicilio, constraints);
		
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
		tema.setTema(campoNumeroDomicilio, false);
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

	private void comportamientoBotones() {
		btnBuscarCliente.addActionListener(a -> {
			try {
				new CU17_controller(ventana);	
				ventana.revalidate();
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		});
		
		btnAltaCliente.addActionListener(a -> {
			try {				
				new CU04_AltaCliente(ventana);
				ventana.revalidate();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                        "Error.", JOptionPane.ERROR_MESSAGE);    
			}
		});
		
		btnAgregarHijo.addActionListener(a -> {
			try {				
				componentesAlDeclararHijos(false, null);
				
				new CU01_DeclararHijo(ventana);
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnQuitarHijo.addActionListener(a -> {
			try {			
				if(poliza.getHijosDeclarado().size() > 0) {
					int hijoSeleccionado = tablaHijos.getSelectedRow();
					poliza.getHijosDeclarado().remove(hijoSeleccionado);
					
					List<HijoDeclarado> hijos = poliza.getHijosDeclarado();
					
					if(hijos.size() == 0) {
						tema.setTema(btnQuitarHijo, false);
					}							
					cargarTabla(hijos);
					return;
				}
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
					
					poliza.setCiudad(seleccionCiudad.getItemAt(seleccionCiudad.getSelectedIndex()));
					poliza.setAnioModelo(seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex()));
					poliza.setMotor(campoMotor.getText());
					poliza.setChasis(campoChasis.getText());
					poliza.setPatente(campoPatente.getText());
					poliza.setSumaAsegurada(Float.parseFloat(campoSumaAsegurada.getText()));
					poliza.setKmRealizadosPorAnio(seleccionKm.getItemAt(seleccionKm.getSelectedIndex()));
					poliza.setNumerosSiniestrosUltimoAnios(GestorEnum.get().getEnumSiniestros(campoNumerosSiniestros.getText()));

					
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
				
					poliza.setNumerosSiniestrosUltimoAnios(GestorEnum.get().getEnumSiniestros(campoNumerosSiniestros.getText()));
					new CU01_AltaPoliza2(ventana, poliza);
				}
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.setContentPane(menu);	
				ventana.setTitle(tituloAnterior);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void comportamientoComboBox() {
		seleccionProvincia.addActionListener (a -> {
			Provincia provincia = seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex());
			seleccionCiudad.removeAllItems();
			Iterator<Ciudad> iteratorCiudad = GestorDomicilio.get().sortCiudades(provincia).iterator();
			while(iteratorCiudad.hasNext()){
				seleccionCiudad.addItem(iteratorCiudad.next());
			}
		});		
		
		seleccionMarca.addActionListener (a -> {
			tema.setTema(seleccionMarca, true);
			if(seleccionMarca.getSelectedIndex()!=0) {
				//para cuando es seleccionado luego de clickear el confirmar datos
				
				seleccionModelo.setEnabled(false);	
				seleccionModelo.removeAllItems();

				Iterator<Modelo> iteratorModelo = seleccionMarca.getItemAt(seleccionMarca.getSelectedIndex()).getModelos().iterator();
				while(iteratorModelo.hasNext()){
					seleccionModelo.addItem(iteratorModelo.next());
				}				
				
				seleccionAnio.setEnabled(false);	
				campoSumaAsegurada.setText("");
				seleccionAnio.removeAllItems();
									
				Iterator<AnioModelo> iteratorAnioModelo = GestorParametrosVehiculo.get().sortAniosModelo(seleccionModelo.getItemAt(seleccionModelo.getSelectedIndex()).getAnios()).iterator();
				while(iteratorAnioModelo.hasNext()){
					seleccionAnio.addItem(iteratorAnioModelo.next());
				}
				
				seleccionModelo.setEnabled(true);
				seleccionAnio.setEnabled(true);
				tema.setTema(seleccionModelo, true);
				tema.setTema(seleccionAnio, true);
				campoSumaAsegurada.setText(seleccionAnio.getItemAt(seleccionAnio.getSelectedIndex()).getSumaAsegurada().toString());
			}
			else {
				tema.setTema(seleccionModelo, false);
				tema.setTema(seleccionAnio, false);
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
				tema.setTema(seleccionAnio, false);
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
			tema.setTema(seleccionKm, true);	
		});
	}

	private void comportamientoCampos() {
		
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
	
	/**
	 * Éste método es invocado una vez obtenido el cliente buscado o que fue dado de alta.
	 * Setea los campos correspondientes a los atributos del cliente, inhabilita/habilita
	 * ciertos componentes del panel, y recargar ciertos valores para la generación de la 
	 * póliza.
	 * @param cliente
	 */
	public void obtenidoCliente(Cliente cliente){
		poliza.setCliente(cliente);
		campoNumeroCliente.setText(cliente.getNumeroCliente().toString());
		campoTipoDocumento.setText(GestorEnum.get().getStringTipoDocumento(cliente.getTipoDocumento()));
		campoNumeroDocumento.setText(cliente.getNumeroDocumento().toString());
		campoApellido.setText(cliente.getApellido());
		campoNombres.setText(cliente.getNombre());
		campoCalle.setText(cliente.getCalle());
		campoNumeroDomicilio.setText(cliente.getNumeroCalle().toString());
		campoNumerosSiniestros.setText(GestorEnum.get().getStringSiniestros(
				GestorSubsistemaSiniestros.get().getSiniestrosUltimosAnios(cliente.getTipoDocumento(), cliente.getNumeroDocumento(), LocalDate.now().getYear())));
		
		if(cliente.getPiso()==null) {
			campoPiso.setText("-");
			campoDepartamento.setText("-");
		}else {
			campoPiso.setText(cliente.getPiso().toString());
			campoDepartamento.setText(cliente.getDepartamento());
		}
		
		if(primerCliente) {
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

			ArrayList<Provincia> provincias = (ArrayList<Provincia>) GestorDomicilio.get().getProvincias(cliente.getCiudad().getProvincia().getPais().getIdPais());
			
			Iterator<Provincia> iteradorProvincias = provincias.iterator();
			while(iteradorProvincias.hasNext()){
				seleccionProvincia.addItem(iteradorProvincias.next());
			}
			
			ArrayList<Marca> marcas = (ArrayList<Marca>) GestorParametrosVehiculo.get().getMarcas();
			seleccionMarca.addItem(new Marca("Seleccionar marca"));
			Iterator<Marca> marcasIterator = marcas.iterator();
			while(marcasIterator.hasNext()){
				seleccionMarca.addItem(marcasIterator.next());
			}
			
			seleccionKm.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar kilometraje",
					"0 - 9.999", "10.000 - 19.999", "20.000 - 29.999", "30.000 - 39.999", "40.000 - 49.999",
					"50.000 - 59.999", "60.000 - 69.999", "70.000 - 79.999", "80.000 - 89.999", "90.000 - 99.999",
					"100.00 - 109.999", "110.000 - 119.999", "120.000 - 129.999", "130.000 - 139.999", "140.000 - 149.999",
					"150.000 - 159.999", "160.000 - 169.999", "170.000 - 179.999", "180.000 - 189.999", "190.000 - 199.999",
					"200.000 - 209.999", "210.000 - 219.999", "220.000 - 229.999", "230.000 - 239.999", "240.000 - 249.999",
					"250.000 - 259.999", "260.000 - 269.999", "270.000 - 279.999", "280.000 - 289.999", "290.000 - 299.999",
					"Más de 300.000 km"
			}));
			

			primerCliente = false;
		}
		
		seleccionProvincia.setSelectedItem(cliente.getCiudad().getProvincia());	
		
		seleccionCiudad.removeAllItems();
		Iterator<Ciudad> iteratorCiudad = GestorDomicilio.get().sortCiudades(cliente.getCiudad().getProvincia()).iterator();
		while(iteratorCiudad.hasNext()){
			seleccionCiudad.addItem(iteratorCiudad.next());
		}
		
		seleccionCiudad.setSelectedItem(cliente.getCiudad());	
	
	}

	/**
	 * Este método retorna un valor Boolean de acuerdo a sí
	 * se dan las condiciones necesarias para generar una
	 * póliza Si no se dan las condiciones necesarias 
	 * genera un aviso de error, distiguiendo que se está
	 * incumpliendo para poder generar póliza.
	 * @return
	 */
	private Boolean condicionesGenerarPoliza() {
		String patenteLargo = "", patenteFormato6 = "", patenteFormato7 = "";
		String chasisBlanco = "", chasisLargo = "", chasisFormato = "";
		String motorBlanco = "", motorLargo = "", motorFormato = "";
		String marcaSelecciono = "", kmSelecciono = "";
		
		Boolean valido = true;
		
		int errorNumero = 1;
	
		if (seleccionMarca.getSelectedIndex() == 0) {
			tema.erroneo(seleccionMarca);
			//seleccionMarca.setBackground(colorErroneo);
			marcaSelecciono = errorNumero+") No se ha seleccionado un valor del campo marca.\n";
			errorNumero++;
			valido = false;
		}
		
		String textoMotor = campoMotor.getText();
		String textoChasis = campoChasis.getText();
		String textoPatente = campoPatente.getText();
		
		if(textoMotor.isEmpty()) {
			tema.erroneo(campoMotor);
			motorBlanco = errorNumero+") No se ha introducido un número de motor\n";
			errorNumero++;
			valido = false;
		}
		else {
			if(textoMotor.length() == 17) {
				for(int i = 10; i < 17; i++) {
					if(Character.isLetter(textoMotor.charAt(i))) {
						tema.erroneo(campoMotor);
						motorFormato = errorNumero+") Formato de motor incorrecto. El formato de un número de chasis es CCCCCCCCCC9999999, donde\n"
								+ "las C debe indican que debe escribirse un dígito o una letra los 9 indican que deb escrirse un dígito.\n";
						errorNumero++;
						valido = false;
						i = 17;
					}
					else {
						tema.setTema(campoMotor, true);
					}
				}
						
			}
			else {
				tema.erroneo(campoMotor);
				motorLargo = errorNumero+") La definición de un número de motor debe ser de longitud 17.\n";
				errorNumero++;
				valido = false;
			}	
		}
		
		if(textoChasis.isEmpty()) {
			tema.erroneo(campoChasis);
			chasisBlanco = errorNumero+") No se ha introducido un número de chasis.\n";
			errorNumero++;
			valido = false;
		}
		else{
			if(textoChasis.length() == 8) {

				for(int i = 1; i < 8; i++) {
					
					if(!Character.isDigit(textoChasis.charAt(i))) {
						tema.erroneo(campoChasis);
						chasisFormato = errorNumero+") Formato de chasis incorrecto. El formato de un número de chasis es C9999999, donde C indica\n"
								+ "que debe escribirse un dígito o una letra y los 9 indican quedebe escribirse un dígito.\n";
						errorNumero++;
						valido = false;
						i = 8;
					}
					else {
						tema.setTema(campoChasis, true);
					}
				}
				
				
			}
			else {
				tema.erroneo(campoChasis);
				chasisLargo = errorNumero+") La definición de un número de chasis debe ser de longitud 8.\n";
				errorNumero++;
				valido = false;
			}			
		}
		
		if(!textoPatente.isEmpty()) {
			switch (textoPatente.length()) {
			
			//para patente longitud 6
	        case 6:
	        	for(int i = 0; i < 6; i++) {
	        		
		        	switch(i) {
		        		case 0:
		        		case 1:
		        		case 2:
		    				if(!Character.isLetter(textoPatente.charAt(i))) {
		    					tema.erroneo(campoPatente);
		    					patenteFormato6 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					i = 6;
		    				}
		    				else {
		    					tema.setTema(campoPatente, true);
		    				}
		        		break;
		        		
		        		case 3:
		        		case 4:
		        		case 5:
		    				if(!Character.isDigit(textoPatente.charAt(i))) {
		    					tema.erroneo(campoPatente);
		    					patenteFormato6 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					i = 6;
		    				}
		    				else {
		    					tema.setTema(campoPatente, true);
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
		    					tema.erroneo(campoPatente);
		    					patenteFormato7 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					j = 7;
		    				}else {
		    					tema.setTema(campoPatente, true);
		    				}
		        		break;
		        
		        		case 2:
		        		case 3:
		        		case 4:
		    				if(Character.isLetter(textoPatente.charAt(j))) {
		    					tema.erroneo(campoPatente);
		    					patenteFormato7 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";	
		    					errorNumero++;
		    					valido = false;
		    					j = 7;
		    				}else {
		    					tema.setTema(campoPatente, true);
		    				}
		        		break;
		        	}
	        		
	        	}
	        break;

	        default:
	        	tema.erroneo(campoPatente);
				patenteLargo = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
				errorNumero++;
				valido = false;
			break;

			}		
		}
		else {
			tema.setTema(campoPatente, true);
		}
		
		if (seleccionKm.getSelectedIndex() == 0) {
			//seleccionKm.setBackground(colorErroneo);
			tema.erroneo(seleccionKm);
			kmSelecciono = errorNumero+") No se ha seleccionado un valor del campo km realizados por año.\n";
			valido = false;
		}

		String mensajeError = marcaSelecciono + motorBlanco + motorFormato + motorLargo + chasisBlanco + chasisFormato + chasisLargo + patenteFormato6 + patenteFormato7 + patenteLargo + kmSelecciono;
		
		if(!valido) {
			JOptionPane.showConfirmDialog(ventana, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		return valido;
	}

	/**
	 * Éste método inhabilita o habilita los componetes de este frame de
	 * acuerdo a sí está o no declarandose un hijo.
	 * @param val
	 */
	public void componentesAlDeclararHijos(Boolean val, HijoDeclarado hijo) {
		
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
		
		if(hijo == null) {
			tema.setTema(btnQuitarHijo, false);
		}
		else {
			tema.setTema(btnQuitarHijo, true);
		}

		
		if(val) {
			if(hijo != null) {
				hijo.setPoliza(poliza);
    			poliza.getHijosDeclarado().add(hijo);
    						        			
    			cargarTabla(poliza.getHijosDeclarado());
			}
		}
	}

	/**
	 * Recarga el modelo de la tabla cada vez que se elimina o agrega un hijo,
	 * de modo que haya una fila por cada hijo declarado. Luego carga los hijos
	 * actualmente declarados.
	 * @param hijosDeclarado
	 */
	private void cargarTabla(List<HijoDeclarado> hijosDeclarado) {
		int tamanioTablaActual = hijosDeclarado.size();
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
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
		
		for(int fila = 0; fila < tamanioTablaActual; fila++) {
			
			model.setValueAt(fila+1, fila, 0);
			
			LocalDate date = hijosDeclarado.get(fila).getFechaNacimiento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
			String formattedString = date.format(formatter);
			
			model.setValueAt(formattedString, fila, 1);
			
			String sexo = GestorEnum.get().getStringSexo(hijosDeclarado.get(fila).getSexo());
			
			model.setValueAt(sexo, fila, 2);
			
			if(sexo.equals("Femenino")) {
				model.setValueAt(GestorEnum.get().getStringEstadoCivilFem(hijosDeclarado.get(fila).getEstadoCivil()), fila, 3);
			}
			else {
				model.setValueAt(GestorEnum.get().getStringEstadoCivil(hijosDeclarado.get(fila).getEstadoCivil()), fila, 3);
			}
		}
		
	}	
}


