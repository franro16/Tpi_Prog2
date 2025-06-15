package models;

public enum Resultado {
    GANADOR_BOXEADOR1,
    GANADOR_BOXEADOR2;

    public String mostrarResultado() {
        switch (this) {
            case GANADOR_BOXEADOR1:
                return "Ganó el Boxeador 1";
            case GANADOR_BOXEADOR2:
                return "Ganó el Boxeador 2";
        }
        throw new IllegalStateException("Resultado no reconocido: " + this);
    }
}



