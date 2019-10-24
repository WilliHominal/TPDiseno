package isi.dds.tp.app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
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

@SuppressWarnings("serial")
public class CU04_AltaCliente extends JPanel{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU04_AltaCliente();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CU04_AltaCliente() {		
		JFrame frame = new JFrame();
		frame.pack();
		frame.setBounds(0,0,1024,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		new CU04_AltaCliente(frame);
		frame.setVisible(true);		
	}
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private GestorTema tema = GestorTema.get();
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lapellido = new JLabel("Apellido*:");
	private JLabel lnombre = new JLabel("Nombres*:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento*:");
	private JLabel lnumeroDocumento = new JLabel("Número documento*:");
	private JLabel lnumeroCUIL = new JLabel("Número CUIL*:");
	private JLabel lsexo = new JLabel("Sexo*:");
	private JLabel lcalle = new JLabel("Calle*:");
	private JLabel lnumeroDomicilio = new JLabel("Número domicilio*:");
	private JLabel lpiso = new JLabel("Piso:");
	private JLabel ldepartamento = new JLabel("Departamento:");
	private JLabel lpais = new JLabel("Pais*:");
	private JLabel lprovincia = new JLabel("Provincia*:");
	private JLabel lciudad = new JLabel("Ciudad*:");
	private JLabel lcodigoPostal = new JLabel("Código postal*:");
	private JLabel lcondicionIva = new JLabel("Condición de IVA*:");
	private JLabel lcorreoElectronico = new JLabel("Correo electrónico*:");
	private JLabel lestadoCivil = new JLabel("Estado civil*:");
	private JLabel lprofesion = new JLabel("Profesión*:");
	private JLabel lanioRegistro = new JLabel("Año registro*:");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatios)");
		
	private JTextField campoNumeroCliente = new JTextField(15);
	private JTextField campoApellido = new JTextField(15);
	private JTextField campoNombre = new JTextField(15);
	private JTextField campoNumeroDocumento = new JTextField(15);
	private JTextField campoNumeroCUIL = new JTextField(15);
	private JTextField campoCalle = new JTextField(15);
	private JTextField campoNumeroDomicilio = new JTextField(5);
	private JTextField campoPiso = new JTextField(2);
	private JTextField campoDepartamento = new JTextField(2);
	private JTextField campoCodigoPostal = new JTextField(5);
	private JTextField campoCorreoElectronico = new JTextField(15);
	private JTextField campoProfesion = new JTextField(15);
	private JTextField campoAnioRegistro = new JTextField(5);
	
	
	private JButton btnConfirmar = new JButton("CONFIRMAR DATOS");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JComboBox<String> seleccionTipoDocumento = new JComboBox<String>();
	private JComboBox<String> seleccionSexo = new JComboBox<String>();
	private JComboBox<Pais> seleccionPais = new JComboBox<Pais>();
	private JComboBox<Provincia> seleccionProvincia = new JComboBox<Provincia>();
	private JComboBox<Ciudad> seleccionCiudad = new JComboBox<Ciudad>();
	private JComboBox<String> seleccionCondicionIva = new JComboBox<String>();
	private JComboBox<String> seleccionEstadoCivil = new JComboBox<String>();

	public CU04_AltaCliente(JFrame ventana) {
		this.ventana = ventana;
		try {		
			panelAnterior = (JPanel) ventana.getContentPane();
		}catch(Exception ex) {
		    panelAnterior = null;
		}
		
		ventana.setContentPane(this);
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		comportamientos();
		ventana.setTitle("Alta cliente");		
	}

	private void inicializarComponentes() {
		btnConfirmar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		
		seleccionTipoDocumento.setPreferredSize(new Dimension(180, 25));
		seleccionSexo.setPreferredSize(new Dimension(120, 25));
		seleccionPais.setPreferredSize(new Dimension(130, 25));
		seleccionProvincia.setPreferredSize(new Dimension(130, 25));
		seleccionCiudad.setPreferredSize(new Dimension(130, 25));
		seleccionCondicionIva.setPreferredSize(new Dimension(180, 25));
		seleccionEstadoCivil.setPreferredSize(new Dimension(150, 25));
		
		seleccionTipoDocumento.addItem("Selecionar tipo documento");
		EnumTipoDocumento[] tipoDocumentos = EnumTipoDocumento.values();
		for(int i=0; i<tipoDocumentos.length; i++){
			seleccionTipoDocumento.addItem(GestorEnum.get().getStringTipoDocumento(tipoDocumentos[i]));
		}
		
		seleccionSexo.addItem("Selecionar sexo");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			seleccionSexo.addItem(GestorEnum.get().getStringSexo(sexos[i]));
		}
		
		List<Pais> paises = GestorDomicilio.get().getPaises();
		Iterator<Pais> iteradorPais = paises.iterator();
		while(iteradorPais.hasNext()){
			seleccionPais.addItem(iteradorPais.next());
		}
		
		List<Provincia> provincias = seleccionPais.getItemAt(seleccionPais.getSelectedIndex()).getProvincias();
		Iterator<Provincia> iteradorProvincias = provincias.iterator();
		while(iteradorProvincias.hasNext()){
			seleccionProvincia.addItem(iteradorProvincias.next());
		}
		
		List<Ciudad> ciudades = GestorDomicilio.get().sortCiudades(seleccionProvincia.getItemAt(seleccionPais.getSelectedIndex()).getCiudades());
		Iterator<Ciudad> iteradorCiudades = ciudades.iterator();
		while(iteradorCiudades.hasNext()){
			seleccionCiudad.addItem(iteradorCiudades.next());
		}
		
		seleccionCondicionIva.addItem("Selecionar condición de IVA");
		EnumCondicionIVA[] condicionesIva = EnumCondicionIVA.values();
		for(int i=0; i<condicionesIva.length; i++){
			seleccionCondicionIva.addItem(GestorEnum.get().getStringCondicionIva(condicionesIva[i]));
		}
		
		seleccionEstadoCivil.addItem("Selecionar estado civil");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			seleccionEstadoCivil.addItem(GestorEnum.get().getStringEstadoCivil(estadosCivil[i]));
		}
		
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//FILA 1
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnumeroCliente, constraints);
		constraints.insets.set(5, 100, 5, 5);
		add(campoNumeroCliente, constraints);	
		
		constraints.gridy = 1;
		constraints.insets.set(10, 5, 10, 5);
		add(lapellido, constraints);
		constraints.insets.set(5, 65, 5, 5);
		add(campoApellido, constraints);
		constraints.insets.set(10, 240, 10, 5);
		add(lnombre, constraints);
		constraints.insets.set(5, 303, 5, 5);
		add(campoNombre, constraints);
		
		constraints.gridy = 2;
		constraints.insets.set(10, 5, 10, 5);
		add(ltipoDocumento, constraints);
		constraints.insets.set(5, 108, 5, 5);
		add(seleccionTipoDocumento, constraints);
		constraints.insets.set(10, 310, 10, 5);
		add(lnumeroDocumento, constraints);
		constraints.insets.set(5, 432, 5, 5);
		add(campoNumeroDocumento, constraints);
		
		constraints.gridy = 3;
		constraints.insets.set(10, 5, 10, 5);
		add(lnumeroCUIL, constraints);
		constraints.insets.set(5, 94, 5, 5);
		add(campoNumeroCUIL, constraints);
		
		constraints.gridy = 4;
		constraints.insets.set(10, 5, 10, 5);
		add(lsexo, constraints);
		constraints.insets.set(5, 45, 5, 5);
		add(seleccionSexo, constraints);
		
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		constraints.insets.set(10, 5, 10, 5);
		add(lcalle, constraints);
		constraints.insets.set(5, 48, 5, 5);
		add(campoCalle, constraints);	
		constraints.insets.set(10, 220, 10, 5);
		add(lnumeroDomicilio, constraints);
		constraints.insets.set(5, 330, 5, 5);
		add(campoNumeroDomicilio, constraints);	
		constraints.insets.set(10, 405, 10, 5);
		add(lpiso, constraints);
		constraints.insets.set(5, 438, 5, 5);
		add(campoPiso, constraints);	
		constraints.insets.set(10, 483, 10, 5);
		add(ldepartamento, constraints);
		constraints.insets.set(5, 570 , 5, 5);
		add(campoDepartamento, constraints);	
		
		constraints.gridy = 6;
		constraints.insets.set(10, 5, 10, 5);
		add(lpais, constraints);
		constraints.insets.set(5, 45, 5, 5);
		add(seleccionPais, constraints);	
		constraints.insets.set(10, 195, 10, 5);
		add(lprovincia, constraints);
		constraints.insets.set(5, 262, 5, 5);
		add(seleccionProvincia, constraints);	
		constraints.insets.set(10, 415, 10, 5);
		add(lciudad, constraints);
		constraints.insets.set(5, 470, 5, 5);
		add(seleccionCiudad, constraints);
		constraints.insets.set(10, 620, 10, 5);
		add(lcodigoPostal, constraints);
		constraints.insets.set(5, 710 , 5, 5);
		add(campoCodigoPostal, constraints);	
		
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.insets.set(10, 5, 10, 5);
		add(lcondicionIva, constraints);
		constraints.insets.set(5, 115, 5, 5);
		add(seleccionCondicionIva, constraints);	
		
		constraints.gridy = 8;
		constraints.insets.set(10, 5, 10, 5);
		add(lcorreoElectronico, constraints);
		constraints.insets.set(5, 118, 5, 5);
		add(campoCorreoElectronico, constraints);	

		constraints.gridy = 9;
		constraints.insets.set(10, 5, 10, 5);
		add(lestadoCivil, constraints);
		constraints.insets.set(5, 82, 5, 5);
		add(seleccionEstadoCivil, constraints);
		
		constraints.gridy = 10;
		constraints.insets.set(10, 5, 10, 5);
		add(lprofesion, constraints);
		constraints.insets.set(5, 72, 5, 5);
		add(campoProfesion, constraints);
		constraints.insets.set(10, 245, 10, 5);
		add(lanioRegistro, constraints);
		constraints.insets.set(5, 324, 5, 5);
		add(campoAnioRegistro, constraints);
		
		constraints.gridy = 11;
		constraints.insets.set(10, 5, 10, 5);
		add(ldatosObligatorios, constraints);
		
		constraints.gridy = 12;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(30, 5, 10, 170);
		add(btnConfirmar, constraints);
		constraints.insets.set(30, 5, 10, 0);
		add(btnCancelar, constraints);

	}
		
	private void inicializarTema() {	
		tema.panel(this);
		
		tema.label(lnumeroCliente);
		tema.label(ltipoDocumento);
		tema.label(lnumeroDocumento);
		tema.label(lapellido);
		tema.label(lnombre);
		tema.label(lnumeroCUIL);
		tema.label(lsexo);
		tema.label(lcalle);
		tema.label(lnumeroDomicilio);
		tema.label(lpiso);
		tema.label(ldepartamento);
		tema.label(lpais);
		tema.label(lprovincia);
		tema.label(lciudad);
		tema.label(lcodigoPostal);
		tema.label(lcondicionIva);
		tema.label(lcorreoElectronico);
		tema.label(lestadoCivil);
		tema.label(lprofesion);
		tema.label(lanioRegistro);
		tema.labelChica(ldatosObligatorios);
		
		tema.campo(campoNumeroCliente, false);
		tema.campo(campoApellido, true);
		tema.campo(campoNombre, true);
		tema.campo(campoNumeroDocumento, true);
		tema.campo(campoNumeroCUIL, true);
		tema.campo(campoCalle, true);
		tema.campo(campoNumeroDomicilio, true);
		tema.campo(campoPiso, true);
		tema.campo(campoDepartamento, true);
		tema.campo(campoCodigoPostal, true);
		tema.campo(campoCorreoElectronico, true);
		tema.campo(campoProfesion, true);
		tema.campo(campoAnioRegistro, true);
		
		tema.boton(btnConfirmar);
		tema.boton(btnCancelar);
		
		tema.seleccion(seleccionTipoDocumento, true);
		tema.seleccion(seleccionSexo, true);
		tema.seleccion(seleccionPais, true);
		tema.seleccion(seleccionProvincia, true);
		tema.seleccion(seleccionCiudad, true);
		tema.seleccion(seleccionCondicionIva, true);
		tema.seleccion(seleccionEstadoCivil, true);

	}

	public void comportamientos() {
		btnConfirmar.addActionListener(a -> {
           	JOptionPane.showMessageDialog(null, "Solo se implementó el mockup del caso de uso 04:  Dar de Alta Cliente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);       	
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.setContentPane(panelAnterior);				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
	    
		seleccionPais.addActionListener (a -> {
			seleccionProvincia.setEnabled(false);	
			seleccionProvincia.removeAllItems();
			Iterator<Provincia> iteratorProvincias = seleccionPais.getItemAt(seleccionPais.getSelectedIndex()).getProvincias().iterator();
			while(iteratorProvincias.hasNext()){
				seleccionProvincia.addItem(iteratorProvincias.next());
			}				
			
			seleccionCiudad.setEnabled(false);	
			seleccionCiudad.removeAllItems();
								
			Iterator<Ciudad> iteratorCiudad = GestorDomicilio.get().sortCiudades(seleccionProvincia.getItemAt(seleccionPais.getSelectedIndex()).getCiudades()).iterator();
			while(iteratorCiudad.hasNext()){
				seleccionCiudad.addItem(iteratorCiudad.next());
			}
			
			seleccionProvincia.setEnabled(true);
			seleccionCiudad.setEnabled(true);
		});
		
		seleccionProvincia.addActionListener (a -> {
			seleccionCiudad.setEnabled(false);	
			seleccionCiudad.removeAllItems();
								
			Iterator<Ciudad> iteratorCiudad = seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex()).getCiudades().iterator();
			while(iteratorCiudad.hasNext()){
				seleccionCiudad.addItem(iteratorCiudad.next());
			}
			
			seleccionCiudad.setEnabled(true);
		});			

	}

}
