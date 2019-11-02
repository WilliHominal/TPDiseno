package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.dao.DAOCliente;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.enums.EnumTipoDocumento;

public class GestorCliente {
	
	private static GestorCliente instanciaGestor = null;
	
	private GestorPoliza gestorPoliza = GestorPoliza.get();
	 
    private GestorCliente() { }

    public static GestorCliente get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }    
        return instanciaGestor;
    }
    
	public void cargarClientes() {
		DAOCliente.getDAO().cargarClientes();
	}
    
    public void actualizarCliente(Cliente cliente, Poliza poliza) {
    	if(cliente.getPolizas() == null) {
			cliente.setPolizas(new ArrayList<Poliza>());
		}
    	actualizarCondicion(cliente); 
    	cliente.getPolizas().add(poliza);
    }
    
    public Cliente getCliente(Long numeroCliente) {
    	Cliente cliente = DAOCliente.getDAO().getCliente(numeroCliente);
    	return cliente;
    }
    
    public List<Poliza> getPoliza(Long numeroCliente) {
    	return null;
    }
    
    public void actualizarCondicion(Cliente cliente) {
    	if(cliente.getPolizas().size() == 0) {
    		cliente.setCondicion(EnumCondicion.NORMAL);
    	}else {
    		Boolean polizasVigentes = gestorPoliza.vigenciaPolizas(cliente.getNumeroCliente());
    		if(!polizasVigentes) {
    			cliente.setCondicion(EnumCondicion.NORMAL);
    		}
    		else {
    			Boolean noTieneSiniestros = GestorSubsistemaSiniestros.get().getSiniestroUltimosAnios(cliente.getTipoDocumento(), cliente.getNumeroDocumento(), LocalDate.now().getYear()).equals(EnumSiniestros.NINGUNO);
    			Boolean cuotasImpagas = gestorPoliza.omisionPago(cliente.getNumeroCliente());
    			Boolean esClienteActivo = 500 < 365*2;
    			if(!noTieneSiniestros && cuotasImpagas && esClienteActivo) {
    				cliente.setCondicion(EnumCondicion.NORMAL);
    			}
    			else{
    				cliente.setCondicion(EnumCondicion.PLATA);
    			}
    		}
    	}
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
