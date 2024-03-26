package application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainController {

    @FXML
    private HBox mainBox;
    
    @FXML
    private TextField categoryField;

    @FXML
    private Button submitBtn;
    
    @FXML
    private Label errorLabel;
    
	@FXML
	public void showHomePage() {

		URL url = getClass().getClassLoader().getResource("view/Home.fxml");
		try {
			AnchorPane panel = (AnchorPane) FXMLLoader.load(url);

			if (mainBox.getChildren().size() > 1)
				mainBox.getChildren().remove(2);

			mainBox.getChildren().add(panel); // Add categoryPane to mainBox
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @FXML
    public void showCategoryPage() {
        URL url = getClass().getClassLoader().getResource("view/Category.fxml");

        try {
            AnchorPane panel = (AnchorPane)FXMLLoader.load(url);
            
			if (mainBox.getChildren().size() > 1)
				mainBox.getChildren().remove(2);
            
            mainBox.getChildren().add(panel); // Add categoryPane to mainBox
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleSubmitBtn() {
        String category = categoryField.getText().trim();
        
        if(category.isEmpty()){
          errorLabel.setText("Category name cannot be empty.");
          return;
        }
        
        File file = new File("categories.csv");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equalsIgnoreCase(category)) {
                        errorLabel.setText("This category already exists.");
                        return;  // return without saving the duplicated category
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        
        try (FileWriter csvWriter = new FileWriter(file, true)) {
            csvWriter.append(category);
            csvWriter.append("\n");
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        categoryField.clear();
        errorLabel.setText("");  // Clear the error label if operation is successful
    }
}
