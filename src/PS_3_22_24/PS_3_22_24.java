package PS_3_22_24;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PS_3_22_24 extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("22.24 Shadows for a Rectangle in the Scene Graph\n");
        int[] values = {5,3,3,0,0,0};
        VBox box = new VBox();
        box.setStyle(
            "-fx-background-color: white;" +
            "-fx-spacing: 15; " +
            "-fx-padding: 25,25,25,25; " +
            "-fx-alignment: center"
        );
        Label heading = new Label("22.24 Shadows for a Rectangle in the Scene Graph\n");
        heading.setFont(new Font("Calibre", 20));

        HBox slider = new HBox();
        slider.setStyle(
            "-fx-spacing: 8;" +
            "-fx-alignment: center;" +
            "-fx-pref-height: 80");

        Label h2 = new Label("radius");
        h2.setFont(new Font("Calibre", 20));
        h2.setRotate(360);
        h2.setTranslateX(10);

        Slider radius = new Slider();
        radius.setOrientation(Orientation.VERTICAL);

        Label h3 = new Label("Off Set");
        h3.setFont(new Font("Calibre", 20));
        h3.setRotate(270);
        h3.setTranslateX(10);

        Slider offSetX = new Slider();
        offSetX.setOrientation(Orientation.VERTICAL);

        Slider offSetY = new Slider();
        offSetY.setOrientation(Orientation.VERTICAL);

        Label h4 = new Label("RGB");
        h4.setFont(new Font("Calibre", 20));
        h4.setRotate(360);
        h4.setTranslateX(10);

        Slider red = new Slider();
        red.setOrientation(Orientation.VERTICAL);

        Slider green = new Slider();
        green.setOrientation(Orientation.VERTICAL);

        Slider blue = new Slider();
        blue.setOrientation(Orientation.VERTICAL);

        slider.getChildren().addAll(h2, radius, h3, offSetX,offSetY, h4, red, green, blue);


        Rectangle r = new Rectangle(150,75);
        r.setFill(Color.rgb(25,78,200));
        r.setEffect(new DropShadow(5.0,3.0,3.0,Color.rgb(values[3],values[4],values[5])));

        box.getChildren().addAll(heading, r, slider);

        radius.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                values[0] = newValue.intValue();
                r.setEffect(new DropShadow(values[0],values[1],values[2], Color.rgb(values[3],values[4],values[5])));
            }
        );
        offSetX.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                values[1] = newValue.intValue();
                r.setEffect(new DropShadow(values[0],values[1],values[2], Color.rgb(values[3],values[4],values[5])));
            }
        );

        offSetY.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                values[2] = newValue.intValue();
                r.setEffect(new DropShadow(values[0],values[1],values[2], Color.rgb(values[3],values[4],values[5])));
            }
        );

        red.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                values[3] = newValue.intValue();
                r.setEffect(new DropShadow(values[0],values[1],values[2], Color.rgb(values[3],values[4],values[5])));
            }
        );

        green.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                values[5] = newValue.intValue();
                r.setEffect(new DropShadow(values[0],values[1],values[2], Color.rgb(values[3],values[4],values[5])));
            }
        );

        blue.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                values[4] = newValue.intValue();
                r.setEffect(new DropShadow(values[0],values[1],values[2], Color.rgb(values[3],values[4],values[5])));
            }
        );


        Scene scene = new Scene(box, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}