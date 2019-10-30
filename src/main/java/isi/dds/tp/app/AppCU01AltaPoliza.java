package isi.dds.tp.app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import isi.dds.tp.controller.CU01Controller;
import isi.dds.tp.gestor.GestorCliente;

public class AppCU01AltaPoliza {
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					CU01Controller a = new CU01Controller(frame);
					a.obtenidoCliente(GestorCliente.get().getCliente(5448044249l));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
