package isi.dds.tp.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import isi.dds.tp.gestor.GestorTema;

public class CU17View1 extends JPanel {
	private static final long serialVersionUID = 2371539154905078614L;

	private GestorTema tema = GestorTema.get();
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombre = new JLabel("Nombres:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel lnumeroDocumento = new JLabel("Documento:");
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	
	private JTextField campoNumeroCliente = new JTextField(18);
	private JTextField campoNumeroDocumento = new JTextField(18);
	private JTextField campoNombre = new JTextField(18);
	private JTextField campoApellido = new JTextField(18);
	private JTextField campoTotalFilas = new JTextField(3);
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JComboBox<String> seleccionTipoDocumento = new JComboBox<String>();
	private JTable tablaClientes = new JTable();
	private JScrollPane tablaClientesScroll = new JScrollPane(tablaClientes);
	private Object[][] datosTabla = {{""},{""},{""},{""},{""}};
	private DefaultTableModel model;
	
	public CU17View1() {
		inicializarComponentes();
		ubicarComponentes();
		addTablaClientes(0);	
		addListenerCampoNumeroCliente();
		addListenerCampoApellido();
		addListenerCampoNombre();
	}

	private void inicializarComponentes() {	
		tema.setTema(this);
		tema.setTema(lnumeroCliente);
		tema.setTema(ltipoDocumento);
		tema.setTema(lnumeroDocumento);
		tema.setTema(lapellido);
		tema.setTema(lnombre);
		tema.setTema(ltotalFilas);	
		tema.setTema(campoNumeroCliente, true);
		tema.setTema(campoApellido, true);
		tema.setTema(campoNumeroDocumento, true);
		tema.setTema(campoNombre, true);
		tema.setTema(campoTotalFilas, false);
		tema.setTema(btnBuscar, true);
		tema.setTema(btnCancelar, true);
		tema.setTema(seleccionTipoDocumento, true);
		tema.setTema(tablaClientes, true);
		tema.setTema(tablaClientesScroll, true);
	
		btnCancelar.setPreferredSize(new Dimension(105, 25));
		btnBuscar.setPreferredSize(new Dimension(105, 25));
		seleccionTipoDocumento.setPreferredSize(new Dimension(183, 25));
		tablaClientesScroll.setPreferredSize(new Dimension(600, 500));	
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnumeroCliente, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoNumeroCliente, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lapellido, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoApellido, constraints);	

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnombre, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoNombre, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(ltipoDocumento, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionTipoDocumento, constraints);	

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnumeroDocumento, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoNumeroDocumento, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(50, 5, 5, 150);
		add(btnBuscar, constraints);
		constraints.insets.set(50, 150, 5, 5);
		add(btnCancelar, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 8;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 50, 5, 5);
		add(tablaClientesScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);
	}
			
	public void addListenerBtnBuscar(ActionListener listener) {
		btnBuscar.addActionListener(listener);
	}

	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerTablaClientes(MouseListener listener) {
		tablaClientes.addMouseListener(listener);
	}
	
	public void addListenerCampoNumeroCliente() {
		campoNumeroCliente.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isDigit(caracter) && campoApellido.getText().length() < 8){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
			}
		}); 
	}
	
	public void addListenerCampoApellido() {
		campoApellido.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) && campoApellido.getText().length() < 30){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
			}
		}); 
	}
	
	public void addListenerCampoNombre() {
	    campoNombre.addKeyListener(new KeyAdapter(){ 
	    	public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) && campoNombre.getText().length() < 30){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    });
	}
	
	public void addItemTipoCobertura(String item) {
		seleccionTipoDocumento.addItem(item);
	}

	public Integer getRowTablaClientes(Point point) {
		return tablaClientes.rowAtPoint(point);
	}

	public String getNumeroCliente() {
		return campoNumeroCliente.getText();
	}

	public String getApellido() {
		return campoApellido.getText();
	}

	public String getNombre() {
		return campoNombre.getText();
	}

	public String getNumeroDocumento() {
		return campoNumeroDocumento.getText();
	}

	public String getTipoDocumento() {
		return seleccionTipoDocumento.getItemAt(seleccionTipoDocumento.getSelectedIndex());
	}
	
	public void addTablaClientes(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaClientes.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaClientes.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(3).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(4).setCellRenderer(centrado);
		
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(70);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(110);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(120);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(80);
		
		tablaClientes.getColumnModel().getColumn(0).setHeaderValue("Nro. cliente");
		tablaClientes.getColumnModel().getColumn(1).setHeaderValue("Apellido");
		tablaClientes.getColumnModel().getColumn(2).setHeaderValue("Nombre");
		tablaClientes.getColumnModel().getColumn(3).setHeaderValue("Tipo documento");
		tablaClientes.getColumnModel().getColumn(4).setHeaderValue("Nro. documento");
		
		if(tamanioTablaActual > 0) {	
			tablaClientes.setToolTipText("Doble click para seleccionar un cliente");
			tablaClientes.setEnabled(true);
			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaClientes.setToolTipText(null);
			tablaClientes.setEnabled(false);
			campoTotalFilas.setText("");
		}
	}	
	
	public void setValoresTablaClientes(Integer fila, Long numeroCliente, String apellido, String nombre, String tipoDocumento, String numDocumento) {
		model.setValueAt(numeroCliente, fila, 0);
		model.setValueAt(apellido, fila, 1);
		model.setValueAt(nombre, fila, 2);
		model.setValueAt(tipoDocumento, fila, 3);
		model.setValueAt(numDocumento, fila, 4);
	}
}


