package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

public class MainController {

    @FXML
    private HBox mainBox;
    
    @FXML
    
    public void showCategoryPage() {
        URL url = getClass().getClassLoader().getResource("view/Category.fxml");

        try {
            AnchorPane panel = (AnchorPane)FXMLLoader.load(url);
            mainBox.getChildren().add(panel); // Add categoryPane to mainBox
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
