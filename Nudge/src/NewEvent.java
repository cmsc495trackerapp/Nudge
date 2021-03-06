/*File: User.java
 *Author: Zackary Scott, Orin, Chi
 *Date: 4/18/2019
 *Purpose: Creates a task object that stores tasks in a User HashMap<Task>. The 
 *tasks will be put into the database.
 */
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class NewEvent extends javax.swing.JFrame {

    /**
     * Creates new form CreateNewEventGUI
     */
    User user;
    Connection con = DBConnect.connectDB();
    SwingCalendar frame;

    public NewEvent(String currentDate, User user, SwingCalendar frame) {
        super("Nudge");
        initComponents();
        this.user = user;
        this.frame = frame;
        this.setTitle("Nudge");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jColorChooser1 = new javax.swing.JColorChooser();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        eventBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        timeField = new javax.swing.JTextField();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jDesktopPane1.setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(650, 350));

        jLabel1.setText("Create New Event");

        jLabel2.setText("Event Category");

        eventBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Family", "Friends", "Work", "School" }));
        eventBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Date");

        taskTextArea.setColumns(20);
        taskTextArea.setRows(5);
        taskTextArea.setText(" ");
        jScrollPane1.setViewportView(taskTextArea);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Start Time");

        dateTextField.setEditable(false);
        dateTextField.setToolTipText("Format MM/DD/YEAR numbers only.");
        dateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTextFieldActionPerformed(evt);
            }
        });

        timeField.setText(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateTextField)
                                    .addComponent(eventBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(timeField)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addGap(11, 11, 11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(eventBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eventBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventBoxActionPerformed
    //this saves the task to the database and repaints the SwingCalendar
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            if (!taskTextArea.getText().isEmpty()
                    && !dateTextField.getText().isEmpty()
                    && !taskTextArea.getText().isEmpty()) {
                String category = eventBox.getSelectedItem().toString();
                String date = dateTextField.getText();
                String time = null;
                int userTime = parseInt(timeField.getText());

                if (userTime > 2359) {
                    JOptionPane.showMessageDialog(null, "Please enter 24-hour (Military) time");

                } else {
                    time = String.format("%04d", userTime);
                                   String task = taskTextArea.getText();
                PreparedStatement ps = con.prepareStatement("INSERT INTO "
                        + "TASKTABLE (USERNAME, CATEGORY, DATE, TIME, TASK)"
                        + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                ps.setString(
                        1, user.getName());
                ps.setString(
                        2, category);
                ps.setString(
                        3, date);
                ps.setString(
                        4, time);
                ps.setString(
                        5, task);
                ps.executeUpdate();
                ResultSet key = ps.getGeneratedKeys();

                while (key.next()) {
                    user.getTasks().add(new Task(category, date, time, task, key.getInt(1)));
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Task created successfully!");
                dispose();
                frame.updateTaskPanel();

                }
 
            } else {
                JOptionPane.showMessageDialog(null,
                        "Check to make everything is filled in!");
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Wrong format for input.");
            //JOptionPane.showMessageDialog(null, "Please fill out all data fields.");
        } catch (NumberFormatException ex1) {
            
            JOptionPane
            .showMessageDialog(null, "Wrong time input.");

        }
    }//GEN-LAST:event_saveButtonActionPerformed


    private void dateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTextFieldActionPerformed

    public void updateBox(String currentDate) {
        dateTextField.setText(currentDate);
    }

    private void repaintFrame() {
        frame.table2.updateUI();
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dateTextField;
    private javax.swing.JComboBox<String> eventBox;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextArea taskTextArea;
    private javax.swing.JTextField timeField;
    // End of variables declaration//GEN-END:variables
}
