package isi.dds.tp.app;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import isi.dds.tp.enums.*;
import isi.dds.tp.gestor.*;
import isi.dds.tp.modelo.*;

@SuppressWarnings("serial")
public class CU01_AltaPolizaV2 extends JPanel  {
	
	/////////////////////////////////////////////////////////////////BORRAR CLIENTE + POLIZA Y USAR LOS DATOS QUE LE PASA LA OTRA INTERFAZ
	public Cliente cliente = new Cliente(new Ciudad(new Provincia(new Pais("PA1"), "PR1"), "C1", 0), 123456l, EnumCondicion.NORMAL, "APE", "NOM", EnumTipoDocumento.DNI, 11111111, 
			2011111118l, EnumSexo.MASCULINO, LocalDate.now(), "CALLE", 123, 3, "C", 2020, EnumCondicionIVA.CONSUMIDOR_FINAL, "correo.xd", EnumEstadoCivil.CASADO, "PROF", 2019);

	//public Poliza poliza = new Poliza(1231212l);
	public Poliza pol2 = new Poliza(123l);
	public List<Poliza> polizas = new ArrayList<Poliza>();
	/////////////////////////////////////////////////////////////////
	
	private Object tema[];
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetraBloqueado, colorLetra, colorErroneo;
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
	private JLabel lDesc1 = new JLabel("-Descuento por más de una unidad");
	private JLabel lDesc2 = new JLabel("-Descuento por pago semestral");
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
	private JTextField tfSumaAsegurada = new JTextField(10);
	private JTextField tfPremio = new JTextField(10);
	private JTextField tfDescuentos = new JTextField(10);
	private JTextField tfMontoTotal = new JTextField(10);
	
	private JButton btnConfirmarDatos = new JButton("CONFIRMAR DATOS");
	private JButton btnGenerarPoliza = new JButton("GENERAR PÓLIZA");
	private JButton btnVolver = new JButton("VOLVER");
	
	private JComboBox<String> seleccionTipoCobertura = new JComboBox<String>();
	
	private final ButtonGroup rbFormaPago = new ButtonGroup();
	private JRadioButton mensual = new JRadioButton("Mensual");
	private JRadioButton semestral = new JRadioButton("Semestral");
	
	JTable tablaPagos = new JTable(6,2);
	JScrollPane scrollTablaPagos;
	
	//QUITAR UNA VEZ CREADO BIEN LA POLIZA

	public CU01_AltaPolizaV2(JFrame ventana, Object[] tema, Poliza poliza) {

		/////////////////////////////////////////////////////////////////BORRAR
		polizas.add(poliza);
		polizas.add(pol2);
		cliente.setPolizas(polizas);
		poliza.setCliente(cliente);
		AnioModelo am = new AnioModelo();
		Marca mar = new Marca( "Marca1");
		Modelo mod = new Modelo(mar, "Modelo1", 0.54f);
		am.setAnio(1997);
		am.setModelo(mod);
		poliza.setAnioModelo(am);

		/////////////////////////////////////////////////////////////////BORRAR

		this.tema = tema;
		inicializarComponentes();
		inicializarTema();
		ubicarComponentes();
		
		DefaultCellEditor editor = (DefaultCellEditor) tablaPagos.getDefaultEditor(Object.class);
		editor.setClickCountToStart(10000);
			
		//setear fecha default dia inicio vigencia
		Date diaActual = new Date();
		Calendar diaManana = Calendar.getInstance(); 
		Calendar diaMesProximo = Calendar.getInstance(); 
		diaManana.setTime(diaActual); 
		diaMesProximo.setTime(diaManana.getTime()); 
		diaManana.add(Calendar.DATE, 1);
		diaMesProximo.add(Calendar.MONTH, 1);
		dcInicioVigencia.setDate(diaManana.getTime());

		//datos auxiliares para calcular si la fecha inicio vigencia esta dentro de los limites permitidos
		int diaDelMes1 = diaManana.get(Calendar.DAY_OF_MONTH);
		int mesDelAnio1 = diaManana.get(Calendar.MONTH);
		int anioActual1 = diaManana.get(Calendar.YEAR);
		int mesDelAnio2 = diaMesProximo.get(Calendar.MONTH);
		int anioActual2 = diaMesProximo.get(Calendar.YEAR);
		
		//ESTO definirlo como atributos de clase y/o en los metodos de ubicar componenes
		
		
		
		//Listener Botones
		btnConfirmarDatos.addActionListener(a -> {
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
			btnConfirmarDatos.setEnabled(false);
			dcInicioVigencia.setEnabled(false);
			seleccionTipoCobertura.setEnabled(false);
			mensual.setEnabled(false);
			semestral.setEnabled(false);
			
			//cargo datos parte inferior
			btnGenerarPoliza.setEnabled(true);

			tfApellido.setText(poliza.getCliente().getApellido());
			tfNombre.setText(poliza.getCliente().getNombre());
			tfModelo.setText(poliza.getAnioModelo().getModelo().getNombre());
			tfMarca.setText(poliza.getAnioModelo().getModelo().getMarca().getNombre());
			tfMotor.setText(poliza.getMotor());
			tfChasis.setText(poliza.getChasis());
			tfPatente.setText(poliza.getPatente());

			if (poliza.getCliente().getPolizas().size() > 1){lDesc1.setVisible(true);}
			if (poliza.getCliente().getPolizas().size() > 1)
				lDesc1.setVisible(true);

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

		//listener para sacar color rojo cuando lo selecciona
		seleccionTipoCobertura.addActionListener (a -> {
			seleccionTipoCobertura.setForeground(colorLetra);
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
				if (gc.calcularTiempoActivo(poliza.getCliente()) < 365*2)
					esPlata = false;

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
				//VOLVER A VENTANA ANTERIOR
				new CU01_AltaPolizaV2(ventana, tema, new Poliza(123123l)); //CAMBIAR POR VENTANA ANTERIOR
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
		constraints.insets.set(5, 5, 0, 55);
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
		constraints.anchor = GridBagConstraints.CENTER;
		add(new JLabel("________________________________________________________________________________________________________________________________________"), constraints);
		constraints.gridwidth=1;
		
		//FILA 3
		constraints.anchor = GridBagConstraints.WEST;
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
		
		add(lDesc1, constraints);
		constraints.insets.set(5, 5, 5, 5);
		
		//FILA 12
		constraints.gridy=12;
		constraints.gridx=0;
		constraints.gridwidth=4;
		constraints.insets.set(5, 770, 5, 5);
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
		add(btnGenerarPoliza, constraints);
		constraints.insets.set(15, 815, 5, 5);
		add(btnVolver, constraints);
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
			
		tfApellido.setDisabledTextColor(colorLetraBloqueado);
		tfApellido.setFont(letra);
		tfApellido.setBackground(colorFondoTexto);
		tfApellido.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfNombre.setDisabledTextColor(colorLetraBloqueado);
		tfNombre.setFont(letra);
		tfNombre.setBackground(colorFondoTexto);
		tfNombre.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfMarca.setDisabledTextColor(colorLetraBloqueado);
		tfMarca.setFont(letra);
		tfMarca.setBackground(colorFondoTexto);
		tfMarca.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfMotor.setDisabledTextColor(colorLetraBloqueado);
		tfMotor.setFont(letra);
		tfMotor.setBackground(colorFondoTexto);
		tfMotor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfModelo.setDisabledTextColor(colorLetraBloqueado);
		tfModelo.setFont(letra);
		tfModelo.setBackground(colorFondoTexto);
		tfModelo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfChasis.setDisabledTextColor(colorLetraBloqueado);
		tfChasis.setFont(letra);
		tfChasis.setBackground(colorFondoTexto);
		tfChasis.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfPatente.setDisabledTextColor(colorLetraBloqueado);
		tfPatente.setFont(letra);
		tfPatente.setBackground(colorFondoTexto);
		tfPatente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfSumaAsegurada.setDisabledTextColor(colorLetraBloqueado);
		tfSumaAsegurada.setFont(letra);
		tfSumaAsegurada.setBackground(colorFondoTexto);
		tfSumaAsegurada.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfPremio.setDisabledTextColor(colorLetraBloqueado);
		tfPremio.setFont(letra);
		tfPremio.setBackground(colorFondoTexto);
		tfPremio.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfDescuentos.setDisabledTextColor(colorLetraBloqueado);
		tfDescuentos.setFont(letra);
		tfDescuentos.setBackground(colorFondoTexto);
		tfDescuentos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tfMontoTotal.setDisabledTextColor(colorLetraBloqueado);
		tfMontoTotal.setFont(letra);
		tfMontoTotal.setBackground(colorFondoTexto);
		tfMontoTotal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		
		btnConfirmarDatos.setBackground(colorBoton);
		btnConfirmarDatos.setFont(letra);
		btnGenerarPoliza.setBackground(colorBoton);
		btnGenerarPoliza.setFont(letra);
		btnVolver.setBackground(colorBoton);
		btnVolver.setFont(letra);
		
		seleccionTipoCobertura.setBackground(colorFondoTexto);
		seleccionTipoCobertura.setFont(letra);
		mensual.setBackground(colorFondoPantalla);
		mensual.setFont(letra);
		semestral.setBackground(colorFondoPantalla);
		semestral.setFont(letra);
		
		tablaPagos.setBackground(colorFondoTexto);
		tablaPagos.setFont(letra);
		
		tablaPagos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scrollTablaPagos.getViewport().setBackground(colorFondoTexto);
		//scrollTablaPagos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).setBorder(new LineBorder(borde));
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcInicio.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		((JTextFieldDateEditor)dcFin.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcFin.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	private void inicializarComponentes() {
		
		seleccionTipoCobertura.addItem("SELECCIONAR TIPO DE COBERTURA");
		seleccionTipoCobertura.addItem("RESPONSABILIDAD CIVIL");
		seleccionTipoCobertura.addItem("RESP. CIVIL, ROBO O INCENDIO TOTAL");
		seleccionTipoCobertura.addItem("TODO TOTAL");
		seleccionTipoCobertura.addItem("TERCEROS COMPLETOS");
		seleccionTipoCobertura.addItem("TODO RIESGO CON FRANQUICIA");
		
		rbFormaPago.add(mensual);
		rbFormaPago.add(semestral);
				
		dcInicioVigencia.setPreferredSize(new Dimension(100, 20));
		dcInicio.setPreferredSize(new Dimension(100, 20));
		dcFin.setPreferredSize(new Dimension(100, 20));
		
		btnConfirmarDatos.setPreferredSize(new Dimension(160, 25));
		btnGenerarPoliza.setPreferredSize(new Dimension(160, 25));
		btnVolver.setPreferredSize(new Dimension(160, 25));
				
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
		btnGenerarPoliza.setEnabled(false);
		dcInicio.setEnabled(false);
		dcFin.setEnabled(false);
		tablaPagos.setEnabled(false);
		
		mensual.setSelected(true);

		
		lDesc1.setVisible(false);
		lDesc2.setVisible(false);
				
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
	}
	
	private void comportamientoBotones() {
		
	}
	
}