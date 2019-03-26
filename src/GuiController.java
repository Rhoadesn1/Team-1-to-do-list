/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.Locale;
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
  
     FileChooser fileChooser = new FileChooser(); // creates a new fileChooser
      onStart Start = new onStart(); // calls the OnStart class
      String[] nEvent = new String[50];  // creates a string array of 50 places
        String space = " "; // string to space the datepicker and description strings
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       datePicker.setValue(LocalDate.now()); // sets the datepicker to the current date
   
    }     // declaring fxml values for the buttons
    @FXML
    Button addButton;
    @FXML
    Button saveButton;
    @FXML
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalEvent> eventList; // delcaring an eventlist
    
    //creating a list of arrays to store the events
    ObservableList<LocalEvent> list = FXCollections.observableArrayList();
    
    
    
    
   
    
  
    
    @FXML
    private void addEvent(Event e) throws ParseException{ // method that adds an event when the user clicks add event
        int i;
        i = 0;
    list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));  // creating new event using current datepicker value and text in 
    // the description field
    eventList.setItems(list);
      
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");  //the input format for the date
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-mm-dd"); // the output format for the date
            
            String save = datePicker.getValue().toString(); // saving the value of the datepicker into a string
            Date date2 = inputFormat.parse(save);  // parsing the string to a new format.
            String date = outputFormat.format(date2); // saving the new format to the String date
          

    String text = descriptionTextField.getText();  // saving the description of the event to a string
    String together = date + space + text;  // concatenating strings 
    
    if (nEvent[i] == null)  // checking to see if the current value of the array is null
    {
    nEvent[i] = together;  // if so it adds the event string to the current index of the array
    }
    else if (nEvent[i] != null)  // if not null
    {
    while (nEvent[i] != null) // while its not null it will increase the index 
    {
    i++;
    }
    nEvent[i] = together; // when it finds an empty space it will add the event to there.
    }
    Refresh(); // refreshes description and datepicker fields
  
    }
    
    @FXML 
    private void saveList(Event e) throws IOException{ // method that stores the events into an array and then stores the contents of that array into a file
    Window stage = eventList.getScene().getWindow(); // creating a stage thru the eventList scene and window
    fileChooser.setTitle("Save Dialog"); // generic save dialog
    fileChooser.setInitialFileName("My Save"); // generic Initial file name dialog 
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt")); // allowing the user to store list as only a txt file
    int i =0; // declaring and setting the array index to 0
    File recordsDir = new File(System.getProperty("user.home"), ".To-Do List/records"); //setting a directory location
if (! recordsDir.exists()) { // if directory doesnt exist it will create it on the users comp
    recordsDir.mkdirs();
}
     
     fileChooser.setInitialDirectory(recordsDir); //sets the initial directory to the directory we created
     File file = fileChooser.showSaveDialog(stage); // opens the save window
     BufferedWriter out = null; // declaring bufferedwriter variable
      
           
    try {  // writing to a file
        FileWriter fstream = new FileWriter(file);
          out = new BufferedWriter(fstream);
         
           
           while (nEvent[i] != null) // while the index of the array is not null write the current index to the file
           {
           out.write(nEvent[i]);
           out.newLine();
           i++;
           }
               out.close();
              
        }
    catch(FileNotFoundException ex)
            {
            ex.printStackTrace();
            }
        
        
   
 
    
      try {
    fileChooser.setInitialDirectory(file.getParentFile());  // sets the initial directory to the directory last used
    }
    catch (Exception ex)
            {
            ex.printStackTrace();
            }
        
    }
    
    @FXML
    private void loadList(Event e) throws FileNotFoundException, ParseException{ // When the loadlist button is hit, this method is active. 
        //It allows the user to choose a list and then load it.
    
    Window stage = eventList.getScene().getWindow(); //calling the scene through an action event. i.e. the event list
    fileChooser.setTitle("Load Dialog"); // generic load dialog
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt")); // This allows the user to only load .txt files
    
    
    File recordsDir = new File(System.getProperty("user.home"), ".To-Do List/records");
if (! recordsDir.exists()) { // checking to see if this directory has been made, if not it will make it. 
    recordsDir.mkdirs();
}
     
    fileChooser.setInitialDirectory(recordsDir);
    
        File file = fileChooser.showOpenDialog(stage);
    Scanner  fileInput = new Scanner(new FileReader(file));
    
  
    while (fileInput.hasNext()) // while loop that reads the data from a file/ parses the date into a Date object and then adds the date object and 
                                //description into an event. It does this until the file has no more output. 
    {
        String date;
        date = fileInput.next();

       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //creating the formatter/parser
         Date date2 = formatter.parse(date); //parsing the date
        String description = fileInput.nextLine();  // getting the description
        list.add(new LocalEvent(date2, description)); // creating a localevent with the date and description from the file
        eventList.setItems(list); // setting the list
    }
    fileInput.close();
    
    
    fileChooser.setInitialDirectory(file.getParentFile()); // opon clicking loadlist the directory will open up to the last diretory used
    

        
    }
    
    private void Refresh(){ // Void variable that refreshes the description box and the localdate
    datePicker.setValue(LocalDate.now());
    descriptionTextField.setText(null);
    }
    
    
    
}
