package isi.dds.tp.app;

import java.awt.EventQueue;

import javax.swing.JFrame;

import isi.dds.tp.controller.CU17Controller1;
import isi.dds.tp.hibernate.HibernateUtil;

public class AppCU17BuscarCliente {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				HibernateUtil.apagarLog(true);
				JFrame frame = new JFrame();
				new CU17Controller1(frame);
				frame.setVisible(true);					
			}
		});
	}
}
