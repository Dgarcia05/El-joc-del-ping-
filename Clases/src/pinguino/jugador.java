package pinguino;

public class jugador {

	private String nombre;
    private Inventario inventario;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.inventario = new Inventario();
    }

    public String getNombre() {
        return nombre;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void mostrarInventario() {
        System.out.println("Inventario de " + nombre + ": " + inventario);
    }
}
