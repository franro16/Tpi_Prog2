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
    //esto para que quede mas prolijo el nombre en pantalla
    public String nombreMostrar() {
        switch (this) {
            case OCTAVOS:
                return "Octavos de Final";
            case CUARTOS:
                return "Cuartos de Final";
            case SEMIFINAL:
                return "Semifinales";
            case FINAL:
                return "Final";
            default:
                return "Desconocido";
        }
    }
}
