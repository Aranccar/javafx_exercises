package MiniBrowser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiniBrowser extends Application {

    public static void main(String[] args) { launch(args); }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MiniBrowser.fxml"));
        stage.setTitle("Mini Browser");
        stage.setScene(new Scene(root));

        stage.show();

    }
}
// --module-path /home/uca/Downloads/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules=javafx.controls,javafx.fxml,javafx.web