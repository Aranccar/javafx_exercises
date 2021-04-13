
package PS_3_22_22;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class PS_3_22_22 extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("ShadowText");
        VBox box = new VBox();
        Text text = new Text("JavaFX");
        text.setFont(Font.font("serif", FontWeight.BOLD, 60));
        text.setStyle("-fx-effect: dropshadow(two-pass-box,dodgerblue,15.0,0.6,3.0,3.0);" + "-fx-fill: white");
        box.getChildren().add(text);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 250, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}