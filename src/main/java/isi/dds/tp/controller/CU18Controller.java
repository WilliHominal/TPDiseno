package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Pago;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.view.CU12View1;
import isi.dds.tp.view.CU18View;

public class CU18Controller {
	private CU18Controller instancia;
	private CU18View view18;
	private CU12Controller1 cu12Controller;
	
	private Boolean esRealizarPagoPoliza = false;
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	private GestorTema tema = GestorTema.get();
	
	private Poliza polizaObtenida = null;

	public CU18Controller(JFrame ventana) {
		instancia = this;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			if(ventana.getContentPane() instanceof CU12View1) {
				esRealizarPagoPoliza = true;
			}
			panelAnterior = (JPanel) ventana.getContentPane();
			tituloAnterior = ventana.getTitle();
		}catch(Exception ex) {
			panelAnterior = null;
		}
		setView12();
	}

	private void setView12() {
		tema.setTema(ventana, "Buscar póliza");
		view18= new CU18View();
		view18.addListenerBtnCancelar(new ListenerBtnCancelar());
		view18.addListenerBtnBuscar(new ListenerBuscar());
		view18.addListenerBtnAceptar(new ListenerBtnAceptar());
		ventana.setContentPane(view18);
	}

	public void obtenidaPoliza(Poliza poliza) {
		if(esRealizarPagoPoliza) {
			cu12Controller.obtenidaPoliza(poliza);
		}
		try {
			ventana.setContentPane(panelAnterior);
			ventana.setTitle(tituloAnterior);
			view18.setVisible(false);
		}catch(Exception ex) {
		    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarPolizaSeleccionado(Poliza poliza) {
		view18.componentesAlObtenerPoliza();
		view18.setNumeroCliente(poliza.getCliente().getNumeroCliente().toString());
		view18.setNumeroPoliza(poliza.getNumeroPoliza().toString());
		view18.setApellido(poliza.getCliente().getApellido());
		view18.setNombre(poliza.getCliente().getNombre());
		view18.setTipoDocumento(poliza.getCliente().getTipoDocumento().toString());
		view18.setNumeroDocumento(poliza.getCliente().getNumeroDocumento());
		
		Pago ppago = GestorPoliza.get().ultimoPago(poliza);
		
		if(ppago != null) {
			view18.setfechaPago(ppago.getFechaPago().toString().substring(0,2) + "/" +ppago.getFechaPago().getMonthValue() + "/"+(ppago.getFechaPago().getYear()) );
			view18.setMonto(ppago.getImporte().toString());
		}		
		else {
			view18.setfechaPago("-");
			view18.setMonto("-");
		}		
	}

	private Boolean condicionesBuscarPoliza() {
		String textoPoliza = view18.getNumeroPoliza();
	
		String textoError = "";
	
		//los valores boolean son para luego la interfaz establezca los colores de los campos mal validados
		Boolean error = false;
		
		//---------- posible error en la introdución del número del número de poliza
		if(textoPoliza.isEmpty() ) {
			error = true;
			textoError = "No se ha introducido un número de póliza\n";
		}
		else {
			if(textoPoliza.length() != 13) {
				error = true;
				textoError = "La definición de un número de póliza debe ser de longitud 13.\n";
			}
		}
		if(error) {
			view18.noValido(error);
			JOptionPane.showConfirmDialog(ventana, textoError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void volver() {
		ventana.setContentPane(panelAnterior);
		ventana.setTitle(tituloAnterior);
		view18.setVisible(false);
	}
	
	public void setPagoPolizaController(CU12Controller1 cu12Controller) {
		this.cu12Controller = cu12Controller;
	}
	
	//para caso de prueba
	public CU18View getView(){
		return view18;
	}
	
	//---------- LISTENERS USADOS
	private class ListenerBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(!condicionesBuscarPoliza()) {
					return;
				}
				Long numeroPoliza = null;

				if(!(view18.getNumeroPoliza().isEmpty())) {
					numeroPoliza = Long.parseLong(view18.getNumeroPoliza());
				}				
				
				polizaObtenida = GestorPoliza.get().buscarPoliza(numeroPoliza);
				if (polizaObtenida != null) {
					cargarPolizaSeleccionado(polizaObtenida);
				}
				else {
					JOptionPane.showMessageDialog(null, "No existe póliza con ese número.\n",
				                          "Error.", JOptionPane.ERROR_MESSAGE); 
					}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(ventana, "No se pudo obtener la póliza desde la base de datos",
                        "Error.", JOptionPane.ERROR_MESSAGE);  
			}
		}
	}
	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				volver();
				HibernateUtil.cerrarSessionesUsadas();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class ListenerBtnAceptar implements ActionListener{
	public void actionPerformed(ActionEvent e) {
			try {
				int seleccion = JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(seleccion == 0) {
					instancia.obtenidaPoliza(polizaObtenida);
				}				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}