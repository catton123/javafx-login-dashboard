package user;

import user.User;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> colNama;
    @FXML
    private TableColumn<User, String> colNim;
    @FXML
    private TableColumn<User, String> colNoHp;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colJenisKelamin;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colPassword;

    ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadUserData();
    }

    private void loadUserData() {
        try {
            Connection conn = DatabaseConnection.connect();
            String query = "SELECT * FROM user";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userList.add(new User(
                        rs.getString("nama lengkap"),
                        rs.getString("nim"),
                        rs.getString("nomor hp"),
                        rs.getString("email"),
                        rs.getString("jenis kelamin"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }

            colNama.setCellValueFactory(new PropertyValueFactory<>("namaLengkap"));
            colNim.setCellValueFactory(new PropertyValueFactory<>("nim"));
            colNoHp.setCellValueFactory(new PropertyValueFactory<>("noHp"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colJenisKelamin.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

            userTable.setItems(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBack(ActionEvent event) {
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
