package isi.dds.tp.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import isi.dds.tp.gestor.GestorTema;
@SuppressWarnings("unused")
public class CU12View2 extends JPanel{
	private static final long serialVersionUID = 8114763541940589488L;
	
	private GestorTema tema = GestorTema.get();

	private JLabel lMontoAbonado = new JLabel("Monto abonado por el cliente:");
	private JLabel lVuelto = new JLabel("Vuelto a entregar:");
	
	private JTextField campoMontoAbonado = new JTextField(10);
	private JTextField campoVuelto = new JTextField(10);
	
	private JButton btnCompletarPago = new JButton("COMPLETAR EL PAGO");
	private JButton btnCambiarMonto = new JButton("ELEGIR OTRO MONTO");
	private JButton btnCancelar = new JButton("CANCELAR PAGO");
	
	public CU12View2() {
		
	}
}
