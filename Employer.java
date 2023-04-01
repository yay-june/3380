package sixgolfballs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class Employer extends mainframe{

    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField descriptionField;
    private JButton addButton;
    private JList<Task> taskList;
    private DefaultListModel<Task> taskListModel;

    public mainframe() {
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
                        String newDescription = JOptionPane.showInputDialog(mainPanel, "Enter new description:", task.getDescription());
                        if (newDescription != null) {
                            task.setDescription(newDescription);
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
        addButton = new JButton("Add Task");
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
    }

    private void addTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        if (!name.isBlank() && !description.isBlank()) {
            Task task = new Task(name, description);
            taskListModel.addElement(task);
            nameField.setText("");
            descriptionField.setText("");
        }
    }
    public void recordCompletionTime(Task task, LocalDateTime dateTime) {
        task.setCompletionTime(dateTime);
    }


    public static void main(String[] args) {
        mainframe frame = new mainframe();
        frame.setVisible(true);
    }

}
