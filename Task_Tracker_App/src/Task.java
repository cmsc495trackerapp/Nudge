
import java.util.Collections;

/*File: Task.java
 *Author: Zackary Scott, Orin
 *Date: 4/18/2019
 *Purpose: Task is the object that will be stored in the User HashMap by the
 *NewEvent.java file.
 */
public class Task implements Comparable<Task> {

    //Variables to store task information
    private String date = "";
    private String task = "";
    private String time = "";
    private String category = "";
    private int id;

    //Constructor
    Task(String category, String date, String time, String task, int id) {
        this.category = category;
        this.date = date;
        this.task = task;
        this.time = time;
        this.id = id;
    }

    //toString override to return date and task string
    @Override
    public String toString() {
        return date + "\n" + task;
    }

    //getters
    public String getDate() {

        return this.date;
    }

    public String getTime() {
        return this.time;
    }
    public String getCategory(){
        return category;
    }
    public String getTask() {
        return task;
    }
    //setters
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setTime(String time){
        this.time = time;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public int compareTo(Task o) {
        if (getTime() == null || o.getTime() == null) {
            return 0;
        }
        return getTime().compareTo(o.getTime());
    }
    public int getId(){
        return id;
    }

}
