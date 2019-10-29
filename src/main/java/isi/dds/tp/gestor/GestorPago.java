package isi.dds.tp.gestor;

public class GestorPago {
	
	private static GestorPago instanciaGestor = null;
	 
    private GestorPago() { }

    public static GestorPago get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPago();
        }    
        return instanciaGestor;
    }
}
