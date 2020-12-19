package MiniBrowser;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MiniBrowserController implements Initializable {

    String https = "https://";
    String addressLink;
    WebEngine engine;
    ArrayList<String> history = new ArrayList<String>();
    int currPage = 0;

    @FXML TextField addressBar;
    @FXML WebView web;
    @FXML Button prevButton;
    @FXML Button nextButton;
    public void browse(){
        addressLink = addressBar.getText();
        engine.load(https+addressLink);
        history.add(https + addressLink);
        currPage = history.size() - 1;
        prevButton.setDisable(false);
        nextButton.setDisable(true);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = web.getEngine();
        engine.load(https+"www.google.com");
        history.add(https+"www.google.com");
    }
    public void prevButtonClicked() {
        engine.load(history.get(--currPage));
        nextButton.setDisable(false);
        if(currPage == 0) prevButton.setDisable(true);
        addressBar.setText(history.get(currPage));
    }
    public void nextButtonClicked() {
        engine.load(history.get(++currPage));
        prevButton.setDisable(false);
        if(currPage == history.size() - 1) nextButton.setDisable(true);
        addressBar.setText(history.get(currPage));
    }
}