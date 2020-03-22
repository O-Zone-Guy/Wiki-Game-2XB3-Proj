/**
   @brief A module that provides a User Interface for a user to interact with.
   @author Harshveer Singh Gaba
   @file UInterface.java
   @date 2020-03-22
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

/**
 * @brief User Interface class
 */
public class UInterface {

	// State variables
	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	String pageOne, pageTwo;
	int pageOneId, pageTwoId;

	/**
	 * @brief Launching the application and displaying the form
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UInterface window = new UInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @brief Constructor for User Interface class module
	 */
	public UInterface() {
		initialize();
	}

	/**
	 * @brief Adding components and functions to the frame
	 */
	private void initialize() {
		
		// Creating a new Frame and setting basic operations
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		// Creating a new text field for input for page 1
		t1 = new JTextField();
		t1.setBounds(62, 84, 152, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		// Creating a new text field for input for page 2
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(361, 84, 152, 20);
		frame.getContentPane().add(t2);
		
		// Creating a label to provide a description for the first text field
		JLabel l1 = new JLabel("Insert Page 1");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(62, 59, 152, 14);
		frame.getContentPane().add(l1);
		
		// Creating a label to provide a description for the second text field
		JLabel l2 = new JLabel("Insert Page 2");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(361, 59, 152, 14);
		frame.getContentPane().add(l2);
		
		// Creating a new button for execution
		JButton btnNewButton = new JButton("Go!");
		// Adding an ActionListener to the button for it to access ButtonClicked functionality
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * @brief Retrieves data from two text fields and performs functions
			 * @param ActionEvent Button Clicked ActionEvent
			 */
			public void actionPerformed(ActionEvent e) {
				pageOne = t1.getText();
				pageTwo = t2.getText();
				
				SQLHandler sql = new SQLHandler("data/database.db3");
				
				try {
					pageOneId = sql.getPageId(pageOne);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Page One is invalid");
				}
				try {
					pageTwoId = sql.getPageId(pageTwo);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Page Two is invalid");
				}
			}
		});
		btnNewButton.setBounds(242, 138, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//TODO: Creating a new label to add arrow image 
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(224, 230, 127, 20);
		frame.getContentPane().add(lblNewLabel);
	}
}
