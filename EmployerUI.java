import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JToggleButton;

public class EmployerUI extends JFrame 
{
	private JPanel contentPane;
	private JTable TodayLists;
	private JTable WeekLists;
	private JTextField txtWelcomeEmployer;
	private JTextField txtPropertyOfSixgolfballs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployerUI frame = new EmployerUI("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EmployerUI(String userName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		CardLayout cl = (CardLayout)(contentPane.getLayout());
		
		JPanel EmployerMenu = new JPanel();
		contentPane.add(EmployerMenu, "EmployerMenu");
		EmployerMenu.setLayout(null);
		
		TodayLists = new JTable();
		TodayLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TodayLists.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		TodayLists.setBounds(10, 125, 309, 54);
		TodayLists.setModel(new DefaultTableModel(
			new Object[][] {
				{"List 1"},
				{"List 2"},
				{"List 3"},
			},
			new String[] {
				"Task"
			}
		));
		EmployerMenu.add(TodayLists);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		btnEdit.setBounds(424, 70, 94, 25);
		EmployerMenu.add(btnEdit);
		
		txtWelcomeEmployer = new JTextField();
		txtWelcomeEmployer.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtWelcomeEmployer.setText("Welcome Employer!");
		txtWelcomeEmployer.setBounds(0, 0, 558, 44);
		EmployerMenu.add(txtWelcomeEmployer);
		txtWelcomeEmployer.setColumns(10);
		
		JLabel lblTaskLists = new JLabel("Task Lists");
		lblTaskLists.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTaskLists.setBounds(10, 71, 309, 19);
		EmployerMenu.add(lblTaskLists);
		
		JLabel lblTodayLists = new JLabel("Today");
		lblTodayLists.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTodayLists.setBounds(10, 100, 309, 15);
		EmployerMenu.add(lblTodayLists);
		
		JLabel lblWeekLists = new JLabel("This Week");
		lblWeekLists.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeekLists.setBounds(10, 206, 294, 24);
		EmployerMenu.add(lblWeekLists);
		
		WeekLists = new JTable();
		WeekLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		WeekLists.setModel(new DefaultTableModel(
			new Object[][] {
				{"List 1"},
				{"List 2"},
				{"List 3"},
			},
			new String[] {
				"New column"
			}
		));
		WeekLists.setBounds(10, 240, 309, 54);
		EmployerMenu.add(WeekLists);
		
		txtPropertyOfSixgolfballs = new JTextField();
		txtPropertyOfSixgolfballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPropertyOfSixgolfballs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPropertyOfSixgolfballs.setText("Property Of SixGolfBalls");
		txtPropertyOfSixgolfballs.setBounds(0, 320, 558, 34);
		EmployerMenu.add(txtPropertyOfSixgolfballs);
		txtPropertyOfSixgolfballs.setColumns(10);
	}
}

/*
		JPanel EmployerMenu = new JPanel();
		contentPane.add(EmployerMenu, "EmployerMenu");
		EmployerMenu.setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, "NextPanel");
			}
		});
		btnNext.setBounds(484, 11, 64, 25);
		EmployerMenu.add(btnNext);
		
		JPanel NextPanel = new JPanel();
		contentPane.add(NextPanel, "NextPanel");
		NextPanel.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, "EmployerMenu");
			}
		});
		btnBack.setBounds(424, 70, 94, 25);
		NextPanel.add(btnBack);
*/
