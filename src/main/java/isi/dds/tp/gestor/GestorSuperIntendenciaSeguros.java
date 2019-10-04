package isi.dds.tp.gestor;

public class GestorSuperIntendenciaSeguros {
	
	private static GestorSuperIntendenciaSeguros instanciaGestor = null;
	 
    private GestorSuperIntendenciaSeguros() {

    }

    public static GestorSuperIntendenciaSeguros getGestorSuperIntendenciaSeguros() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorSuperIntendenciaSeguros();
        }    
        return instanciaGestor;
    }

	public Float getSumaAsegurada(String nombre, String nombre2, Integer anio) {
		// TODO Auto-generated method stub
		return null;
	}

}
