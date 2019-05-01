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
import static java.util.Collections.list;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
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
      String[] nEvent = new String[50];  // creates a string array of 50 places
        String space = " "; // string to space the datepicker and description strings

    public GuiController() throws FileNotFoundException {
       
    }
     
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
    TextField listName;
    @FXML
    ListView<LocalEvent> eventList; // delcaring an eventlist
      ObservableList<LocalEvent> list = FXCollections.observableArrayList();
       
    ListView<LocalEvent> newEventList;
        ObservableList<LocalEvent> newList = FXCollections.observableArrayList();
  
    @FXML
    ListView<String> fileList;
      ObservableList<String> list2 = FXCollections.observableArrayList();
    int increase = 0;

 
    
    //creating a list of arrays to store the events
  
  
     
    
    
    
   
       
 
    
   @FXML
   public void showFile(Event e) throws IOException
   {
       
       list.clear();
       eventList.setItems(null);
     File recordsDir = new File("C://Users/Public/Documents/.To-Do List/records"); // creates a directory if the directory doesnt exist already
if (! recordsDir.exists()) {
    recordsDir.mkdirs();
}

    File fileNames = new File("C://Users//Public//Documents//.To-Do List//records//fileNames.txt");
    if (! fileNames.exists())
    {
   if (fileNames.createNewFile())
   {
       System.out.println("File has been created.");
   }
  
    }
    
    list2.clear();
     Scanner  fileInput = new Scanner(new FileReader("C://Users//Public//Documents//.To-Do List//records//fileNames.txt"));
  
     while (fileInput.hasNextLine())
     {
        
         String s = fileInput.nextLine();
        String f = s.substring(0, s.length()-4);
         list2.add(f);
     fileList.setItems(list2);
    
     }
   
  Refresh();
    
   }
    
   @FXML
    public void loadFile() throws FileNotFoundException, ParseException
    {
  list.clear();
        
        
    String fileLocation = "C://Users//Public//Documents//.To-Do List//records//";
    String textfile = fileList.getSelectionModel().getSelectedItem() + ".txt";
    fileLocation = fileLocation + textfile;
    
    try {
    Scanner  fileInput = new Scanner(new FileReader(fileLocation));
    
  
  
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
    }
      catch(Exception e) {
    System.out.println(fileLocation);
    }
  
    fileList.refresh();
    
    }
    
    @FXML
    private void addEvent(Event e) throws ParseException{ // method that adds an event when the user clicks add event
        int i;
        i = 0;


if(datePicker.getValue() != null && descriptionTextField.getText()!= null)
    list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));  // creating new event using current datepicker value and text in 
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
    
        //C:\Users\Public\Documents
           File recordsDir = new File("C://Users/Public/Documents/.To-Do List/records"); // creates a directory if the directory doesnt exist already
if (! recordsDir.exists()) {
    recordsDir.mkdirs();
}

    File fileNames = new File("C://Users//Public//Documents//.To-Do List//records//fileNames.txt");
    if (! fileNames.exists())
    {
   if (fileNames.createNewFile())
   {
       System.out.println("File has been created.");
   }
   else 
   {
   System.out.println("File already exists.");
   }
    }
        
        
        int i =0; // declaring and setting the array index to 0
    BufferedWriter out = null; // declaring bufferedwriter variable
         File file;
         increase = 0;
    if (listName.getText().isEmpty())
    {
        file = new File(recordsDir, "List1" + ".txt");
   increase=1;

  while(file.exists()){
        increase++;
        file = new File(recordsDir, "List" + increase +  ".txt" );
            
    }
  
    }
    else{
        file = new File(recordsDir, listName.getText() + ".txt" );
    while(file.exists()){
        increase++;
        file = new File(recordsDir, listName.getText() + increase + ".txt" );
            
    }
    }
       try {  // writing all events to a new file
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
        
        
   FileWriter fstream = new FileWriter("C://Users//Public//Documents//.To-Do List//records//fileNames.txt", true); //if this file exists append it
   out = new BufferedWriter(fstream);
   out.write(file.getName()); //writing the name of the new file to this document
   out.newLine();
   out.close();
  
   
 
    
      try {
    fileChooser.setInitialDirectory(file.getParentFile());  // sets the initial directory to the directory last used
    }
    catch (Exception ex)
            {
            ex.printStackTrace();
            }
      
       list2.clear(); //clears the list of all document names
       
     Scanner  fileInput = new Scanner(new FileReader("C://Users//Public//Documents//.To-Do List//records//fileNames.txt"));
    
     while (fileInput.hasNextLine())
     {
        
         String s = fileInput.nextLine();
         String f = s.substring(0, s.length()-4);
         list2.add(f);
     fileList.setItems(list2); //loads a new list with all the names of the saved documents
    
     }
    
  list.clear(); //clears the event list
  eventList.refresh();
  int aIndex =0;
 while(aIndex != 49)
 {
 nEvent[aIndex] = null;
 aIndex = aIndex + 1;
 }
 
   
      
        Refresh();
    }
    
    
    @FXML
    private void deleteList(Event e) throws FileNotFoundException, IOException { // When the deletelist button is hit, this method is active. 
       
    
    File recordsDir = new File("C://"
            + "Users/Public/Documents/.To-Do List/records");
if (! recordsDir.exists()) { // checking to see if this directory has been made, if not it will make it. 
    recordsDir.mkdirs();
}
     
    fileChooser.setInitialDirectory(recordsDir);
    

String fileLocation = "C://Users//Public//Documents//.To-Do List//records//";
 String textfile = fileList.getSelectionModel().getSelectedItem();
    fileLocation = fileLocation + textfile + ".txt"; //Gets the selected file name and adds it to the file location. 
File file = new File(fileLocation);
        String x = file.getName(); //getting the file name so we can delete it from the list later
      
        
        if(file.delete()) 
        { 
            System.out.println(x +" deleted successfully."); 
            
        } 
        else
        { 
            System.out.println("Failed to delete the list."); 
        } 
          BufferedWriter out = null;
          File tempFile = new File("myTempFile.txt");
          Scanner  fileInput = new Scanner(new FileReader("C://Users//Public//Documents//.To-Do List//records//fileNames.txt"));
         
                LinkedList<String> LL= new LinkedList<String>();
        
             
    
    if(!list.isEmpty()){ // clearing the event list if its not empty 
  list.clear(); 
  eventList.setItems(list);}
  list2.remove(x);//removes the name of the deleted list.
    while (fileInput.hasNextLine())         
    {
     String newS = fileInput.nextLine(); //opens the fileNames document and adds all lines to a linkedlist except for the one we just deleted
        LL.add(newS);
       
        LL.remove(x); 
        
    }
     FileWriter fstream = new FileWriter("C://Users//Public//Documents//.To-Do List//records//fileNames.txt", false); //overwrites the old file 
      out = new BufferedWriter(fstream);
    
    while (!LL.isEmpty()){ //loop to pop and store the names from the Linked List until its empty
        out.write(LL.pop());
        out.newLine();
    }
        out.close();
       
    
    fileInput.close();
 
     list.clear(); //clearing eventlist
     
       eventList.setItems(list);
       fileList.setItems(list2);
       fileList.refresh();
        showFile(e);
        
        
    }
    
    private void Refresh(){ // Void variable that refreshes the description box and the localdate
    datePicker.setValue(LocalDate.now());
    descriptionTextField.setText(null);
    listName.setText("");
    }

   

    
    
    
}
