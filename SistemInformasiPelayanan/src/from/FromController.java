package from;

import database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.sql.*;

public class FromController {
    @FXML private TextField nimField, kelasField, ipkField, semesterField, sksField;
    @FXML private Label messageLabel;

    @FXML
    void handleSimpan() {
        String nim = nimField.getText().trim();
        String kelas = kelasField.getText().trim();
        String ipkText = ipkField.getText().trim();
        String semesterText = semesterField.getText().trim();
        String sksText = sksField.getText().trim();

        if (nim.isEmpty() || kelas.isEmpty() || ipkText.isEmpty() || semesterText.isEmpty() || sksText.isEmpty()) {
            messageLabel.setText("Semua kolom harus diisi.");
            return;
        }

        try (Connection conn = Database.connect()) {
            PreparedStatement check = conn.prepareStatement("SELECT * FROM mahasiswa WHERE nim = ?");
            check.setString(1, nim);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                messageLabel.setText("Data NIM sudah ada!");
                return;
            }

            PreparedStatement insert = conn.prepareStatement("INSERT INTO mahasiswa(nim, kelas, ipk, semester, total_sks) VALUES (?, ?, ?, ?, ?)");
            insert.setString(1, nim);
            insert.setString(2, kelas);
            insert.setDouble(3, Double.parseDouble(ipkText));
            insert.setInt(4, Integer.parseInt(semesterText));
            insert.setInt(5, Integer.parseInt(sksText));
            insert.executeUpdate();

            // Tutup form, kembali ke dashboard
            ((Stage) nimField.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard/dashboard.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dashboard Mahasiswa");
            stage.show();

        } catch (SQLException | NumberFormatException e) {
            messageLabel.setText("Input tidak valid.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
