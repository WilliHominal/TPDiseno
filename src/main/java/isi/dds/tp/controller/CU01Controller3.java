package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.view.CU01View1;
import isi.dds.tp.view.CU01View3;

public class CU01Controller3 {
	private CU01Controller1 controller1;
	private CU01View1 altaPoliza1;
	private CU01View3 declararHijo;
	
	private GestorEnum gestorEnum = GestorEnum.get();
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	
	public CU01Controller3() {
		setView3_DeclararHijos();
	}
	
	private void setView3_DeclararHijos() {
		declararHijo = new CU01View3();
		declararHijo.setResizable(false);
		declararHijo.setBounds(0, 0, 400, 250);
		declararHijo.setLocationRelativeTo(null);
		declararHijo.setTitle("Dar de alta póliza: AGREGAR DATOS HIJOS");
		declararHijo.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setFechaInicioDefault();
		addSelecccionSexo();
		addSeleccionEstadoCivil();
		addListenerVentanaDeclararHijo();
		declararHijo.addListenerSeleccionSexo(new ListenerView3Sexo());
		declararHijo.addListenerBtnAgregarHijo(new ListenerView3AgregarHijo());
		declararHijo.addListenerBtnCancelar(new ListenerView3Cancelar());
		declararHijo.setVisible(true);
	}
	
	private void setFechaInicioDefault() {
		try {
			//TODO elegir como fecha inicial el último día aceptado como fecha válida de cumpleaños
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
			declararHijo.setFecha(fechaParseada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void addSelecccionSexo() {
		declararHijo.addSexo("Seleccionar");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			declararHijo.addSexo(gestorEnum.parseString(sexos[i]));
		}
	}
	
	private void addSeleccionEstadoCivil() {
		declararHijo.addEstadoCivil("Seleccionar");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			declararHijo.addEstadoCivil(gestorEnum.parseStringMasc(estadosCivil[i]));
		}
	}
	
	private void addListenerVentanaDeclararHijo() {
		declararHijo.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	declararHijo.setVisible(false);
				altaPoliza1.componentesAlDeclararHijos(true, controller1.getPoliza().getHijosDeclarado().size());
		    }
		});
	}
	
	private Boolean validarEdad(LocalDate fechaNacLocalDate) {
		//TODO validar en gestor poliza
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
	
	//-------- LISTENER VIEW 3
	private class ListenerView3Sexo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!declararHijo.getSexo().equals("Seleccionar")) {
				Integer index = declararHijo.indexSeleccionEstadoCivil();
				
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
			}
		}
	}
	
	private class ListenerView3AgregarHijo implements ActionListener{
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
				controller1.agregarHijoTabla(hijo);
				declararHijo.setVisible(false);
				
			}
		}
	}

	public void setController1(CU01Controller1 controller1) {
		this.controller1 = controller1;
	}

	private class ListenerView3Cancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			declararHijo.setVisible(false);
			altaPoliza1.componentesAlDeclararHijos(true, controller1.getPoliza().getHijosDeclarado().size());
		}
	}
}
