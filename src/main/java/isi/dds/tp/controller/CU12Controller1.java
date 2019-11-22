package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Cuota;
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
		Float importeTotal = 0f;
		LocalDate fechaActual = LocalDate.now();
		List<Integer> cuotasImpagas = new ArrayList<Integer>();
		
		//cargo cuotas de la poliza
		cuotas = poliza.getCuotas();
		cuotas.sort((Cuota c1, Cuota c2) -> c1.getUltimoDiaPago().compareTo(c2.getUltimoDiaPago()));

		if (poliza.getFormaPago() == EnumFormaPago.MENSUAL) {
			for (int i=0; i<6; i++) {
				if (cuotas.get(i).getEstado().equals(EnumEstadoCuota.IMPAGO))
					cuotasImpagas.add(i);
			}
		} else {
			for (int i=0; i<1; i++) {
				if (cuotas.get(i).getEstado().equals(EnumEstadoCuota.IMPAGO))
					cuotasImpagas.add(i);
			}
		}
		
		if (cuotasImpagas.size() != 0) {
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
			
			
			
			
			
			if (poliza.getFormaPago() == EnumFormaPago.MENSUAL) {
				int contadorAux = 0;
				for (int i=0; i<6; i++) {
					if (cuotas.get(i).getEstado().equals(EnumEstadoCuota.IMPAGO)) {
						view1.setCuotaOriginal(contadorAux, cuotas.get(i).getMonto().toString());
						
						if (fechaActual.isBefore(cuotas.get(i).getUltimoDiaPago())) {
							view1.setCuotaActual(contadorAux, ((Float)(cuotas.get(i).getMonto()-1234)).toString());
							importeTotal += cuotas.get(i).getMonto() - 1234;
						} else if (fechaActual.isAfter(cuotas.get(i).getUltimoDiaPago())) {
							view1.setCuotaActual(contadorAux, ((Float)(cuotas.get(i).getMonto()+1234)).toString());
							importeTotal += cuotas.get(i).getMonto() + 1234;
						} else {
							view1.setCuotaActual(contadorAux, cuotas.get(i).getMonto().toString());
							importeTotal += cuotas.get(i).getMonto();
						}
						
						contadorAux++;
					}
				}
			} else {
				if (cuotas.get(0).getEstado().equals(EnumEstadoCuota.IMPAGO)) {
					view1.setCuotaOriginal(0, cuotas.get(0).getMonto().toString());
					if (fechaActual.isBefore(cuotas.get(0).getUltimoDiaPago())) {
						view1.setCuotaActual(0, ((Float)(cuotas.get(0).getMonto()-1234)).toString());
						importeTotal += cuotas.get(0).getMonto() - 1234;
					} else if (fechaActual.isAfter(cuotas.get(0).getUltimoDiaPago())) {
						view1.setCuotaActual(0, ((Float)(cuotas.get(0).getMonto()+1234)).toString());
						importeTotal += cuotas.get(0).getMonto() + 1234;
					} else {
						view1.setCuotaActual(0, cuotas.get(0).getMonto().toString());
						importeTotal += cuotas.get(0).getMonto();
					}
				}
			}
			
			
			
			ordenarCuotas(cuotasImpagas);
			System.out.println(cuotasImpagas);
			
			//seteo monto cuotas impagas
			//TODO CU12 está mal esto, EnumPagoCuota es para cuando se paga una cuota, es decir, si se paga una cuota recién se le setea ese estado. Esto se hace cuando validas--listo?
			
			//TODO CU12 No se bien si calcular monto total es de todas las cuotas que no pago, o las que quiere pagar, consultar al profe.
			view1.setImportesTotales(importeTotal.toString());
			
			view1.habilitarBotonConfirmarPago();
			
		} else {
			JOptionPane.showMessageDialog(ventana, "La póliza ya está pagada", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public void volver() {
		ventana.setContentPane(panelAnterior);
		ventana.setTitle(tituloAnterior);
		view1.setVisible(false);
	}
	
	public CU12View1 getView(){
		return view1;
	}
	
	public List<Cuota> getCuotasApagar() {
		return cuotasApagar;
	}
	
	private Float calcularImporteParcial() {
		Float resultado = 0f;
		for (int i=0; i<cuotasApagar.size(); i++) {
			resultado += Float.parseFloat(view1.getCuotaActual(i));
		}
		
		return resultado;
	}
	
	private void ordenarCuotas(List<Integer> numeroCuotas) {

		switch(numeroCuotas.size()) {
		case 1: view1.ubicarUnaCuota(); break;
		case 2: view1.ubicarDosCuotas(); break;
		case 3: view1.ubicarTresCuotas(); break;
		case 4: view1.ubicarCuatroCuotas(); break;
		case 5: view1.ubicarCincoCuotas(); break;
		case 6: view1.ubicarSeisCuotas(); break;
		}
	}
	
	private class ListenerBtnBuscarPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				new CU18Controller(ventana).setPagoPolizaController(instancia);
				// ahi aplicar el obtenidaPoliza(con la poliza que se selecciona)
		
				// el cliente 5400000004 tiene la poliza-> 3528000000401 con cuotas impagas, pero tiene las primeras pagas
				// el cliente 5400000003 tiene la poliza 3528000000300 con todas las cuotas impagas
			//	obtenidaPoliza((DAOPoliza.getDAO().getPolizas(5400000003l).get(0)));//quitar una vez implementado lo de arriba
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

					new CU12Controller2(ventana).setCU12Controller1(instancia);
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
				HibernateUtil.cerrarSessionesUsadas();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerSeleccionCuota1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(1)) {
				switch(view1.cantidadCuotasDisponibles()) {
				case 6: cuotasApagar.add(cuotas.get(0)); break;
				case 5: cuotasApagar.add(cuotas.get(1)); break;
				case 4: cuotasApagar.add(cuotas.get(2)); break;
				case 3: cuotasApagar.add(cuotas.get(3)); break;
				case 2: cuotasApagar.add(cuotas.get(4)); break;
				case 1: cuotasApagar.add(cuotas.get(5)); break;
				}
			} else {
				switch(view1.cantidadCuotasDisponibles()) {
				case 6: cuotasApagar.remove(cuotas.get(0)); break;
				case 5: cuotasApagar.remove(cuotas.get(1)); break;
				case 4: cuotasApagar.remove(cuotas.get(2)); break;
				case 3: cuotasApagar.remove(cuotas.get(3)); break;
				case 2: cuotasApagar.remove(cuotas.get(4)); break;
				case 1: cuotasApagar.remove(cuotas.get(5)); break;
				}
			}
			view1.setImportesParciales(calcularImporteParcial().toString());
		}
	}
	private class ListenerSeleccionCuota2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//TODO CU12 para todos los listener de los check, agregar la cuota que se debería seleccionar, ya que como está hecho arriba no elige la correcta y cuando ya pagaste alguna cuota no te deja volver a pagar
			if (view1.getEstadoCheckBoxCuota(2))
				cuotasApagar.add(cuotas.get(1));
			else
				cuotasApagar.remove(cuotas.get(1));
			view1.setImportesParciales(calcularImporteParcial().toString());
		}
	}
	private class ListenerSeleccionCuota3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(3))
				cuotasApagar.add(cuotas.get(2));
			else
				cuotasApagar.remove(cuotas.get(2));
			view1.setImportesParciales(calcularImporteParcial().toString());
		}
	}
	private class ListenerSeleccionCuota4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(4))
				cuotasApagar.add(cuotas.get(3));
			else
				cuotasApagar.remove(cuotas.get(3));
			view1.setImportesParciales(calcularImporteParcial().toString());
		}
	}
	private class ListenerSeleccionCuota5 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(5))
				cuotasApagar.add(cuotas.get(4));
			else
				cuotasApagar.remove(cuotas.get(4));
			view1.setImportesParciales(calcularImporteParcial().toString());
		}
	}
	private class ListenerSeleccionCuota6 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (view1.getEstadoCheckBoxCuota(6))
				cuotasApagar.add(cuotas.get(5));
			else
				cuotasApagar.remove(cuotas.get(5));
			view1.setImportesParciales(calcularImporteParcial().toString());
		}
	}
	
}