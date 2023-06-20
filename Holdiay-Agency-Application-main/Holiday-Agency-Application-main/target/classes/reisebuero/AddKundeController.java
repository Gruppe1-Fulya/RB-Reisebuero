package reisebuero;

import Classes.Kunde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddKundeController implements Initializable {

    @FXML
    private TextField addKundeId;
    @FXML
    private TextField addKundeName;
    @FXML
    private TextField addNachname;
    @FXML
    private TextField addTel;
    @FXML
    private TextField addKontostand;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save() {
        if(addKundeName.getText().isEmpty() || addNachname.getText().isEmpty() || addTel.getText().isEmpty() ||
                 addHBId.getText().isEmpty()|| addKontostand.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            Kunde kunde = new Kunde(Integer.parseInt(addKundeId.getText()),addKundeName.getText(), addNachname.getText());
            tier.setTelefonnummer(Integer.parseInt(addTel.getText()));
            tier.setKontostand(Double.parseDouble(addKontostand.getText()));

            FullDB.getKundeQuery(tier);
            FullDB.insertKunde(kunde);
            clean();
        }
    }

    @FXML
    private void clean() {
        addKundeId.setText(null);
        addKundeName.setText(null);
        addNachname.setText(null);
        addTel.setText(null);
        addKontostand.setText(null);
    }

}
