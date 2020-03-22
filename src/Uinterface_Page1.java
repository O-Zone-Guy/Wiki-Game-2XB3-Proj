import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JDialog;

public class Uinterface_Page1 {

	private JFrame frame;
	private JTextField t1;
	private JTextField t2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uinterface_Page1 window = new Uinterface_Page1();
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
	public Uinterface_Page1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		t1 = new JTextField();
		t1.setBounds(62, 84, 152, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(361, 84, 152, 20);
		frame.getContentPane().add(t2);
		
		JLabel l1 = new JLabel("Insert Page 1");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(62, 59, 152, 14);
		frame.getContentPane().add(l1);
		
		JLabel l2 = new JLabel("Insert Page 2");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(361, 59, 152, 14);
		frame.getContentPane().add(l2);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int page1_address = Integer.parseInt(t1.getText());
				int page2_address = Integer.parseInt(t2.getText());
				
			}
		});
		btnNewButton.setBounds(242, 138, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(224, 230, 127, 20);
		frame.getContentPane().add(lblNewLabel);
	}
}
