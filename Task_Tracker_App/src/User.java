/*File: User.java
 *Author: Zackary Scott
 *Date: 4/18/2019
 *Purpose: Creates a User object that stores tasks in a HashMap. The tasks will
 *be grabbed by the date which will be formatted MM/DD/YEAR.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class User {
    //Variables for user information and storing tasks for the user.
    private List<Task> tasks;
    private String userName = "";
    private String password = "";
    //user data needed to get the proper tasks back from database.
    User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.tasks = new ArrayList();
    }
    //toString method to return useful data of user.
    @Override
    public String toString(){
        return userName + " " + password;
    }
    public List<Task> getTasks (){
        Collections.sort(tasks);
        return tasks;
    }
    public void newTasksList(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.remove(i);
        }
    }
    public String getName(){
        return userName;
    }
}
