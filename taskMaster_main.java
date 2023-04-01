import java.awt.EventQueue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class taskMaster_main {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taskMaster_main window = new taskMaster_main();
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
	public taskMaster_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Label for Title
		
		JLabel title = new JLabel("Task Master");
		title.setFont(new Font("Bradley Hand", Font.PLAIN, 30));
		title.setBounds(140, 6, 170, 38);
		frame.getContentPane().add(title);
		
		// Label for selecting user type
		// i.e. Employer or Employee
		
		JLabel lblNewLabel = new JLabel("Select user:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 153, 93, 16);
		frame.getContentPane().add(lblNewLabel);

		JComboBox viewDecision_dropDown = new JComboBox();
		viewDecision_dropDown.setToolTipText("");
		viewDecision_dropDown.setModel(new DefaultComboBoxModel(new String[] {"", "Employer", "Employee"}));
		viewDecision_dropDown.setBounds(113, 149, 115, 27);
		frame.getContentPane().add(viewDecision_dropDown);

		// Label for entering user name
		
		JLabel lblNewLabel_2 = new JLabel("Enter Name:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(26, 105, 93, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(114, 100, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// Confirm button
		// Saves user name & user type
		
		final JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String actionCommand = btnNewButton.getActionCommand();
			    
			    // Get the text inputted by the user from the text field
			    String nameInput = textField.getText();
			    
			    // Get the selected item from the drop-down menu
			    String userType = (String) viewDecision_dropDown.getSelectedItem();
			    
			    // Create an object to store the text and selected item
			    userProfile userProfile = new userProfile(nameInput, userType);
			    
			    // Do something with the object, like pass it to another method or save it to a database
			    // For example: myDatabase.save(myObject);
			    
			}
		});
		btnNewButton.setMnemonic(KeyEvent.VK_ENTER);
		btnNewButton.setMultiClickThreshhold(1L);
		btnNewButton.setBounds(140, 208, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		
	}
	public class ProfileSaver {
        private static final String FILENAME = "objects.txt";

        public static void saveObject(userProfile userProfile) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
                writer.println(nameInput.getText() + "," + userType.getSelection());
                System.out.println("Object saved to file.");
            } catch (IOException e) {
                System.err.println("Error saving object to file: " + e.getMessage());
            }
        }
    }
}
