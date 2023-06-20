package reisebuero;

import Classes.Person;
import Classes.Kunde;
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

public class TableControllerTier implements Initializable {
    @FXML
    private TableView<Tier> table;
    @FXML
    private TableColumn<Tier,Integer> col_id;
    @FXML
    private TableColumn<Person,String> col_kundename;
    @FXML
    private TableColumn<Person,String> col_nachname;
    @FXML
    private TableColumn<Person,Integer> col_telnum;
    @FXML
    private TableColumn<Tier,Double> col_kontostand;
    Kunde kunde = null;
    ObservableList<Kunde> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addKunde.fxml")));
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
        oblist = FullDB.getKundeDB();
        table.setItems(oblist);
    }

    @FXML
    private void deleteKunde() {
        kunde = table.getSelectionModel().getSelectedItem();
        FullDB.deleteKunde(kunde);
    }

    @FXML
    private void loadDate() {
        refreshTable();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_tiername.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_telnum.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
        col_kontostand.setCellValueFactory(new PropertyValueFactory<>("kontostand"));
        col_rabatt.setCellValueFactory(new PropertyValueFactory<>("rabatt"));
        col_gekauftereisepakete.setCellValueFactory(new PropertyValueFactory<>("gekauftereisepakete"));

        table.setItems(oblist);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
