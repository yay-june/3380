import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Application title text "Task Master"
		JLabel lblApplicationTitle = new JLabel("Task Master");
		lblApplicationTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblApplicationTitle.setBounds(125, 0, 194, 53);
		contentPane.add(lblApplicationTitle);
		
		// Label for name text field
		JLabel lblName = new JLabel("Enter Name:");
		lblName.setBounds(26, 100, 86, 34);
		contentPane.add(lblName);
		
		// Text field for name
		textField = new JTextField();
		textField.setBounds(125, 107, 175, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Label for user type selection
		JLabel lblUserType = new JLabel("Select user type:");
		lblUserType.setBounds(26, 141, 86, 14);
		contentPane.add(lblUserType);
		
		// User type selection box
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Employee", "Employer"}));
		comboBox.setBounds(125, 137, 175, 22);
		contentPane.add(comboBox);
		
		// Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String userName = textField.getText();
				String userType = (String)comboBox.getSelectedItem();
				
				if (!userName.isEmpty() & !userType.isEmpty()) {
					System.out.println("Hello, " + userName + ", you have selected: " + userType);
					//UserProfile user = new UserProfile(name, userType);
					//user.saveUserProfileAs(user.getUserName());
					
					if (userType.equals("Employee")) {
						EmployeeUI newWindow = new EmployeeUI(userName);
						newWindow.setVisible(true);
						setVisible(false);
						
						//EmployeeUI newEmployeeUI = new EmployeeUI("Hello");
						//newEmployeeUI.setVisible(true);
					} else if (userType.equals("Employer")) {
						EmployerUI newWindow = new EmployerUI(userName);
						newWindow.setVisible(true);
						setVisible(false);
						
						//EmployerUI newEmployerUI = new EmployerUI("Yellow");
						//newEmployerUI.setVisible(true);
					}
				}
			}
		});
		btnLogin.setBounds(125, 170, 175, 23);
		contentPane.add(btnLogin);
	}
}
