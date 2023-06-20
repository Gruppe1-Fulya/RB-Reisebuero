package reisebuero;

import Classes.Buchung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AddBuchungController implements Initializable {

    @FXML
    private ChoiceBox<String> addKunde;
    @FXML
    private TextField addDate;
    @FXML
    private TextField addStartzeit;
    @FXML
    private TextField addEndezeit;
    int buchungid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buchungid = FullDB.getBuchungid();
            getKundeinfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getKundeinfo() throws SQLException {
        ResultSet resultSet = FullDB.getKundeinfo();
        while (resultSet.next()) {
            addKunde.getItems().add(resultSet.getString("name") + " | " + resultSet.getString("nachname"));
        }
    }

    @FXML
    private void save() throws SQLException, ParseException {
        if(addDate.getText().isEmpty() || addStartzeit.getText().isEmpty()|| addEndezeit.getText().isEmpty() || addKunde.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            String[] kundeinfo = addKunde.getValue().split("\\|");
            Buchung buchung = new Buchung(kundeinfo[1],kundeinfo[2]);
            buchung.setDate(addDate.getText());
            buchung.setStartzeit(addStartzeit.getText());
            buchung.setEndezeit(addEndezeit.getText());

            FullDB.getBuchungQuery(buchung);
            FullDB.insertBuchung(buchung);
            clean();
        }
    }

    @FXML
    private void clean() {
        addDate.setText(null);
        addStartzeit.setText(null);
        addEndezeit.setText(null);
        addKunde.setValue(null);
    }

}
