package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import isi.dds.tp.gestor.GestorTema;

public class CU12View1 extends JPanel{
	private static final long serialVersionUID = 4146373067631227216L;
	
	private GestorTema tema = GestorTema.get();
	
	private JLabel lTitularSeguro = new JLabel("Titular del seguro");
	private JLabel lNumeroCliente = new JLabel("Número cliente:");
	private JLabel lApellido = new JLabel("Apellido:");
	private JLabel lNombres = new JLabel("Nombres:");
	private JLabel lNumeroPoliza = new JLabel("Número de póliza:");
	private JLabel lVigencia = new JLabel("Vigencia");
	private JLabel lInicioVigencia = new JLabel("Inicio:");
	private JLabel lFinVigencia = new JLabel("Fin:");
	private JLabel lDatosVehiculo = new JLabel("Datos del vehículo");
	private JLabel lMarca = new JLabel("Marca:");
	private JLabel lModelo = new JLabel("Modelo:");
	private JLabel lPatente = new JLabel("Patente:");
	private JLabel lCuotasPendientes = new JLabel("Cuotas pendientes de pago");
	private JLabel lValorOriginal = new JLabel("Valor original");
	private JLabel lValorActual = new JLabel("Valor actual");
	private JLabel lImportesParciales = new JLabel("Importes parciales:");
	private JLabel lImportesTotales = new JLabel("Importes totales:");
	
	private JTextField campoNumeroCliente = new JTextField(12);
	private JTextField campoApellido = new JTextField(12);
	private JTextField campoNombres = new JTextField(12);
	private JTextField campoNumeroPoliza = new JTextField(12);
	private JTextField campoMarca = new JTextField(12);
	private JTextField campoModelo= new JTextField(12);
	private JTextField campoPatente = new JTextField(12);
	private JTextField campoCuotaOriginal1 = new JTextField(12);
	private JTextField campoCuotaOriginal2 = new JTextField(12);
	private JTextField campoCuotaOriginal3 = new JTextField(12);
	private JTextField campoCuotaOriginal4 = new JTextField(12);
	private JTextField campoCuotaOriginal5 = new JTextField(12);
	private JTextField campoCuotaOriginal6 = new JTextField(12);
	private JTextField campoCuotaActual1 = new JTextField(12);
	private JTextField campoCuotaActual2 = new JTextField(12);
	private JTextField campoCuotaActual3 = new JTextField(12);
	private JTextField campoCuotaActual4 = new JTextField(12);
	private JTextField campoCuotaActual5 = new JTextField(12);
	private JTextField campoCuotaActual6 = new JTextField(12);
	private JTextField campoImportesParciales = new JTextField(12);
	private JTextField campoImportesTotales = new JTextField(12);
	private JTextField campoInicioVigencia = new JTextField(12);
	private JTextField campoFinVigencia = new JTextField(12);
	
	private JButton btnConfirmarPago = new JButton("CONFIRMAR PAGOS");
	private JButton btnCancelar = new JButton("CANCELAR");
	private JButton btnBuscarPoliza = new JButton("BUSCAR PÓLIZA");
	
	private JCheckBox cbCuotaN1 = new JCheckBox("Número 1:");
	private JCheckBox cbCuotaN2 = new JCheckBox("Número 2:");
	private JCheckBox cbCuotaN3 = new JCheckBox("Número 3:");
	private JCheckBox cbCuotaN4 = new JCheckBox("Número 4:");
	private JCheckBox cbCuotaN5 = new JCheckBox("Número 5:");
	private JCheckBox cbCuotaN6 = new JCheckBox("Número 6:");
	
	public CU12View1() {
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
	}

	private void inicializarComponentes() {
		campoNumeroCliente.setEnabled(false);
		campoApellido.setEnabled(false);
		campoNombres.setEnabled(false);
		campoNumeroPoliza.setEnabled(false);
		campoMarca.setEnabled(false);
		campoModelo.setEnabled(false);
		campoPatente.setEnabled(false);
		campoCuotaOriginal1.setEnabled(false);
		campoCuotaOriginal2.setEnabled(false);
		campoCuotaOriginal3.setEnabled(false);
		campoCuotaOriginal4.setEnabled(false);
		campoCuotaOriginal5.setEnabled(false);
		campoCuotaOriginal6.setEnabled(false);
		campoCuotaActual1.setEnabled(false);
		campoCuotaActual2.setEnabled(false);
		campoCuotaActual3.setEnabled(false);
		campoCuotaActual4.setEnabled(false);
		campoCuotaActual5.setEnabled(false);
		campoCuotaActual6.setEnabled(false);
		campoImportesParciales.setEnabled(false);
		campoImportesTotales.setEnabled(false);
		campoInicioVigencia.setEnabled(false);
		campoFinVigencia.setEnabled(false);
		cbCuotaN1.setEnabled(false);
		cbCuotaN2.setEnabled(false);
		cbCuotaN3.setEnabled(false);
		cbCuotaN4.setEnabled(false);
		cbCuotaN5.setEnabled(false);
		cbCuotaN6.setEnabled(false);
	}
	
	private void inicializarTema() {	
		tema.setTema(this);
		tema.setTemaNegritaSubrayado(lTitularSeguro);
		tema.setTema(lNumeroCliente);
		tema.setTema(lApellido);
		tema.setTema(lNombres);
		tema.setTema(lNumeroPoliza);
		tema.setTemaNegritaSubrayado(lVigencia);
		tema.setTema(lInicioVigencia);
		tema.setTema(lFinVigencia);
		tema.setTema(lMarca);
		tema.setTema(lModelo);
		tema.setTema(lPatente);
		tema.setTemaNegritaSubrayado(lDatosVehiculo);
		tema.setTemaNegritaSubrayado(lCuotasPendientes);
		tema.setTema(lValorOriginal);
		tema.setTema(lValorActual);
		tema.setTemaNegritaSubrayado(lImportesParciales);
		tema.setTemaNegritaSubrayado(lImportesTotales);
		tema.setTema(campoNumeroCliente, false);
		tema.setTema(campoApellido, false);
		tema.setTema(campoNombres, false);
		tema.setTema(campoNumeroPoliza, false);
		tema.setTema(campoMarca, false);
		tema.setTema(campoModelo, false);
		tema.setTema(campoPatente, false);
		tema.setTema(campoCuotaOriginal1, false);
		tema.setTema(campoCuotaOriginal2, false);
		tema.setTema(campoCuotaOriginal3, false);
		tema.setTema(campoCuotaOriginal4, false);
		tema.setTema(campoCuotaOriginal5, false);
		tema.setTema(campoCuotaOriginal6, false);
		tema.setTema(campoCuotaActual1, false);
		tema.setTema(campoCuotaActual2, false);
		tema.setTema(campoCuotaActual3, false);
		tema.setTema(campoCuotaActual4, false);
		tema.setTema(campoCuotaActual5, false);
		tema.setTema(campoCuotaActual6, false);
		tema.setTema(campoImportesParciales, false);
		tema.setTema(campoImportesTotales, false);
		tema.setTema(campoInicioVigencia, false);
		tema.setTema(campoFinVigencia, false);
		tema.setTema(cbCuotaN1, null);
		tema.setTema(cbCuotaN2, null);
		tema.setTema(cbCuotaN3, null);
		tema.setTema(cbCuotaN4, null);
		tema.setTema(cbCuotaN5, null);
		tema.setTema(cbCuotaN6, null);
		tema.setTema(btnConfirmarPago, false);
		tema.setTema(btnCancelar, true);
		tema.setTema(btnBuscarPoliza, true);
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		///////////////////////////////////////////////////FILA 0
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 25, 20);
		add(btnBuscarPoliza, constraints);
		///////////////////////////////////////////////////FILA 1
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 13, 20);
		add(lTitularSeguro, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 0, 13, 20);
		add(lCuotasPendientes, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 10, 13, 20);
		add(lValorOriginal, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 10, 13, 25);
		add(lValorActual, constraints);
		///////////////////////////////////////////////////FILA 2
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 8, 20);
		add(lNumeroCliente, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 8, 150);
		add(campoNumeroCliente, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(cbCuotaN1, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoCuotaOriginal1, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 25);
		add(campoCuotaActual1, constraints);
		///////////////////////////////////////////////////FILA 3
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 8, 20);
		add(lApellido, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 8, 150);
		add(campoApellido, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(cbCuotaN2, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoCuotaOriginal2, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 25);
		add(campoCuotaActual2, constraints);
		///////////////////////////////////////////////////FILA 4
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 8, 20);
		add(lNombres, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 8, 150);
		add(campoNombres, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(cbCuotaN3, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoCuotaOriginal3, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 25);
		add(campoCuotaActual3, constraints);
		///////////////////////////////////////////////////FILA 5
		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 8, 20);
		add(lNumeroPoliza, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 8, 150);
		add(campoNumeroPoliza, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(cbCuotaN4, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoCuotaOriginal4, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 25);
		add(campoCuotaActual4, constraints);
		///////////////////////////////////////////////////FILA 6
		constraints.gridy = 6;
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(cbCuotaN5, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoCuotaOriginal5, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 25);
		add(campoCuotaActual5, constraints);
		///////////////////////////////////////////////////FILA 7
		constraints.gridy = 7;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 8, 20);
		add(lVigencia, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(cbCuotaN6, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoCuotaOriginal6, constraints);
		
		constraints.gridx = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 25);
		add(campoCuotaActual6, constraints);
		///////////////////////////////////////////////////FILA 8
		constraints.gridy = 8;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 13, 20);
		add(lInicioVigencia, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 13, 150);
		add(campoInicioVigencia, constraints);
		///////////////////////////////////////////////////FILA 9
		constraints.gridy = 9;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 12, 20);
		add(lFinVigencia, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 12, 150);
		add(campoFinVigencia, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 12, 20);
		add(lImportesParciales, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 12, 20);
		add(campoImportesParciales, constraints);
		///////////////////////////////////////////////////FILA 10
		constraints.gridy = 10;
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 0, 8, 20);
		add(lImportesTotales, constraints);
		
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0, 10, 8, 20);
		add(campoImportesTotales, constraints);
		///////////////////////////////////////////////////FILA 11
		constraints.gridy = 11;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 8, 20);
		add(lDatosVehiculo, constraints);
		///////////////////////////////////////////////////FILA 12
		constraints.gridy = 12;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 12, 20);
		add(lMarca, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 12, 150);
		add(campoMarca, constraints);
		///////////////////////////////////////////////////FILA 13
		constraints.gridy = 13;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 11, 20);
		add(lModelo, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 11, 150);
		add(campoModelo, constraints);
		///////////////////////////////////////////////////FILA 14
		constraints.gridy = 14;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0, 25, 30, 20);
		add(lPatente, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 30, 150);
		add(campoPatente, constraints);
		///////////////////////////////////////////////////FILA 15
		constraints.gridy = 15;
		constraints.gridx = 0;
		constraints.gridwidth = 10;
		constraints.insets.set(0, 655, 0, 5);
		add(btnConfirmarPago, constraints);
		
		constraints.insets.set(0, 830, 0, 5);
		add(btnCancelar, constraints);
	}
	
	public void addListenerBtn_BuscarPoliza(ActionListener listener) {
		btnBuscarPoliza.addActionListener(listener);
	}

	public void addListenerBtn_ConfirmarPago(ActionListener listener) {
		btnConfirmarPago.addActionListener(listener);
	}
	
	public void addListenerBtn_Cancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void setNumeroCliente(String numeroCliente) {
		this.campoNumeroCliente.setText(numeroCliente);
	}

	public void setApellido(String apellido) {
		this.campoApellido.setText(apellido);
	}

	public void setNombre(String nombre) {
		this.campoNombres.setText(nombre);
	}
	
	public void setNumeroPoliza(String numeroPoliza) {
		this.campoNumeroPoliza.setText(numeroPoliza);
	}
	
	public void setInicioVigencia(String fechaInicio) {
		this.campoInicioVigencia.setText(fechaInicio);
	}
	
	public void setFinVigencia(String fechaFin) {
		this.campoFinVigencia.setText(fechaFin);
	}
	
	public void setMarca(String marca) {
		this.campoMarca.setText(marca);
	}
	
	public void setModelo(String modelo) {
		this.campoModelo.setText(modelo);
	}
	
	public void setPatente(String patente) {
		this.campoPatente.setText(patente);
	}
	
	public void setCuotaOriginal(int i, String cuotaOriginal) {
		switch (i) {
			case 0: this.campoCuotaOriginal1.setText(cuotaOriginal);break;
			case 1: this.campoCuotaOriginal2.setText(cuotaOriginal);break;
			case 2: this.campoCuotaOriginal3.setText(cuotaOriginal);break;
			case 3: this.campoCuotaOriginal4.setText(cuotaOriginal);break;
			case 4: this.campoCuotaOriginal5.setText(cuotaOriginal);break;
			case 5: this.campoCuotaOriginal6.setText(cuotaOriginal);break;
		}
	}
	
	public void setCuotaActual(int i, String cuotaActual) {
		switch (i) {
			case 0: this.campoCuotaActual1.setText(cuotaActual);break;
			case 1: this.campoCuotaActual2.setText(cuotaActual);break;
			case 2: this.campoCuotaActual3.setText(cuotaActual);break;
			case 3: this.campoCuotaActual4.setText(cuotaActual);break;
			case 4: this.campoCuotaActual5.setText(cuotaActual);break;
			case 5: this.campoCuotaActual6.setText(cuotaActual);break;
		}
	}
	
	public void setImportesParciales(String importesParciales) {
		this.campoImportesParciales.setText(importesParciales);
	}
	public void setImportesTotales(String importesTotales) {
		this.campoImportesTotales.setText(importesTotales);
	}
	
	public void deshabilitarCuota1 () {
		this.cbCuotaN1.setEnabled(false);
	}
	public void deshabilitarCuota2 () {
		this.cbCuotaN2.setEnabled(false);
	}
	public void deshabilitarCuota3 () {
		this.cbCuotaN3.setEnabled(false);
	}
	public void deshabilitarCuota4 () {
		this.cbCuotaN4.setEnabled(false);
	}
	public void deshabilitarCuota5 () {
		this.cbCuotaN5.setEnabled(false);
	}
	public void deshabilitarsCuota6 () {
		this.cbCuotaN6.setEnabled(false);
	}
	
	public void ubicarUnaCuota(Boolean semestral) {
		if(semestral) {
			cbCuotaN1.setText("Número 1:");
			cuotaVisible(0, true);
			
			for (int i=1; i<6; i++)
				cuotaVisible(i,false);
		}
		else {
			cbCuotaN1.setText("Número 6:");
			cuotaVisible(0, true);
			
			for (int i=1; i<6; i++)
				cuotaVisible(i,false);
		}		
	}
	
	public void ubicarDosCuotas() {
		cbCuotaN1.setText("Número 5:");
		cbCuotaN2.setText("Número 6:");
		for (int i=0; i<2; i++)
			cuotaVisible(i, true);
		
		for (int i=2; i<6; i++)
			cuotaVisible(i,false);
	}
	public void ubicarTresCuotas() {
		cbCuotaN1.setText("Número 4:");
		cbCuotaN2.setText("Número 5:");
		cbCuotaN3.setText("Número 6:");
		for (int i=0; i<3; i++)
			cuotaVisible(i, true);
		
		for (int i=3; i<6; i++)
			cuotaVisible(i,false);
	}
	public void ubicarCuatroCuotas() {
		cbCuotaN1.setText("Número 3:");
		cbCuotaN2.setText("Número 4:");
		cbCuotaN3.setText("Número 5:");
		cbCuotaN4.setText("Número 6:");
		for (int i=0; i<4; i++)
			cuotaVisible(i, true);
		
		for (int i=4; i<6; i++)
			cuotaVisible(i,false);
	}
	public void ubicarCincoCuotas() {
		cbCuotaN1.setText("Número 2:");
		cbCuotaN2.setText("Número 3:");
		cbCuotaN3.setText("Número 4:");
		cbCuotaN4.setText("Número 5:");
		cbCuotaN5.setText("Número 6:");
		for (int i=0; i<5; i++)
			cuotaVisible(i, true);
		
		for (int i=5; i<6; i++)
			cuotaVisible(i, false);
	}
	public void ubicarSeisCuotas() {
		cbCuotaN1.setText("Número 1:");
		cbCuotaN2.setText("Número 2:");
		cbCuotaN3.setText("Número 3:");
		cbCuotaN4.setText("Número 4:");
		cbCuotaN5.setText("Número 5:");
		cbCuotaN6.setText("Número 6:");
		for (int i=0; i<6; i++)
			cuotaVisible(i, true);
	}
	
	public Integer cantidadCuotasDisponibles() {
		if (cbCuotaN6.isVisible()) {
			return 6;
		} else if (cbCuotaN5.isVisible()) {
			return 5;
		} else if (cbCuotaN4.isVisible()) {
			return 4;
		} else if (cbCuotaN3.isVisible()) {
			return 3;
		} else if (cbCuotaN2.isVisible()) {
			return 2;
		} else if (cbCuotaN1.isVisible()) {
			return 1;
		} else {
			return 0;
		}
	}

	public void habilitarBotonConfirmarPago() {
		this.btnConfirmarPago.setEnabled(true);
		tema.setTema(btnConfirmarPago, true);
	}

	public boolean getEstadoCheckBoxCuota(int numCuota) {
		switch(numCuota) {
		case 1: return cbCuotaN1.isSelected();
		case 2: return cbCuotaN2.isSelected();
		case 3: return cbCuotaN3.isSelected();
		case 4: return cbCuotaN4.isSelected();
		case 5: return cbCuotaN5.isSelected();
		case 6: return cbCuotaN6.isSelected();
		}
		return false;
	}
	
	public String getNumeroPoliza() {
		return this.campoNumeroPoliza.getText();
	}
	
	public void noValido(Boolean cantidad, Boolean seleccion) {
		tema.setTema(cbCuotaN1, null);
		tema.setTema(cbCuotaN2, null);
		tema.setTema(cbCuotaN3, null);
		tema.setTema(cbCuotaN4, null);
		tema.setTema(cbCuotaN5, null);
		tema.setTema(cbCuotaN6, null);
		
		if(cantidad) {
			if (cbCuotaN1.isEnabled() && !cbCuotaN1.isSelected())
				tema.erroneo(cbCuotaN1);
			if (cbCuotaN2.isEnabled() && !cbCuotaN2.isSelected())
				tema.erroneo(cbCuotaN2);
			if (cbCuotaN3.isEnabled() && !cbCuotaN3.isSelected())
				tema.erroneo(cbCuotaN3);
			if (cbCuotaN4.isEnabled() && !cbCuotaN4.isSelected())
				tema.erroneo(cbCuotaN4);
			if (cbCuotaN5.isEnabled() && !cbCuotaN5.isSelected())
				tema.erroneo(cbCuotaN5);
			if (cbCuotaN6.isEnabled() && !cbCuotaN6.isSelected())
				tema.erroneo(cbCuotaN6);
		}
		if (seleccion) {
			if (cbCuotaN6.isEnabled() && cbCuotaN6.isSelected()) {
				if (!cbCuotaN5.isSelected())
					tema.erroneo(cbCuotaN5);
				if (!cbCuotaN4.isSelected())
					tema.erroneo(cbCuotaN4);
				if (!cbCuotaN3.isSelected())
					tema.erroneo(cbCuotaN3);
				if (!cbCuotaN2.isSelected())
					tema.erroneo(cbCuotaN2);
				if (!cbCuotaN1.isSelected())
					tema.erroneo(cbCuotaN1);
			} else {
				if (cbCuotaN5.isEnabled() && cbCuotaN5.isSelected()) {
					if (!cbCuotaN4.isSelected())
						tema.erroneo(cbCuotaN4);
					if (!cbCuotaN3.isSelected())
						tema.erroneo(cbCuotaN3);
					if (!cbCuotaN2.isSelected())
						tema.erroneo(cbCuotaN2);
					if (!cbCuotaN1.isSelected())
						tema.erroneo(cbCuotaN1);
				} else {
					if (cbCuotaN4.isEnabled() && cbCuotaN4.isSelected()) {
						if (!cbCuotaN3.isSelected())
							tema.erroneo(cbCuotaN3);
						if (!cbCuotaN2.isSelected())
							tema.erroneo(cbCuotaN2);
						if (!cbCuotaN1.isSelected())
							tema.erroneo(cbCuotaN1);
					} else {
						if (cbCuotaN3.isEnabled() && cbCuotaN3.isSelected()) {
							if (!cbCuotaN2.isSelected())
								tema.erroneo(cbCuotaN2);
							if (!cbCuotaN1.isSelected())
								tema.erroneo(cbCuotaN1);
						} else {
							if (cbCuotaN2.isEnabled() && cbCuotaN2.isSelected())
								if (!cbCuotaN1.isSelected())
									tema.erroneo(cbCuotaN1);
						}
					}
				}
			}
		}
	}

	public void habilitarSeleccionCuotas() {
		this.cbCuotaN1.setEnabled(true);
		this.cbCuotaN2.setEnabled(true);
		this.cbCuotaN3.setEnabled(true);
		this.cbCuotaN4.setEnabled(true);
		this.cbCuotaN5.setEnabled(true);
		this.cbCuotaN6.setEnabled(true);
	}
	
	public void cuotaVisible (int cuota, boolean visibilidad) {
		switch (cuota) {
		case 0: this.cbCuotaN1.setVisible(visibilidad); campoCuotaActual1.setVisible(visibilidad); campoCuotaOriginal1.setVisible(visibilidad); break;
		case 1: this.cbCuotaN2.setVisible(visibilidad); campoCuotaActual2.setVisible(visibilidad); campoCuotaOriginal2.setVisible(visibilidad); break;
		case 2: this.cbCuotaN3.setVisible(visibilidad); campoCuotaActual3.setVisible(visibilidad); campoCuotaOriginal3.setVisible(visibilidad); break;
		case 3: this.cbCuotaN4.setVisible(visibilidad); campoCuotaActual4.setVisible(visibilidad); campoCuotaOriginal4.setVisible(visibilidad); break;
		case 4: this.cbCuotaN5.setVisible(visibilidad); campoCuotaActual5.setVisible(visibilidad); campoCuotaOriginal5.setVisible(visibilidad); break;
		case 5: this.cbCuotaN6.setVisible(visibilidad); campoCuotaActual6.setVisible(visibilidad); campoCuotaOriginal6.setVisible(visibilidad); break;
		}
	}
	
	public void addListenerSeleccionCuota1(ActionListener listener) {
		cbCuotaN1.addActionListener (a -> {
			noValido(false, false);
		});	
		cbCuotaN1.addActionListener (listener);		
	}
	public void addListenerSeleccionCuota2(ActionListener listener) {
		cbCuotaN2.addActionListener (a -> {
			noValido(false, false);
		});	
		cbCuotaN2.addActionListener (listener);		
	}
	public void addListenerSeleccionCuota3(ActionListener listener) {
		cbCuotaN3.addActionListener (a -> {
			noValido(false, false);
		});	
		cbCuotaN3.addActionListener (listener);		
	}
	public void addListenerSeleccionCuota4(ActionListener listener) {
		cbCuotaN4.addActionListener (a -> {
			noValido(false, false);
		});	
		cbCuotaN4.addActionListener (listener);		
	}
	public void addListenerSeleccionCuota5(ActionListener listener) {
		cbCuotaN5.addActionListener (a -> {
			noValido(false, false);
		});	
		cbCuotaN5.addActionListener(listener);		
	}
	public void addListenerSeleccionCuota6(ActionListener listener) {
		cbCuotaN6.addActionListener (a -> {
			noValido(false, false);
		});	
		cbCuotaN6.addActionListener (listener);		
	}

	public String getCuotaActual(int i) {
		switch (i) {
			case 0:return this.campoCuotaActual1.getText();
			case 1:return this.campoCuotaActual2.getText();
			case 2:return this.campoCuotaActual3.getText();
			case 3:return this.campoCuotaActual4.getText();
			case 4:return this.campoCuotaActual5.getText();
			case 5:return this.campoCuotaActual6.getText();
		}
		return null;
	}
	
	public String getImportesParciales() {
		return this.campoImportesParciales.getText();
	}

	public int cantidadCuotasSeleccionadas() {
		int cantidad = 0;
		if (cbCuotaN1.isSelected())
			cantidad++;
		if (cbCuotaN2.isSelected())
			cantidad++;
		if (cbCuotaN3.isSelected())
			cantidad++;
		if (cbCuotaN4.isSelected())
			cantidad++;
		if (cbCuotaN5.isSelected())
			cantidad++;
		if (cbCuotaN6.isSelected())
			cantidad++;
		
		return cantidad;
	}
}
