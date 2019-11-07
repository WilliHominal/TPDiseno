package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.view.CU01View2;

public class CU01Controller2 {
	private CU01View2 view2;
	private CU01Controller1 controller1;
	
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	private Poliza poliza;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	public CU01Controller2(JFrame ventana, Poliza poliza) {
		this.poliza = poliza;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
		setView2();
	}

	private void setView2() {
		view2 = new CU01View2();
		setFechaInicioVigenciaDefault();
		addSeleccionTipoCobertura();
		view2.addListenerBtnConfirmarDatos(new ListenerBtnConfirmarDatos());
		view2.addListenerBtnGenerarPoliza(new ListenerBtnGenerarPoliza());
		view2.addListenerBtnVolver(new ListenerBtnVolver());
		ventana.setContentPane(view2);
		ventana.revalidate();
	}
	
	public void setCU01Controller1(CU01Controller1 controller1) {
		this.controller1 = controller1;
	}

	private void setFechaInicioVigenciaDefault() {
		try {
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaParseada); 
		      calendar.add(Calendar.DAY_OF_YEAR, 1);  
		      calendar.getTime(); 
		      
		      Calendar calendar2 = Calendar.getInstance();
		      calendar2.setTime(fechaParseada); 
		      calendar2.add(Calendar.MONTH, 1); 
		      calendar2.getTime(); 
		      
		      view2.setInicioVigencia(calendar.getTime(), calendar2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void addSeleccionTipoCobertura() {
		GestorTipoCobertura gestorTipoCobertura = GestorTipoCobertura.get();
		if(gestorPoliza.esModeloViejo(poliza)) {
			view2.addTipoCobertura(gestorTipoCobertura.getTipoCobertura(EnumTipoCobertura.RESPONSABILIDAD_CIVIL));
			view2.inhabilitarSeleccion();
		}
		else {
			ArrayList<TipoCobertura> tipoCoberturas = (ArrayList<TipoCobertura>) gestorTipoCobertura.getTiposCobertura();
			if(tipoCoberturas.size() == 1) {
				view2.addTipoCobertura(tipoCoberturas.get(0));
				view2.inhabilitarSeleccion();
			}
			else {
				Iterator<TipoCobertura> iteradorTipoCoberturas = tipoCoberturas.iterator();
				view2.addTipoCobertura(new TipoCobertura("Seleccionar tipo cobertura"));
				while(iteradorTipoCoberturas.hasNext()){
					view2.addTipoCobertura(iteradorTipoCoberturas.next());
				}	
			}	
		}
	}

	private Boolean condicionesConfirmarDatos(){
		String errorTipoCobertura = "";
		String errorFechaVigencia = "";
		Boolean tipoCoberturaError = false, inicioVigenciaError = false;

		if (view2.getTipoCobertura().equals(new TipoCobertura("Seleccionar tipo cobertura"))) {
			tipoCoberturaError = true;
			errorTipoCobertura = "Seleccione un tipo de cobertura.\n";
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaInicioVigenciaDate = null;

		try {
			fechaInicioVigenciaDate = formato.parse(formato.format(view2.getInicioVigencia()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LocalDate fechaInicioVigenciaLocalDate = fechaInicioVigenciaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if(gestorPoliza.vigenciaFechaInicioVigencia(fechaInicioVigenciaLocalDate)){
			errorFechaVigencia = "La fecha de inicio de vigencia debe estar dentro del mes próximo respecto a la fecha actual.\n";
			inicioVigenciaError = true;
		}
		
		view2.componentesAlConfirmarDatos(tipoCoberturaError, inicioVigenciaError, poliza.getCliente().getPolizas().size());
		if(tipoCoberturaError || inicioVigenciaError) {
			JOptionPane.showConfirmDialog(ventana, errorTipoCobertura + errorFechaVigencia , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		return !(tipoCoberturaError || inicioVigenciaError);
	}

	//----------------- LISTENER
	private class ListenerBtnConfirmarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!condicionesConfirmarDatos()) {
				return;
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			LocalDate inicioVigencia = view2.getInicioVigencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Boolean descuentoMasDeUnaUnidad = false, descuentoSemestral = false;	
			
			if(view2.eligioMensual()) {
				gestorPoliza.actualizarPoliza(poliza, view2.getTipoCobertura(), inicioVigencia, EnumFormaPago.MENSUAL);
			}
			else {
				gestorPoliza.actualizarPoliza(poliza, view2.getTipoCobertura(), inicioVigencia, EnumFormaPago.SEMESTRAL );
				descuentoSemestral = true;
			}
			
			if (poliza.getCliente().getPolizas().size() > 0) {
				descuentoMasDeUnaUnidad = true;
			}

			gestorPoliza.calcularPremio(poliza, descuentoSemestral);
			
			Float premio = poliza.getValorPremio();
			Float descuento = poliza.getValorDescuento();
			Float montoTotal = premio - descuento;
			Float montoCuota = montoTotal / 6;			
			
			view2.setApellido(poliza.getCliente().getApellido());
			view2.setNombre(poliza.getCliente().getNombre());
			view2.setModelo(poliza.getAnioModelo().getModelo().getNombre());
			view2.setMarca(poliza.getAnioModelo().getModelo().getMarca().getNombre());
			view2.setMotor(poliza.getMotor());
			view2.setChasis(poliza.getChasis());
			view2.setPatente(poliza.getPatente());

			view2.visualizarDescuentos(descuentoMasDeUnaUnidad, descuentoSemestral);
			view2.setFechaInicio(dateFormat.format(view2.getInicioVigencia()));
			view2.setFechaFin(dateFormat.format(Date.from(inicioVigencia.plusMonths(6).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
			
			Locale.setDefault(Locale.US);
			DecimalFormat num = new DecimalFormat("#,###.00");
			
			view2.setPremio(num.format(premio));
			view2.setSumaAsegurada(num.format(poliza.getSumaAsegurada()));
			view2.setDescuento(num.format(descuento));
			
			
			if(view2.eligioMensual()) {
				view2.cargarTabla(6);
				LocalDate fechaMensual = inicioVigencia.minusDays(1);
				for (int contador=0; contador<6; contador++) {	
					view2.cargarDatosTabla(dateFormat.format(Date.from(fechaMensual.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())), contador, 0);
					view2.cargarDatosTabla("$ " + num.format(montoCuota), contador, 1);
					gestorPoliza.addCuota(poliza, montoCuota, fechaMensual);
					fechaMensual = fechaMensual.plusMonths(1);					
				}				
			} else {
				LocalDate fechaSemestral = inicioVigencia.minusDays(1);
				view2.cargarTabla(1);
				view2.cargarDatosTabla(dateFormat.format(Date.from(fechaSemestral.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())), 0, 0);
				view2.cargarDatosTabla("$ " + num.format(montoTotal), 0, 1);
				gestorPoliza.addCuota(poliza, montoTotal, fechaSemestral);
			}
			view2.setMontoTotal(num.format(montoTotal));
			
			setFechaInicioVigenciaDefault();
		}
	}
	
	private class ListenerBtnGenerarPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int seleccion = JOptionPane.showConfirmDialog(ventana, "¿Desea generar la póliza?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(seleccion == 0) {
				if(gestorPoliza.altaPoliza(poliza)) {
					JOptionPane.showConfirmDialog(ventana, "Póliza generada correctamente.", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					controller1.volver();
					view2.setVisible(false);
					HibernateUtil.cerrarSessionesUsadas();
				}
			}
		}
	}
	
	private class ListenerBtnVolver implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(ventana, "¿Desea corregir algún dato ingresado?", "Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				ventana.setContentPane(panelAnterior);
				ventana.setTitle(tituloAnterior);
				view2.setVisible(false);
			}
		}
	}	
}
