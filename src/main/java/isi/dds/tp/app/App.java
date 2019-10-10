package isi.dds.tp.app;

import java.awt.*;
import java.util.logging.Level;

import javax.swing.*;

import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.*;


public class App {

	private JFrame frame;
	//el color del borde deberia ser igual al del fondo
	//Color colorBoton, Color colorFondoPantalla, Color colorFondoTexto, Color borde, Color colorLetraBloqueado, Font letra
	private Object[] tema = {new Color(0, 128, 128), new Color(192,192,192), /*new Color(192,192,192)*/new Color(222,184,135), Color.BLACK, Color.GRAY, new Font("Open Sans", Font.PLAIN, 13)};
	private AltaClientes altaClientes;
	private AltaPoliza1 altaPoliza1;
	private AltaPoliza2 altaPoliza2;
	private BuscarCliente buscarCliente;
	private DeclararHijo declararHijo;
	private MenuPrincipal menu;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		
		Boolean conectoBase = conectarBaseDatos();//TODO agregar condicion si da falso
		
		//CargarBase.get();
		
		if(!conectoBase) {
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

	public DeclararHijo getDeclararHijo() {
		return declararHijo;
	}

	public void setDeclararHijo() {
		this.declararHijo = new DeclararHijo(frame);
		frame.setContentPane(declararHijo);
	}

	public MenuPrincipal getMenu() {
		return menu;
	}

	public void setMenu() {
		this.menu = new MenuPrincipal(frame);
		frame.setContentPane(menu);
	}

	public Boolean conectarBaseDatos() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		if(HibernateUtil.getSessionFactory() != null) {
			return false;
		}
		else {
			return true;
		}
		
	}
			
}
