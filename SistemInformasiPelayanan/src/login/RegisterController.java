package login;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DatabaseConnection;

public class RegisterController {

    @FXML private TextField namaField;
    @FXML private TextField nimField;
    @FXML private TextField nohpField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private RadioButton lakiRadio;
    @FXML private RadioButton perempuanRadio;
    @FXML private Label messageLabel;

    private ToggleGroup genderGroup;

    @FXML
    public void initialize() {
        genderGroup = new ToggleGroup();
        lakiRadio.setToggleGroup(genderGroup);
        perempuanRadio.setToggleGroup(genderGroup);
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        String nama = namaField.getText();
        String nim = nimField.getText();
        String nohp = nohpField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String jenisKelamin = lakiRadio.isSelected() ? "L" : perempuanRadio.isSelected() ? "P" : "";

        if (nama.isEmpty() || nim.isEmpty() || nohp.isEmpty() || email.isEmpty() ||
            username.isEmpty() || password.isEmpty() || jenisKelamin.isEmpty()) {
            messageLabel.setText("Semua field harus diisi!");
            return;
        }

        try {
            Connection conn = DatabaseConnection.connect();

            // Cek apakah username sudah ada
            String checkQuery = "SELECT * FROM user WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                messageLabel.setText("Username sudah terdaftar.");
                return;
            }

            // Simpan akun baru
            String query = "INSERT INTO user (username, password, nim, `nama Lengkap`, `nomor hp`, email, `jenis kelamin`) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, nim);
            stmt.setString(4, nama);
            stmt.setString(5, nohp);
            stmt.setString(6, email);
            stmt.setString(7, jenisKelamin);
            stmt.executeUpdate();

            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Registrasi berhasil! Silakan login.");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Registrasi gagal.");
        }
    }

    @FXML
    public void goToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}