package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.TipoCobertura;

@SuppressWarnings("serial")
public class CU01_AltaPoliza2 extends JPanel  {
	
	
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU01_AltaPoliza2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CU01_AltaPoliza2() {				
		poliza = new Poliza(2222222111l);
		Cliente cliente1 = GestorCliente.get().getCliente(123456l);
		poliza.setCliente(cliente1);		
		JFrame frame = new JFrame();
		new CU01_AltaPoliza2(frame, poliza);
		GestorTema.get().setTema(frame, "Dar de alta póliza: GENERAR PÓLIZA");
	}
	
	private Poliza poliza;
	private JFrame ventana;
	private CU01_AltaPoliza1 cu01_ap1;
	private GestorTema tema = GestorTema.get();
	
	private String tituloAnterior = "";
	
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
	
	//private JTable tablaPagos = new JTable(6, 2);
	private JScrollPane scrollTablaPagos;
	private JTable tablaPagos = new JTable(6, 2);
	private DefaultTableModel model = (DefaultTableModel) tablaPagos.getModel();


	public CU01_AltaPoliza2(JFrame ventana, Poliza poliza) {
		this.ventana = ventana;
		this.poliza = poliza;
		this.tituloAnterior = ventana.getTitle();
		try {			
			cu01_ap1 = (CU01_AltaPoliza1) ventana.getContentPane();	
		}catch(Exception ex) {
			cu01_ap1 = null;
		}
		ventana.setContentPane(this);
		
		inicializarComponentes();
		iniciabilizarTema();
		ubicarComponentes();		
		comportamiento();
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


	private void inicializarComponentes() {
		ArrayList<TipoCobertura> tipoCoberturas = (ArrayList<TipoCobertura>) GestorTipoCobertura.get().getTiposCobertura();
		Iterator<TipoCobertura> iteradorTipoCoberturas = tipoCoberturas.iterator();
		seleccionTipoCobertura.addItem(new TipoCobertura("Seleccionar tipo cobertura"));
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
			e.printStackTrace();
		}
		
		dcInicioVigencia.setPreferredSize(new Dimension(105, 20));
		dcInicio.setPreferredSize(new Dimension(105, 20));
		dcFin.setPreferredSize(new Dimension(105, 20));
		
		btnConfirmarDatos.setPreferredSize(new Dimension(160, 25));
		btnGenerarPoliza.setPreferredSize(new Dimension(160, 25));
		btnVolver.setPreferredSize(new Dimension(160, 25));
		mensual.setSelected(true);

		lDescUnidad.setVisible(false);
		lDescSemestral.setVisible(false);
				

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
	
	private void iniciabilizarTema() {
		tema.setTema(this);

		tema.setTemaSubrayado(lTipoCobertura);
		tema.setTemaSubrayado(lFechaInicioVigencia);
		tema.setTemaSubrayado(lFormaPago);
		tema.setTemaSubrayado(lInfoPoliza);
		tema.setTemaSubrayado(lTitularSeguro);
		tema.setTemaSubrayado(lDatosVehiculo);
		tema.setTemaSubrayado(lFechaInicio);
		tema.setTemaSubrayado(lFechaFin);

		tema.setTema(lDescUnidad);
		tema.setTema(lDescSemestral);
		tema.setTema(lApellido);
		tema.setTema(lNombre);
		tema.setTema(lModelo);
		tema.setTema(lMarca);
		tema.setTema(lMotor);
		tema.setTema(lChasis);
		tema.setTema(lPatente);
		tema.setTema(lSumaAsegurada);
		tema.setTema(lPremio);
		tema.setTema(lDescuento);
		tema.setTema(lMontoTotal);
		
		tema.setTema(campoApellido, false);
		tema.setTema(campoNombre, false);
		tema.setTema(campoMarca, false);
		tema.setTema(campoModelo, false);
		tema.setTema(campoMotor, false);
		tema.setTema(campoChasis, false);
		tema.setTema(campoPatente, false);
		tema.setTema(campoSumaAsegurada, false);
		tema.setTema(campoPremio, false);
		tema.setTema(campoDescuentos, false);
		tema.setTema(campoMontoTotal, false);
		
		tema.setTema(btnConfirmarDatos, true);
		tema.setTema(btnGenerarPoliza, false);
		tema.setTema(btnVolver, true);
		
		tema.setTema(seleccionTipoCobertura, true);
		
		tema.setTema(mensual);
		tema.setTema(semestral);
		
		tema.setTema(tablaPagos, false);
		tema.setTema(scrollTablaPagos, false);
		
		tema.setTema(dcInicioVigencia, true);
		tema.setTema(dcInicio, false);
		tema.setTema(dcFin, false);
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
		
		btnGenerarPoliza.addActionListener(a -> {

			if(JOptionPane.showConfirmDialog(ventana, "¿Desea generar la póliza?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				GestorPoliza gp = GestorPoliza.get();
				gp.altaPoliza(poliza);

				//veo si es cliente Plata o Activo
				Boolean esPlata = true;

				//TODO actualizar para siniestros
			//	if (!poliza.getCliente().getNumerosSiniestrosUltimoAnios().equals(EnumSiniestros.NINGUNO))
				//	esPlata = false;

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
					//diria que si modificas el cliente de la poliza, se actualiza el cliente de la base de datos al persistir la poliza
					gc.actualizarCondicion(poliza.getCliente(), EnumCondicion.PLATA);
				else
					gc.actualizarCondicion(poliza.getCliente(), EnumCondicion.ACTIVO);

				System.out.println(poliza.getCliente().getCondicion());

				JOptionPane.showConfirmDialog(ventana, "Póliza generada correctamente.", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnVolver.addActionListener(a -> {
			if(JOptionPane.showConfirmDialog(ventana, "¿Desea corregir algún dato ingresado?", "Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				ventana.setContentPane(cu01_ap1);
				ventana.setTitle(tituloAnterior);
				this.setVisible(false);
			}
		});
		
		//listener para sacar color rojo cuando lo selecciona
		seleccionTipoCobertura.addActionListener (a -> {
			tema.setTema(seleccionTipoCobertura, true);
		});
	}
		
	public Boolean condicionesConfirmarDatos(){
		
		String errorTipoCobertura = "";
		String errorFechaVigencia = "";
		Boolean valido = true;
		
		//verifico datos
		if (seleccionTipoCobertura.getSelectedIndex() == 0) {
			tema.erroneo(seleccionTipoCobertura);
			errorTipoCobertura = "Seleccione un tipo de cobertura.\n";
			valido = false;
		}		
		
		
		SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
		
		Date fechaInicioVigenciaDate = null;

		try {
			fechaInicioVigenciaDate = formato.parse(formato.format(dcInicioVigencia.getDate()));
		} catch (ParseException e) {
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
			tema.erroneo(((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()));
			valido = false;
		}
		else {
			tema.setTema(dcInicioVigencia, true);
		}
		
		if(!valido) {
			JOptionPane.showConfirmDialog(this, errorTipoCobertura + errorFechaVigencia , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		else {			
			/*
			btnConfirmarDatos.setEnabled(false);
			seleccionTipoCobertura.setEnabled(false);
			
			mensual.setEnabled(false);
			semestral.setEnabled(false);
			tema.seleccion(seleccionTipoCobertura, false);
			tema.calendario(dcInicioVigencia, false);
			*/
			
			dcInicioVigencia.setEnabled(true);
			
			tema.setTema(btnGenerarPoliza, true);
			
			if (semestral.isSelected()) {
				lDescSemestral.setVisible(true);
			}
			if (poliza.getCliente().getPolizas().size() > 1) {
				lDescUnidad.setVisible(true);
			}
			

		}
		return valido;
	}
}