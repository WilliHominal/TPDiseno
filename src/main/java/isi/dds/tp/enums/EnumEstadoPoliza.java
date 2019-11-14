package isi.dds.tp.enums;

public enum EnumEstadoPoliza {
	GENERADA("Generada"),
	NO_VIGENTE("No vigente"),
	VIGENTE("Vigente"),
	SUSPENDIDA("Suspendida");
	
	private String value;

    private EnumEstadoPoliza(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumEstadoPoliza getEnum(String value) {
        for(EnumEstadoPoliza v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
