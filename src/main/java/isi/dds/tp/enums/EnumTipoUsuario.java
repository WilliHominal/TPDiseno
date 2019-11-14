package isi.dds.tp.enums;

public enum EnumTipoUsuario {
	PRODUCTOR_SEGURO("Productor seguro"),
	COBRADOR("Cobrador"),
	GERENCIA("Gerencia");
	
    private String value;

    private EnumTipoUsuario(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumTipoUsuario getEnum(String value) {
        for(EnumTipoUsuario v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
