package reisebuero;

import Classes.Reisepakete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableControllerReisepakete implements Initializable {
    @FXML
    private TableView<Reisepakete> table;
    @FXML
    private TableColumn<Reisepakete,Integer> col_id;
    @FXML
    private TableColumn<Reisepakete,String> col_name;
    @FXML
    private TableColumn<Reisepakete,String> col_nachname;
    @FXML
    private TableColumn<Reisepakete,Integer> col_medizin;


    @FXML
    private TextField setKundename;
    @FXML
    private TextField setNachname;
    ObservableList<Reisepakete> oblist = FXCollections.observableArrayList();
    Reisepakete reisepakete = null;

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addReisepakete.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void refreshTable() {
        oblist = FullDB.getReisepaketeDB();
        table.setItems(oblist);
    }

    @FXML
    private void loadDate() {
        refreshTable();

        col_id.setCellValueFactory(new PropertyValueFactory<>("kundeid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("kundename"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));

        table.setItems(oblist);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }

    @FXML
    private void printReisepakete() {
        rezept = table.getSelectionModel().getSelectedItem();
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/printedReisepakete.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        loadReisepakete(reisepakete);
    }

    @FXML
    private void loadReisepakete(Reisepakete reisepakete) {

        setKundename.setText(reisepakete.getKundename());
        setNachname.setText(rezept.getNachname());

    }
}
