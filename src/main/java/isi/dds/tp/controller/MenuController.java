package isi.dds.tp.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.view.CU04View;
import isi.dds.tp.view.MenuView2;
import isi.dds.tp.view.MenuView1;

public class MenuController {
	
	private MenuView1 menuView1;
	private MenuView2 menuView2;
	private JFrame ventana;
	private GestorTema tema = GestorTema.get();
	
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
	
	public MenuController(JFrame ventana) {
		this.ventana = ventana;
		addListenerVentana();
		if(HibernateUtil.conectarBaseDatos()) {
			setMenu();
		}
		else {
			setErrorBaseDatos();
		}
		ventana.setVisible(true);	
	}

	private void setMenu() {
		menuView1 = new MenuView1();
		tema.setTema(ventana, "MENÚ");
		ventana.setContentPane(menuView1);
		menuView1.addListenerBtn_AltaPoliza(new ListenerAltaPoliza());
		//menu.addListenerBtn_ConsultarPoliza(new ListenerFuncionNoDisponible());
		//menu.addListenerBtn_GenerarPropuesta(new ListenerFuncionNoDisponible());
		//menu.addListenerBtn_RegistrarPagoPoliza(new ListenerFuncionNoDisponible());
		menuView1.addListenerBtn_AltaCliente(new ListenerAltaCliente());
		menuView1.addListenerBtn_ConsultarCliente(new ListenerConsultarCliente());
		//menu.addListenerBtn_ActualizarFactores(new ListenerFuncionNoDisponible());
		//menu.addListenerBtn_GenerarInforme(new ListenerFuncionNoDisponible());
		//menu.addListenerBtn_GenerarListado(new ListenerFuncionNoDisponible());
		menuView1.addListenerBtn_Salir(new ListenerSalir());
	}
	
	private void setErrorBaseDatos() {
		menuView2 = new MenuView2();
		tema.setTema(ventana, "CONFIGURAR BASE DE DATOS");
		JScrollPane scroll = new JScrollPane(menuView2);
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		ventana.setContentPane(scroll);
		menuView2.addSalirListener(new ListenerSalir());		
	}

	class ListenerFuncionNoDisponible implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class ListenerAltaPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(menuView1.cargarBaseDatos()) {
					HibernateUtil.recargarBaseDatos();
					menuView1.yaCargoBaseDatos();
				}
				new CU01Controller(ventana);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerAltaCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(menuView1.cargarBaseDatos()) {
					HibernateUtil.recargarBaseDatos();
					menuView1.yaCargoBaseDatos();
				}
				new CU04View(ventana);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerConsultarCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(menuView1.cargarBaseDatos()) {
					HibernateUtil.recargarBaseDatos();
					menuView1.yaCargoBaseDatos();
				}
				new CU17Controller(ventana);
				//a.setMenuController(instancia);
				ventana.revalidate();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerSalir implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				ventana.setVisible(false);	
				HibernateUtil.shutdown();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void addListenerVentana() {
		ventana.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	ventana.setVisible(false);
				HibernateUtil.shutdown();
				System.exit(0);
		    }
		});
	}

	public MenuView1 getMenu() {
		return menuView1;
	}
}