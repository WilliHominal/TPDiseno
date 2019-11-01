package isi.dds.tp.gestor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class GestorTema {
	
	private static GestorTema instanciaGestor = null;
	
	private static Dimension DIMENSION_VENTANA = new Dimension(1064,600);
	private static Dimension DIMENSION_BOTON = new Dimension(160, 25);
	private static Dimension DIMENSION_COMBO_BOX_CHICO = new Dimension(105, 25);
	private static Dimension DIMENSION_COMBO_BOX = new Dimension(164, 22);
	private static Dimension DIMENSION_COMBO_BOX_GRANDE = new Dimension(260, 22);
	private static Dimension DIMENSION_CALENDARIO = new Dimension(105, 20);
	private static Color COLOR_BOTON;
	private static Color COLOR_BOTON_DESHABILITADO;
	private static Color COLOR_FONDO_PANTALLA;
	private static Color COLOR_FONDO_TEXTO; 
	private static Color COLOR_FONDO_DESHABILITADO;
	private static Color COLOR_FONDO_ERRONEO;
	private static Color COLOR_LETRA;
	private static Color COLOR_LETRA_ERRONEA;
	private static Color COLOR_LETRA_DESHABILITADA;
	private static Color COLOR_LETRA_BOTON_DESHABILITADA;
	private static Font FUENTE_LETRA_NORMAL = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	private static Font FUENTE_LETRA_CHICA = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
	private static Font FUENTE_LETRA_GRANDE = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	
    private GestorTema() {
    	ToolTipManager.sharedInstance().setInitialDelay(200);
    	tema1();
    }

    public static GestorTema get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTema();
        }    
        return instanciaGestor;
    }
        
    private void tema1() {
    	COLOR_BOTON = new Color(179,149,62);
    	COLOR_BOTON_DESHABILITADO = new Color(102,83,26);
    	COLOR_FONDO_PANTALLA = new Color(139, 163, 255);
    	COLOR_FONDO_TEXTO = new Color(217,224,255);
    	COLOR_FONDO_DESHABILITADO = new Color(70,82,128);
    	COLOR_FONDO_ERRONEO = new Color(255,83,77);
    	COLOR_LETRA = Color.BLACK;
    	COLOR_LETRA_ERRONEA = COLOR_FONDO_ERRONEO.darker().darker();
    	COLOR_LETRA_DESHABILITADA =  COLOR_FONDO_TEXTO.darker();
    	COLOR_LETRA_BOTON_DESHABILITADA = Color.DARK_GRAY;
    }
    
    @SuppressWarnings("unused")
	private void tema2() {
    	COLOR_BOTON = new Color(52,179,176);
    	COLOR_BOTON_DESHABILITADO = COLOR_BOTON.darker();
    	COLOR_FONDO_PANTALLA = new Color(255,172,125);
    	COLOR_FONDO_TEXTO = new Color(164,255,254);
    	COLOR_FONDO_DESHABILITADO = new Color(179,109,70);
    	COLOR_FONDO_ERRONEO = new Color(255,83,77);
    	COLOR_LETRA = Color.BLACK;
    	COLOR_LETRA_ERRONEA = COLOR_FONDO_ERRONEO.darker().darker();
    	COLOR_LETRA_DESHABILITADA = COLOR_FONDO_TEXTO.darker();
    	COLOR_LETRA_BOTON_DESHABILITADA = Color.DARK_GRAY;
    }

    public void setTema(JPanel panel) {
    	panel.setFont(FUENTE_LETRA_NORMAL);
    	panel.setBackground(COLOR_FONDO_PANTALLA);
    	panel.setForeground(COLOR_LETRA);
    	panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    	//panel.setBorder(BorderFactory.createLineBorder(COLOR_BOTON_DESHABILITADO, 5));
    }
    
	public void setTema(JFrame ventana, String titulo) {
		ventana.setResizable(false);
		ventana.setSize(DIMENSION_VENTANA);
		ventana.setTitle(titulo);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
    
    public void setTema(JLabel label) {
    	label.setForeground(COLOR_LETRA);
    	label.setFont(FUENTE_LETRA_NORMAL);
    }
    
    public void setTemaLabelchica(JLabel label) {
    	label.setForeground(COLOR_LETRA);
    	label.setFont(FUENTE_LETRA_CHICA);
    }
    
    public void setTemaSubrayado(JLabel label) {
		Font font = FUENTE_LETRA_NORMAL;
		Map<TextAttribute, Object> titulo = new HashMap<>(font.getAttributes());
		titulo.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		label.setFont(font.deriveFont(titulo));
		label.setForeground(COLOR_LETRA);
    }
    
	public void setTemaTitulo(JLabel label) {
		//Font font = FUENTE_LETRA_GRANDE;
		//Map<TextAttribute, Object> titulo = new HashMap<>(font.getAttributes());
		//titulo.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		//label.setFont(font.deriveFont(titulo));
		label.setFont(FUENTE_LETRA_GRANDE);
		label.setForeground(COLOR_LETRA);
	}
    
    public void setTema(JButton btn, Boolean habilitado) {
    	btn.setPreferredSize(DIMENSION_BOTON);
    	btn.setFont(FUENTE_LETRA_NORMAL);
    	if(habilitado) {
        	btn.setBackground(COLOR_BOTON);
        	btn.setForeground(COLOR_LETRA);
        	btn.setEnabled(true);
        	btn.setContentAreaFilled(false);
    		btn.setOpaque(true);
    	}
    	else {
        	btn.setBackground(COLOR_BOTON_DESHABILITADO);
    		UIManager.put( "Button.disabledText", COLOR_LETRA_BOTON_DESHABILITADA);
        	btn.setEnabled(false);
    	}
    }
        
    public void setTema(JButton btn, Boolean habilitado, Icon icono) {
    	Font font = FUENTE_LETRA_NORMAL;
		Map<TextAttribute, Object> subrayado = new HashMap<>(font.getAttributes());
		subrayado.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
		
		btn.setHorizontalAlignment(SwingConstants.LEADING);
		btn.setBackground(COLOR_FONDO_PANTALLA);
		btn.setFont(font.deriveFont(subrayado));
		btn.setBorder(new LineBorder(COLOR_FONDO_PANTALLA));
		btn.setIcon(icono);
		
	   	if(habilitado) {
			btn.setForeground(COLOR_LETRA);
			btn.setEnabled(true);
			btn.setContentAreaFilled(false);
    		btn.setOpaque(true);
	   	}
    	else {
    		btn.setDisabledIcon(icono);
    		UIManager.put( "Button.disabledText", COLOR_LETRA_BOTON_DESHABILITADA);
			btn.setEnabled(false);
    	}
    }
    
    public void setTema(JTextField campo, Boolean habilitada) {
    	//campo.setCaretColor(colorBoton);
    	if(habilitada) {
    		campo.setEnabled(habilitada);
        	campo.setFont(FUENTE_LETRA_NORMAL);
        	campo.setBackground(COLOR_FONDO_TEXTO);
        	campo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        	campo.setForeground(COLOR_LETRA);
    	}
    	else {
    		campo.setEnabled(habilitada);
        	campo.setFont(FUENTE_LETRA_NORMAL);
        	campo.setBackground(COLOR_FONDO_DESHABILITADO);
        	campo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        	campo.setDisabledTextColor(COLOR_LETRA_DESHABILITADA);
    	}
    }
    
	public void setTema(JTextArea textArea) {
		textArea.setBackground(COLOR_FONDO_PANTALLA);
		textArea.setFont(FUENTE_LETRA_NORMAL);
		textArea.setForeground(COLOR_LETRA_ERRONEA);
	}
    
	public void setTema(JComboBox<?> seleccion, Boolean habilitada) {
    	seleccion.setPreferredSize(DIMENSION_COMBO_BOX);
    	if(habilitada) {
    		seleccion.setEnabled(habilitada);
    		seleccion.setBackground(COLOR_FONDO_TEXTO);
    		seleccion.setFont(FUENTE_LETRA_NORMAL);
    		seleccion.setForeground(COLOR_LETRA);
    	}
    	else {
    		seleccion.setEnabled(habilitada);
    		UIManager.put( "ComboBox.disabledBackground", COLOR_FONDO_DESHABILITADO );
    		UIManager.put( "ComboBox.disabledForeground", COLOR_LETRA_DESHABILITADA );
    		seleccion.setBackground(COLOR_FONDO_DESHABILITADO);
    		seleccion.setFont(FUENTE_LETRA_NORMAL);
    		seleccion.setForeground(COLOR_LETRA_DESHABILITADA);
    	}
    }
    
    public void setComboBoxChico(JComboBox<?> seleccion, Boolean habilitada) {
    	setTema(seleccion, habilitada);
    	seleccion.setPreferredSize(DIMENSION_COMBO_BOX_CHICO);
    }
    
    public void setComboBoxGrande(JComboBox<?> seleccion, Boolean habilitada) {
    	setTema(seleccion, habilitada);
    	seleccion.setPreferredSize(DIMENSION_COMBO_BOX_GRANDE);
    }
    
    public void setTema(JRadioButton rbtn) {
    	rbtn.setBackground(COLOR_FONDO_PANTALLA);
    	rbtn.setFont(FUENTE_LETRA_NORMAL);
    	rbtn.setForeground(COLOR_LETRA);
    }

	public void setTema(JCheckBox check, String tip) {
		check.setBackground(COLOR_FONDO_PANTALLA);
		check.setFont(FUENTE_LETRA_NORMAL);
		check.setForeground(COLOR_LETRA);
		check.setToolTipText(tip);
	}
    
	public void setTema(JScrollPane tablaScroll, Boolean habilitada) {
		tablaScroll.setBackground(COLOR_FONDO_PANTALLA);
		tablaScroll.setEnabled(habilitada);
		tablaScroll.getVerticalScrollBar().setBackground(COLOR_FONDO_PANTALLA);
		tablaScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
	            @Override
	            protected void configureScrollBarColors() {
	                this.thumbColor = COLOR_FONDO_PANTALLA.darker();
	            }
	    });
    	if(habilitada) {
    		tablaScroll.getViewport().setBackground(COLOR_FONDO_TEXTO);	
    		tablaScroll.setForeground(COLOR_LETRA);
    	}
    	else {
    		tablaScroll.getViewport().setBackground(COLOR_FONDO_DESHABILITADO);	
    		tablaScroll.setForeground(COLOR_LETRA_DESHABILITADA);
    	}
	}

	public void setTema(JTable tabla, Boolean habilitada) {
		JTableHeader th = tabla.getTableHeader();
		th.setFont(FUENTE_LETRA_NORMAL);
		th.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		tabla.setEnabled(habilitada);
		tabla.setFont(FUENTE_LETRA_NORMAL);
    	if(habilitada) {
    		tabla.getTableHeader().setToolTipText("Doble click en la cabecera para ordenar");
    		tabla.setAutoCreateRowSorter(true);
    		tabla.setFillsViewportHeight(true);
    		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		tabla.setForeground(COLOR_LETRA);
    		tabla.setBackground(COLOR_FONDO_TEXTO);
    		th.setBackground(COLOR_FONDO_TEXTO);
    		th.setForeground(COLOR_LETRA);
    	}
    	else {
    		tabla.setForeground(COLOR_LETRA_DESHABILITADA);
    		tabla.setBackground(COLOR_FONDO_DESHABILITADO);
    		th.setBackground(COLOR_FONDO_DESHABILITADO);
    		th.setForeground(COLOR_LETRA_DESHABILITADA);
    	}
	}
	
	public void setTema(JDateChooser calendario, Boolean habilitado) {
		calendario.setPreferredSize(DIMENSION_CALENDARIO);
		calendario.setDateFormatString("dd/MM/yyyy");;
		if(habilitado) {
			calendario.setEnabled(habilitado);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBackground(COLOR_FONDO_TEXTO);
			((JTextFieldDateEditor)calendario.getDateEditor()).setFont(FUENTE_LETRA_NORMAL);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			((JTextFieldDateEditor)calendario.getDateEditor()).setForeground(COLOR_LETRA);
		}
		else {
			calendario.setEnabled(habilitado);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBackground(COLOR_FONDO_DESHABILITADO);
			((JTextFieldDateEditor)calendario.getDateEditor()).setFont(FUENTE_LETRA_NORMAL);
			((JTextFieldDateEditor)calendario.getDateEditor()).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			((JTextFieldDateEditor)calendario.getDateEditor()).setDisabledTextColor(COLOR_LETRA_DESHABILITADA);
		}
	}
    
	public void erroneo(Component componente) {
		componente.setBackground(COLOR_FONDO_ERRONEO);
		componente.setForeground(COLOR_LETRA_ERRONEA);
	}
}
