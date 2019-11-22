package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import isi.dds.tp.gestor.GestorBitacora;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosPoliza;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.BitacoraParametrosPoliza;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.ParametrosPoliza;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.RiesgoCiudad;
import isi.dds.tp.modelo.RiesgoModelo;
import isi.dds.tp.modelo.RiesgoTipoCobertura;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.view.CU08View;

public class CU08Controller {
	private CU08View view;
	
	private GestorDomicilio gestorDom = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	private GestorTipoCobertura gestorCobertura = GestorTipoCobertura.get();
	private GestorParametrosPoliza gestorParametros = GestorParametrosPoliza.get();
	private GestorBitacora gestorBitacora = GestorBitacora.get();
	
	private ParametrosPoliza paramPNueva = new ParametrosPoliza();
	
	private JFrame ventana;
	private JPanel panelAnteriorAPoliza;
	private String tituloAnteriorAPoliza = "";
	
	private HashMap<String, Campo> mapCampos = new HashMap<String, Campo>();
	
	private DecimalFormat num;
	private String  keyTipoCobertura = "tipo_cobertura", keyModelo = "modelo", keyCiudad = "ciudad", keyGuardaGarage = "guarda_garage", keyTieneAlarma = "tiene_alarma",
			keyTieneRastreo = "tiene_rastreo", keyTieneTuercas = "tiene_tuercas", keyKm = "km", keyCero = "cero_siniestros", keyUno = "un_siniestro", keyDos = "dos_siniestros",
			keyMuchos = "mas_de_dos_siniestros", keyHijos = "cantidad_hijos", keyDerechoEmision = "derecho_emision", keyUnidadAdicional = "unidad_adicional";
	
	public CU08Controller(JFrame ventana) {
		this.ventana = ventana;
		this.tituloAnteriorAPoliza = ventana.getTitle();
		try {
			panelAnteriorAPoliza = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnteriorAPoliza = null;
		}
		setView();
	}
	
	private void setView() {
		Locale.setDefault(Locale.US);
		num = new DecimalFormat("0.00000000");
		GestorTema.get().setTema(ventana, "Actualizar factores");
		view = new CU08View();
		cargarElementos();
		view.addListenerBtnCancelar(new ListenerBtnCancelar());
		view.addListenerSeleccionMarca(new ListenerSeleccionMarca());
		view.addListenerSeleccionModelo(new ListenerSeleccionModelo());
		view.addListenerSeleccionProvincia(new ListenerSeleccionProvincia());
		view.addListenerSeleccionCiudad(new ListenerSeleccionCiudad());
		view.addListenerSeleccionTipoCobertura(new ListenerSeleccionTipoCobertura()); 
		view.addListenerBtnActualizarFactores(new ListenerBtnActualizar());
		
		view.addListenerCampoTipoCobertura(new ListenerCampo());
		view.addListenerCampoModelo(new ListenerCampo());
		view.addListenerCampoCiudad(new ListenerCampo());
		view.addListenerCampoGuardaGarage(new ListenerCampo());
		view.addListenerCampoTieneAlarma(new ListenerCampo());
		view.addListenerCampoTieneRastreo(new ListenerCampo());
		view.addListenerCampoTieneTuercas(new ListenerCampo());
		view.addListenerCampoKm(new ListenerCampo());
		view.addListenerCampoCeroSiniestros(new ListenerCampo());
		view.addListenerCampoUnSiniestro(new ListenerCampo());
		view.addListenerCampoDosSiniestros(new ListenerCampo());
		view.addListenerCampoMuchosSiniestros(new ListenerCampo());
		view.addListenerCampoCantidadHijos(new ListenerCampo());
		view.addListenerCampoDerechoEmision(new ListenerCampo());
		view.addListenerCampoDescuentoUnidadAdicional(new ListenerCampo());
		
		JScrollPane scroll = new JScrollPane(view);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		ventana.setContentPane(scroll);
		ventana.revalidate();		
	}
	
	public void cargarElementos() {		
		gestorParametros.nuevosParametros(paramPNueva);		
		view.setGuardaGarageActual(num.format(paramPNueva.getPorcentajeGuardaEnGarage()));
		view.setTieneAlarmaActual(num.format(paramPNueva.getPorcentajeAlarma()));
		view.setTieneRastreoActual(num.format(paramPNueva.getPorcentajeRastreoVehicular()));
		view.setTieneTuercasActual(num.format(paramPNueva.getPorcentajeTuercasAntirobo()));
		view.setKmActual(num.format(paramPNueva.getPorcentajeAjusteKm()));
		view.setCeroSiniestrosActual(num.format(paramPNueva.getPorcentajeNingunSiniestro()));
		view.setUnSiniestroActual(num.format(paramPNueva.getPorcentajeUnSiniestro()));
		view.setDosSiniestrosActual(num.format(paramPNueva.getPorcentajeDosSiniestros()));
		view.setMuchosSiniestrosActual(num.format(paramPNueva.getPorcentajeMayorADosSiniestros()));
		view.setCantidadHijosActual(num.format(paramPNueva.getPorcentajePorHijoRegistrado()));
		view.setDerechoEmisionActual(num.format(paramPNueva.getValorDerechoEmision()));
		view.setDescuentoUnidadAdicionalActual(num.format(paramPNueva.getDescuentoUnidadAdicional()));
		

		List<Provincia> provincias = gestorDom.getProvincias(1);
		Iterator<Provincia> iteratorProvincias = provincias.iterator();
		while(iteratorProvincias.hasNext()) {
			view.addProvincia(iteratorProvincias.next());
		}
		cargarCiudades();
		
		List<Marca> marcas = gestorVehiculo.getMarcas();
		Iterator<Marca> iteratorMarcas = marcas.iterator();
		while(iteratorMarcas.hasNext()) {
			view.addMarca(iteratorMarcas.next());
		}
		cargarModelos();
		
		List<TipoCobertura> tiposCobertura = gestorCobertura.getTiposCobertura();
		Iterator<TipoCobertura> iteratorTiposCobertura = tiposCobertura.iterator();
		while(iteratorTiposCobertura.hasNext()) {
			view.addTipoCobertura(iteratorTiposCobertura.next());
		}	
		view.setTipoCoberturaActual(num.format(gestorCobertura.getUltimoRiesgoTipoCobertura(view.getItemTipoCobertura().getTipoCobertura())));				
	}
	
	private void cargarCiudades() {
		List<Ciudad> ciudades = view.getItemProvincia().getCiudades();
		Iterator<Ciudad> iteratorCiudades = ciudades.iterator();
		while(iteratorCiudades.hasNext()) {
			view.addCiudad(iteratorCiudades.next());
		}
		
		view.setCiudadActual(num.format(gestorDom.getUltimoRiesgoCiudad(view.getItemCiudad().getIdCiudad())));
	}
	
	private void cargarModelos() {
		List<Modelo> modelos = view.getItemMarca().getModelos();
		Iterator<Modelo> iteratorModelos = modelos.iterator();
		while(iteratorModelos.hasNext()) {
			view.addModelo(iteratorModelos.next());
		}
		
		view.setModeloActual(num.format(gestorVehiculo.getUltimoRiesgoModelo(view.getItemModelo().getIdModelo())));
	}
	
	private boolean validarCampos() {		
		mapCampos.put(keyTipoCobertura, new Campo(") Valor de riesgo porcentual del tipo de cobertura: "));
		mapCampos.put(keyModelo, new Campo(") Valor de riesgo porcentual de modelo: "));
		mapCampos.put(keyCiudad, new Campo(") Valor de riesgo porcentual de ciudad: "));
		mapCampos.put(keyGuardaGarage, new Campo(") Valor porcentual acerca de sí el vehículo es guardado en un garage: "));
		mapCampos.put(keyTieneAlarma, new Campo(") Valor porcentual acerca de sí el vehículo tiene alarma: "));
		mapCampos.put(keyTieneRastreo, new Campo(") Valor porcentual acerca de sí el vehículo tiene rastreo vehicular: "));
		mapCampos.put(keyTieneTuercas, new Campo(") Valor porcentual acerca de sí el vehículo tiene tuercas antirrobo: "));
		mapCampos.put(keyKm, new Campo(") Valor porcentual por cada 10.00 km que el vehículo posee: "));
		mapCampos.put(keyCero, new Campo(") Valor porcentual que corresponde a si el titular del vehículo no posee siniestros en el último año: "));
		mapCampos.put(keyUno, new Campo(") Valor porcentual que corresponde a si el titular del vehículo posee un siniestro en el último año: "));
		mapCampos.put(keyDos, new Campo(") Valor porcentual que corresponde a si el titular del vehículo posee dos siniestros en el último año: "));
		mapCampos.put(keyMuchos, new Campo(") Valor porcentual que corresponde a si el titular del vehículo posee más de dos siniestros en el último año: "));
		mapCampos.put(keyHijos, new Campo(") Valor porcentual que corresponde a la recargar hecha por la cantidad de hijos registrados en la póliza: "));
		mapCampos.put(keyDerechoEmision, new Campo(") Valor que indica el costo de emitir la póliza: "));
		mapCampos.put(keyUnidadAdicional, new Campo(") Valor indicativo del descuento por poseer más de una póliza registrada: "));
		
		String avisoError = "Los siguientes campos poseen valores no válidos:\n", errorCampoNulo = " valor nulo.\n", errorCampoMayorUno = " valor porcentual mayor/igual a 1.\n";
		Integer contadorErrores = 1;	
		Boolean error = false;

		
		if(view.getCheckTipoCobertura()) {
			Campo c = mapCampos.get(keyTipoCobertura);	
			try {
				c.valor = Float.parseFloat(view.getTipoCobertura());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorTipoCobertura();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyTipoCobertura, c);	
			}
		}
		
		
		if(view.getCheckModelo()) {
			Campo c = mapCampos.get(keyModelo);	
			try {
				c.valor = Float.parseFloat(view.getModelo());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorModelo();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyModelo, c);	
			}
		}
		
		if(view.getCheckCiudad()) {
			Campo c = mapCampos.get(keyCiudad);	
			try {
				c.valor = Float.parseFloat(view.getCiudad());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorCiudad();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyCiudad, c);	
			}		
		}
		
		
		if(view.getCheckGuardaGarage()) {
			Campo c = mapCampos.get(keyGuardaGarage);	
			try {
				c.valor = Float.parseFloat(view.getGuardaGarage());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorGuardaGarage();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyGuardaGarage, c);	
			}	
		}
		
		if(view.getCheckTieneAlarma()) {
			Campo c = mapCampos.get(keyTieneAlarma);	
			try {
				c.valor = Float.parseFloat(view.getTieneAlarma());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorTieneAlarma();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyTieneAlarma, c);	
			}	
		}
		
		if(view.getCheckTieneRastreo()) {
			Campo c = mapCampos.get(keyTieneRastreo);	
			try {
				c.valor = Float.parseFloat(view.getTieneRastreo());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorTieneRastreo();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyTieneRastreo, c);	
			}	
		}
		
		if(view.getCheckTieneTuercas()) {
			Campo c = mapCampos.get(keyTieneTuercas);	
			try {
				c.valor = Float.parseFloat(view.getTieneTuercas());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorTieneTuercas();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyTieneTuercas, c);	
			}	
		}
		
		if(view.getCheckKm()) {
			Campo c = mapCampos.get(keyKm);	
			try {
				c.valor = Float.parseFloat(view.getKm());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorKm();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyKm, c);	
			}	
		}
		
		if(view.getCheckCeroSiniestros()) {
			Campo c = mapCampos.get(keyCero);	
			try {
				c.valor = Float.parseFloat(view.getCeroSiniestros());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorCeroSiniestros();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyCero, c);	
			}	
		}				
		
		if(view.getCheckUnSiniestro()) {
			Campo c = mapCampos.get(keyUno);	
			try {
				c.valor = Float.parseFloat(view.getUnSiniestro());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorUnSiniestro();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyUno, c);	
			}	
		}
		
		if(view.getCheckDosSiniestros()) {
			Campo c = mapCampos.get(keyDos);	
			try {
				c.valor = Float.parseFloat(view.getDosSiniestros());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorDosSiniestros();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyDos, c);	
			}	
		}
		
		if(view.getCheckMuchosSiniestros()) {
			Campo c = mapCampos.get(keyMuchos);	
			try {
				c.valor = Float.parseFloat(view.getMuchosSiniestros());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorMuchosSiniestros();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyMuchos, c);	
			}	
		}
		
		if(view.getCheckCantidadHijos()) {
			Campo c = mapCampos.get(keyHijos);	
			try {
				c.valor = Float.parseFloat(view.getCantidadHijos());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				contadorErrores++;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorCantidadHijos();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyHijos, c);	
			}	
		}
		
		if(view.getCheckDerechoEmision()) {
			Campo c = mapCampos.get(keyDerechoEmision);	
			try {
				c.valor = Float.parseFloat(view.getDerechoEmision());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) ) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo+errorCampoNulo;
				contadorErrores++;
				view.setErrorDerechoEmision();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyDerechoEmision, c);	
			}	
		}
		
		if(view.getCheckDescuentoUnidadAdicional()) {
			Campo c = mapCampos.get(keyUnidadAdicional);	
			try {
				c.valor = Float.parseFloat(view.getDescuentoUnidadAdicional());
			}catch(java.lang.NumberFormatException ex) {}
			
			if( !(c.valor > 0) || c.valor >= 1) {
				error = true;
				avisoError += contadorErrores+c.nombrarCampo;
				if(!(c.valor > 0))
					avisoError = avisoError+errorCampoNulo;
				else
					avisoError = avisoError+errorCampoMayorUno;
				view.setErrorDescuentoUnidadAdicional();
			}
			else {
				c.elegido = true;
				mapCampos.put(keyUnidadAdicional, c);	
			}	
		}
		
		if(error) {
			JOptionPane.showMessageDialog(ventana, avisoError, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return !error;
	}
	
	public void volver() {
		ventana.setContentPane(panelAnteriorAPoliza);
		ventana.setTitle(tituloAnteriorAPoliza);
		view.setVisible(false);
	}
	
	private class Campo{
		public Boolean elegido = false;
		public Float valor = 0f;
		public String nombrarCampo = ""; 
		
		public Campo(String n) {
			nombrarCampo = n;
		}
	}
	
	//------------- LISTENERS
	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {		
				volver();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ListenerBtnActualizar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {								
				String impresionAviso = "Se han modificado los siguientes parámetros:\n", stringFinal = ".\n"; 
				Integer contador = 1;
				Boolean modificoParam = false;
				BitacoraParametrosPoliza bitacora = gestorBitacora.newBitacoraParametros();
				
				if(!validarCampos()) {
					return;
				}	
				
				Campo campo = null;	
				
				campo = mapCampos.get(keyTipoCobertura);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					RiesgoTipoCobertura r = gestorCobertura.newRiesgoCobertura(view.getItemTipoCobertura().getTipoCobertura(), campo.valor);
					gestorBitacora.addRiesgoTipoCobertura(bitacora, r);		
				}
				
				campo = mapCampos.get(keyModelo);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					RiesgoModelo r = gestorVehiculo.newRiesgoModelo(view.getItemModelo().getIdModelo(), campo.valor);
					gestorBitacora.addRiesgoModelo(bitacora, r);	
				}
				
				campo = mapCampos.get(keyCiudad);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;						
					RiesgoCiudad r =  gestorDom.newRiesgoCiudad(view.getItemCiudad().getIdCiudad(), campo.valor);
					gestorBitacora.addRiesgoCiudad(bitacora, r);					
				}
				
				campo = mapCampos.get(keyGuardaGarage);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeGuardaEnGarage(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyTieneAlarma);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeAlarma(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyTieneRastreo);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeRastreoVehicular(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyTieneTuercas);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeTuercasAntirobo(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyKm);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeAjusteKm(campo.valor);
					modificoParam = true;
				}
				
				campo = mapCampos.get(keyCero);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeNingunSiniestro(campo.valor);
					modificoParam = true;
				}
				
				campo = mapCampos.get(keyUno);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					contador++;
					paramPNueva.setPorcentajeUnSiniestro(campo.valor);
					modificoParam = true;
				}
				
				campo = mapCampos.get(keyDos);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+(campo.valor).toString()+stringFinal;
					contador++;
					paramPNueva.setPorcentajeDosSiniestros(campo.valor);
					modificoParam = true;
				}
				
				campo = mapCampos.get(keyMuchos);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					paramPNueva.setPorcentajeMayorADosSiniestros(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyHijos);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					paramPNueva.setPorcentajePorHijoRegistrado(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyDerechoEmision);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					paramPNueva.setValorDerechoEmision(campo.valor);
					modificoParam = true;	
				}
				
				campo = mapCampos.get(keyUnidadAdicional);
				if(campo.elegido) {
					impresionAviso += contador+campo.nombrarCampo+num.format(campo.valor)+stringFinal;
					paramPNueva.setDescuentoUnidadAdicional(campo.valor);
					modificoParam = true;	
				}

				if(modificoParam) {
					gestorBitacora.addParametrosPoliza(bitacora, paramPNueva);
				}					
				
				
				gestorBitacora.registrarCambiosEnParametros(bitacora);
			
				JOptionPane.showMessageDialog(ventana, impresionAviso, "Aviso", JOptionPane.INFORMATION_MESSAGE);
				HibernateUtil.cerrarSessionesUsadas();
				volver();				
			}catch(Exception ex) {
				ex.printStackTrace();
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
	
	private class ListenerSeleccionTipoCobertura implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.setTipoCoberturaActual(gestorCobertura.getUltimoRiesgoTipoCobertura(view.getItemTipoCobertura().getTipoCobertura()).toString());				
		}
	}
	
	private class ListenerSeleccionMarca implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.habilitarSeleccionModelo(false);
			cargarModelos();
			view.habilitarSeleccionModelo(true);
		}
	}
		
	private class ListenerSeleccionModelo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.estaHabilitadaSeleccionModelo()) {
				view.setModeloActual(gestorVehiculo.getUltimoRiesgoModelo(view.getItemModelo().getIdModelo()).toString());	
			}			
		}
	}
	
	private class ListenerSeleccionProvincia implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.habilitarSeleccionCiudad(false);
			cargarCiudades();
			view.habilitarSeleccionCiudad(true);
		}
	}
	
	private class ListenerSeleccionCiudad implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.estaHabilitadaSeleccionCiudad()) {
				view.setCiudadActual(gestorDom.getUltimoRiesgoCiudad(view.getItemCiudad().getIdCiudad()).toString());	
			}			
		}
	}
	
	public class ListenerCampo implements KeyListener{
		public JTextField campoTemporal;
		private String temporal = "";
		public void keyTyped(KeyEvent e) {
			Character caracter = e.getKeyChar();
			if(Character.isDigit(caracter) ||  caracter == '.'){
				temporal = campoTemporal.getText();
				if(caracter == '.' && temporal.contains(".")) {
					e.consume();
				}else {
					GestorTema.get().setTema(campoTemporal, true);
				}				
			}
			else{
				e.consume();
			}
		}
		
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
				GestorTema.get().setTema(campoTemporal, true);
			}
		}
		public void keyReleased(KeyEvent e) {  }		
	}
}
