package isi.dds.tp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import isi.dds.tp.gestor.GestorTema;

public class CU01View3 extends JFrame  {
	private static final long serialVersionUID = 6648717348390850354L;

	private JPanel panel =  new JPanel();
	private GestorTema tema = GestorTema.get();
	
	private JLabel lfechaNacimiento = new JLabel("Fecha nacimiento*");
	private JLabel lsexo = new JLabel("Sexo*");
	private JLabel lestadocivil = new JLabel("Estado civil*");
	private JLabel ldatosObligatorios = new JLabel("(*) datos obligatorios");

	private JDateChooser dcFechaNacimiento = new JDateChooser();
	
	private JComboBox<String> seleccionSexo = new JComboBox<String>();
	private JComboBox<String> seleccionEstadoCivil = new JComboBox<String>();

	private JButton btnAgregarHijo = new JButton("AGREGAR HIJO");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	public CU01View3() {
		ubicarComponentes();
		inicializarTema();
		addListenerSeleccionEstadoCivil();
		addListenerDcFechaNacimiento();
		setContentPane(panel);	
	}

	private void ubicarComponentes() {
		panel.setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(5, 5, 3, 65);
		panel.add(lfechaNacimiento, constraints);
		
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 5, 65);
		panel.add(dcFechaNacimiento, constraints);
		
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(15, 5, 3, 65);
		panel.add(lsexo, constraints);
		
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 5, 65);
		panel.add(seleccionSexo, constraints);
		
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(15, 5, 3, 65);
		panel.add(lestadocivil, constraints);
		
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 5, 10, 65);
		panel.add(seleccionEstadoCivil, constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 3, 5);
		panel.add(btnAgregarHijo, constraints);
		
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 3, 5);
		panel.add(btnCancelar, constraints);
		
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		constraints.insets.set(5, 5, 0, 5);
		panel.add(ldatosObligatorios, constraints);
	}
	
	private void inicializarTema() {		
		tema.setTema(panel);
		tema.setTema(lfechaNacimiento);
		tema.setTema(lsexo);
		tema.setTema(lestadocivil);
		tema.setTema(ldatosObligatorios);
		tema.setTema(dcFechaNacimiento, true);
		tema.setComboBoxChico(seleccionSexo, true);
		tema.setComboBoxChico(seleccionEstadoCivil, true);
		tema.setTema(btnAgregarHijo, true);
		tema.setTema(btnCancelar, true);
	}
	
	public void noValido(Boolean fechaError, Boolean sexoError, Boolean estadoCivilError) {
		if(fechaError) {
			tema.erroneo(((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()));
		}
		if(sexoError) {
			tema.erroneo(seleccionSexo);	
		}
		if(estadoCivilError) {
			tema.erroneo(seleccionEstadoCivil);
		}
	}
	
	public void addListenerSeleccionSexo(ActionListener listener) {
		seleccionSexo.addActionListener(listener);
		seleccionSexo.addActionListener (a -> {
			if(seleccionSexo.getSelectedIndex() > 0){
				tema.setComboBoxChico(seleccionSexo, true);
			}
		});
	}
	
	public void addListenerDcFechaNacimiento() {
		((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).addActionListener (a -> {
			tema.setTema(dcFechaNacimiento, true);
		});
	}
	
	public void addListenerSeleccionEstadoCivil() {
		seleccionEstadoCivil.addActionListener (a -> {
			if(seleccionEstadoCivil.getSelectedIndex() > 0){
				tema.setComboBoxChico(seleccionEstadoCivil, true);
			}
		});
	}
	
	public void addListenerBtnAgregarHijo(ActionListener listener) {
		btnAgregarHijo.addActionListener(listener);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}

	public void addSexo(String sexo) {
		seleccionSexo.addItem(sexo);
	}
	
	public void addEstadoCivil(String estadoCivil) {
		seleccionEstadoCivil.addItem(estadoCivil);
	}
	
	public void setFecha(Date fecha) {
		dcFechaNacimiento.setDate(fecha);
	}
	
	public void setEstadoCivil(Integer index) {
		seleccionEstadoCivil.setSelectedIndex(index);
	}
	
	public String getSexo() {
		return seleccionSexo.getItemAt(seleccionSexo.getSelectedIndex());
	}
	
	public String getEstadoCivil() {
		return seleccionEstadoCivil.getItemAt(seleccionEstadoCivil.getSelectedIndex());
	}
	
	public Date getFechaNac() {
		return dcFechaNacimiento.getDate();
	}
	
	public Integer indexSeleccionEstadoCivil() {
		int index = seleccionEstadoCivil.getSelectedIndex();
		seleccionEstadoCivil.removeAllItems();
		return index;
	}
}
