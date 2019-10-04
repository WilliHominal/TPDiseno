package isi.dds.tp.gestor;

public class GestorSubsistemaSiniestros {

	private static GestorSubsistemaSiniestros instanciaGestor = null;
	 
    private GestorSubsistemaSiniestros() {

    }

    public static GestorSubsistemaSiniestros getGestorSubsistemaSiniestros() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorSubsistemaSiniestros();
        }    
        return instanciaGestor;
    }

	public int getSiniestrosUltimosAnios(Integer numeroDocumento) {
		// TODO Auto-generated method stub
		return 0;
	}
}
