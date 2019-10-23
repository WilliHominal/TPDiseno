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

	public List<Cliente> buscarClientes(Long numeroCliente, String apellido, String nombre, EnumTipoDocumento tipoDocumento, String numeroDocumento) {
    	String condicionesConsulta = "";
    	
    	if(numeroCliente != null) {
    		condicionesConsulta += " and numero_cliente="+numeroCliente;
    	}
    	
    	if(apellido != null) {
    		condicionesConsulta += " and to_ascii(apellido, 'latin1') ilike  to_ascii('"+apellido+"%', 'latin1') ";

    	}
    	
    	if(nombre != null) {
   			condicionesConsulta += " and to_ascii(nombre, 'LATIN1') ilike TO_ASCII('"+nombre+")%', 'latin1') ";
    	}
    	
    	if(tipoDocumento != null) {
    		condicionesConsulta += " and tipo_documento = '"+tipoDocumento+"' ";
    	}
    	
    	if(numeroDocumento != null) {
    		condicionesConsulta += " and documento = '"+numeroDocumento+"' ";
    	}

    	condicionesConsulta += " order by numero_cliente asc";
	
    	
    	return ClienteDAO.getDAO().getClientes(condicionesConsulta);
    }
    
}
