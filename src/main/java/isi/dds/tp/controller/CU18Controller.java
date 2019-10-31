package isi.dds.tp.controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import isi.dds.tp.view.CU18View1;

@SuppressWarnings("unused")
public class CU18Controller {
	
	
	private CU18Controller instancia;
	private CU18View1 buscarPoliza;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";

	public CU18Controller(JFrame ventana) {
		instancia = this;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
		setBuscarPoliza();
	}

	private void setBuscarPoliza() {
		buscarPoliza = new CU18View1();
	}
}
