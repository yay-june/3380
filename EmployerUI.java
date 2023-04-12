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


public class EmployerUI extends JFrame 
{
	private JPanel contentPane;
	private JTextField txtPropertyOfSixgolfballs;
	
	private Checklist listToView;
    private JTextField taskNameField;
    private JTextField taskDescriptionField;
    private JButton addTaskButton;
    private JList<Task> taskList;
    private DefaultListModel<Task> taskListModel;
	private JTextField newListText;
	private HeaderPanel listHeader;

	
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
	
	private void refreshTaskModel() {
    	taskListModel = new DefaultListModel<Task>();
    	if (listToView != null) {
	    	for (Task t : listToView.getTasks() ) {
	    		taskListModel.addElement(t);
	    	}
    	}
    	taskList.setModel(taskListModel);
	}
	
	public EmployerUI(String userName) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 401);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(255, 255, 255));
		//contentPane.setBackground(new Color(0,0,0,125));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		CardLayout cl = (CardLayout)(contentPane.getLayout());
		
		// Employer Menu
		
		JPanel EmployerMenu = new JPanel();
		contentPane.add(EmployerMenu, "EmployerMenu");
		EmployerMenu.setLayout(null);
		
		HeaderPanel header = new HeaderPanel(EmployerMenu, this);
		header.setTitleText("Welcome, "+userName+ "!");
		header.showBackButton(false);
		
		// Premake ListViewer for header
		JPanel ListViewer = new JPanel();
		contentPane.add(ListViewer, "ListViewer");
		
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 0, 568, 120);
        ListViewer.add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(null);
		
		HeaderPanel listHeader = new HeaderPanel(inputPanel, this);
		listHeader.setTitleText("");
		listHeader.showBackButton(true);
		
		// Back to employer Menu
		
		JButton btnNewList = new JButton("New List");
		btnNewList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, "ListCreator");
			}
		});
		
		btnNewList.setBounds(460, 56, 88, 25);
		EmployerMenu.add(btnNewList);
		
		ListDisplayPanel ListPanel = new ListDisplayPanel(EmployerMenu);
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
		EmployerMenu.add(txtPropertyOfSixgolfballs);
		txtPropertyOfSixgolfballs.setColumns(10);
		
		JButton btnDeleteSelection = new JButton("Delete Selection");
		btnDeleteSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!CurrentLists.getSelectionModel().isSelectionEmpty()) {
					String nameOfList = ((Checklist)CurrentLists.getValueAt(CurrentLists.getSelectedRow(), CurrentLists.getSelectedColumn())).getName();
					File listToDelete = new File("checklists/" + nameOfList);
					listToDelete.delete();
					try {
						ListPanel.refreshLists();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeleteSelection.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteSelection.setBounds(329, 56, 121, 25);
		EmployerMenu.add(btnDeleteSelection);
		
		JButton btnDeleteSelection_1 = new JButton("Delete Selection");
		btnDeleteSelection_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!PastLists.getSelectionModel().isSelectionEmpty()) {
					String nameOfList = ((Checklist) PastLists.getValueAt(PastLists.getSelectedRow(), PastLists.getSelectedColumn())).getName();
					File listToDelete = new File("checklists/" + nameOfList);
					listToDelete.delete();
					try {
						ListPanel.refreshLists();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeleteSelection_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteSelection_1.setBounds(329, 192, 121, 25);
		EmployerMenu.add(btnDeleteSelection_1);
		
		JLabel imgLabel = new JLabel(new ImageIcon("background.jpg"));
		imgLabel.setBounds(0, 0, 569, 366);
		EmployerMenu.add(imgLabel);
		
		// List Creator
		/*
		JPanel ListCreator = new JPanel();
		contentPane.add(ListCreator, "ListCreator");
		ListCreator.setLayout(null);
		
		newListNameField = new JTextField();
		newListNameField.setBounds(76, 8, 145, 20);
		//newListNameField.setBorderPainted(false);
		ListCreator.add(newListNameField);
		newListNameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("List Name:");
		lblNewLabel.setBounds(10, 11, 84, 14);
		ListCreator.add(lblNewLabel);
		
		JButton btnCreateList = new JButton("Create List");
		btnCreateList.setBounds(119, 47, 102, 23);
		//btnCreateList.setBorderPainted(false);
		ListCreator.add(btnCreateList);
		btnCreateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newListName = newListNameField.getText();
				if (!newListName.isEmpty()) {
					cl.show(contentPane, "EmployerMenu");
					Checklist newList = new Checklist(newListName);
					newList.saveChecklistAs("checklists/" + newList.getName());
					
					try {
						ListPanel.refreshLists();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					newListNameField.setText("");
				}
			}
		});
		
		JButton btnCancelNewList = new JButton("Cancel");
		btnCancelNewList.setBounds(10, 47, 89, 23);
		//btnCancelNewList.setBorderPainted(false);
		ListCreator.add(btnCancelNewList);
		btnCancelNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newListNameField.setText("");
				cl.show(contentPane, "EmployerMenu");
			}
		});
		
		JLabel imgLabel2 = new JLabel(new ImageIcon("background.jpg"));
		imgLabel2.setBounds(0, 0, 569, 366);
		ListCreator.add(imgLabel2);
		*/
		
		JPanel ListCreator = new JPanel();
		contentPane.add(ListCreator, "ListCreator");
		ListCreator.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 118, 47);
		ListCreator.add(lblNewLabel);
		
		newListText = new JTextField();
		newListText.setBounds(138, 19, 308, 36);
		ListCreator.add(newListText);
		newListText.setColumns(10);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(182, 147, 205, 153);
		ListCreator.add(calendar);
        calendar.setVisible(true);
	
		/*JComboBox Numbers = new JComboBox();
		Numbers.setBounds(138, 84, 85, 22);
		ListCreator.add(Numbers);
		Numbers.addItem(" ");
		for(int i = 1; i <= 99; i++) {
		    Numbers.addItem(Integer.toString(i));
		}*/
		
		JCheckBox dueDateCheckbox = new JCheckBox("Use custom due date?") {
		    protected void paintComponent(Graphics g)
		    {
		        g.setColor( getBackground() );
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		dueDateCheckbox.setOpaque(false);
		dueDateCheckbox.setBackground(new Color(255, 255, 255, 0));
		dueDateCheckbox.setBounds(178, 93, 209, 23);
		ListCreator.add(dueDateCheckbox);
        
        JButton CreateButton = new JButton("Create List");
		CreateButton.setBounds(440, 317, 118, 36);
		ListCreator.add(CreateButton);
        CreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newListName = newListText.getText();
				if (!newListName.isEmpty()) {
					//String numDaysUntilRepeat = (String) Numbers.getSelectedItem();
					Checklist newList = new Checklist(newListName);
					
					if (dueDateCheckbox.isSelected()) {
						newList.setDueDate(calendar.getDate());
						/*if (numDaysUntilRepeat.equals(" ")) {
							newList.setRecurrence(-1);
						} else {
							newList.setRecurrence(Integer.parseInt(numDaysUntilRepeat));
						}*/
					}
					
					//System.out.println(newList.getDueDate());
					newList.saveChecklistAs("checklists/" + newList.getName());
					
					
					try {
						ListPanel.refreshLists();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					/*
					if(freqType.equals("Daily"))
					{
						//String freqType = (String)FrequencyBox.getSelectedItem();
						//int freqNumber = (int)Numbers.getSelectedItem();
						System.out.println("DAILY!");
						//newList.setFrequencyType("Daily");
						//newList.setDailyFrequency(freqNumber);
						//newList.setSuffix(Suffix.getSelectedItem());
								//"checklists/"+ newList.getName()+FrequencyBox.getSelectedItem()+Numbers.getSelectedItem()+Suffix.getSelectedItem());
					}
					*/
					newListText.setText("");
					cl.show(contentPane, "EmployerMenu");
				}
				
			}
		});
    	
        JButton CancelButton = new JButton("Cancel");
		CancelButton.setBounds(10, 317, 106, 36);
		ListCreator.add(CancelButton);
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newListText.setText("");
				cl.show(contentPane, "EmployerMenu");
			}
		});
		
		JLabel imgLabel2 = new JLabel(new ImageIcon("background.jpg"));
		imgLabel2.setBounds(0, 0, 569, 366);
		ListCreator.add(imgLabel2);
        	
		// List Viewer
	
		taskListModel = new DefaultListModel<Task>();
        ListViewer.setLayout(null);
        JScrollPane taskListScrollPane = new JScrollPane();
        taskListScrollPane.setBounds(0, 178, 568, 186);
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
        	            //JTextArea inputArea = new JTextArea(task.getTaskDescription());
        	            //JScrollPane scrollPane = new JScrollPane(inputArea);
        	            //scrollPane.setPreferredSize(new Dimension(400, 200));
        	            /*int result = JOptionPane.showConfirmDialog(ListViewer, scrollPane, "Enter new description:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        	            
        	            if (result == JOptionPane.OK_OPTION) {
        	                String newDescription = inputArea.getText();
        	                task.setTaskDescription(newDescription);
        	                refreshTaskModel();
        	            }*/
        	        }
        	    }
        	}
        });
       
        listHeader.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cl.show(contentPane, "EmployerMenu");
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
        btnNewButton.setBounds(458, 79, 100, 32);
        inputPanel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	listToView.saveChecklistAs("checklists/" + listToView.getName());
				cl.show(contentPane, "EmployerMenu");
				listToView = null;
				taskListModel = null;
				
				try {
					ListPanel.refreshLists();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
        
        // Create delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(142, 79, 100, 30);
        inputPanel.add(deleteButton);
        deleteButton.setPreferredSize(new Dimension(200, 50));
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index >= 0) {
                    listToView.removeTask(index);
                    refreshTaskModel();
                }
            }
        });
        deleteButton.setPreferredSize(new Dimension(100, 30));
        addTaskButton = new JButton("Add");
        addTaskButton.setBounds(10, 79, 100, 30);
        inputPanel.add(addTaskButton);
        addTaskButton.setPreferredSize(new Dimension(200, 50));
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        addTaskButton.setPreferredSize(new Dimension(100, 30));
        JLabel label = new JLabel("Task Name:");
        label.setBounds(10, 131, 151, 40);
        ListViewer.add(label);
        taskNameField = new JTextField();
        taskNameField.setBounds(94, 131, 164, 40);
        ListViewer.add(taskNameField);
        taskNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel label_1 = new JLabel("Task Description:");
        label_1.setBounds(268, 131, 151, 40);
        ListViewer.add(label_1);
        taskDescriptionField = new JTextField();
        taskDescriptionField.setBounds(394, 131, 164, 40);
        ListViewer.add(taskDescriptionField);
        taskDescriptionField.setFont(new Font("Arial", Font.PLAIN, 18));
	}
    
    private void addTask() {
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        if (!name.isBlank()) {
        	listToView.addTask(new Task(name, description));
        	refreshTaskModel();
        	
            taskNameField.setText("");
            taskDescriptionField.setText("");
        }
    }
}