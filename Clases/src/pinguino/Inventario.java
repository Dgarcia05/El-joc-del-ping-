package pinguino;

public class Inventario {
    private int dadosRapidos;
    private int dadosLentos;
    private int peces;
    private int bolasDeNieve;

    public Inventario() {
        this.dadosRapidos = 0;
        this.dadosLentos = 0;
        this.peces = 0;
        this.bolasDeNieve = 0;
    }

    public void agregarDadoRapido() {
        if (dadosRapidos < 3) dadosRapidos++;
    }

    public void agregarDadoLento() {
        if (dadosLentos < 3) dadosLentos++;
    }

    public void agregarPez() {
        if (peces < 2) peces++;
    }

    public void agregarBolas(int cantidad) {
        bolasDeNieve = Math.max(0, Math.min(bolasDeNieve + cantidad, 6));
    }

    public int getDadosRapidos() {
        return dadosRapidos;
    }

    public int getDadosLentos() {
        return dadosLentos;
    }

    public int getPeces() {
        return peces;
    }

    public int getBolasDeNieve() {
        return bolasDeNieve;
    }

    public void setDadosRapidos(int cantidad) {
        this.dadosRapidos = Math.max(0, Math.min(cantidad, 3));
    }
    
    public void setDadosLentos(int cantidad) {
        this.dadosLentos = Math.max(0, Math.min(cantidad, 3));
    }

   
    public boolean usarDadoRapido() {
        if (dadosRapidos > 0) {
            dadosRapidos--;
            return true;
        }
        return false;
    }

    public boolean usarDadoLento() {
        if (dadosLentos > 0) {
            dadosLentos--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Dados rápidos: " + dadosRapidos + ", Dados lentos: " + dadosLentos +
               ", Peces: " + peces + ", Bolas de nieve: " + bolasDeNieve;
    }
}
