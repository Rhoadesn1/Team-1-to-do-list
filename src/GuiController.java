/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalEvent> eventList; 
    
    ObservableList<LocalEvent> list = FXCollections.observableArrayList();
    
    @FXML
    private void addEvent(Event e){
    list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
    eventList.setItems(list);
    Refresh();
    }
    
    private void Refresh(){
    datePicker.setValue(LocalDate.now());
    descriptionTextField.setText(null);
    }
    
}
