import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class EmployerUI extends JFrame 
{
	private JPanel contentPane;
	private JTable TodayLists;
	private JTable WeekLists;
	private JTextField txtWelcomeEmployer;
	private JTextField txtPropertyOfSixgolfballs;
	private JTextField newListNameField;
	private ArrayList<Checklist> checklists;
	
	private Checklist listToView;
    private JTextField taskNameField;
    private JTextField taskDescriptionField;
    private JButton addTaskButton;
    private JList<Task> taskList;
    private DefaultListModel<Task> taskListModel;
	
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
	
	private ArrayList<String> getFiles(String directory) {
		ArrayList<String> textFiles = new ArrayList<String>();
		
		File dir = new File(directory);
		if (!dir.exists()){
			dir.mkdirs();
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			textFiles.add(file.getName());
		}
		return textFiles;
	}
	
	private void getChecklists() throws IOException {
		checklists = new ArrayList<Checklist>();
		ArrayList<String> files = getFiles("checklists/");
		for (String fileName : files) {
			//System.out.println(fileName);
			Checklist list =  new Checklist("checklists/" + fileName, "");
			checklists.add(list);
		}
	}
	
	private void refreshLists() throws IOException {
		getChecklists();
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Today's Lists"});
		if (!checklists.isEmpty()) {
			for (Checklist list : checklists) {
				model.addRow(new Checklist[] {list});
			}
		}
		TodayLists.setModel(model);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		CardLayout cl = (CardLayout)(contentPane.getLayout());
		
		// Employer Menu
		
		JPanel EmployerMenu = new JPanel();
		contentPane.add(EmployerMenu, "EmployerMenu");
		EmployerMenu.setLayout(null);
		
		TodayLists = new JTable();
		TodayLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TodayLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TodayLists.setDefaultEditor(Object.class, null);
		TodayLists.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					cl.show(contentPane, "ListViewer");
					listToView = (Checklist) TodayLists.getValueAt(TodayLists.getSelectedRow(), TodayLists.getSelectedColumn());
					//listToView.addTask(new Task("Eat your veggies", ""));
					//listToView.addTask(new Task("Go to the store", "buy more veggies"));
					refreshTaskModel();
				}
			}
		});
		
		TodayLists.setBounds(10, 125, 309, 54);
		EmployerMenu.add(TodayLists);
		
		try {
			refreshLists();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scroll_table = new JScrollPane(TodayLists);
	    scroll_table.setBounds(10, 55, 309, 118);
	    scroll_table.setVisible(true);
	    EmployerMenu.add(scroll_table);
		
		JButton btnNewList = new JButton("New List");
		btnNewList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, "ListCreator");
			}
		});
		btnNewList.setBounds(460, 56, 88, 25);
		EmployerMenu.add(btnNewList);
		
		txtWelcomeEmployer = new JTextField();
		txtWelcomeEmployer.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtWelcomeEmployer.setText("Welcome " + userName + "!");
		txtWelcomeEmployer.setBounds(0, 0, 558, 44);
		EmployerMenu.add(txtWelcomeEmployer);
		txtWelcomeEmployer.setColumns(10);
		
		WeekLists = new JTable();
		WeekLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		WeekLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		WeekLists.setDefaultEditor(Object.class, null);
		WeekLists.setModel(new DefaultTableModel(
			new Object[][] {
				{"List 1"}
			},
			new String[] {
				"This Week's Lists"
			}
		));
		WeekLists.setBounds(10, 240, 309, 54);
		EmployerMenu.add(WeekLists);
		
		JScrollPane scroll_table2 = new JScrollPane(WeekLists);
		scroll_table2.setBounds(10, 191, 309, 118);
		scroll_table2.setVisible(true);
	    EmployerMenu.add(scroll_table2);
		
		txtPropertyOfSixgolfballs = new JTextField();
		txtPropertyOfSixgolfballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPropertyOfSixgolfballs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPropertyOfSixgolfballs.setText("Property Of SixGolfBalls");
		txtPropertyOfSixgolfballs.setBounds(0, 320, 558, 34);
		EmployerMenu.add(txtPropertyOfSixgolfballs);
		txtPropertyOfSixgolfballs.setColumns(10);
		
		JButton btnDeleteSelection = new JButton("Delete Selection");
		btnDeleteSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameOfList = TodayLists.getValueAt(TodayLists.getSelectedRow(), TodayLists.getSelectedColumn()).toString();
				File listToDelete = new File("checklists/" + nameOfList);
				listToDelete.delete();
				try {
					refreshLists();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteSelection.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteSelection.setBounds(329, 56, 121, 25);
		EmployerMenu.add(btnDeleteSelection);
		
		JButton btnDeleteSelection_1 = new JButton("Delete Selection");
		btnDeleteSelection_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDeleteSelection_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteSelection_1.setBounds(329, 192, 121, 25);
		EmployerMenu.add(btnDeleteSelection_1);
		
		// List Creator
		
		JPanel ListCreator = new JPanel();
		contentPane.add(ListCreator, "ListCreator");
		ListCreator.setLayout(null);
		
		newListNameField = new JTextField();
		newListNameField.setBounds(76, 8, 145, 20);
		ListCreator.add(newListNameField);
		newListNameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("List Name:");
		lblNewLabel.setBounds(10, 11, 84, 14);
		ListCreator.add(lblNewLabel);
		
		JButton btnCreateList = new JButton("Create List");
		btnCreateList.setBounds(119, 47, 102, 23);
		ListCreator.add(btnCreateList);
		btnCreateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newListName = newListNameField.getText();
				if (!newListName.isEmpty()) {
					cl.show(contentPane, "EmployerMenu");
					Checklist newList = new Checklist(newListName);
					newList.saveChecklistAs("checklists/" + newList.getName());
					
					try {
						refreshLists();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					newListNameField.setText("");
				}
			}
		});
		
		JButton btnCancelNewList = new JButton("Cancel");
		btnCancelNewList.setBounds(10, 47, 89, 23);
		ListCreator.add(btnCancelNewList);
		btnCancelNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newListNameField.setText("");
				cl.show(contentPane, "EmployerMenu");
			}
		});
		
		// List Viewer
		
		JPanel ListViewer = new JPanel();
		contentPane.add(ListViewer, "ListViewer");
		ListViewer.setLayout(new BorderLayout());
	
		taskListModel = new DefaultListModel<Task>();
		//taskListModel.addElement(new Task("debug", "debug"));
        taskList = new JList<>(taskListModel);
        taskList.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        	    if (e.getClickCount() == 2) {
        	        int index = taskList.getSelectedIndex();
        	        if (index >= 0) {
        	            Task task = taskListModel.get(index);
        	            JTextArea inputArea = new JTextArea(task.getTaskDescription());
        	            JScrollPane scrollPane = new JScrollPane(inputArea);
        	            scrollPane.setPreferredSize(new Dimension(400, 200));
        	            int result = JOptionPane.showConfirmDialog(ListViewer, scrollPane, "Enter new description:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        	            
        	            if (result == JOptionPane.OK_OPTION) {
        	                String newDescription = inputArea.getText();
        	                task.setTaskDescription(newDescription);
        	                refreshTaskModel();
        	            }
        	        }
        	    }
        	}
        });
        JScrollPane taskListScrollPane = new JScrollPane(taskList);
        ListViewer.add(taskListScrollPane, BorderLayout.CENTER);

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));

        // Create name field
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1, 2));
        
        JButton btnNewButton = new JButton("Save List");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	listToView.saveChecklistAs("checklists/" + listToView.getName());
				try {
					refreshLists();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				cl.show(contentPane, "EmployerMenu");
				listToView = null;
				taskListModel = null;
            }
        });
        namePanel.add(btnNewButton);
        namePanel.add(new JLabel("Task Name:"));
        taskNameField = new JTextField();
        taskNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        namePanel.add(taskNameField);
        inputPanel.add(namePanel);

        // Create description field
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2));
        descriptionPanel.add(new JLabel("Task Description:"));
        taskDescriptionField = new JTextField();
        taskDescriptionField.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionPanel.add(taskDescriptionField);
        inputPanel.add(descriptionPanel);

        // Create add button
        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addTaskButton = new JButton("Add");
        addTaskButton.setPreferredSize(new Dimension(200, 50));
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        addButtonPanel.add(addTaskButton);
        inputPanel.add(addButtonPanel);

        ListViewer.add(inputPanel, BorderLayout.NORTH);

        // Create delete button
        JButton deleteButton = new JButton("Delete");
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

        // Create panel for delete button
        JPanel deleteButtonPanel = new JPanel();
        deleteButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        deleteButtonPanel.add(deleteButton);

        // Add delete button panel to input panel
        inputPanel.add(deleteButtonPanel);
        
        addTaskButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setPreferredSize(new Dimension(100, 30));
        ListViewer.add(inputPanel, BorderLayout.NORTH);
	}
    
    private void addTask() {
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        if (!name.isBlank() && !description.isBlank()) {
        	listToView.addTask(new Task(name, description));
        	refreshTaskModel();
        	
            taskNameField.setText("");
            taskDescriptionField.setText("");
        }
    }
}