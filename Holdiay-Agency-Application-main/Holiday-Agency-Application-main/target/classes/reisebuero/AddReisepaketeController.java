package reisebuero;

import Classes.Reisepakete;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddReisepaketeController implements Initializable {

    @FXML
    private ChoiceBox<String> addKunde;
    @FXML
    //private TextField addDate;
    int buchungid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            kundeid = FullDB.getReisepaketeId();
            getKundeinfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getKundeinfo() throws SQLException {
        ResultSet resultSet = FullDB.getKundeinfo();
        while (resultSet.next()) {
            addKunde.getItems().add(resultSet.getString("kundeid") + "| " + resultSet.getString("name") + " | " + resultSet.getString("nachname"));
        }
    }
    @FXML
    private void save() throws SQLException {
        if(addKunde.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            String[] kundeinfo = addKunde.getValue().split("\\|");
            Reisepakete reisepakete = new Reisepakete(Integer.parseInt(kundeinfo[0]));
            reisepakete.setNachaname(kundeinfo[2]);
            reisepakete.setKundename(kundeinfo[1]);

            FullDB.getreisepaketeQuery(rezept);
            FullDB.insertRezepte(rezept);
            clean();
        }
    }

    @FXML
    private void clean() {
        //addDate.setText(null);
        addMedizin.setText(null);
        addTier.setValue(null);
    }

}
