package isi.dds.tp.modelo;

public class Impresora {
	private static Impresora impresora = null;
	 
    private Impresora() { }

    public static Impresora getGestorBitacora() {
        if (impresora == null){
        	impresora = new Impresora();
        }    
        return impresora;
    }
}
