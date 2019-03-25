/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JFileChooser;

/**
 *
 * @author Nicholas Rhoades
 */
public class GuiController implements Initializable {
   
     FileChooser fileChooser = new FileChooser();
      onStart Start = new onStart();
      String[] nEvent = new String[50];
        String space = " ";
     
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
    
    
    
    
   
    
  
    
    @FXML
    private void addEvent(Event e){
        int i = 0;
    list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
    eventList.setItems(list);
    String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String text = descriptionTextField.getText();
   
    String together = date + ":" + space + text;
    if (nEvent[i] == null)
    {
    nEvent[i] = together;
    }
    else if (nEvent[i] != null)
    {
    while (nEvent[i] != null)
    {
    i++;
    }
    nEvent[i] = together;
    }
    
    Refresh();
    }
    
    @FXML 
    private void saveList(Event e) throws IOException{
    Window stage = eventList.getScene().getWindow();
    fileChooser.setTitle("Save Dialog");
    fileChooser.setInitialFileName("My Save");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
    int i =0;
    File recordsDir = new File(System.getProperty("user.home"), ".To-Do List/records");
if (! recordsDir.exists()) {
    recordsDir.mkdirs();
}
     
     fileChooser.setInitialDirectory(recordsDir);
     File file = fileChooser.showSaveDialog(stage);
     BufferedWriter out = null;
      
           
    try {
        FileWriter fstream = new FileWriter(file);
          out = new BufferedWriter(fstream);
         
           
           while (nEvent[i] != null)
           {
           out.write(nEvent[i]);
           out.write(space);
           i++;
           }
               out.close();
              
        }
    catch(FileNotFoundException ex)
            {
            ex.printStackTrace();
            }
        
        
   
 
    
      try {
    fileChooser.setInitialDirectory(file.getParentFile());
    }
    catch (Exception ex)
            {
            ex.printStackTrace();
            }
        
    }
    
    @FXML
    private void loadList(Event e){
    
    Window stage = eventList.getScene().getWindow();
    fileChooser.setTitle("Load Dialog");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
    
    
    File recordsDir = new File(System.getProperty("user.home"), ".To-Do List/records");
if (! recordsDir.exists()) {
    recordsDir.mkdirs();
}
     
    fileChooser.setInitialDirectory(recordsDir);
    
    
     try {
    File file = fileChooser.showOpenDialog(stage);
    Scanner fileInput=null;
    try {
           fileInput = new Scanner(file);
        }
    catch(FileNotFoundException e1)
            {
        
            }
    
    
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
