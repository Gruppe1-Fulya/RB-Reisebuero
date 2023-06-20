package reisebuero;

import Classes.Person;
import Classes.Buchung;
import Classes.Personal;
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

public class TableControllerAppointment implements Initializable {
    @FXML
    private TableView<Buchung> table;
    @FXML
    private TableColumn<Buchung,Integer> col_zustand;
    @FXML
    private TableColumn<Person,String> col_kundename;
    @FXML
    private TableColumn<Person,String> col_kundenachname;
    @FXML
    private TableColumn<Buchung,String> col_date;
    @FXML
    private TableColumn<Buchung,String> col_startzeit;
    @FXML
    private TableColumn<Buchung,String> col_endezeit;
    Buchung buchung = null;
    ObservableList<Buchung> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addBuchung.fxml")));
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
    private void makeDone() {
        termin = table.getSelectionModel().getSelectedItem();
        FullDB.makeDone();
        refreshTable();
    }


    @FXML
    protected void refreshTable() {
        oblist = FullDB.getBuchungDB();
        table.setItems(oblist);
    }

    @FXML
    private void removeBuchung() {
        buchung = table.getSelectionModel().getSelectedItem();
        FullDB.removeBuchung(buchung);
    }

    @FXML
    private void loadDate() {
        refreshTable();

        col_zustand.setCellValueFactory(new PropertyValueFactory<>("zustand"));
        col_kundename.setCellValueFactory(new PropertyValueFactory<>("kundename"));
        col_kundenachname.setCellValueFactory(new PropertyValueFactory<>("kundenachname"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_startzeit.setCellValueFactory(new PropertyValueFactory<>("startzeit"));
        col_endezeit.setCellValueFactory(new PropertyValueFactory<>("endezeit"));

        table.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
