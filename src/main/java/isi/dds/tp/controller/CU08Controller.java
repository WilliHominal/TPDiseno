package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
import isi.dds.tp.view.CU08View;

@SuppressWarnings("unused")
public class CU08Controller {
	private CU08View view;
	
	private GestorDomicilio gestorDom = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	private GestorTipoCobertura gestorCobertura = GestorTipoCobertura.get();
	private GestorParametrosPoliza gestosParametros = GestorParametrosPoliza.get();
	private GestorBitacora gestorBitacora = GestorBitacora.get();
	
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
		view.addListenerSeleccionMarca(null);
		view.addListenerSeleccionModelo(null);
		view.addListenerSeleccionProvincia(null);
		view.addListenerSeleccionCiudad(null);
		view.addListenerSeleccionTipoCobertura(null);
		view.addListenerBtnActualizarFactores(null);
		JScrollPane scroll = new JScrollPane(view);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		ventana.setContentPane(scroll);
		ventana.revalidate();		
	}
	
	//------------- LISTENERS
	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				ventana.setContentPane(panelAnteriorAPoliza);
				ventana.setTitle(tituloAnteriorAPoliza);
				view.setVisible(false);
				//HibernateUtil.shutdown();
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
}
