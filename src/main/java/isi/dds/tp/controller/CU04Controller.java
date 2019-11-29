package isi.dds.tp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import isi.dds.tp.enums.EnumCondicionIVA;
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.view.CU04View;

public class CU04Controller {
	private CU04View view;
	
	private GestorDomicilio gestorDomicilio = GestorDomicilio.get();
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	
	public CU04Controller(JFrame ventana){
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {		
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
		    panelAnterior = null;
		}		
		
		setDarAltaCliente();
	}
	
	private void setDarAltaCliente() {
		view = new CU04View();
		GestorTema.get().setTema(ventana, "Dar de alta cliente");
		ventana.setContentPane(view);
		addSeleccionTipoDocumento();
		addSeleccionSexo();
		addSeleccionPais();
		addSeleccionProvincia();
		addSeleccionCiudad();
		addSeleccionCondicionIva();
		addSeleccionEstadoCivil();
		view.addListenerBtnConfirmar(new ListenerConfirmar());
		view.addListenerBtnCancelar(new ListenerCancelar());
		view.addListenerSeleccionPais(new ListenerPais());
		view.addListenerSeleccionProvincia(new ListenerProvincia());
		view.addListenerSeleccionSexo(new ListenerSexo());
	}

	//------ METODOS
	private void addSeleccionTipoDocumento() {
		view.addTipoDocumento("Seleccionar tipo doc.");
		EnumTipoDocumento[] tipoDocumentos = EnumTipoDocumento.values();
		for(int i=0; i<tipoDocumentos.length; i++){
			view.addTipoDocumento(tipoDocumentos[i].toString());
		}
	}
	
	private void addSeleccionSexo() {
		view.addSexo("Seleccionar sexo");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			view.addSexo(sexos[i].toString());
		}
	}
	
	private void addSeleccionPais() {
		List<Pais> paises = gestorDomicilio.getPaises();
		Iterator<Pais> iteradorPais = paises.iterator();
		while(iteradorPais.hasNext()){
			view.addPais(iteradorPais.next());
		}
	}
	
	private void addSeleccionProvincia() {
		List<Provincia> provincias = view.getPais().getProvincias();
		Iterator<Provincia> iteradorProvincias = provincias.iterator();
		while(iteradorProvincias.hasNext()){
			view.addProvincia(iteradorProvincias.next());
		}
	}
	
	private void addSeleccionCiudad() {
		List<Ciudad> ciudades = gestorDomicilio.sortCiudades(view.getProvincia().getIdProvincia());
		Iterator<Ciudad> iteradorCiudades = ciudades.iterator();
		while(iteradorCiudades.hasNext()){
			view.addCiudad(iteradorCiudades.next());
		}
	}
	
	private void addSeleccionCondicionIva() {
		view.addCondicionIva("Seleccionar cond. de IVA");
		EnumCondicionIVA[] condicionesIva = EnumCondicionIVA.values();
		for(int i=0; i<condicionesIva.length; i++){
			view.addCondicionIva(condicionesIva[i].toString());
		}
	}
	
	private void addSeleccionEstadoCivil() {
		view.addEstadoCivil("Seleccionar estado civil");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			view.addEstadoCivil(estadosCivil[i].toString());
		}
	}
	
	//------- LISTENERS USADOS
	class ListenerConfirmar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Solo se implementó el mockup del caso de uso 04:  Dar de Alta Cliente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);       	
		}
	}
	
	class ListenerCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				view.setVisible(false);
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class ListenerPais implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.habilitarProvincia(false);
			Iterator<Provincia> iteratorProvincias = view.getPais().getProvincias().iterator();
			while(iteratorProvincias.hasNext()){
				view.addProvincia(iteratorProvincias.next());
			}				
			
			view.habilitarCiudad(false);
								
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(view.getProvincia().getIdProvincia()).iterator();
			while(iteratorCiudad.hasNext()){
				view.addCiudad(iteratorCiudad.next());
			}
			
			view.habilitarProvincia(true);
			view.habilitarCiudad(true);
		}
	}
	
	class ListenerProvincia implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.habilitarCiudad(false);
								
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(view.getProvincia().getIdProvincia()).iterator();
			while(iteratorCiudad.hasNext()){
				view.addCiudad(iteratorCiudad.next());
			}
			
			view.habilitarCiudad(true);
		}
	}
	
	class ListenerSexo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!view.getSexo().equals("Seleccionar sexo")) {
				Integer index = view.indexSeleccionEstadoCivil();
				
				EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
				for(int i=0; i<estadosCivil.length; i++){
					view.addEstadoCivil(estadosCivil[i].toString(EnumSexo.getEnum(view.getSexo())));
				}	
								
				view.setEstadoCivil(index);
			}
		}
	}	
}
