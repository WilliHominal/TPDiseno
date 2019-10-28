package isi.dds.tp.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.view.CU01_AltaPoliza1;
import isi.dds.tp.view.CU04_AltaCliente;
import isi.dds.tp.view.ErrorBaseDatos;
import isi.dds.tp.view.Menu;

public class Menu_controller {
	
	private Menu menu;
	private ErrorBaseDatos errorBaseDatos;
	private JFrame ventana;
	private GestorTema tema = GestorTema.get();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Menu_controller(new JFrame());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Menu_controller(JFrame ventana) {
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
		menu = new Menu();
		tema.setTema(ventana, "MENÚ");
		ventana.setContentPane(menu);
		menu.addListenerBtn_AltaPoliza(new ListenerAltaPoliza());
		menu.addListenerBtn_ConsultarPoliza(new ListenerFuncionNoDisponible());
		menu.addListenerBtn_GenerarPropuesta(new ListenerFuncionNoDisponible());
		menu.addListenerBtn_RegistrarPagoPoliza(new ListenerFuncionNoDisponible());
		menu.addListenerBtn_AltaCliente(new ListenerAltaCliente());
		menu.addListenerBtn_ConsultarCliente(new ListenerConsultarCliente());
		menu.addListenerBtn_ActualizarFactores(new ListenerFuncionNoDisponible());
		menu.addListenerBtn_GenerarInforme(new ListenerFuncionNoDisponible());
		menu.addListenerBtn_GenerarListado(new ListenerFuncionNoDisponible());
		menu.addListenerBtn_Salir(new ListenerSalir());
	}
	
	private void setErrorBaseDatos() {
		errorBaseDatos = new ErrorBaseDatos();
		tema.setTema(ventana, "CONFIGURAR BASE DE DATOS");
		JScrollPane scroll = new JScrollPane(errorBaseDatos);
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		ventana.setContentPane(scroll);
		errorBaseDatos.addSalirListener(new ListenerSalir());		
	}

	class ListenerFuncionNoDisponible implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(ventana, "Función temporalmente no disponible.\n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class ListenerAltaPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(menu.cargarBaseDatos()) {
					HibernateUtil.recargarBaseDatos();
					menu.yaCargoBaseDatos();
				}
				new CU01_AltaPoliza1(ventana);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerAltaCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(menu.cargarBaseDatos()) {
					HibernateUtil.recargarBaseDatos();
					menu.yaCargoBaseDatos();
				}
				new CU04_AltaCliente(ventana);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerConsultarCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(menu.cargarBaseDatos()) {
					HibernateUtil.recargarBaseDatos();
					menu.yaCargoBaseDatos();
				}
				new CU17_controller(ventana);
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

	public Menu getMenu() {
		return menu;
	}
}