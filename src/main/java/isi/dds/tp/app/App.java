package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;

import isi.dds.tp.pruebas.PanelPrueba;

public class App {

	private JFrame frame;
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
		inicializar();
	}

	private void inicializar() {
		frame = new JFrame();
		//frame.setTitle("AAAA");
		//frame.getContentPane().setFont(new Font("Serif", Font.PLAIN, 11));
	//	frame.setBounds(0,0,1024,600);

		setAltaPoliza2();
		frame.setContentPane(altaPoliza2);
		
		//AltaPolizaBis pruebas = new AltaPolizaBis(frame); //quitar esto
		//frame.setContentPane(pruebas);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}

	public AltaPoliza1 getAltaPoliza1() {
		return altaPoliza1;
	}

	public void setAltaPoliza1() {
		this.altaPoliza1 = new AltaPoliza1(frame);
	}

	public AltaPoliza2 getAltaPoliza2() {
		return altaPoliza2;
	}

	public void setAltaPoliza2( ) {
		this.altaPoliza2 = new AltaPoliza2(frame);
	}

	public BuscarCliente getBuscarCliente() {
		return buscarCliente;
	}

	public void setBuscarCliente( ) {
		this.buscarCliente = new BuscarCliente(frame);
	}

	public DeclararHijo getDeclararHijo() {
		return declararHijo;
	}

	public void setDeclararHijo() {
		this.declararHijo = new DeclararHijo(frame);
	}

	public MenuPrincipal getMenu() {
		return menu;
	}

	public void setMenu() {
		this.menu = new MenuPrincipal(frame);
	}
	
}
