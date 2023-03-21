package sixgolfballs;

public class Task
{
    private String taskName;
    private String taskDescription;
    private String completeByDate;
    private boolean isComplete;
    
    public Task(String taskName, String taskDescription, String completeByDate)
    {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.completeByDate = completeByDate;
        this.isComplete = false;
    }
    
    public void tasksetcompleteByDate(String completeByDate)
    {
        this.completeByDate = completeByDate;
    }
    
    public void taskSetName(String taskName)
    {
        this.taskName = taskName;
    }
    
    public void taskSetDescription(String taskDescription)
    {
        this.taskDescription = taskDescription;
    }
    
    public void taskSetComplete(boolean isComplete)
    {
        this.isComplete = isComplete;
    }
    
    public String toString()
    {
        return "Task: "+taskName+"\nDescription: "+taskDescription+"\nComplete by: "+completeByDate+"\nStatus: "+isComplete;
    }
}
