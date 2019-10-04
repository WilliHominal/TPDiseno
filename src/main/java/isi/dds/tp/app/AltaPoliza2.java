package isi.dds.tp.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
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
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumCondicionIVA;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;

@SuppressWarnings("serial")
public class AltaPoliza2 extends JPanel  {
	
	/////////////////////////////////////////////////////////////////BORRAR CLIENTE + POLIZA Y USAR LOS DATOS QUE LE PASA LA OTRA INTERFAZ
	public Cliente cliente = new Cliente(new Ciudad(new Provincia(new Pais("PA1"), "PR1"), "C1", 0), 123456l, EnumCondicion.NORMAL, "APE", "NOM", EnumTipoDocumento.DNI, 11111111, 
			2011111118l, EnumSexo.MASCULINO, LocalDate.now(), "CALLE", 123, 3, "C", 2020, EnumCondicionIVA.CONSUMIDOR_FINAL, "correo.xd", EnumEstadoCivil.CASADO, "PROF", 2019);
	public Poliza poliza = new Poliza(1231212l);
	public Poliza pol2 = new Poliza(123l);
	public List<Poliza> polizas = new ArrayList<Poliza>();
	/////////////////////////////////////////////////////////////////
	
	JLabel lTipoCobertura = new JLabel("Tipo de cobertura"), lFechaInicioVigencia = new JLabel("Fecha de inicio de vigencia de la póliza"), lFormaPago = new JLabel("Forma de pago"),
			lInfoPoliza = new JLabel("Información de la póliza"), lTitularSeguro =  new JLabel("Titular del seguro"), lDatosVehiculo =  new JLabel("Datos del vehículo"),
			lFechaInicio =  new JLabel("Fecha inicio"), lFechaFin =  new JLabel("Fecha fin"), lDesc1 = new JLabel("-Descuento por más de una unidad"), lDesc2 = new JLabel("-Descuento por pago semestral"),
			lApellido = new JLabel("Apellido:"), lNombre = new JLabel("Nombre:"), lMarca = new JLabel("Marca:"), lModelo = new JLabel("Modelo:"), lMotor = new JLabel("Motor:"), 
			lChasis = new JLabel("Chasis:"), lPatente = new JLabel("Patente:"), lSumaAsegurada = new JLabel("Suma asegurada:"), lPremio = new JLabel("Premio:"), 
			lDescuento = new JLabel("Importe por descuento:"), lMontoTotal = new JLabel("Monto total:");
	
	JDateChooser dcInicioVigencia = new JDateChooser(), dcInicio = new JDateChooser(), dcFin = new JDateChooser();
	
	JTextField tfApellido = new JTextField(15), tfNombre = new JTextField(15), tfMarca = new JTextField(15), tfMotor = new JTextField(15), 
			tfModelo = new JTextField(10), tfChasis = new JTextField(10), tfPatente = new JTextField(10),
			tfSumaAsegurada = new JTextField(10), tfPremio = new JTextField(10), tfDescuentos = new JTextField(10), tfMontoTotal = new JTextField(10);
	
	JButton confirmarDatos = new JButton("CONFIRMAR DATOS"), generarPoliza = new JButton("GENERAR PÓLIZA"), volver = new JButton("VOLVER");
	
	JComboBox<String> seleccionTipoCobertura = new JComboBox<String>();
	final ButtonGroup rbFormaPago = new ButtonGroup();
	JRadioButton mensual = new JRadioButton("Mensual"), semestral = new JRadioButton("Semestral");
	
	JTable tablaPagos = new JTable(6,2);
	JScrollPane scrollTablaPagos;

	public AltaPoliza2(JFrame ventana, Object[] tema) {
		
		/////////////////////////////////////////////////////////////////BORRAR
		polizas.add(poliza);
		polizas.add(pol2);
		cliente.setPolizas(polizas);
		poliza.setCliente(cliente);
		AnioModelo am = new AnioModelo();
		Marca mar = new Marca("Marca1");
		Modelo mod = new Modelo(mar, 1, "Modelo1", 0.54f);
		am.setAnio(1997);
		am.setModelo(mod);
		poliza.setAnioModelo(am);
		/////////////////////////////////////////////////////////////////BORRAR
		
		inicializarTema((Color) tema[0], (Color) tema[1], (Color)tema[2], (Color) tema[3], (Font) tema[4], (Font) tema[5]);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		rbFormaPago.add(mensual);
		rbFormaPago.add(semestral);
		
		//tabla
		tablaPagos.setFillsViewportHeight(true);
		tablaPagos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		
		scrollTablaPagos = new JScrollPane(tablaPagos,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTablaPagos.setPreferredSize(new Dimension(325, 119));
		
		scrollTablaPagos.setBorder(new LineBorder((Color)tema[3]));
		scrollTablaPagos.getViewport().setBackground((Color)tema[2]);
		scrollTablaPagos.setBorder(new LineBorder((Color)tema[3]));
		
		tablaPagos.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tablaPagos.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		tablaPagos.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaPagos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPagos.getColumnModel().getColumn(0).setHeaderValue("Último día de pago");
		tablaPagos.getColumnModel().getColumn(1).setHeaderValue("Monto");
		
		DefaultCellEditor editor = (DefaultCellEditor) tablaPagos.getDefaultEditor(Object.class);
		editor.setClickCountToStart(10000);

		//deshabilitar campos
		tfApellido.setEnabled(false);
		tfNombre.setEnabled(false);
		tfModelo.setEnabled(false);
		tfMarca.setEnabled(false);
		tfMotor.setEnabled(false);
		tfChasis.setEnabled(false);
		tfPatente.setEnabled(false);
		tfSumaAsegurada.setEnabled(false);
		tfPremio.setEnabled(false);
		tfDescuentos.setEnabled(false);
		tfMontoTotal.setEnabled(false);
		generarPoliza.setEnabled(false);
		dcInicio.setEnabled(false);
		dcFin.setEnabled(false);
		mensual.setSelected(true);
		
		//setear fecha default dia inicio vigencia
		Date diaActual = new Date();
		Calendar diaManana = Calendar.getInstance(); 
		Calendar diaMesProximo = Calendar.getInstance(); 
		diaManana.setTime(diaActual); 
		diaMesProximo.setTime(diaManana.getTime()); 
		diaManana.add(Calendar.DATE, 1);
		diaMesProximo.add(Calendar.MONTH, 1);
		dcInicioVigencia.setDate(diaManana.getTime());
		
		int diaDelMes1 = diaManana.get(Calendar.DAY_OF_MONTH);
		int mesDelAnio1 = diaManana.get(Calendar.MONTH);
		int anioActual1 = diaManana.get(Calendar.YEAR);
		int mesDelAnio2 = diaMesProximo.get(Calendar.MONTH);
		int anioActual2 = diaMesProximo.get(Calendar.YEAR);
		
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
		add(new JLabel("________________________________________________________________________________________________________________________________________"), constraints);
		constraints.gridwidth=1;

		//FILA 3
		constraints.gridy=3;
		constraints.gridx=0;
		constraints.gridwidth=4;
		lInfoPoliza.setFont(new Font(lInfoPoliza.getFont().getName(), lInfoPoliza.getFont().getStyle(), 20));
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
		add(lSumaAsegurada, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(tfSumaAsegurada, constraints);
		constraints.insets.set(5, 275, 5, 5);
		add(lPremio, constraints);
		constraints.insets.set(5, 335, 5, 5);
		add(tfPremio, constraints);
		constraints.insets.set(5, 490, 5, 5);
		add(lDescuento, constraints);
		constraints.insets.set(5, 645, 5, 5);
		add(tfDescuentos, constraints);
		constraints.insets.set(5, 770, 5, 5);
		lDesc1.setVisible(false);
		add(lDesc1, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 12
		constraints.gridy=12;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(5, 770, 5, 5);
		lDesc2.setVisible(false);
		add(lDesc2, constraints);
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
		constraints.insets.set(15, 135, 5, 5);
		add(lMontoTotal, constraints);
		constraints.insets.set(15, 215, 5, 5);
		add(tfMontoTotal, constraints);
		constraints.insets.set(15, 645, 5, 5);
		add(generarPoliza, constraints);
		constraints.insets.set(15, 815, 5, 5);
		add(volver, constraints);
		
		//Listener Botones
		confirmarDatos.addActionListener(a -> {
			//verifico datos
			if (seleccionTipoCobertura.getSelectedIndex() == 0) {
				seleccionTipoCobertura.setForeground(Color.red);
				JOptionPane.showConfirmDialog(ventana, "Seleccione un tipo de cobertura.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int anioInicioVigencia = dcInicioVigencia.getCalendar().get(Calendar.YEAR);
			int mesInicioVigencia = dcInicioVigencia.getCalendar().get(Calendar.MONTH);
			int diaInicioVigencia = dcInicioVigencia.getCalendar().get(Calendar.DAY_OF_MONTH);
			
			if ((anioInicioVigencia < anioActual1) || (anioInicioVigencia == anioActual1 && mesInicioVigencia < mesDelAnio1) || (anioInicioVigencia == anioActual1 && mesInicioVigencia == mesDelAnio1 && diaInicioVigencia < diaDelMes1)) {
				((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setForeground(Color.red);
				JOptionPane.showConfirmDialog(ventana, "La fecha de inicio de vigencia debe estar dentro del mes próximo respecto a la fecha actual.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if ((anioInicioVigencia > anioActual2) || (anioInicioVigencia == anioActual2 && mesInicioVigencia > mesDelAnio2) || (anioInicioVigencia == anioActual2 && mesInicioVigencia == mesDelAnio2 && diaInicioVigencia > diaDelMes1)) {
				((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setForeground(Color.red);
				JOptionPane.showConfirmDialog(ventana, "La fecha de inicio de vigencia debe estar dentro del mes próximo respecto a la fecha actual.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (semestral.isSelected()) {
				lDesc2.setVisible(true);
			}
			//deshabilito parte superior
			confirmarDatos.setEnabled(false);
			dcInicioVigencia.setEnabled(false);
			seleccionTipoCobertura.setEnabled(false);
			mensual.setEnabled(false);
			semestral.setEnabled(false);
			
			//cargo datos parte inferior
			generarPoliza.setEnabled(true);
			
			/////////////////////////////////////////////////////////////////
			//ver si le paso poliza auxiliar o parametros separados
			tfApellido.setText(poliza.getCliente().getApellido());
			tfNombre.setText(poliza.getCliente().getNombre());
			tfModelo.setText(poliza.getAnioModelo().getModelo().getNombre());
			tfMarca.setText(poliza.getAnioModelo().getModelo().getMarca().getNombre());
			tfMotor.setText(poliza.getMotor());
			tfChasis.setText(poliza.getChasis());
			tfPatente.setText(poliza.getPatente());
			
			if (poliza.getCliente().getPolizas().size() > 1){lDesc1.setVisible(true);}
			
			//GestorParametroPoliza gpp = new GestorParametroPoliza();
			//GestorSuperIntendenciaSeguros gsis = new GestorSuperIntendenciaSeguros();
			//float sumaAsegurada = gsis.getSumaAsegurada(poliza.getAnioModelo().getModelo().getMarca().getNombre(), poliza.getAnioModelo().getModelo().getNombre(), poliza.getAnioModelo().getAnio());
			//float prima = gpp.calcularPrima(a,b,c);
			//float derechoEmision = gpp.getDerechoEmision();
			//float premio = gpp.calcularPremio(prima, derechoEmision);
			//float descuento = gpp.calcularDescuento(boolean desc1, boolean desc2);
			//
			/////////////////////////////////////////////////////////////////
			
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
				tfMontoTotal.setHorizontalAlignment(JTextField.RIGHT);
				tfMontoTotal.setText("$ " + Float.toString(montoTotal));
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				model.setValueAt(dateFormat.format(fechaAnteriorAInicioVigencia.getTime()), 0, 0);
				model.setValueAt("$ " + "5", 0, 1);							//CAMBIAR 5 POR MONTO TOTAL
				tfMontoTotal.setHorizontalAlignment(JTextField.RIGHT);
				tfMontoTotal.setText("$ " + "5");							//CAMBIAR 5 POR MONTO TOTAL
			}
		});
		
		seleccionTipoCobertura.addActionListener (a -> {
			seleccionTipoCobertura.setForeground(Color.black);
		});
		
		generarPoliza.addActionListener(a -> {
			
		});
		
		volver.addActionListener(a -> {
			if(JOptionPane.showConfirmDialog(ventana, "¿Desea corregir algún dato ingresado?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				//VOLVER A VENTANA ANTERIOR
				new AltaPoliza2(ventana, tema); //CAMBIAR POR VENTANA ANTERIOR
			}
		});
		
		//ajustes ventana
		ventana.setContentPane(this);
		ventana.pack();
		ventana.setSize(1024, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: GENERAR PÓLIZA");
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
	}
	
	public void inicializarTema(Color colorBoton, Color colorFondoPantalla, Color colorFondoTexto, Color borde, Font letra, Font letraTitulo) {
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setFont(letra);
		setBackground(colorFondoPantalla);
		
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
		lDesc1.setFont(letra);
		lDesc2.setFont(letra);
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
		tfSumaAsegurada.setFont(letra);
		tfSumaAsegurada.setBackground(colorFondoTexto);
		tfSumaAsegurada.setBorder(new LineBorder(borde));
		tfPremio.setFont(letra);
		tfPremio.setBackground(colorFondoTexto);
		tfPremio.setBorder(new LineBorder(borde));
		tfDescuentos.setFont(letra);
		tfDescuentos.setBackground(colorFondoTexto);
		tfDescuentos.setBorder(new LineBorder(borde));
		tfMontoTotal.setFont(letra);
		tfMontoTotal.setBackground(colorFondoTexto);
		tfMontoTotal.setBorder(new LineBorder(borde));
		
		tfApellido.setDisabledTextColor(Color.blue);
		
		confirmarDatos.setBackground(colorBoton);
		confirmarDatos.setFont(letra);
		generarPoliza.setBackground(colorBoton);
		generarPoliza.setFont(letra);
		volver.setBackground(colorBoton);
		volver.setFont(letra);
		
		seleccionTipoCobertura.setBackground(colorFondoTexto);
		seleccionTipoCobertura.setFont(letra);

		mensual.setBackground(colorFondoPantalla);
		mensual.setFont(letra);
		semestral.setBackground(colorFondoPantalla);
		semestral.setFont(letra);
		
		tablaPagos.setBackground(colorFondoTexto);
		tablaPagos.setFont(letra);
		
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBorder(new LineBorder(borde));
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setBorder(new LineBorder(borde));
		((JTextFieldDateEditor)dcFin.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setBorder(new LineBorder(borde));
	}

}
