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

	public AltaPoliza2(JFrame ventana) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel lTipoCobertura = new JLabel("Tipo de cobertura"), lFechaInicioVigencia = new JLabel("Fecha de inicio de vigencia de la póliza"), lFormaPago = new JLabel("Forma de pago"),
				lInfoPoliza = new JLabel("Información de la póliza"), lTitularSeguro =  new JLabel("Titular del seguro"), lDatosVehiculo =  new JLabel("Datos del vehículo"),
				lFechaInicio =  new JLabel("Fecha inicio"), lFechaFin =  new JLabel("Fecha fin");
		JDateChooser dcInicioVigencia = new JDateChooser(), dcInicio = new JDateChooser(), dcFin = new JDateChooser();
		JTextField tfApellido = new JTextField(15), tfNombre = new JTextField(15), tfMarca = new JTextField(15), tfMotor = new JTextField(15), 
				tfModelo = new JTextField(10), tfChasis = new JTextField(10), tfPatente = new JTextField(10),
				tfF1 = new JTextField(10), tfF2 = new JTextField(10), tfF3 = new JTextField(10), tfF4 = new JTextField(10);
		JButton confirmarDatos = new JButton("CONFIRMAR DATOS"), generarPoliza = new JButton("GENERAR PÓLIZA"), volver = new JButton("VOLVER");
		JComboBox<String> seleccionTipoCobertura = new JComboBox<String>();
		final ButtonGroup rbFormaPago = new ButtonGroup();
		JRadioButton mensual = new JRadioButton("Mensual"), semestral = new JRadioButton("Semestral");
		rbFormaPago.add(mensual);
		rbFormaPago.add(semestral);
		JTable tablaPagos = new JTable(6,2);
		
		//tabla
		tablaPagos.setFillsViewportHeight(true);
		tablaPagos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		
		JScrollPane scrollTablaPagos = new JScrollPane(tablaPagos,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

		//constraints generales
		constraints.insets=new Insets(5, 5, 5, 55);
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.WEST;
		constraints.gridwidth=1;
		
		//FILA 0
		constraints.gridy=0;
		constraints.gridx=0;
		panel.add(lTipoCobertura, constraints);
		
		constraints.gridx=1;
		panel.add(lFechaInicioVigencia, constraints);

		constraints.gridx=2;
		constraints.insets.set(5, 5, 5, 140);
		panel.add(lFormaPago, constraints);
		constraints.insets.set(5, 5, 5, 55);
		
		constraints.gridx=3;
		constraints.insets.set(5, 5, 5, 5);
		constraints.gridheight=2;
		panel.add(confirmarDatos, constraints);
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
		panel.add(seleccionTipoCobertura, constraints);
		constraints.fill=GridBagConstraints.NONE;
		
		constraints.gridx=1;
		panel.add(dcInicioVigencia, constraints);
		
		constraints.gridx=2;
		panel.add(mensual, constraints);
		constraints.anchor=GridBagConstraints.EAST;
		panel.add(semestral, constraints);
		constraints.anchor=GridBagConstraints.WEST;
		
		//FILA 2
		constraints.gridy=2;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(0, 5, 5, 5);
		panel.add(new JLabel("__________________________________________________________________________________________________________________________________________"), constraints);
		constraints.gridwidth=1;

		//FILA 3
		constraints.gridy=3;
		constraints.gridx=0;
		constraints.gridwidth=4;
		lInfoPoliza.setFont(new Font(lInfoPoliza.getFont().getName(), lInfoPoliza.getFont().getStyle(), 20));
		panel.add(lInfoPoliza, constraints);
		constraints.gridwidth=1;
		
		//FILA 4
		constraints.gridy=4;
		constraints.gridx=0;
		panel.add(lTitularSeguro, constraints);
		
		//FILA 5
		constraints.gridy=5;
		constraints.gridx=0;
		constraints.gridwidth=3;
		panel.add(new JLabel("Apellido:"), constraints);
		constraints.insets.set(5, 65, 5, 5);
		panel.add(tfApellido, constraints);
		constraints.insets.set(5, 275, 5, 5);
		panel.add(new JLabel("Nombre:"), constraints);
		constraints.insets.set(5, 335, 5, 5);
		panel.add(tfNombre, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 6
		constraints.gridy=6;
		constraints.gridx=0;
		panel.add(lDatosVehiculo, constraints);
		
		//FILA 7
		constraints.gridy=7;
		constraints.gridx=0;
		constraints.gridwidth=3;
		panel.add(new JLabel("Marca:"), constraints);
		constraints.insets.set(5, 65, 5, 5);
		panel.add(tfMarca, constraints);
		constraints.insets.set(5, 275, 5, 5);
		panel.add(new JLabel("Modelo:"), constraints);
		constraints.insets.set(5, 335, 5, 5);
		panel.add(tfModelo, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 8
		constraints.gridy=8;
		constraints.gridx=0;
		constraints.gridwidth=3;
		panel.add(new JLabel("Motor:"), constraints);
		constraints.insets.set(5, 65, 5, 5);
		panel.add(tfMotor, constraints);
		constraints.insets.set(5, 275, 5, 5);
		panel.add(new JLabel("Chasis:"), constraints);
		constraints.insets.set(5, 335, 5, 5);
		panel.add(tfChasis, constraints);
		constraints.insets.set(5, 490, 5, 5);
		panel.add(new JLabel("Patente:"), constraints);
		constraints.insets.set(5, 555, 5, 5);
		panel.add(tfPatente, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 9
		constraints.gridy=9;
		constraints.gridx=0;
		constraints.gridwidth=3;
		panel.add(lFechaInicio, constraints);
		constraints.insets.set(5, 275, 5, 5);
		panel.add(lFechaFin, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 10
		constraints.gridy=10;
		constraints.gridx=0;
		constraints.gridwidth=3;
		panel.add(dcInicio, constraints);
		constraints.insets.set(5, 275, 5, 5);
		panel.add(dcFin, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 11
		constraints.gridy=11;
		constraints.gridx=0;
		constraints.gridwidth=4;
		panel.add(new JLabel("Suma asegurada:"), constraints);
		constraints.insets.set(5, 120, 5, 5);
		panel.add(tfF1, constraints);
		constraints.insets.set(5, 275, 5, 5);
		panel.add(new JLabel("Premio:"), constraints);
		constraints.insets.set(5, 335, 5, 5);
		panel.add(tfF2, constraints);
		constraints.insets.set(5, 490, 5, 5);
		panel.add(new JLabel("Importe por descuento:"), constraints);
		constraints.insets.set(5, 645, 5, 5);
		panel.add(tfF3, constraints);
		constraints.insets.set(5, 760, 5, 5);
		panel.add(new JLabel("-Descuento por más de una unidad"), constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 12
		constraints.gridy=12;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(5, 760, 5, 5);
		panel.add(new JLabel("-Descuento por pago semestral"), constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 13
		constraints.gridy=13;
		constraints.gridx=0;
		constraints.gridwidth=2;
		panel.add(scrollTablaPagos, constraints);
		
		//FILA 14
		constraints.gridy=14;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(15, 135, 30, 5);
		panel.add(new JLabel("Monto total:"), constraints);
		constraints.insets.set(15, 215, 30, 5);
		panel.add(tfF4, constraints);
		constraints.insets.set(15, 645, 30, 5);
		panel.add(generarPoliza, constraints);
		constraints.insets.set(15, 815, 30, 5);
		panel.add(volver, constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(1024, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: GENERAR PÓLIZA");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
	}

}
