package reisebuero;

import Classes.Zahlung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddZahlungController implements Initializable {

    @FXML
    private ChoiceBox<String> addZahlungsart;
    @FXML
    private TextField addZahlungsbetrag;
    @FXML
    private ChoiceBox<String> addKunde;

    private final String[] zahlungsarten = {"Bar", "Kreditkarte", "Anweisung"};
    int zahlungid;
    int kundeid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            zahlungid = FullDB.getZahlungid();
            getKundeinfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addZahlungsart.getItems().addAll(zahlungsarten);
    }

    @FXML
    public void getKundeinfo() throws SQLException {
        ResultSet resultSet = FullDB.getKundeinfo();
        while (resultSet.next()) {
            addKunde.getItems().add(resultSet.getInt("kundeid") + " | " + resultSet.getString("name") + " | " + resultSet.getString("nachname"));
            kundeid =  resultSet.getInt("kundeid");
        }
    }

    @FXML
    private void save() throws SQLException {

        if(addZahlungsbetrag.getText().isEmpty() || addZahlungsart.getValue().isEmpty() || addKunde.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            Zahlung zahlung;
            zahlung = new Zahlung(addZahlungsart.getValue(), Double.parseDouble(addZahlungsbetrag.getText()), kundeid);
            FullDB.getZahlungQuery(zahlung);
            FullDB.insertZahlung(zahlung);
            clean();
        }
    }

    double rabattsatz = 0.0;
            if (buchungsAnzahl > 5 && totalPreis > 1000) {
        rabattsatz = 0.1; // %10 indirim
    } else if (buchungsAnzahl > 3 && totalPreis > 500) {
        rabattsatz = 0.05; // %5 indirim
    }

    double rabattBetrag = totalPreis * rabattsatz;
    double zahlungBetrag = totalPreis - rabattBetrag;

    // Yeni Zahlung nesnesini oluştur
    zahlung = new Zahlung(addZahlungsart.getValue(),zahlungBetrag, kundeid);
            FullDB.getZahlungQuery(zahlung);
            FullDB.insertZahlung(zahlung);
    clean();
}
    }


@FXML
    private void clean() {
        addZahlungsart.setValue(null);
        addZahlungsbetrag.setText(null);
        addKunde.setValue(null);
    }
}
