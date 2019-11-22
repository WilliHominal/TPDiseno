package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Pais;
import isi.dds.tp.modelo.Provincia;

public class CU04View extends JPanel{	
	private static final long serialVersionUID = -1269025063146813420L;
	
	private GestorTema tema = GestorTema.get();
	
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
	private JLabel ldatosObligatorios = new JLabel("Datos obligatorios (*)");
		
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

	public CU04View() {
		ubicarComponentes();
		inicializarTema();
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
	
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
		constraints.insets.set(25, 30, 5, 5);
		add(ldatosObligatorios, constraints);
		
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(25, 5, 5, 170);
		add(btnConfirmar, constraints);
		constraints.insets.set(25, 5, 5, 0);
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
	
	public void addListenerBtnConfirmar(ActionListener listener) {
		btnConfirmar.addActionListener(listener);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerSeleccionPais(ActionListener listener) {
		seleccionPais.addActionListener (listener);
	}
	
	public void addListenerSeleccionProvincia(ActionListener listener) {
		seleccionProvincia.addActionListener (listener);	
	}
	
	public void addListenerSeleccionSexo(ActionListener listener) {
		seleccionSexo.addActionListener (listener);	
	}
	
	public void addPais(Pais pais) {
		seleccionPais.addItem(pais);
	}
	
	public void addProvincia(Provincia provincia) {
		seleccionProvincia.addItem(provincia);
	}
	
	public void addCiudad(Ciudad ciudad) {
		seleccionCiudad.addItem(ciudad);
	}
	
	public void addTipoDocumento(String tipoDocumento) {
		seleccionTipoDocumento.addItem(tipoDocumento);
	}
	
	public void addSexo(String sexo) {
		seleccionSexo.addItem(sexo);
	}
	
	public void addCondicionIva(String condicionIva) {
		seleccionCondicionIva.addItem(condicionIva);
	}
	
	public void addEstadoCivil(String estadoCivil) {
		seleccionEstadoCivil.addItem(estadoCivil);
	}
	
	public Pais getPais() {
		return seleccionPais.getItemAt(seleccionPais.getSelectedIndex());
	}
	
	public Provincia getProvincia() {
		return seleccionProvincia.getItemAt(seleccionProvincia.getSelectedIndex());
	}
	
	public String getSexo() {
		return seleccionSexo.getItemAt(seleccionSexo.getSelectedIndex());
	}
	
	public void habilitarProvincia(Boolean habilitado) {
		if(!habilitado) {
			seleccionProvincia.removeAllItems();
		}
		seleccionProvincia.setEnabled(true);	
	}
	
	public void habilitarCiudad(Boolean habilitado) {
		if(!habilitado) {
			seleccionCiudad.removeAllItems();
		}
		seleccionCiudad.setEnabled(true);	
	}

	public Integer indexSeleccionEstadoCivil() {
		int index = seleccionEstadoCivil.getSelectedIndex();
		seleccionEstadoCivil.removeAllItems();
		return index;
	}

	public void setEstadoCivil(Integer index) {
		seleccionEstadoCivil.setSelectedIndex(index);
	}
}