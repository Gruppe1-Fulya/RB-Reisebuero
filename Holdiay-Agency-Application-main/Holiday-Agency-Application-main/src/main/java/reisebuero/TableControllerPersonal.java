package reisebuero;

import Classes.Person;
import Classes.Personal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class TableControllerPersonal implements Initializable {
    @FXML
    private TableView<Personal> table;
    @FXML
    private TableColumn<Person,Integer> col_id;
    @FXML
    private TableColumn<Person,String> col_name;
    @FXML
    private TableColumn<Person,String> col_nachname;
    @FXML
    private TableColumn<Person,Integer> col_telnum;
    @FXML
    private TableColumn<Personal,Double> col_gehalt;
    @FXML
    private TableColumn<Personal,Integer> col_personalnummer;

    Personal personal = null;
    ObservableList<Personal> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addPersonal.fxml")));
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
        oblist = FullDB.getPersonalDB();
        table.setItems(oblist);
    }

    @FXML
    private void deletePersonal() {
        personal = table.getSelectionModel().getSelectedItem();
        FullDB.deletePersonal(personal);
    }

    @FXML
    private void loadDate() {
        refreshTable();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_telnum.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
        col_personalnummer.setCellValueFactory(new PropertyValueFactory<>("personalnummer"));
        col_gehalt.setCellValueFactory(new PropertyValueFactory<>("gehalt"));

        table.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
