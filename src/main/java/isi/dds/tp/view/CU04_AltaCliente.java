package isi.dds.tp.view;

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

import com.toedter.calendar.JDateChooser;

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
					JFrame frame = new JFrame();
					new CU04_AltaCliente(frame);
					GestorTema.get().setTema(frame, "Dar de alta cliente");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private GestorTema tema = GestorTema.get();
	
	private String tituloAnterior = "";
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lapellido = new JLabel("Apellido*:");
	private JLabel lnombre = new JLabel("Nombres*:");
	private JLabel lfechaNacimiento = new JLabel("Fecha nacimiento*:");
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
	private JLabel ldatosObligatorios = new JLabel("Datos obligatios (*)");
		
	private JTextField campoNumeroCliente = new JTextField(16);
	private JTextField campoApellido = new JTextField(16);
	private JTextField campoNombre = new JTextField(16);
	private JTextField campoNumeroDocumento = new JTextField(16);
	private JTextField campoNumeroCUIL = new JTextField(16);
	private JTextField campoCalle = new JTextField(16);
	private JTextField campoNumeroDomicilio = new JTextField(5);
	private JTextField campoPiso = new JTextField(2);
	private JTextField campoDepartamento = new JTextField(2);
	private JTextField campoCodigoPostal = new JTextField(5);
	private JTextField campoCorreoElectronico = new JTextField(16);
	private JTextField campoProfesion = new JTextField(16);
	private JTextField campoAnioRegistro = new JTextField(5);
	
	private JDateChooser dcFechaNacimiento = new JDateChooser();
	
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
		this.tituloAnterior = ventana.getTitle();
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
	}

	private void inicializarComponentes() {
		btnConfirmar.setPreferredSize(new Dimension(150, 25));
		btnCancelar.setPreferredSize(new Dimension(150, 25));
		
		dcFechaNacimiento.setPreferredSize(new Dimension(164, 25));
		
		seleccionTipoDocumento.setPreferredSize(new Dimension(164, 25));
		seleccionSexo.setPreferredSize(new Dimension(164, 25));
		seleccionPais.setPreferredSize(new Dimension(164, 25));
		seleccionProvincia.setPreferredSize(new Dimension(164, 25));
		seleccionCiudad.setPreferredSize(new Dimension(164, 25));
		seleccionCondicionIva.setPreferredSize(new Dimension(164, 25));
		seleccionEstadoCivil.setPreferredSize(new Dimension(164, 25));
		
		seleccionTipoDocumento.addItem("Selecionar tipo doc.");
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
		
		List<Ciudad> ciudades = GestorDomicilio.get().sortCiudades(seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex()));
		Iterator<Ciudad> iteradorCiudades = ciudades.iterator();
		while(iteradorCiudades.hasNext()){
			seleccionCiudad.addItem(iteradorCiudades.next());
		}
		
		seleccionCondicionIva.addItem("Selecionar cond. de IVA");
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
		constraints.insets.set(10, 25, 13, 5);
		add(lnumeroCliente, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoNumeroCliente, constraints);	
		constraints.insets.set(10, 310, 13, 5);
		add(lnumeroCUIL, constraints);
		constraints.insets.set(5, 375, 5, 5);
		add(campoNumeroCUIL, constraints); 
		
		constraints.gridy = 1;
		constraints.insets.set(10, 60, 13, 5);
		add(lapellido, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoApellido, constraints);
		constraints.insets.set(10, 310, 13, 5);
		add(lnombre, constraints);
		constraints.insets.set(5, 375, 5, 5);
		add(campoNombre, constraints); 
		

		constraints.gridy = 2;
		constraints.insets.set(10, 8, 13, 5);
		add(lfechaNacimiento, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(dcFechaNacimiento, constraints);
		
		
		constraints.gridy = 3;
		constraints.insets.set(10, 17, 13, 5);
		add(ltipoDocumento, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(seleccionTipoDocumento, constraints);
		constraints.insets.set(10, 310, 13, 5);
		add(lnumeroDocumento, constraints);
		constraints.insets.set(5, 433, 5, 5);
		add(campoNumeroDocumento, constraints);
		
		constraints.gridy = 4;
		constraints.insets.set(10, 30, 13, 5);
		add(lnumeroCUIL, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoNumeroCUIL, constraints);
		
		constraints.gridy = 5;
		constraints.insets.set(10, 77, 13, 5);
		add(lsexo, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(seleccionSexo, constraints);
		
		constraints.gridy = 6;
		constraints.gridwidth = 4;
		constraints.insets.set(10, 76, 13, 5);
		add(lcalle, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoCalle, constraints);	
		constraints.insets.set(10, 310, 13, 5);
		add(lnumeroDomicilio, constraints);
		constraints.insets.set(5, 422, 5, 5);
		add(campoNumeroDomicilio, constraints);	
		constraints.insets.set(10, 500, 13, 5);
		add(lpiso, constraints);
		constraints.insets.set(5, 534, 5, 5);
		add(campoPiso, constraints);	
		constraints.insets.set(10, 585, 13, 5);
		add(ldepartamento, constraints);
		constraints.insets.set(5, 672 , 5, 5);
		add(campoDepartamento, constraints);	
		
		constraints.gridy = 7;
		constraints.insets.set(10, 80, 13, 5);
		add(lpais, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(seleccionPais, constraints);	
		constraints.insets.set(10, 310, 13, 5);
		add(lprovincia, constraints);
		constraints.insets.set(5, 375, 5, 5);
		add(seleccionProvincia, constraints);	
		constraints.insets.set(10, 565, 13, 5);
		add(lciudad, constraints);
		constraints.insets.set(5, 619, 5, 5);
		add(seleccionCiudad, constraints);
		constraints.insets.set(10, 805, 13, 5);
		add(lcodigoPostal, constraints);
		constraints.insets.set(5, 893 , 5, 5);
		add(campoCodigoPostal, constraints);	
		
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		constraints.insets.set(10, 8, 13, 5);
		add(lcondicionIva, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(seleccionCondicionIva, constraints);	
		
		constraints.gridy = 9;
		constraints.insets.set(10, 2, 13, 5);
		add(lcorreoElectronico, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoCorreoElectronico, constraints);	

		constraints.gridy = 10;
		constraints.insets.set(10, 40, 13, 5);
		add(lestadoCivil, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(seleccionEstadoCivil, constraints);
		
		constraints.gridy = 11;
		constraints.insets.set(10, 51, 13, 5);
		add(lprofesion, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoProfesion, constraints);
		constraints.insets.set(10, 310, 13, 5);
		add(lanioRegistro, constraints);
		constraints.insets.set(5, 386, 5, 5);
		add(campoAnioRegistro, constraints);
		
		constraints.gridy = 12;
		constraints.insets.set(5, 30, 5, 5);
		add(ldatosObligatorios, constraints);
		
		constraints.gridy = 13;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 170);
		add(btnConfirmar, constraints);
		constraints.insets.set(5, 5, 5, 0);
		add(btnCancelar, constraints);

	}
		
	private void inicializarTema() {	
		tema.setTema(this);
		
		tema.setTema(lnumeroCliente);
		tema.setTema(ltipoDocumento);
		tema.setTema(lnumeroDocumento);
		tema.setTema(lapellido);
		tema.setTema(lnombre);
		tema.setTema(lfechaNacimiento);
		tema.setTema(lnumeroCUIL);
		tema.setTema(lsexo);
		tema.setTema(lcalle);
		tema.setTema(lnumeroDomicilio);
		tema.setTema(lpiso);
		tema.setTema(ldepartamento);
		tema.setTema(lpais);
		tema.setTema(lprovincia);
		tema.setTema(lciudad);
		tema.setTema(lcodigoPostal);
		tema.setTema(lcondicionIva);
		tema.setTema(lcorreoElectronico);
		tema.setTema(lestadoCivil);
		tema.setTema(lprofesion);
		tema.setTema(lanioRegistro);
		tema.setTemaLabelchica(ldatosObligatorios);
		
		tema.setTema(campoNumeroCliente, false);
		tema.setTema(campoApellido, true);
		tema.setTema(campoNombre, true);
		tema.setTema(campoNumeroDocumento, true);
		tema.setTema(campoNumeroCUIL, true);
		tema.setTema(campoCalle, true);
		tema.setTema(campoNumeroDomicilio, true);
		tema.setTema(campoPiso, true);
		tema.setTema(campoDepartamento, true);
		tema.setTema(campoCodigoPostal, true);
		tema.setTema(campoCorreoElectronico, true);
		tema.setTema(campoProfesion, true);
		tema.setTema(campoAnioRegistro, true);
		
		tema.setTema(dcFechaNacimiento, true);
		
		tema.setTema(btnConfirmar, true);
		tema.setTema(btnCancelar, true);
		
		tema.setTema(seleccionTipoDocumento, true);
		tema.setTema(seleccionSexo, true);
		tema.setTema(seleccionPais, true);
		tema.setTema(seleccionProvincia, true);
		tema.setTema(seleccionCiudad, true);
		tema.setTema(seleccionCondicionIva, true);
		tema.setTema(seleccionEstadoCivil, true);

	}

	public void comportamientos() {
		btnConfirmar.addActionListener(a -> {
           	JOptionPane.showMessageDialog(null, "Solo se implementó el mockup del caso de uso 04:  Dar de Alta Cliente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);       	
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
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
								
			Iterator<Ciudad> iteratorCiudad = GestorDomicilio.get().sortCiudades(seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex())).iterator();
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