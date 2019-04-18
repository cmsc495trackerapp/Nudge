/*File: User.java
 *Author: Zackary Scott
 *Date: 4/18/2019
 *Purpose: Creates a User object that stores tasks in a HashMap. The tasks will
 *be grabbed by the date which will be formatted YearMonthDay.
 */
import java.util.HashMap;
public class User {
    //Variables for user information and storing tasks for the user.
    private HashMap<String, Task> tasks = new HashMap<>();
    private String userName = "";
    private String password = "";
    //user data needed to get the proper tasks back from database.
    User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    //toString method to return useful data of user.
    @Override
    public String toString(){
        return userName + " " + password;
    }
    public HashMap<String, Task> getTasks (){
        return tasks;
    }
    public void setTasks(String date, Task task){
        tasks.put(date, task);
    }
}
