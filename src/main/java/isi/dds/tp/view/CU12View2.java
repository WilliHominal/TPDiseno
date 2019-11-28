package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import isi.dds.tp.gestor.GestorTema;

public class CU12View2 extends JPanel{
	private static final long serialVersionUID = 8114763541940589488L;
	
	private GestorTema tema = GestorTema.get();

	private JLabel lMontoAbonado = new JLabel("Monto abonado por el cliente:");
	private JLabel lVuelto = new JLabel("Vuelto a entregar:");
	
	private JTextField campoMontoAbonado = new JTextField(16);
	private JTextField campoVuelto = new JTextField(16);
	
	private JButton btnCompletarPago = new JButton("COMPLETAR EL PAGO");
	private JButton btnCambiarMonto = new JButton("ELEGIR OTRO MONTO");
	private JButton btnCancelar = new JButton("CANCELAR PAGO");
	
	public CU12View2() {
		inicializarTema();
		inicializarComponentes();
		ubicarComponentes();
	}
	
	private void inicializarComponentes() {
		campoVuelto.setEnabled(false);
		btnCompletarPago.setPreferredSize(new Dimension(180,25));
		btnCambiarMonto.setPreferredSize(new Dimension(180,25));
		btnCancelar.setPreferredSize(new Dimension(180,25));
		btnCompletarPago.setEnabled(false);
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
		constraints.insets.set(5, 5, 35, 25);
		add(lMontoAbonado, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 25, 35, 5);
		add(campoMontoAbonado, constraints);
		///////////////////////////////////////////////////FILA 1
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 75, 25);
		add(lVuelto, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 25, 75, 5);
		add(campoVuelto, constraints);
		///////////////////////////////////////////////////FILA 2
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 25, 25);
		add(btnCompletarPago, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 25, 25, 5);
		add(btnCambiarMonto, constraints);
		///////////////////////////////////////////////////FILA 3
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 5, 5, 5);
		add(btnCancelar, constraints);
	}
	
	private void inicializarTema() {
		tema.setTema(this);
		tema.setTema(lMontoAbonado);
		tema.setTema(lVuelto);
		tema.setTema(campoMontoAbonado, true);
		tema.setTema(campoVuelto, false);
		tema.setTema(btnCompletarPago, false);
		tema.setTema(btnCancelar, true);
		tema.setTema(btnCambiarMonto, true);
	}

	public void addListenerBtn_CompletarPago(ActionListener listener) {
		btnCompletarPago.addActionListener(listener);
	}
	
	public void addListenerBtn_Cancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerBtn_CambiarMonto(ActionListener listener) {
		btnCambiarMonto.addActionListener(listener);
	}
	
	public void addListenerCampoMontoAbonado(KeyListener listener) {
	    campoMontoAbonado.addKeyListener(new KeyAdapter(){ 
	    	public void keyTyped(KeyEvent e){
				tema.setTema(campoMontoAbonado, true);
			}
	    });
	   campoMontoAbonado.addKeyListener(listener);
	    
	}

	public String getMontoAbonado() {
		return campoMontoAbonado.getText();
	}

	public void setVuelto(String vuelto) {
		campoVuelto.setText(vuelto);
	}
	
	public String getVuelto() {
		return campoVuelto.getText();
	}

	public void noValido(Boolean errorMonto) {
		if (errorMonto)
			tema.erroneo(campoMontoAbonado);
		else
			tema.setTema(campoMontoAbonado, true);
	}

	public void setMontoAbonado(String montoAbonado) {
		this.campoMontoAbonado.setText(montoAbonado);
	}

	public void habilitarBotonPago(boolean b) {
		tema.setTema(btnCompletarPago, b);
		this.btnCompletarPago.setEnabled(b);
	}

	public boolean pagoHabilitado() {
		return this.btnCompletarPago.isEnabled();
	}
	
}
