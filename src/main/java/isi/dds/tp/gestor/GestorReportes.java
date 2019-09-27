package isi.dds.tp.gestor;

public class GestorReportes {
	
	private static GestorReportes instanciaGestor = null;
	 
    private GestorReportes() {

    }

    public static GestorReportes getGestorReportes() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorReportes();
        }    
        return instanciaGestor;
    }

}
