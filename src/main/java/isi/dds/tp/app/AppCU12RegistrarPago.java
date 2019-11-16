package isi.dds.tp.app;

import java.awt.EventQueue;

import javax.swing.JFrame;

import isi.dds.tp.controller.CU12Controller1;
import isi.dds.tp.controller.CU12Controller2;
import isi.dds.tp.hibernate.HibernateUtil;

public class AppCU12RegistrarPago {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				HibernateUtil.apagarLog(true);
				JFrame frame = new JFrame();
				new CU12Controller1(frame);
				frame.setVisible(true);					
			}
		});
	}
}
