/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicholas Rhoades
 */
import java.time.LocalDate;
import java.util.Date;

public class LocalEvent { // Basic event class that gets called when adding events
    private String description; // intializing description variable
    private LocalDate date; // initializing the localdate variable
    private Date ldate;  // initializing the Date variable

    /**
     * @return the description
     */
    public String getDescription() { // getting description
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) { // setting description
        this.description = description;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() { // getting local date
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) { //setting local date
        this.date = date;
    }
    
     public Date getlDate() { //getting date
        return ldate;
    }

    /**
     * @param date the date to set
     */
    public void setlDate(Date ldate) { // setting date
        this.ldate = ldate;
    }
    
    
    
    public LocalEvent(LocalDate date, String description){ // Local event class that has the parameters LocalDate, and String
    
        this.setDate(date);
        this.setDescription(description);
    }
    
     public LocalEvent(Date ldate, String description){ // Local event class that has the parameters Date, and String
 
        this.setlDate(ldate);
        this.setDescription(description);
    } 
      
    
    
    public void saveEvent(){
    
    }
    
    @Override // overriding the toString() method to produce two different results. One for clicking add event and one for loading the event
    public String toString(){
        if (this.getDate() == null) {
    return "At: " + this.getlDate().toString().substring(0, 10) + " " + this.getDescription();
        }
        else return "At: " + this.getDate() + " " + this.getDescription();
    }
    
 
  
    
}
