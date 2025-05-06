package pinguino;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántos jugadores? ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();  

        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 0; i < numJugadores; i++) {
            System.out.print("Introduce el nombre del jugador " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            jugadores.add(new Jugador(nombre));
        }

        Juego juego = new Juego(jugadores);
        juego.iniciar();
    }
}
