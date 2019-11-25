package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import isi.dds.tp.gestor.GestorTema;

public class CU18View  extends JPanel{
	private static final long serialVersionUID = 267584656685000175L;

	private GestorTema tema = GestorTema.get();
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	private JButton btnAceptar = new JButton("ACEPTAR");
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lnumeroPoliza = new JLabel("Número poliza:") ;
	private JLabel lnumeroPol = new JLabel("Número poliza:") ;
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombres = new JLabel("Nombres:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel lnumeroDocumento = new JLabel("Documento:");
	private JLabel lultimoPago  = new JLabel("Último pago");
	private JLabel lfechaPago = new JLabel("Fecha pago:");
	private JLabel lmonto = new JLabel("Monto:");

	private JTextField campoNumeroCliente = new JTextField(16);
	private JTextField campoNumeroPoliza = new JTextField(16);
	private JTextField campoNumeroPol = new JTextField(16);
	private JTextField campoTipoDocumento = new JTextField(16);
	private JTextField campoNumeroDocumento = new JTextField(16);
	private JTextField campoApellido = new JTextField(16);
	private JTextField campoNombres = new JTextField(16);
	private JTextField campofechaPago = new JTextField(8);
	private JTextField campoMonto = new JTextField(10);
	
	public CU18View() {
		inicializarComponentes();
		ubicarComponentes();	
		addListenerCampoNumeroPoliza();
	}

	private void inicializarComponentes() {			
		tema.setTema(this);
		tema.setTema(lnumeroPoliza);
		tema.setTema(campoNumeroPoliza, true);
		campoNumeroPoliza.setToolTipText("9999999999999");
		tema.setTema(btnBuscar, true);
		tema.setTema(btnCancelar, true);
		tema.setTema(btnAceptar, false);
		tema.setTema(lnumeroCliente);
		tema.setTema(lnumeroPol);
		tema.setTema(ltipoDocumento);
		tema.setTema(lnumeroDocumento);
		tema.setTema(lapellido);
		tema.setTema(lnombres);
		tema.setTema(lfechaPago);
		tema.setTemaSubrayado(lultimoPago);
		tema.setTema(lmonto);
		tema.setTema(campoNumeroCliente, false);
		tema.setTema(campoNumeroPol, false);
		tema.setTema(campoApellido, false);
		tema.setTema(campoTipoDocumento, false);
		tema.setTema(campoNumeroDocumento, false);
		tema.setTema(campoNombres, false);
		tema.setTema(campofechaPago, false);
		tema.setTema(campoMonto, false);
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 21, 10, 10);
		add(lnumeroPoliza, constraints);
		
		constraints.insets.set(10, 120, 10, 10);
		add(campoNumeroPoliza, constraints);	
		
		constraints.insets.set(10, 330, 10, 10);
		add(btnBuscar, constraints);
		
		constraints.gridy = 1;
		constraints.insets.set(10, 330, 20, 10);
		add(btnCancelar, constraints);	
		
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 9;
		constraints.insets.set(0, 0, 20, 0);
		add(new JLabel("______________________________________________________________________________"), constraints);
		

		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 21, 13, 5);
		add(lnumeroCliente, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campoNumeroCliente, constraints);
		
		constraints.gridy = 6;
		constraints.insets.set(10, 25, 13, 5);
		add(lnumeroPol, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campoNumeroPol, constraints);
		
		constraints.gridy = 7;
		constraints.insets.set(10, 60, 13, 5);
		add(lapellido, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campoApellido, constraints);
		
		constraints.gridy = 8;
		constraints.insets.set(10, 53, 13, 5);
		add(lnombres, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campoNombres, constraints); 
		
		constraints.gridy = 9;
		constraints.insets.set(10, 17, 13, 5);
		add(ltipoDocumento, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campoTipoDocumento, constraints);
		
		constraints.gridy = 10;
		constraints.insets.set(10, 42, 13, 5);
		add(lnumeroDocumento, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campoNumeroDocumento, constraints);
		
				
		constraints.gridy = 11;
		constraints.insets.set(10, 40, 5, 5);
		add(lultimoPago, constraints);
		
		
		constraints.gridy = 12;
		constraints.insets.set(10, 41, 13, 5);
		add(lfechaPago, constraints);
		
		constraints.insets.set(5, 120, 5, 5);
		add(campofechaPago, constraints);		
		
		constraints.insets.set(10, 235, 13, 5);
		add(lmonto, constraints);
		
		constraints.insets.set(5, 280, 5, 5);
		add(campoMonto, constraints);
		
		constraints.gridy = 13;
		constraints.insets.set(40, 120, 5, 5);
		add(btnAceptar, constraints);	
	}
	
	public void addListenerBtnBuscar(ActionListener listener) {
		btnBuscar.addActionListener(listener);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerBtnAceptar(ActionListener listener) {
		btnAceptar.addActionListener(listener);
	}
	
	public void addListenerCampoNumeroPoliza() {
		campoNumeroPoliza.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				tema.setTema(campoNumeroPoliza, true);
				if(Character.isDigit(caracter) && campoNumeroPoliza.getText().length() < 13){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
				}
			}
		}); 
	}
	
	
	public void componentesAlObtenerPoliza() {
		tema.setTema(btnAceptar, true);
		tema.setTema(campoNumeroCliente, false);
		tema.setTema(campoApellido, false);
		tema.setTema(campoTipoDocumento, false);
		tema.setTema(campoNumeroDocumento, false);
		tema.setTema(campoNombres, false);
		tema.setTema(campofechaPago, false);
		tema.setTema(campoMonto, false);
		tema.setTema(btnAceptar, true);
	}
	
	
	public void setNumeroCliente(String numeroCliente) {
		this.campoNumeroCliente.setText(numeroCliente);
	}

	public void setNumeroPoliza(String numeroPoliza) {
		this.campoNumeroPol.setText(numeroPoliza);
	}
	public String getNumeroPoliza() {
		return campoNumeroPoliza.getText();
	}
	
	public void setApellido(String apellido) {
		this.campoApellido.setText(apellido);
	}

	public void setNombre(String nombre) {
		this.campoNombres.setText(nombre);
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.campoTipoDocumento.setText(tipoDocumento);
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.campoNumeroDocumento.setText(numeroDocumento);
	}
	
	public void setfechaPago(String fecha) {
		this.campofechaPago.setText(fecha);
	}
	public void setMonto(String monto) {
		this.campoMonto.setText(monto);
	}
	
	public void noValido(Boolean numeroPoliza) {
		if(numeroPoliza) {
			tema.erroneo(campoNumeroPoliza);
		}
	}
	
}
