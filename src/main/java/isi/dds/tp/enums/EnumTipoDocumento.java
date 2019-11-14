package isi.dds.tp.enums;

public enum EnumTipoDocumento {
	DNI("DNI"),
	PASAPORTE("Pasaporte"),
	LIBRETA_CIVIL("Libreta civil"),
	LIBRETA_DE_ENROLAMIENTO("Libreta de enrolamiento");
	
    private String value;

    private EnumTipoDocumento(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumTipoDocumento getEnum(String value) {
        for(EnumTipoDocumento v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
