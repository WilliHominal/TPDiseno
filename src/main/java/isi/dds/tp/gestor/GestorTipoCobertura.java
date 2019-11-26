package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.List;
import isi.dds.tp.dao.DAOTipoCobertura;
import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.modelo.RiesgoTipoCobertura;

public class GestorTipoCobertura {
	
	private static GestorTipoCobertura instanciaGestor = null;
	 
    private GestorTipoCobertura() { }

    public static GestorTipoCobertura get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTipoCobertura();
        }    
        return instanciaGestor;
    }
    
    public RiesgoTipoCobertura newRiesgoCobertura(EnumTipoCobertura enumTipoCobertura, Float riesgo) {
    	TipoCobertura tipoCobertura = getTipoCobertura(enumTipoCobertura);
    	RiesgoTipoCobertura rviejo = getUltimoRiesgoTipoCoberturaAux(tipoCobertura.getTipoCobertura());
    	rviejo.setFechaFinVigencia(LocalDate.now());
    	
    	RiesgoTipoCobertura rnuevo = new RiesgoTipoCobertura();
    	rnuevo.setFechaInicioVigencia(LocalDate.now());
    	rnuevo.setValorPorcentual(riesgo);  
    	tipoCobertura.getRiesgo().add(rnuevo); 
    	DAOTipoCobertura.getDAO().updateTipoCobertura(tipoCobertura);
    	return rnuevo;
    }
	
	public void updateTipoCobertura(TipoCobertura tipo){
		DAOTipoCobertura.getDAO().updateTipoCobertura(tipo);
    }

	public TipoCobertura getTipoCobertura(EnumTipoCobertura tipo){
		return DAOTipoCobertura.getDAO().getTipoCobertura(tipo);
    }
	
	public List<TipoCobertura> getTiposCobertura(){
		return DAOTipoCobertura.getDAO().getTiposCobertura();
    }
    
    public Float getUltimoRiesgoTipoCobertura(EnumTipoCobertura tipo) {
    	return getUltimoRiesgoTipoCoberturaAux(tipo).getValorPorcentual(); 
    }
    
    private RiesgoTipoCobertura getUltimoRiesgoTipoCoberturaAux(EnumTipoCobertura tipo) {
    	return DAOTipoCobertura.getDAO().getUltimoRiesgoTipoCobertura(tipo.name()); 
    }
}
