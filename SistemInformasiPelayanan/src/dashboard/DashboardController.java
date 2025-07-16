package dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class DashboardController {

    // Fungsi Logout
    public void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/logout.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Logout");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Logout failed.");
        }
    }

    // Fungsi Buka Data Pengguna
    public void openUserTable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/list_table.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
            stage.setScene(new Scene(root));
            stage.setTitle("Data pengguna");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal membuka data pengguna.");
        }
    }

    @FXML
    private void handleTambahMatkul(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/matakuliah/tambah_matakuliah.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
            stage.setScene(new Scene(root));
            stage.setTitle("Tambah Mata Kuliah");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLihatMatkul(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/matakuliah/daftar_matakuliah.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
            stage.setScene(new Scene(root));
            stage.setTitle("Daftar Mata Kuliah");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
