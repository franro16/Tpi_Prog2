package models;

public enum FaseTorneo {
    OCTAVOS,
    CUARTOS,
    SEMIFINAL,
    FINAL;
    public FaseTorneo siguienteFase() {
        switch (this) {
            case OCTAVOS:
                return CUARTOS;
            case CUARTOS:
                return SEMIFINAL;
            case SEMIFINAL:
                return FINAL;
            case FINAL:
                return FINAL; // no hay siguiente
            default:
                return null;
        }
    }


}
