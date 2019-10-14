package isi.dds.tp.app;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import isi.dds.tp.hibernate.HibernateUtil;


public class App {

	private JFrame frame;
	private CU01_AP1 altaPoliza1;
	private CU01_AP2 altaPoliza2;
	private MenuPrincipal menu;	
	
							//  colorBoton     			colorFondoPantalla     colorFondoTexto         borde		colorLetraBloqueado  
	private Object[] tema = {new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK, new Color(71,71,71), 
			//colorLetra			letra							colorErroneo
			Color.BLACK, new Font("Open Sans", Font.PLAIN, 13), new Color(255,102,102)};
	

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
			HibernateUtil.cargarBase();
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

	public CU01_AP1 getAltaPoliza1() {
		return altaPoliza1;
	}

	public void setAltaPoliza1() {
		this.altaPoliza1 = new CU01_AP1(frame, tema);
		frame.setContentPane(altaPoliza1);
	}

	public CU01_AP2 getAltaPoliza2() {
		return altaPoliza2;
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
