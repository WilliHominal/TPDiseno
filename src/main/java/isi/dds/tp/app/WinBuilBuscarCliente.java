package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WinBuilBuscarCliente extends JPanel {

	public WinBuilBuscarCliente(JFrame principal) {
		principal.add(this);
		setBackground(Color.LIGHT_GRAY);
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
