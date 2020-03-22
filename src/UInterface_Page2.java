import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UInterface_Page2 {

	private JFrame frame;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UInterface_Page2 window = new UInterface_Page2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UInterface_Page2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		t1 = new JTextField();
		t1.setBounds(10, 71, 147, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insert Page 1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 46, 147, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea ta1 = new JTextArea();
		ta1.setBounds(167, 69, 237, 163);
		frame.getContentPane().add(ta1);
		
		JComboBox c1 = new JComboBox();
		c1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: Add all items from the database when focused on
			}
		});
		c1.setBounds(414, 70, 147, 22);
		frame.getContentPane().add(c1);
		
		JLabel lblNewLabel_1 = new JLabel("//TODO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(167, 46, 237, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Select Page 2");
		lblNewLabel_2.setBounds(414, 46, 147, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int page1 = Integer.parseInt(t1.getText());
				int page2 = (Integer) c1.getSelectedItem();
			}
		});
		btnNewButton.setBounds(167, 243, 237, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
