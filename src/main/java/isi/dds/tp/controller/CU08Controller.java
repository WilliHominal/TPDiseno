package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

@SuppressWarnings("unused")
public class CU08Controller {
	private CU08View view;
	
	private GestorDomicilio gestorDom = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	private GestorTipoCobertura gestorCobertura = GestorTipoCobertura.get();
	private GestorParametrosPoliza gestorParametros = GestorParametrosPoliza.get();
	private GestorBitacora gestorBitacora = GestorBitacora.get();
	
	private ParametrosPoliza paramPNueva = new ParametrosPoliza();
	private BitacoraParametrosPoliza bitacora = gestorBitacora.newBitacoraParametros();
	
	private JFrame ventana;
	private JPanel panelAnteriorAPoliza;
	private String tituloAnteriorAPoliza = "";
	
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
		GestorTema.get().setTema(ventana, "ACTUALIZAR FACTORES");
		view = new CU08View();
		cargarElementos();
		view.addListenerBtnCancelar(new ListenerBtnCancelar());
		view.addListenerCampoTipoCobertura(new ListenerCampoNumerico());
		view.addListenerCampoModelo(new ListenerCampoNumerico());
		view.addListenerCampoCiudad(new ListenerCampoNumerico());
		view.addListenerCampoGuardaGarage(new ListenerCampoNumerico());
		view.addListenerCampoTieneAlarma(new ListenerCampoNumerico());
		view.addListenerCampoTieneRastreo(new ListenerCampoNumerico());
		view.addListenerCampoTieneTuercas(new ListenerCampoNumerico());
		view.addListenerCampoKm(new ListenerCampoNumerico());
		view.addListenerCampoCeroSiniestros(new ListenerCampoNumerico());
		view.addListenerCampoUnSiniestro(new ListenerCampoNumerico());
		view.addListenerCampoDosSiniestros(new ListenerCampoNumerico());
		view.addListenerCampoMuchosSiniestros(new ListenerCampoNumerico());
		view.addListenerCampoCantidadHijos(new ListenerCampoNumerico());
		view.addListenerCampoDerechoEmision(new ListenerCampoNumerico());
		view.addListenerCampoDescuentoUnidadAdicional(new ListenerCampoNumerico());
		view.addListenerSeleccionMarca(new ListenerSeleccionMarca());
		view.addListenerSeleccionModelo(new ListenerSeleccionModelo());
		view.addListenerSeleccionProvincia(new ListenerSeleccionProvincia());
		view.addListenerSeleccionCiudad(new ListenerSeleccionCiudad());
		view.addListenerSeleccionTipoCobertura(new ListenerSeleccionTipoCobertura()); 
	//	view.addListenerBtnActualizarFactores(new ListenerBtnActualizar());
		JScrollPane scroll = new JScrollPane(view);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		ventana.setContentPane(scroll);
		ventana.revalidate();		
	}
	
	public void cargarElementos() {

		gestorParametros.nuevosParametros(paramPNueva);
		
		view.setGuardaGarageActual(paramPNueva.getPorcentajeGuardaEnGarage().toString());
		view.setTieneAlarmaActual(paramPNueva.getPorcentajeAlarma().toString());
		view.setTieneRastreoActual(paramPNueva.getPorcentajeRastreoVehicular().toString());
		view.setTieneTuercasActual(paramPNueva.getPorcentajeTuercasAntirobo().toString());
		view.setKmActual(paramPNueva.getPorcentajeAjusteKm().toString());
		view.setCeroSiniestrosActual(paramPNueva.getPorcentajeNingunSiniestro().toString());
		view.setUnSiniestroActual(paramPNueva.getPorcentajeUnSiniestro().toString());
		view.setDosSiniestrosActual(paramPNueva.getPorcentajeDosSiniestros().toString());
		view.setMuchosSiniestrosActual(paramPNueva.getPorcentajeMayorADosSiniestros().toString());
		view.setCantidadHijosActual(paramPNueva.getPorcentajePorHijoRegistrado().toString());
		view.setDerechoEmisionActual(paramPNueva.getValorDerechoEmision().toString());
		view.setDescuentoUnidadAdicionalActual(paramPNueva.getDescuentoUnidadAdicional().toString());
		

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
		view.setTipoCoberturaActual(gestorCobertura.getUltimoRiesgoTipoCobertura(view.getItemTipoCobertura().getTipoCobertura()).toString());				
	}
	
	private void cargarCiudades() {
		List<Ciudad> ciudades = view.getItemProvincia().getCiudades();
		Iterator<Ciudad> iteratorCiudades = ciudades.iterator();
		while(iteratorCiudades.hasNext()) {
			view.addCiudad(iteratorCiudades.next());
		}
		
		view.setCiudadActual(gestorDom.getUltimoRiesgoCiudad(view.getItemCiudad().getIdCiudad()).toString());
	}
	
	private void cargarModelos() {
		List<Modelo> modelos = view.getItemMarca().getModelos();
		Iterator<Modelo> iteratorModelos = modelos.iterator();
		while(iteratorModelos.hasNext()) {
			view.addModelo(iteratorModelos.next());
		}
		
		view.setModeloActual(gestorVehiculo.getUltimoRiesgoModelo(view.getItemModelo().getIdModelo()).toString());
	}
	
	//------------- LISTENERS
	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				ventana.setContentPane(panelAnteriorAPoliza);
				ventana.setTitle(tituloAnteriorAPoliza);
				view.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ListenerBtnActualizar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				//TODO CU08 Validar campos 
				String impresionAviso = "Se modificarán los siguientes parámetros: \n";
				Integer contador = 1;	
				Boolean modificoParam = false;
				BitacoraParametrosPoliza bitacora = new BitacoraParametrosPoliza();
				
				
				//TODO SI necesito persistir hacer lo de abajo en el gestor de bitacora
				if(view.getCheckTipoCobertura()) {
					impresionAviso += contador+") Valor de riesgo porcentual del tipo de cobertura.\n";
					contador++;
					TipoCobertura t = view.getItemTipoCobertura();
					RiesgoTipoCobertura r = gestorCobertura.newRiesgoCobertura(t.getTipoCobertura(), Float.parseFloat(view.getTipoCobertura()));
					t.getRiesgo().add(r);
					gestorBitacora.addRiesgoTipoCobertura(bitacora, r);
					//TODO ver si update TipoCobertura
				}
				
				if(view.getCheckModelo()) {
					impresionAviso += contador+") Valor de riesgo porcentual de modelo.\n";
					contador++;
					Modelo m = view.getItemModelo();
					RiesgoModelo r = gestorVehiculo.newRiesgoModelo(m.getIdModelo(), Float.parseFloat(view.getModelo()));
					m.getRiesgos().add(r);
					gestorBitacora.addRiesgoModelo(bitacora, r);
					//TODO ver si update modelo
				}
				
				if(view.getCheckCiudad()) {
					impresionAviso += contador+") Valor de riesgo porcentual de ciudad.\n";
					contador++;
					Ciudad c = view.getItemCiudad();
					RiesgoCiudad r =  gestorDom.newRiesgoCiudad(c.getIdCiudad(), Float.parseFloat(view.getCiudad()));
					c.getRiesgos().add(r);
					gestorBitacora.addRiesgoCiudad(bitacora, r);
					//TODO ver si update Ciudad
				}
				
				if(view.getCheckGuardaGarage()) {
					impresionAviso += contador+") Valor porcentual acerca de sí el vehículo es guardado en un garage.\n";
					contador++;
					paramPNueva.setPorcentajeGuardaEnGarage(Float.parseFloat(view.getGuardaGarage()));
					modificoParam = true;
				}
				
				if(view.getCheckTieneAlarma()) {
					impresionAviso += contador+") Valor porcentual acerca de sí el vehículo tiene alarma.\n";
					contador++;
					paramPNueva.setPorcentajeAlarma(Float.parseFloat(view.getTieneAlarma()));
					modificoParam = true;
				}
				
				if(view.getCheckTieneRastreo()) {
					impresionAviso += contador+") Valor porcentual acerca de sí el vehículo tiene rastreo vehicular.\n";
					contador++;
					paramPNueva.setPorcentajeRastreoVehicular(Float.parseFloat(view.getTieneRastreo()));
					modificoParam = true;
				}
				
				if(view.getCheckTieneTuercas()) {
					impresionAviso += contador+") Valor porcentual acerca de sí el vehículo tiene tuercas antirrobo.\n";
					contador++;
					paramPNueva.setPorcentajeTuercasAntirobo(Float.parseFloat(view.getTieneTuercas()));
					modificoParam = true;
				}
				
				if(view.getCheckKm()) {
					impresionAviso += contador+") Valor porcentual por cada 10.00 km que el vehículo posee.\n";
					contador++;
					paramPNueva.setPorcentajeAjusteKm(Float.parseFloat(view.getKm()));
					modificoParam = true;
				}
				
				if(view.getCheckCeroSiniestros()) {
					impresionAviso += contador+") Valor porcentual que corresponde a si el titular del vehículo no posee siniestros en el último año.\n";
					contador++;
					paramPNueva.setPorcentajeNingunSiniestro(Float.parseFloat(view.getCeroSiniestros()));
					modificoParam = true;
				}				
				
				if(view.getCheckUnSiniestro()) {
					impresionAviso += contador+") Valor porcentual que corresponde a si el titular del vehículo posee un siniestro en el último año.\n";
					contador++;
					paramPNueva.setPorcentajeUnSiniestro(Float.parseFloat(view.getUnSiniestro()));
					modificoParam = true;
				}
				
				if(view.getCheckDosSiniestros()) {
					impresionAviso += contador+") Valor porcentual que corresponde a si el titular del vehículo posee dos siniestros en el último año.\n";
					contador++;
					paramPNueva.setPorcentajeDosSiniestros(Float.parseFloat(view.getDosSiniestros()));
					modificoParam = true;
				}
				
				if(view.getCheckMuchosSiniestros()) {
					impresionAviso += contador+") Valor porcentual que corresponde a si el titular del vehículo posee más de dos siniestros en el último año.\n";
					contador++;
					paramPNueva.setPorcentajeMayorADosSiniestros(Float.parseFloat(view.getMuchosSiniestros()));
					modificoParam = true;
				}
				
				if(view.getCheckCantidadHijos()) {
					impresionAviso += contador+") Valor porcentual que corresponde a la recargar hecha por la cantidad de hijos registrados en la póliza.\n";
					contador++;
					paramPNueva.setPorcentajePorHijoRegistrado(Float.parseFloat(view.getCantidadHijos()));
					modificoParam = true;
				}
				
				if(view.getCheckDerechoEmision()) {
					impresionAviso += contador+") Valor que indica el costo de emitir la póliza.\n";
					contador++;
					paramPNueva.setValorDerechoEmision(Float.parseFloat(view.getDerechoEmision()));
					modificoParam = true;
				}
				
				if(view.getCheckDescuentoUnidadAdicional()) {
					impresionAviso += contador+") Valor indicativo del descuento por poseer más de una póliza registrada.\n";
					paramPNueva.setDescuentoUnidadAdicional(Float.parseFloat(view.getDescuentoUnidadAdicional()));
					modificoParam = true;
				}

				if(modificoParam) {
					gestorBitacora.addParametrosPoliza(bitacora, paramPNueva);
				}
				
				//TODO CU08 persistir la bitacora y los parametros y los riesgos
				//TODO update a las clases principales
				
				JOptionPane.showMessageDialog(ventana, impresionAviso, "Confirmación", JOptionPane.INFORMATION_MESSAGE);

				//HibernateUtil.cerrarSessionesUsadas();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerCampoNumerico implements KeyListener {
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if(Character.isDigit(caracter) || caracter == '.' || caracter == ','){	}
			else{	e.consume(); }
		} 
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }		
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
}
