package isi.dds.tp.gestor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class GestorTema {
	
	private static GestorTema instanciaGestor = null;
	
	private static Color colorBoton;
	private static Color colorBotonDeshabilitado;
	private static Color colorFondoPantalla;
	private static Color colorFondoTexto; 
	private static Color colorFondoDeshabilitado;
	private static Color colorFondoErroneo;
	private static Color colorLetra;
	private static Color colorLetraErronea;
	private static Color colorLetraDeshabilitada;
	private static Font letraGrande;
	private static Font letra;
	private static Font letraChica;
	
    private GestorTema() {
    	ToolTipManager.sharedInstance().setInitialDelay(1000);
    	tema2();
    }

    public static GestorTema get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTema();
        }    
        return instanciaGestor;
    }
    
    @SuppressWarnings("unused")
	private void tema1() {
    	
    	instanciaGestor = null;
    	colorBoton = new Color(0, 128, 128);
    	colorBotonDeshabilitado =  new Color(0, 50, 50);
    	colorFondoPantalla = new Color(204,204,204);
    	colorFondoTexto = new Color(204, 204, 153).brighter(); 
    	colorFondoDeshabilitado = colorFondoTexto.darker();
    	colorFondoErroneo = new Color(255,102,102);
    	colorLetra = Color.BLACK;
    	colorLetraErronea = colorFondoErroneo.darker().darker();
    	colorLetraDeshabilitada = Color.DARK_GRAY;
    	letraGrande = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    	letra = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    	letraChica = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
    	UIManager.put("Button.select", new ColorUIResource(Color.RED));
    }
    
    private void tema2() {
    	colorBoton = new Color(179,149,62);
    	colorBotonDeshabilitado = new Color(102,83,26);
    	colorFondoPantalla = new Color(139, 163, 255);
    	colorFondoTexto = new Color(217,224,255);
    	colorFondoDeshabilitado = new Color(70,82,128);
    	colorFondoErroneo = new Color(255,83,77);
    	colorLetra =  Color.BLACK;//colorFondoDeshabilitado;
    	colorLetraErronea = colorFondoErroneo.darker().darker();
    	colorLetraDeshabilitada = colorFondoTexto.darker().darker();
    	letraGrande = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    	letra = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    	letraChica = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
    }
    
    @SuppressWarnings("unused")
	private void tema3() {
    	colorBoton = new Color(252,74,26);
    	colorBotonDeshabilitado = colorBoton.darker();
    	colorFondoPantalla = new Color(174,189,172);
    	colorFondoTexto = new Color(247,183,51);
    	colorFondoDeshabilitado = new Color(198,146,41);
    	colorFondoErroneo = new Color(255,83,77);
    	colorLetra =  Color.BLACK;//colorFondoDeshabilitado;
    	colorLetraErronea = colorFondoErroneo.darker().darker();
    	colorLetraDeshabilitada = colorFondoTexto.darker();
    	letraGrande = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    	letra = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    	letraChica = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
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
		label.setForeground(colorLetra);
    }
    
	public void labelTituloSubrayada(JLabel label) {
		Font font = letraGrande;
		Map<TextAttribute, Object> titulo = new HashMap<>(font.getAttributes());
		titulo.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		label.setFont(font.deriveFont(titulo));
		label.setForeground(colorLetra);
	}
    
    public void boton(JButton btn, Boolean habilitado) {
 
    	if(habilitado) {
        	btn.setBackground(colorBoton);
        	btn.setFont(letra);
        	btn.setForeground(colorLetra);
        	btn.setEnabled(true);
        	btn.setContentAreaFilled(false);
    		btn.setOpaque(true);
    	}
    	else {
        	btn.setBackground(colorBotonDeshabilitado);
    		UIManager.put( "Button.disabledText", colorLetraDeshabilitada );
        	btn.setFont(letra);
        	btn.setEnabled(false);
    	}
    }
    
    public void botonSubrayado(JButton btn, Boolean habilitado, Icon icono) {
    	Font font = letra;
		Map<TextAttribute, Object> subrayado = new HashMap<>(font.getAttributes());
		subrayado.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		
		btn.setHorizontalAlignment(SwingConstants.LEADING);
		btn.setBackground(colorFondoPantalla);
		btn.setFont(font.deriveFont(subrayado));
		btn.setBorder(new LineBorder(colorFondoPantalla));
		btn.setIcon(icono);
		
	   	if(habilitado) {
			btn.setForeground(colorLetra);
			btn.setEnabled(true);
			btn.setContentAreaFilled(false);
    		btn.setOpaque(true);
	   	}
    	else {
    		btn.setDisabledIcon(icono);
    		btn.setBackground(colorFondoPantalla);
    		UIManager.put( "Button.disabledText", colorLetraDeshabilitada );
			btn.setEnabled(false);
    	}
    }
    
    public void campo(JTextField campo, Boolean habilitada) {
    	if(habilitada) {
    		campo.setEnabled(habilitada);
        	campo.setFont(letra);
        	campo.setBackground(colorFondoTexto);
        	campo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        	campo.setForeground(colorLetra);
    	}
    	else {
    		campo.setEnabled(habilitada);
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
    		seleccion.setEnabled(habilitada);
    		seleccion.setBackground(colorFondoTexto);
    		seleccion.setFont(letra);
    		seleccion.setForeground(colorLetra);
    	}
    	else {
    		seleccion.setEnabled(habilitada);
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
    
	public void tablaScroll(JScrollPane tablaScroll, Boolean habilitada) {
    	if(habilitada) {
    		tablaScroll.getViewport().setBackground(colorFondoTexto);	
    		tablaScroll.setForeground(colorLetra);
    	}
    	else {
    		tablaScroll.getViewport().setBackground(colorFondoDeshabilitado);	
    		tablaScroll.setForeground(colorLetraDeshabilitada);
    	}
	}

	public void tabla(JTable tabla, Boolean habilitada) {
    	if(habilitada) {
    		tabla.setEnabled(habilitada);
    		tabla.setFont(letra);
    		tabla.getTableHeader().setToolTipText("Doble click en la cabecera para ordenar");
    		tabla.setAutoCreateRowSorter(true);
    		tabla.setFillsViewportHeight(true);
    		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		tabla.setForeground(colorLetra);
    		tabla.setBackground(colorFondoTexto);
    	}
    	else {
    		tabla.setEnabled(habilitada);
    		tabla.setFont(letra);
    		tabla.setForeground(colorLetraDeshabilitada);
    		tabla.setBackground(colorFondoDeshabilitado);
    	}
	}
	
	public void calendario(JDateChooser calendario, Boolean habilitado) {
		if(habilitado) {
			calendario.setEnabled(habilitado);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBackground(colorFondoTexto);
			((JTextFieldDateEditor)calendario.getDateEditor()).setFont(letra);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			((JTextFieldDateEditor)calendario.getDateEditor()).setForeground(colorLetra);
		}
		else {
			calendario.setEnabled(habilitado);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBackground(colorFondoDeshabilitado);
			((JTextFieldDateEditor)calendario.getDateEditor()).setFont(letra);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			((JTextFieldDateEditor)calendario.getDateEditor()).setDisabledTextColor(colorLetraDeshabilitada);
		}
	}
    
	public void erroneo(Component componente) {
		componente.setBackground(colorFondoErroneo);
		componente.setForeground(colorLetraErronea);
	}

}
