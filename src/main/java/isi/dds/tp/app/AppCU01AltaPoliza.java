package isi.dds.tp.app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import isi.dds.tp.controller.CU01Controller1;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.hibernate.HibernateUtil;

public class AppCU01AltaPoliza {
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					    	frame.setVisible(false);
							HibernateUtil.shutdown();
							System.exit(0);
					    }
					});
					CU01Controller1 a = new CU01Controller1(frame);
					a.obtenidoCliente(GestorCliente.get().getCliente(5400000000l));
					a.getView().setPatente("AAA123");
					a.getView().setMotor("AAA12345678901231");
					a.getView().setChasis("A9012312");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
