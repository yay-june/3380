import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import com.jgoodies.common.collect.LinkedListModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;

public class ListCreator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblOccurences;
	private JLabel lblEvery;
	private JComboBox CalendarBox;
	private JList list;
	private JTable CalendarTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListCreator frame = new ListCreator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(58, 58, 118, 58);
		contentPane.add(lblNewLabel);
		
		String[] Options1= {"Daily","Weekly","Monthly","Yearly"};
		JComboBox<String> FrequencyBox = new JComboBox(Options1);
		FrequencyBox.setBounds(212, 167, 137, 41);
		contentPane.add(FrequencyBox);
		
		
	
		textField = new JTextField();
		textField.setBounds(212, 72, 192, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblOccurences = new JLabel("Frequency ");
		lblOccurences.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOccurences.setBounds(58, 155, 118, 58);
		contentPane.add(lblOccurences);
		
		lblEvery = new JLabel("Every ");
		lblEvery.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEvery.setBounds(58, 291, 118, 58);
		contentPane.add(lblEvery);
		
		
		
		/*CalendarBox =   new JComboBox();
		CalendarBox.setBounds(386, 167, 137, 41);
		contentPane.add(CalendarBox);
		*/
		JComboBox Days = new JComboBox();
		Days.setBounds(212, 312, 85, 22);
		contentPane.add(Days);
		for(int i = 1; i <= 99; i++) {
		    Days.addItem(i);
		}
		
		JButton btnNewButton = new JButton("Create List");
		btnNewButton.setBounds(58, 423, 118, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(552, 423, 106, 36);
		contentPane.add(btnNewButton_1);
		
		String[] Suffixes= {"Days","Weeks","Months","Years"};
		JComboBox Suffix = new JComboBox(Suffixes);
		Suffix.setBounds(386, 312, 137, 22);
		contentPane.add(Suffix);
		
		
		
		String[] Options2= {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		JList<String> list_1 = new JList(Options2);
    	list_1.setVisible(false);
        list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		list_1.setBounds(386, 167, 231, 122);
		contentPane.add(list_1);
		
		JLabel lblEvery_1 = new JLabel("Every ");
		lblEvery_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEvery_1.setBounds(446, 100, 118, 58);
		lblEvery_1.setVisible(false);
		contentPane.add(lblEvery_1);
		
		
		
        
        FrequencyBox.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e) {
        	if (FrequencyBox.getSelectedItem().equals("Weekly")) {
        		Suffix.setSelectedItem("Weeks");
            	list_1.setVisible(true);
            	Days.setVisible(false);
            	Suffix.setVisible(false);
            	lblEvery.setVisible(false);
        		lblEvery_1.setVisible(true);
        		/*lblEach.setVisible(false);
        		EachBox.setVisible(false);
 				*/

        		
        	
            } else if (FrequencyBox.getSelectedItem().equals("Monthly")) {
            	Suffix.setSelectedItem("Months");
            	Days.setSelectedItem(1);
            	list_1.setVisible(false);
            	Days.setVisible(true);
            	Suffix.setVisible(true);
            	lblEvery.setVisible(true);
        		lblEvery_1.setVisible(false);
        		//lblEach.setVisible(true);
        	//	EachBox.setVisible(true);



            }
            else if(FrequencyBox.getSelectedItem().equals("Daily")) {
            	Suffix.setSelectedItem("Days");
            	Days.setSelectedItem(1);
            	list_1.setVisible(false);
            	Days.setVisible(true);
            	Suffix.setVisible(true);
            	lblEvery.setVisible(true);
        		lblEvery_1.setVisible(false);
        	//	lblEach.setVisible(false);
        		//EachBox.setVisible(false);


            	
            }
            else if(FrequencyBox.getSelectedItem().equals("Yearly")) {
            	Suffix.setSelectedItem("Years");
            	Days.setSelectedItem(1);
            	list_1.setVisible(false);
            	Days.setVisible(true);
            	Suffix.setVisible(true);
            	lblEvery.setVisible(true);
            	lblEvery_1.setVisible(false);
        		//lblEach.setVisible(true);
        	//	lblEach.setVisible(false);
        	//	EachBox.setVisible(false);


            }
        }
    
			
			
		});
	}
}

