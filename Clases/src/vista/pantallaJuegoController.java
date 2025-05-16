package vista;

import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pinguino.Juego;
import pinguino.Jugador;
import pinguino.Dado;

public class pantallaJuegoController {

    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;

    private final int COLUMNS = 5;  
    private final int TOTAL_JUGADORES = 4;

    private Juego juego;
    private int turnoActual = 0;
    private void actualizarInventarioVisual(Jugador jugador) {
    	 peces_t.setText("Peces: " + jugador.getInventario().getPeces());
    	    nieve_t.setText("Bolas de nieve: " + jugador.getInventario().getBolasDeNieve());
    	    rapido_t.setText("Dado Rápido: " + jugador.getInventario().getDadosRapidos());
    	    lento_t.setText("Dado Lento: " + jugador.getInventario().getDadosLentos());
    }

    @FXML
    private void initialize() {
        List<Jugador> jugadores = List.of(
            new Jugador("Jugador 1"),
            new Jugador("Jugador 2"),
            new Jugador("Jugador 3"),
            new Jugador("Jugador 4")
        );
        inicializarJuego(jugadores);
        mostrarTablero();
        eventos.setText("¡El juego ha comenzado! Turno de " + jugadores.get(0).getNombre());
    }


    @FXML
    private void handleNewGame() {
        
        List<Jugador> jugadores = List.of(
            new Jugador("Jugador 1"),
            new Jugador("Jugador 2"),
            new Jugador("Jugador 3"),
            new Jugador("Jugador 4")
        );
        inicializarJuego(jugadores);
        mostrarTablero();
        eventos.setText("Nuevo juego iniciado. Turno de " + jugadores.get(0).getNombre());
    }

    @FXML
    private void handleDado(ActionEvent event) {
        if (juego == null || juego.getJugadores().isEmpty()) {
            eventos.setText("Primero inicia un juego.");
            return;
        }

        Jugador jugadorActual = juego.getJugadores().get(turnoActual);

        juego.turno(jugadorActual, "normal");

        int nuevaPos = jugadorActual.getPosicion();

        dadoResultText.setText("Ha salido: " + Dado.getResultado());

        if (nuevaPos >= 49) {
            jugadorActual.setPosicion(49);
            eventos.setText(jugadorActual.getNombre() + " ha ganado!");
        } else {
            eventos.setText(jugadorActual.getNombre() + " avanzó a la casilla " + nuevaPos);
        }

        actualizarPosicionVisual(turnoActual, nuevaPos);
        actualizarInventarioVisual(jugadorActual);

        turnoActual = (turnoActual + 1) % TOTAL_JUGADORES;
        eventos.setText(eventos.getText() + "\nTurno de " + juego.getJugadores().get(turnoActual).getNombre());
    }


    
    public void inicializarJuego(List<Jugador> jugadores) {
        this.juego = new Juego(jugadores);
        this.turnoActual = 0;

       
        for (int i = 0; i < jugadores.size(); i++) {
            actualizarPosicionVisual(i, jugadores.get(i).getPosicion());
        }

        mostrarTablero();
    }
    private void actualizarPosicionVisual(int jugadorIndex, int posicion) {
        int fila = posicion / COLUMNS;
        int columna = posicion % COLUMNS;

        switch (jugadorIndex) {
            case 0 -> {
                GridPane.setRowIndex(P1, fila);
                GridPane.setColumnIndex(P1, columna);
            }
            case 1 -> {
                GridPane.setRowIndex(P2, fila);
                GridPane.setColumnIndex(P2, columna);
            }
            case 2 -> {
                GridPane.setRowIndex(P3, fila);
                GridPane.setColumnIndex(P3, columna);
            }
            case 3 -> {
                GridPane.setRowIndex(P4, fila);
                GridPane.setColumnIndex(P4, columna);
            }
        }
    }

    @FXML
    private void mostrarTablero() {
        if (juego == null || juego.getTablero() == null) {
            
            eventos.setText("No hay juego o tablero para mostrar.");
            return;
        }

        List<pinguino.Casilla> casillas = juego.getTablero().getCasillas();

        tablero.getChildren().removeIf(node -> node instanceof Pane);

        int columnas = COLUMNS;
        int filas = casillas.size() / columnas;

        for (int i = 0; i < casillas.size(); i++) {
            pinguino.Casilla casilla = casillas.get(i);
            Pane celda = new Pane();
            celda.setPrefSize(60, 60);

            switch (casilla.getTipo()) {
                case NORMAL -> celda.setStyle("-fx-background-color: white; -fx-border-color: black;");
                case AGUJERO -> celda.setStyle("-fx-background-color: black; -fx-border-color: white;");
                case TRINEO -> celda.setStyle("-fx-background-color: blue; -fx-border-color: black;");
                case EVENTO -> celda.setStyle("-fx-background-color: orange; -fx-border-color: black;");
            }

            int row = i / columnas;
            int col = i % columnas;
            tablero.add(celda, col, row);
        }

       
        tablero.getChildren().removeAll(P1, P2, P3, P4);
        tablero.getChildren().addAll(P1, P2, P3, P4);
    }

    
}
