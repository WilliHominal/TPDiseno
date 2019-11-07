package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;
import java.util.List;
import isi.dds.tp.dao.DAOCliente;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.hibernate.HibernateUtil;

public class GestorCliente {
	private static GestorCliente instanciaGestor = null;
	 
    private GestorCliente() { }

    public static GestorCliente get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }   
        return instanciaGestor;
    }
    
    public Cliente getCliente(Long numeroCliente) {
    	return DAOCliente.getDAO().getCliente(numeroCliente);
    }
    
    public void actualizarCliente(Cliente cliente, Poliza poliza) {    	
    	if(cliente.getPolizas().size() <= 1) {
    		//la cantidad es uno, porque se actualiza al cliente luego de añadirle la póliza, con lo cuál ya tendría al menos una póliza
    		cliente.setCondicion(EnumCondicion.NORMAL);
    	}else {
    		Boolean polizasVigentes = GestorPoliza.get().vigenciaPolizas(cliente.getNumeroCliente());
    		if(!polizasVigentes) {
    			cliente.setCondicion(EnumCondicion.NORMAL);
    		}
    		else {
    			Boolean tieneSiniestros = !poliza.getNumerosSiniestrosUltimoAnios().equals(EnumSiniestros.NINGUNO);
    			Boolean cuotasImpagas = GestorPoliza.get().omisionPago(cliente.getNumeroCliente());
    			Boolean esClienteActivo = clienteActivo();
    			if(tieneSiniestros || cuotasImpagas || !esClienteActivo) {
    				cliente.setCondicion(EnumCondicion.NORMAL);
    			}
    			else{
    				cliente.setCondicion(EnumCondicion.PLATA);
    			}
    		}
    	}
    	HibernateUtil.shutdown();
    	DAOCliente.getDAO().updateCliente(cliente);
    }

	public Boolean clienteActivo() {
		// TODO CU01 - implementar
		//si el cliente tiene la primer
		return false;
	}

	public List<Cliente> buscarClientes(Long numeroCliente, String apellido, String nombre, EnumTipoDocumento tipoDocumento, String numeroDocumento) {
    	String condicionesConsulta = "";
    	if(numeroCliente != null) {
    		condicionesConsulta += " and numero_cliente="+numeroCliente;
    	}
    	
    	if(!apellido.isEmpty()) {
    		condicionesConsulta += " and to_ascii(apellido, 'latin1') ilike  to_ascii('"+apellido+"%', 'latin1') ";
    	}
    	
    	if(!nombre.isEmpty()) {
   			condicionesConsulta += " and to_ascii(nombre, 'LATIN1') ilike TO_ASCII('"+nombre+"%', 'latin1') ";
    	}
    	
    	if(tipoDocumento != null) {
    		condicionesConsulta += " and tipo_documento = '"+tipoDocumento+"' ";
    	}
    	
    	if(!numeroDocumento.isEmpty()) {
    		condicionesConsulta += " and documento = '"+numeroDocumento+"' ";
    	}
    	condicionesConsulta += " order by numero_cliente asc";
    	return DAOCliente.getDAO().getClientes(condicionesConsulta);
    }
}
