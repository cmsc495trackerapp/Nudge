/*File: Task.java
 *Author: Zackary Scott Orin
 *Date: 4/18/2019
 *Purpose: Creates a Task object stored in the User HashMap.
 */
public class Task {
    //Variables to store task information
    private String date = "";
    private String task = "";
    //Constructor
    Task(String date, String task){
        this.date = date;
        this.task = task;
    }
    //toString override to return date and task string
    @Override
    public String toString(){
       return date + "\n" +task; 
    }
    
    //getters
    public String getDate(){
    	
		return this.date;
    }
    
    public String getTask(){
    	
		return this.task;
    }
    
    //setters
    public void setDate(String date){
    	this.date = date;
    }
    
    public void setTask(String task){
    	this.task = task;
    }
    
    
    
}
