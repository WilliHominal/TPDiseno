package isi.dds.tp.view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import isi.dds.tp.controller.CU08Controller;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.Provincia;
import isi.dds.tp.modelo.TipoCobertura;

public class CU08View extends JPanel{
	private static final long serialVersionUID = -5523172427711498930L;
	
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					    	frame.setVisible(false);
							HibernateUtil.shutdown();
							System.exit(0);
					    }
					});
					new CU08Controller(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private GestorTema tema = GestorTema.get();
	
	private JLabel ltipoCobertura = new JLabel("Valor porcentual tipo cobertura:");
	private JLabel lmodelo = new JLabel("Valor porcentual modelo:");
	private JLabel lciudad = new JLabel("Valor porcentual ciudad:");
	private JLabel lguardaGarage = new JLabel("Valor porcentual si se guarda en garage:");
	private JLabel ltieneAlarma = new JLabel("Valor porcentual si posee alarma:");
	private JLabel ltieneRastreo = new JLabel("Valor porcentual si posee dispositivo de rastreo vehicular:");
	private JLabel ltieneTuercas = new JLabel("Valor porcentual si posee tuercas antirrobo en las cuatro ruedas:");
	private JLabel lkm = new JLabel("Ajuste por Kilómetros realizados por año. Valor de ajuste cada 10.000 Km:");
	private JLabel lceroSiniestros = new JLabel("Ajuste por número de siniestros en el úlitmo año. Sin siniestros:");
	private JLabel lunSiniestro = new JLabel("Ajuste por número de siniestros en el úlitmo año. Con 1 siniestro:");
	private JLabel ldosSiniestros = new JLabel("Ajuste por número de siniestros en el úlitmo año. Con 2 siniestros:");
	private JLabel lmuchosSiniestros = new JLabel("Ajuste por número de siniestros en el úlitmo año. Con más de dos siniestros:");
	private JLabel lcantidadHijos = new JLabel("Ajuste por cantidad de hijos entre 18 y 30 años. Valor porcentual por cada hijo:");
	private JLabel lderechoEmision = new JLabel("Derecho de emisión:");
	private JLabel ldescuentoUnidadAdicional = new JLabel("Descuento por unidad adicional:");
	
	private JLabel lcoberturas = new JLabel("Tipos de cobertura:");
	private JLabel lmarcas = new JLabel("Marcas:");
	private JLabel lmodelos = new JLabel("Modelos:");
	private JLabel lprovincias = new JLabel("Provincias:");
	private JLabel lciudades = new JLabel("Ciudades:");
	
	private String labelValorActual = "Valor actual:";
	
	private JLabel lvalorActual1 = new JLabel(labelValorActual);
	private JLabel lvalorActual2 = new JLabel(labelValorActual);
	private JLabel lvalorActual3 = new JLabel(labelValorActual);
	private JLabel lvalorActual4 = new JLabel(labelValorActual);
	private JLabel lvalorActual5 = new JLabel(labelValorActual);
	private JLabel lvalorActual6 = new JLabel(labelValorActual);
	private JLabel lvalorActual7 = new JLabel(labelValorActual);
	private JLabel lvalorActual8 = new JLabel(labelValorActual);
	private JLabel lvalorActual9 = new JLabel(labelValorActual);
	private JLabel lvalorActual10 = new JLabel(labelValorActual);
	private JLabel lvalorActual11 = new JLabel(labelValorActual);
	private JLabel lvalorActual12 = new JLabel(labelValorActual);
	private JLabel lvalorActual13 = new JLabel(labelValorActual);
	private JLabel lvalorActual14 = new JLabel(labelValorActual);
	private JLabel lvalorActual15 = new JLabel(labelValorActual);
	
	private String labelValorModificar = "Valor a establecer:";
	
	private JLabel lvalorModificar1 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar2 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar3 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar4 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar5 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar6 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar7 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar8 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar9 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar10 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar11 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar12 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar13 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar14 = new JLabel(labelValorModificar);
	private JLabel lvalorModificar15 = new JLabel(labelValorModificar);
	
	private JLabel ltituloCheck = new JLabel("Marcar");
	//TODO cambiar nombres y agregar ubicacion y retornar true si estan seleccionados
	
	//TODO agregar radio button para marcar y desmarcar todos
	private JCheckBox checkCobertura = new JCheckBox();
	private JCheckBox checkElegir2 = new JCheckBox();
	private JCheckBox checkElegir3 = new JCheckBox();
	private JCheckBox checkElegir4 = new JCheckBox();
	private JCheckBox checkElegir5 = new JCheckBox();
	private JCheckBox checkElegir6 = new JCheckBox();
	private JCheckBox checkElegir7 = new JCheckBox();
	private JCheckBox checkElegir8 = new JCheckBox();
	private JCheckBox checkElegir9 = new JCheckBox();
	private JCheckBox checkElegir10 = new JCheckBox();
	private JCheckBox checkElegir11 = new JCheckBox();
	private JCheckBox checkElegir12 = new JCheckBox();
	private JCheckBox checkElegir13 = new JCheckBox();
	private JCheckBox checkElegir14 = new JCheckBox();
	private JCheckBox checkElegir15 = new JCheckBox();
	
	private JTextField campoTipoCobertura = new JTextField(16);
	private JTextField campoModelo = new JTextField(16);
	private JTextField campoCiudad = new JTextField(16);
	private JTextField campoGuardaGarage = new JTextField(16);
	private JTextField campoTieneAlarma = new JTextField(16);
	private JTextField campoTieneRastreo = new JTextField(16);
	private JTextField campoTieneTuercas = new JTextField(16);
	private JTextField campoKm = new JTextField(16);
	private JTextField campoCeroSiniestros = new JTextField(16);
	private JTextField campoUnSiniestro = new JTextField(16);
	private JTextField campoDosSiniestros = new JTextField(16);
	private JTextField campoMuchosSiniestros = new JTextField(16);
	private JTextField campoCantidadHijos = new JTextField(16);
	private JTextField campoDerechoEmision = new JTextField(16);
	private JTextField campoDescuentoUnidadAdicional = new JTextField(16);
	
	private JTextField campoTipoCoberturaActual = new JTextField(16);
	private JTextField campoModeloActual = new JTextField(16);
	private JTextField campoCiudadActual = new JTextField(16);
	private JTextField campoGuardaGarageActual = new JTextField(16);
	private JTextField campoTieneAlarmaActual = new JTextField(16);
	private JTextField campoTieneRastreoActual = new JTextField(16);
	private JTextField campoTieneTuercasActual = new JTextField(16);
	private JTextField campoKmActual = new JTextField(16);
	private JTextField campoCeroSiniestrosActual = new JTextField(16);
	private JTextField campoUnSiniestroActual = new JTextField(16);
	private JTextField campoDosSiniestrosActual = new JTextField(16);
	private JTextField campoMuchosSiniestrosActual = new JTextField(16);
	private JTextField campoCantidadHijosActual = new JTextField(16);
	private JTextField campoDerechoEmisionActual = new JTextField(16);
	private JTextField campoDescuentoUnidadAdicionalActual = new JTextField(16);
	
	private JComboBox<TipoCobertura> seleccionTipoCobertura = new JComboBox<TipoCobertura>();
	private JComboBox<Marca> seleccionMarca = new JComboBox<Marca>();
	private JComboBox<Modelo> seleccionModelo = new JComboBox<Modelo>();
	private JComboBox<Provincia> seleccionProvincia = new JComboBox<Provincia>();
	private JComboBox<Ciudad> seleccionCiudad = new JComboBox<Ciudad>();
	
	private JButton btnActualizarFactores1 = new JButton("ACTUALIZAR");
	private JButton btnActualizarFactores2 = new JButton("ACTUALIZAR");
	private JButton btnCancelar1 = new JButton("CANCELAR");
	private JButton btnCancelar2 = new JButton("CANCELAR");
	
	public CU08View() {
		ubicarComponentes();
		inicializarTema();
	}

	private void inicializarTema() {
		tema.setTema(this);
		tema.setTemaSubrayado(ltipoCobertura);
		tema.setTemaSubrayado(lmodelo);
		tema.setTemaSubrayado(lciudad);
		tema.setTemaSubrayado(lguardaGarage);
		tema.setTemaSubrayado(ltieneAlarma);
		tema.setTemaSubrayado(ltieneRastreo);
		tema.setTemaSubrayado(ltieneTuercas);
		tema.setTemaSubrayado(lkm);
		tema.setTemaSubrayado(lceroSiniestros);
		tema.setTemaSubrayado(lunSiniestro);
		tema.setTemaSubrayado(ldosSiniestros);
		tema.setTemaSubrayado(lmuchosSiniestros);
		tema.setTemaSubrayado(lcantidadHijos);
		tema.setTemaSubrayado(lderechoEmision);
		tema.setTemaSubrayado(ldescuentoUnidadAdicional);
		
		tema.setTema(lcoberturas);
		tema.setTema(lmarcas);
		tema.setTema(lmodelos);
		tema.setTema(lprovincias);
		tema.setTema(lciudades);
		
		tema.setTema(lvalorActual1);
		tema.setTema(lvalorActual2);
		tema.setTema(lvalorActual3);
		tema.setTema(lvalorActual4);
		tema.setTema(lvalorActual5);
		tema.setTema(lvalorActual6);
		tema.setTema(lvalorActual7);
		tema.setTema(lvalorActual8);
		tema.setTema(lvalorActual9);
		tema.setTema(lvalorActual10);
		tema.setTema(lvalorActual11);
		tema.setTema(lvalorActual12);
		tema.setTema(lvalorActual13);
		tema.setTema(lvalorActual14);
		tema.setTema(lvalorActual15);
		
		tema.setTema(lvalorModificar1);
		tema.setTema(lvalorModificar2);
		tema.setTema(lvalorModificar3);
		tema.setTema(lvalorModificar4);
		tema.setTema(lvalorModificar5);
		tema.setTema(lvalorModificar6);
		tema.setTema(lvalorModificar7);
		tema.setTema(lvalorModificar8);
		tema.setTema(lvalorModificar9);
		tema.setTema(lvalorModificar10);
		tema.setTema(lvalorModificar11);
		tema.setTema(lvalorModificar12);
		tema.setTema(lvalorModificar13);
		tema.setTema(lvalorModificar14);
		tema.setTema(lvalorModificar15);
		
		tema.setTema(ltituloCheck);
		
		tema.setTema(checkCobertura, "Marcar para modificar el valor porcentual del tipo de cobertura elegido.");
		tema.setTema(checkElegir2, "");
		tema.setTema(checkElegir3, "");
		tema.setTema(checkElegir4, "");
		tema.setTema(checkElegir5, "");
		tema.setTema(checkElegir6, "");
		tema.setTema(checkElegir7, "");
		tema.setTema(checkElegir8, "");
		tema.setTema(checkElegir9, "");
		tema.setTema(checkElegir10, "");
		tema.setTema(checkElegir11, "");
		tema.setTema(checkElegir12, "");
		tema.setTema(checkElegir13, "");
		tema.setTema(checkElegir14, "");
		tema.setTema(checkElegir15, "");
		
		tema.setTema(campoTipoCobertura, true);
		tema.setTema(campoModelo, true);
		tema.setTema(campoCiudad, true);
		tema.setTema(campoGuardaGarage, true);
		tema.setTema(campoTieneAlarma, true);
		tema.setTema(campoTieneRastreo, true);
		tema.setTema(campoTieneTuercas, true);
		tema.setTema(campoKm, true);
		tema.setTema(campoCeroSiniestros, true);
		tema.setTema(campoUnSiniestro, true);
		tema.setTema(campoDosSiniestros, true);
		tema.setTema(campoMuchosSiniestros, true);
		tema.setTema(campoCantidadHijos, true);
		tema.setTema(campoDerechoEmision, true);
		tema.setTema(campoDescuentoUnidadAdicional, true);
		
		tema.setTema(campoTipoCoberturaActual, false);
		tema.setTema(campoModeloActual, false);
		tema.setTema(campoCiudadActual, false);
		tema.setTema(campoGuardaGarageActual, false);
		tema.setTema(campoTieneAlarmaActual, false);
		tema.setTema(campoTieneRastreoActual, false);
		tema.setTema(campoTieneTuercasActual, false);
		tema.setTema(campoKmActual, false);
		tema.setTema(campoCeroSiniestrosActual, false);
		tema.setTema(campoUnSiniestroActual, false);
		tema.setTema(campoDosSiniestrosActual, false);
		tema.setTema(campoMuchosSiniestrosActual, false);
		tema.setTema(campoCantidadHijosActual, false);
		tema.setTema(campoDerechoEmisionActual, false);
		tema.setTema(campoDescuentoUnidadAdicionalActual, false);
		
		tema.setTema(seleccionTipoCobertura, true);
		tema.setTema(seleccionMarca, true);
		tema.setTema(seleccionModelo, true);
		tema.setTema(seleccionProvincia, true);
		tema.setTema(seleccionCiudad, true);
		
		tema.setTema(btnActualizarFactores1, true);
		tema.setTema(btnActualizarFactores2, true);
		tema.setTema(btnCancelar1, true);
		tema.setTema(btnCancelar2, true);
	}

	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
			     Insets titulo = new Insets(20,   5, 10, 30);
			      Insets check = new Insets(20,   5, 10,  5);
		    Insets primerLabel = new Insets( 5,   5, 15,  5);
		    Insets primerCampo = new Insets( 5, 115, 15,  5);
		   Insets segundaLabel = new Insets( 5, 315, 15,  5);
		   Insets segundoCampo = new Insets( 5, 390, 15,  5);
		Insets tituloSeleccion = new Insets(20,  30, 10, 30);
		      Insets seleccion = new Insets( 5,  30, 15,  5);
		
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.insets = check;
		add(checkCobertura, constraints);
		constraints.gridx = 1;
		
		      	  
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 5, 10, 30);
		add(btnActualizarFactores1, constraints);
		constraints.gridx = 2;
		add(btnCancelar1, constraints);
		
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 4;
		constraints.gridx = 1;
		
		//y = 1 - 2   valor actual = 1
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.insets = check;
		//add(checkCobertura, constraints);
		constraints.gridx = 1;
		constraints.insets = titulo;
		add(ltipoCobertura, constraints);
		
		constraints.gridy = 2;
		constraints.insets = primerLabel;
		add(lvalorModificar1, constraints);
		constraints.insets = primerCampo;
		add(campoTipoCobertura, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual1, constraints);
		constraints.insets = segundoCampo;
		add(campoTipoCoberturaActual, constraints);
		
		constraints.gridx = 5;
		constraints.gridwidth = 1;
		constraints.gridy = 1;
		constraints.insets = tituloSeleccion;
		add(lcoberturas, constraints);
		constraints.gridy = 2;
		constraints.insets = seleccion;
		add(seleccionTipoCobertura, constraints);
		constraints.gridwidth = 4;
		constraints.gridx = 1;
		
		//y = 3 - 4   valor actual = 2
		constraints.gridy = 3;
		constraints.insets = titulo;
		add(lmodelo, constraints);
		
		constraints.gridy = 4;
		constraints.insets = primerLabel;
		add(lvalorModificar2, constraints);
		constraints.insets = primerCampo;
		add(campoModelo, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual2, constraints);
		constraints.insets = segundoCampo;
		add(campoModeloActual, constraints);
		
		constraints.gridx = 5;
		constraints.gridwidth = 1;
		constraints.gridy = 3;
		constraints.insets = tituloSeleccion;
		add(lmarcas, constraints);
		constraints.gridy = 4;
		constraints.insets = seleccion;
		add(seleccionMarca, constraints);
		constraints.gridx = 6;
		constraints.gridy = 3;
		constraints.insets = tituloSeleccion;
		add(lmodelos, constraints);
		constraints.gridy = 4;
		constraints.insets = seleccion;
		add(seleccionModelo, constraints);
		constraints.gridwidth = 4;
		constraints.gridx = 1;
		
		//y = 5 - 6   valor actual = 3
		constraints.gridy = 5;
		constraints.insets = titulo;
		add(lciudad, constraints);
		
		constraints.gridy = 6;
		constraints.insets = primerLabel;
		add(lvalorModificar3, constraints);
		constraints.insets = primerCampo;
		add(campoCiudad, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual3, constraints);
		constraints.insets = segundoCampo;
		add(campoCiudadActual, constraints);
		
		
		constraints.gridx = 5;
		constraints.gridwidth = 1;
		constraints.gridy = 5;
		constraints.insets = tituloSeleccion;
		add(lprovincias, constraints);
		constraints.gridy = 6;
		constraints.insets = seleccion;
		add(seleccionProvincia, constraints);
		constraints.gridx = 6;
		constraints.gridy = 5;
		constraints.insets = tituloSeleccion;
		add(lciudades, constraints);
		constraints.gridy = 6;
		constraints.insets = seleccion;
		add(seleccionCiudad, constraints);
		constraints.gridwidth = 4;
		constraints.gridx = 1;
		
		
		//y = 7 - 8   valor actual = 4
		constraints.gridy = 7;
		constraints.insets = titulo;
		add(lguardaGarage, constraints);
		
		constraints.gridy = 8;
		constraints.insets = primerLabel;
		add(lvalorModificar4, constraints);
		constraints.insets = primerCampo;
		add(campoGuardaGarage, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual4, constraints);
		constraints.insets = segundoCampo;
		add(campoGuardaGarageActual, constraints);
		
		//y = 9 - 10   valor actual = 5
		constraints.gridy = 9;
		constraints.insets = titulo;
		add(ltieneAlarma, constraints);
		
		constraints.gridy = 10;
		constraints.insets = primerLabel;
		add(lvalorModificar5, constraints);
		constraints.insets = primerCampo;
		add(campoTieneAlarma, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual5, constraints);
		constraints.insets = segundoCampo;
		add(campoTieneAlarmaActual, constraints);
		
		//y = 11 - 12   valor actual = 6
		constraints.gridy = 11;
		constraints.insets = titulo;
		add(ltieneRastreo, constraints);
		
		constraints.gridy = 12;
		constraints.insets = primerLabel;
		add(lvalorModificar6, constraints);
		constraints.insets = primerCampo;
		add(campoTieneRastreo, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual6, constraints);
		constraints.insets = segundoCampo;
		add(campoTieneRastreoActual, constraints);
		
		//y = 13 - 14   valor actual = 7
		constraints.gridy = 13;
		constraints.insets = titulo;
		add(ltieneTuercas, constraints);
		
		constraints.gridy = 14;
		constraints.insets = primerLabel;
		add(lvalorModificar7, constraints);
		constraints.insets = primerCampo;
		add(campoTieneTuercas, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual7, constraints);
		constraints.insets = segundoCampo;
		add(campoTieneTuercasActual, constraints);
		
		//y = 15 - 16   valor actual = 8
		constraints.gridy = 15;
		constraints.insets = titulo;
		add(lkm, constraints);
		
		constraints.gridy = 16;
		constraints.insets = primerLabel;
		add(lvalorModificar8, constraints);
		constraints.insets = primerCampo;
		add(campoKm, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual8, constraints);
		constraints.insets = segundoCampo;
		add(campoKmActual, constraints);
		
		//y = 17 - 18   valor actual = 9
		constraints.gridy = 17;
		constraints.insets = titulo;
		add(lceroSiniestros, constraints);
		
		constraints.gridy = 18;
		constraints.insets = primerLabel;
		add(lvalorModificar9, constraints);
		constraints.insets = primerCampo;
		add(campoCeroSiniestros, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual9, constraints);
		constraints.insets = segundoCampo;
		add(campoCeroSiniestrosActual, constraints);
		
		//y = 19 - 20   valor actual = 10
		constraints.gridy = 19;
		constraints.insets = titulo;
		add(lunSiniestro, constraints);
		
		constraints.gridy = 20;
		constraints.insets = primerLabel;
		add(lvalorModificar10, constraints);
		constraints.insets = primerCampo;
		add(campoUnSiniestro, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual10, constraints);
		constraints.insets = segundoCampo;
		add(campoUnSiniestroActual, constraints);
		
		//y = 21 - 22   valor actual = 11
		constraints.gridy = 21;
		constraints.insets = titulo;
		add(ldosSiniestros, constraints);
		
		constraints.gridy = 22;
		constraints.insets = primerLabel;
		add(lvalorModificar11, constraints);
		constraints.insets = primerCampo;
		add(campoDosSiniestros, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual11, constraints);
		constraints.insets = segundoCampo;
		add(campoDosSiniestrosActual, constraints);
		
		//y = 23 - 24   valor actual = 12
		constraints.gridy = 23;
		constraints.insets = titulo;
		add(lmuchosSiniestros, constraints);
		
		constraints.gridy = 24;
		constraints.insets = primerLabel;
		add(lvalorModificar12, constraints);
		constraints.insets = primerCampo;
		add(campoMuchosSiniestros, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual12, constraints);
		constraints.insets = segundoCampo;
		add(campoMuchosSiniestrosActual, constraints);
		
		//y = 25 - 26   valor actual = 13
		constraints.gridy = 25;
		constraints.insets = titulo;
		add(lcantidadHijos, constraints);
		
		constraints.gridy = 26;
		constraints.insets = primerLabel;
		add(lvalorModificar13, constraints);
		constraints.insets = primerCampo;
		add(campoCantidadHijos, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual13, constraints);
		constraints.insets = segundoCampo;
		add(campoCantidadHijosActual, constraints);
		
		//y = 27 - 28   valor actual = 14
		constraints.gridy = 27;
		constraints.insets = titulo;
		add(lderechoEmision, constraints);
		
		constraints.gridy = 28;
		constraints.insets = primerLabel;
		add(lvalorModificar14, constraints);
		constraints.insets = primerCampo;
		add(campoDerechoEmision, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual14, constraints);
		constraints.insets = segundoCampo;
		add(campoDerechoEmisionActual, constraints);
		
		//y = 29 - 30   valor actual = 15
		constraints.gridy = 29;
		constraints.insets = titulo;
		add(ldescuentoUnidadAdicional, constraints);
		
		constraints.gridy = 30;
		constraints.insets = primerLabel;
		add(lvalorModificar15, constraints);
		constraints.insets = primerCampo;
		add(campoDescuentoUnidadAdicional, constraints);
		constraints.insets = segundaLabel;
		add(lvalorActual15, constraints);
		constraints.insets = segundoCampo;
		add(campoDescuentoUnidadAdicionalActual, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 31;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(30, 5, 10, 30);
		add(btnActualizarFactores2, constraints);
		constraints.gridx = 2;
		add(btnCancelar2, constraints);
		
	}
	
	public void addListenerCampoTipoCobertura(KeyListener listener){
		campoTipoCobertura.addKeyListener(listener);
	}

	public void addListenerCampoModelo(KeyListener listener){
		campoModelo.addKeyListener(listener);
	}

	public void addListenerCampoCiudad(KeyListener listener){
		campoCiudad.addKeyListener(listener);
	}

	public void addListenerCampoGuardaGarage(KeyListener listener){
		campoGuardaGarage.addKeyListener(listener);
	}

	public void addListenerCampoTieneAlarma(KeyListener listener){
		campoTieneAlarma.addKeyListener(listener);
	}

	public void addListenerCampoTieneRastreo(KeyListener listener){
		campoTieneRastreo.addKeyListener(listener);
	}

	public void addListenerCampoTieneTuercas(KeyListener listener){
		campoTieneTuercas.addKeyListener(listener);
	}

	public void addListenerCampoKm(KeyListener listener){
		campoKm.addKeyListener(listener);
	}

	public void addListenerCampoCeroSiniestros(KeyListener listener){
		campoCeroSiniestros.addKeyListener(listener);
	}

	public void addListenerCampoUnSiniestro(KeyListener listener){
		campoUnSiniestro.addKeyListener(listener);
	}

	public void addListenerCampoDosSiniestros(KeyListener listener){
		campoDosSiniestros.addKeyListener(listener);
	}

	public void addListenerCampoMuchosSiniestros(KeyListener listener){
		campoMuchosSiniestros.addKeyListener(listener);
	}
	
	public void addListenerCampoCantidadHijos(KeyListener listener){
		campoCantidadHijos.addKeyListener(listener);
	}
	
	public void addListenerCampoDerechoEmision(KeyListener listener){
		campoDerechoEmision.addKeyListener(listener);
	}
	
	public void addListenerCampoDescuentoUnidadAdicional(KeyListener listener){
		campoDescuentoUnidadAdicional.addKeyListener(listener);
	}
	
	public void addListenerSeleccionMarca(ActionListener listener) {
		seleccionMarca.addActionListener(listener);
	}
	
	public void addListenerSeleccionModelo(ActionListener listener) {
		seleccionModelo.addActionListener(listener);
	}

	public void addListenerSeleccionProvincia(ActionListener listener) {
		seleccionProvincia.addActionListener(listener);
	}
	
	public void addListenerSeleccionCiudad(ActionListener listener) {
		seleccionCiudad.addActionListener(listener);
	}
	
	public void addListenerSeleccionTipoCobertura(ActionListener listener) {
		seleccionTipoCobertura.addActionListener(listener);
	}
	
	public void addListenerBtnActualizarFactores(ActionListener listener) {
		btnActualizarFactores1.addActionListener(listener);
		btnActualizarFactores2.addActionListener(listener);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar1.addActionListener(listener);
		btnCancelar2.addActionListener(listener);
	}

	public String getTipoCobertura() {
		return campoTipoCobertura.getText();
	}

	public String getModelo() {
		return campoModelo.getText();
	}

	public String getCiudad() {
		return campoCiudad.getText();
	}

	public String getGuardaGarage() {
		return campoGuardaGarage.getText();
	}

	public String getTieneAlarma() {
		return campoTieneAlarma.getText();
	}

	public String getTieneRastreo() {
		return campoTieneRastreo.getText();
	}

	public String getTieneTuercas() {
		return campoTieneTuercas.getText();
	}

	public String getKm() {
		return campoKm.getText();
	}

	public String getCeroSiniestros() {
		return campoCeroSiniestros.getText();
	}

	public String getUnSiniestro() {
		return campoUnSiniestro.getText();
	}

	public String getDosSiniestros() {
		return campoDosSiniestros.getText();
	}

	public String getMuchosSiniestros() {
		return campoMuchosSiniestros.getText();
	}

	public String getCantidadHijos() {
		return campoCantidadHijos.getText();
	}

	public String getDerechoEmision() {
		return campoDerechoEmision.getText();
	}

	public String getDescuentoUnidadAdicional() {
		return campoDescuentoUnidadAdicional.getText();
	}

	public void setTipoCoberturaActual(String tipoCoberturaActual) {
		this.campoTipoCoberturaActual.setText(tipoCoberturaActual);
	}

	public void setModeloActual(String modeloActual) {
		this.campoModeloActual.setText(modeloActual);
	}

	public void setCiudadActual(String ciudadActual) {
		this.campoCiudadActual.setText(ciudadActual);
	}

	public void setGuardaGarageActual(String guardaGarageActual) {
		this.campoGuardaGarageActual.setText(guardaGarageActual);
	}

	public void setTieneAlarmaActual(String tieneAlarmaActual) {
		this.campoTieneAlarmaActual.setText(tieneAlarmaActual);
	}

	public void setTieneRastreoActual(String tieneRastreoActual) {
		this.campoTieneRastreoActual.setText(tieneRastreoActual);
	}

	public void setTieneTuercasActual(String tieneTuercasActual) {
		this.campoTieneTuercasActual.setText(tieneTuercasActual);
	}

	public void setKmActual(String kmActual) {
		this.campoKmActual.setText(kmActual);
	}

	public void setCeroSiniestrosActual(String ceroSiniestrosActual) {
		this.campoCeroSiniestrosActual.setText(ceroSiniestrosActual);
	}

	public void setUnSiniestroActual(String unSiniestroActual) {
		this.campoUnSiniestroActual.setText(unSiniestroActual);
	}

	public void setDosSiniestrosActual(String dosSiniestrosActual) {
		this.campoDosSiniestrosActual.setText(dosSiniestrosActual);
	}

	public void setMuchosSiniestrosActual(String muchosSiniestrosActual) {
		this.campoMuchosSiniestrosActual.setText(muchosSiniestrosActual);
	}

	public void setCantidadHijosActual(String cantidadHijosActual) {
		this.campoCantidadHijosActual.setText(cantidadHijosActual);
	}

	public void setDerechoEmisionActual(String derechoEmisionActual) {
		this.campoDerechoEmisionActual.setText(derechoEmisionActual);
	}

	public void setDescuentoUnidadAdicionalActual(String descuentoUnidadAdicionalActual) {
		this.campoDescuentoUnidadAdicionalActual.setText(descuentoUnidadAdicionalActual);
	}
	
	public void addTipoCobertura(TipoCobertura tipoCobertura) {
		this.seleccionTipoCobertura.addItem(tipoCobertura);
	}

	public void addMarca(Marca marca) {
		this.seleccionMarca.addItem(marca);
	}

	public void addModelo(Modelo modelo) {
		this.seleccionModelo.addItem(modelo);
	}

	public void addProvincia(Provincia provincia) {
		this.seleccionProvincia.addItem(provincia);
	}

	public void addCiudad(Ciudad ciudad) {
		this.seleccionCiudad.addItem(ciudad);
	}
}
