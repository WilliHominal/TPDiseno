package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;

public class WinBuilApp {

	private JFrame frame;
	private WinBuilAltaClientes altaClientes;
	private WinBuilAltaPoliza1 altaPoliza1;
	private WinBuilAltaPoliza2 altaPoliza2;
	private WinBuilBuscarCliente buscarCliente;
	private WinBuilDeclararHijo declararHijo;
	private WinBuilMenuPrincipal menu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBuilApp window = new WinBuilApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WinBuilApp() {
		inicializar();
	}

	private void inicializar() {
		frame = new JFrame();
		frame.setTitle("AAAA");
		frame.getContentPane().setFont(new Font("Serif", Font.PLAIN, 11));
		frame.setBounds(0,0,1024,600);
		
		setAltaPoliza1();
		frame.setContentPane(altaPoliza1);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);				
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public WinBuilAltaClientes getAltaClientes() {
		return altaClientes;
	}

	public void setAltaClientes() {
		this.altaClientes = new WinBuilAltaClientes(frame);
	}

	public WinBuilAltaPoliza1 getAltaPoliza1() {
		return altaPoliza1;
	}

	public void setAltaPoliza1() {
		this.altaPoliza1 = new WinBuilAltaPoliza1(frame);
	}

	public WinBuilAltaPoliza2 getAltaPoliza2() {
		return altaPoliza2;
	}

	public void setAltaPoliza2( ) {
		this.altaPoliza2 = new WinBuilAltaPoliza2(frame);
	}

	public WinBuilBuscarCliente getBuscarCliente() {
		return buscarCliente;
	}

	public void setBuscarCliente( ) {
		this.buscarCliente = new WinBuilBuscarCliente(frame);
	}

	public WinBuilDeclararHijo getDeclararHijo() {
		return declararHijo;
	}

	public void setDeclararHijo() {
		this.declararHijo = new WinBuilDeclararHijo(frame);
	}

	public WinBuilMenuPrincipal getMenu() {
		return menu;
	}

	public void setMenu() {
		this.menu = new WinBuilMenuPrincipal(frame);
	}
	
}
