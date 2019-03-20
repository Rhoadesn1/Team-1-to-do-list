/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JFileChooser;

/**
 *
 * @author Nicholas Rhoades
 */
public class GuiController implements Initializable {
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       datePicker.setValue(LocalDate.now());
       
       
    }    
    @FXML
    Button addButton;
    @FXML
    Button saveButton;
    @FXML
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalEvent> eventList; 
    
    ObservableList<LocalEvent> list = FXCollections.observableArrayList();
    FileChooser fileChooser = new FileChooser();
    
  
    
    @FXML
    private void addEvent(Event e){
    list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
    eventList.setItems(list);
    Refresh();
    }
    
    @FXML 
    private void saveList(Event e){
    Window stage = eventList.getScene().getWindow();
    fileChooser.setTitle("Save Dialog");
    fileChooser.setInitialFileName("My Save");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
    
    try {
    File file = fileChooser.showSaveDialog(stage);
    
    fileChooser.setInitialDirectory(file.getParentFile());
    }
    catch (Exception ex)
            {
            
            }
        
    }
    
    @FXML
    private void loadList(Event e){
    
    Window stage = eventList.getScene().getWindow();
    fileChooser.setTitle("Load Dialog");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
    
     try {
    File file = fileChooser.showOpenDialog(stage);
    
    fileChooser.setInitialDirectory(file.getParentFile());
    }
    catch (Exception ex)
            {
            
            }
        
    }
    
    private void Refresh(){
    datePicker.setValue(LocalDate.now());
    descriptionTextField.setText(null);
    }
    
    
    
}
