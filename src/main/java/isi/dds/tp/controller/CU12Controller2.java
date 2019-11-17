package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.gestor.GestorPago;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.view.CU12View2;

public class CU12Controller2 {
	private CU12View2 view2;
	private CU12Controller1 controller1;
	
	private GestorPago gestorPago = GestorPago.get();
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	public CU12Controller2(JFrame ventana) {
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
		setView();
	}

	private void setView() {
		GestorTema.get().setTema(ventana, "Realizar pago de póliza: REGISTRAR PAGO DE PÓLIZA");
		view2 = new CU12View2();
		
		view2.addListenerBtn_CambiarMonto(new ListenerBtnCambiarMonto());;
		view2.addListenerBtn_Cancelar(new ListenerBtnCancelar());
		view2.addListenerBtn_CompletarPago(new ListenerBtnCompletarPago());
		view2.addListenerCampoMontoAbonado(new ListenerCampoMontoAbonado());
		
		ventana.setContentPane(view2);
		ventana.revalidate();
	}
	
	public void setCU12Controller1(CU12Controller1 controller1) {
		this.controller1 = controller1;
	}
	
	private Float calcularVuelto(char caracter) {
		//TODO CU12 creo que el vuelot no se calcula bien
		Float montoAbonado = 0f, totalAPagar = 0f;
		if (!view2.getMontoAbonado().isEmpty())
			montoAbonado = Float.parseFloat(view2.getMontoAbonado())*10 + (float) Character.getNumericValue(caracter);
		else
			montoAbonado = (float) Character.getNumericValue(caracter);
		totalAPagar = Float.parseFloat(controller1.getView().getImportesParciales());
		return montoAbonado-totalAPagar;
	}
	
	private Float calcularVuelto() {
		Float montoAbonado = 0f, totalAPagar = 0f;
		if (!view2.getMontoAbonado().isEmpty())
			montoAbonado = (Float.parseFloat(view2.getMontoAbonado())/10f);
		else
			montoAbonado = 0f;
		totalAPagar = Float.parseFloat(controller1.getView().getImportesParciales());
		return montoAbonado-totalAPagar;
	}
	
	private Boolean condicionesGenerarPago() {
		Boolean errorMonto = false;
		String textoErrorMonto = "";
		Integer errorNumero = 1;
		
		if (Float.parseFloat(view2.getVuelto()) < 0) {
			errorMonto = true;
			textoErrorMonto = errorNumero+") El monto abonado debe ser mayor al importe a pagar.\n";
			errorNumero++;
		}
		
		if(errorMonto) {
			view2.noValido(errorMonto);
			JOptionPane.showConfirmDialog(ventana, textoErrorMonto, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public void volver() {
		ventana.setContentPane(panelAnterior);
		ventana.setTitle(tituloAnterior);
		view2.setVisible(false);
	}
	
	private class ListenerCampoMontoAbonado implements KeyListener{
		public void keyTyped(KeyEvent e) {
			Character caracter = e.getKeyChar();
			if(Character.isDigit(caracter) ||  caracter == '.' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
				//TODO CU12 fijarse bien lo del boton borrar
				if(caracter == '.' && view2.getMontoAbonado().contains(".")) {
					e.consume();
				}else {
					if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						if (view2.getMontoAbonado().length() == 1)
							view2.setVuelto( ((Float)( - Float.parseFloat(controller1.getView().getImportesParciales()) )).toString() );
						else
							view2.setVuelto(calcularVuelto().toString());
					}
					else {
						e.setKeyChar(caracter);
						view2.setVuelto(calcularVuelto(caracter).toString());
					}	
				}			
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {} 
	}	

	private class ListenerBtnCambiarMonto implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ventana.setContentPane(panelAnterior);
			ventana.setTitle(tituloAnterior);
			view2.setVisible(false);
		}
	}
	private class ListenerBtnCompletarPago implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(!condicionesGenerarPago()) {
					return;
				}				
				
				int seleccion = JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar el pago?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(seleccion == 0) {
					gestorPago.registrarPago(controller1.getCuotasApagar(), Float.parseFloat(controller1.getView().getImportesParciales()));
					JOptionPane.showConfirmDialog(ventana, "Pago registrado correctamente.", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					controller1.volver();
					view2.setVisible(false);
					HibernateUtil.cerrarSessionesUsadas();
					
				} else {
					view2.noValido(false);
				}
			}catch(Exception ex) {
				//TODO CU12 poner bien el mensaje y hacer que si no se introduce algun monto no esté habilitado el boton pagar 
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				volver();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
