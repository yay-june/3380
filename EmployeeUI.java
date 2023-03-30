
import java.awt.BorderLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class EmployeeUI extends JFrame {

    private JPanel contentPane;
    private TaskListPanel taskListPanel;
    private CompletedTaskPanel completedTaskPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeUI frame = new EmployeeUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EmployeeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setTitle("Employee Task Tracker");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        taskListPanel = new TaskListPanel();
        contentPane.add(taskListPanel, BorderLayout.CENTER);

        completedTaskPanel = new CompletedTaskPanel();
        contentPane.add(completedTaskPanel, BorderLayout.EAST);
    }

    private class TaskListPanel extends JPanel {
        private JList<String> taskList;
        private DefaultListModel<String> taskListModel;
        private JButton completeButton;

        public TaskListPanel() {
            setLayout(new BorderLayout(0, 0));
            Border taskBorder = BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Pending Tasks");
            setBorder(taskBorder);

            JScrollPane taskScrollPane = new JScrollPane();

            taskListModel = new DefaultListModel<String>();
            taskListModel.addElement("Task 1");
            taskListModel.addElement("Task 2");
            taskListModel.addElement("Task 3");

            taskList = new JList<String>(taskListModel);
            taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            taskList.setFont(new Font("Tahoma", Font.PLAIN, 18));
            taskList.setVisibleRowCount(-1);
            taskScrollPane.setViewportView(taskList);
            add(taskScrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            completeButton = new JButton("Complete Task");
            completeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            completeButton.setForeground(Color.WHITE);
            completeButton.setBackground(new Color(0, 128, 0));
            completeButton.setPreferredSize(new Dimension(200, 50));
            completeButton.setFocusPainted(false);
            completeButton.setBorderPainted(false);
            completeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String completedTask = taskListModel.remove(selectedIndex);
                        completedTaskPanel.addCompletedTask(completedTask);
                    }
                }
            });
            buttonPanel.add(completeButton);
        }

        public JButton getCompleteButton() {
            return completeButton;
        }

        public DefaultListModel<String> getTaskListModel() {
            return taskListModel;
        }
    }
}
