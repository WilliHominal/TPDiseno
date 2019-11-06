package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import isi.dds.tp.controller.CU01Controller2;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.TipoCobertura;

public class CU01View2 extends JPanel  {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Poliza poliza = new Poliza();
					poliza.setCliente(GestorCliente.get().getCliente(5448044249l));
					poliza.setMotor("2222222222222222");
					poliza.setChasis("222222222");
					poliza.setAnioModelo(GestorParametrosVehiculo.get().getAnioModelo(117)); //anio mayor a 10
					//poliza.setAnioModelo(GestorParametrosVehiculo.get().getAnioModelo(118)); //anio menor a 10
					poliza.setCiudad(GestorDomicilio.get().getCiudad(112));
					poliza.setSumaAsegurada(332444f);

					JFrame frame = new JFrame();
					new CU01Controller2(frame, poliza);
					GestorTema.get().setTema(frame, "Dar de alta póliza: 22222222");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	private static final long serialVersionUID = -7012157343373007588L;
	
	private GestorTema tema = GestorTema.get();
	
	private JLabel lTipoCobertura = new JLabel("Tipo de cobertura");
	private JLabel lFechaInicioVigencia = new JLabel("Fecha de inicio de vigencia de la póliza");
	private JLabel lFormaPago = new JLabel("Forma de pago");
	private JLabel lInfoPoliza = new JLabel("Información de la póliza");
	private JLabel lTitularSeguro =  new JLabel("Titular del seguro");
	private JLabel lDatosVehiculo =  new JLabel("Datos del vehículo");
	private JLabel lValoresPoliza = new JLabel("Valores de la póliza");
	private JLabel lVigencia =  new JLabel("Vigencia");
	private JLabel lFechaInicio =  new JLabel("Inicio:");
	private JLabel lFechaFin =  new JLabel("Fin:");
	private JLabel lApellido= new JLabel("Apellido:");
	private JLabel lNombre = new JLabel("Nombre:");
	private JLabel lMarca = new JLabel("Marca:");
	private JLabel lModelo = new JLabel("Modelo:");
	private JLabel lMotor = new JLabel("Motor:");
	private JLabel lChasis = new JLabel("Chasis:");
	private JLabel lPatente = new JLabel("Patente:");
	private JLabel lSumaAsegurada= new JLabel("Suma asegurada:");
	private JLabel lPremio = new JLabel("Premio:");
	private JLabel lImportePorDescuento = new JLabel("Importe por descuento:");
	private JLabel lDescUnidad = new JLabel("-Descuento por más de una unidad");
	private JLabel lDescSemestral = new JLabel("-Descuento por pago semestral");
	private JLabel lMontoTotal = new JLabel("Monto total:");
	
	private JTextField campoApellido = new JTextField(16);
	private JTextField campoNombre = new JTextField(16);
	private JTextField campoMarca = new JTextField(16);
	private JTextField campoMotor = new JTextField(16);
	private JTextField campoModelo = new JTextField(16);
	private JTextField campoChasis = new JTextField(16);
	private JTextField campoPatente = new JTextField(16);
	private JTextField campoFin = new JTextField(10);
	private JTextField campoInicio = new JTextField(10);
	private JTextField campoSumaAsegurada = new JTextField(10);
	private JTextField campoPremio = new JTextField(10);
	private JTextField campoImportPorDescuentos = new JTextField(10);
	private JTextField campoMontoTotal = new JTextField(11);
	
	private JButton btnConfirmarDatos = new JButton("CONFIRMAR DATOS");
	private JButton btnGenerarPoliza = new JButton("GENERAR PÓLIZA");
	private JButton btnVolver = new JButton("VOLVER");
	
	private JComboBox<TipoCobertura> seleccionTipoCobertura = new JComboBox<TipoCobertura>();
	private JDateChooser dcInicioVigencia = new JDateChooser();
	
	private final ButtonGroup rbFormaPago = new ButtonGroup();
	private JRadioButton mensual = new JRadioButton("Mensual");
	private JRadioButton semestral = new JRadioButton("Semestral");
	
	private JTable tablaPagos = new JTable(6, 2);
	private JScrollPane scrollTablaPagos = new JScrollPane(tablaPagos);
	private DefaultTableModel model;
	private Object[][] datosTabla = {{""},{""}};

	public CU01View2() {
		iniciabilizarTema();
		ubicarComponentes();
		addListenerSeleccionTipoCobertura();
		addListenerCalendarioInicioVigencia();
	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets=new Insets(5, 5, 5, 55);
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.anchor=GridBagConstraints.WEST;
		constraints.gridy = 0;
		constraints.gridx = 0;
		add(lTipoCobertura, constraints);
		
		constraints.gridx = 1;
		add(lFechaInicioVigencia, constraints);
		constraints.gridx = 2;
		constraints.insets.set(5, 5, 5, 140);
		add(lFormaPago, constraints);
		constraints.insets.set(5, 5, 5, 55);
		
		constraints.gridx = 3;
		constraints.insets.set(5, 0, 5, 10);
		constraints.gridheight=2;
		add(btnConfirmarDatos, constraints);
		constraints.insets.set(5, 5, 5, 55);
		
		constraints.gridheight=1;
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.insets.set(5, 5, 0, 30);
		add(seleccionTipoCobertura, constraints);
		
		constraints.gridx = 1;
		add(dcInicioVigencia, constraints);
		constraints.insets.set(5, 5, 0, 60);
		constraints.gridx = 2;
		add(mensual, constraints);
		constraints.insets.set(5, 90, 0, 5);
		add(semestral, constraints);
		
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.gridwidth = 4;
		constraints.insets.set(0, 5, 5, 5);
		constraints.anchor = GridBagConstraints.CENTER;
		add(new JLabel("________________________________________________________________________________________________________________________________________"), constraints);
		constraints.gridwidth=1;
		
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridy = 3;
		constraints.gridwidth = 5;
		add(lInfoPoliza, constraints);
		constraints.gridwidth=1;
		
		constraints.gridy = 4;
		add(lTitularSeguro, constraints);
		
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.insets.set(5, 55, 5, 5);
		add(lApellido, constraints);
		constraints.insets.set(5, 110, 5, 5);
		add(campoApellido, constraints);
		constraints.insets.set(5, 310, 5, 5);
		add(lNombre, constraints);
		constraints.insets.set(5, 365, 5, 5);
		add(campoNombre, constraints);
		
		constraints.gridy = 6;
		constraints.insets.set(5, 5, 5, 5);
		add(lDatosVehiculo, constraints);
		
		constraints.gridy = 7;
		constraints.gridwidth = 3;
		constraints.insets.set(5, 65, 5, 5);
		add(lMarca, constraints);
		constraints.insets.set(5, 110, 5, 5);
		add(campoMarca, constraints);
		constraints.insets.set(5, 312, 5, 5);
		add(lModelo, constraints);
		constraints.insets.set(5, 365, 5, 5);
		add(campoModelo, constraints);
		
		constraints.gridy = 8;
		constraints.gridwidth = 3;
		constraints.insets.set(5, 67, 5, 5);
		add(lMotor, constraints);
		constraints.insets.set(5, 110, 5, 5);
		add(campoMotor, constraints);
		constraints.insets.set(5, 316, 5, 5);
		add(lChasis, constraints);
		constraints.insets.set(5, 365, 5, 5);
		add(campoChasis, constraints);
		constraints.insets.set(5, 570, 5, 5);
		add(lPatente, constraints);
		constraints.insets.set(5, 620, 5, 5);
		add(campoPatente, constraints);
		
		constraints.gridy = 9;
		constraints.insets.set(5, 5, 5, 5);
		add(lVigencia, constraints);
		
		constraints.gridy = 10;
		constraints.insets.set(5, 70, 5, 5);
		add(lFechaInicio, constraints);
		constraints.insets.set(5, 110, 5, 5);
		add(campoInicio, constraints);
		constraints.insets.set(5, 337, 5, 5);
		add(lFechaFin, constraints);
		constraints.insets.set(5, 365, 5, 5);
		add(campoFin, constraints);
		
		constraints.gridy = 11;
		constraints.insets.set(5, 5, 5, 5);
		add(lValoresPoliza, constraints);
		
		constraints.gridy = 12;
		constraints.gridwidth = 4;
		constraints.insets.set(5, 5, 5, 5);
		add(lSumaAsegurada, constraints);
		constraints.insets.set(5, 110, 5, 5);
		add(campoSumaAsegurada, constraints);
		constraints.insets.set(5, 315, 5, 5);
		add(lPremio, constraints);
		constraints.insets.set(5, 365, 5, 5);
		add(campoPremio, constraints);
		constraints.insets.set(5, 570, 5, 5);
		add(lImportePorDescuento, constraints);
		constraints.insets.set(5, 700, 5, 5);
		add(campoImportPorDescuentos, constraints);
		
		constraints.gridy=13;
		constraints.gridwidth = 5;
		constraints.gridheight = 7;
		constraints.insets.set(10, 5, 5, 5);
		add(scrollTablaPagos, constraints);
		
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.insets.set(10, 700, 5, 5);
		add(lDescUnidad, constraints);
		constraints.insets.set(35, 700, 5, 5);
		add(lDescSemestral, constraints);

		constraints.gridy=20;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 92, 5, 5);
		add(lMontoTotal, constraints);
		constraints.insets.set(5, 162, 5, 5);
		add(campoMontoTotal, constraints);
		constraints.insets.set(15, 645, 5, 5);
		add(btnGenerarPoliza, constraints);
		constraints.insets.set(15, 815, 5, 5);
		add(btnVolver, constraints);
	}

	private void iniciabilizarTema() {
		rbFormaPago.add(mensual);
		rbFormaPago.add(semestral);
		mensual.setSelected(true);
		lDescUnidad.setVisible(false);
		lDescSemestral.setVisible(false);				
		cargarTabla(0);
		tema.setTema(this);
		tema.setTemaSubrayado(lTipoCobertura);
		tema.setTemaSubrayado(lFechaInicioVigencia);
		tema.setTemaSubrayado(lFormaPago);
		tema.setTemaTitulo(lInfoPoliza);
		tema.setTemaSubrayado(lTitularSeguro);
		tema.setTemaSubrayado(lDatosVehiculo);
		tema.setTemaSubrayado(lVigencia);
		tema.setTemaSubrayado(lValoresPoliza);
		tema.setTema(lFechaInicio);
		tema.setTema(lFechaFin);
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
		tema.setTema(lImportePorDescuento);
		tema.setTema(lMontoTotal);
		tema.setTema(campoApellido, false);
		tema.setTema(campoNombre, false);
		tema.setTema(campoMarca, false);
		tema.setTema(campoModelo, false);
		tema.setTema(campoMotor, false);
		tema.setTema(campoChasis, false);
		tema.setTema(campoPatente, false);
		tema.setTema(campoInicio, false);
		tema.setTema(campoFin, false);
		tema.setTema(campoSumaAsegurada, false);
		tema.setTema(campoPremio, false);
		tema.setTema(campoImportPorDescuentos, false);
		tema.setTema(campoMontoTotal, false);
		campoMontoTotal.setHorizontalAlignment(JTextField.RIGHT);
		tema.setTema(btnConfirmarDatos, true);
		tema.setTema(btnGenerarPoliza, false);
		tema.setTema(btnVolver, true);
		tema.setComboBoxGrande(seleccionTipoCobertura, true);
		tema.setTema(tablaPagos, false);
		tema.setTema(scrollTablaPagos, false);
		tema.setTema(mensual);
		tema.setTema(semestral);
		tema.setTema(dcInicioVigencia, true);
		scrollTablaPagos.setPreferredSize(new Dimension(270, 120));
	}	
	
	public void cargarTabla(Integer cantidadCuotas) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, 6) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		model = tableModel;		
		tablaPagos.setModel(tableModel);
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		tablaPagos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaPagos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaPagos.getColumnModel().getColumn(0).setPreferredWidth(135);
		tablaPagos.getColumnModel().getColumn(1).setPreferredWidth(135);
		tablaPagos.getColumnModel().getColumn(0).setHeaderValue("Último día de pago");
		tablaPagos.getColumnModel().getColumn(1).setHeaderValue("Monto");
	}
	
	public void cargarDatosTabla(String valorAgregado, Integer x, Integer y) {
		model.setValueAt(valorAgregado, x, y);	
	}
	
	public void componentesAlConfirmarDatos(Boolean tipoCoberturaError, Boolean inicioVigenciaError, Integer cantPolizas) {
		if((tipoCoberturaError || inicioVigenciaError)) {
			if(tipoCoberturaError) {
				tema.erroneo(seleccionTipoCobertura);
			}
			else {
				tema.erroneo(((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()));
			}
		}
		else {
			tema.setTema(dcInicioVigencia, true);
			tema.setTema(btnGenerarPoliza, true);
			if (semestral.isSelected()) {
				lDescSemestral.setVisible(true);
			}
			if (cantPolizas > 1) {
				lDescUnidad.setVisible(true);
			}
		}
	}
	
	public void addListenerBtnConfirmarDatos(ActionListener listener) {
		btnConfirmarDatos.addActionListener(listener);
	}

	public void addListenerBtnGenerarPoliza(ActionListener listener) {
		btnGenerarPoliza.addActionListener(listener);
	}
	
	public void addListenerBtnVolver(ActionListener listener) {
		btnVolver.addActionListener(listener);
	}
	
	private void addListenerSeleccionTipoCobertura() {
		seleccionTipoCobertura.addActionListener (a -> {
			//listener para sacar color rojo cuando lo selecciona
			tema.setComboBoxGrande(seleccionTipoCobertura, true);
		});
	}
	
	private void addListenerCalendarioInicioVigencia() {
		((JTextFieldDateEditor)dcInicioVigencia.getDateEditor()).addActionListener (a -> {
			tema.setTema(dcInicioVigencia, true);
		});
	}
	
	public void addTipoCobertura(TipoCobertura tipoCobertura) {
		seleccionTipoCobertura.addItem(tipoCobertura);
	}
	
	public void setInicioVigencia(Date minimoInicioVigencia, Date maximoInicioVigencia) {
		dcInicioVigencia.setDate(minimoInicioVigencia);
		dcInicioVigencia.setSelectableDateRange(minimoInicioVigencia, maximoInicioVigencia);
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.campoInicio.setText(fechaInicio);
	}
	
	public void setFechaFin(String fechaFin) {
		this.campoFin.setText(fechaFin);
	}

	public void setApellido(String apellido) {
		this.campoApellido.setText(apellido);
	}

	public void setNombre(String nombre) {
		this.campoNombre.setText(nombre);
	}

	public void setMarca(String marca) {
		this.campoMarca.setText(marca);
	}

	public void setMotor(String motor) {
		this.campoMotor.setText(motor);
	}

	public void setModelo(String modelo) {
		this.campoModelo.setText(modelo);
	}

	public void setChasis(String chasis) {
		this.campoChasis.setText(chasis);
	}

	public void setPatente(String patente) {
		this.campoPatente.setText(patente);
	}

	public void setSumaAsegurada(String sumaAsegurada) {
		this.campoSumaAsegurada.setText("$ "+sumaAsegurada);
	}
	
	public void setPremio(String premio) {
		this.campoPremio.setText("$ "+premio);
	}
	
	public void setDescuento(String descuento) {
		campoImportPorDescuentos.setText("$ "+descuento);
	}
	
	public void setMontoTotal(String montoTotal) {
		campoMontoTotal.setText("$ "+montoTotal);
	}
	
	public Date getInicioVigencia() {
		return dcInicioVigencia.getDate();
	}
	
	public TipoCobertura getTipoCobertura() {
		return seleccionTipoCobertura.getItemAt(seleccionTipoCobertura.getSelectedIndex());
	}
	
	public String[] getValorTabla(Integer x) {
		return ((String)model.getValueAt(x, 1)).split(" ");
	}
	
	public Boolean eligioMensual() {
		return mensual.isSelected();
	}

	public void visualizarDescuentos(Boolean descuentoMasDeUnaUnidad, Boolean descuentoSemestral) {
		lDescSemestral.setVisible(descuentoSemestral);
		lDescUnidad.setVisible(descuentoMasDeUnaUnidad);
	}

	public void inhabilitarSeleccion() {
		tema.setTema(seleccionTipoCobertura, false);
	}
}