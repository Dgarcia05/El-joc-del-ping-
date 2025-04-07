package pinguino;

public class inventario {
private ArrayList<BolasDeNieve> bolasdenieve;
private ArrayList<Pescado> pescado;

	    public Inventario() {
	        this.equipamiento = new ArrayList<>();
	    }

	    public void agregarInventario(Inventario inventario) {
	        this.inventario.add(equipamiento);
	    }

	    public void mostrarInventario() {
	        System.out.println("Inventario:");
	        for (Inventario i : inventario) {
	            System.out.println(i.getNombre() + " - " + i.getTipo());
	        }
	}
}
