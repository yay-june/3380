import java.util.ArrayList;

public class Checklist
{
    private String listName;
    private ArrayList<Task> tasks;
    
    public Checklist(String listName)
    {
        this.listName = listName;
        tasks = new ArrayList<Task>();
    }
    
    public String displayList()
    {
    	// Format the arraylist of tasks into a string that can be used by the JList model in UI
    	return "";
    }
    
    public void addTask(Task newTask)
    {
        tasks.add(newTask);  
    }
    
    public void removeTask(int index)
    {
        tasks.remove(index);
    }
}