package isi.dds.tp.app;

import java.awt.Color;
import java.util.List;
import javax.swing.*;
import isi.dds.tp.modelo.HijoDeclarado;

@SuppressWarnings("serial")
public class DeclararHijo extends JFrame  {

	private	Object[] tema;
	private	AltaPoliza1 alta;
	private List hijo;
	
	public DeclararHijo(Object[] tema, AltaPoliza1 alta, List hijo) {
		this.alta = alta;
		this.tema = tema;
		this.hijo = hijo;
		app();
	}
	
	
	public void app() {
		HijoDeclarado hijo = null;
		
		
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 512, 200);
		setLocationRelativeTo(null);
		setTitle("Dar de alta póliza: AGREGAR DATOS HIJOS");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		//si es true todo
		//poliza.getHijosDeclarado().add(hijo);
		
	}

}
