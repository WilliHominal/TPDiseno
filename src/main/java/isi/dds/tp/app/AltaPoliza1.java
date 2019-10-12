package isi.dds.tp.app;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.*;


@SuppressWarnings("serial")
public class AltaPoliza1 extends JPanel {
	
	private JFrame ventana;
	private Object[] tema;
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetraBloqueado, colorLetra;
	private Font letra;
	
	private Poliza poliza = new Poliza();;
	
	
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
		
	private JTextField tnumeroCliente = new JTextField(10);
	private JTextField ttipoDocumento = new JTextField(15);
	private JTextField tnumeroDocumento = new JTextField(8);
	private JTextField tapellido = new JTextField(15);
	private JTextField tnombres = new JTextField(15);
	private JTextField tcalle = new JTextField(15);
	private JTextField tnumeroDomicilio = new JTextField(8);
	private JTextField tdepartamento = new JTextField(3);
	private JTextField tmotor = new JTextField(15);
	private JTextField tchasis = new JTextField(8);
	private JTextField tpatente = new JTextField(7);
	private JTextField tsumaAsegurada = new JTextField(15);
	private JTextField tnumerosSiniestros = new JTextField(10); 
	
	private JButton btnBuscarCliente = new JButton("BUSCAR CLIENTES");
	private JButton btnAltaCliente = new JButton("DAR ALTA CLIENTES");
	private JButton btnAgregarHijo = new JButton("Agregar datos hijo");
	private JButton btnQuitarHijo = new JButton("Quitar datos hijo");
	private JButton btnConfirmarDatos = new JButton("Confirmar datos");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JComboBox<Provincia> cmbProvincia = new JComboBox<Provincia>();
	private JComboBox<Ciudad> cmbCiudad = new JComboBox<Ciudad>();
	private JComboBox<Marca> cmbMarca = new JComboBox<Marca>();
	private JComboBox<Modelo> cmbModelo = new JComboBox<Modelo>();
	private JComboBox<AnioModelo> cmbAnio = new JComboBox<AnioModelo>();
	private JComboBox<String> cmbKm = new JComboBox<String>();

	private ButtonGroup garage = new ButtonGroup(), alarma = new ButtonGroup(), rastreo = new ButtonGroup(), tuercas = new ButtonGroup(); 
	private JRadioButton rgarageSi = new JRadioButton("SI");
	private JRadioButton rgarageNo = new JRadioButton("NO");
	private JRadioButton ralarmaSi = new JRadioButton("SI");
	private JRadioButton ralarmaNo = new JRadioButton("NO");
	private JRadioButton rrastreoSi = new JRadioButton("SI");
	private JRadioButton rrastreoNo = new JRadioButton("NO");
	private JRadioButton rtuercasSi = new JRadioButton("SI");
	private JRadioButton rtuercasNo = new JRadioButton("NO");
	
	private JTable tablaHijos = new JTable(6, 4);
	private JScrollPane tablaHijosScroll = new JScrollPane(tablaHijos,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private Object[][] datosTabla = {{""},{""},{""},{""}};

	
	public AltaPoliza1(JFrame ventana, Object[] tema) {
		this.ventana = ventana;
		this.tema = tema;
			
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();

		comportamientoBotones();
		comportamientoComboBox();		
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
		add(tnumeroCliente, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ltipoDocumento, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(ttipoDocumento, constraints);
		
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ldocumento, constraints);
		
		constraints.gridx = 7;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(tnumeroDocumento, constraints);
		
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
		add(tapellido, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnombres, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(tnombres, constraints);
		
		//FILA 3
		constraints.gridy = 2;
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lcalle, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(tcalle, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(lnumeroDom, constraints);
		
		constraints.gridx = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 15);
		add(tnumeroDomicilio, constraints);
		
		constraints.gridx = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 5, 5);
		add(ldepartamento, constraints);
		
		constraints.gridx = 7;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 0, 5, 0);
		add(tdepartamento, constraints);
		
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
		add(cmbProvincia, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lciudad, constraints);
		constraints.insets.set(5, 355, 5, 5);
		add(cmbCiudad, constraints);
		
		//FILA 6
		constraints.gridy = 5;
		constraints.insets.set(5, 5, 5, 5);
		add(lmarca, constraints);
		constraints.insets.set(5, 111, 5, 5);
		add(cmbMarca, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lmodelo, constraints);
		constraints.insets.set(5, 410, 5, 5);
		add(cmbModelo, constraints);
		constraints.insets.set(5, 575, 5, 5);
		add(lanio, constraints);
		constraints.insets.set(5, 664, 5, 5);
		add(cmbAnio, constraints);

		//FILA 7
		constraints.gridy = 6;
		constraints.insets.set(5, 5, 5, 5);
		add(lmotor, constraints);
		constraints.insets.set(5, 60, 5, 5);
		add(tmotor, constraints);
		constraints.insets.set(5, 295, 5, 5);
		add(lchasis, constraints);
		constraints.insets.set(5, 349, 5, 5);
		add(tchasis, constraints);
		constraints.insets.set(5, 575, 5, 5);
		add(lpatente, constraints);
		constraints.insets.set(5, 638, 5, 5);
		add(tpatente, constraints);
		
		//FILA 8
		constraints.gridy = 7;
		constraints.insets.set(5, 5, 5, 5);
		add(lsumaAseg, constraints);
		constraints.insets.set(5, 113, 5, 5);
		add(tsumaAsegurada, constraints);
		constraints.insets.set(5, 298, 5, 5);
		add(lmoneda, constraints);

		//FILA 9
		constraints.gridy = 8;
		constraints.insets.set(5, 5, 5, 5);
		add(lkm, constraints);
		constraints.insets.set(5, 145, 5, 5);
		add(cmbKm, constraints);
		
		//FILA 10
		constraints.gridy = 9;
		constraints.insets.set(5, 5, 5, 5);
		add(lsiniestros, constraints);
		constraints.insets.set(5, 247, 5, 5);
		add(tnumerosSiniestros, constraints);
	
		//FILA 11
		constraints.gridy = 10;
		constraints.insets.set(5, 5, 5, 5);
		add(lgarage, constraints);
		constraints.insets.set(5, 140, 0, 5);
		add(rgarageSi, constraints);
		constraints.insets.set(5, 175, 0, 5);
		add(rgarageNo, constraints);
		
		constraints.insets.set(5, 250, 0, 5);
		add(lalarma, constraints);
		constraints.insets.set(5, 340, 0, 5);
		add(ralarmaSi, constraints);
		constraints.insets.set(5, 375, 0, 5);
		add(ralarmaNo, constraints);
		
		//FILA 11
		constraints.gridy = 11;
		constraints.insets.set(2, 5, 5, 5);
		add(lrastreo, constraints);
		constraints.insets.set(2, 245, 3, 5);
		add(rrastreoSi, constraints);
		constraints.insets.set(2, 280, 3, 5);
		add(rrastreoNo, constraints);
		
		constraints.insets.set(2, 355, 5, 5);
		add(ltuercas, constraints);
		constraints.insets.set(2, 515, 3, 5);
		add(rtuercasSi, constraints);
		constraints.insets.set(2, 550, 3, 5);
		add(rtuercasNo, constraints);
		
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
			
		tnumeroCliente.setFont(letra);
		tnumeroCliente.setBackground(colorFondoTexto);
		tnumeroCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tnumeroCliente.setDisabledTextColor(colorLetraBloqueado);
		ttipoDocumento.setFont(letra);
		ttipoDocumento.setBackground(colorFondoTexto);
		ttipoDocumento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		ttipoDocumento.setDisabledTextColor(colorLetraBloqueado);
		tnumeroDocumento.setFont(letra);
		tnumeroDocumento.setBackground(colorFondoTexto);
		tnumeroDocumento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tnumeroDocumento.setDisabledTextColor(colorLetraBloqueado);
		tapellido.setFont(letra);
		tapellido.setBackground(colorFondoTexto);
		tapellido.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tapellido.setDisabledTextColor(colorLetraBloqueado);
		tnombres.setFont(letra);
		tnombres.setBackground(colorFondoTexto);
		tnombres.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tnombres.setDisabledTextColor(colorLetraBloqueado);
		tcalle.setFont(letra);
		tcalle.setBackground(colorFondoTexto);
		tcalle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tcalle.setDisabledTextColor(colorLetraBloqueado);
		tnumeroDomicilio.setFont(letra);
		tnumeroDomicilio.setBackground(colorFondoTexto);
		tnumeroDomicilio.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tnumeroDomicilio.setDisabledTextColor(colorLetraBloqueado);
		tdepartamento.setFont(letra);
		tdepartamento.setBackground(colorFondoTexto);
		tdepartamento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tdepartamento.setDisabledTextColor(colorLetraBloqueado);
		tsumaAsegurada.setFont(letra);
		tsumaAsegurada.setBackground(colorFondoPantalla);
		tsumaAsegurada.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tsumaAsegurada.setDisabledTextColor(colorLetraBloqueado);
		tnumerosSiniestros.setFont(letra);
		tnumerosSiniestros.setBackground(colorFondoPantalla);
		tnumerosSiniestros.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tnumerosSiniestros.setDisabledTextColor(colorLetraBloqueado);
		
		
		tmotor.setFont(letra);
		tmotor.setBackground(colorFondoPantalla);
		tmotor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tchasis.setFont(letra);
		tchasis.setBackground(colorFondoPantalla);
		tchasis.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tpatente.setFont(letra);
		tpatente.setBackground(colorFondoPantalla);
		tpatente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		
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
		
		cmbProvincia.setBackground(colorFondoTexto);
		cmbProvincia.setFont(letra);
		cmbCiudad.setBackground(colorFondoTexto);
		cmbCiudad.setFont(letra);
		cmbMarca.setBackground(colorFondoTexto);
		cmbMarca.setFont(letra);
		cmbModelo.setBackground(colorFondoTexto);
		cmbModelo.setFont(letra);
		cmbAnio.setBackground(colorFondoTexto);
		cmbAnio.setFont(letra);
		cmbKm.setBackground(colorFondoTexto);
		cmbKm.setFont(letra);
		
		rgarageSi.setBackground(colorFondoPantalla);
		rgarageSi.setFont(letra);
		rgarageNo.setBackground(colorFondoPantalla);
		rgarageNo.setFont(letra);
		ralarmaSi.setBackground(colorFondoPantalla);
		ralarmaSi.setFont(letra);
		ralarmaNo.setBackground(colorFondoPantalla);
		ralarmaNo.setFont(letra);
		rrastreoSi.setBackground(colorFondoPantalla);
		rrastreoSi.setFont(letra);
		rrastreoNo.setBackground(colorFondoPantalla);
		rrastreoNo.setFont(letra);
		rtuercasSi.setBackground(colorFondoPantalla);
		rtuercasSi.setFont(letra);
		rtuercasNo.setBackground(colorFondoPantalla);
		rtuercasNo.setFont(letra);
		
		
		tablaHijos.setBackground(colorFondoTexto);
		tablaHijos.setFont(letra);
		tablaHijos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tablaHijosScroll.getViewport().setBackground(colorFondoTexto);
		//tablaHijosScroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

	}
	
	private void inicializarComponentes() {
		
		//deshabilitar componentes
		tnumeroCliente.setEnabled(false);
		ttipoDocumento.setEnabled(false);
		tnumeroDocumento.setEnabled(false);
		tapellido.setEnabled(false);
		tnombres.setEnabled(false);
		tcalle.setEnabled(false);
		tnumeroDomicilio.setEnabled(false);
		tdepartamento.setEnabled(false);
		tsumaAsegurada.setEnabled(false);
		tsumaAsegurada.setHorizontalAlignment(SwingConstants.RIGHT);
		tnumerosSiniestros.setEnabled(false);
		tmotor.setEnabled(false);
		tchasis.setEnabled(false);
		tpatente.setEnabled(false);
		
		cmbProvincia.setEnabled(false);
		cmbCiudad.setEnabled(false);
		cmbMarca.setEnabled(false);
		cmbModelo.setEnabled(false);
		cmbAnio.setEnabled(false);
		cmbKm.setEnabled(false);
		
		rgarageSi.setEnabled(false);
		rgarageNo.setEnabled(false);
		ralarmaSi.setEnabled(false);
		ralarmaNo.setEnabled(false);
		rrastreoNo.setEnabled(false);
		rrastreoSi.setEnabled(false);
		rtuercasSi.setEnabled(false);
		rtuercasNo.setEnabled(false);
		
		tablaHijos.setEnabled(false);
		
		btnQuitarHijo.setEnabled(false);
		btnConfirmarDatos.setEnabled(false);
		btnAgregarHijo.setEnabled(false);
		
		garage.add(rgarageSi);
		garage.add(rgarageNo);
		rgarageNo.setSelected(true);
		alarma.add(ralarmaSi);
		alarma.add(ralarmaNo);
		ralarmaNo.setSelected(true);
		rastreo.add(rrastreoSi);
		rastreo.add(rrastreoNo);
		rrastreoNo.setSelected(true);
		tuercas.add(rtuercasSi);
		tuercas.add(rtuercasNo);
		rtuercasNo.setSelected(true);
		
		//dar cierto tamaño
		cmbProvincia.setPreferredSize(new Dimension(199, 25));
		cmbCiudad.setPreferredSize(new Dimension(199, 25));
		cmbMarca.setPreferredSize(new Dimension(163, 25));
		cmbModelo.setPreferredSize(new Dimension(145, 25));
		cmbAnio.setPreferredSize(new Dimension(130, 25));
		cmbKm.setPreferredSize(new Dimension(220, 25));
		
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
				Cliente cliente = GestorCliente.get().getCliente(123456l);
				obtenerCliente(cliente);
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
				Cliente cliente = GestorCliente.get().getCliente(123456l);								
				obtenerCliente(cliente);			
				btnBuscarCliente.setEnabled(false);
				btnAltaCliente.setEnabled(false);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                        "Error.", JOptionPane.ERROR_MESSAGE);    
			}
		});
		
		btnAgregarHijo.addActionListener(a -> {
			try {				
				List hijo = new ArrayList();
				new DeclararHijo(tema, this, hijo);
				
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
				condicionesConfirmacion();

				if(JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
					
					HibernateUtil.shutdown();
					
					poliza.setCiudad(cmbCiudad.getItemAt(cmbCiudad.getSelectedIndex()));
					poliza.setAnioModelo(cmbAnio.getItemAt(cmbAnio.getSelectedIndex()));
					poliza.setMotor(tmotor.getText());
					poliza.setChasis(tchasis.getText());
					poliza.setPatente(tpatente.getText());
					poliza.setSumaAsegurada(Float.parseFloat(tsumaAsegurada.getText()));
					poliza.setKmRealizadosPorAnio(cmbKm.getItemAt(cmbKm.getSelectedIndex()));
					//TODO modificar como ver los sinietros
					poliza.setNumerosSiniestrosUltimoAnios(EnumSiniestros.valueOf(tnumerosSiniestros.getText()));
					
					if(rgarageSi.isSelected()) {
						poliza.setGuardaGarage(true);
					}else {
						poliza.setGuardaGarage(false);
					}
					
					if(ralarmaSi.isSelected()) {
						poliza.setTieneAlarma(true);
					}else {
						poliza.setTieneAlarma(false);
					}
					
					if(rrastreoSi.isSelected()) {
						poliza.setTieneRastreoVehicular(true);
					}else {
						poliza.setTieneRastreoVehicular(false);
					}
					
					if(rtuercasSi.isSelected()) {
						poliza.setTieneTuercasAntirobo(true);
					}else {
						poliza.setTieneTuercasAntirobo(false);
					}
										
					this.setVisible(false);
					ventana.remove(this);
					ventana.setContentPane(new AltaPoliza2(ventana, tema, poliza));
				}
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.remove(this);				
				ventana.setContentPane(new AltaPoliza1(ventana, tema));
				
				HibernateUtil.shutdown();
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	public void comportamientoComboBox() {
		cmbProvincia.addActionListener (a -> {
			
			Provincia provincia = cmbProvincia.getItemAt(cmbProvincia.getSelectedIndex());
			
			cmbCiudad.removeAllItems();
			
			Iterator<Ciudad> iteratorCiudad = provincia.getCiudades().iterator();
			while(iteratorCiudad.hasNext()){
				cmbCiudad.addItem(iteratorCiudad.next());
			}
		});		
		
		cmbMarca.addActionListener (a -> {
			
			if(cmbMarca.getSelectedIndex()!=0) {
				//para cuando es seleccionado luego de clickear el confirmar datos
				cmbMarca.setForeground(colorLetra);
				
				List <Modelo> modelos = cmbMarca.getItemAt(cmbMarca.getSelectedIndex()).getModelos();
				
				cmbModelo.setEnabled(false);
				cmbModelo.removeAllItems();

				//TODO fijarse se poner el seleccionar modelo
				//cmbModelo.addItem(new Modelo("Seleccionar modelo"));
				
				//TODO ordernar
				//Collections.sort(modelos);
				
				Iterator<Modelo> iteratorModelo = modelos.iterator();
				while(iteratorModelo.hasNext()){
					cmbModelo.addItem(iteratorModelo.next());
				}				
				
				cmbAnio.setEnabled(false);	
				tsumaAsegurada.setText("");
				cmbAnio.removeAllItems();
									
				Iterator<AnioModelo> iteratorAnioModelo = cmbModelo.getItemAt(0).getAnios().iterator();
				while(iteratorAnioModelo.hasNext()){
					cmbAnio.addItem(iteratorAnioModelo.next());
				}
				
				cmbModelo.setEnabled(true);
				cmbAnio.setEnabled(true);
				tsumaAsegurada.setText(cmbAnio.getItemAt(cmbAnio.getSelectedIndex()).getSumaAsegurada().toString());
				//cmbModelo.setSelectedIndex(0);
			}
			else {
				cmbModelo.setEnabled(false);
				cmbAnio.setEnabled(false);
				cmbModelo.removeAllItems();
				tsumaAsegurada.setText("");	
				cmbAnio.removeAllItems();
				

			}			
		});
		
		cmbModelo.addActionListener (a -> {
			if(cmbModelo.isEnabled()) {
				
					Modelo modelo = cmbModelo.getItemAt(cmbModelo.getSelectedIndex());
					
					cmbAnio.setEnabled(false);	
					tsumaAsegurada.setText("");
					cmbAnio.removeAllItems();
										
					Iterator<AnioModelo> iteratorAnioModelo = modelo.getAnios().iterator();
					while(iteratorAnioModelo.hasNext()){
						cmbAnio.addItem(iteratorAnioModelo.next());
					}
					
					cmbAnio.setEnabled(true);
					tsumaAsegurada.setText(cmbAnio.getItemAt(cmbAnio.getSelectedIndex()).getSumaAsegurada().toString());
			}
			else {
				cmbAnio.setEnabled(false);
			}

		});			
		
		cmbAnio.addActionListener (a -> {
			if(cmbAnio.isEnabled()) {
				tsumaAsegurada.setText(cmbAnio.getItemAt(cmbAnio.getSelectedIndex()).getSumaAsegurada().toString());
			}
			else {
				tsumaAsegurada.setText("");
			}
		});
		
		cmbKm.addActionListener (a -> {
			cmbKm.setForeground(colorLetra);			
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void obtenerCliente(Cliente cliente){
		
		//cambiar
		//SETEAR CAMPOS CLIENTES
		tnumeroCliente.setText(cliente.getNumeroCliente().toString());
		ttipoDocumento.setText(cliente.getTipoDocumento().toString());
		tnumeroDocumento.setText(cliente.getNumeroDocumento().toString());
		tapellido.setText(cliente.getApellido());
		tnombres.setText(cliente.getNombre());
		tcalle.setText(cliente.getCalle());
		tnumeroDomicilio.setText(cliente.getNumeroCalle().toString());
		tdepartamento.setText(cliente.getDepartamento());
		
		tnumerosSiniestros.setText("CONSULTAR siniestros");
		
		tmotor.setEnabled(true);
		tchasis.setEnabled(true);
		tpatente.setEnabled(true);
		cmbProvincia.setEnabled(true);
		cmbCiudad.setEnabled(true);
		cmbMarca.setEnabled(true);
		cmbKm.setEnabled(true);
		rgarageSi.setEnabled(true);
		rgarageNo.setEnabled(true);
		ralarmaSi.setEnabled(true);
		ralarmaNo.setEnabled(true);
		rrastreoNo.setEnabled(true);
		rrastreoSi.setEnabled(true);
		rtuercasSi.setEnabled(true);
		rtuercasNo.setEnabled(true);
		tablaHijos.setEnabled(true);
		btnAgregarHijo.setEnabled(true);
		
		tnumeroCliente.setBackground(colorFondoPantalla);
		ttipoDocumento.setBackground(colorFondoPantalla);
		tnumeroDocumento.setBackground(colorFondoPantalla);
		tapellido.setBackground(colorFondoPantalla);
		tnombres.setBackground(colorFondoPantalla);
		tcalle.setBackground(colorFondoPantalla);
		tnumeroDomicilio.setBackground(colorFondoPantalla);
		tdepartamento.setBackground(colorFondoPantalla);
				
		tmotor.setBackground(colorFondoTexto);
		tchasis.setBackground(colorFondoTexto);
		tpatente.setBackground(colorFondoTexto);
		//tsumaAsegurada.setBackground(colorFondoTexto);
		
		tmotor.setBorder(new LineBorder(borde));
		tchasis.setBorder(new LineBorder(borde));
		tpatente.setBorder(new LineBorder(borde));
		tablaHijos.setBorder(new LineBorder(borde));

		
		//TODO determinar que provincias elegir primero
		ArrayList<Provincia> provincias = (ArrayList<Provincia>) GestorDomicilio.get().getProvincias(100);
		Iterator<Provincia> iteradorProvincias = provincias.iterator();
		while(iteradorProvincias.hasNext()){
			cmbProvincia.addItem(iteradorProvincias.next());
		}
				
		Iterator<Ciudad> iteratorCiudad = provincias.get(0).getCiudades().iterator();
		while(iteratorCiudad.hasNext()){
			cmbCiudad.addItem(iteratorCiudad.next());
		}
		
		ArrayList<Marca> marcas = (ArrayList<Marca>) GestorParametrosVehiculo.get().getMarcas();
		cmbMarca.addItem(new Marca("Seleccionar marca"));
		Iterator<Marca> marcasIterator = marcas.iterator();
		while(marcasIterator.hasNext()){
			cmbMarca.addItem(marcasIterator.next());
		}
		
		
		cmbKm.setModel(new DefaultComboBoxModel(new String[] {"Selecionar kilometraje",
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

	private void condicionesConfirmacion() {
		//TODO enumerar errores, usar case
		
		if (cmbMarca.getSelectedIndex() == 0) {
			cmbMarca.setForeground(Color.red);
			JOptionPane.showConfirmDialog(ventana, "Seleccione un marca.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(tmotor.getText().isBlank()) {
			//TODO fijarse que al declaracion del campo se corresponda con la de un motor
			tmotor.setBackground(new Color(255,102,102));
			JOptionPane.showConfirmDialog(ventana, "Introduzca un número de motor", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return;					
		}
		else {
			tmotor.setBackground(colorFondoTexto);
		}
		
		if(tchasis.getText().isBlank()) {
			//TODO fijarse que al declaracion del campo se corresponda con la de un chasis
			tchasis.setBackground(new Color(255,102,102));
			JOptionPane.showConfirmDialog(ventana, "Introduzca un número de chasis", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return;	
		}
		else {
			tchasis.setBackground(colorFondoTexto);	
		}
		
		if(tpatente.getText().isBlank()) {
			//TODO fijarse que al declaracion del campo se corresponda con la de una patente
			tpatente.setBackground(new Color(255,102,102));
			JOptionPane.showConfirmDialog(ventana, "Introduzca un número de patente", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return;	
		}
		else {
			tpatente.setBackground(colorFondoTexto);
		}
		
		if (cmbKm.getSelectedIndex() == 0) {
			cmbKm.setForeground(Color.red);
			JOptionPane.showConfirmDialog(ventana, "Seleccione un kilometraje.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return;
		}	
	}
	
}


