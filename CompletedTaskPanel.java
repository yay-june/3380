
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CompletedTaskPanel extends JPanel {
    private JList<String> completedTaskList;
    private DefaultListModel<String> completedTaskListModel;

    public CompletedTaskPanel() {
        setLayout(new BorderLayout(0, 0));
        Border taskBorder = BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Completed Tasks");
        setBorder(taskBorder);

        JScrollPane taskScrollPane = new JScrollPane();

        completedTaskListModel = new DefaultListModel<String>();
        completedTaskList = new JList<String>(completedTaskListModel);
        completedTaskList.setFont(new Font("Tahoma", Font.PLAIN, 18));
        completedTaskList.setVisibleRowCount(-1);
        taskScrollPane.setViewportView(completedTaskList);
        add(taskScrollPane, BorderLayout.CENTER);
    }

    public void addCompletedTask(String task) {
        completedTaskListModel.addElement(task);
    }
}