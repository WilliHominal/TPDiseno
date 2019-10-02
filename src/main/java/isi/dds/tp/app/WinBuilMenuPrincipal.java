package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class WinBuilMenuPrincipal extends JPanel {

	public WinBuilMenuPrincipal(JFrame principal) {
		principal.add(this);
		setBackground(Color.LIGHT_GRAY);
		setBounds(0,0,1024,600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		

	}

}
