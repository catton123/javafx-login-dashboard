package matakuliah;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.Node;

public class DaftarMatakuliahController {

    @FXML
    private TableView<Matakuliah> tabelMatkul;
    @FXML
    private TableColumn<Matakuliah, String> colId;
    @FXML
    private TableColumn<Matakuliah, String> colKode;
    @FXML
    private TableColumn<Matakuliah, String> colNama;
    @FXML
    private TableColumn<Matakuliah, String> colSks;

    ObservableList<Matakuliah> matkulList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadDataMatakuliah();
    }

    private void loadDataMatakuliah() {
        try {
            Connection conn = DatabaseConnection.connect();
            String query = "SELECT * FROM matakuliah";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("Mulai ambil data dari tabel matakuliah...");

            while (rs.next()) {
                String id = rs.getString("id");
                System.out.println("Data ditemukan: ID = " + id);
                matkulList.add(new Matakuliah(
                    id,
                    rs.getString("kode_matakuliah"),
                    rs.getString("nama_matakuliah"),
                    rs.getString("sks")
                ));
            }

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colKode.setCellValueFactory(new PropertyValueFactory<>("kode_matakuliah"));
            colNama.setCellValueFactory(new PropertyValueFactory<>("nama_matakuliah"));
            colSks.setCellValueFactory(new PropertyValueFactory<>("sks"));

            tabelMatkul.setItems(matkulList);

        } catch (Exception e) {
            System.out.println("Gagal ambil data: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
