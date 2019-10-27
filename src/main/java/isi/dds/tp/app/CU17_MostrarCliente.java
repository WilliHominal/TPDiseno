package isi.dds.tp.app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.Cliente;

@SuppressWarnings("serial")
public class CU17_MostrarCliente extends JPanel{
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					
					Cliente cliente = GestorCliente.get().getCliente(5434663813l);
					
					new CU17_MostrarCliente(frame, cliente);
					GestorTema.get().ventana(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame ventana;
	private CU17_BuscarCliente panelAnterior;
	private GestorTema tema = GestorTema.get();
	private Cliente cliente;
	private String tituloAnterior;
	
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
	private JTextField campoNumeroDomicilio = new JTextField(5);
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
	
	public CU17_MostrarCliente(JFrame ventana, Cliente cliente){
		this.cliente = cliente;
		this.ventana = ventana;
		this.tituloAnterior = ventana.getTitle();
		try {		
			panelAnterior = (CU17_BuscarCliente) ventana.getContentPane();
		}catch(Exception ex) {
		    panelAnterior = null;
		}
		ventana.setContentPane(this);
		
		inicializarComponentes();
		ubicarComponentes();
		comportamientos();
		
		ventana.setTitle("Buscar cliente: MOSTRAR CLIENTE SELECCIONADO");	
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
		constraints.insets.set(10, 5, 10, 170);
		add(btnElegirCliente, constraints);
		constraints.insets.set(10, 5, 10, 0);
		add(btnVolver, constraints);

	}
	
	private void inicializarComponentes() {
		btnElegirCliente.setPreferredSize(new Dimension(164, 25));
		btnVolver.setPreferredSize(new Dimension(164, 25));
		
		tema.panel(this);
		
		tema.label(lnumeroCliente);
		tema.label(lcondicion);
		tema.label(ltipoDocumento);
		tema.label(lnumeroDocumento);
		tema.label(lapellido);
		tema.label(lnombre);
		tema.label(lfechaNacimiento);
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
		
		tema.campo(campoNumeroCliente, false);
		tema.campo(campoCondicion, false);
		tema.campo(campoApellido, false);
		tema.campo(campoNombre, false);
		tema.campo(campoNumeroDocumento, false);
		tema.campo(campoNumeroCUIL, false);
		tema.campo(campoCalle, false);
		tema.campo(campoNumeroDomicilio, false);
		tema.campo(campoPiso, false);
		tema.campo(campoDepartamento, false);
		tema.campo(campoCodigoPostal, false);
		tema.campo(campoCorreoElectronico, false);
		tema.campo(campoProfesion, false);
		tema.campo(campoAnioRegistro, false);
		tema.campo(campoTipoDocumento, false);
		tema.campo(campoSexo, false);
		tema.campo(campoFechaNacimiento, false);
		tema.campo(campoPais, false);
		tema.campo(campoProvincia, false);
		tema.campo(campoCiudad, false);
		tema.campo(campoCondicionIva, false);
		tema.campo(campoEstadoCivil, false);
		
		campoNumeroCliente.setText(cliente.getNumeroCliente().toString());
		campoCondicion.setText(cliente.getCondicion().toString());
		campoApellido.setText(cliente.getApellido());
		campoNombre.setText(cliente.getNombre());
		campoNumeroDocumento.setText(cliente.getNumeroDocumento());
		campoNumeroCUIL.setText(cliente.getNumeroCuil().toString());
		campoCalle.setText(cliente.getCalle());
		campoNumeroDomicilio.setText(cliente.getNumeroCalle().toString());
		if(cliente.getPiso()==null) {
			campoPiso.setText("-");
			campoDepartamento.setText("-");
		}else {
			campoPiso.setText(cliente.getPiso().toString());
			campoDepartamento.setText(cliente.getDepartamento());
		}
		campoCodigoPostal.setText(cliente.getCodigoPostal().toString());
		campoCorreoElectronico.setText(cliente.getCorreoElectronico());
		campoProfesion.setText(cliente.getProfesion());
		campoAnioRegistro.setText(cliente.getAnioRegistro().toString());
		campoTipoDocumento.setText(GestorEnum.get().getStringTipoDocumento(cliente.getTipoDocumento()));
		campoSexo.setText(GestorEnum.get().getStringSexo(cliente.getSexo()));
		campoFechaNacimiento.setText(cliente.getFechaNacimiento().toString());
		campoPais.setText(cliente.getCiudad().getProvincia().getPais().getNombre());
		campoProvincia.setText(cliente.getCiudad().getProvincia().getNombre());
		campoCiudad.setText(cliente.getCiudad().getNombre());
		campoCondicionIva.setText(GestorEnum.get().getStringCondicionIva(cliente.getCondicionIva()));
		if(campoSexo.getText().equals("Femenino")) {
			campoEstadoCivil.setText(GestorEnum.get().getStringEstadoCivilFem(cliente.getEstadoCivil()));
		}
		else {
			campoEstadoCivil.setText(GestorEnum.get().getStringEstadoCivil(cliente.getEstadoCivil()));
		}
		
		tema.boton(btnElegirCliente, true);
		tema.boton(btnVolver, true);
	}

	
	private void comportamientos() {
		btnElegirCliente.addActionListener(a -> {
			try {			
				panelAnterior.obtenidoCliente();
	        	this.setVisible(false);    
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}  	
		});
		 
		btnVolver.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.setContentPane(panelAnterior);	
				ventana.setTitle(tituloAnterior);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});		
	}
}
