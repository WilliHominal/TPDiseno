package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.view.CU01View2;

public class CU01Controller2 {
	private CU01View2 altaPoliza2;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	@SuppressWarnings("unused")
	private String tituloAnterior = "";
	
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	
	private Poliza poliza;
	
	public CU01Controller2(JFrame ventana, Poliza poliza) {
		this.poliza = poliza;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
			
		setView2_AltaPoliza2();
	}

	private void setView2_AltaPoliza2() {
		altaPoliza2 = new CU01View2();
		setFechaInicioVigenciaDefault();
		addSeleccionTipoCobertura();
		altaPoliza2.addListenerBtnConfirmarDatos(new ListenerView2ConfirmarDatos());
		altaPoliza2.addListenerBtnGenerarPoliza(new ListenerView2GenerarPoliza());
		altaPoliza2.addListenerBtnVolver(new ListenerView2Volver());
		ventana.setContentPane(altaPoliza2);
		ventana.revalidate();
	}
	
	private void setFechaInicioVigenciaDefault() {
		try {
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaParseada); 
		      calendar.add(Calendar.DAY_OF_YEAR, 1);  
		      calendar.getTime(); 
		      altaPoliza2.setInicioVigencia(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void addSeleccionTipoCobertura() {
		ArrayList<TipoCobertura> tipoCoberturas = (ArrayList<TipoCobertura>) GestorTipoCobertura.get().getTiposCobertura();
		Iterator<TipoCobertura> iteradorTipoCoberturas = tipoCoberturas.iterator();
		altaPoliza2.addTipoCobertura(new TipoCobertura("Seleccionar tipo cobertura"));
		while(iteradorTipoCoberturas.hasNext()){
			altaPoliza2.addTipoCobertura(iteradorTipoCoberturas.next());
		}
	}

	private Boolean condicionesConfirmarDatos(){
		String errorTipoCobertura = "";
		String errorFechaVigencia = "";
		Boolean tipoCoberturaError = false, inicioVigenciaError = false;

		if (altaPoliza2.getTipoCobertura().equals(new TipoCobertura("Seleccionar tipo cobertura"))) {
			tipoCoberturaError = true;
			errorTipoCobertura = "Seleccione un tipo de cobertura.\n";
		}	
		
		SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
		Date fechaInicioVigenciaDate = null;

		try {
			fechaInicioVigenciaDate = formato.parse(formato.format(altaPoliza2.getInicioVigencia()));
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
			inicioVigenciaError = true;
		}
		
		altaPoliza2.componentesAlConfirmarDatos(tipoCoberturaError, inicioVigenciaError, poliza.getCliente().getPolizas().size());
		if(tipoCoberturaError || inicioVigenciaError) {
			JOptionPane.showConfirmDialog(ventana, errorTipoCobertura + errorFechaVigencia , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		return !(tipoCoberturaError || inicioVigenciaError);
	}

	//----------------- LISTENER
	private class ListenerView2ConfirmarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!condicionesConfirmarDatos()) {
				return;
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar fechaFinVigencia = Calendar.getInstance();
			fechaFinVigencia.setTime(altaPoliza2.getInicioVigencia());
			fechaFinVigencia.add(Calendar.MONTH, 6);
			
			Calendar fechaAnteriorAInicioVigencia = Calendar.getInstance();
			fechaAnteriorAInicioVigencia.setTime(altaPoliza2.getInicioVigencia());
			fechaAnteriorAInicioVigencia.add(Calendar.DATE, -1);
			
			LocalDate inicioVigencia = altaPoliza2.getInicioVigencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate finVigencia = fechaFinVigencia.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						
			Boolean descuentoMasDeUnaUnidad = false, descuentoSemestral = false;
			if (poliza.getCliente().getPolizas().size() > 1) descuentoMasDeUnaUnidad = true;
			if (!altaPoliza2.eligioMensual()) descuentoSemestral = true;
			
			if(altaPoliza2.eligioMensual()) {
				gestorPoliza.actualizarPoliza(poliza, altaPoliza2.getTipoCobertura(), inicioVigencia, finVigencia, EnumFormaPago.MENSUAL);
			}
			else {
				gestorPoliza.actualizarPoliza(poliza, altaPoliza2.getTipoCobertura(), inicioVigencia, finVigencia,EnumFormaPago.SEMESTRAL );
			}
			
			
			Float premio = gestorPoliza.calcularPremio(poliza);
			Float descuento = gestorPoliza.calcularDescuento(poliza, descuentoSemestral);
			Float montoCuota = 2f;
			Float montoTotal = 0f;
			
			
			altaPoliza2.setApellido(poliza.getCliente().getApellido());
			altaPoliza2.setNombre(poliza.getCliente().getNombre());
			altaPoliza2.setModelo(poliza.getAnioModelo().getModelo().getNombre());
			altaPoliza2.setMarca(poliza.getAnioModelo().getModelo().getMarca().getNombre());
			altaPoliza2.setMotor(poliza.getMotor());
			altaPoliza2.setChasis(poliza.getChasis());
			altaPoliza2.setPatente(poliza.getPatente());
			altaPoliza2.setSumaAsegurada(poliza.getSumaAsegurada().toString());
			altaPoliza2.setPremio(premio.toString());
			altaPoliza2.setDescuento(Float.toString(descuento));
			altaPoliza2.visualizarDescuentos(descuentoMasDeUnaUnidad, descuentoSemestral);
			altaPoliza2.setFechaInicio(dateFormat.format(altaPoliza2.getInicioVigencia()));
			altaPoliza2.setFechaFin(dateFormat.format(fechaFinVigencia.getTime()));
			
			if(altaPoliza2.eligioMensual()) {
				altaPoliza2.cargarTabla(6);
				for (int contador=0; contador<6; contador++) {
					Calendar fechaAux = Calendar.getInstance(); 
					fechaAux.setTime(fechaAnteriorAInicioVigencia.getTime());
					fechaAux.add(Calendar.MONTH, contador);
					altaPoliza2.cargarDatosTabla(dateFormat.format(fechaAux.getTime()), contador, 0);
					altaPoliza2.cargarDatosTabla("$ " + montoCuota, contador, 1); //CAMBIAR 2 POR MONTO DE LA CUOTA
					String auxMonto[] = altaPoliza2.getValorTabla(contador);
					montoTotal += Float.parseFloat(auxMonto[1]);
				}				
				altaPoliza2.setMontoTotal("$ " + Float.toString(montoTotal));
			} else {
				montoTotal = 5f;
				altaPoliza2.cargarTabla(1);
				altaPoliza2.cargarDatosTabla(dateFormat.format(fechaAnteriorAInicioVigencia.getTime()), 0, 0);
				altaPoliza2.cargarDatosTabla("$ " + montoTotal, 0, 1);
				altaPoliza2.setMontoTotal("$ " + montoTotal);
			}
			
			setFechaInicioVigenciaDefault();
		}
	}
	
	private class ListenerView2GenerarPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(ventana, "¿Desea generar la póliza?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0) {
				gestorPoliza.altaPoliza(poliza);
				//TODO cuando no se genere una poliza, no lanzar el joptionpane
				JOptionPane.showConfirmDialog(ventana, "Póliza generada correctamente.", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				//TODO mostrar poliza
			}
		}
	}
	
	private class ListenerView2Volver implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(ventana, "¿Desea corregir algún dato ingresado?", "Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
				ventana.setContentPane(panelAnterior);
				ventana.setTitle("Dar de alta póliza: INGRESAR DATOS");
				altaPoliza2.setVisible(false);
			}
		}
	}	
}
