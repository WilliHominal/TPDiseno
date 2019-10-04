package isi.dds.tp.app;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class AltaPoliza2 extends JPanel  {
	
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
	private JLabel lSumaA= new JLabel("Suma asegurada:");
	private JLabel lPremio = new JLabel("Premio:");
	private JLabel lImporte = new JLabel("Importe por descuento:");
	private JLabel lDescuentoPor= new JLabel("-Descuento por más de una unidad");
	private JLabel lDescuento = new JLabel("-Descuento por pago semestral");
	private JLabel lMontoTotal = new JLabel("Monto total:");

	private JDateChooser dcInicioVigencia = new JDateChooser();
	private JDateChooser dcInicio = new JDateChooser();
	private JDateChooser dcFin = new JDateChooser();
	
	private JTextField tfApellido = new JTextField(15);
	private JTextField tfNombre = new JTextField(15);
	private JTextField tfMarca = new JTextField(15);
	private JTextField tfMotor = new JTextField(15);
	private JTextField tfModelo = new JTextField(10);
	private JTextField tfChasis = new JTextField(10);
	private JTextField tfPatente = new JTextField(10);
	private JTextField tfF1 = new JTextField(10);
	private JTextField tfF2 = new JTextField(10);
	private JTextField tfF3 = new JTextField(10);
	private JTextField tfF4 = new JTextField(10);
	
	private JButton confirmarDatos = new JButton("CONFIRMAR DATOS");
	private JButton generarPoliza = new JButton("GENERAR PÓLIZA");
	private JButton volver = new JButton("VOLVER");
	
	private JRadioButton mensual = new JRadioButton("Mensual");
	private JRadioButton semestral = new JRadioButton("Semestral");
	
	private JTable tablaPagos = new JTable(6,2);
	JScrollPane scrollTablaPagos = new JScrollPane(tablaPagos,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JComboBox<String> seleccionTipoCobertura = new JComboBox<String>(); //cambiar

	public AltaPoliza2(JFrame ventana, Object[] tema) {
		setLayout(new GridBagLayout());
		inicializarTema((Color) tema[0], (Color) tema[1], (Color)tema[2], (Color) tema[3], (Font) tema[4], (Font) tema[5]);
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		final ButtonGroup rbFormaPago = new ButtonGroup();
		rbFormaPago.add(mensual);
		rbFormaPago.add(semestral);
		
		
		//tabla
		tablaPagos.setFillsViewportHeight(true);
		tablaPagos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		
		
		scrollTablaPagos.setPreferredSize(new Dimension(325, 119));
		
		tablaPagos.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tablaPagos.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		tablaPagos.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaPagos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPagos.getColumnModel().getColumn(0).setHeaderValue("Último día de pago");
		tablaPagos.getColumnModel().getColumn(1).setHeaderValue("Monto");
		
		DefaultCellEditor editor = (DefaultCellEditor) tablaPagos.getDefaultEditor(Object.class);
		editor.setClickCountToStart(10000);
		
		//subrayado


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
		constraints.insets.set(5, 5, 5, 5);
		constraints.gridheight=2;
		add(confirmarDatos, constraints);
		constraints.insets.set(5, 5, 5, 55);
		constraints.gridheight=1;
		
		//FILA 1
		constraints.gridy=1;
		constraints.gridx=0;
		constraints.insets.set(5, 5, 0, 55);
		seleccionTipoCobertura.addItem("SELECCIONAR TIPO DE COBERTURA");
		seleccionTipoCobertura.addItem("RESPONSABILIDAD CIVIL");
		seleccionTipoCobertura.addItem("RESP. CIVIL, ROBO O INCENDIO TOTAL");
		seleccionTipoCobertura.addItem("TODO TOTAL");
		seleccionTipoCobertura.addItem("TERCEROS COMPLETOS");
		seleccionTipoCobertura.addItem("TODO RIESGO CON FRANQUICIA");
		constraints.fill=GridBagConstraints.HORIZONTAL;
		add(seleccionTipoCobertura, constraints);
		constraints.fill=GridBagConstraints.NONE;
		
		constraints.gridx=1;
		add(dcInicioVigencia, constraints);
		
		constraints.gridx=2;
		add(mensual, constraints);
		constraints.anchor=GridBagConstraints.EAST;
		add(semestral, constraints);
		constraints.anchor=GridBagConstraints.WEST;
		
		//FILA 2
		constraints.gridy=2;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(0, 5, 5, 5);
		add(new JLabel("__________________________________________________________________________________________________________________________________________"), constraints);
		constraints.gridwidth=1;

		//FILA 3
		constraints.gridy=3;
		constraints.gridx=0;
		constraints.gridwidth=4;
		add(lInfoPoliza, constraints);
		constraints.gridwidth=1;
		
		//FILA 4
		constraints.gridy=4;
		constraints.gridx=0;
		add(lTitularSeguro, constraints);
		

		//FILA 5
		constraints.gridy=5;
		constraints.gridx=0;
		constraints.gridwidth=3;
		add(lApellido, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(tfApellido, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lNombre, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(tfNombre, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 6
		constraints.gridy=6;
		constraints.gridx=0;
		add(lDatosVehiculo, constraints);
		
		//FILA 7
		constraints.gridy=7;
		constraints.gridx=0;
		constraints.gridwidth=3;
		add(lMarca, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(tfMarca, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lModelo, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(tfModelo, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 8
		constraints.gridy=8;
		constraints.gridx=0;
		constraints.gridwidth=3;
		add(lMotor, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(tfMotor, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lChasis, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(tfChasis, constraints);
		constraints.insets.set(5, 490, 5, 5);
		add(lPatente, constraints);
		constraints.insets.set(5, 555, 5, 5);
		add(tfPatente, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 9
		constraints.gridy=9;
		constraints.gridx=0;
		constraints.gridwidth=3;
		add(lFechaInicio, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lFechaFin, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 10
		constraints.gridy=10;
		constraints.gridx=0;
		constraints.gridwidth=3;
		add(dcInicio, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(dcFin, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 11
		constraints.gridy=11;
		constraints.gridx=0;
		constraints.gridwidth=4;
		add(lSumaA, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(tfF1, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lPremio, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(tfF2, constraints);
		constraints.insets.set(5, 490, 5, 5);
		add(lImporte, constraints);
		constraints.insets.set(5, 645, 5, 5);
		add(tfF3, constraints);
		constraints.insets.set(5, 760, 5, 5);
		add(lDescuentoPor, constraints);
		constraints.insets.set(5, 5, 5, 5);

		//FILA 12
		constraints.gridy=12;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(5, 760, 5, 5);
		add(lDescuento, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 13
		constraints.gridy=13;
		constraints.gridx=0;
		constraints.gridwidth=2;
		add(scrollTablaPagos, constraints);
		
		//FILA 14
		
		constraints.gridy=14;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(15, 135, 30, 5);
		add(lMontoTotal, constraints);
		constraints.insets.set(15, 215, 30, 5);
		add(tfF4, constraints);
		constraints.insets.set(15, 645, 30, 5);
		add(generarPoliza, constraints);
		constraints.insets.set(15, 815, 30, 5);
		add(volver, constraints);
		

		ventana.setSize(1024, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: GENERAR PÓLIZA");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
	}

	public void inicializarTema(Color colorBoton, Color colorFondoPantalla, Color colorFondoTexto, Color borde, Font letra, Font letraTitulo) {
				
		setBackground(colorFondoPantalla);
		setFont(letra);
		Font subrayado = lTipoCobertura.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(subrayado.getAttributes());
		
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lTipoCobertura.setFont(subrayado.deriveFont(attributes));
		lFechaInicioVigencia.setFont(subrayado.deriveFont(attributes));
		lFormaPago.setFont(subrayado.deriveFont(attributes));
		lInfoPoliza.setFont(subrayado.deriveFont(attributes));
		lTitularSeguro.setFont(subrayado.deriveFont(attributes));
		lDatosVehiculo.setFont(subrayado.deriveFont(attributes));
		lFechaInicio.setFont(subrayado.deriveFont(attributes));
		lFechaFin.setFont(subrayado.deriveFont(attributes));
		
		/**
		lInfoPoliza.setFont(letraTitulo);
		lTipoCobertura.setFont(letra);
		lFechaInicioVigencia.setFont(letra);
		lFormaPago.setFont(letra);
		lTitularSeguro.setFont(letra);
		lDatosVehiculo.setFont(letra);
		lFechaInicio.setFont(letra);
		lFechaFin.setFont(letra);
		lApellido.setFont(letra);
		lNombre.setFont(letra);
		lMarca.setFont(letra);
		lModelo.setFont(letra);
		lMotor.setFont(letra);
		lChasis.setFont(letra);
		lPatente.setFont(letra);
		lSumaA.setFont(letra);
		lPremio.setFont(letra);
		lImporte.setFont(letra);
		lDescuentoPor.setFont(letra);
		lDescuento.setFont(letra);
		lMontoTotal.setFont(letra);
		
		*/
		dcInicioVigencia.setFont(letra);
		dcInicioVigencia.setBackground(colorFondoTexto);
		dcInicioVigencia.setBorder(new LineBorder(borde));
		dcInicio.setFont(letra);
		dcInicio.setBackground(colorFondoTexto);
		dcInicio.setBorder(new LineBorder(borde));
		dcFin.setFont(letra);
		dcFin.setBackground(colorFondoTexto);
		dcFin.setBorder(new LineBorder(borde));
		
		tfApellido.setFont(letra);
		tfApellido.setBackground(colorFondoTexto);
		tfApellido.setBorder(new LineBorder(borde));
		tfNombre.setFont(letra);
		tfNombre.setBackground(colorFondoTexto);
		tfNombre.setBorder(new LineBorder(borde));
		tfMarca.setFont(letra);
		tfMarca.setBackground(colorFondoTexto);
		tfMarca.setBorder(new LineBorder(borde));
		tfMotor.setFont(letra);
		tfMotor.setBackground(colorFondoTexto);
		tfMotor.setBorder(new LineBorder(borde));
		tfModelo.setFont(letra);
		tfModelo.setBackground(colorFondoTexto);
		tfModelo.setBorder(new LineBorder(borde));
		tfChasis.setFont(letra);
		tfChasis.setBackground(colorFondoTexto);
		tfChasis.setBorder(new LineBorder(borde));
		tfPatente.setFont(letra);
		tfPatente.setBackground(colorFondoTexto);
		tfPatente.setBorder(new LineBorder(borde));
		tfF1.setFont(letra);
		tfF1.setBackground(colorFondoTexto);
		tfF1.setBorder(new LineBorder(borde));
		tfF2.setFont(letra);
		tfF2.setBackground(colorFondoTexto);
		tfF2.setBorder(new LineBorder(borde));
		tfF3.setFont(letra);
		tfF3.setBackground(colorFondoTexto);
		tfF3.setBorder(new LineBorder(borde));
		tfF4.setFont(letra);
		tfF4.setBackground(colorFondoTexto);
		tfF4.setBorder(new LineBorder(borde));
		
		confirmarDatos.setFont(letra);
		confirmarDatos.setBackground(colorBoton);
		generarPoliza.setFont(letra);
		generarPoliza.setBackground(colorBoton);
		volver.setFont(letra);
		volver.setBackground(colorBoton);
		
		mensual.setFont(letra);
		mensual.setBackground(colorFondoPantalla);
		semestral.setFont(letra);
		semestral.setBackground(colorFondoPantalla);
		
		tablaPagos.setFont(letra);
		tablaPagos.setBackground(colorFondoTexto);
		scrollTablaPagos.getViewport().setBackground(colorFondoTexto);
		
	}
}
