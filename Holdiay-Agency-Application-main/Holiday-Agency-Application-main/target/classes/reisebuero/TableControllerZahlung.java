package reisebuero;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class TableControllerZahlung {
    @FXML
    private TableView<Zahlung> table;
    @FXML
    private TableColumn<Zahlung,Integer> col_zustand;
    @FXML
    private TableColumn<Person, String> col_kundename;
    @FXML
    private TableColumn<Person, String> col_nachname;
    @FXML
    private TableColumn<Zahlung,String> col_zahlungsart;
    @FXML
    private TableColumn<Zahlung,Double> col_zahlungsbetrag;
    @FXML
    private ListView<Double> col_gesamtbetrag;
    Zahlung zahlung = null;
    int id = 0;
    ObservableList<Zahlung> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addZahlung.fxml")));
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
    protected void refreshTable() {
        oblist = FullDB.getZahlungDB();
        table.setItems(oblist);
    }

    @FXML
    private void removeZahlung() {
        zahlung = table.getSelectionModel().getSelectedItem();
        id = Zahlung.getZahlungid();
        FullDB.deleteZahlung(id);
        refreshTable();
    }

    @FXML
    private void loadDate(){
        refreshTable();

        col_zustand.setCellValueFactory(new PropertyValueFactory<>("zustand"));
        col_tiername.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_zahlungsart.setCellValueFactory(new PropertyValueFactory<>("zahlungsart"));
        col_zahlungsbetrag.setCellValueFactory(new PropertyValueFactory<>("zahlungsbetrag"));

        table.setItems(oblist);
    }

    @FXML
    private void getTotalAmount() {
        col_gesamtbetrag.setItems(FXCollections.observableArrayList(FullDB.getTotalAmount()));
    }

    @FXML
    private void makePaid() {
        zahlung = table.getSelectionModel().getSelectedItem();
        FullDB.makePaid(zahlung);
        refreshTable();
    }

    public void initialize() throws SQLException {
        loadDate();
        getTotalAmount();
    }
}
