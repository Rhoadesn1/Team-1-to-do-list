
import java.io.File;
import javafx.event.Event;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicholas Rhoades
 */
public class onStart {
    
    public onStart() // this class is called on start. If you need to add something upon launch. DO IT HERE
    {
    
     FileChooser firstFileChooser = new FileChooser(); // creates a filechooser
     
     File recordsDir = new File(System.getProperty("user.home"), ".To-Do List/records"); // creates a directory if the directory doesnt exist already
if (! recordsDir.exists()) {
    recordsDir.mkdirs();
}
       firstFileChooser.setTitle("Load Dialog"); // generic load dialog
       firstFileChooser.getTitle(); 
     firstFileChooser.setInitialDirectory(recordsDir); // sets directory to the one we created
     File file = firstFileChooser.showOpenDialog(new Stage()); // opens the load file stage/ window
    
     firstFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt")); // allows users to only open txt files
     
     
     
     
     
     
    }
    
    
    
}
