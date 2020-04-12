package cas.xb3.proj.UI;

/**
 @brief A module that provides a User Interface for a user to interact with.
 @author Harshveer Singh Gaba
 @file UInterface.java
 @date 2020-03-22
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cas.xb3.proj.NodeT;
import cas.xb3.proj.PathT;
import cas.xb3.proj.alg.Algorithms;
import cas.xb3.proj.alg.SQLHandler;

/**
 * @brief User Interface class
 */
public class UInterface{

  // State variables
  private JFrame frame;
  private JTextField t1;
  private JTextField t2;
  private JTextField t3;
  private int mode = 0;

  /**
   * @brief Launching the application and displaying the form
   */
  public static void main(String[] args) {
    UInterface window = new UInterface();
    window.frame.setVisible(true);
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
    frame.setBounds(0, 0, 520, 300);
    frame.getContentPane().setLayout(null);
    frame.setTitle("Wiki Game Shortest Path");
    frame.setLocationRelativeTo(null); // draw frame in center of screen

    // Creating a new text field for input for page 1
    t1 = new JTextField();
    t1.setBounds(96, 94, 152, 20);
    frame.getContentPane().add(t1);
    t1.setText("Kleroterion"); // set example text

    // Creating a new text field for input for page 2
    t2 = new JTextField();
    t2.setBounds(286, 94, 152, 20);
    frame.getContentPane().add(t2);
    t2.setText("Homer");

    t3 = new JTextField();
    t3.setBounds(286, 140, 152, 20);
    frame.getContentPane().add(t3);
    t3.setText("France");

    // Creating a label to provide a description for the first text field
    JLabel l1 = new JLabel("Start Page");
    l1.setBounds(96, 62, 152, 14);
    frame.getContentPane().add(l1);

    // Creating a label to provide a description for the second text field
    JLabel l2 = new JLabel("End Page");
    l2.setBounds(286, 62, 152, 14);
    frame.getContentPane().add(l2);

    // Example Text
    JLabel example_label = new JLabel("Example ");
    example_label.setBounds(24, 24, 100, 14);
    frame.getContentPane().add(example_label);

    // Custom Text
    JLabel custom_label = new JLabel("Custom ");
    custom_label.setBounds(24, 96, 100, 14);
    frame.getContentPane().add(custom_label);

    // Must Include Text
    JLabel must_label = new JLabel("Path Must Include Page ");
    must_label.setBounds(96, 140, 200, 14);
    frame.getContentPane().add(must_label);

    // Creating a combobox for a predetermined destination
    String[] start_examples = new String[] {"CUSTOM",
        "Glover, Missouri TO Doss, Missouri",
        "Lee's Summit, Missouri TO Missouri Route 350",
    };
    JComboBox<String> c1 = new JComboBox<>(start_examples);
    c1.setBounds(96, 24, 341, 20);
    frame.getContentPane().add(c1);

    // Creating a new button for execution
    JButton button = new JButton("");
    button.setText("Find Paths!");
    button.setBounds(190, 190, 148, 36);
    // Adding an ActionListener to the button for it to access ButtonClicked functionality
    button.addActionListener(new ActionListener() {
      /**
       * @brief Retrieves data from two text fields and performs functions
       * @param e Button Clicked ActionEvent
       */
      public void actionPerformed(ActionEvent e) {
        mode = 0; // mode 0 uses text fields, mode 1 uses dropdown.

        String start = t1.getText().trim();
        String end = t2.getText().trim();
        String must = t3.getText().trim();

        String combo_value = String.valueOf(c1.getSelectedItem()).trim();
        if (!combo_value.equals("CUSTOM"))
        {
          String[] split = combo_value.split(" TO ");
          start = split[0];
          end = split[1];
          mode = 1;
        }

        int start_id = 0;
        int end_id = 0;
        int must_id = -1; // -1 is not a valid id.

        try {
          start_id = SQLHandler.getPageId(start);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Start Page is not a page");
          return;
        }

        try {
          end_id = SQLHandler.getPageId(end);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "End Page is not a page");
          return;
        }

        if (!must.equals("")) {
          try {
            must_id = SQLHandler.getPageId(must);
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Must Include Page is not a page");
            return;
          }
        }

        try {
          run(start_id, end_id, must_id);
        } catch (SQLException ex) {
          //ex.printStackTrace();
          JOptionPane.showMessageDialog(null, "SQL Error");
        }

        button.setText("Find Paths!");
      }
    });
    frame.getContentPane().add(button);

  }

  /**
   * @brief Run search
   * @param start id of the start page
   * @param end id of the end page
   * @param must_id id of the page that must be included in the path of any results.
   */
  public void run(int start, int end, int must_id) throws SQLException {
    // create new frame for each result.
    Set<PathT> result = Algorithms.getPaths(start, end);
    NodeT first = SQLHandler.getNode(start);

    String start_name = SQLHandler.getPageName(start);
    String end_name = SQLHandler.getPageName(end);

    if (result.size() == 0) {
      JOptionPane.showMessageDialog(null, "There is no direct path from " + start_name + " to " + end_name);
      return;
    }

    Set<PathT> paths = result;

    // if mode is custom and must_id is valid
    if (mode == 0 && must_id >= 0)
    {
      String must_name = SQLHandler.getPageName(must_id);
      paths = Algorithms.searchPaths(result, SQLHandler.getPageName(must_id));
      if (paths.size() == 0) {
        JOptionPane.showMessageDialog(null, "There is no path from " + start_name + " to " + end_name + " that contains " + must_name);
        return;
      }
    }

    for (PathT path : paths)
    {
      ArrayList<NodeT> nodes = new ArrayList<>();
      nodes.add(first);
      nodes.addAll(path.getPath());

      UIResults pt = new UIResults(nodes);
    }
  }
}

