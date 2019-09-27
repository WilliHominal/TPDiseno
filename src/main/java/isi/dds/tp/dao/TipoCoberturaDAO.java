package isi.dds.tp.dao;

public class TipoCoberturaDAO {
	
	private static TipoCoberturaDAO instanciaDAO = null;
	 
    private TipoCoberturaDAO() {

    }

    public static TipoCoberturaDAO getTipoCoberturaDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new TipoCoberturaDAO();
        }    
        return instanciaDAO;
    }

}
