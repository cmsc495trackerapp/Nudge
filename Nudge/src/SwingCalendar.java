/*File: SwingCalendar.java
 *Author: Zackary Scott, Orin, Chi
 *Date: 4/18/2019
 *Purpose: Creates a Calendar for the User to store tasks on any given day.
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class SwingCalendar extends JFrame {

    NewEvent event;
    String currentDateSelected;
    User user;
    JScrollPane scrollPane;
    JTable table;
    JTable table2;
    TaskTableModel taskTableModel;
    /**
     * Serial UID
     */
    private static final long serialVersionUID = -4848552573027674675L;
    DefaultTableModel model;
    Calendar cal = new GregorianCalendar();
    JLabel label;
    Connection con = DBConnect.connectDB();
    // Constructor

    SwingCalendar(User user) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Nudge");
        this.setLocation(750, 450);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.setVisible(false);
        // adds a window listner to react on window closing to shutdown server
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        this.user = user;
        // Label for the Month and Year.
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        // updates when previous button is clicked.
        JButton b1 = new JButton("Prev");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
                updateMonth();
            }
        });
        // updates when next button is clicked.
        JButton b2 = new JButton("Next");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
                updateMonth();
            }
        });

        // Opens a NewEvent window when clicked
        JButton b3 = new JButton("Create New Event");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                event.setVisible(true);
            }
        });
        // Variables for Calendar
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(b1, BorderLayout.WEST);
        panel.add(label, BorderLayout.CENTER);
        panel.add(b2, BorderLayout.EAST);
        // Labels Columns for calendar
        String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        model = new DefaultTableModel(null, columns);
        table = new JTable(model);
        table.setCellSelectionEnabled(true);
        
        taskTableModel = new TaskTableModel(this.user.getTasks());
        table2 = new JTable(taskTableModel);
        // Cell Listener
        //This is when date is clicked
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
                char monthChar[] = {month.charAt(0), month.charAt(1), month.charAt(2)};
                month = String.valueOf(monthChar);
                int year = cal.get(Calendar.YEAR);
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                Integer selectedDay = null;
                if (row >= 0 && col >= 0) {
                    selectedDay = (Integer) table.getValueAt(row, col);

                }
                //Updates teh frames from the methods when date
                //is clicked.
                if (selectedDay != null) {
                    if(selectedDay<=9){
                        currentDateSelected = formatMonthStr(month) + "/" +"0"+selectedDay + "/" + year;
                    }else{
                        currentDateSelected = formatMonthStr(month) + "/" + selectedDay + "/" + year;
                    }
                    event.updateBox(currentDateSelected);
                    System.out.println(currentDateSelected);
                    updateTaskPanel();
                }

            }
        });
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(225, 150));

        scrollPane = new JScrollPane(table2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 250));

        updateMonth();
        // Builds the calendar days.
        selectCurrentDay();
        event.updateBox(currentDateSelected);
        this.add(panel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pane, BorderLayout.WEST);
        this.add(b3, BorderLayout.SOUTH);
    }// end SwingCalendar constructor.
    //class made to handle making the table model.

    class TaskTableModel extends AbstractTableModel {
        //Makes variable to set the Column name.

        String column[] = {"Tasks"};
        private static final long serialVersionUID = 1L;
        
        List<Task> tasks;

        public TaskTableModel(List<Task> tasks) {
            this.tasks = tasks;
        }
        //Gets column count

        @Override
        public int getColumnCount() {
            return 1;
        }
        //Gets row count.

        @Override
        public int getRowCount() {
            return (tasks == null) ? 0 : tasks.size();
        }
        //Gets value of cell.

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return (tasks == null) ? null : tasks.get(rowIndex);
        }
        //Makes cells editable.

        @Override
        public boolean isCellEditable(int columnIndex, int rowIndex) {
            return true;
        }
        //sets the Column name

        @Override
        public String getColumnName(int index) {
            return column[index];
        }
    }
    //class made to Render proper cells and to be able to edit them.

    class EventCell extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        JPanel panel;
        JLabel text;
        JLabel text2;
        JLabel timeText;
        JButton deleteButton;
        Task task;
        //constructor

        public EventCell() {
            

            panel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;

            //new event button
            text = new JLabel();
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(5,30,5,5);
            panel.add(text, c);

            text2 = new JLabel();
            c.gridx = 0;
            c.gridy = 1;
            panel.add(text2, c);
            
            JLabel timeLabel = new JLabel("Time");
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5,60,5,40);
            panel.add(timeLabel, c);
            
            timeText = new JLabel();
            c.gridx = 1;
            c.gridy = 1;
            panel.add(timeText, c);
            
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0; i < user.getTasks().size(); i++){
                        if(user.getTasks().get(i).getId() == task.getId()){
                            user.getTasks().remove(i);
                            table2.removeAll();
                            deleteTaskFromDB(task.getId());
                            break;
                        }
                    }
                    repaintTheFrame();
                    JOptionPane.showMessageDialog(null, "Task deleted");
                }
                
            });
            c.gridx = 3;
            c.gridy = 1;
            panel.add(deleteButton, c);
        }

        private void updateData(Task task, boolean isSelected, JTable table) {
            this.task = task;

            text.setText(task.getCategory());
            text2.setText(task.getTask());
            timeText.setText(task.getTime());

            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(table.getBackground());
            }
        }

        @Override
        public Object getCellEditorValue() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            Task task = (Task) value;
            updateData(task, isSelected, table);
            return panel;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Task task = (Task) value;
            updateData(task, isSelected, table);
            return panel;
        }
    }

    // Stops the server
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        DBConnect.serverStop();
    }
    //formats month

    private String formatMonthStr(String month) {
        switch (month.toUpperCase()) {
            case "JAN":
                return "01";
            case "FEB":
                return "02";
            case "MAR":
                return "03";
            case "APR":
                return "04";
            case "MAY":
                return "05";
            case "JUN":
                return "06";
            case "JUL":
                return "07";
            case "AUG":
                return "08";
            case "SEP":
                return "09";
            case "OCT":
                return "10";
            case "NOV":
                return "11";
            case "DEC":
                return "12";
        }

        return null;
    }

    // Updates the month.
    private void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            model.setValueAt(day, i / 7, i % 7);
            i = i + 1;
        }
    }// end of updateMonth method
    //selects current day at start of program.

    private void selectCurrentDay() {
        // Creates a selection of the current date.
        Date date = new Date();
        int day = date.getDate();
        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {
                if (table.getValueAt(j, i) != null && day == (int) table.getValueAt(j, i)) {
                    table.changeSelection(j, i, false, false);
                }
            }
        } // end outer for loop.
        String strArray[] = date.toString().split(" ");
        currentDateSelected = formatMonthStr(strArray[1]) + "/" + strArray[2] + "/" + strArray[5];
        updateTaskPanel();
        event = new NewEvent(currentDateSelected, this.user, this);
    }
    //updates the task panel when a date is clicked
    
    public void updateTaskPanel() {
        getUserTask();
        taskTableMaker();
        repaintTheFrame();
    }
    //makes the table from the user ArrayList<Task> object.
    public void getUserTask(){
        //removes current user list
        while (user.getTasks().size() > 0) {
            user.newTasksList();
        }
        //adds tasks to user list for current date.
        try {
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM TASKTABLE "
                    + "WHERE username=? AND date=?");
            ps2.setString(1, user.getName());
            ps2.setString(2, currentDateSelected);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                user.getTasks().add(new Task(rs2.getString("category"), rs2.getString("date"), rs2.getString("time"), rs2.getString("task"), rs2.getInt(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(user.getTasks());
    }
    //Makes the data for the tables from the User ArrayList of Tasks.
    public void taskTableMaker() {
        table2.clearSelection();
        table2.removeAll();
        Object[][] data = new Object[user.getTasks().size()][1];
        System.out.println(user.getTasks().size());
        for (int i = 0; i < user.getTasks().size(); i++) {
            data[i][0] = user.getTasks().get(i);
        }
        table2.getColumn("Tasks").setCellRenderer(new EventCell());
        table2.getColumn("Tasks").setCellEditor(new EventCell());
        
        table2.setRowHeight(100);
        table2.setFocusable(false);
        table2.updateUI();
    }
    //Deletes task from database.
    public void deleteTaskFromDB(int taskId){
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM TASKTABLE WHERE ID=?");
            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //repaints the frame.

    public void repaintTheFrame() {
        this.invalidate();
        this.validate();
        this.repaint();
    }
}// end of SwingCalendar class
