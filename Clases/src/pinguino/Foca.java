package pinguino;

public class Foca {
    private boolean soborno;

    public Foca() {
        this.soborno = false;
    }

    public boolean isSoborno() {
        return soborno;
    }

    public void setSoborno(boolean soborno) {
        this.soborno = soborno;
    }

    // El jugador es aplastado: vuelve al inicio
    public void aplastarJugador(Jugador p) {
        if (!soborno) {
            p.setPosicion(0);
            System.out.println("¡La foca ha aplastado al pingüino! Regresa al inicio.");
        } else {
            System.out.println("La foca fue sobornada. No aplasta al pingüino.");
        }
    }

    // El jugador es golpeado: retrocede 3 casillas
    public void golpearJugador(Jugador p) {
        if (!soborno) {
            int nuevaPos = Math.max(0, p.getPosicion() - 3);
            p.setPosicion(nuevaPos);
            System.out.println("¡La foca golpeó al pingüino! Retrocede 3 casillas.");
        } else {
            System.out.println("La foca fue sobornada. No golpea al pingüino.");
        }
    }

    // Activar soborno
    public void esSobornado() {
        this.soborno = true;
        System.out.println("La foca ha sido sobornada. No atacará al pingüino.");
    }
}
