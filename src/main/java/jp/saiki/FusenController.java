package jp.saiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by akio on 2014/09/07.
 */
public class FusenController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="titleLabel"
    private Label titleLabel; // Value injected by FXMLLoader

    @FXML // fx:id="closeButton"
    private Button closeButton; // Value injected by FXMLLoader

    @FXML // fx:id="text"
    private TextArea text; // Value injected by FXMLLoader

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'Fusen.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'Fusen.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'Fusen.fxml'.";
    }

    @FXML
    void handlerCloseButtonAction(ActionEvent e) {
        this.stage.close();
    }

}


