package isi.dds.tp.app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import isi.dds.tp.controller.MenuController;

public class AppPrincipal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MenuController(new JFrame());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
