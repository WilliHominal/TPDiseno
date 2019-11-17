package isi.dds.tp.app;

import java.awt.EventQueue;

import javax.swing.JFrame;

import isi.dds.tp.controller.CU18Controller;
import isi.dds.tp.hibernate.HibernateUtil;

public class AppCU18BuscarPoliza {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			HibernateUtil.apagarLog(true);
				JFrame frame = new JFrame();
				new CU18Controller(frame);
				frame.setVisible(true);					
			}
		});
	}
}