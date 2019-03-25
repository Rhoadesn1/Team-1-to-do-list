
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
    
    public onStart()
    {
    
     FileChooser firstFileChooser = new FileChooser();
     
     File recordsDir = new File(System.getProperty("user.home"), ".To-Do List/records");
if (! recordsDir.exists()) {
    recordsDir.mkdirs();
}
       firstFileChooser.setTitle("Load Dialog");
       firstFileChooser.getTitle();
     firstFileChooser.setInitialDirectory(recordsDir);
     File file = firstFileChooser.showOpenDialog(new Stage());
    
     firstFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
     
    }
    
    
    
}
