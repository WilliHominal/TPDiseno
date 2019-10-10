package isi.dds.tp.gestor;

import java.util.List;
import isi.dds.tp.dao.TipoCoberturaDAO;
import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.modelo.RiesgoTipoCobertura;

public class GestorTipoCobertura {
	
	private static GestorTipoCobertura instanciaGestor = null;
	 
    private GestorTipoCobertura() {

    }

    public static GestorTipoCobertura get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorTipoCobertura();
        }    
        return instanciaGestor;
    }
    
    
    public void addTipoCobertura(TipoCobertura t) {
    	
    	TipoCoberturaDAO.getDAO().addTipoCobertura(t);
    	
    }
    
    public void addRiesgoCobertura(RiesgoTipoCobertura r) {
    
    	TipoCoberturaDAO.getDAO().addRiesgoCobertura(r);
    }
    
    
	public List<TipoCobertura> getTiposCobertura(){
    	
    	List<TipoCobertura> tiposCoberturas = null;
    	
    	tiposCoberturas = TipoCoberturaDAO.getDAO().getTiposCobertura();
    	
    	return tiposCoberturas;
    }
    
  
	public List<RiesgoTipoCobertura> getRiesgosCobertura(EnumTipoCobertura tipo) {
    	
		List<RiesgoTipoCobertura> riesgos = null;
    	
		riesgos = TipoCoberturaDAO.getDAO().getRiesgosCobertura(tipo);
    	
    	return riesgos;
    }
    
    public RiesgoTipoCobertura getUltimoRiesgoTipoCobertura(EnumTipoCobertura tipo) {
    	
    	RiesgoTipoCobertura riesgo = null;
    	
    	riesgo = TipoCoberturaDAO.getDAO().getUltimoRiesgoTipoCobertura(tipo);
    	
    	return riesgo;
    }
}
