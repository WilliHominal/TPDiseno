package isi.dds.tp.controller;

import java.awt.EventQueue;
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
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.view.CU01_AltaPoliza1;
import isi.dds.tp.view.CU17_BuscarCliente;
import isi.dds.tp.view.CU17_MostrarCliente;

public class CU17_controller {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					GestorTema.get().setTema(frame, "Buscar cliente");
					new CU17_controller(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	private JFrame ventana;
	private JPanel panelAnterior;
	private CU17_BuscarCliente buscarCliente;
	private CU17_MostrarCliente mostrarCliente;
	
	private CU01_controller altaPolizaController = null;
	
	private GestorTema tema = GestorTema.get();
	private GestorEnum gestorEnum = GestorEnum.get();
	
	private String tituloAnterior = "";
	private Boolean esAltaPoliza = false;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Cliente clienteObtenido = null;
	
	public CU17_controller(JFrame ventana) {
		this.ventana = ventana;
		try {		
			if(ventana.getContentPane() instanceof CU01_AltaPoliza1) {
				esAltaPoliza = true;
			}
			panelAnterior = (JPanel) ventana.getContentPane();
			tituloAnterior = ventana.getTitle();
		}catch(Exception ex) {
		    panelAnterior = null;
		}
		setBuscarCliente();
	}
	
	private void setBuscarCliente() {
		this.buscarCliente = new CU17_BuscarCliente();
		cargarSeleccionTipoDocumento();
		buscarCliente.addListenerBtnCancelar(new ListenerCancelar());
		buscarCliente.addListenerBtnBuscar(new ListenerBuscar());
		buscarCliente.addListenerTablaClientes(new ListenerTablaClientes());
		tema.setTema(ventana, "BUSCAR CLIENTE");
		ventana.setContentPane(buscarCliente);
	}
	
	private void setMostrarCliente(Cliente cliente) {
		this.mostrarCliente = new CU17_MostrarCliente();
		cargarClienteSeleccionado(cliente);
		mostrarCliente.addListenerBtn_Volver(new ListenerVolver());
		mostrarCliente.addListenerBtn_ElegirCliente(new ListenerElegirCliente());
		tema.setTema(ventana, "BUSCAR CLIENTE: MOSTRAR CLIENTE SELECCIONADO");
		ventana.setContentPane(mostrarCliente);
		ventana.revalidate();
	}

	class ListenerBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Long numeroCliente = null;
				String apellido = buscarCliente.getApellido();
				String nombre = buscarCliente.getNombre();
				EnumTipoDocumento tipoDocumento= null;
				String numeroDocumento = buscarCliente.getNumeroDocumento(); 	
				
				if(!(buscarCliente.getNumeroCliente().isEmpty())) {
					numeroCliente = Long.parseLong(buscarCliente.getNumeroCliente());
				}
				
				if(!buscarCliente.getTipoDocumento().equals("Selecionar tipo documento")) {
					tipoDocumento = GestorEnum.get().parseEnumTipoDocumento(buscarCliente.getTipoDocumento());
				}
				
				clientes = GestorCliente.get().buscarClientes(numeroCliente, apellido, nombre, tipoDocumento, numeroDocumento);
				
				cargarTabla(clientes);

					
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		}
	}
	
	class ListenerCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
				buscarCliente.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerTablaClientes implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = buscarCliente.getRowTablaClientes(point);
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	    		clienteObtenido = clientes.get(row);
	    		setMostrarCliente(clienteObtenido);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	class ListenerElegirCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				obtenidoCliente(); 
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}  	
		}
	}
	
	class ListenerVolver implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				mostrarCliente.setVisible(false);
				ventana.setContentPane(buscarCliente);	
				ventana.setTitle("Buscar cliente");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void cargarSeleccionTipoDocumento() {
		buscarCliente.addItemTipoCobertura("Selecionar tipo documento");
		EnumTipoDocumento[] tipoDocumentos = EnumTipoDocumento.values();
		for(int i=0; i<tipoDocumentos.length; i++){
			buscarCliente.addItemTipoCobertura(gestorEnum.parseString(tipoDocumentos[i]));
		}
	}
	
	private void cargarTabla(List<Cliente> clientes) {
		if(clientes == null) {
			buscarCliente.addTablaClientes(0);
		}
		else {
			int cantClientes = clientes.size();
			if(cantClientes > 0){
				buscarCliente.addTablaClientes(cantClientes);
				Cliente c = null;
				for(int fila=0; fila<cantClientes; fila++) {
					c = clientes.get(fila);
					buscarCliente.setValoresTablaClientes(fila, c.getNumeroCliente(), c.getApellido(), c.getNombre(), gestorEnum.parseString(c.getTipoDocumento()), c.getNumeroDocumento());
				}
			}
			else {
				buscarCliente.addTablaClientes(0);
				JOptionPane.showMessageDialog(ventana, "No se han encontrado clientes que cumplan con ese criterio de búsqueda.", "Clente no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
		
	}

	private void cargarClienteSeleccionado(Cliente cliente) {
		
		mostrarCliente.setNumeroCliente(cliente.getNumeroCliente().toString());
		mostrarCliente.setCondicion(cliente.getCondicion().toString());
		mostrarCliente.setApellido(cliente.getApellido());
		mostrarCliente.setNombre(cliente.getNombre());
		mostrarCliente.setNumeroDocumento(cliente.getNumeroDocumento());
		mostrarCliente.setNumeroCuil(cliente.getNumeroCuil().toString());
		mostrarCliente.setCalle(cliente.getCalle());
		mostrarCliente.setNumeroCalle(cliente.getNumeroCalle().toString());
		
		if(cliente.getPiso()==null) {
			mostrarCliente.setPiso("-");
			mostrarCliente.setDepartamento("-");
		}else {
			mostrarCliente.setPiso(cliente.getPiso().toString());
			mostrarCliente.setDepartamento(cliente.getDepartamento());
		}
		mostrarCliente.setCodigoPostal(cliente.getCodigoPostal().toString());
		mostrarCliente.setCorreoElectronico(cliente.getCorreoElectronico());
		mostrarCliente.setProfesion(cliente.getProfesion());
		mostrarCliente.setAnioRegistro(cliente.getAnioRegistro().toString());
		mostrarCliente.setTipoDocumento(gestorEnum.parseString(cliente.getTipoDocumento()));
		mostrarCliente.setSexo(gestorEnum.parseString(cliente.getSexo()));
		mostrarCliente.setFechaNacimiento(cliente.getFechaNacimiento().toString());
		mostrarCliente.setCiudad(cliente.getCiudad().getProvincia().getPais().getNombre());
		mostrarCliente.setProvincia(cliente.getCiudad().getProvincia().getNombre());
		mostrarCliente.setPais(cliente.getCiudad().getNombre());
		mostrarCliente.setCondicionIva(gestorEnum.parseString(cliente.getCondicionIva()));
		if(cliente.getSexo().equals(EnumSexo.FEMENINO)) {
			mostrarCliente.setEstadoCivil(gestorEnum.parseStringFem(cliente.getEstadoCivil()));
		}
		else {
			mostrarCliente.setEstadoCivil(gestorEnum.parseStringMasc(cliente.getEstadoCivil()));
		}
	}
	
	public void obtenidoCliente() {
		if(esAltaPoliza) {
	    	ventana.setContentPane(panelAnterior);
	    	ventana.setTitle(tituloAnterior);
	    	
			altaPolizaController.obtenidoCliente(clienteObtenido);
	    	mostrarCliente.setVisible(false);
	    	
		}
		else {
	    	ventana.setTitle(tituloAnterior);
	    	mostrarCliente.setVisible(false);
	    	ventana.setContentPane(panelAnterior);
		}
	}

	public void setAltaPolizaController(CU01_controller altaPolizaController) {
		this.altaPolizaController = altaPolizaController;
	}	
}
