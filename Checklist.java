import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Checklist implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String listName;
    private boolean isComplete;
    private ArrayList<Task> tasks;
    
    public Checklist(String listName)
    {
        this.listName = listName;
        tasks = new ArrayList<Task>();
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
    
    public String getTasksAsString()
    {
    	String ret = "";
    	for(Task t: tasks) {
    		ret += t + "\n";
    	}
    	if (ret.length() > 2) {
    		ret = ret.substring(0, ret.length() - 2);
    	}
    	return ret;
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
}