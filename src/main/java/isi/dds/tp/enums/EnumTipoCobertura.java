package isi.dds.tp.enums;

public enum EnumTipoCobertura {
	RESPONSABILIDAD_CIVIL("Responsabilidad civil"),
	RESP_CIVIL_ROBO_O_INCENDIO_TOTAL("Resp. civil, robo o incendio total"),
	TODO_TOTAL("Todo total"),
	TERCEROS_COMPLETOS("Terceros completos"),
	TODO_RIESGO_CON_FRANQUICIA("Todo riesgo con franquicia");
	
    private String value;

    private EnumTipoCobertura(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumTipoCobertura getEnum(String value) {
        for(EnumTipoCobertura v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
