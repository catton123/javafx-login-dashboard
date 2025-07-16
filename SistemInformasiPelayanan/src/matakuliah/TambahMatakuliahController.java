package matakuliah;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TambahMatakuliahController {

    @FXML private TextField fieldKode;
    @FXML private TextField fieldNama;
    @FXML private TextField fieldSks;
    @FXML private Label labelStatus;

    // Simpan ke database
    @FXML
    private void handleSimpan() {
        try {
            Connection conn = DatabaseConnection.connect();
            String query = "INSERT INTO matakuliah (kode_matakuliah, nama_matakuliah, sks) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, fieldKode.getText());
            ps.setString(2, fieldNama.getText());
            ps.setString(3, fieldSks.getText());
            ps.executeUpdate();

            labelStatus.setText("✅ Mata kuliah berhasil disimpan!");
            labelStatus.setStyle("-fx-text-fill: green;");

            // Kosongkan input field
            fieldKode.clear();
            fieldNama.clear();
            fieldSks.clear();

        } catch (Exception e) {
            e.printStackTrace();
            labelStatus.setText("❌ Gagal menyimpan: " + e.getMessage());
            labelStatus.setStyle("-fx-text-fill: red;");
        }
    }

    // Kembali ke dashboard
    @FXML
    private void handleKembali(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard/dashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
