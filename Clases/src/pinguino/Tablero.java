package pinguino;

import java.util.*;

public class Tablero {
    private int totalCasillas;
    private List<Casilla> casillas;
    private List<Integer> agujeros; 

    public Tablero(int totalCasillas) {
        this.totalCasillas = Math.max(totalCasillas, 50);
        this.casillas = new ArrayList<>();
        this.agujeros = new ArrayList<>();
        generarTablero();
    }

    private void generarTablero() {
        Random rand = new Random();
        Set<Integer> usados = new HashSet<>();

        while (usados.size() < totalCasillas / 5) {
            usados.add(rand.nextInt(totalCasillas));
        }

        for (int i = 0; i < totalCasillas; i++) {
            Casilla.Tipo tipo = Casilla.Tipo.NORMAL;

            if (usados.contains(i)) {
                int tipoRand = rand.nextInt(4);
                switch (tipoRand) {
                    case 0 -> {
                        tipo = Casilla.Tipo.AGUJERO;
                        agujeros.add(i);  
                    }
                    case 1 -> tipo = Casilla.Tipo.TRINEO;
                    case 2 -> tipo = Casilla.Tipo.EVENTO;
                    case 3 -> tipo = Casilla.Tipo.NORMAL;
                }
            }

            casillas.add(new Casilla(i, tipo));
        }
    }

    public Casilla getCasilla(int index) {
        return casillas.get(index);
    }

    
    public int obtenerAgujeroAnterior(int posicionActual) {
        int anterior = -1;
        for (int pos : agujeros) {
            if (pos < posicionActual) anterior = pos;
            else break;
        }
        return anterior;
    }
}
