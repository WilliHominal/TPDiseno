package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.gestor.GestorPago;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Pago;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.view.CU12View1;

public class CU12Controller1 {

	private CU12Controller1 instancia;
	private CU12View1 view1;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	private List<Cuota> cuotasApagar = new ArrayList<Cuota>();
	private List<Cuota> cuotas = new ArrayList<Cuota>();
	
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
		//TODO CU12 solo deben aparecer las cuotas a pagar el resto (setvisible false) y ademas el label de esas cuotas debe coincidir con la cuota que se está pagando
		//(si se pago ya la cuota uno, deben aparecer las cuotas 2,3,4,5,6 y sus label deben decir que son esas cuotas)
		List<Cuota> cuotasImpagas = new ArrayList<Cuota>();
		Float importeTotal = 0f;
		List<Float> montoActual = new ArrayList<Float>();
		view1.setNumeroCliente(poliza.getCliente().getNumeroCliente().toString());
		view1.setApellido(poliza.getCliente().getApellido());
		view1.setNombre(poliza.getCliente().getNombre());
		view1.setNumeroPoliza(poliza.getNumeroPoliza().toString());
		view1.setMarca(poliza.getAnioModelo().getModelo().getMarca().getNombre());
		view1.setModelo(poliza.getAnioModelo().getModelo().getNombre());
		view1.setPatente(poliza.getPatente());
		
		view1.habilitarSeleccionCuotas();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		view1.setInicioVigencia(dateFormat.format(Date.from(poliza.getInicioVigencia().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		view1.setFinVigencia(dateFormat.format(Date.from(poliza.getFinVigencia().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		
		//cargo cuotas de la poliza
		cuotas = poliza.getCuotas(); //DAOPoliza.getDAO().getCuotas(poliza.getNumeroPoliza()); //no hace falta consultar al DAO

		//busco las impagas
		for (Cuota cuota : cuotas) {
			if (cuota.getEstado().equals(EnumEstadoCuota.IMPAGO)) 
				cuotasImpagas.add(cuota);
		}
		//seteo monto cuotas impagas
		//TODO CU12 está mal esto, EnumPagoCuota es para cuando se paga una cuota, es decir, si se paga una cuota recién se le setea ese estado. Esto se hace cuando validas
		
		//TODO CU12 No se bien si calcular monto total es de todas las cuotas que no pago, o las que quiere pagar, consultar al profe.
		for (Cuota cuota : cuotasImpagas) {
			montoActual.add(cuota.getMonto());
			/*if (cuota.getEstadoPagoCuota().equals(EnumPagoCuota.EN_TERMINO)) {
				montoActual.add(cuota.getMonto());
			} else {
				if (cuota.getEstadoPagoCuota().equals(EnumPagoCuota.EN_MORA)) {
					montoActual.add(cuota.getMonto() + 1234);
				} else {
					if (cuota.getEstadoPagoCuota().equals(EnumPagoCuota.ADELANTADA)) {
						montoActual.add(cuota.getMonto() - 1234);
					/
				}
			}*/
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
	
	public void volver() {
		ventana.setContentPane(panelAnterior);
		ventana.setTitle(tituloAnterior);
		view1.setVisible(false);
	}
	
	public CU12View1 getView(){
		return view1;
	}
	
	private class ListenerBtnBuscarPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				//TODO MODIFICAR CON CU18
				//CU18Controller a = new CU18Controller(ventana);	
				//a.setPagoPolizaController(instancia); // ahi aplicar el obtenidaPoliza(con la poliza que se selecciona)
				
				
				// el cliente 5400000004 tiene la poliza-> 3528000000401 con cuotas impagas, pero tiene las primeras pagas
				// el cliente 5400000003 tiene la poliza 3528000000300 con todas las cuotas impagas
				//obtenidaPoliza((DAOPoliza.getDAO().getPolizas(5400000003l).get(0)));//quitar una vez implementado lo de arriba
				ventana.revalidate();
			}catch(Exception ex) {
				ex.printStackTrace();
            	JOptionPane.showMessageDialog(ventana, "No se pudo obtener la póliza desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		}
	}
	private class ListenerBtnConfirmarPago implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String mensaje = "El monto a pagar es de $" + view1.getImportesParciales() + ", ¿Desea continuar con el pago?";
			
			try {
				if(!condicionesGenerarPago()) {
					return;
				}				
				
				int seleccion = JOptionPane.showConfirmDialog(ventana, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				view1.noValido(false, false);
				if(seleccion == 0) {
					Pago pago = GestorPago.get().realizarPago(cuotasApagar);
					new CU12Controller2(ventana, pago).setCU12Controller1(instancia);
				}
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				volver();
				view1.setVisible(false);
				HibernateUtil.cerrarSessionesUsadas();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerSeleccionCuota1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(1)) {
				cuotasApagar.add(cuotas.get(0));
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual1());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual1()) ).toString());
				}
			} else {
				cuotasApagar.remove(cuotas.get(0));
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual1()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(2)) {
				cuotasApagar.add(cuotas.get(1));
				if (!view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual2());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual2()) ).toString());
				}
			} else {
				cuotasApagar.remove(cuotas.get(1));
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual2()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(3)) {
				cuotasApagar.add(cuotas.get(2));
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual3());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual3()) ).toString());
				}
			} else {
				cuotasApagar.remove(cuotas.get(2));
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual3()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(4)) {
				cuotasApagar.add(cuotas.get(3));
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual4());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual4()) ).toString());
				}
			} else {
				cuotasApagar.remove(cuotas.get(3));
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual4()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota5 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(5)) {
				cuotasApagar.add(cuotas.get(4));
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(1) && !view1.getEstadoCheckBoxCuota(6)) {
					view1.setImportesParciales(view1.getCuotaActual5());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual5()) ).toString());
				}
			} else {
				cuotasApagar.remove(cuotas.get(4));
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual5()) ).toString());
			}
		}
	}
	private class ListenerSeleccionCuota6 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(6)) {
				cuotasApagar.add(cuotas.get(5));
				if (!view1.getEstadoCheckBoxCuota(2) && !view1.getEstadoCheckBoxCuota(3) && !view1.getEstadoCheckBoxCuota(4) && !view1.getEstadoCheckBoxCuota(5) && !view1.getEstadoCheckBoxCuota(1)) {
					view1.setImportesParciales(view1.getCuotaActual6());
				} else {
					view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) + Float.parseFloat(view1.getCuotaActual6()) ).toString());
				}
			} else {
				cuotasApagar.remove(cuotas.get(5));
				view1.setImportesParciales(Float.valueOf( Float.parseFloat(view1.getImportesParciales()) - Float.parseFloat(view1.getCuotaActual6()) ).toString());
			}
		}
	}
	
}