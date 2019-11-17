package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import isi.dds.tp.dao.DAOPago;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.modelo.Cuota;
import isi.dds.tp.modelo.Pago;

public class GestorPago {
	
	private static GestorPago instanciaGestor = null;
	 
    private GestorPago() { }

    public static GestorPago get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPago();
        }    
        return instanciaGestor;
    }
    
    public void registrarPago (List<Cuota> cuotasApagar, Float importe) {
    	for(Cuota cuota: cuotasApagar) {
    		cuota.setEstado(EnumEstadoCuota.PAGO);
    	}
    	Pago pago = new Pago();
    	pago.setCuotas(cuotasApagar);
    	pago.setFechaPago(LocalDate.now());
    	pago.setHora(LocalTime.now());
    	pago.setOperador("Operador n");
    	pago.setImporte(importe);
    	DAOPago.getDAO().addPago(pago);    	
    }
}
