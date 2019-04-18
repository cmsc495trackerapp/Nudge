import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class SwingCalendar extends JFrame {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -4848552573027674675L;
	DefaultTableModel model;
	Calendar cal = new GregorianCalendar();
	JLabel label;

	SwingCalendar() {
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Swing Calandar");
		this.setSize(600, 400);
		this.setLayout(new BorderLayout());
		this.setVisible(false);

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JButton b1 = new JButton("Prev");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});

		JButton b2 = new JButton("Next");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(b1, BorderLayout.WEST);
		panel.add(label, BorderLayout.CENTER);
		panel.add(b2, BorderLayout.EAST);

		String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		model = new DefaultTableModel(null, columns);
		JTable table = new JTable(model);
		table.setCellSelectionEnabled(true);
		
	
		
//		//Cell Listener
//		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
//		      public void valueChanged(ListSelectionEvent e) {
//		        Integer selectedData = null;
//		        int selectedRow = table.getSelectedRow();
//		        int selectedColumn = table.getSelectedColumn();
//		        System.out.println(selectedRow + " " + selectedColumn);
//		        selectedData =   (Integer) table.getValueAt(selectedRow, selectedColumn);
//		        
//		        System.out.println("Selected: " + selectedData);
//		      }
//		});
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int row = table.rowAtPoint(evt.getPoint());
			    int col = table.columnAtPoint(evt.getPoint());
			    if (row >= 0 && col >= 0) {
			    	Integer selectedData = null;
			    	selectedData =   (Integer) table.getValueAt(row, col);
			        System.out.println("Selected: " + selectedData);

			    }
			 }
			});
		
		
		
		
		
		JScrollPane pane = new JScrollPane(table);
		
		pane.setPreferredSize(new Dimension(225, 150));

		// Task text area
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		textArea.setLineWrap(true);
		textArea.setEditable(true);
		scrollPane.setPreferredSize(new Dimension(250, 250));

		
		this.add(panel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(pane, BorderLayout.WEST);

		
		this.updateMonth();
		
		Date day = new Date();
		int date = day.getDate();
		for(int i = 0; i < table.getColumnCount(); i++) {
			for(int j = 0; j < table.getRowCount(); j++) {
				Object n =  table.getValueAt(j, i);
				if(n != null) {
					if(date == (int) table.getValueAt(j, i)) {
						table.clearSelection();
						table.changeSelection(j, i, false, false);
					}
				}
				
			}
		}
		

	}
	
	private void formWindowClosing(java.awt.event.WindowEvent evt) {
        DBConnect.serverStop();        // TODO add your handling code here:
    }

	void updateMonth() {
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
			
			//TODO
			//if day has event then model.setValueAt(day + "*", i / 7, i % 7);
			
			//else do below
			model.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}

	}
}