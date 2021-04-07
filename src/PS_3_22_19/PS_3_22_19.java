package PS_3_22_19;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PS_3_22_19 extends Application {
    private List<String> alarms = new ArrayList<>();
    int sec = 0;
    @Override public void start(Stage stage) {
        stage.setTitle("Digital Alarm Clock");
        VBox clockBox = new VBox();
        clockBox.setStyle(
            "-fx-background-color: white;" +
            "-fx-spacing: 25; " +
            "-fx-padding: 70,70,70,70; " +
            "-fx-alignment: center"
        );
        Label label = new Label("00:00:00");
        label.setFont(new Font("Calibre", 50));
        label.setStyle("-fx-text-fill: black");
        Button setAlarm = new Button("Set AlarmClock");
        setAlarm.setFont(new Font("Calibre", 20));
        setAlarm.setStyle(
            "-fx-pref-height: 20;" +
            "-fx-pref-width: 150;" +
            "-fx-alignment: center"
        );
        clockBox.getChildren().addAll(label, setAlarm);
        Timeline cycle = new Timeline(
            new KeyFrame(Duration.seconds(0),
                actionEvent -> {
                    Calendar time = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    label.setText(simpleDateFormat.format(time.getTime()));
                    String tempTime = String.format("%s%s", time.getTime().toString().substring(11, 13) , time.getTime().toString().substring(14,16));
                    sec += 1;
                    if (sec % 60 == 0) {
                        for (int i = 0; i< alarms.size(); i++){
                            if (alarms.get(i).equals(tempTime)) {
                                System.out.println("WAKE UPP!!!!!!!");
                                break;
                            }
                        }
                    }

                }),
            new KeyFrame(Duration.seconds(1))
        );
        cycle.setCycleCount(Animation.INDEFINITE);
        cycle.play();
        VBox alarmBox = new VBox();
        alarmBox.setStyle(
            "-fx-background-color: white;" +
            "-fx-spacing: 25; " +
            "-fx-padding: 70,70,70,70; " +
            "-fx-alignment: center");

        HBox valueContainer = new HBox();
        valueContainer.setStyle(
            "-fx-spacing: 10;" +
            "-fx-alignment: center;" +
            "-fx-pref-height: 50"
        );


        ComboBox hours = new ComboBox();
        hours.setMaxHeight(30);
        hours.getItems().addAll
            ("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        hours.setPromptText("Hour");

        Label colon = new Label(":");
        colon.setFont(new Font("Calibre", 50));
        colon.setStyle("-fx-text-fill: white");

        ComboBox minutes = new ComboBox();
        minutes.setStyle("-fx-max-height: 30");
        minutes.getItems().addAll
            (
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
                43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59
            );
        minutes.setPromptText("Mins");
        valueContainer.getChildren().addAll(hours, colon, minutes);
        Button done = new Button("Submit");
        done.setFont(new Font("Calibre", 25));
        done.setStyle("-fx-pref-height: 20;" +
            "-fx-pref-width: 156;" +
            "-fx-alignment: center");
        alarmBox.getChildren().addAll(valueContainer, done);
        Scene clock = new Scene(clockBox, 380, 300);
        Scene alarm = new Scene(alarmBox, 380, 300);
        setAlarm.setOnAction(event -> {
            stage.setScene(alarm);
        });
        done.setOnAction(event -> {
            if (hours.getValue() != null || hours.getValue() != null) {
                alarms.add(String.format("%s%s", hours.getValue(), minutes.getValue()));
                System.out.printf("Alarm is set for %s:%s%n", hours.getValue(),minutes.getValue());
                hours.setValue(null);
                minutes.setValue(null);
                stage.setScene(clock);
            };
        });
        stage.setScene(clock);
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}