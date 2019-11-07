package isi.dds.tp.app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import isi.dds.tp.controller.MenuController;
import isi.dds.tp.hibernate.HibernateUtil;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HibernateUtil.apagarLog(true);
					new MenuController(new JFrame());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
