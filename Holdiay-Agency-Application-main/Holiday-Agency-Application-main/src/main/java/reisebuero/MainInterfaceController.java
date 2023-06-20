package reisebuero;

import Classes.Person;
import Classes.Personal;
import Classes.Buchung;
import Classes.Kunde;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainInterfaceController implements Initializable {
    @FXML
    private TableView<Buchung> buchungTable;
    @FXML
    private TableColumn<Person,String> col_kundename;
    @FXML
    private TableColumn<Person,String> col_kundenachname;
    @FXML
    private TableColumn<Buchung,String> col_startzeit;
    ObservableList<Buchung> oblist = FXCollections.observableArrayList();

    @FXML
    private void getPersonalListe() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Personal.fxml")));
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
    private void getKundeListe() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Kunde.fxml")));
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
    private void getBuchungView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Buchung.fxml")));
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
    private void getZahlungView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Kontenstelle.fxml")));
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
    private void getReisepaketeView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Reisepakete.fxml")));
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
    private void refreshBuchung() {
        oblist = FullDB.getBuchungDB();
        buchungTable.setItems(oblist);
    }
    @FXML
    private void loadDate() {
        refreshBuchung();

        col_kundename.setCellValueFactory(new PropertyValueFactory<>("kundename"));
        col_kundenachname.setCellValueFactory(new PropertyValueFactory<>("kundenachname"));
        col_startzeit.setCellValueFactory(new PropertyValueFactory<>("startzeit"));


        LocalDate today = LocalDate.now();
        Date date = java.sql.Date.valueOf(today);
        FilteredList<Buchung> filteredData = new FilteredList<>(oblist, p -> p.getDate().equals(date));
        buchungTable.setItems(filteredData.sorted());

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FullDB.setConnection();
        loadDate();
    }
}
