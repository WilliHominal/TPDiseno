package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.view.CU01View1;

public class CU01Controller1 {	
	
	private CU01Controller1 instancia;
	private CU01View1 view1;
	
	private GestorEnum gestorEnum = GestorEnum.get();
	private GestorDomicilio gestorDomicilio = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	private GestorSubsistemaSiniestros gestorSubsistemaSiniestros = GestorSubsistemaSiniestros.get();
	
	private Boolean primerCliente = true;
	private Poliza poliza;
	
	private JFrame ventana;
	private JPanel panelAnteriorAPoliza;
	private String tituloAnteriorAPoliza = "";
	
	public CU01Controller1(JFrame ventana) {
		instancia = this;
		this.ventana = ventana;
		this.tituloAnteriorAPoliza = ventana.getTitle();
		try {
			panelAnteriorAPoliza = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnteriorAPoliza = null;
		}
			
		setView1();
	}
	
	private void setView1() {
		GestorTema.get().setTema(ventana, "Dar de alta póliza: INGRESAR DATOS");
		this.view1 = new CU01View1();
		view1.addListenerBtn_BuscarCliente(new ListenerBtnBuscarCliente());;
		view1.addListenerBtn_AltaCliente(new ListenerBtnAltaCliente());
		view1.addListenerBtn_Cancelar(new ListenerBtnCancelar());
		view1.addListenerBtn_AgregarHijo(new ListenerBtnDeclararHijo());
		view1.addListenerBtn_QuitarHijo(new ListenerBtnQuitarHijo());
		view1.addListenerSeleccionProvincia(new ListenerSeleccionProvincia());
		view1.addListenerSeleccionMarca(new ListenerSeleccionMarca());
		view1.addListenerSeleccionModelo(new ListenerSeleccionModelo());
		view1.addListenerSeleccionAnioModelo(new ListenerSeleccionAnioModelo());
		view1.addListenerCampoMotor(new ListenerCampoMotor());
		view1.addListenerCampoChasis(new ListenerCampoChasis());
		view1.addListenerCampoPatente(new ListenerCampoPatente());
		view1.addListenerBtn_ConfirmarDatos(new ListenerBtnConfirmarDatos());
		ventana.setContentPane(view1);
		ventana.revalidate();		
	}
	
	/**
	 * Dicha función recarga todos los hijos declarados de la póliza actual en la tabla del panel
	 */
	private  void cargarTabla(){
		Integer cantHijos = poliza.getHijosDeclarado().size();
		view1.cargarTabla(cantHijos);
		if(cantHijos > 0) {
			for(int fila = 0; fila < cantHijos; fila++) {
				LocalDate date = poliza.getHijosDeclarado().get(fila).getFechaNacimiento();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
				String formattedString = date.format(formatter);
				String sexo = GestorEnum.get().parseString(poliza.getHijosDeclarado().get(fila).getSexo());
				String estadoCivil = "";
				if(sexo.equals("Femenino")) {
					estadoCivil = gestorEnum.parseStringFem(poliza.getHijosDeclarado().get(fila).getEstadoCivil());
				}
				else {
					estadoCivil = gestorEnum.parseStringMasc(poliza.getHijosDeclarado().get(fila).getEstadoCivil());
				}
				view1.cargarHijosTabla(fila, formattedString, sexo, estadoCivil);
			}
		}
	}	

	/**
	 * Éste método es invocado una vez obtenido el cliente buscado o que fue dado de alta. Setea los campos
	 * correspondientes a los atributos del cliente, habilita componentes del panel que permiten introducir
	 * valores para la generación de al póliza, y carga objetos a los correspondientes JComboBox.
	 * @param cliente Este parámetro es solicitado por el método, ya que con el setea los campos del panel 
	 * CU01View y asocia ese cliente a la póliza a generar.
	 */
	public void obtenidoCliente(Cliente cliente) {
		view1.setNumeroCliente(cliente.getNumeroCliente().toString());
		view1.setApellido(cliente.getApellido());
		view1.setNombre(cliente.getNombre());
		view1.setTipoDocumento(gestorEnum.parseString(cliente.getTipoDocumento()));
		view1.setNumeroDocumento(cliente.getNumeroDocumento());
		view1.setCalle(cliente.getCalle()) ;
		view1.setNumeroCalle(cliente.getNumeroCalle().toString());
		
		String numeroSiniestros = gestorEnum.parseString(gestorSubsistemaSiniestros.getSiniestroUltimosAnios(cliente.getTipoDocumento(), cliente.getNumeroDocumento(), LocalDate.now().getYear()));
		poliza = gestorPoliza.newPoliza(cliente, numeroSiniestros);
		view1.setNumeroSiniestros(numeroSiniestros);

		if(cliente.getPiso() == null) {
			view1.setPiso("-");
			view1.setDepartamento("-");
		}else {
			view1.setPiso(cliente.getPiso().toString());
			view1.setDepartamento(cliente.getDepartamento());
		}
		
		if(primerCliente) {
			view1.componentesAlObtenerCliente();
			ArrayList<Provincia> provincias = (ArrayList<Provincia>) gestorDomicilio.getProvincias(cliente.getCiudad().getProvincia().getPais().getIdPais());
			Iterator<Provincia> iteradorProvincias = provincias.iterator();
			while(iteradorProvincias.hasNext()){
				view1.addProvincia(iteradorProvincias.next());
			}
			
			ArrayList<Marca> marcas = (ArrayList<Marca>) gestorVehiculo.getMarcas();
			view1.addMarca(new Marca("Seleccionar marca"));
			Iterator<Marca> marcasIterator = marcas.iterator();
			while(marcasIterator.hasNext()){
				view1.addMarca(marcasIterator.next());
			}
			
			view1.setProvinciaInicio(cliente.getCiudad().getProvincia());
			view1.habilitarSeleccionModelo(false);
			view1.habilitarSeleccionAnioModelo(false);
			
			view1.addKmsAnio();
			primerCliente = false;
		}

		
		Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(cliente.getCiudad().getProvincia()).iterator();
		view1.addCiudad(iteratorCiudad.next(), true);
		while(iteratorCiudad.hasNext()){
			view1.addCiudad(iteratorCiudad.next(), false);
		}
		
		view1.setCiudadInicio(cliente.getCiudad());
	}

	/**
	 * Éste método asocia la póliza a generar con el hijo recientemente declarado y luego lo carga en la tabla
	 * de hijos declarados del panel CU01View.
	 * @param hijoDeclarado Hijo declarado a asociar con la póliza y a cargar en la tabla.
	 */
	public void agregarHijoTabla(HijoDeclarado hijoDeclarado) {
		if(hijoDeclarado != null) {
			gestorPoliza.addHijo(poliza, hijoDeclarado);
			cargarTabla();
			componentesAlDeclararHijos(true);
		}
	}

	/** 
	 * Este método retorna un valor Boolean de acuerdo a sí se dan las condiciones necesarias para generar una
	 * póliza. Si no se dan las condiciones necesarias genera un aviso de error, distiguiendo que se está
	 * incumpliendo para poder generar póliza.
	 */
	private  Boolean condicionesGenerarPoliza() {
		String textoMotor = view1.getMotor();
		String textoChasis = view1.getChasis();
		String textoPatente = view1.getPatente();
		String textoErrorPatente = "", textoErrorChasis = "", textoErrorMotor = "",  textoErrorMarca = "",  textoErrorKm = "";
		
		//los valores boolean son para luego la interfaz establezca los colores de los campos mal validados
		Boolean errorEnMarca = false, errorEnMotor = false, errorEnChasis = false, errorEnPatente = false, errorEnKm = false;
		
		Integer errorNumero = 1;
	
		//---------- posible error en seleccionar marca
		if (view1.getMarca().equals(new Marca("Seleccionar marca"))) {
			errorEnMarca  = true;
			textoErrorMarca = errorNumero+") No se ha seleccionado un valor del campo marca.\n";
			errorNumero++;
		}
		
		//---------- posible error en la introdución del número de motor
		if(textoMotor.isEmpty()) {
			errorEnMotor = true;
			textoErrorMotor = errorNumero+") No se ha introducido un número de motor\n";
			errorNumero++;
		}
		else {
			if(textoMotor.length() == 17) {
				for(int i = 10; i < 17; i++) {
					if(Character.isLetter(textoMotor.charAt(i))) {
						errorEnMotor = true;
						textoErrorMotor = errorNumero+") Formato de motor incorrecto. El formato de un número de chasis es CCCCCCCCCC9999999, donde\n"
								+ "las C debe indican que debe escribirse un dígito o una letra los 9 indican que deb escrirse un dígito.\n";
						errorNumero++;
						break;
					}
				}
				if(!errorEnMotor) {
					if(gestorPoliza.validarMotor(textoMotor)) {
						errorEnMotor = true;
						textoErrorMotor = errorNumero+") El valor ingresado del número de motor, ya está registrado como parte de otra póliza.\n";
						errorNumero++;
					}
				}
			}
			else {
				errorEnMotor = true;
				textoErrorMotor = errorNumero+") La definición de un número de motor debe ser de longitud 17.\n";
				errorNumero++;
			}	
		}
		
		//---------- posible error en la introdución del numero de chasis
		if(textoChasis.isEmpty()) {
			errorEnChasis = true;
			textoErrorChasis = errorNumero+") No se ha introducido un número de chasis.\n";
			errorNumero++;
		}
		else{
			if(textoChasis.length() == 8) {
				for(int i = 1; i < 8; i++) {
					if(!Character.isDigit(textoChasis.charAt(i))) {
						errorEnChasis = true;
						textoErrorChasis = errorNumero+") Formato de chasis incorrecto. El formato de un número de chasis es C9999999, donde C indica\n"
								+ "que debe escribirse un dígito o una letra y los 9 indican quedebe escribirse un dígito.\n";
						errorNumero++;
						break;
					}
				}
				if(!errorEnChasis) {
					if(gestorPoliza.validarChasis(textoChasis)) {
						errorEnChasis = true;
						textoErrorChasis = errorNumero+") El valor ingresado del número de chasis, ya está registrado como parte de otra póliza.\n";
						errorNumero++;
					}
				}
			}
			else {
				errorEnChasis = true;
				textoErrorChasis = errorNumero+") La definición de un número de chasis debe ser de longitud 8.\n";
				errorNumero++;
			}			
		}
		
		//---------- posible error en la introducción del número de patente
		if(!textoPatente.isEmpty()) {
			switch (textoPatente.length()) {
		        case 6: //para patente longitud 6
		        	for(int i = 0; i < 6; i++) {	        		
			        	switch(i) {
			        		case 0: case 1: case 2:
			    				if(!Character.isLetter(textoPatente.charAt(i))) {
			    					errorEnPatente = true;
			    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
			    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
			    					errorNumero++;
			    				}
			        		break;
			        		
			        		case 3: case 4: case 5:
			    				if(!Character.isDigit(textoPatente.charAt(i))) {
			    					errorEnPatente = true;
			    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
			    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
			    					errorNumero++;
			    				}
			        		break;
			        	}
			        	if(errorEnPatente) {
			        		break;
			        	}
		        	}
					if(!errorEnPatente) {
						if(gestorPoliza.validarPatente(textoPatente)) {
							errorEnPatente = true;
							textoErrorPatente = errorNumero+") El valor ingresado del número de motor, ya está registrado como parte de otra póliza.\n";
							errorNumero++;
						}
					}
		        break;     
		        
		        case 7:  //para patente longitud 7
		        	for(int j = 0; j < 7; j++) {
			        	switch(j) {
			        		case 0: case 1: case 5: case 6:
			    				if(Character.isDigit(textoPatente.charAt(j))) {
			    					errorEnPatente = true;
			    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
			    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
			    					errorNumero++;
			    				}
			        		break;
			        
			        		case 2: case 3: case 4:
			    				if(Character.isLetter(textoPatente.charAt(j))) {
			    					errorEnPatente = true;
			    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
			    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";	
			    					errorNumero++;
			    				}
			        		break;
			        	}		    
			        	if(errorEnPatente) {
			        		break;
			        	}
		        	}
					if(!errorEnPatente) {
						if(gestorPoliza.validarPatente(textoPatente)) {
							errorEnPatente = true;
							textoErrorPatente = errorNumero+") El valor ingresado del número de motor, ya está registrado como parte de otra póliza.\n";
							errorNumero++;
						}
					}
		        break;
	
		        default:
		        	errorEnPatente = true;
		        	textoErrorPatente = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
					errorNumero++;
				break;
			}	
		}
		
		//---------- posible error en la no selección de un kilometraje
		if (view1.getKmAnio().equals("Selecionar kilometraje")) {
			errorEnKm = true;
			textoErrorKm = errorNumero+") No se ha seleccionado un valor del campo km realizados por año.\n";
		}
		
		String mensajeError = textoErrorMarca + textoErrorMotor + textoErrorChasis + textoErrorPatente + textoErrorKm;
		
		if(errorEnMarca || errorEnMotor || errorEnChasis || errorEnPatente || errorEnKm) {
			view1.noValido(errorEnMarca, errorEnMotor, errorEnChasis, errorEnPatente, errorEnKm);
			JOptionPane.showConfirmDialog(ventana, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void componentesAlDeclararHijos(Boolean estaDeclarando) { 
		view1.componentesAlDeclararHijos(estaDeclarando, poliza.getHijosDeclarado().size());
	}
	
	public void volver() {
		ventana.setContentPane(panelAnteriorAPoliza);
		ventana.setTitle(tituloAnteriorAPoliza);
		view1.setVisible(false);
		HibernateUtil.cerrarSessionesUsadas();
	}
	
	//-------- LISTENER
	private class ListenerBtnBuscarCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				CU17Controller1 a = new CU17Controller1(ventana);	
				a.setAltaPolizaController(instancia);
				ventana.revalidate();
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(ventana, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		}
	}

	private class ListenerBtnAltaCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {				
				new CU04Controller(ventana);
				ventana.revalidate();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(ventana, "No se pudo obtener el cliente desde la base de datos",
                        "Error.", JOptionPane.ERROR_MESSAGE);    
			}
		}
	}
	
	private class ListenerBtnDeclararHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {				
				view1.componentesAlDeclararHijos(false, 0);
				new CU01Controller3().setCU01Controller1(instancia);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerBtnQuitarHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(poliza.getHijosDeclarado().size() > 0) {
					gestorPoliza.removeHijo(poliza, view1.getFilaSeleccionada());
					cargarTabla();
					return;
				}
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerBtnConfirmarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				if(!condicionesGenerarPoliza()) {
					return;
				}				
				int seleccion = JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(seleccion == 0) {
					String patente = null;
					
					if(!view1.getPatente().equalsIgnoreCase("")) {
						patente = view1.getPatente();
					}	
					gestorPoliza.actualizarPoliza(
							poliza, view1.getCiudad(), view1.getAnioModelo(), view1.getMotor(), view1.getChasis(), patente, 
							view1.getAnioModelo().getSumaAsegurada(), view1.getKmAnio(), view1.getGarage(),
							view1.getAlarma(), view1.getRastreo(), view1.getTuercasAntirrobo()
					);
					new CU01Controller2(ventana, poliza).setCU01Controller1(instancia);
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
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerSeleccionProvincia implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Provincia provincia = view1.getProvincia();
			view1.habilitarSeleccionCiuad(false);
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(provincia).iterator();
			view1.addCiudad(iteratorCiudad.next(), true);
			while(iteratorCiudad.hasNext()){
				view1.addCiudad(iteratorCiudad.next(), false);
			}
			view1.habilitarSeleccionCiuad(true);
		}
	}
	
	private class ListenerSeleccionMarca implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Marca marca = view1.getMarca();
			view1.habilitarSeleccionModelo(false);
			view1.habilitarSeleccionAnioModelo(false);
			if(!marca.equals(new Marca("Seleccionar marca"))) {
				Iterator<Modelo> iteratorModelo = gestorVehiculo.sortModelos(marca).iterator();
				while(iteratorModelo.hasNext()){
					view1.addModelo(iteratorModelo.next());
				}				
				Iterator<AnioModelo> iteratorAnioModelo = gestorVehiculo.sortAniosModelo(view1.getModelo()).iterator();
				while(iteratorAnioModelo.hasNext()){
					view1.addAnioModelo(iteratorAnioModelo.next());
				}			
				view1.habilitarSeleccionModelo(true);
				view1.habilitarSeleccionAnioModelo(true);
				DecimalFormat num = new DecimalFormat("#,###.00");
				view1.setSumaAsegurada(num.format(view1.getAnioModelo().getSumaAsegurada()));
			}
		}
	}
	
	private class ListenerSeleccionModelo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(view1.habilitadaSeleccionModelo()) {
				view1.habilitarSeleccionAnioModelo(false);
				Modelo modelo = view1.getModelo();		
				Iterator<AnioModelo> iteratorAnioModelo = gestorVehiculo.sortAniosModelo(modelo).iterator();
				while(iteratorAnioModelo.hasNext()){
					view1.addAnioModelo(iteratorAnioModelo.next());
				}
				view1.habilitarSeleccionAnioModelo(true);
				view1.setSumaAsegurada(view1.getAnioModelo().getSumaAsegurada().toString());
				DecimalFormat num = new DecimalFormat("#,###.00");
				view1.setSumaAsegurada(num.format(view1.getAnioModelo().getSumaAsegurada()));
			}
		}
	}
	
	private class ListenerSeleccionAnioModelo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(view1.habilitadaSeleccionAnioModelo()) {
				DecimalFormat num = new DecimalFormat("#,###.00");
				view1.setSumaAsegurada(num.format(view1.getAnioModelo().getSumaAsegurada()));
			}
			else {
				view1.setSumaAsegurada("");
			}
		}
	}	

	private class ListenerCampoMotor implements KeyListener{
		public void keyTyped(KeyEvent e) {
			Character caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
					&& view1.getMotor().length() < 17 ){
				if(Character.isLowerCase(caracter)){
			    	 caracter = Character.toUpperCase(caracter);
				}
				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		} 
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
	}
	
	private class ListenerCampoChasis implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if(( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
					&& view1.getChasis().length() < 8 ){
				if(Character.isLowerCase(caracter)){
			    	 caracter = Character.toUpperCase(caracter);
				}
				e.setKeyChar(caracter);
			}
			else{
				e.consume();  // ignorar el evento de teclado
				//getToolkit().beep();
			}
		} 
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
	}
	
	private class ListenerCampoPatente implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
					&& view1.getPatente().length() < 7 ){
				if(Character.isLowerCase(caracter)){
			    	 caracter = Character.toUpperCase(caracter);
				}
				e.setKeyChar(caracter);
			}
			else{
				e.consume();  // ignorar el evento de teclado
			//	getToolkit().beep();
			}
		} 
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
	}
}
