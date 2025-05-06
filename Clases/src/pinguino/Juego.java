package pinguino;

import modelo.*;
import java.util.*;

public class Juego {
	private Tablero tablero;
	private List<Jugador> jugadores;
	private Map<String, Integer> posicionesTrineo = new HashMap<>();

	private Scanner scanner = new Scanner(System.in);

	public Juego(List<Jugador> jugadores) {
		this.tablero = new Tablero();
		this.jugadores = jugadores;
	}

	public void iniciar() {
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            for (Jugador jugador : jugadores) {
                System.out.println("Turno de " + jugador.getNombre() + " (posición: " + jugador.getPosicion() + ")");
                System.out.println("Inventario: Dados = " + jugador.getInventario().getDados() +
                                   ", Peces = " + jugador.getInventario().getPeces() +
                                   ", Bolas de nieve = " + jugador.getInventario().getBolas());

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
                    System.out.println("jugador.getNombre() + " ha ganado el juego!");
                    juegoTerminado = true;
                    break;
                }
            }
        }
    }

	public void turno(Jugador jugador, String tipoDado) {
		int tirada = 0;

		if (tipoDado.equals("especial")) {
			if (jugador.getInventario().getDados() <= 0) {
				System.out.println("No tienes dados especiales. Usando dado normal.");
				tirada = Dado.tirarNormal();
			} else {
				jugador.getInventario().añadirDado(); // Gastamos uno
				int probabilidad = new Random().nextInt(100);
				if (probabilidad < 30) {
					System.out.println("Usaste un dado rápido!");
					tirada = Dado.tirarRapido();
				} else {
					System.out.println("Usaste un dado lento.");
					tirada = Dado.tirarLento();
				}
			}
		} else {
			tirada = Dado.tirarNormal();
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
		if (lanzador.getInventario().getBolas() <= 0) {
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
		int nuevaPos = Math.max(0, objetivo.getPosicion() - 2);
		objetivo.setPosicion(nuevaPos);
		lanzador.getInventario().añadirBola(); // gasta bola
		System.out.println(lanzador.getNombre() + " lanzó una bola de nieve a " + objetivo.getNombre()
				+ ", que retrocede a la casilla " + nuevaPos);
	}

	public int buscarForadoAnterior(int actual) {
		for (int i = actual - 1; i >= 0; i--) {
			if (tablero.getCasilla(i) instanceof modelo.CasillaForado) {
				return i;
			}
		}
		return 0;
	}

	public int buscarTrineoSiguiente(int actual) {
		for (int i = actual + 1; i < 50; i++) {
			if (tablero.getCasilla(i) instanceof modelo.CasillaTrineo) {
				return i;
			}
		}
//		return actual;
	}

	public void activarEventoAleatorio(Jugador jugador) {
		int aleatorio = new Random().nextInt(100);
		if (aleatorio < 30) {
			jugador.getInventario().añadirPez();
			System.out.println("Evento: ¡Has obtenido un pez!");
		} else if (aleatorio < 60) {
			jugador.getInventario().añadirBola();
			System.out.println("Evento: ¡Has obtenido entre 1 y 3 bolas de nieve!");
		} else {
			jugador.getInventario().añadirDado();
			System.out.println("Evento: ¡Has obtenido un dado especial!");
		}
	}
}
