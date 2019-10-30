package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import isi.dds.tp.gestor.GestorTema;

public class CU17View2 extends JPanel{
	private static final long serialVersionUID = 1728174776538659768L;

	private GestorTema tema = GestorTema.get();
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lcondicion = new JLabel("Condición:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombre = new JLabel("Nombre:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel lnumeroDocumento = new JLabel("Número documento:");
	private JLabel lnumeroCUIL = new JLabel("Número CUIL:");
	private JLabel lsexo = new JLabel("Sexo:");
	private JLabel lfechaNacimiento = new JLabel("Fecha nacimiento:");
	private JLabel lcalle = new JLabel("Calle:");	
	private JLabel lnumeroDomicilio = new JLabel("Número domicilio:");
	private JLabel lpiso = new JLabel("Piso:");
	private JLabel ldepartamento = new JLabel("Departamento:");
	private JLabel lpais = new JLabel("País:");
	private JLabel lprovincia = new JLabel("Provincia:");
	private JLabel lciudad = new JLabel("Ciudad:");
	private JLabel lcodigoPostal = new JLabel("Código postal:");
	private JLabel lcondicionIva = new JLabel("Condición de IVA:");
	private JLabel lcorreoElectronico = new JLabel("Correo eléctronico:");
	private JLabel lestadoCivil = new JLabel("Estado civil:");
	private JLabel lprofesion = new JLabel("Profesión:");
	private JLabel lanioRegistro = new JLabel("Año registro:");
	
	private JTextField campoNumeroCliente = new JTextField(16);
	private JTextField campoCondicion = new JTextField(16);
	private JTextField campoApellido = new JTextField(16);
	private JTextField campoNombre = new JTextField(16);
	private JTextField campoTipoDocumento = new JTextField(16);
	private JTextField campoNumeroDocumento = new JTextField(16);
	private JTextField campoNumeroCUIL = new JTextField(16);
	private JTextField campoSexo = new JTextField(16);
	private JTextField campoFechaNacimiento = new JTextField(16);
	private JTextField campoCalle = new JTextField(16);
	private JTextField campoNumeroCalle = new JTextField(5);
	private JTextField campoPiso = new JTextField(2);
	private JTextField campoDepartamento = new JTextField(2);
	private JTextField campoPais = new JTextField(16);
	private JTextField campoProvincia = new JTextField(16);
	private JTextField campoCiudad = new JTextField(16);
	private JTextField campoCodigoPostal = new JTextField(5);
	private JTextField campoCondicionIva = new JTextField(16);
	private JTextField campoCorreoElectronico = new JTextField(16);
	private JTextField campoEstadoCivil = new JTextField(16);
	private JTextField campoProfesion = new JTextField(16);
	private JTextField campoAnioRegistro = new JTextField(5);
	
	private JButton btnElegirCliente = new JButton("ELEGIR CLIENTE");
	private JButton btnVolver = new JButton("VOLVER");
	
	public CU17View2(){
		inicializarComponentes();
		ubicarComponentes();
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
		add(campoFechaNacimiento, constraints);
		
		constraints.gridy = 3;
		constraints.insets.set(10, 17, 13, 5);
		add(ltipoDocumento, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoTipoDocumento, constraints);
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
		add(campoSexo, constraints);
		
		constraints.gridy = 6;
		constraints.gridwidth = 4;
		constraints.insets.set(10, 76, 13, 5);
		add(lcalle, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoCalle, constraints);	
		constraints.insets.set(10, 310, 13, 5);
		add(lnumeroDomicilio, constraints);
		constraints.insets.set(5, 422, 5, 5);
		add(campoNumeroCalle, constraints);	
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
		add(campoPais, constraints);	
		constraints.insets.set(10, 310, 13, 5);
		add(lprovincia, constraints);
		constraints.insets.set(5, 375, 5, 5);
		add(campoProvincia, constraints);	
		constraints.insets.set(10, 565, 13, 5);
		add(lciudad, constraints);
		constraints.insets.set(5, 619, 5, 5);
		add(campoCiudad, constraints);
		constraints.insets.set(10, 805, 13, 5);
		add(lcodigoPostal, constraints);
		constraints.insets.set(5, 893 , 5, 5);
		add(campoCodigoPostal, constraints);	
		
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		constraints.insets.set(10, 8, 13, 5);
		add(lcondicionIva, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoCondicionIva, constraints);	
		
		constraints.gridy = 9;
		constraints.insets.set(10, 2, 13, 5);
		add(lcorreoElectronico, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoCorreoElectronico, constraints);	

		constraints.gridy = 10;
		constraints.insets.set(10, 39, 13, 5);
		add(lestadoCivil, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoEstadoCivil, constraints);
		
		constraints.gridy = 11;
		constraints.insets.set(10, 50, 13, 5);
		add(lprofesion, constraints);
		constraints.insets.set(5, 120, 5, 5);
		add(campoProfesion, constraints);
		constraints.insets.set(10, 310, 13, 5);
		add(lanioRegistro, constraints);
		constraints.insets.set(5, 386, 5, 5);
		add(campoAnioRegistro, constraints);
		
		constraints.gridy = 13;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 190);
		add(btnElegirCliente, constraints);
		constraints.insets.set(10, 5, 10, 0);
		add(btnVolver, constraints);
	}
	
	private void inicializarComponentes() {
		tema.setTema(this);
		tema.setTema(lnumeroCliente);
		tema.setTema(lcondicion);
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
		tema.setTema(campoNumeroCliente, false);
		tema.setTema(campoCondicion, false);
		tema.setTema(campoApellido, false);
		tema.setTema(campoNombre, false);
		tema.setTema(campoNumeroDocumento, false);
		tema.setTema(campoNumeroCUIL, false);
		tema.setTema(campoCalle, false);
		tema.setTema(campoNumeroCalle, false);
		tema.setTema(campoPiso, false);
		tema.setTema(campoDepartamento, false);
		tema.setTema(campoCodigoPostal, false);
		tema.setTema(campoCorreoElectronico, false);
		tema.setTema(campoProfesion, false);
		tema.setTema(campoAnioRegistro, false);
		tema.setTema(campoTipoDocumento, false);
		tema.setTema(campoSexo, false);
		tema.setTema(campoFechaNacimiento, false);
		tema.setTema(campoPais, false);
		tema.setTema(campoProvincia, false);
		tema.setTema(campoCiudad, false);
		tema.setTema(campoCondicionIva, false);
		tema.setTema(campoEstadoCivil, false);
		tema.setTema(btnElegirCliente, true);
		tema.setTema(btnVolver, true);
	}

	public void addListenerBtn_ElegirCliente(ActionListener listener) {
		btnElegirCliente.addActionListener(listener);
	}
	
	public void addListenerBtn_Volver(ActionListener listener) {
		btnVolver.addActionListener(listener);
	}
	
	public void setNumeroCliente(String numeroCliente) {
		this.campoNumeroCliente.setText(numeroCliente);
	}

	public void setCondicion(String condicion) {
		this.campoCondicion.setText(condicion);
	}

	public void setApellido(String apellido) {
		this.campoApellido.setText(apellido);
	}

	public void setNombre(String nombre) {
		this.campoNombre.setText(nombre);
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.campoTipoDocumento.setText(tipoDocumento);
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.campoNumeroDocumento.setText(numeroDocumento);
	}
	
	public void setNumeroCuil(String numeroCuil) {
		this.campoNumeroCUIL.setText(numeroCuil);
	}

	public void setSexo(String sexo) {
		this.campoSexo.setText(sexo);
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.campoFechaNacimiento.setText(fechaNacimiento);
	}

	public void setCalle(String calle) {
		this.campoCalle.setText(calle);
	}

	public void setNumeroCalle(String numeroCalle) {
		this.campoNumeroCalle.setText(numeroCalle);
	}

	public void setPiso(String piso) {
		this.campoPiso.setText(piso);
	}

	public void setDepartamento(String departamento) {
		this.campoDepartamento.setText(departamento);
	}

	public void setPais(String pais) {
		this.campoPais.setText(pais);
	}

	public void setProvincia(String provincia) {
		this.campoProvincia.setText(provincia);
	}

	public void setCiudad(String ciudad) {
		this.campoCiudad.setText(ciudad);
	}

	public void setCodigoPostal(String codigoPostal) {
		this.campoCodigoPostal.setText(codigoPostal);
	}

	public void setCondicionIva(String condicionIva) {
		this.campoCondicionIva.setText(condicionIva);
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.campoCorreoElectronico.setText(correoElectronico);
	}

	public void setEstadoCivil(String estadoCivil) {
		this.campoEstadoCivil.setText(estadoCivil);
	}

	public void setProfesion(String profesion) {
		this.campoProfesion.setText(profesion);
	}

	public void setAnioRegistro(String anioRegistro) {
		this.campoAnioRegistro.setText(anioRegistro);
	}
}
