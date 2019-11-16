package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.enums.EnumEstadoPoliza;
import isi.dds.tp.enums.EnumPagoCuota;
import isi.dds.tp.gestor.GestorPago;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.view.CU12View1;

@SuppressWarnings("unused")
public class CU12Controller1 {

	private CU12Controller1 instancia;
	private CU12View1 view1;
	
	private GestorPago gestorPago = GestorPago.get();
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	public CU12Controller1(JFrame ventana) {
		instancia = this;
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
		GestorTema.get().setTema(ventana, "Realizar pago de póliza: SELECCIÓN DE MONTO A PAGAR");
		view1 = new CU12View1();
		
		view1.addListenerBtn_BuscarPoliza(new ListenerBtnBuscarPoliza());;
		view1.addListenerBtn_Cancelar(new ListenerBtnCancelar());
		view1.addListenerBtn_ConfirmarPago(new ListenerBtnConfirmarPago());
		view1.addListenerSeleccionCuota1(new ListenerSeleccionCuota1());
		view1.addListenerSeleccionCuota2(new ListenerSeleccionCuota2());
		view1.addListenerSeleccionCuota3(new ListenerSeleccionCuota3());
		view1.addListenerSeleccionCuota4(new ListenerSeleccionCuota4());
		view1.addListenerSeleccionCuota5(new ListenerSeleccionCuota5());
		view1.addListenerSeleccionCuota6(new ListenerSeleccionCuota6());
		
		ventana.setContentPane(view1);
		ventana.revalidate();
	}
	
	public void obtenidaPoliza (Poliza poliza) {
		List<Cuota> cuotas = new ArrayList<Cuota>(), cuotasImpagas = new ArrayList<Cuota>();
		Float importeTotal = 0f, importeParcial = 0f;
		List<Float> montoActual = new ArrayList<Float>();
		
		view1.setNumeroCliente(poliza.getCliente().getNumeroCliente().toString());
		view1.setApellido(poliza.getCliente().getApellido());
		view1.setNombre(poliza.getCliente().getNombre());
		view1.setNumeroPoliza(poliza.getNumeroPoliza().toString());
		view1.setMarca(poliza.getAnioModelo().getModelo().getMarca().getNombre());
		view1.setModelo(poliza.getAnioModelo().getModelo().getNombre());
		view1.setPatente(poliza.getPatente());
		view1.setInicioVigencia(poliza.getInicioVigencia().toString());
		view1.setFinVigencia(poliza.getFinVigencia().toString());
		view1.habilitarSeleccionCuotas();
		
		//cargo cuotas de la poliza
		cuotas = DAOPoliza.getDAO().getCuotas(poliza.getNumeroPoliza());
		//busco las impagas
		for (Cuota cuota : cuotas)
			if (cuota.getEstado() == EnumEstadoCuota.IMPAGO)
				cuotasImpagas.add(cuota);
		
		//seteo monto cuotas impagas
		for (Cuota cuota : cuotasImpagas) {
			if (cuota.getEstadoPagoCuota() == EnumPagoCuota.EN_TERMINO) {
				montoActual.add(cuota.getMonto());
			} else {
				if (cuota.getEstadoPagoCuota() == EnumPagoCuota.EN_MORA) {
					montoActual.add(cuota.getMonto() + 1234);
				} else {
					if (cuota.getEstadoPagoCuota() == EnumPagoCuota.ADELANTADA) {
						montoActual.add(cuota.getMonto() - 1234);
					}
				}
			}
			
		}
		
		if (cuotasImpagas.size() != 0) {
			for (int i=0; i < cuotasImpagas.size(); i++) {
				switch (i) {
				case 0: view1.setCuotaOriginal1(cuotasImpagas.get(i).getMonto().toString()); view1.setCuotaActual1(montoActual.get(i).toString()); break;
				case 1: view1.setCuotaOriginal2(cuotasImpagas.get(i).getMonto().toString()); view1.setCuotaActual2(montoActual.get(i).toString()); break;
				case 2: view1.setCuotaOriginal3(cuotasImpagas.get(i).getMonto().toString()); view1.setCuotaActual3(montoActual.get(i).toString()); break;
				case 3: view1.setCuotaOriginal4(cuotasImpagas.get(i).getMonto().toString()); view1.setCuotaActual4(montoActual.get(i).toString()); break;
				case 4: view1.setCuotaOriginal5(cuotasImpagas.get(i).getMonto().toString()); view1.setCuotaActual5(montoActual.get(i).toString()); break;
				case 5: view1.setCuotaOriginal6(cuotasImpagas.get(i).getMonto().toString()); view1.setCuotaActual6(montoActual.get(i).toString()); break;
				}
			}
		}
		
		switch (cuotasImpagas.size()) {
		case 0: view1.deshabilitarCuota1();
		case 1: view1.deshabilitarCuota2();
		case 2: view1.deshabilitarCuota3();
		case 3: view1.deshabilitarCuota4();
		case 4: view1.deshabilitarCuota5();
		case 5: view1.deshabilitarCuota6();break;
		}
		
		for (Float monto : montoActual) {
			importeTotal += monto;
		}
		view1.setImportesTotales(importeTotal.toString());
		
		view1.habilitarBotonConfirmarPago();
	}
	
	private class ListenerBtnBuscarPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				//TODO MODIFICAR CON CU18
				/*CU18Controller a = new CU18Controller(ventana);	
				//a.setPagoPolizaController(instancia); // ahi aplicar el obtenidaPoliza(con la poliza que se selecciona)
				*/
				obtenidaPoliza((DAOPoliza.getDAO().getPolizas(5400000004l).get(1)));
				ventana.revalidate();
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(ventana, "No se pudo obtener la póliza desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		}
	}
	
	private Boolean condicionesGenerarPago() {
		Boolean errorSeleccion = false;
		Boolean errorCantidad = false;
		String textoErrorSeleccion = "", textoErrorCantidad = "";
		
		Integer cantidad = 0, errorNumero = 1;
		
		for (int i=6; i>0; i--) {
			if (view1.getEstadoCheckBoxCuota(i)) {
				for (int j=i-1; j>0; j--) {
					if (!view1.getEstadoCheckBoxCuota(j)) {
						errorSeleccion = true;
					}
				}
				cantidad++;
			}
		}
		
		if (cantidad == 0) {
			errorCantidad = true;
			textoErrorCantidad = errorNumero+") No se ha seleccionado ninguna cuota.\n";
			errorNumero++;
		}
		
		if (errorSeleccion) {
			textoErrorSeleccion = errorNumero+") Las cuotas a pagar deben ser seleccionadas en orden según su fecha límite de pago.\n";
		}
		
		String mensajeError = textoErrorCantidad + textoErrorSeleccion;
		
		if(errorCantidad || errorSeleccion) {
			view1.noValido(errorCantidad, errorSeleccion);
			JOptionPane.showConfirmDialog(ventana, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private class ListenerBtnConfirmarPago implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(!condicionesGenerarPago()) {
					return;
				}				
				
				int seleccion = JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar el pago?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(seleccion == 0) {
					gestorPago.registrarPago(Long.parseLong(view1.getNumeroPoliza()), view1.cantidadCuotasSeleccionadas());
					JOptionPane.showConfirmDialog(ventana, "PAGO REGISTRADO PASA A PANTALLA 2", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					//new CU12Controller2(ventana, poliza).setCU01Controller1(instancia);
				} else {
					view1.noValido(false, false);
				}
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void volver() {
		ventana.setContentPane(panelAnterior);
		ventana.setTitle(tituloAnterior);
		view1.setVisible(false);
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
	
	private class ListenerSeleccionCuota1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(1)) {
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual1());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual1()) ).toString());
				}
			} else {
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual1()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(2)) {
				if (!view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual2());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual2()) ).toString());
				}
			} else {
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual2()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(3)) {
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual3());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual3()) ).toString());
				}
			} else {
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual3()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(4)) {
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual4());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual4()) ).toString());
				}
			} else {
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual4()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota5 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(5)) {
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual5());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual5()) ).toString());
				}
			} else {
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual5()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota6 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(6)) {
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(1)) {
					view1.setImportesParciales(view1.getCuotaActual6());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual6()) ).toString());
				}
			} else {
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual6()) ).toString());
			}
		}
	}
}
