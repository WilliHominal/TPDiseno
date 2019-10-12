package isi.dds.tp.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

@SuppressWarnings("serial")
public class CU01_DeclararHijo extends JFrame  {
	
	private Object[] tema = {new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK, new Color(71,71,71), Color.BLACK, new Font("Open Sans", Font.PLAIN, 13)};
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetraBloqueado, colorLetra;
	private Font letra;
	private JPanel panel;
	
	private	CU01_AltaPolizaV1 alta;
	private List hijo;
	
	private JLabel lfechaNacimiento = new JLabel("Fecha nacimiento*");
	private JLabel lsexo = new JLabel("Sexo*");
	private JLabel lestadocivil = new JLabel("Estado civil*");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatorios");

	private JDateChooser dcFechaNacimiento = new JDateChooser();
	
	private JComboBox<String> cmbSexo = new JComboBox<String>();
	private JComboBox<String> cmbEstadoCivil = new JComboBox<String>();

	private JButton btnAgregarHijo = new JButton("AGREGAR HIJO");
	private JButton btnCancelar = new JButton("CANCELAR");

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU01_DeclararHijo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public CU01_DeclararHijo(Object[] tema, CU01_AltaPolizaV1 alta, List hijo) {
	
        this.alta = alta;
		this.tema = tema;
		this.hijo = hijo;
		
		new CU01_DeclararHijo();
		

	}
	
	//void app
	public CU01_DeclararHijo() {
		
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
		add(cmbSexo, constraints);
		
		
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(15, 5, 3, 80);
		add(lestadocivil, constraints);
		
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 10, 80);
		add(cmbEstadoCivil, constraints);
		
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
		cmbEstadoCivil.setPreferredSize(new Dimension(105, 25));
		cmbSexo.setPreferredSize(new Dimension(105, 25));
		
		cmbSexo.addItem("Selecionar");
		cmbSexo.addItem("Femenino");
		cmbSexo.addItem("Masculino");
		
		cmbEstadoCivil.addItem("Selecionar");
		cmbEstadoCivil.addItem("Soltero");
		cmbEstadoCivil.addItem("Casado");
		cmbEstadoCivil.addItem("Viudo");
		cmbEstadoCivil.addItem("Divorciado");
		cmbEstadoCivil.addItem("Separado");
		cmbEstadoCivil.addItem("En relación");

		dcFechaNacimiento.setPreferredSize(new Dimension(105, 25));
		
		btnAgregarHijo.setPreferredSize(new Dimension(130, 25));
		btnCancelar.setPreferredSize(new Dimension(130, 25));
		
	}
	
	private void inicializarTema() {		
		
		colorBoton = (Color) tema[0];
		colorFondoPantalla = (Color) tema[1];
		colorFondoTexto = (Color)tema[2];
		borde = (Color)tema[3];
		colorLetraBloqueado = (Color) tema[4];
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
		
		cmbSexo.setBackground(colorFondoTexto);
		cmbSexo.setFont(letra);
		cmbEstadoCivil.setBackground(colorFondoTexto);
		cmbEstadoCivil.setFont(letra);

		btnAgregarHijo.setBackground(colorBoton);
		btnAgregarHijo.setFont(letra);
		btnCancelar.setBackground(colorBoton);
		btnCancelar.setFont(letra);
	}

	private void comportamiento() {
		
		cmbSexo.addActionListener (a -> {
			cmbSexo.setForeground(colorLetra);
			if(cmbSexo.getSelectedIndex() == 1) {
				
				cmbEstadoCivil.setEnabled(false);
				
				int index = cmbEstadoCivil.getSelectedIndex();
				
				cmbEstadoCivil.removeAllItems();
				
				cmbEstadoCivil.addItem("Selecionar");
				cmbEstadoCivil.addItem("Soltera");
				cmbEstadoCivil.addItem("Casada");
				cmbEstadoCivil.addItem("Viuda");
				cmbEstadoCivil.addItem("Divorciada");
				cmbEstadoCivil.addItem("Separada");
				cmbEstadoCivil.addItem("En relación");
				
				cmbEstadoCivil.setSelectedIndex(index);
				
				cmbEstadoCivil.setEnabled(true);
			}
		});
		
		cmbEstadoCivil.addActionListener (a -> {
			cmbEstadoCivil.setForeground(colorLetra);
		});
		
		btnAgregarHijo.addActionListener(a -> {
			
			if (cmbSexo.getSelectedIndex() == 0) {
				cmbSexo.setForeground(Color.red);
				JOptionPane.showConfirmDialog(cmbSexo, "Seleccione un sexo.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (cmbEstadoCivil.getSelectedIndex() == 0) {
				cmbEstadoCivil.setForeground(Color.red);
				JOptionPane.showConfirmDialog(cmbSexo, "Seleccione un estado civil.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
		});
		
		btnCancelar.addActionListener(a -> {
			
			//alta.componentesParaPoliza(true);
			this.setVisible(false);
			
		});
	}
}
