package isi.dds.tp.enums;

public enum EnumCondicion {
	ACTIVO("Activo"),
	NORMAL("Normal"),
	PLATA("Plata");
	
	private String value;

    private EnumCondicion(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumCondicion getEnum(String value) {
        for(EnumCondicion v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
