package isi.dds.tp.gestor;

public class GestorSubsistemaSiniestros {

	private static GestorSubsistemaSiniestros instanciaGestor = null;
	 
    private GestorSubsistemaSiniestros() {

    }

    public static GestorSubsistemaSiniestros get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorSubsistemaSiniestros();
        }    
        return instanciaGestor;
    }

	public int getSiniestrosUltimosAnios(Integer numeroDocumento) {
		// TODO se usa para cuando se da de alta un cliente y se actualiza cada tanto o hay que generar una tabla por dni de los clientes
		return 0;
	}
	
	
}
