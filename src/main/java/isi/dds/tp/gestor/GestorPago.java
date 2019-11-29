package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.dao.DAOPago;
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
    
    public void registrarPago (List<Cuota> cuotasApagar, Float monto) {
		Pago pago = new Pago();
    	pago.setFechaPago(LocalDate.now());
    	pago.setHora(LocalTime.now());
    	pago.setOperador("Operador n");
    	pago.setImporte(monto);
    	pago.setPremio(cuotasApagar.get(0).getPoliza().getValorPremio());
    	pago.setCuotas(new ArrayList<Cuota>());
		for(Cuota cuota:cuotasApagar) {
			GestorPoliza.get().pagarCuota(cuota, pago);
			pago.getCuotas().add(cuota);
		}			
		DAOPago.getDAO().addPago(pago);
    }
    
    public void calcularRedondeos() {
    	//TODO falta
    }
}
	

	