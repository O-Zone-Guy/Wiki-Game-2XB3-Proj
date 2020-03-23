/**
   @brief A module that provides a User Interface for a user to interact with.
   @author Harshveer Singh Gaba
   @file UInterface_radio.java
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
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * @brief User Interface class
 */
public class UInterface_radio {

	// State variables
	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	String pageOne, pageTwo, pageTwo_determined;
	int pageOneId, pageTwoId, pageTwo_determinedId;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * @brief Launching the application and displaying the form
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UInterface_radio window = new UInterface_radio();
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
	public UInterface_radio() {
		initialize();
	}

	/**
	 * @brief Adding components and functions to the frame
	 */
	private void initialize() {
		
		// Creating a new Frame and setting basic operations
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		// Creating a new text field for input for page 1
		t1 = new JTextField();
		t1.setBounds(10, 84, 152, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		// Creating a new text field for input for page 2
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(360, 84, 152, 20);
		frame.getContentPane().add(t2);
		t2.setEditable(false);
		
		// Creating a label to provide a description for the first text field
		JLabel l1 = new JLabel("Start Page");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(10, 59, 152, 14);
		frame.getContentPane().add(l1);
		
		// Creating a label to provide a description for the second text field
		JLabel l2 = new JLabel("End Page");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(360, 59, 152, 14);
		frame.getContentPane().add(l2);
		
		// Creating a combobox for a predetermined destination
		JComboBox c1 = new JComboBox();
		c1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: Add all the predetermined destinations
			}
		});
		c1.setBounds(190, 83, 143, 22);
		frame.getContentPane().add(c1);
		c1.setEnabled(false);
		
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
				pageTwo_determined = (String) c1.getSelectedItem();
				
				if (pageTwo_determined == null && pageTwo.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Please input an End Page or select one from the Predetermined Destinations");
				}
				
				if (pageOne.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Please input a Start Page");
				}
				
				SQLHandler sql = new SQLHandler("data/database.db3");
				try {
					pageOneId = sql.getPageId(pageOne);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Start Page is invalid");
				}
				try {
					pageTwoId = sql.getPageId(pageTwo);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "End Page is invalid");
				}
				try {
					pageTwo_determinedId = sql.getPageId(pageTwo_determined);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Predetermined End Page is invalid");
				}
				
				if (pageTwo_determined == null && pageTwo != null) {
					//TODO: Perform function
				}
				
				if (pageTwo_determined != null && pageTwo == null) {
					//TODO: Perform function
				}
			}
		});
		btnNewButton.setBounds(214, 314, 89, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		JLabel lblNewLabel_1 = new JLabel("Predetermined Destination");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(172, 59, 178, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("//TODO: Add Graph");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 115, 502, 188);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Start-End");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				t2.setEditable(true);
				c1.setEnabled(false);;
				btnNewButton.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(10, 314, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Start-Predetermined");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				t2.setEditable(false);
				c1.setEnabled(true);
				btnNewButton.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(374, 314, 138, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
	}
}
