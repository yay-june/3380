package sixgolfballs;

public class EmployeeUI {
    public String userID;
            
            
    public EmployeeUI()
    {
        
    }
    
    public void displayMyLists()
    {
        
    }
    
    public void setTaskAsComplete(taskName, userID)
    {
        String completedBy = userID.name;
        if (taskName.isComplete == false)
        {
            taskName.isComplete = true;
        }
        else
        {
            System.out.println("Task is already complete.");

        }
    }
}
