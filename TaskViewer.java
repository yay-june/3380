import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TaskViewer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskViewer frame = new TaskViewer();
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
	public TaskViewer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(26, 124, 154, 70);
	//	textArea.setPreferredSize(new Dimension(300, 300));
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("View Description ");
		lblNewLabel.setBounds(26, 50, 133, 79);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Task Viewer");
		lblNewLabel_1.setBounds(232, 11, 220, 104);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblNewLabel_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(437, 124, 161, 70);
		contentPane.add(editorPane);
		
		JLabel lblNewLabel_2 = new JLabel("Add Notes");
		lblNewLabel_2.setBounds(437, 82, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Creation Date:");
		lblNewLabel_3.setBounds(147, 263, 121, 26);
		contentPane.add(lblNewLabel_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(247, 264, 136, 22);
		contentPane.add(textArea_1);
		
		JLabel lblNewLabel_4 = new JLabel("Completion Status:");
		lblNewLabel_4.setBounds(26, 368, 110, 14);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 411, 96, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Save changes/Submit");
		btnNewButton.setBounds(424, 411, 154, 23);
		contentPane.add(btnNewButton);
	}
}
