package isi.dds.tp.app;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.*;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.gestor.*;
import isi.dds.tp.modelo.*;

@SuppressWarnings("serial")
public class CU01_AP2 extends JPanel  {
	
	
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU01_AP2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CU01_AP2() {
		Cliente cliente1 = new Cliente(null, 123456l, EnumCondicion.NORMAL, "APELLIDO", "NOMBRES", EnumTipoDocumento.DNI, 99999999, 
				2011111118l, EnumSexo.MASCULINO, LocalDate.now(), "CALLE", 123, 3, "C", 2020, EnumCondicionIVA.CONSUMIDOR_FINAL, "correo@HOTMAIL.COM", EnumEstadoCivil.CASADO, "PROFESOR", 2019, EnumSiniestros.MAS_DE_DOS);

		Object[] tema = {new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK,
				//colorLetra			letra							colorErroneo
				Color.BLACK, new Font("Open Sans", Font.PLAIN, 13), new Color(255,102,102)};
		altaPoliza = new CU01_AP1(new JFrame(), tema);
		altaPoliza.poliza = new Poliza();
		altaPoliza.poliza.setCliente(cliente1);
		new CU01_AP2(new JFrame(), tema, altaPoliza, poliza);
	}
	
	private Poliza poliza;
	private JFrame ventana;
	public CU01_AP1 altaPoliza;
	
	private Object tema[];
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetra, colorErroneo;
	private Font letra;
	
	private JLabel lTipoCobertura = new JLabel("Tipo de cobertura");
	private JLabel lFechaInicioVigencia = new JLabel("Fecha de inicio de vigencia de la póliza");
	private JLabel lFormaPago = new JLabel("Forma de pago");
	private JLabel lInfoPoliza = new JLabel("Información de la póliza");
	private JLabel lTitularSeguro =  new JLabel("Titular del seguro");
	private JLabel lDatosVehiculo =  new JLabel("Datos del vehículo");
	private JLabel lFechaInicio =  new JLabel("Fecha inicio");
	private JLabel lFechaFin =  new JLabel("Fecha fin");
	private JLabel lApellido= new JLabel("Apellido:");
	private JLabel lNombre = new JLabel("Nombre:");
	private JLabel lMarca = new JLabel("Marca:");
	private JLabel lModelo = new JLabel("Modelo:");
	private JLabel lMotor = new JLabel("Motor:");
	private JLabel lChasis = new JLabel("Chasis:");
	private JLabel lPatente = new JLabel("Patente:");
	private JLabel lSumaAsegurada= new JLabel("Suma asegurada:");
	private JLabel lPremio = new JLabel("Premio:");
	private JLabel lDescuento = new JLabel("Importe por descuento:");
	private JLabel lDescUnidad = new JLabel("-Descuento por más de una unidad");
	private JLabel lDescSemestral = new JLabel("-Descuento por pago semestral");
	private JLabel lMontoTotal = new JLabel("Monto total:");
	
	private JDateChooser dcInicioVigencia = new JDateChooser();
	private JDateChooser dcInicio = new JDateChooser();
	private JDateChooser dcFin = new JDateChooser();
	
	private JTextField campoApellido = new JTextField(15);
	private JTextField campoNombre = new JTextField(15);
	private JTextField campoMarca = new JTextField(15);
	private JTextField campoMotor = new JTextField(15);
	private JTextField campoModelo = new JTextField(10);
	private JTextField campoChasis = new JTextField(10);
	private JTextField campoPatente = new JTextField(10);
	private JTextField campoSumaAsegurada = new JTextField(10);
	private JTextField campoPremio = new JTextField(10);
	private JTextField campoDescuentos = new JTextField(10);
	private JTextField campoMontoTotal = new JTextField(10);
	
	private JButton btnConfirmarDatos = new JButton("CONFIRMAR DATOS");
	private JButton btnGenerarPoliza = new JButton("GENERAR PÓLIZA");
	private JButton btnVolver = new JButton("VOLVER");
	
	private JComboBox<TipoCobertura> seleccionTipoCobertura = new JComboBox<TipoCobertura>();
	
	private final ButtonGroup rbFormaPago = new ButtonGroup();
	private JRadioButton mensual = new JRadioButton("Mensual");
	private JRadioButton semestral = new JRadioButton("Semestral");
	
	private JTable tablaPagos = new JTable(6,2);
	private JScrollPane scrollTablaPagos;

	public CU01_AP2(JFrame ventana, Object[] tema, CU01_AP1 altaPoliza, Poliza poliza) {
		this.altaPoliza = altaPoliza;
		this.ventana = ventana;
		this.tema = tema;
		this.poliza = poliza;

		inicializarComponentes();
		inicializarTema();
		ubicarComponentes();
		
		comportamiento();

		ventana.setContentPane(this);
		ventana.pack();
		ventana.setSize(1024, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: GENERAR PÓLIZA");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		//constraints generales
		constraints.insets=new Insets(5, 5, 5, 55);
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.WEST;
		constraints.gridwidth=1;
		
		//FILA 0
		constraints.gridy=0;
		constraints.gridx=0;
		add(lTipoCobertura, constraints);
		
		constraints.gridx=1;
		add(lFechaInicioVigencia, constraints);
		constraints.gridx=2;
		constraints.insets.set(5, 5, 5, 140);
		add(lFormaPago, constraints);
		constraints.insets.set(5, 5, 5, 55);
		
		constraints.gridx=3;
		constraints.insets.set(5, 0, 5, 10);
		constraints.gridheight=2;
		add(btnConfirmarDatos, constraints);
		constraints.insets.set(5, 5, 5, 55);
		constraints.gridheight=1;
		
		//FILA 1
		constraints.gridy=1;
		constraints.gridx=0;
		constraints.insets.set(5, 5, 0, 30);
		constraints.fill=GridBagConstraints.HORIZONTAL;
		add(seleccionTipoCobertura, constraints);
		constraints.fill=GridBagConstraints.NONE;
		
		constraints.gridx=1;
		add(dcInicioVigencia, constraints);
		
		constraints.insets.set(5, 5, 0, 60);
		constraints.gridx=2;
		add(mensual, constraints);
		constraints.anchor=GridBagConstraints.EAST;
		add(semestral, constraints);
		constraints.anchor=GridBagConstraints.EAST;
		
		//FILA 2
		constraints.gridy=2;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(0, 5, 5, 5);
		constraints.anchor = GridBagConstraints.CENTER;
		add(new JLabel("________________________________________________________________________________________________________________________________________"), constraints);
		constraints.gridwidth=1;
		
		//FILA 3
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridy=3;
		constraints.gridwidth=5;
		lInfoPoliza.setFont(new Font(lInfoPoliza.getFont().getName(), lInfoPoliza.getFont().getStyle(), 20));
		add(lInfoPoliza, constraints);
		constraints.gridwidth=1;
		
		//FILA 4
		constraints.gridy=4;
		add(lTitularSeguro, constraints);
		
		//FILA 5
		constraints.gridy=5;
		constraints.gridwidth=3;
		add(lApellido, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(campoApellido, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lNombre, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(campoNombre, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 6
		constraints.gridy=6;
		add(lDatosVehiculo, constraints);
		
		//FILA 7
		constraints.gridy=7;
		constraints.gridwidth=3;
		add(lMarca, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(campoMarca, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lModelo, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(campoModelo, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 8
		constraints.gridy=8;
		constraints.gridwidth=3;
		add(lMotor, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(campoMotor, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lChasis, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(campoChasis, constraints);
		constraints.insets.set(5, 490, 5, 5);
		add(lPatente, constraints);
		constraints.insets.set(5, 555, 5, 5);
		add(campoPatente, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 9
		constraints.gridy=9;
		constraints.gridwidth=3;
		add(lFechaInicio, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lFechaFin, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 10
		constraints.gridy=10;
		constraints.gridwidth=3;
		add(dcInicio, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(dcFin, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 11
		constraints.gridy=11;
		constraints.gridwidth=4;
		add(lSumaAsegurada, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoSumaAsegurada, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lPremio, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(campoPremio, constraints);
		constraints.insets.set(5, 490, 5, 5);
		add(lDescuento, constraints);
		constraints.insets.set(5, 645, 5, 5);
		add(campoDescuentos, constraints);
		constraints.insets.set(5, 770, 5, 5);
		
		add(lDescUnidad, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 12
		constraints.gridy=12;
		constraints.gridwidth=4;
		constraints.insets.set(5, 770, 5, 5);
		add(lDescSemestral, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 13
		constraints.gridy=13;
		constraints.gridwidth=2;
		add(scrollTablaPagos, constraints);
		
		//FILA 14
		constraints.gridy=14;
		constraints.gridwidth=4;
		constraints.insets.set(15, 135, 5, 5);
		add(lMontoTotal, constraints);
		constraints.insets.set(15, 215, 5, 5);
		add(campoMontoTotal, constraints);
		constraints.insets.set(15, 645, 5, 5);
		add(btnGenerarPoliza, constraints);
		constraints.insets.set(15, 815, 5, 5);
		add(btnVolver, constraints);
	}
	
	private void inicializarTema() {

		colorBoton = (Color) tema[0];
		colorFondoPantalla = (Color) tema[1];
		colorFondoTexto = (Color)tema[2];
		borde = (Color)tema[3];
		colorLetra = (Color) tema[4];
		letra = (Font) tema[5];
		colorErroneo = (Color) tema[6];
		
		setBounds(0,0,1024,600);
		setFont(letra);
		setForeground(colorLetra);
		setBackground(colorFondoPantalla);
		
		UIManager.put( "ComboBox.disabledBackground", colorFondoTexto );
		UIManager.put( "ComboBox.disabledForeground", colorLetra );		
		
		//subrayado
		Font font = lTipoCobertura.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lTipoCobertura.setFont(font.deriveFont(attributes));
		lFechaInicioVigencia.setFont(font.deriveFont(attributes));
		lFormaPago.setFont(font.deriveFont(attributes));
		lInfoPoliza.setFont(font.deriveFont(attributes));
		lTitularSeguro.setFont(font.deriveFont(attributes));
		lDatosVehiculo.setFont(font.deriveFont(attributes));
		lFechaInicio.setFont(font.deriveFont(attributes));
		lFechaFin.setFont(font.deriveFont(attributes));
		
		lTipoCobertura.setFont(letra);
		lFechaInicioVigencia.setFont(letra);
		lFormaPago.setFont(letra);
		lInfoPoliza .setFont(letra);
		lTitularSeguro.setFont(letra);
		lDatosVehiculo.setFont(letra);
		lFechaInicio.setFont(letra);
		lFechaFin.setFont(letra);
		lDescUnidad.setFont(letra);
		lDescSemestral.setFont(letra);
		lApellido.setFont(letra);
		lNombre.setFont(letra);
		lModelo.setFont(letra);
		lMarca.setFont(letra);
		lMotor.setFont(letra);
		lChasis.setFont(letra);
		lPatente.setFont(letra);
		lSumaAsegurada.setFont(letra);
		lPremio.setFont(letra);
		lDescuento.setFont(letra);
		lMontoTotal.setFont(letra);
			
		
		campoApellido.setDisabledTextColor(colorLetra);
		campoApellido.setFont(letra);
		campoApellido.setBackground(colorFondoPantalla);
		campoApellido.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNombre.setDisabledTextColor(colorLetra);
		campoNombre.setFont(letra);
		campoNombre.setBackground(colorFondoPantalla);
		campoNombre.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoMarca.setDisabledTextColor(colorLetra);
		campoMarca.setFont(letra);
		campoMarca.setBackground(colorFondoPantalla);
		campoMarca.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoMotor.setDisabledTextColor(colorLetra);
		campoMotor.setFont(letra);
		campoMotor.setBackground(colorFondoPantalla);
		campoMotor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoModelo.setDisabledTextColor(colorLetra);
		campoModelo.setFont(letra);
		campoModelo.setBackground(colorFondoPantalla);
		campoModelo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoChasis.setDisabledTextColor(colorLetra);
		campoChasis.setFont(letra);
		campoChasis.setBackground(colorFondoPantalla);
		campoChasis.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoPatente.setDisabledTextColor(colorLetra);
		campoPatente.setFont(letra);
		campoPatente.setBackground(colorFondoPantalla);
		campoPatente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoSumaAsegurada.setDisabledTextColor(colorLetra);
		campoSumaAsegurada.setFont(letra);
		campoSumaAsegurada.setBackground(colorFondoPantalla);
		campoSumaAsegurada.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoPremio.setDisabledTextColor(colorLetra);
		campoPremio.setFont(letra);
		campoPremio.setBackground(colorFondoPantalla);
		campoPremio.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoDescuentos.setDisabledTextColor(colorLetra);
		campoDescuentos.setFont(letra);
		campoDescuentos.setBackground(colorFondoPantalla);
		campoDescuentos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoMontoTotal.setDisabledTextColor(colorLetra);
		campoMontoTotal.setFont(letra);
		campoMontoTotal.setBackground(colorFondoPantalla);
		campoMontoTotal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		
		btnConfirmarDatos.setBackground(colorBoton);
		btnConfirmarDatos.setFont(letra);
		btnGenerarPoliza.setBackground(colorBoton);
		btnGenerarPoliza.setFont(letra);
		btnVolver.setBackground(colorBoton);
		btnVolver.setFont(letra);
		
		seleccionTipoCobertura.setBackground(colorFondoTexto);
		seleccionTipoCobertura.setFont(letra);
		seleccionTipoCobertura.setForeground(colorLetra);
		mensual.setBackground(colorFondoPantalla);
		mensual.setFont(letra);
		semestral.setBackground(colorFondoPantalla);
		semestral.setFont(letra);
		
		tablaPagos.setBackground(colorFondoPantalla);
		tablaPagos.setFont(letra);
		
		tablaPagos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scrollTablaPagos.getViewport().setBackground(colorFondoPantalla);
		scrollTablaPagos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBorder(new LineBorder(borde));
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setForeground(colorLetra);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setBackground(colorFondoPantalla);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setForeground(colorLetra);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setBackground(colorFondoPantalla);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		((JTextFieldDateEditor)dcFin.getDateEditor()).setForeground(colorLetra);
	}

	private void inicializarComponentes() {

		ArrayList<TipoCobertura> tipoCoberturas = (ArrayList<TipoCobertura>) GestorTipoCobertura.get().getTiposCobertura();
		Iterator<TipoCobertura> iteradorTipoCoberturas = tipoCoberturas.iterator();
		while(iteradorTipoCoberturas.hasNext()){
			seleccionTipoCobertura.addItem(iteradorTipoCoberturas.next());
		}
		
		rbFormaPago.add(mensual);
		rbFormaPago.add(semestral);
		
		try {
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaParseada); 
		      calendar.add(Calendar.DAY_OF_YEAR, 1);  
		      calendar.getTime(); 
			dcInicioVigencia.setDate(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dcInicioVigencia.setPreferredSize(new Dimension(105, 20));
		dcInicio.setPreferredSize(new Dimension(105, 20));
		dcFin.setPreferredSize(new Dimension(105, 20));
		
		btnConfirmarDatos.setPreferredSize(new Dimension(160, 25));
		btnGenerarPoliza.setPreferredSize(new Dimension(160, 25));
		btnVolver.setPreferredSize(new Dimension(160, 25));
				
		campoApellido.setEnabled(false);
		campoNombre.setEnabled(false);
		campoModelo.setEnabled(false);
		campoMarca.setEnabled(false);
		campoMotor.setEnabled(false);
		campoChasis.setEnabled(false);
		campoPatente.setEnabled(false);
		campoSumaAsegurada.setEnabled(false);
		campoPremio.setEnabled(false);
		campoDescuentos.setEnabled(false);
		campoMontoTotal.setEnabled(false);
		btnGenerarPoliza.setEnabled(false);
		dcInicio.setEnabled(false);
		dcFin.setEnabled(false);
		tablaPagos.setEnabled(false);
		
		mensual.setSelected(true);

		lDescUnidad.setVisible(false);
		lDescSemestral.setVisible(false);
				
		tablaPagos.setFillsViewportHeight(true);
		tablaPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );

		scrollTablaPagos = new JScrollPane(tablaPagos,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTablaPagos.setPreferredSize(new Dimension(325, 119));

		tablaPagos.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tablaPagos.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		tablaPagos.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaPagos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPagos.getColumnModel().getColumn(0).setHeaderValue("Último día de pago");
		tablaPagos.getColumnModel().getColumn(1).setHeaderValue("Monto");
		
		DefaultCellEditor editor = (DefaultCellEditor) tablaPagos.getDefaultEditor(Object.class);
		editor.setClickCountToStart(10000);
	}
	
	private void comportamiento() {
		
		btnConfirmarDatos.addActionListener(a -> {
			
			if(!condicionesConfirmarDatos()) {
				return;
			}
			
			campoApellido.setText(poliza.getCliente().getApellido());
			campoNombre.setText(poliza.getCliente().getNombre());
			campoModelo.setText(poliza.getAnioModelo().getModelo().getNombre());
			campoMarca.setText(poliza.getAnioModelo().getModelo().getMarca().getNombre());
			campoMotor.setText(poliza.getMotor());
			campoChasis.setText(poliza.getChasis());
			//deshabilito parte superior		
			/*
			//calculo premio, prima derechoEmision y descuento
			GestorParametroPoliza gpp = GestorParametroPoliza.getGestorParametroPoliza();
			GestorSuperIntendenciaSeguros gsis = GestorSuperIntendenciaSeguros.getGestorSuperIntendenciaSeguros();
			float sumaAsegurada = gsis.getSumaAsegurada(poliza.getAnioModelo().getModelo().getMarca().getNombre(), poliza.getAnioModelo().getModelo().getNombre(), poliza.getAnioModelo().getAnio());
			GestorTipoCobertura gtc = GestorTipoCobertura.getGestorTipoCobertura();
			GestorParametrosVehiculo gpv = GestorParametrosVehiculo.getGestorParametroPoliza();
			GestorDomicilio gd = GestorDomicilio.getGestorDomicilio();
			float prima = gpp.calcularPrima(gtc.getRiesgoCobertura(poliza.getTipoCobertura()), gpv.getRiesgoModelo(poliza.getAnioModelo().getModelo()), gd.getRiesgoCiudad(poliza.getCiudad()));
			float derechoEmision = gpp.getDerechoEmision();
			float premio = gpp.calcularPremio(prima, derechoEmision);
			Boolean descuentoMasDeUnaUnidad = false, descuentoSemestral = false;
			if (poliza.getCliente().getPolizas().size() > 1)
				descuentoMasDeUnaUnidad = true;
			if (semestral.isSelected())
				descuentoSemestral = true;
			float descuento = gpp.calcularDescuento(descuentoMasDeUnaUnidad, descuentoSemestral);
			tfSumaAsegurada.setText(Float.toString(sumaAsegurada));
			tfPremio.setText(Float.toString(premio));
			tfDescuentos.setText(Float.toString(descuento));*/

			
			//seteo datos tabla
			dcInicio.setDate(dcInicioVigencia.getDate());
			Calendar fechaFinVigencia = Calendar.getInstance();
			fechaFinVigencia.setTime(dcInicioVigencia.getDate());
			fechaFinVigencia.add(Calendar.MONTH, 6);
			dcFin.setDate(fechaFinVigencia.getTime());
			Calendar fechaAnteriorAInicioVigencia = Calendar.getInstance();
			fechaAnteriorAInicioVigencia.setTime(dcInicioVigencia.getDate());
			fechaAnteriorAInicioVigencia.add(Calendar.DATE, -1);
			
			DefaultTableModel model = (DefaultTableModel) tablaPagos.getModel();
			if(mensual.isSelected()) {
				Float montoTotal = 0f;
				for (int contador=0; contador<6; contador++) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
					Calendar fechaAux = Calendar.getInstance(); 
					fechaAux.setTime(fechaAnteriorAInicioVigencia.getTime());
					fechaAux.add(Calendar.MONTH, contador);
					
					model.setValueAt(dateFormat.format(fechaAux.getTime()), contador, 0);
					model.setValueAt("$ " + "2", contador, 1);				//CAMBIAR 2 POR MONTO DE LA CUOTA
					String auxMonto[] = ((String)model.getValueAt(contador, 1)).split(" ");
					montoTotal += Float.parseFloat(auxMonto[1]);
				}
				campoMontoTotal.setHorizontalAlignment(JTextField.RIGHT);
				campoMontoTotal.setText("$ " + Float.toString(montoTotal));
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				model.setValueAt(dateFormat.format(fechaAnteriorAInicioVigencia.getTime()), 0, 0);
				model.setValueAt("$ " + "5", 0, 1);							//CAMBIAR 5 POR MONTO TOTAL
				campoMontoTotal.setHorizontalAlignment(JTextField.RIGHT);
				campoMontoTotal.setText("$ " + "5");							//CAMBIAR 5 POR MONTO TOTAL
			}
		});
		
		//TODO VER ESTO DE SELECCIONAR COMBOBOXC

		btnGenerarPoliza.addActionListener(a -> {

			if(JOptionPane.showConfirmDialog(ventana, "¿Desea generar la póliza?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				GestorPoliza gp = GestorPoliza.get();
				gp.altaPoliza(poliza);

				//veo si es cliente Plata o Activo
				Boolean esPlata = true;

				GestorSubsistemaSiniestros gss = GestorSubsistemaSiniestros.get();
				if (gss.getSiniestrosUltimosAnios(poliza.getCliente().getNumeroDocumento()) > 0)
					esPlata = false;

				for (Cuota cuota : poliza.getCuotas()) {
					if (cuota.getEstado() == EnumEstadoCuota.IMPAGO)
						esPlata = false;
				}

				GestorCliente gc = GestorCliente.get();
				//TODO implementar calcularTiempoActivo
				if (gc.calcularTiempoActivo(poliza.getCliente()) < 365*2)
					esPlata = false;

				//TODO implementar actualizarCondicion
				if (esPlata)
					gc.actualizarCondicion(poliza.getCliente(), EnumCondicion.PLATA);
				else
					gc.actualizarCondicion(poliza.getCliente(), EnumCondicion.ACTIVO);

				System.out.println(poliza.getCliente().getCondicion());

				JOptionPane.showConfirmDialog(ventana, "Póliza generada correctamente.", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnVolver.addActionListener(a -> {
			if(JOptionPane.showConfirmDialog(ventana, "¿Desea corregir algún dato ingresado?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				ventana.setContentPane(altaPoliza);
				this.setVisible(false);
			}
		});
		
		//listener para sacar color rojo cuando lo selecciona
		seleccionTipoCobertura.addActionListener (a -> {
			seleccionTipoCobertura.setBackground(colorFondoTexto);
		});
	}
		
	public Boolean condicionesConfirmarDatos(){
		
		String errorTipoCobertura = "";
		String errorFechaVigencia = "";
		Boolean valido = true;
		
		//verifico datos
		if (seleccionTipoCobertura.getSelectedIndex() == 0) {
			seleccionTipoCobertura.setBackground(colorErroneo);
			errorTipoCobertura = "Seleccione un tipo de cobertura.\n";
			valido = false;
		}		
		
		
		SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
		
		Date fechaInicioVigenciaDate = null;

		try {
			fechaInicioVigenciaDate = formato.parse(formato.format(dcInicioVigencia.getDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate fechaInicioVigenciaLocalDate = fechaInicioVigenciaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int anioActual = LocalDate.now().getYear();
		int mesActual = LocalDate.now().getMonthValue();
		int diaActual = LocalDate.now().getDayOfMonth();
		
		int anioInicioVigencia = fechaInicioVigenciaLocalDate.getYear();
		int mesInicioVigencia = fechaInicioVigenciaLocalDate.getMonthValue();
		int diaInicioVigencia = fechaInicioVigenciaLocalDate.getDayOfMonth();
		
		if (!((anioActual == anioInicioVigencia && mesActual == mesInicioVigencia && diaActual < diaInicioVigencia)
			|| (anioActual == anioInicioVigencia && mesActual == mesInicioVigencia-1 && diaActual > diaInicioVigencia)
			|| (anioActual == anioInicioVigencia-1 && mesActual == 12 && mesInicioVigencia == 1 && diaActual >= diaInicioVigencia)
			)){
			errorFechaVigencia = "La fecha de inicio de vigencia debe estar dentro del mes próximo respecto a la fecha actual.\n";
			((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBackground(colorErroneo);
			valido = false;
		}
		else {
			((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBackground(colorFondoTexto);
		}
		
		if(!valido) {
			JOptionPane.showConfirmDialog(this, errorTipoCobertura + errorFechaVigencia , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		else {			
			btnConfirmarDatos.setEnabled(false);
			seleccionTipoCobertura.setEnabled(false);
			dcInicioVigencia.setEnabled(false);
			mensual.setEnabled(false);
			semestral.setEnabled(false);
			btnGenerarPoliza.setEnabled(true);
			if (semestral.isSelected()) {
				lDescSemestral.setVisible(true);
			}
			if (poliza.getCliente().getPolizas().size() > 1) {
				lDescUnidad.setVisible(true);
			}
			
			UIManager.put( "ComboBox.disabledBackground", colorFondoPantalla );
			seleccionTipoCobertura.setBackground(colorFondoPantalla);
			campoApellido.setBackground(colorFondoTexto);
			campoNombre.setBackground(colorFondoTexto);
			campoMarca.setBackground(colorFondoTexto);
			campoMotor.setBackground(colorFondoTexto);
			campoModelo.setBackground(colorFondoTexto);
			campoChasis.setBackground(colorFondoTexto);
			campoPatente.setBackground(colorFondoTexto);
			campoSumaAsegurada.setBackground(colorFondoTexto);
			campoPremio.setBackground(colorFondoTexto);
			campoDescuentos.setBackground(colorFondoTexto);
			campoMontoTotal.setBackground(colorFondoTexto);
			tablaPagos.setBackground(colorFondoTexto);
			scrollTablaPagos.getViewport().setBackground(colorFondoTexto);

			((JTextFieldDateEditor)dcInicio.getDateEditor()).setBackground(colorFondoTexto);
			((JTextFieldDateEditor)dcFin.getDateEditor()).setBackground(colorFondoTexto);
			((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setDisabledTextColor(colorLetra);;
			((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBackground(colorFondoPantalla);	
		}
		return valido;
	}
	
}