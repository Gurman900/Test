package Com.cetpa;

import java.io.InputStream;

public class UploadMainFile 
{
	private String name, email;
	private InputStream input;
	private int password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public InputStream getInput() {
		return input;
	}
	public void setInput(InputStream input) {
		this.input = input;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	
	public UploadMainFile() 
	{
		
	}
	public UploadMainFile(String name, String email, InputStream input, int password) {
		
		this.name = name;
		this.email = email;
		this.input = input;
		this.password = password;
	}
	
	

}
