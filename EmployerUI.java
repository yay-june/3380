package sixgolfballs;

public class EmployerUI 
{
    public EmployerUI()
    {
        
    }
    
    public Checklist createChecklist(String listName)
    {
        return new Checklist(listName);
    }
}
