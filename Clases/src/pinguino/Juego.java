package pinguino;

import java.util.*;

public class Juego {
    private Tablero tablero;
    private List<Jugador> jugadores;

    private Scanner scanner = new Scanner(System.in);

    public Juego(List<Jugador> jugadores) {
        this.tablero = new Tablero(50);
        this.jugadores = jugadores;
    }
    public Tablero getTablero() {
        return tablero;
    }

    public void iniciar() {
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            for (Jugador jugador : jugadores) {
            	System.out.println();
            	System.out.println();
                System.out.println("Turno de " + jugador.getNombre() + " (posición: " + jugador.getPosicion() + ")");
            

                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. Tirar dado normal");
                System.out.println("2. Tirar dado especial (rápido o lento)");
                System.out.println("3. Lanzar bola de nieve a otro jugador");
                System.out.print("Opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> turno(jugador, "normal");
                    case 2 -> turno(jugador, "especial");
                    case 3 -> lanzarBolaNieve(jugador);
                    default -> {
                        System.out.println("Opción no válida. Se tira dado normal por defecto.");
                        turno(jugador, "normal");
                    }
                }

                if (jugador.getPosicion() >= 49) {
                    System.out.println(jugador.getNombre() + " ha ganado el juego!");
                    juegoTerminado = true;
                    break;
                }
            }
        }
    }

    public void turno(Jugador jugador, String tipoDado) {
        int tirada = 0;

        if (tipoDado.equals("especial")) {
            // Intentar usar dado rápido primero
            if (jugador.getInventario().getDadosRapidos() > 0) {
                jugador.getInventario().usarDadoRapido();  // Descontar dado rápido
                System.out.println("Dado rápido!");
                Dado.DadoRapido();
                tirada = Dado.getResultado();
            } 
            // Si no hay dados rápidos, intentar dado lento
            else if (jugador.getInventario().getDadosLentos() > 0) {
                jugador.getInventario().usarDadoLento();  // Descontar dado lento
                System.out.println("Dado lento.");
                Dado.DadoLento();
                tirada = Dado.getResultado();
            } 
            // Si no hay dados especiales, tirar dado normal
            else {
                System.out.println("No tienes dados especiales. Tirando dado normal.");
                Dado.DadoNormal();
                tirada = Dado.getResultado();
            }
        } else {
            Dado.DadoNormal();
            tirada = Dado.getResultado();
        }

        int nuevaPos = jugador.getPosicion() + tirada;
        if (nuevaPos >= 49) {
            jugador.setPosicion(49);
            System.out.println(jugador.getNombre() + " ha llegado a la meta!");
        } else {
            jugador.setPosicion(nuevaPos);
            tablero.getCasilla(nuevaPos).activar(jugador, this);
        }
    }




    public void lanzarBolaNieve(Jugador lanzador) {
        if (lanzador.getInventario().getBolasDeNieve() <= 0) {
            System.out.println("No tienes bolas de nieve para lanzar.");
            return;
        }

        // Restar una bola de nieve
        lanzador.getInventario().agregarBolas(-1);

        System.out.println("¿A quién quieres lanzar la bola de nieve?");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            if (!j.equals(lanzador)) {
                System.out.println(i + ": " + j.getNombre());
            }
        }

        Scanner scanner = new Scanner(System.in);
        int indiceObjetivo = scanner.nextInt();

        if (indiceObjetivo < 0 || indiceObjetivo >= jugadores.size() || jugadores.get(indiceObjetivo).equals(lanzador)) {
            System.out.println("Selección inválida.");
            return;
        }

        Jugador objetivo = jugadores.get(indiceObjetivo);
        int nuevaPos = objetivo.getPosicion() - 3;  // Ejemplo: retrocede 3 casillas
        if (nuevaPos < 0) nuevaPos = 0;
        objetivo.setPosicion(nuevaPos);

        System.out.println(lanzador.getNombre() + " lanzó una bola de nieve a " + objetivo.getNombre() + ". " +
                           objetivo.getNombre() + " retrocede a la casilla " + nuevaPos);
    }

	public List<Jugador> getJugadores() {
	
		return jugadores;
	}
}
