package reisebuero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/MainInterface.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Image icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/png/pet.png")));
            stage.getIcons().add(icon);
            stage.setTitle("Reisebüro System");
            stage.setWidth(1366);
            stage.setHeight(768);

            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}