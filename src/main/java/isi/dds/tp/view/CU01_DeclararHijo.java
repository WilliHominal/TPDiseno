package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorTema;
import isi.dds.tp.modelo.HijoDeclarado;

@SuppressWarnings("serial")
public class CU01_DeclararHijo extends JPanel  {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU01_DeclararHijo(new JFrame());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame ventana =  new JFrame();
	private JFrame ventanaAltaPoliza =  new JFrame();
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
	
	public CU01_DeclararHijo(JFrame frame) {
		ventanaAltaPoliza = frame;
		
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		comportamiento();
		
		ventana.setVisible(true);		
	}

	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
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
		ventana.setContentPane(this);
		ventana.setResizable(false);
		ventana.setBounds(0, 0, 420, 250);
		ventana.setLocationRelativeTo(null);
		ventana.setTitle("Dar de alta póliza: AGREGAR DATOS HIJOS");
		ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		seleccionEstadoCivil.setPreferredSize(new Dimension(105, 25));
		seleccionSexo.setPreferredSize(new Dimension(105, 25));
		
		seleccionSexo.addItem("Selecionar");
		EnumSexo[] sexos = EnumSexo.values();
		for(int i=0; i<sexos.length; i++){
			seleccionSexo.addItem(GestorEnum.get().getStringSexo(sexos[i]));
		}
		
		seleccionEstadoCivil.addItem("Selecionar");
		EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
		for(int i=0; i<estadosCivil.length; i++){
			seleccionEstadoCivil.addItem(GestorEnum.get().getStringEstadoCivil(estadosCivil[i]));
		}

		try {
			//TODO elegir como fecha inicial el último día aceptado como fecha válida de cumpleaños
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
			dcFechaNacimiento.setDate(fechaParseada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		dcFechaNacimiento.setPreferredSize(new Dimension(105, 25));
		
		btnAgregarHijo.setPreferredSize(new Dimension(130, 25));
		btnCancelar.setPreferredSize(new Dimension(130, 25));
	}
	
	private void inicializarTema() {	
		tema.panel(this);
		tema.label(lfechaNacimiento);
		tema.label(lsexo);
		tema.label(lestadocivil);
		tema.label(ldatosObligatorios);
		tema.calendario(dcFechaNacimiento, true);
		tema.seleccion(seleccionSexo, true);
		tema.seleccion(seleccionEstadoCivil, true);
		tema.boton(btnAgregarHijo, true);
		tema.boton(btnCancelar, true);
	}

	private void comportamiento() {
		
		seleccionSexo.addActionListener (a -> {
			
			if(seleccionSexo.getSelectedIndex() == 1) {
				
				seleccionEstadoCivil.setEnabled(false);
				
				int index = seleccionEstadoCivil.getSelectedIndex();
				
				seleccionEstadoCivil.removeAllItems();
				
				seleccionEstadoCivil.addItem("Selecionar");
				EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
				for(int i=0; i<estadosCivil.length; i++){
					seleccionEstadoCivil.addItem(GestorEnum.get().getStringEstadoCivil(estadosCivil[i]));
				}
				
				seleccionEstadoCivil.setSelectedIndex(index);
				
				seleccionEstadoCivil.setEnabled(true);
			}
			else {
				seleccionEstadoCivil.setEnabled(false);
				
				int index = seleccionEstadoCivil.getSelectedIndex();
				
				seleccionEstadoCivil.removeAllItems();
				
				seleccionEstadoCivil.addItem("Selecionar");
				EnumEstadoCivil[] estadosCivil = EnumEstadoCivil.values();
				for(int i=0; i<estadosCivil.length; i++){
					seleccionEstadoCivil.addItem(GestorEnum.get().getStringEstadoCivilFem(estadosCivil[i]));
				}
				
				seleccionEstadoCivil.setSelectedIndex(index);
				
				seleccionEstadoCivil.setEnabled(true);
			}
			if(seleccionSexo.getSelectedIndex()>0) {
				tema.seleccion(seleccionSexo, true);
			}
		});
		
		seleccionEstadoCivil.addActionListener (a -> {
			if(seleccionEstadoCivil.getSelectedIndex() > 0){
				tema.seleccion(seleccionEstadoCivil, true);
			}
		});
		
		btnAgregarHijo.addActionListener(a -> {

			String fechaNac = "";
			String sexo = "";
			String estadoCivil = "";
			Boolean huboError = false;
			
			SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
			
			Date fechaNacDate = null;
	
			try {
				fechaNacDate = formato.parse(formato.format(dcFechaNacimiento.getDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if(!validarEdad(fechaNacLocalDate)) {
				fechaNac = "La fecha de nacimiento que se introdujo dicta una edad que no se encuentra en el rango adecuado (18 a 30 años).\n";
				huboError = true;
			}
			
			if (seleccionSexo.getSelectedIndex() == 0) {
				tema.erroneo(seleccionSexo);
				sexo = "No ha seleccionado un valor del campo sexo.\n";
				huboError = true;
			}
			
			if (seleccionEstadoCivil.getSelectedIndex() == 0) {
				tema.erroneo(seleccionEstadoCivil);
				estadoCivil = "No ha seleccionado un valor del campo estado civil.";
				huboError = true;
			}
			
			if(huboError) {
				JOptionPane.showConfirmDialog(this, fechaNac + sexo + estadoCivil , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else {
				HijoDeclarado hijo = new HijoDeclarado();
				
				hijo.setFechaNacimiento(fechaNacLocalDate);
				hijo.setSexo(GestorEnum.get().getEnumSexo(seleccionSexo.getItemAt(seleccionSexo.getSelectedIndex())));
				hijo.setEstadoCivil(GestorEnum.get().getEnumEstadoCivil(seleccionEstadoCivil.getItemAt(seleccionEstadoCivil.getSelectedIndex())));

				ventana.setVisible(false);
				((CU01_AltaPoliza1) ventanaAltaPoliza.getContentPane()).componentesAlDeclararHijos(true, hijo);
			}
			
		});
		
		btnCancelar.addActionListener(a -> {
			ventana.setVisible(false);
			((CU01_AltaPoliza1) ventanaAltaPoliza.getContentPane()).componentesAlDeclararHijos(true, null);
		});
		
		ventana.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				ventana.setVisible(false);
				((CU01_AltaPoliza1) ventanaAltaPoliza.getContentPane()).componentesAlDeclararHijos(true, null);
		    }
		});
	}

	private Boolean validarEdad(LocalDate fechaNacLocalDate) {
		
		int anioHoy = LocalDate.now().getYear();
		int diaDelAnioHoy = LocalDate.now().getDayOfYear();
		int anioDeclarado = fechaNacLocalDate.getYear();
		int diaDelAnioDeclarado = fechaNacLocalDate.getDayOfYear();
		
		Boolean valido1 = false;
		Boolean valido2 = false;
		
		if(anioHoy > (anioDeclarado+18)) {
			valido1 = true;
		}
		else {
			if(anioHoy < (anioDeclarado+18)) {
				valido1 = false;
			}
			else {
				if(diaDelAnioHoy >= diaDelAnioDeclarado) {
					valido1 = true;
				}
				else {
					valido1 = false;
				}
			}
		}
		
		if(anioHoy < (anioDeclarado+30)) {
			valido2 = true;
		}
		else {
			if(anioHoy > (anioDeclarado+30)) {
				valido2 = false;
			}
			else {
				if(diaDelAnioHoy < diaDelAnioDeclarado) {
					valido2 = true;
				}
				else {
					valido2 = false;
				}
			}
		}
		
		if(valido1&&valido2){
			tema.calendario(dcFechaNacimiento, true);
			return true;
		}
		else {
			tema.erroneo(((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()));
			return false;
		}
	}
	
}
