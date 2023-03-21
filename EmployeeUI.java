package sixgolfballs;

public class EmployeeUI {
    public String employeeID;
            
            
    public EmployeeUI()
    {
        
    }
    
    public void displayMyLists()
    {
        
    }
    
    public void setTaskAsComplete(taskName, employeeID)
    {
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
