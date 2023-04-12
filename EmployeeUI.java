import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.Color;

import com.jgoodies.common.collect.LinkedListModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JCheckBox;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import java.text.NumberFormat;
//import java.text.NumberFormatter;


public class EmployeeUI extends JFrame 
{
	private JPanel contentPane;
	private Checklist listToView;
    private JList<Task> taskList;
    private DefaultListModel<Task> taskListModel;
    private JTextField txtPropertyOfSixgolfballs;
	
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
	
	private void refreshTaskModel() {
    	taskListModel = new DefaultListModel<Task>();
    	if (listToView != null) {
	    	for (Task t : listToView.getTasks() ) {
	    		taskListModel.addElement(t);
	    	}
    	}
    	taskList.setModel(taskListModel);
	}
	
	public EmployeeUI(String userName) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		CardLayout cl = (CardLayout)(contentPane.getLayout());
		
		// Employee Menu
		
		JPanel EmployeeMenu = new JPanel();
		contentPane.add(EmployeeMenu, "EmployeeMenu");
		EmployeeMenu.setLayout(null);
		
		HeaderPanel header = new HeaderPanel(EmployeeMenu, this);
		header.setTitleText("Welcome, "+userName+ "!");
		header.showBackButton(false);
		
		JPanel ListViewer = new JPanel();
		contentPane.add(ListViewer, "ListViewer");
		
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 0, 568, 96);
        ListViewer.add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(null);
		
		HeaderPanel listHeader = new HeaderPanel(inputPanel, this);
		listHeader.setTitleText("");
		listHeader.showBackButton(true);
		
		ListDisplayPanel ListPanel = new ListDisplayPanel(EmployeeMenu);
		JTable CurrentLists = ListPanel.getCurrentListsTable();
		CurrentLists.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 & !CurrentLists.getSelectionModel().isSelectionEmpty()) {
					cl.show(contentPane, "ListViewer");
					listToView = (Checklist) CurrentLists.getValueAt(CurrentLists.getSelectedRow(), CurrentLists.getSelectedColumn());
					listHeader.setTitleText("Viewing list " + listToView.getName());
					refreshTaskModel();
				}
			}
		});
		
		JTable PastLists = ListPanel.getPastListsTable();
		PastLists.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 & !PastLists.getSelectionModel().isSelectionEmpty()) {
					cl.show(contentPane, "ListViewer");
					listToView = (Checklist) PastLists.getValueAt(PastLists.getSelectedRow(), PastLists.getSelectedColumn());
					listHeader.setTitleText("Viewing list " + listToView.getName());
					refreshTaskModel();
				}
			}
		});
		
		ListPanel.getListPanel().setBounds(0, 43, 560, 276);
		ListPanel.getTopScrollPane().setBounds(10, 11, 560, 130);
		//ListPanel.getTopScrollPane().setViewportView(CurrentLists);
		ListPanel.getBottomScrollPane().setBounds(10, 152, 550, 113);
		
		txtPropertyOfSixgolfballs = new JTextField() {
		    protected void paintComponent(Graphics g)
		    {
		        g.setColor( getBackground() );
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		txtPropertyOfSixgolfballs.setOpaque(false);
		txtPropertyOfSixgolfballs.setBackground(new Color(255, 255, 255, 50));
		txtPropertyOfSixgolfballs.setEditable(false);
		txtPropertyOfSixgolfballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPropertyOfSixgolfballs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPropertyOfSixgolfballs.setText("Property Of SixGolfBalls");
		txtPropertyOfSixgolfballs.setBounds(10, 320, 550, 34);
		EmployeeMenu.add(txtPropertyOfSixgolfballs);
		txtPropertyOfSixgolfballs.setColumns(10);
		
		JLabel imgLabel = new JLabel(new ImageIcon("background.jpg"));
		imgLabel.setBounds(0, 0, 569, 366);
		EmployeeMenu.add(imgLabel);
        	
		// List Viewer
	
		taskListModel = new DefaultListModel<Task>();
        ListViewer.setLayout(null);
        JScrollPane taskListScrollPane = new JScrollPane();
        taskListScrollPane.setBounds(0, 95, 568, 269);
        ListViewer.add(taskListScrollPane);
        //taskListModel.addElement(new Task("debug", "debug"));
        taskList = new JList<>(taskListModel);
        taskListScrollPane.setViewportView(taskList);
        taskList.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        	    if (e.getClickCount() == 2) {
        	        int index = taskList.getSelectedIndex();
        	        if (index >= 0) {
        	            Task task = taskListModel.get(index);
        	            task.setTaskIsComplete(!task.getTaskIsComplete());
        	            refreshTaskModel();
        	        }
        	    }
        	}
        });
       
        listHeader.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cl.show(contentPane, "EmployeeMenu");
				listToView = null;
				taskListModel = null;
				
				try {
					ListPanel.refreshLists();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
        
        JButton btnNewButton = new JButton("Save List");
        btnNewButton.setBounds(10, 55, 548, 39);
        inputPanel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	listToView.saveChecklistAs("checklists/" + listToView.getName());
				cl.show(contentPane, "EmployeeMenu");
				listToView = null;
				taskListModel = null;
				
				try {
					ListPanel.refreshLists();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
	}
}
/*
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
}*/