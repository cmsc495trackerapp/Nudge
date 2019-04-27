/*File: SwingCalendar.java
 *Author: Zackary Scott, Orin, Chi
 *Date: 4/18/2019
 *Purpose: Creates a Calendar for the User to store tasks on any given day.
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class SwingCalendar extends JFrame implements TableCellRenderer{
    NewEvent event;
    String currentDateSelected;

    /**
     * Serial UID
     */
    private static final long serialVersionUID = -4848552573027674675L;
    DefaultTableModel model;
    Calendar cal = new GregorianCalendar();
    JLabel label;
    //Constructor
    SwingCalendar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Swing Calandar");
        this.setLocation(750, 450);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.setVisible(false);
        //adds a window listner to react on window closing to shutdown server
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        //Label for the Month and Year.
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        //updates when previous button is clicked.
        JButton b1 = new JButton("Prev");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
                updateMonth();
            }
        });
        //updates when next button is clicked.
        JButton b2 = new JButton("Next");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
                updateMonth();
            }
        });
        
        //Opens a NewEvent window when clicked 
        JButton b3 = new JButton("Create New Event");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                event.setVisible(true);
            }
        });
        //Variables for Calendar
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(b1, BorderLayout.WEST);
        panel.add(label, BorderLayout.CENTER);
        panel.add(b2, BorderLayout.EAST);
        //Labels Columns for calendar
        String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model);
        table.setCellSelectionEnabled(true);
        //Cell Listener
        /*TODO: display tasks when the date is selected*/
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                String month = cal.getDisplayName(Calendar.MONTH, 
                                            Calendar.LONG, 
                                            Locale.US);
                char monthChar[] = {month.charAt(0),month.charAt(1),month.charAt(2)};
                month = String.valueOf(monthChar);
                int year = cal.get(Calendar.YEAR);
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                Integer selectedDay = null;
                if (row >= 0 && col >= 0) {
                    selectedDay =   (Integer) table.getValueAt(row, col);
                    System.out.println("Selected: " + selectedDay);

                }
                if(selectedDay != null) {
                	event.updateBox(month+" "+ selectedDay+" "+year);
                }
                
            }
        });
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(225, 150));
        //Task text area.
        
        List<Task> tasks = new ArrayList();
    	Task task1 = new Task("today","get eggs");
    	Task task2 = new Task("today","get asdfasdf");
    	Task task3 = new Task("today","get egasdgasgs");
    	Task task4 = new Task("today","get asdjasrdtjhga");
    	Task task5 = new Task("today","get asdjsadfh");
    	tasks.add(task1);
    	tasks.add(task2);
    	tasks.add(task3);
    	tasks.add(task4);
    	tasks.add(task5);
        
    	getTableCellRendererComponent();
        
    	JTable table2 = new JTable(data,colNames);
        JScrollPane scrollPane = new JScrollPane(table2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        
        
        
        
        
        
        
        
        
        
        this.add(panel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pane, BorderLayout.WEST);
        this.add(b3, BorderLayout.SOUTH);
        //Builds the calendar days.
        updateMonth();
        selectCurrentDay(table);
    }//end SwingCalendar constructor.
    
    
     public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
    	 Task task = (Task)value;
        JButton showButton = new JButton("Edit");
        showButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(null, "HA-HA!");
          }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(task.getTask()));
        panel.add(showButton);

        if (isSelected) {
          panel.setBackground(table.getSelectionBackground());
        }else{
          panel.setBackground(table.getBackground());
        }
        return panel;
      }
    
    
    
    
    
    //Stops the server
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        DBConnect.serverStop();
    }
    
//    //UpdateTasks
//    private void populateTasks(JScrollPane scrollPane) {
//    	
//    	//get task from User.java from selected date
//    	List<Task> tasks = new ArrayList();
//    	Task task1 = new Task("today","get eggs");
//    	Task task2 = new Task("today","get asdfasdf");
//    	Task task3 = new Task("today","get egasdgasgs");
//    	Task task4 = new Task("today","get asdjasrdtjhga");
//    	Task task5 = new Task("today","get asdjsadfh");
//    	tasks.add(task1);
//    	tasks.add(task2);
//    	tasks.add(task3);
//    	tasks.add(task4);
//    	tasks.add(task5);
//    	
//    	Object[] colNames = {"Task","Edit","Delete"};
//    	Object[][] data = new Object[tasks.size()][3];
//    	
//    	for (int i = 0; i < tasks.size(); i++) {
//    		data[i][1] = tasks.get(i).getTask();
//    	}
//    	
//    	JTable table = new JTable(data,colNames);
//    	scrollPane.add(table);
//    	
//    	
//    }
    
    private void addTaskBlock() {
    	
    	
    	
    	
    }
    
    //Updates the month.
    private void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, 
                                            Calendar.LONG, 
                                            Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            //TODO
            //if day has event then model.setValueAt(day + "*", i / 7, i % 7);

            //else do below
            model.setValueAt(day, i / 7, i % 7);
            i = i + 1;
        }
    }//end of updateMonth method
    private void selectCurrentDay(JTable table){
        //Creates a selection of the current date.
        Date date = new Date();
        int day = date.getDate();
        for(int i = 0; i < table.getColumnCount(); i++) {
            for(int j = 0; j < table.getRowCount(); j++) {
                if(table.getValueAt(j,i) != null 
                        && day == (int) table.getValueAt(j, i)) {
                    table.changeSelection(j, i, false, false);
                }
            }
        }//end outer for loop.
        String strArray[] = date.toString().split(" ");
        currentDateSelected = strArray[1]+" "+strArray[2]+" "+strArray[5];
        event = new NewEvent(currentDateSelected);
    }
}//end of SwingCalendar class