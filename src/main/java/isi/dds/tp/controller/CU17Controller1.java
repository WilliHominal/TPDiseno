package isi.dds.tp.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.view.CU01View1;
import isi.dds.tp.view.CU17View1;

public class CU17Controller1 {    
	private CU17Controller1 instancia;
	private CU17View1 view1;
	
	private CU01Controller1 altaPolizaController = null;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
		
	private GestorTema tema = GestorTema.get();
	private GestorEnum gestorEnum = GestorEnum.get();

	private Boolean esAltaPoliza = false;
	private Cliente clienteObtenido = null;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public CU17Controller1(JFrame ventana) {
		this.instancia = this;
		this.ventana = ventana;
		try {		
			if(ventana.getContentPane() instanceof CU01View1) {
				esAltaPoliza = true;
			}
			panelAnterior = (JPanel) ventana.getContentPane();
			tituloAnterior = ventana.getTitle();
		}catch(Exception ex) {
		    panelAnterior = null;
		}
		setView1();
	}
	
	private void setView1() {
		tema.setTema(ventana, "Buscar cliente");
		this.view1 = new CU17View1();

		view1.addItemTipoCobertura("Selecionar tipo doc.");
		EnumTipoDocumento[] tipoDocumentos = EnumTipoDocumento.values();
		for(int i=0; i<tipoDocumentos.length; i++){
			view1.addItemTipoCobertura(gestorEnum.parseString(tipoDocumentos[i]));
		}
		
		view1.addListenerBtnCancelar(new ListenerCancelar());
		view1.addListenerBtnBuscar(new ListenerBuscar());
		view1.addListenerTablaClientes(new ListenerTablaClientes());
		tema.setTema(ventana, "BUSCAR CLIENTE");
		ventana.setContentPane(view1);
	}
	
	private void cargarTabla(List<Cliente> clientes) {
		if(clientes == null) {
			view1.addTablaClientes(0);
		}
		else {
			int cantClientes = clientes.size();
			if(cantClientes > 0){
				view1.addTablaClientes(cantClientes);
				Cliente c = null;
				for(int fila=0; fila<cantClientes; fila++) {
					c = clientes.get(fila);
					view1.setValoresTablaClientes(fila, c.getNumeroCliente(), c.getApellido(), c.getNombre(), gestorEnum.parseString(c.getTipoDocumento()), c.getNumeroDocumento());
				}
			}
			else {
				view1.addTablaClientes(0);
				JOptionPane.showMessageDialog(ventana, "No se han encontrado clientes que cumplan con ese criterio de búsqueda.", "Clente no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}

	public void setAltaPolizaController(CU01Controller1 altaPolizaController) {
		this.altaPolizaController = altaPolizaController;
	}
	
	public void obtenidoCliente(Cliente cliente) {
		if(esAltaPoliza) {
			altaPolizaController.obtenidoCliente(cliente);
			if(cliente.getPolizas() == null) {
				cliente.setPolizas(new ArrayList<Poliza>());
			}
		}
		try {
			ventana.setContentPane(panelAnterior);
			ventana.setTitle(tituloAnterior);
			view1.setVisible(false);
		}catch(Exception ex) {
		    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//---------- LISTENERS USADOS
	private class ListenerBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Long numeroCliente = null;
				String apellido = view1.getApellido();
				String nombre = view1.getNombre();
				EnumTipoDocumento tipoDocumento= null;
				String numeroDocumento = view1.getNumeroDocumento(); 	
				
				if(!(view1.getNumeroCliente().isEmpty())) {
					numeroCliente = Long.parseLong(view1.getNumeroCliente());
				}
				
				if(!view1.getTipoDocumento().equals("Selecionar tipo documento")) {
					tipoDocumento = GestorEnum.get().parseEnumTipoDocumento(view1.getTipoDocumento());
				}
				
				clientes = GestorCliente.get().buscarClientes(numeroCliente, apellido, nombre, tipoDocumento, numeroDocumento);
				
				cargarTabla(clientes);

					
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);  
            	ex.printStackTrace(); //TODO eliminar
			}
		}
	}
	
	private class ListenerCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
				view1.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerTablaClientes implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = view1.getRowTablaClientes(point);
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	    		clienteObtenido = clientes.get(row);
	    		new CU17Controller2(ventana, clienteObtenido).setCU17Controller1(instancia);;
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
}
