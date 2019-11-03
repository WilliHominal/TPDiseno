package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				//HibernateUtil.cerrarSessionesUsadas();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
