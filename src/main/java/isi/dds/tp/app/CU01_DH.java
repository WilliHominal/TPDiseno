package isi.dds.tp.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

@SuppressWarnings("serial")
public class CU01_DH extends JFrame  {
	
	public final static class DeclararHijoAbierto {
	    private DeclararHijoAbierto(){}
	    public static Boolean declararHijoAbierto = false;
	    public static List<Object> hijo;
	}
	
	private Object[] tema = {new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK, new Color(71,71,71), Color.BLACK, new Font("Open Sans", Font.PLAIN, 13)};
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, colorLetra;
	private Font letra;
	private JPanel panel;
	
	private JLabel lfechaNacimiento = new JLabel("Fecha nacimiento*");
	private JLabel lsexo = new JLabel("Sexo*");
	private JLabel lestadocivil = new JLabel("Estado civil*");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatorios");

	private JDateChooser dcFechaNacimiento = new JDateChooser();
	
	private JComboBox<String> seleccionSexo = new JComboBox<String>();
	private JComboBox<String> seleccionEstadoCivil = new JComboBox<String>();

	private JButton btnAgregarHijo = new JButton("AGREGAR HIJO");
	private JButton btnCancelar = new JButton("CANCELAR");

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU01_DH();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CU01_DH(Object[] tema) {
		this.tema = tema;
		
		if(DeclararHijoAbierto.declararHijoAbierto == false) {
			DeclararHijoAbierto.declararHijoAbierto = true;
			DeclararHijoAbierto.hijo = new ArrayList<Object>();
		}
		
		new CU01_DH();
	}
	
	//void app
	public CU01_DH() {
		
		panel = new JPanel();
		this.setContentPane(panel);
		
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
	
		comportamiento();
		
		setResizable(false);
		setBounds(0, 0, 420, 250);
		setLocationRelativeTo(null);
		setTitle("Dar de alta póliza: AGREGAR DATOS HIJOS");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		//si es true todo
		//poliza.getHijosDeclarado().add(hijo);
		
	}

	private void ubicarComponentes() {
		panel.setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 5, 3, 80);
		add(lfechaNacimiento, constraints);
		
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 5, 80);
		add(dcFechaNacimiento, constraints);
		
		
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(15, 5, 3, 80);
		add(lsexo, constraints);
		
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 5, 80);
		add(seleccionSexo, constraints);
		
		
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(15, 5, 3, 80);
		add(lestadocivil, constraints);
		
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 10, 80);
		add(seleccionEstadoCivil, constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 3, 5);
		add(btnAgregarHijo, constraints);
		

		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 3, 5);
		add(btnCancelar, constraints);
		
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		constraints.insets.set(5, 5, 0, 5);
		add(ldatosObligatorios, constraints);
	
	}
	
	private void inicializarComponentes() {
		seleccionEstadoCivil.setPreferredSize(new Dimension(105, 25));
		seleccionSexo.setPreferredSize(new Dimension(105, 25));
		
		seleccionSexo.addItem("Selecionar");
		seleccionSexo.addItem("Femenino");
		seleccionSexo.addItem("Masculino");
		
		seleccionEstadoCivil.addItem("Selecionar");
		seleccionEstadoCivil.addItem("Soltero");
		seleccionEstadoCivil.addItem("Casado");
		seleccionEstadoCivil.addItem("Viudo");
		seleccionEstadoCivil.addItem("Divorciado");
		seleccionEstadoCivil.addItem("Separado");
		seleccionEstadoCivil.addItem("En relación");

		dcFechaNacimiento.setPreferredSize(new Dimension(105, 25));
		
		btnAgregarHijo.setPreferredSize(new Dimension(130, 25));
		btnCancelar.setPreferredSize(new Dimension(130, 25));
		
	}
	
	private void inicializarTema() {		
		
		colorBoton = (Color) tema[0];
		colorFondoPantalla = (Color) tema[1];
		colorFondoTexto = (Color)tema[2];
		colorLetra = (Color) tema[5];
		letra = (Font) tema[6];
		
		panel.setFont(letra);
		panel.setBackground(colorFondoPantalla);

		lfechaNacimiento.setFont(letra);
		lsexo.setFont(letra);
		lestadocivil.setFont(letra);
		ldatosObligatorios.setFont(new Font("Open Sans", Font.ITALIC, 9));

		((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setFont(letra);
		
		seleccionSexo.setBackground(colorFondoTexto);
		seleccionSexo.setFont(letra);
		seleccionEstadoCivil.setBackground(colorFondoTexto);
		seleccionEstadoCivil.setFont(letra);

		btnAgregarHijo.setBackground(colorBoton);
		btnAgregarHijo.setFont(letra);
		btnCancelar.setBackground(colorBoton);
		btnCancelar.setFont(letra);
	}

	private void comportamiento() {
		
		seleccionSexo.addActionListener (a -> {
			seleccionSexo.setForeground(colorLetra);
			if(seleccionSexo.getSelectedIndex() == 1) {
				
				seleccionEstadoCivil.setEnabled(false);
				
				int index = seleccionEstadoCivil.getSelectedIndex();
				
				seleccionEstadoCivil.removeAllItems();
				
				seleccionEstadoCivil.addItem("Selecionar");
				seleccionEstadoCivil.addItem("Soltera");
				seleccionEstadoCivil.addItem("Casada");
				seleccionEstadoCivil.addItem("Viuda");
				seleccionEstadoCivil.addItem("Divorciada");
				seleccionEstadoCivil.addItem("Separada");
				seleccionEstadoCivil.addItem("En relación");
				
				seleccionEstadoCivil.setSelectedIndex(index);
				
				seleccionEstadoCivil.setEnabled(true);
			}
		});
		
		seleccionEstadoCivil.addActionListener (a -> {
			seleccionEstadoCivil.setForeground(colorLetra);
		});
		
		btnAgregarHijo.addActionListener(a -> {
			
			if (seleccionSexo.getSelectedIndex() == 0) {
				seleccionSexo.setForeground(Color.red);
				JOptionPane.showConfirmDialog(seleccionSexo, "Seleccione un sexo.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (seleccionEstadoCivil.getSelectedIndex() == 0) {
				seleccionEstadoCivil.setForeground(Color.red);
				JOptionPane.showConfirmDialog(seleccionSexo, "Seleccione un estado civil.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			

			if(DeclararHijoAbierto.declararHijoAbierto == true) {
				DeclararHijoAbierto.declararHijoAbierto = false;
				//DeclararHijoAbierto.hijo.add(dcFechaNacimiento); //TODO agregar fecha
				DeclararHijoAbierto.hijo.add(seleccionSexo.getItemAt(seleccionSexo.getSelectedIndex()).strip());
				DeclararHijoAbierto.hijo.add(seleccionEstadoCivil.getItemAt(seleccionEstadoCivil.getSelectedIndex()).strip());
				
			}
			
		});
		
		btnCancelar.addActionListener(a -> {
			this.setVisible(false);
			if(DeclararHijoAbierto.declararHijoAbierto == true) {
				DeclararHijoAbierto.declararHijoAbierto = false;
			}
			//alta.componentesParaPoliza(true);
		});
	}
}
