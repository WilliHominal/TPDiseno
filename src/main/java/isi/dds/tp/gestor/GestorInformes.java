package isi.dds.tp.gestor;

public class GestorInformes {
	
	private static GestorInformes instanciaGestor = null;
	 
    private GestorInformes() {

    }

    public static GestorInformes get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorInformes();
        }    
        return instanciaGestor;
    }

}
