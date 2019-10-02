package isi.dds.tp.app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

@SuppressWarnings("serial")
public class WinBuilAltaClientes extends JPanel{


	public WinBuilAltaClientes(JFrame principal) {
		principal.add(this);
		setBackground(Color.LIGHT_GRAY);
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
