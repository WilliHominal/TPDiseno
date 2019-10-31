package isi.dds.tp.controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import isi.dds.tp.view.CU12View1;

@SuppressWarnings("unused")
public class CU12Controller1 {

	private CU12Controller1 instancia;
	private CU12View1 registrarPago1;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	public CU12Controller1(JFrame ventana) {
		instancia = this;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
		setRegistrarPago();
	}

	private void setRegistrarPago() {
		registrarPago1 = new CU12View1();
	}
}
