package isi.dds.tp.app;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.*;


public class App {

	private JFrame frame;
	private AltaClientes altaClientes;
	private AltaPoliza1 altaPoliza1;
	private AltaPoliza2 altaPoliza2;
	private BuscarCliente buscarCliente;
	private MenuPrincipal menu;	
	
							//  colorBoton     			colorFondoPantalla     colorFondoTexto         borde		colorLetraBloqueado  colorLetra			letra
	private Object[] tema = {new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK, new Color(71,71,71), Color.BLACK, new Font("Open Sans", Font.PLAIN, 13)};
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new App();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public App() {		
		Boolean conectoBase = conectarBaseDatos();//TODO agregar condicion si da falso
		if(!conectoBase) {
			//HibernateUtil.cargarBase();
			inicializar();
		}	
	}

	private void inicializar() {
		
		frame = new JFrame();
		setAltaPoliza1();
		frame.setVisible(true);
		
	
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public AltaClientes getAltaClientes() {
		return altaClientes;
	}

	public void setAltaClientes() {
		this.altaClientes = new AltaClientes(frame);
		frame.setContentPane(altaClientes);
	}

	public AltaPoliza1 getAltaPoliza1() {
		return altaPoliza1;
	}

	public void setAltaPoliza1() {
		this.altaPoliza1 = new AltaPoliza1(frame, tema);
		frame.setContentPane(altaPoliza1);
	}

	public AltaPoliza2 getAltaPoliza2() {
		return altaPoliza2;
	}

	public void setAltaPoliza2( ) {
		this.altaPoliza2 = new AltaPoliza2(frame, tema, new Poliza());
		frame.setContentPane(altaPoliza2);
	}

	public BuscarCliente getBuscarCliente() {
		return buscarCliente;
	}

	public void setBuscarCliente( ) {
		this.buscarCliente = new BuscarCliente(frame);
		frame.setContentPane(buscarCliente);
	}

	public MenuPrincipal getMenu() {
		return menu;
	}

	public void setMenu() {
		this.menu = new MenuPrincipal(frame);
		frame.setContentPane(menu);
	}

	public Boolean conectarBaseDatos() {
		//desactiva que se abra la consola cada vez que se inicia
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF); 
	    
		if(HibernateUtil.getSessionFactory() != null) {
			return false;
		}
		else {
			return true;
		}
	}
			
}
