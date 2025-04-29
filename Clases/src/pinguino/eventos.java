package pinguino;
import java.util.Random;

public class eventos {
	public static void main(String[] args) {
int evento = random.nextInt(100); // 0-99

        if (evento < 50) {
            if (random.nextBoolean()) {
                jugador.getInventario().agregarPez();
                System.out.println(jugador.getNombre() + " ha obtenido un pez.");
            } else {
                int bolas = random.nextInt(3) + 1; // 1 a 3 bolas
                jugador.getInventario().agregarBolas(bolas);
                System.out.println(jugador.getNombre() + " ha obtenido " + bolas + " bolas de nieve.");
            }
        } else {
            jugador.getInventario().agregarDado();

            if (random.nextBoolean()) {
                System.out.println(jugador.getNombre() + " ha obtenido un dado rápido (avanza 5-10 casillas).");
            } else {
                System.out.println(jugador.getNombre() + " ha obtenido un dado lento (1-3 casillas).");
            }
        }
}
}
