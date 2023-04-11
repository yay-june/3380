
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class EmployeeUI extends JFrame {
    private JPanel contentPane;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeUI frame = new EmployeeUI("");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EmployeeUI(String userName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		CardLayout cl = (CardLayout)(contentPane.getLayout());
		
		JPanel EmployeeMenu = new JPanel();
		contentPane.add(EmployeeMenu, "EmployeeMenu");
		EmployeeMenu.setLayout(null);
		
		ListDisplayPanel ListPanel = new ListDisplayPanel(EmployeeMenu);
		JTable CurrentLists = ListPanel.getCurrentListsTable();
		ListPanel.getCurrentListsTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//cl.show(contentPane, "ListViewer");
					//listToView = (Checklist) CurrentLists.getValueAt(CurrentLists.getSelectedRow(), CurrentLists.getSelectedColumn());
					//listToView.addTask(new Task("Eat your veggies", ""));
					//listToView.addTask(new Task("Go to the store", "buy more veggies"));
					//refreshTaskModel();
				}
			}
		});
    }
}