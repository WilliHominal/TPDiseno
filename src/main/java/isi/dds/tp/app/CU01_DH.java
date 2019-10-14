package isi.dds.tp.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import isi.dds.tp.enums.GestorEnum;
import isi.dds.tp.modelo.HijoDeclarado;

@SuppressWarnings("serial")
public class CU01_DH extends JFrame  {
	
	public final static class DeclararHijoAbierto {
	    private DeclararHijoAbierto(){}
	    public static Boolean declararHijoAbierto = false;
	    public static HijoDeclarado hijo;
	    public static Boolean hijoDeclarado = false;
	}
	
	Boolean seleccionoFechaNac = true;
	Boolean seleccionoSexo = true;
	Boolean seleccionoEstadoCivil = true;
	
	private Object[] tema;
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, colorLetra, colorErroneo;
	private Font letra;
	
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

					new CU01_DH(new Object[]{new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK, new Color(71,71,71),
							Color.BLACK, new Font("Open Sans", Font.PLAIN, 13), new Color(255,102,102)});
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
			DeclararHijoAbierto.hijo = new HijoDeclarado();
		}
		
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

		try {
			java.util.Date fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
			dcFechaNacimiento.setDate(fechaParseada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		colorErroneo = (Color) tema[7];
		
		
		setFont(letra);
		setBackground(colorFondoPantalla);

		lfechaNacimiento.setFont(letra);
		lsexo.setFont(letra);
		lestadocivil.setFont(letra);
		ldatosObligatorios.setFont(new Font("Open Sans", Font.ITALIC, 9));

		((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setBackground(colorFondoTexto);
		((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setFont(letra);
		((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setForeground(colorLetra);
		
		seleccionSexo.setBackground(colorFondoTexto);
		seleccionSexo.setFont(letra);
		seleccionSexo.setForeground(colorLetra);
		seleccionEstadoCivil.setBackground(colorFondoTexto);
		seleccionEstadoCivil.setFont(letra);
		seleccionEstadoCivil.setForeground(colorLetra);

		btnAgregarHijo.setBackground(colorBoton);
		btnAgregarHijo.setFont(letra);
		btnCancelar.setBackground(colorBoton);
		btnCancelar.setFont(letra);
	}

	private void comportamiento() {
		
		seleccionSexo.addActionListener (a -> {
			
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
			if(seleccionSexo.getSelectedIndex()>0) {
				seleccionSexo.setBackground(colorFondoTexto);
			}
		});
		
		seleccionEstadoCivil.addActionListener (a -> {
			if(seleccionEstadoCivil.getSelectedIndex() > 0){
				seleccionEstadoCivil.setBackground(colorFondoTexto);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if(!validarEdad(fechaNacLocalDate)) {
				fechaNac = "La fecha de nacimiento que se introdujo dicta una edad que no se encuentra en el rango adecuado (18 a 30 años).\n";
				huboError = true;
			}
			
			if (seleccionSexo.getSelectedIndex() == 0) {
				seleccionSexo.setBackground(colorErroneo);
				sexo = "No ha seleccionado un valor del campo sexo.\n";
				huboError = true;
			}
			
			if (seleccionEstadoCivil.getSelectedIndex() == 0) {
				seleccionEstadoCivil.setBackground(colorErroneo);
				estadoCivil = "No ha seleccionado un valor del campo estado civil.";
				huboError = true;
			}
			
			if(huboError) {
				JOptionPane.showConfirmDialog(this, fechaNac + sexo + estadoCivil , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(DeclararHijoAbierto.declararHijoAbierto == true) {
					
					DeclararHijoAbierto.declararHijoAbierto = false;
					
					DeclararHijoAbierto.hijo.setFechaNacimiento(fechaNacLocalDate);
					
					DeclararHijoAbierto.hijo.setSexo(GestorEnum.get().getEnumSexo(seleccionSexo.getItemAt(seleccionSexo.getSelectedIndex())));
	
					DeclararHijoAbierto.hijo.setEstadoCivil(GestorEnum.get().getEnumEstadoCivil(seleccionEstadoCivil.getItemAt(seleccionEstadoCivil.getSelectedIndex())));
						
					DeclararHijoAbierto.hijoDeclarado = true;
					
					this.setVisible(false);
				}
				
			}
			
		});
		
		btnCancelar.addActionListener(a -> {
			if(DeclararHijoAbierto.declararHijoAbierto == true) {
				DeclararHijoAbierto.declararHijoAbierto = false;
				DeclararHijoAbierto.hijo = null;
				DeclararHijoAbierto.hijoDeclarado = false;
			}
			this.setVisible(false);
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
			((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setBackground(colorFondoTexto);
			return true;
		}
		else {
			((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setBackground(colorErroneo);
			return false;
		}
	}
	
}
