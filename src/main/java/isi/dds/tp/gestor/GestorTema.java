package isi.dds.tp.gestor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class GestorTema {
	
	private static GestorTema instanciaGestor = null;
	private static Color colorBoton = new Color(0, 128, 128);
	private static Color colorFondoPantalla = new Color(204,204,204);
	private static Color colorFondoTexto = new Color(204, 204, 153).brighter(); 
	private static Color colorFondoDeshabilitado = colorFondoTexto.darker();
	private static Color colorFondoErroneo = new Color(255,102,102);
	private static Color colorLetra = Color.BLACK;
	private static Color colorLetraErronea = colorFondoErroneo.darker().darker();
	private static Color colorLetraDeshabilitada = Color.GRAY;
	private static Font letraGrande = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
	private static Font letra = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	private static Font letraChica = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
	public static Object[] tema = {colorBoton, colorFondoPantalla, colorFondoTexto, colorLetra, letra, colorFondoErroneo, colorFondoDeshabilitado, colorLetraDeshabilitada};
	
	
    private GestorTema() {

    }

    public static GestorTema get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTema();
        }    
        return instanciaGestor;
    }

    public void panel(JPanel panel) {
    	panel.setFont(letra);
    	panel.setBackground(colorFondoPantalla);
    	panel.setForeground(colorLetra);
    }
    
    public void label(JLabel label) {
    	label.setForeground(colorLetra);
    	label.setFont(letra);
    }
    
    public void labelChica(JLabel label) {
    	label.setForeground(colorLetra);
    	label.setFont(letraChica);
    }
    
    public void labelSubrayada(JLabel label) {
		Font font = letra;
		Map<TextAttribute, Object> titulo = new HashMap<>(font.getAttributes());
		titulo.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		label.setFont(font.deriveFont(titulo));
    }
    
	public void labelTituloSubrayada(JLabel label) {
		Font font = letraGrande;
		Map<TextAttribute, Object> titulo = new HashMap<>(font.getAttributes());
		titulo.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		label.setFont(font.deriveFont(titulo));
	}
    
    public void boton(JButton btn) {
    	btn.setBackground(colorBoton);
    	btn.setFont(letra);
    	btn.setForeground(colorLetra);
    }
    
    public void botonSubrayado(JButton btn) {
		Font font = letra;
		Map<TextAttribute, Object> subrayado = new HashMap<>(font.getAttributes());
		subrayado.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		btn.setBackground(colorFondoPantalla);
		btn.setFont(font.deriveFont(subrayado));
		btn.setForeground(colorLetra);
		btn.setBorder(new LineBorder(colorFondoPantalla));
    }
    
    public void campo(JTextField campo, Boolean habilitada) {
    	if(habilitada) {
        	campo.setFont(letra);
        	campo.setBackground(colorFondoTexto);
        	campo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        	campo.setForeground(colorLetra);
    	}
    	else {
        	campo.setFont(letra);
        	campo.setBackground(colorFondoDeshabilitado);
        	campo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        	campo.setDisabledTextColor(colorLetraDeshabilitada);
    	}
    }
    
	public void textArea(JTextArea textArea) {
		textArea.setBackground(colorFondoPantalla);
		textArea.setFont(letra);
		textArea.setForeground(colorLetraErronea);
	}
    
    @SuppressWarnings("rawtypes")
	public void seleccion(JComboBox seleccion, Boolean habilitada) {
    	if(habilitada) {
    		seleccion.setBackground(colorFondoTexto);
    		seleccion.setFont(letra);
    		seleccion.setForeground(colorLetra);
    	}
    	else {
    		UIManager.put( "ComboBox.disabledBackground", colorFondoDeshabilitado );
    		UIManager.put( "ComboBox.disabledForeground", colorLetraDeshabilitada );
    		seleccion.setBackground(colorFondoDeshabilitado);
    		seleccion.setFont(letra);
    		seleccion.setForeground(colorLetraDeshabilitada);
    	}
    }
    
    public void radioButton(JRadioButton rbtn) {
    	rbtn.setBackground(colorFondoPantalla);
    	rbtn.setFont(letra);
    	rbtn.setForeground(colorLetra);
    }

	public void check(JCheckBox check, String tip) {
		check.setBackground(colorFondoPantalla);
		check.setFont(letra);
		check.setForeground(colorLetra);
		check.setToolTipText(tip);
	}
    
	public void tablaScroll(JScrollPane tablaScroll, Boolean habilitada ) {
    	if(habilitada) {
    		tablaScroll.getViewport().setBackground(colorFondoTexto);	
    		//tablaHijosScroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    		tablaScroll.setForeground(colorLetra);
    	}
    	else {
    		tablaScroll.getViewport().setBackground(colorFondoDeshabilitado);	
    		//tablaHijosScroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    		tablaScroll.setForeground(colorLetraDeshabilitada);
    	}
	}

	public void tabla(JTable tabla, Boolean habilitada) {
    	if(habilitada) {
    		tabla.setFont(letra);
    		//tablaHijos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    		tabla.setForeground(colorLetra);
    		tabla.setBackground(colorFondoTexto);
    	}
    	else {
    		tabla.setFont(letra);
    		//tablaHijos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    		tabla.setForeground(colorLetraDeshabilitada);
    		tabla.setBackground(colorFondoDeshabilitado);
    	}
	}
	
	public void calendario(JDateChooser calendario, Boolean habilitado) {
		if(habilitado) {
			((JTextFieldDateEditor)calendario.getDateEditor()).setBackground(colorFondoTexto);
			((JTextFieldDateEditor)calendario.getDateEditor()).setFont(letra);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			((JTextFieldDateEditor)calendario.getDateEditor()).setForeground(colorLetra);
		}
		else {
			((JTextFieldDateEditor)calendario.getDateEditor()).setBackground(colorFondoDeshabilitado);
			((JTextFieldDateEditor)calendario.getDateEditor()).setFont(letra);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			((JTextFieldDateEditor)calendario.getDateEditor()).setForeground(colorLetraDeshabilitada);
		}
	}
    
	public void erroneo(Component componente) {
		componente.setBackground(colorFondoErroneo);
		componente.setForeground(colorLetraErronea);
	}

}
