package isi.dds.tp.gestor;

public class GestorSistemaFinanciero {
	
	private static GestorSistemaFinanciero instanciaGestor = null;
	 
    private GestorSistemaFinanciero() {

    }

    public static GestorSistemaFinanciero get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorSistemaFinanciero();
        }    
        return instanciaGestor;
    }

}
