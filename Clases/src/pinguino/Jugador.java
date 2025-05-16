package pinguino;

public class Jugador {
    private String nombre;
    private Inventario inventario;
    private int posicion;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.inventario = new Inventario();
        this.posicion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void mostrarInventario() {
        System.out.println("Inventario de " + nombre + ": " + inventario);
    }

	public static Jugador get(int turnoActual) {
		// TODO Auto-generated method stub
		return null;
	}
}
