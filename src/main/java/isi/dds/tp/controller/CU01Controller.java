package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.view.CU01View1;
import isi.dds.tp.view.CU01View2;
import isi.dds.tp.view.CU01View3;

public class CU01Controller {
		
	private GestorEnum gestorEnum = GestorEnum.get();
	private GestorDomicilio gestorDomicilio = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	
	private CU01Controller instancia;
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	private CU01View1 altaPoliza1;
	private Boolean primerCliente = true;
	public Poliza poliza = new Poliza();
	public List<HijoDeclarado> hijosDeclarados =  new ArrayList<HijoDeclarado>();
	
	private CU01View3 declararHijo;
	
	public CU01Controller(JFrame ventana) {
		instancia = this;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
			panelAnterior = null;
		}
			
		setAltaPoliza1();
	}
	
	private void setAltaPoliza1() {
		GestorTema.get().setTema(ventana, "Dar de alta póliza: INGRESAR DATOS");
		this.altaPoliza1 = new CU01View1();
		altaPoliza1.addListenerBtn_BuscarCliente(new ListenerBuscarCliente());;
		altaPoliza1.addListenerBtn_AltaCliente(new ListenerAltaCliente());
		altaPoliza1.addListenerBtn_AgregarHijo(new ListenerDeclararHijo());
		altaPoliza1.addListenerBtn_QuitarHijo(new ListenerQuitarHijo());
		altaPoliza1.addListenerSeleccionProvincia(new ListenerProvincia());
		altaPoliza1.addListenerSeleccionMarca(new ListenerMarca());
		altaPoliza1.addListenerSeleccionModelo(new ListenerModelo());
		altaPoliza1.addListenerSeleccionAnioModelo(new ListenerAnioModelo());
		altaPoliza1.addListenerBtn_ConfirmarDatos(new ListenerConfirmarDatos());
		altaPoliza1.addListenerBtn_Cancelar(new ListenerCancelarAltaPoliza());
		poliza.setHijosDeclarado(new ArrayList<HijoDeclarado>());
		ventana.setContentPane(altaPoliza1);
		ventana.revalidate();
	}
	
	public void cargarTabla(){
		Integer cantHijos = hijosDeclarados.size();
		altaPoliza1.cargarTabla(cantHijos);
		if(cantHijos > 0) {
			for(int fila = 0; fila < cantHijos; fila++) {
				LocalDate date = hijosDeclarados.get(fila).getFechaNacimiento();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
				String formattedString = date.format(formatter);
				String sexo = GestorEnum.get().parseString(hijosDeclarados.get(fila).getSexo());
				String estadoCivil = "";
				if(sexo.equals("Femenino")) {
					estadoCivil = gestorEnum.parseStringFem(hijosDeclarados.get(fila).getEstadoCivil());
				}
				else {
					estadoCivil = gestorEnum.parseStringMasc(hijosDeclarados.get(fila).getEstadoCivil());
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
		poliza.setCliente(cliente);
	
		altaPoliza1.setNumeroCliente(cliente.getNumeroCliente().toString());
		altaPoliza1.setApellido(cliente.getApellido());
		altaPoliza1.setNombre(cliente.getNombre());
		altaPoliza1.setTipoDocumento(gestorEnum.parseString(cliente.getTipoDocumento()));
		altaPoliza1.setNumeroDocumento(cliente.getNumeroDocumento());
		altaPoliza1.setCalle(cliente.getCalle()) ;
		altaPoliza1.setNumeroCalle(cliente.getNumeroCalle().toString());
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

	/**
	 * Este método retorna un valor Boolean de acuerdo a sí
	 * se dan las condiciones necesarias para generar una
	 * póliza Si no se dan las condiciones necesarias 
	 * genera un aviso de error, distiguiendo que se está
	 * incumpliendo para poder generar póliza.
	 * @return
	 */
	public Boolean condicionesGenerarPoliza() {
		String patenteLargo = "", patenteFormato6 = "", patenteFormato7 = "";
		String chasisBlanco = "", chasisLargo = "", chasisFormato = "";
		String motorBlanco = "", motorLargo = "", motorFormato = "";
		String marcaSelecciono = "", kmSelecciono = "";
		
		//los valores boolean son para luego la interfaz establezca los colores de los campos mal validados
		Boolean marca = false, motor = false, chasis = false, patente = false, km = false;
		
		Boolean valido = true;
		
		int errorNumero = 1;
	
		if (altaPoliza1.getMarca().equals(new Marca("Seleccionar marca"))) {
			marca  = true;
			marcaSelecciono = errorNumero+") No se ha seleccionado un valor del campo marca.\n";
			errorNumero++;
			valido = false;
		}
		
		String textoMotor = altaPoliza1.getMotor();
		String textoChasis = altaPoliza1.getChasis();
		String textoPatente = altaPoliza1.getPatente();
		
		if(textoMotor.isEmpty()) {
			motor = true;
			motorBlanco = errorNumero+") No se ha introducido un número de motor\n";
			errorNumero++;
			valido = false;
		}
		else {
			if(textoMotor.length() == 17) {
				for(int i = 10; i < 17; i++) {
					if(Character.isLetter(textoMotor.charAt(i))) {
						motor = true;
						motorFormato = errorNumero+") Formato de motor incorrecto. El formato de un número de chasis es CCCCCCCCCC9999999, donde\n"
								+ "las C debe indican que debe escribirse un dígito o una letra los 9 indican que deb escrirse un dígito.\n";
						errorNumero++;
						valido = false;
						i = 17;
					}
					else {
						motor = false;
					}
				}
			}
			else {
				motor = false;
				motorLargo = errorNumero+") La definición de un número de motor debe ser de longitud 17.\n";
				errorNumero++;
				valido = false;
			}	
		}
		
		if(textoChasis.isEmpty()) {
			chasis = true;
			chasisBlanco = errorNumero+") No se ha introducido un número de chasis.\n";
			errorNumero++;
			valido = false;
		}
		else{
			if(textoChasis.length() == 8) {
				for(int i = 1; i < 8; i++) {
					if(!Character.isDigit(textoChasis.charAt(i))) {
						chasis = true;
						chasisFormato = errorNumero+") Formato de chasis incorrecto. El formato de un número de chasis es C9999999, donde C indica\n"
								+ "que debe escribirse un dígito o una letra y los 9 indican quedebe escribirse un dígito.\n";
						errorNumero++;
						valido = false;
						i = 8;
					}
					else {
						chasis = false;
					}
				}
			}
			else {
				chasis = true;
				chasisLargo = errorNumero+") La definición de un número de chasis debe ser de longitud 8.\n";
				errorNumero++;
				valido = false;
			}			
		}
		
		if(!textoPatente.isEmpty()) {
			switch (textoPatente.length()) {
	        case 6: //para patente longitud 6
	        	for(int i = 0; i < 6; i++) {
	        		
		        	switch(i) {
		        		case 0:
		        		case 1:
		        		case 2:
		    				if(!Character.isLetter(textoPatente.charAt(i))) {
		    					patente = true;
		    					patenteFormato6 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					i = 6;
		    				}
		    				else {
		    					patente = false;
		    				}
		        		break;
		        		
		        		case 3:
		        		case 4:
		        		case 5:
		    				if(!Character.isDigit(textoPatente.charAt(i))) {
		    					patente = true;
		    					patenteFormato6 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					i = 6;
		    				}
		    				else {
		    					patente = false;
		    				}
		        		break;
		        	}
	        		
	        	}
	        break;     

	        //para patente longitud 7
	        case 7:
	        	for(int j = 0; j < 7; j++) {
	        		
		        	switch(j) {
		        		case 0:
		        		case 1:
		        		case 5:
		        		case 6:
		    				if(Character.isDigit(textoPatente.charAt(j))) {
		    					patente = true;
		    					patenteFormato7 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					valido = false;
		    					j = 7;
		    				}else {
		    					patente = false;
		    				}
		        		break;
		        
		        		case 2:
		        		case 3:
		        		case 4:
		    				if(Character.isLetter(textoPatente.charAt(j))) {
		    					patente = true;
		    					patenteFormato7 = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";	
		    					errorNumero++;
		    					valido = false;
		    					j = 7;
		    				}else {
		    					patente = false;
		    				}
		        		break;
		        	}
	        		
	        	}
	        break;

	        default:
	        	patente = true;
				patenteLargo = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
				errorNumero++;
				valido = false;
			break;
			}		
		}
		else {
			patente = false;
		}
		
		if (altaPoliza1.getKmAnio().equals("Selecionar kilometraje")) {
			km = true;
			kmSelecciono = errorNumero+") No se ha seleccionado un valor del campo km realizados por año.\n";
			valido = false;
		}

		String mensajeError = marcaSelecciono + motorBlanco + motorFormato + motorLargo + chasisBlanco + chasisFormato + chasisLargo + patenteFormato6 + patenteFormato7 + patenteLargo + kmSelecciono;
		
		if(!valido) {
			altaPoliza1.noValido(marca, motor, chasis,patente, km);
			
			JOptionPane.showConfirmDialog(ventana, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		return valido;
	}
	
	class ListenerBuscarCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				CU17Controller a = new CU17Controller(ventana);	
				a.setAltaPolizaController(instancia);
				ventana.revalidate();
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(ventana, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		}
	}

	class ListenerAltaCliente implements ActionListener{
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
	
	class ListenerDeclararHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {				
				altaPoliza1.componentesAlDeclararHijos(false, 0);
				setDeclararHijos();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerQuitarHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				if(poliza.getHijosDeclarado().size() > 0) {
					int hijoSeleccionado = altaPoliza1.getFilaSeleccionada();
					poliza.getHijosDeclarado().remove(hijoSeleccionado);
					hijosDeclarados = poliza.getHijosDeclarado();
					cargarTabla();
					return;
				}
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerConfirmarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				if(!condicionesGenerarPoliza()) {
					return;
				}
				
				if(JOptionPane.showConfirmDialog(ventana, "¿Desea confirmar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
					
					poliza.setCiudad(altaPoliza1.getCiudad());
					poliza.setAnioModelo(altaPoliza1.getAnioModelo());
					poliza.setMotor(altaPoliza1.getMotor());
					poliza.setChasis(altaPoliza1.getChasis());
					poliza.setPatente(altaPoliza1.getPatente());
					poliza.setSumaAsegurada(Float.parseFloat(altaPoliza1.getSumaAsegurada()));
					poliza.setKmRealizadosPorAnio(altaPoliza1.getKmAnio());
					poliza.setNumerosSiniestrosUltimoAnios(gestorEnum.parseEnumSiniestros(altaPoliza1.getNumeroSiniestros()));
					poliza.setGuardaGarage(altaPoliza1.getGarage());
					poliza.setTieneAlarma(altaPoliza1.getAlarma());
					poliza.setTieneRastreoVehicular(altaPoliza1.getRastreo());
					poliza.setTieneTuercasAntirobo(altaPoliza1.getTuercasAntirrobo());
					new CU01View2(ventana, poliza);
				}
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ListenerCancelarAltaPoliza implements ActionListener{
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
	
	class ListenerProvincia implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Provincia provincia = altaPoliza1.getProvincia();
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(provincia).iterator();
			altaPoliza1.addCiudad(iteratorCiudad.next(), true);
			while(iteratorCiudad.hasNext()){
				altaPoliza1.addCiudad(iteratorCiudad.next(), false);
			}
		}
	}
	
	class ListenerMarca implements ActionListener{
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
	
	class ListenerModelo implements ActionListener{
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
	
	class ListenerAnioModelo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(altaPoliza1.habilitadaSeleccionAnioModelo()) {
				altaPoliza1.setSumaAsegurada(altaPoliza1.getAnioModelo().getSumaAsegurada().toString());
			}
			else {
				altaPoliza1.setSumaAsegurada("");
			}
		}
	}

	public void agregarHijoTabla(HijoDeclarado hijo) {
		if(hijo != null) {
			hijo.setPoliza(poliza);
			poliza.getHijosDeclarado().add(hijo);
			hijosDeclarados = poliza.getHijosDeclarado();
			cargarTabla();
			altaPoliza1.componentesAlDeclararHijos(true, hijosDeclarados.size());
		}
	}
	
	//------------------------------------------------------------------------------CU01_DeclararHijo
	
	public void setDeclararHijos() {
		declararHijo = new CU01View3();
		declararHijo.setResizable(false);
		declararHijo.setBounds(0, 0, 400, 250);
		declararHijo.setLocationRelativeTo(null);
		declararHijo.setTitle("Dar de alta póliza: AGREGAR DATOS HIJOS");
		declararHijo.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		try {
			//TODO elegir como fecha inicial el último día aceptado como fecha válida de cumpleaños
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
			declararHijo.setFecha(fechaParseada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		declararHijo.addSexo("Seleccionar");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			declararHijo.addSexo(gestorEnum.parseString(sexos[i]));
		}
		
		declararHijo.addEstadoCivil("Seleccionar");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			declararHijo.addEstadoCivil(gestorEnum.parseStringMasc(estadosCivil[i]));
		}
		
		addListenerVentanaDeclararHijo();
		declararHijo.addListenerSeleccionSexo(new ListenerSexo());
		declararHijo.addListenerBtnAgregarHijo(new ListenerAgregarHijo());
		declararHijo.addListenerBtnCancelar(new ListenerCancelarDeclararHijo());
		declararHijo.setVisible(true);
	}
	
	public Boolean validarEdad(LocalDate fechaNacLocalDate) {
		
		int anioHoy = LocalDate.now().getYear();
		int diaDelAnioHoy = LocalDate.now().getDayOfYear();
		int anioDeclarado = fechaNacLocalDate.getYear();
		int diaDelAnioDeclarado = fechaNacLocalDate.getDayOfYear();
		
		Boolean valido1 = false;
		Boolean valido2 = false;
		
		if(anioHoy > (anioDeclarado+18)) {
			valido1 = true;
		}
		else {
			if(anioHoy < (anioDeclarado+18)) {
				valido1 = false;
			}
			else {
				if(diaDelAnioHoy >= diaDelAnioDeclarado) {
					valido1 = true;
				}
				else {
					valido1 = false;
				}
			}
		}
		
		if(anioHoy < (anioDeclarado+30)) {
			valido2 = true;
		}
		else {
			if(anioHoy > (anioDeclarado+30)) {
				valido2 = false;
			}
			else {
				if(diaDelAnioHoy < diaDelAnioDeclarado) {
					valido2 = true;
				}
				else {
					valido2 = false;
				}
			}
		}
		
		if(valido1&&valido2){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addListenerVentanaDeclararHijo() {
		declararHijo.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	declararHijo.setVisible(false);
				altaPoliza1.componentesAlDeclararHijos(true, hijosDeclarados.size());
		    }
		});
	}
	
	class ListenerSexo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!declararHijo.getSexo().equals("Seleccionar")) {
				Integer index = declararHijo.indexSeleccionEstadoCivil();
				declararHijo.habilitarSeleccionEstadoCivil(false);
				if(gestorEnum.parseEnumSexo(declararHijo.getSexo()).equals(EnumSexo.FEMENINO)) {
					declararHijo.addEstadoCivil("Seleccionar");
					EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
					for(int i=0; i<estadosCivil.length; i++){
						declararHijo.addEstadoCivil(gestorEnum.parseStringFem(estadosCivil[i]));
					}
				}
				
				if(gestorEnum.parseEnumSexo(declararHijo.getSexo()).equals(EnumSexo.MASCULINO)) {
					declararHijo.addEstadoCivil("Seleccionar");
					EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
					for(int i=0; i<estadosCivil.length; i++){
						declararHijo.addEstadoCivil(gestorEnum.parseStringMasc(estadosCivil[i]));
					}
				}
				declararHijo.setEstadoCivil(index);
				declararHijo.habilitarSeleccionEstadoCivil(true);
			}
		}
	}
	
	class ListenerAgregarHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			String fechaNac = "";
			String sexo = "";
			String estadoCivil = "";
			Boolean huboError = false;
			Boolean fechaError = false, sexoError = false, estadoCivilError = false;
			SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
			
			Date fechaNacDate = null;
	
			try {
				fechaNacDate = formato.parse(formato.format(declararHijo.getFechaNac()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if(!validarEdad(fechaNacLocalDate)) {
				fechaError = true;
				fechaNac = "La fecha de nacimiento que se introdujo dicta una edad que no se encuentra en el rango adecuado (18 a 30 años).\n";
				huboError = true;
			}
			
			if (declararHijo.getSexo().equals("Seleccionar")) {
				sexoError = true;
				sexo = "No ha seleccionado un valor del campo sexo.\n";
				huboError = true;
			}
			
			if (declararHijo.getEstadoCivil().equals("Seleccionar")) {
				estadoCivilError = true;
				estadoCivil = "No ha seleccionado un valor del campo estado civil.";
				huboError = true;
			}
			
			if(huboError) {
				declararHijo.noValido(fechaError, sexoError, estadoCivilError);
				JOptionPane.showConfirmDialog(declararHijo, fechaNac + sexo + estadoCivil , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else {
				HijoDeclarado hijo = new HijoDeclarado();
				
				hijo.setFechaNacimiento(fechaNacLocalDate);
				hijo.setSexo(gestorEnum.parseEnumSexo(declararHijo.getSexo()));
				hijo.setEstadoCivil(gestorEnum.parseEnumEstadoCivil(declararHijo.getEstadoCivil()));
				agregarHijoTabla(hijo);
				declararHijo.setVisible(false);
				
			}
		}
	}

	class ListenerCancelarDeclararHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			declararHijo.setVisible(false);
			altaPoliza1.componentesAlDeclararHijos(true, hijosDeclarados.size());
		}
	}
}
