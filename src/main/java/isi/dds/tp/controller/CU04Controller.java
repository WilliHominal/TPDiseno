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
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.view.CU04View;

public class CU04Controller {

	private JFrame ventana;
	private JPanel panelAnterior;
	private String tituloAnterior = "";
	private GestorEnum gestorEnum = GestorEnum.get();
	private GestorDomicilio gestorDomicilio = GestorDomicilio.get();
	private CU04View altaCliente;
	
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
	
	public void setDarAltaCliente() {
		altaCliente = new CU04View();
		GestorTema.get().setTema(ventana, "Dar de alta cliente");
		ventana.setContentPane(altaCliente);
		
		altaCliente.addTipoDocumento("Seleccionar tipo doc.");
		EnumTipoDocumento[] tipoDocumentos = EnumTipoDocumento.values();
		for(int i=0; i<tipoDocumentos.length; i++){
			altaCliente.addTipoDocumento(gestorEnum.parseString(tipoDocumentos[i]));
		}
		
		altaCliente.addSexo("Seleccionar sexo");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			altaCliente.addSexo(gestorEnum.parseString(sexos[i]));
		}
		
		List<Pais> paises = gestorDomicilio.getPaises();
		Iterator<Pais> iteradorPais = paises.iterator();
		while(iteradorPais.hasNext()){
			altaCliente.addPais(iteradorPais.next());
		}
		
		List<Provincia> provincias = altaCliente.getPais().getProvincias();
		Iterator<Provincia> iteradorProvincias = provincias.iterator();
		while(iteradorProvincias.hasNext()){
			altaCliente.addProvincia(iteradorProvincias.next());
		}
		
		List<Ciudad> ciudades = gestorDomicilio.sortCiudades(altaCliente.getProvincia());
		Iterator<Ciudad> iteradorCiudades = ciudades.iterator();
		while(iteradorCiudades.hasNext()){
			altaCliente.addCiudad(iteradorCiudades.next());
		}
		
		altaCliente.addCondicionIva("Seleccionar cond. de IVA");
		EnumCondicionIVA[] condicionesIva = EnumCondicionIVA.values();
		for(int i=0; i<condicionesIva.length; i++){
			altaCliente.addCondicionIva(gestorEnum.parseString(condicionesIva[i]));
		}
		
		altaCliente.addEstadoCivil("Seleccionar estado civil");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			altaCliente.addEstadoCivil(gestorEnum.parseStringMasc(estadosCivil[i]));
		}
		
		altaCliente.addListenerBtnConfirmar(new ListenerConfirmar());
		altaCliente.addListenerBtnCancelar(new ListenerCancelar());
		altaCliente.addListenerSeleccionPais(new ListenerPais());
		altaCliente.addListenerSeleccionProvincia(new ListenerProvincia());
		altaCliente.addListenerSeleccionSexo(new ListenerSexo());
	}
	
	class ListenerConfirmar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Solo se implementó el mockup del caso de uso 04:  Dar de Alta Cliente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);       	
		}
	}
	
	class ListenerCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {			
				altaCliente.setVisible(false);
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class ListenerPais implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			altaCliente.habilitarProvincia(false);
			Iterator<Provincia> iteratorProvincias = altaCliente.getPais().getProvincias().iterator();
			while(iteratorProvincias.hasNext()){
				altaCliente.addProvincia(iteratorProvincias.next());
			}				
			
			altaCliente.habilitarCiudad(false);
								
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(altaCliente.getProvincia()).iterator();
			while(iteratorCiudad.hasNext()){
				altaCliente.addCiudad(iteratorCiudad.next());
			}
			
			altaCliente.habilitarProvincia(true);
			altaCliente.habilitarCiudad(true);
		}
	}
	
	class ListenerProvincia implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			altaCliente.habilitarCiudad(false);
								
			Iterator<Ciudad> iteratorCiudad = gestorDomicilio.sortCiudades(altaCliente.getProvincia()).iterator();
			while(iteratorCiudad.hasNext()){
				altaCliente.addCiudad(iteratorCiudad.next());
			}
			
			altaCliente.habilitarCiudad(true);
		}
	}
	
	class ListenerSexo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!altaCliente.getSexo().equals("Seleccionar sexo")) {
				Integer index = altaCliente.indexSeleccionEstadoCivil();
				if(gestorEnum.parseEnumSexo(altaCliente.getSexo()).equals(EnumSexo.FEMENINO)) {
					altaCliente.addEstadoCivil("Seleccionar estado civil");
					EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
					for(int i=0; i<estadosCivil.length; i++){
						altaCliente.addEstadoCivil(gestorEnum.parseStringFem(estadosCivil[i]));
					}
				}
				
				if(gestorEnum.parseEnumSexo(altaCliente.getSexo()).equals(EnumSexo.MASCULINO)) {
					altaCliente.addEstadoCivil("Seleccionar estado civil");
					EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
					for(int i=0; i<estadosCivil.length; i++){
						altaCliente.addEstadoCivil(gestorEnum.parseStringMasc(estadosCivil[i]));
					}
				}
				altaCliente.setEstadoCivil(index);
			}
		}
	}	
}
