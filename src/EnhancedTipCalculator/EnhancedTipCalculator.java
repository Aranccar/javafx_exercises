package EnhancedTipCalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnhancedTipCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EnhancedTipCalculator.fxml"));
        primaryStage.setTitle("Enhanced Tip Calculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


// File -> Project Structure -> Libraries
// Run -> Edit Configurations -> VM Options -> --module-path <libPath> --add-modules=javafx.controls,javafx.fxml