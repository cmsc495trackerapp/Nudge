/*File: Task.java
 *Author: Zackary Scott, Orin
 *Date: 4/18/2019
 *Purpose: Task is the object that will be stored in the User HashMap by the
 *NewEvent.java file.
 */
public class Task {
    //Variables to store task information
    private String date = "";
    private String task = "";
    private String time = "";
    private String category = "";
    private int id;
    //Constructor
    Task(String category, String date, String time, String task, int id){
        this.category = category;
        this.date = date;
        this.task = task;
        this.time = time;
        this.id = id;
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
