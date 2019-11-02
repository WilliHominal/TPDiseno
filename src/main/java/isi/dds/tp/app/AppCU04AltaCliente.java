package isi.dds.tp.app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import isi.dds.tp.controller.CU04Controller;

public class AppCU04AltaCliente {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					new CU04Controller(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
