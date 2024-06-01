package br.edu.fesa.gerenciador_gado.Util.Enums;

public enum RacaGadoEnum {
    
    NELORE("NELORE"),
    ANGUS("ANGUS"),
    HEREFORD("HEREFORD"),
    BRAHMAN("BRAHMAN"),
    CHAROLES("CHAROLES"),
    HOLANDESA("HOLANDESA"),
    JERSEY("JERSEY"),
    PARDO_SUICA("PARDO_SUICA"),
    GIR("GIR"),
    GUERNSEY("GUERNSEY"),
    SIMENTAL("SIMENTAL"),
    NORMANDA("NORMANDA"),
    DEVON("DEVON");

    private final String racaValue;

    RacaGadoEnum(String value) {
        racaValue = value;
    }

    public String getValue() {
        return racaValue;
    }
    
    public static RacaGadoEnum fromValue(String value) {
        for (RacaGadoEnum raca : RacaGadoEnum.values()) {
            if (raca.getValue() == value) {
                return raca;
            }
        }
        throw new IllegalArgumentException("Valor inválido para ProfileEnum: " + value);
    }
}
