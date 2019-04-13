/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicholas Rhoades
 */
public class loadEvent {
    private String fileName;
    
    public String getFileName()
    {
    return fileName;
    }
    public void setFileName(String fileName)
    {
    fileName = this.fileName;
    }
    
   public loadEvent(String name)
    {
    this.setFileName(name);
    }
    
    @Override
    public String toString()
    {
    return this.getFileName();
    }
    
    
}
