package vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class pantallaPrincipalController {

    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private TextField userField;
    @FXML private PasswordField passField;

    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void initialize() {
        System.out.println("pantallaPrincipalController initialized");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Login pressed: " + username + " / " + password);

        if (!username.isEmpty() && !password.isEmpty()) {
            String jdbcUrl = "jdbc:oracle:thin:@//192.168.3.26:1521/XEPDB2";
            String dbUser = "DM2425_PIN_GRUP06";
            String dbPassword = "ACGKS06";

            try {
                Class.forName("oracle.jdbc.OracleDriver"); 

                Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                String sql = "SELECT * FROM USUARIO WHERE NOMBRE_USUARIO = ? AND PASS_USUARIO = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Login exitoso! Bienvenido " + username);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
                    Parent pantallaJuegoRoot = loader.load();

                    Scene pantallaJuegoScene = new Scene(pantallaJuegoRoot);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(pantallaJuegoScene);
                    stage.setTitle("Pantalla de Juego");
                } else {
                    System.out.println("ERROR: Usuario o contraseña incorrectos.");
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Por favor, introduce usuario y contraseña.");
        }
    }

    @FXML
    private void handleRegister() {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Register pressed: " + username + " / " + password);

        if (!username.isEmpty() && !password.isEmpty()) {
            String jdbcUrl = "jdbc:oracle:thin:@//192.168.3.26:1521/XEPDB2";
            String dbUser = "DM2425_PIN_GRUP06";
            String dbPassword = "ACGKS06";

            try {
                Class.forName("oracle.jdbc.OracleDriver");  

                Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

               
                String checkSql = "SELECT * FROM USUARIO WHERE NOMBRE_USUARIO = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, username);

                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    System.out.println("ERROR: El usuario ya existe. Elige otro nombre.");
                } else {
                    
                    String insertSql = "INSERT INTO USUARIO (NOMBRE_USUARIO, PASS_USUARIO) VALUES (?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, password);

                    int rowsInserted = insertStmt.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Usuario registrado correctamente: " + username);
                    } else {
                        System.out.println("ERROR: No se pudo registrar el usuario.");
                    }

                    insertStmt.close();
                }

                rs.close();
                checkStmt.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Por favor, introduce usuario y contraseña.");
        }
    }


    @FXML
    private void handleSaveGame() {
        System.out.println("Save Game clicked");
    }

    @FXML
    private void handleLoadGame() {
        System.out.println("Load Game clicked");
    }

    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game clicked");
        System.exit(0);
    }
}
