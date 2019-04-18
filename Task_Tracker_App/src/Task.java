/*File: Task.java
 *Author: Zackary Scott
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
}
