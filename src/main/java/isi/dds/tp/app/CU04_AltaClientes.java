package isi.dds.tp.app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

@SuppressWarnings("serial")
public class CU04_AltaClientes extends JPanel{


	public CU04_AltaClientes(JFrame principal) {
		principal.add(this);
		setBackground(Color.LIGHT_GRAY);
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
