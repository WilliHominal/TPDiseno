package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;
import java.util.List;
import isi.dds.tp.dao.ClienteDAO;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumTipoDocumento;

public class GestorCliente {
	
	private static GestorCliente instanciaGestor = null;
	 
    private GestorCliente() {

    }

    public static GestorCliente get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }    
        return instanciaGestor;
    }

    public void AltaCliente(Cliente c) {
    	
    	ClienteDAO.getDAO().addCliente(c);
    	
    }
    
    public Integer calcularTiempoActivo(Cliente c) {
    	return null;
    }
    
    public Boolean validacionDatosCliente(Cliente c) {
		return null;
    }
    
    public Boolean validacionCUIL(Cliente c) {
    	return null;
    }
    
    public Boolean comprobarEdad(Cliente c) {
    	return null;
    }
    
    public void actualizarCondicion(Cliente c, EnumCondicion e) {

    }
    
    public Boolean validarFechaVigencia(Cliente c) {
    	return null;
    }
    
    public void generarNumeroCliente(Cliente c) {

    }
    
    public Cliente actualizarCliente(Cliente c) {
    	return null;
    }

    public Cliente getCliente(Long numeroCliente) {
    	Cliente cliente = ClienteDAO.getDAO().getCliente(numeroCliente);
    	return cliente;
    }
    
    public List<Poliza> getPoliza(Long numeroCliente) {
    	return null;
    }

	public List<Cliente> buscarClientes(Long numeroCliente, String apellido, String nombre, EnumTipoDocumento tipoDocumento, Integer numeroDocumento) {
    	//TODO buscar campos por partes, es decir si coincidice alguna parte retonar
    	String condicionesConsulta = "";
    	Boolean cantidadParametros = false;
    	
    	if(numeroCliente != null) {
    		if(condicionesConsulta.isEmpty()) {
    			condicionesConsulta += " where";
    		}
    		condicionesConsulta += " numero_cliente="+numeroCliente;
    		cantidadParametros = true;
    	}
    	
    	if(apellido != null) {
    		if(condicionesConsulta.isEmpty()) {
    			condicionesConsulta += " where";
    		}
    		
    		if(cantidadParametros.booleanValue() == true) {
    			condicionesConsulta += " and apellido = '"+apellido+"' ";
    		}
    		else {
    			condicionesConsulta += " apellido = '"+apellido+"' ";
    		}
    		cantidadParametros = true;
    	}
    	
    	if(nombre != null) {
    		if(condicionesConsulta.isEmpty()) {
    			condicionesConsulta += " where";
    		}
    		
    		if(cantidadParametros.booleanValue() == true) {
    			condicionesConsulta += " and nombre = '"+nombre+"'";
    		}
    		else {
    			condicionesConsulta += " nombre = '"+nombre+"'";
    		}
    		cantidadParametros = true;
    	}
    	
    	if(tipoDocumento != null) {
    		if(condicionesConsulta.isEmpty()) {
    			condicionesConsulta += " where";
    		}
    		
    		if(cantidadParametros.booleanValue() == true) {
    			condicionesConsulta += " and tipo_documento = '"+tipoDocumento+"' ";
    		}
    		else {
    			condicionesConsulta += " tipo_documento = '"+tipoDocumento+"' ";
    		}
    		cantidadParametros = true;
    	}
    	
    	if(numeroDocumento != null) {
    		if(condicionesConsulta.isEmpty()) {
    			condicionesConsulta += " where ";
    		}
    		
    		if(cantidadParametros.booleanValue() == true) {
    			condicionesConsulta += " and numero_documento = "+numeroDocumento;
    		}
    		else {
    			condicionesConsulta += " numero_documento = "+numeroDocumento;
    		}
    	}
    	
    	return ClienteDAO.getDAO().getClientes(condicionesConsulta);
    }
    
}
