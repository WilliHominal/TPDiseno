package isi.dds.tp.gestor;

public class GestorSuperIntendenciaSeguros {
	
	private static GestorSuperIntendenciaSeguros instanciaGestor = null;
	 
    private GestorSuperIntendenciaSeguros() {

    }

    public static GestorSuperIntendenciaSeguros get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorSuperIntendenciaSeguros();
        }    
        return instanciaGestor;
    }

}
