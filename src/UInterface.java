/**
   @brief A module that provides a User Interface for a user to interact with.
   @author Harshveer Singh Gaba
   @file UInterface.java
   @date 2020-03-22
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;

/**
 * @brief User Interface class
 */
public class UInterface{

	// State variables
	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	String pageOne, pageTwo, pageTwo_determined;
	int pageOneId, pageTwoId, pageTwo_determinedId;

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
		frame.setBounds(0, 0, 524, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// MIKE: title
		frame.setTitle("Wikigame");

		//  MIKE: draws frame in center of screen
		frame.setLocationRelativeTo(null);;

		// Creating a new text field for input for page 1
		t1 = new JTextField();
		t1.setBounds(96, 94, 152, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		t1.setText("Kleroterion");

		// Creating a new text field for input for page 2
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(286, 94, 152, 20);
		frame.getContentPane().add(t2);
		t2.setText("Pinakion");

		// Creating a label to provide a description for the first text field
		JLabel l1 = new JLabel("Start Page");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(96, 62, 152, 14);
		frame.getContentPane().add(l1);

		// Creating a label to provide a description for the second text field
		JLabel l2 = new JLabel("End Page");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(282, 62, 152, 14);
		frame.getContentPane().add(l2);

		// Example Text
		JLabel lblNewLabel_1 = new JLabel("Example ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 24, 100, 14);
		frame.getContentPane().add(lblNewLabel_1);

		// Custom Text
		JLabel lblNewLabel_2 = new JLabel("Custom ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 96, 100, 14);
		frame.getContentPane().add(lblNewLabel_2);

		// Creating a combobox for a predetermined destination
		String[] start_examples = new String[] {"", "Glover, Missouri TO Doss, Missouri"};
		JComboBox c1 = new JComboBox(start_examples);
		c1.setBounds(96, 24, 348, 20);
		frame.getContentPane().add(c1);

		// Creating a new button for execution
		JButton btnNewButton = new JButton("Find Paths!");
		// Adding an ActionListener to the button for it to access ButtonClicked functionality
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * @brief Retrieves data from two text fields and performs functions
			 * @param e Button Clicked ActionEvent
			 */
			public void actionPerformed(ActionEvent e) {
				String start = "";
				String end = "";

				String combo_value = String.valueOf(c1.getSelectedItem()).trim();
				System.out.println(combo_value);
				if (!combo_value.equals(""))
				{
					String[] split = combo_value.split(" TO ");
					start = split[0];
					end = split[1];
					System.out.println(start + "" + end);
				}
				else
				{
					start = t1.getText().trim();
					end = t2.getText().trim();
				}

				int start_id = 0;
				int end_id = 0;

				try {
					start_id = SQLHandler.getPageId(start);
				} catch (SQLException ex) {
					ex.printStackTrace();
					return;
				}

				try {
					end_id = SQLHandler.getPageId(end);
				} catch (SQLException ex) {
					ex.printStackTrace();
					return;
				}

				try {
					runSearch(start_id, end_id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(190, 140, 148, 36);
		frame.getContentPane().add(btnNewButton);

	}

	public void runSearch(int start, int end) throws SQLException {
		// MIKE: create new frame for each result.
		Set<PathT> result = Algorithms.getPaths(start, end);
		NodeT first = SQLHandler.getNode(start);

		for (PathT path : result)
		{
			ArrayList<NodeT> nodes = new ArrayList<>();
			nodes.add(first);
			nodes.addAll(path.getPath());

			UIResults pt = new UIResults(nodes);
		}
	}
}

