package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.view.CU01View1;
import isi.dds.tp.view.CU01View2;
import isi.dds.tp.view.CU01View3;

public class CU01Controller1 {	
	private CU01Controller1 instancia;
	private CU01View1 altaPoliza1;
	private CU01View3 declararHijo;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	private GestorEnum gestorEnum = GestorEnum.get();
	private GestorDomicilio gestorDomicilio = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	private GestorSubsistemaSiniestros gestorSubsistemaSiniestros = GestorSubsistemaSiniestros.get();
	
	private Boolean primerCliente = true;
	private Poliza poliza;
	
	public CU01Controller1(JFrame ventana) {
		instancia = this;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
			
		setView1_AltaPoliza1();
	}
	
	private void setView1_AltaPoliza1() {
		//TODO agregar $ a string suma asegurada
		GestorTema.get().setTema(ventana, "Dar de alta póliza: INGRESAR DATOS");
		this.altaPoliza1 = new CU01View1();
		altaPoliza1.addListenerBtn_BuscarCliente(new ListenerView1BuscarCliente());;
		altaPoliza1.addListenerBtn_AltaCliente(new ListenerView1AltaCliente());
		altaPoliza1.addListenerBtn_AgregarHijo(new ListenerView1DeclararHijo());
		altaPoliza1.addListenerBtn_QuitarHijo(new ListenerView1QuitarHijo());
		altaPoliza1.addListenerSeleccionProvincia(new ListenerView1Provincia());
		altaPoliza1.addListenerSeleccionMarca(new ListenerView1Marca());
		altaPoliza1.addListenerSeleccionModelo(new ListenerView1Modelo());
		altaPoliza1.addListenerSeleccionAnioModelo(new ListenerView1AnioModelo());
		altaPoliza1.addListenerBtn_ConfirmarDatos(new ListenerView1ConfirmarDatos());
		altaPoliza1.addListenerBtn_Cancelar(new ListenerView1CancelarAltaPoliza());
		ventana.setContentPane(altaPoliza1);
		ventana.revalidate();
	}
		
	//-------- MÉTODOS QUE TRABAJAN SOBRE CU01View1 - AltaPoliza1
	private  void cargarTabla(){
		Integer cantHijos = poliza.getHijosDeclarado().size();
		altaPoliza1.cargarTabla(cantHijos);
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
				altaPoliza1.cargarHijosTabla(fila, formattedString, sexo, estadoCivil);
			}
		}
	}	

	/**
	 * Éste método es invocado una vez obtenido el cliente buscado o que fue dado de alta.
	 * Setea los campos correspondientes a los atributos del cliente, inhabilita/habilita
	 * ciertos componentes del panel, y recargar ciertos valores para la generación de la 
	 * póliza.
	 * @param cliente
	 */
	public void obtenidoCliente(Cliente cliente) {
		poliza = new Poliza();//TODO hacer en gestor
		gestorPoliza.actualizarPoliza(poliza, cliente);
		altaPoliza1.setNumeroCliente(cliente.getNumeroCliente().toString());
		altaPoliza1.setApellido(cliente.getApellido());
		altaPoliza1.setNombre(cliente.getNombre());
		altaPoliza1.setTipoDocumento(gestorEnum.parseString(cliente.getTipoDocumento()));
		altaPoliza1.setNumeroDocumento(cliente.getNumeroDocumento());
		altaPoliza1.setCalle(cliente.getCalle()) ;
		altaPoliza1.setNumeroCalle(cliente.getNumeroCalle().toString());
		String numeroSiniestros = gestorEnum.parseString(gestorSubsistemaSiniestros.getSiniestroUltimosAnios(cliente.getTipoDocumento(), cliente.getNumeroDocumento(), LocalDate.now().getYear()));
		altaPoliza1.setNumeroSiniestros(numeroSiniestros);
		
		if(cliente.getPiso() == null) {
			altaPoliza1.setPiso("-");
			altaPoliza1.setDepartamento("-");
		}else {
			altaPoliza1.setPiso(cliente.getPiso().toString());
			altaPoliza1.setDepartamento(cliente.getDepartamento());
		}
		
		if(primerCliente) {
			altaPoliza1.componentesAlObtenerCliente();
			
			ArrayList<Provincia> provincias = (ArrayList<Provincia>) gestorDomicilio.getProvincias(cliente.getCiudad().getProvincia().getPais().getIdPais());
			Iterator<Provincia> iteradorProvincias = provincias.iterator();
			while(iteradorProvincias.hasNext()){
				altaPoliza1.addProvincia(iteradorProvincias.next());
			}
			
			ArrayList<Marca> marcas = (ArrayList<Marca>) gestorVehiculo.getMarcas();
			altaPoliza1.addMarca(new Marca("Seleccionar marca"));
			Iterator<Marca> marcasIterator = marcas.iterator();
			while(marcasIterator.hasNext()){
				altaPoliza1.addMarca(marcasIterator.next());
			}
			
			altaPoliza1.addKmsAnio();
		}

		altaPoliza1.setProvinciaInicio(cliente.getCiudad().getProvincia());
		altaPoliza1.habilitarSeleccionModelo(false);
		altaPoliza1.habilitarSeleccionAnioModelo(false);
		
		Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(cliente.getCiudad().getProvincia()).iterator();
		altaPoliza1.addCiudad(iteratorCiudad.next(), true);
		while(iteratorCiudad.hasNext()){
			altaPoliza1.addCiudad(iteratorCiudad.next(), false);
		}
		
		altaPoliza1.setCiudadInicio(cliente.getCiudad());
	}

	public void agregarHijoTabla(HijoDeclarado hijo) {
		if(hijo != null) {
			gestorPoliza.addHijo(poliza, hijo);
			cargarTabla();
			altaPoliza1.componentesAlDeclararHijos(true, poliza.getHijosDeclarado().size());
		}
	}

	/**
	 * Este método retorna un valor Boolean de acuerdo a sí
	 * se dan las condiciones necesarias para generar una
	 * póliza. Si no se dan las condiciones necesarias 
	 * genera un aviso de error, distiguiendo que se está
	 * incumpliendo para poder generar póliza.
	 * @return
	 */
	private  Boolean condicionesGenerarPoliza() {
		String textoMotor = altaPoliza1.getMotor();
		String textoChasis = altaPoliza1.getChasis();
		String textoPatente = altaPoliza1.getPatente();
		String textoErrorPatente = "", textoErrorChasis = "", textoErrorMotor = "",  textoErrorMarca = "",  textoErrorKm = "";
		
		//los valores boolean son para luego la interfaz establezca los colores de los campos mal validados
		Boolean errorEnMarca = false, errorEnMotor = false, errorEnChasis = false, errorEnPatente = false, errorEnKm = false;
		
		Integer errorNumero = 1;
	
		//---------- posible error en seleccionar marca
		if (altaPoliza1.getMarca().equals(new Marca("Seleccionar marca"))) {
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
					if(gestorPoliza.validarMotor(textoPatente)) {
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
		if (altaPoliza1.getKmAnio().equals("Selecionar kilometraje")) {
			errorEnKm = true;
			textoErrorKm = errorNumero+") No se ha seleccionado un valor del campo km realizados por año.\n";
		}
		
		String mensajeError = textoErrorMarca + textoErrorMotor + textoErrorChasis + textoErrorPatente + textoErrorKm;
		
		if(errorEnMarca || errorEnMotor || errorEnChasis || errorEnPatente || errorEnKm) {
			altaPoliza1.noValido(errorEnMarca, errorEnMotor, errorEnChasis, errorEnPatente, errorEnKm);
			JOptionPane.showConfirmDialog(ventana, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public Poliza getPoliza() {
		return poliza;
	}
	//-------- MÉTODOS QUE TRABAJAN SOBRE CU01View3 - DeclararHijo
	
	
	//-------- LISTENER VIEW 1
	private class ListenerView1BuscarCliente implements ActionListener{
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

	private class ListenerView1AltaCliente implements ActionListener{
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
	
	private class ListenerView1DeclararHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {				
				altaPoliza1.componentesAlDeclararHijos(false, 0);
				CU01Controller3 declararHijo = new CU01Controller3();
				declararHijo.setController1(instancia);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerView1QuitarHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(poliza.getHijosDeclarado().size() > 0) {
					gestorPoliza.removeHijo(poliza, altaPoliza1.getFilaSeleccionada());
					cargarTabla();
					return;
				}
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerView1ConfirmarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				if(!condicionesGenerarPoliza()) {
					return;
				}
				//TODO mayuscula a las patentes chasis y motor
				if(JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
					String patente = null;
					
					if(!altaPoliza1.getPatente().equalsIgnoreCase("")) {
						patente = altaPoliza1.getPatente();
					}
					
					
					gestorPoliza.actualizarPoliza(
							poliza, altaPoliza1.getCiudad(), altaPoliza1.getAnioModelo(), altaPoliza1.getMotor(),
							altaPoliza1.getChasis(), patente, Float.parseFloat(altaPoliza1.getSumaAsegurada()), 
							altaPoliza1.getKmAnio(), gestorEnum.parseEnumSiniestros(altaPoliza1.getNumeroSiniestros()), altaPoliza1.getGarage(),
							altaPoliza1.getAlarma(), altaPoliza1.getRastreo(), altaPoliza1.getTuercasAntirrobo()
					);
					new CU01Controller2(ventana, poliza);
				}
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerView1CancelarAltaPoliza implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
				altaPoliza1.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerView1Provincia implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Provincia provincia = altaPoliza1.getProvincia();
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(provincia).iterator();
			altaPoliza1.addCiudad(iteratorCiudad.next(), true);
			while(iteratorCiudad.hasNext()){
				altaPoliza1.addCiudad(iteratorCiudad.next(), false);
			}
		}
	}
	
	private class ListenerView1Marca implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			Marca marca = altaPoliza1.getMarca();
			altaPoliza1.habilitarSeleccionModelo(false);
			altaPoliza1.habilitarSeleccionAnioModelo(false);
			if(!marca.equals(new Marca("Seleccionar marca"))) {
				Iterator<Modelo> iteratorModelo = marca.getModelos().iterator();
				while(iteratorModelo.hasNext()){
					altaPoliza1.addModelo(iteratorModelo.next());
				}				
				Iterator<AnioModelo> iteratorAnioModelo = gestorVehiculo.sortAniosModelo(altaPoliza1.getModelo()).iterator();
				while(iteratorAnioModelo.hasNext()){
					altaPoliza1.addAnioModelo(iteratorAnioModelo.next());
				}			
				altaPoliza1.habilitarSeleccionModelo(true);
				altaPoliza1.habilitarSeleccionAnioModelo(true);
				altaPoliza1.setSumaAsegurada(altaPoliza1.getAnioModelo().getSumaAsegurada().toString());
			}
		}
	}
	
	private class ListenerView1Modelo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(altaPoliza1.habilitadaSeleccionModelo()) {
				altaPoliza1.habilitarSeleccionAnioModelo(false);
				Modelo modelo = altaPoliza1.getModelo();		
				Iterator<AnioModelo> iteratorAnioModelo = gestorVehiculo.sortAniosModelo(modelo).iterator();
				while(iteratorAnioModelo.hasNext()){
					altaPoliza1.addAnioModelo(iteratorAnioModelo.next());
				}
				altaPoliza1.habilitarSeleccionAnioModelo(true);
				altaPoliza1.setSumaAsegurada(altaPoliza1.getAnioModelo().getSumaAsegurada().toString());
			}
		}
	}
	
	private class ListenerView1AnioModelo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(altaPoliza1.habilitadaSeleccionAnioModelo()) {
				altaPoliza1.setSumaAsegurada(altaPoliza1.getAnioModelo().getSumaAsegurada().toString());
			}
			else {
				altaPoliza1.setSumaAsegurada("");
			}
		}
	}

	
	
}
