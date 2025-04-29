package pinguino;

public class inventario {
public Inventario() {
        this.dados = 0;
        this.peces = 0;
        this.bolasDeNieve = 0;
    }

    public void agregarDado() {
        if (dados < 3) dados++;
    }

    public void agregarPez() {
        if (peces < 2) peces++;
    }

    public void agregarBolas(int cantidad) {
        bolasDeNieve = Math.min(bolasDeNieve + cantidad, 6);
    }

    public int getDados() {
        return dados;
    }

    public int getPeces() {
        return peces;
    }

    public int getBolasDeNieve() {
        return bolasDeNieve;
    }

    @Override
    public String toString() {
        return 
		"Dados: " + dados + ", Peces: " + peces + ", Bolas de nieve: " + bolasDeNieve;
    }
}
