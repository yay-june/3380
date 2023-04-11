import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String taskName;
    private String taskDescription;
    private LocalDateTime taskCreationDate;
    private LocalDateTime taskCompletionDate;
    private boolean taskIsComplete;
    
    public Task(String name, String desc)
    {
        this.taskName = name;
        this.taskDescription = desc;
        this.taskCreationDate = LocalDateTime.now();
        this.taskIsComplete = false;
    }
    
    public void setTaskName(String name)
    {
        this.taskName = name;
    }
    
    public String getTaskName() 
    {
    	return taskName;
    }
    
    public void setTaskDescription(String desc)
    {
        this.taskDescription = desc;
    }
    
    public String getTaskDescription() 
    {
    	return taskDescription;
    }
    
    public void setTaskIsComplete(boolean isComplete)
    {
        this.taskIsComplete = isComplete;
        if (taskIsComplete)
        {
        	taskCompletionDate = LocalDateTime.now();
        }
    }
    
    public boolean getTaskIsComplete()
    {
        return taskIsComplete;
    }
    
    public String toString()
    {
    	String msg = "";
    	if (taskIsComplete) {
    		msg = "Complete";
    	} else {
    		msg = "Incomplete";
    	}
    	if (taskDescription.isBlank()) {
    		return String.format("[%s] %s (%s)", msg, taskName, taskCreationDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' hh:mm a")));
    	} else {
        	return String.format("[%s] %s - %s (%s)", msg, taskName, taskDescription, taskCreationDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' hh:mm a")));

    	}
    }
}