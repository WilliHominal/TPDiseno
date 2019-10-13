package isi.dds.tp.app;

import java.awt.Color;
import java.awt.Dimension;
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
import isi.dds.tp.enums.EnumEstadoCivil;
import isi.dds.tp.enums.EnumSexo;
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

	/* PARA PROBAR
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Object[] tema = {new Color(0, 128, 128), new Color(204,204,204), new Color(204, 204, 153), Color.BLACK, new Color(71,71,71),
							Color.BLACK, new Font("Open Sans", Font.PLAIN, 13), new Color(255,102,102)};
					new CU01_DH(tema);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	
	public CU01_DH(Object[] tema) {
		this.tema = tema;
		
		if(DeclararHijoAbierto.declararHijoAbierto == false) {
			DeclararHijoAbierto.declararHijoAbierto = true;
			DeclararHijoAbierto.hijo = new HijoDeclarado();
		}
		
		new CU01_DH();
	}
	
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
				seleccionSexo.setForeground(colorLetra);
			}
		});
		
		seleccionEstadoCivil.addActionListener (a -> {
			if(seleccionEstadoCivil.getSelectedIndex() > 0){
				seleccionEstadoCivil.setForeground(colorLetra);
			}
		});
		
		btnAgregarHijo.addActionListener(a -> {

			
			SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
			
			Date fechaNacDate = null;
	
			try {
				fechaNacDate = formato.parse(formato.format(dcFechaNacimiento.getDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			seleccionoFechaNac = validarEdad(fechaNacLocalDate);
			
			if (seleccionSexo.getSelectedIndex() == 0) {
				seleccionSexo.setForeground(colorErroneo);
				seleccionoSexo = false;
			}
			else {
				seleccionoSexo = true;
			}
			
			if (seleccionEstadoCivil.getSelectedIndex() == 0) {
				seleccionEstadoCivil.setForeground(colorErroneo);
				seleccionoEstadoCivil = false;
			}
			else {
				seleccionoEstadoCivil = true;
			}
			
			if( !(seleccionoEstadoCivil && seleccionoSexo && seleccionoFechaNac) ) {
				avisos();	
				return;
			}
			else {
				if(DeclararHijoAbierto.declararHijoAbierto == true) {
					
					DeclararHijoAbierto.declararHijoAbierto = false;
					
					DeclararHijoAbierto.hijo.setFechaNacimiento(fechaNacLocalDate);
					
					switch(seleccionSexo.getSelectedIndex()) {
						case 1:
							DeclararHijoAbierto.hijo.setSexo(EnumSexo.FEMENINO);
						break;
						
						case 2:
							DeclararHijoAbierto.hijo.setSexo(EnumSexo.MASCULINO);
						break;
					}
					
					switch(seleccionEstadoCivil.getSelectedIndex()) {
						case 1:
							DeclararHijoAbierto.hijo.setEstadoCivil(EnumEstadoCivil.SOLTERO);
						break;
						
						case 2:
							DeclararHijoAbierto.hijo.setEstadoCivil(EnumEstadoCivil.CASADO);
						break;
						
						case 3:
							DeclararHijoAbierto.hijo.setEstadoCivil(EnumEstadoCivil.VIUDO);
						break;
						
						case 4:
							DeclararHijoAbierto.hijo.setEstadoCivil(EnumEstadoCivil.DIVORCIADO);
						break;
						
						case 5:
							DeclararHijoAbierto.hijo.setEstadoCivil(EnumEstadoCivil.SEPARADO);
						break;
						
						case 6:
							DeclararHijoAbierto.hijo.setEstadoCivil(EnumEstadoCivil.EN_RELACION);
						break;
					}
					DeclararHijoAbierto.hijoDeclarado = true;
				}
			}
			this.setVisible(false);
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
			((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setForeground(colorLetra);
			return true;
		}
		else {
			((JTextFieldDateEditor)dcFechaNacimiento.getDateEditor()).setForeground(colorErroneo);
			return false;
		}
	}
	
	public void avisos() {
		String fechaNac = "";
		String sexo = "";
		String estadoCivil = "";
		
		if(!seleccionoFechaNac) {
			fechaNac = "La fecha de nacimiento que se introdujo dicta una edad que no se encuentra en el rango adecuado (18 a 30 años).\n";
		}
		
		if(!seleccionoSexo) {
			sexo = "No ha seleccionado un valor del campo sexo.\n";
		}
		
		if(!seleccionoEstadoCivil) {
			estadoCivil = "No ha seleccionado un valor del campo estado civil.";
		}
		
		JOptionPane.showConfirmDialog(this, fechaNac + sexo + estadoCivil , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
	}
}
