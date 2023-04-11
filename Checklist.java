import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Checklist implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String listName;
    private boolean isComplete;
    private ArrayList<Task> tasks;
    private int daysUntilReccurence;
    private Date dueDate;
    //private 
    //private String frequencyType;
    //private int dailyFrequency; 
   /* private String WeeklyFrequency; 
    private String MonthlyFrequency; 
    private String YearlyFrequency;
    private Object suffixes;
    private String SpecificDate;   //probably not a string */
    
    public void setRecurrence(int days)
    {
    	daysUntilReccurence = days;
    }
    
    public boolean isRecurring()
    {
    	if (daysUntilReccurence == -1) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public void setDueDate(Date d) {
    	dueDate = d;
    }
    
    public Date getDueDate() {
    	return dueDate;
    }
    
    public void updateDueDate() {
    	// if the due date was yesterday, and this.isRecurring(), set due date = previous date + daysUntilRecurrence
    }
    
    public Checklist(String listName)
    {
        this.listName = listName;
        tasks = new ArrayList<Task>();
        daysUntilReccurence = -1;
        
    }
    
    Checklist(String fileName, String nothing) {
		try {
			FileInputStream fi = new FileInputStream(new File(fileName));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Checklist list = (Checklist) oi.readObject();
			this.listName = list.listName;
			this.tasks = list.tasks;
			
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
	public String saveChecklistAs(String fileName)
	{
		try {
			FileOutputStream f = new FileOutputStream(new File(fileName));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(this);
			
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		return "";
	}
    
    public void addTask(Task newTask)
    {
        tasks.add(newTask);  
    }
    
    public ArrayList<Task> getTasks() 
    {
    	return tasks;
    }
    
    public void removeTask(int index)
    {
        tasks.remove(index);
    }
    
    public String getName() {
    	return listName;
    }
    
    public String toString() {
    	return listName;
    }
    
    public boolean isListComplete() {
    	return isComplete;
    }
    
    public void setListComplete(boolean val) {
    	isComplete = val;
    }
    
    public boolean checkForCompleteness() {
    	boolean c = true;
    	if (tasks.isEmpty()) {
    		c = false;
    	} else {
	    	for (Task t : tasks) {
	    		if (!t.getTaskIsComplete()) {
	    			c = false;
	    		}
	    	}
    	}
    	//System.out.println(c);
    	isComplete = c;
    	return c;
    }
}