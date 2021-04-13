package PS_3_22_17;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PS_3_22_17_Controller {
    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LARGE(6);
        private final int radius;
        PenSize(int radius) {this.radius = radius;}
        public int getRadius() {return radius;}
    }
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Rectangle colorRectangle;
    @FXML private Pane drawingAreaPane;
    @FXML private ToggleGroup sizeToggleGroup;
    @FXML private RadioButton smallRadioButton;
    @FXML private RadioButton mediumRadioButton;
    @FXML private RadioButton largeRadioButton;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;
    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor = Color.BLACK;
    public void initialize() {
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);
        redSlider.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                red = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                brushColor = Color.rgb(red, green, blue);
            }
        );
        greenSlider.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                green = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                brushColor = Color.rgb(red, green, blue);
            }
        );
        blueSlider.valueProperty().addListener(
            (ov, oldValue, newValue) -> {
                blue = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                brushColor = Color.rgb(red, green, blue);
            }
        );
    }
    @FXML
    private void drawingAreaMouseDragged(MouseEvent e) {
        double ox = drawingAreaPane.getWidth() / 2;
        double oy = drawingAreaPane.getHeight() / 2;
        int p = 16;
        for (int i = 0; i < p; i++) {
            double theta = Math.toRadians(i * 360 / p);
            double x = Math.cos(theta) * (e.getX() - ox) - Math.sin(theta) * (e.getY() - oy) + ox;
            double y = Math.sin(theta) * (e.getX() - ox) + Math.cos(theta) * (e.getY() - oy) + oy;
            Circle newCircle = new Circle(x, y, radius.getRadius(), brushColor);
            drawingAreaPane.getChildren().add(newCircle);
        }

    }
    @FXML
    private void sizeRadioButtonSelected(ActionEvent e) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }
    @FXML
    private void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if (count > 0) { drawingAreaPane.getChildren().remove(count - 1); }
    }
    @FXML
    private void clearButtonPressed(ActionEvent event) { drawingAreaPane.getChildren().clear(); }
}