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
                System.out.println("Inventario: Dados = " + jugador.getInventario().getDados() +
                                   ", Peces = " + jugador.getInventario().getPeces() +
                                   ", Bolas de nieve = " + jugador.getInventario().getBolasDeNieve());

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
        tablero.getCasilla(jugador.getPosicion()).activar(jugador, this);

        if (tipoDado.equals("especial")) {
            if (jugador.getInventario().getDados() <= 0) {
                System.out.println("No tienes dados especiales. Usando dado normal.");
                Dado.DadoNormal();  
                tirada = Dado.getResultado();
            } else {
                jugador.getInventario().agregarDado(); 
                int probabilidad = new Random().nextInt(100);
                if (probabilidad < 30) {
                    System.out.println("Usaste un dado rápido!");
                    Dado.DadoRapido();
                    tirada = Dado.getResultado();
                } else {
                    System.out.println("Usaste un dado lento.");
                    Dado.DadoLento();
                    tirada = Dado.getResultado();
                }
            }
        } else {
            Dado.DadoNormal();
            tirada = Dado.getResultado();
        }

        int nuevaPos = jugador.getPosicion() + tirada;
        System.out.println(jugador.getNombre() + " avanza " + tirada + " casillas.");

        if (nuevaPos >= 49) {
            jugador.setPosicion(49);
        } else {
            jugador.setPosicion(nuevaPos);
            tablero.getCasilla(nuevaPos).activar(jugador, this);
        }
    }

    public void lanzarBolaNieve(Jugador lanzador) {
        if (lanzador.getInventario().getBolasDeNieve() <= 0) {
            System.out.println("No tienes bolas de nieve.");
            return;
        }

        System.out.println("¿A qué jugador quieres lanzar la bola de nieve?");
        for (int i = 0; i < jugadores.size(); i++) {
            if (!jugadores.get(i).equals(lanzador)) {
                System.out.println((i + 1) + ". " + jugadores.get(i).getNombre() + " (pos: "
                        + jugadores.get(i).getPosicion() + ")");
            }
        }

        int seleccion = scanner.nextInt();
        if (seleccion < 1 || seleccion > jugadores.size() || jugadores.get(seleccion - 1).equals(lanzador)) {
            System.out.println("Selección inválida.");
            return;
        }

        Jugador objetivo = jugadores.get(seleccion - 1);
        objetivo.getInventario().agregarBolas(-1); // Gastamos una bola
        objetivo.setPosicion(Math.max(0, objetivo.getPosicion() - 3));  // Retrocede 3 casillas
        System.out.println(lanzador.getNombre() + " ha lanzado una bola de nieve a " + objetivo.getNombre() + 
                           ". " + objetivo.getNombre() + " retrocede 3 casillas.");
    }
}
