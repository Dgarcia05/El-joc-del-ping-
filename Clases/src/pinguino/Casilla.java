package pinguino;

public class Casilla {
    public enum Tipo { NORMAL, AGUJERO, TRINEO, EVENTO }

    private int posicion;
    private Tipo tipo;

    public Casilla(int posicion, Tipo tipo) {
        this.posicion = posicion;
        this.tipo = tipo;
    }

    public void activar(Jugador jugador, Juego juego) {
        switch (tipo) {
            case AGUJERO -> {
                int actual = jugador.getPosicion();
                int anterior = juego.getTablero().obtenerAgujeroAnterior(actual);
                if (anterior >= 0) {
                    jugador.setPosicion(anterior);
                    System.out.println("¡Caíste en un agujero! Retrocedes al agujero anterior en la casilla " + anterior);
                } else {
                    jugador.setPosicion(0);
                    System.out.println("¡Caíste en el primer agujero! Regresas al inicio.");
                }
            }
            case TRINEO -> {
                int destino = Math.min(49, jugador.getPosicion() + 3);
                jugador.setPosicion(destino);
                System.out.println("¡Te subiste a un trineo! Avanzas a la casilla " + destino);
            }
            case EVENTO -> {
                System.out.println("¡Evento misterioso!");
                Eventos.generarEvento(jugador);
            }
            case NORMAL -> {
                System.out.println("Casilla normal. Nada sucede.");
            }
        }
    }


    public Tipo getTipo() {
        return tipo;
    }

    public int getPosicion() {
        return posicion;
    }
}
