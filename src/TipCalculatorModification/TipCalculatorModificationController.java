package TipCalculatorModification;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class TipCalculatorModificationController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private BigDecimal amount;
    private BigDecimal tipPercentage= new BigDecimal(0.15);
    @FXML
    private TextField amountTextField;
    @FXML
    private Slider tipPercentageSlider;
    @FXML
    private Text tipTextField;
    @FXML
    private Text totalTextField;
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        amountTextField.textProperty().addListener((arg0, oldValue, newValue) -> {
            try {amount = new BigDecimal(newValue.trim());}
            catch (NumberFormatException e) {amount = null;}
            calculate();
        });
        tipPercentageSlider.valueProperty().addListener(
                (observableValue, number, t1) -> {
                    tipPercentage = BigDecimal.valueOf(t1.intValue() * .01);
                    calculate();
                }
        );
    }
    private void calculate() {
        if (amount != null && amount.floatValue() >= 0) {
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } else {
            tipTextField.setText(currency.format(0));
            totalTextField.setText(currency.format(0));
        }
    }
}
