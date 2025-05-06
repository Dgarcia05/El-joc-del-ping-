package pinguino;

import java.util.Random;

public class dado {
    private static int resultado;
    private static Random rand = new Random();

    // Dado normal (del 1 al 6)
    public static void DadoNormal() {
        resultado = rand.nextInt(6) + 1;
    }

    // Dado rápido (del 6 al 10)
    public static void DadoRapido() {
        resultado = rand.nextInt(5) + 6;
    }

    // Dado lento (del 1 al 3)
    public static void DadoLento() {
        resultado = rand.nextInt(3) + 1;
    }

    // Getters y setters
    public static int getResultado() {
        return resultado;
    }

    public static void setResultado(int resultado) {
        Dado.resultado = resultado;
    }
}
