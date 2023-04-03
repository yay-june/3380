import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListViewer extends JFrame {

    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField descriptionField;
    private JButton addButton;
    private JList<Task> taskList;
    private DefaultListModel<Task> taskListModel;

    public ListViewer() {
        super("Checklist App - Employer View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create task list model and list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = taskList.getSelectedIndex();
                    if (index >= 0) {
                        Task task = taskListModel.get(index);
                        String newDescription = JOptionPane.showInputDialog(mainPanel, "Enter new description:", task.getTaskDescription());
                        
                        if (newDescription != null) {
                            task.setTaskDescription(newDescription);
                            taskListModel.setElementAt(task, index);
                        }
                    }
                }
            }
        });
        JScrollPane taskListScrollPane = new JScrollPane(taskList);
        mainPanel.add(taskListScrollPane, BorderLayout.CENTER);

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));

        // Create name field
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1, 2));
        namePanel.add(new JLabel("Task Name:"));
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        namePanel.add(nameField);
        inputPanel.add(namePanel);

        // Create description field
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2));
        descriptionPanel.add(new JLabel("Task Description:"));
        descriptionField = new JTextField();
        descriptionField.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionPanel.add(descriptionField);
        inputPanel.add(descriptionPanel);

        // Create add button
        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(200, 50));
        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        addButtonPanel.add(addButton);
        inputPanel.add(addButtonPanel);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);
        setVisible(true);

        // Create delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(200, 50));
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index >= 0) {
                    taskListModel.removeElementAt(index);
                }
            }
        });

        // Create panel for delete button
        JPanel deleteButtonPanel = new JPanel();
        deleteButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        deleteButtonPanel.add(deleteButton);

        // Add delete button panel to input panel
        inputPanel.add(deleteButtonPanel);
        
        addButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setPreferredSize(new Dimension(100, 30));
	    mainPanel.add(inputPanel, BorderLayout.NORTH);
	
	    setContentPane(mainPanel);
	    setVisible(true);
	}

    private void addTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        if (!name.isBlank() && !description.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy, hh:mm a"); // Modified line
            String timeStamp = LocalDateTime.now().format(formatter);
            Task task = new Task(name, description);
            taskListModel.addElement(task);
            nameField.setText("");
            descriptionField.setText("");
        }
    }

    public static void main(String[] args) {
        ListViewer frame = new ListViewer();
        frame.setVisible(true);
    }

}
