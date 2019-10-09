package isi.dds.tp.gestor;

public class GestorBitacora {

	private static GestorBitacora instanciaGestor = null;
	 
    private GestorBitacora() {

    }
    
    public static GestorBitacora get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorBitacora();
        }    
        return instanciaGestor;
    }
}
