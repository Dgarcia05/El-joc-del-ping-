package pinguino;

public class tablero {

	public static void main(String[] args) {
		
	private List<Casilla> casillas;
	private int totalCasillas;

    public Tablero(int totalCasillas) {
        this.totalCasillas = Math.max(totalCasillas, 50);
        this.casillas = new ArrayList<>();
        generarTablero();
    }

    private void generarTablero() {
        Random rand = new Random();
        Set<Integer> usados = new HashSet<>();

        while (usados.size() < totalCasillas / 5) {
            usados.add(rand.nextInt(totalCasillas));
        }

        for (int i = 0; i < totalCasillas; i++) {
            TipoCasilla tipo = TipoCasilla.NORMAL;

            if (usados.contains(i)) {
                int tipoRand = rand.nextInt(4);
                switch (tipoRand) {
                    case 0: tipo = TipoCasilla.OSO; break;
                    case 1: tipo = TipoCasilla.AGUJERO; break;
                    case 2: tipo = TipoCasilla.TRINEO; break;
                    case 3: tipo = TipoCasilla.INTERROGANTE; break;
                }
            }

            casillas.add(new Casilla(i, tipo));
        }
	}

}
