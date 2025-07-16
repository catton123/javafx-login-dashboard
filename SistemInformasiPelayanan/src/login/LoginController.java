package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField securityCode;

    @FXML
    private Label errorMessage;

    private final String expectedSecurityCode = "2345";

    @FXML
    void btnLogin(ActionEvent event) {
        errorMessage.setText(""); // Reset pesan sebelumnya

        if (username.getText().isEmpty() || password.getText().isEmpty() || securityCode.getText().isEmpty()) {
            errorMessage.setText("Silakan isi semua field.");
            return;
        }

        if (!securityCode.getText().equals(expectedSecurityCode)) {
            errorMessage.setText("Security code salah.");
            return;
        }

        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement login = connection.prepareStatement(query);
            login.setString(1, username.getText());
            login.setString(2, password.getText());
            ResultSet resultSet = login.executeQuery();

            if (resultSet.next()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard/dashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    errorMessage.setText("Gagal memuat halaman dashboard.");
                }
            } else {
                errorMessage.setText("Username atau password salah.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage.setText("Kesalahan koneksi database.");
        }
    }

    @FXML
    private void goToRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



