package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.gestor.GestorPoliza;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.view.CU01View3;

public class CU01Controller3 {	
	private CU01Controller1 controller1;
	private CU01View3 view3;
	
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	
	public CU01Controller3() {
		view3 = new CU01View3();
		view3.setResizable(false);
		view3.setBounds(0, 0, 400, 250);
		view3.setLocationRelativeTo(null);
		view3.setTitle("Dar de alta póliza: AGREGAR DATOS HIJOS");
		view3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		System.out.println("Linea 34 controller 3");
		setFechaInicioDefault();
		System.out.println("Linea 36 controller 3");
		addSelecccionSexo();
		System.out.println("Linea 38 controller 3");
		addSeleccionEstadoCivil();
		System.out.println("Linea 40 controller 3");
		addListenerVentanaDeclararHijo();
		System.out.println("Linea 42 controller 3");
		view3.addListenerPane(new ListenerPanel());
		view3.addListenerSeleccionSexo(new ListenerSeleccionSexo());
		view3.addListenerBtnAgregarHijo(new ListenerBtnAgregarHijo());
		view3.addListenerBtnCancelar(new ListenerBtnCancelar());
		view3.setVisible(true);
	}
	
	private void setFechaInicioDefault() {
		try {
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaParseada); 
		      calendar.add(Calendar.YEAR, -30);
		      calendar.add(Calendar.DAY_OF_YEAR, 1);  
		      calendar.getTime(); 
		      
		      Calendar calendar2 = Calendar.getInstance();
		      calendar2.setTime(fechaParseada); 
		      calendar2.add(Calendar.YEAR, -18);
		      calendar2.add(Calendar.DAY_OF_YEAR, -1);  
		      calendar2.getTime(); 
		      
		      view3.setFecha(calendar.getTime(), calendar2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setCU01Controller1(CU01Controller1 controller1) {
		this.controller1 = controller1;
	}
	
	private void addSelecccionSexo() {
		view3.addSexo("Seleccionar");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			view3.addSexo(sexos[i].toString());
		}
	}
	
	private void addSeleccionEstadoCivil() {
		view3.addEstadoCivil("Seleccionar");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			view3.addEstadoCivil(estadosCivil[i].toString(EnumSexo.MASCULINO));
		}
	}
	
	private void addListenerVentanaDeclararHijo() {
		view3.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	view3.setVisible(false);
				controller1.componentesAlDeclararHijos(true);
		    }
		    
		});
	}
	
	//-------- LISTENER VIEW 3
	private class ListenerPanel implements KeyListener{
		public void keyTyped(KeyEvent e){ }
		public void keyReleased(KeyEvent e){ }
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            	view3.setVisible(false);
    			controller1.componentesAlDeclararHijos(true);
            }
        }
    }

	private class ListenerSeleccionSexo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!view3.getSexo().equals("Seleccionar")) {
				Integer index = view3.indexSeleccionEstadoCivil();
				view3.addEstadoCivil("Seleccionar");
				EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
				for(int i=0; i<estadosCivil.length; i++){
					view3.addEstadoCivil(estadosCivil[i].toString(EnumSexo.getEnum(view3.getSexo())));
				}	
				view3.setEstadoCivil(index);
			}
		}
	}
	
	private class ListenerBtnAgregarHijo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String fechaNac = "";
			String sexo = "";
			String estadoCivil = "";
			Boolean fechaError = false, sexoError = false, estadoCivilError = false;
			
			SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
			Date fechaNacDate = null;

			try {
				fechaNacDate = formato.parse(formato.format(view3.getFechaNac()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if(!gestorPoliza.validarFechaNacimiento(fechaNacLocalDate)) {
				fechaError = true;
				fechaNac = "La fecha de nacimiento que se introdujo dicta una edad que no se encuentra en el rango adecuado (18 a 30 años).\n";
			}
			
			if (view3.getSexo().equals("Seleccionar")) {
				sexoError = true;
				sexo = "No ha seleccionado un valor del campo sexo.\n";
			}
			
			if (view3.getEstadoCivil().equals("Seleccionar")) {
				estadoCivilError = true;
				estadoCivil = "No ha seleccionado un valor del campo estado civil.";
			}
			
			if(fechaError || sexoError || estadoCivilError) {
				view3.noValido(sexoError, estadoCivilError);
				JOptionPane.showConfirmDialog(view3, fechaNac + sexo + estadoCivil , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else{			
				HijoDeclarado hijo = new HijoDeclarado();
				hijo.setFechaNacimiento(fechaNacLocalDate);
				hijo.setSexo(EnumSexo.getEnum(view3.getSexo()));
				hijo.setEstadoCivil(EnumEstadoCivil.getEnum(view3.getEstadoCivil()));
				controller1.agregarHijoTabla(hijo);
				view3.setVisible(false);
			}
		}
	}

	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view3.setVisible(false);
			controller1.componentesAlDeclararHijos(true);
		}
	}
}
