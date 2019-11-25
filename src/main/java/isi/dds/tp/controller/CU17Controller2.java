package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.view.CU17View2;

public class CU17Controller2 {
	private CU17View2 view2;
	private CU17Controller1 controller1;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	private Cliente cliente;
		
	private GestorTema tema = GestorTema.get();

	public CU17Controller2(JFrame ventana, Cliente cliente) {
		this.cliente = cliente;
		this.ventana = ventana;
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
			tituloAnterior = ventana.getTitle();
		}catch(Exception ex) {
		    panelAnterior = null;
		}
		setView2();
	}
	
	private void setView2() {
		this.view2 = new CU17View2();
		cargarClienteSeleccionado(cliente);
		view2.addListenerBtn_Volver(new ListenerVolver());
		view2.addListenerBtn_ElegirCliente(new ListenerElegirCliente());
		tema.setTema(ventana, "Buscar cliente: MOSTRAR CLIENTE SELECCIONADO");
		ventana.setContentPane(view2);
		ventana.revalidate();
	}

	private void cargarClienteSeleccionado(Cliente cliente) {
		view2.setNumeroCliente(cliente.getNumeroCliente().toString());
		view2.setCondicion(cliente.getCondicion().toString());
		view2.setApellido(cliente.getApellido());
		view2.setNombre(cliente.getNombre());
		view2.setNumeroDocumento(cliente.getNumeroDocumento());
		view2.setNumeroCuil(cliente.getNumeroCuil().toString());
		view2.setCalle(cliente.getCalle());
		view2.setNumeroCalle(cliente.getNumeroCalle().toString());
		
		if(cliente.getPiso()==null) {
			view2.setPiso("-");
			view2.setDepartamento("-");
		}else {
			view2.setPiso(cliente.getPiso().toString());
			view2.setDepartamento(cliente.getDepartamento());
		}
		view2.setCodigoPostal(cliente.getCodigoPostal().toString());
		view2.setCorreoElectronico(cliente.getCorreoElectronico());
		view2.setProfesion(cliente.getProfesion());
		view2.setAnioRegistro(cliente.getAnioRegistro().toString());
		view2.setTipoDocumento(cliente.getTipoDocumento().toString());
		view2.setSexo(cliente.getSexo().toString());
		view2.setFechaNacimiento(cliente.getFechaNacimiento().toString().substring(0,2) + "/" +cliente.getFechaNacimiento().getMonthValue() + "/" +cliente.getFechaNacimiento().getYear());
		view2.setPais(cliente.getCiudad().getProvincia().getPais().getNombre());
		view2.setProvincia(cliente.getCiudad().getProvincia().getNombre());
		view2.setCiudad(cliente.getCiudad().getNombre());
		view2.setCondicionIva(cliente.getCondicionIva().toString());
		view2.setEstadoCivil(cliente.getEstadoCivil().toString(cliente.getSexo()));

	}

	public void setCU17Controller1(CU17Controller1 controller1) {
		this.controller1 = controller1;
	}
	
	//---------- LISTENERS USADOS
	private class ListenerElegirCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				
				controller1.obtenidoCliente(cliente);
		    	view2.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}  	
		}
	}
	
	private class ListenerVolver implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				view2.setVisible(false);
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
