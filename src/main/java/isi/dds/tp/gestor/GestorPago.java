package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import isi.dds.tp.dao.DAOPago;
import isi.dds.tp.dao.DAOPoliza;
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
    
    public void registrarPago (List<Cuota> cuotasApagar, Float monto) {
		Pago pago = new Pago();
    	pago.setFechaPago(LocalDate.now());
    	pago.setHora(LocalTime.now());
    	pago.setOperador("Operador n");
    	pago.setImporte(monto);
    	DAOPago.getDAO().addPago(pago);
    	
		//TODO CU12 implementar logica en gestor poliza diria, que calcule los valores atrasados y los bonificados y setearlos en cuota (junto con estadopagocuota, bonificacion etc) y despues setearlos en la view
		//CApaz haya que hacerlo directamente en el controller1, y habria que modificar el diagrama de secuencias que actualiza las cuotas ahi, no se bien
		for(Cuota cuota:cuotasApagar) {
			cuota.setPago(pago);
			cuota.setEstado(EnumEstadoCuota.PAGO);
			DAOPoliza.getDAO().updateCuota(cuota);
    	}
    }
	
	/*
	public Cuota ultimoPago(Poliza poliza) {
    	
    	List<Cuota> cuotas = new ArrayList<Cuota>();
    	List<Cuota> cuotasPago = new ArrayList <Cuota>();
    	cuotas = poliza.getCuotas();
    	
    	
    	
    	for (Cuota cuota : cuotas)
			if (cuota.getEstado() == EnumEstadoCuota.PAGO)
				cuotasPago.add(cuota);
  	
    //	int auxiliar=0;
    	if(cuotasPago.size()>0) {
    		Cuota ultimoPago = cuotasPago.get(0);
    		String fecha= ultimoPago.getPago().getFechaPago().toString();
	    	for(int i=1; i<cuotasPago.size(); i++) {
	    		if(fecha.compareTo(cuotasPago.get(i).getPago().getFechaPago().toString()) < 0) {
	    			fecha = cuotasPago.get(i).getPago().getFechaPago().toString();
	    			ultimoPago = cuotasPago.get(i);
	    	//		auxiliar = i;
	    		}
	    	}
	    	return ultimoPago;
    	}
    	else
    		return null; 
    	
	}
*/
}
	

	