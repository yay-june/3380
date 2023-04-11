import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userType;
	//private 
	
	UserProfile(String name, String type) {
		this.userName = name;
		this.userType = type;	
	}
	
	UserProfile(String fileName) {
		try {
			FileInputStream fi = new FileInputStream(new File(fileName));
			ObjectInputStream oi = new ObjectInputStream(fi);

			UserProfile profile = (UserProfile) oi.readObject();
			this.userName = profile.userName;
			this.userType = profile.userType;

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
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getUserType()
	{
		return userType;
	}
	
	public String toString() 
	{
		return "Name: " + userName + "\nType: " + userType;
	}
	
	public String saveUserProfileAs(String fileName)
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
}