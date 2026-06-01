package model;

public class User {
	private int idNumber;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String password;
	private boolean isActive;
    private String accesLevel;

	public User(int idNumber, String name, String lastName, String phoneNumber, String emailAddress, String password, boolean isActive, String accesLevel) {
		this.idNumber = idNumber;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.isActive = isActive;
        this.accesLevel = accesLevel;
	}

	// Getter methods.
    public int getIdNumber() {
        return this.idNumber;
    }

    public String getName() {
    	return this.name;
    }

    public String getLastName() {
    	return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getEmailAddress() {
    	return this.emailAddress;
    }

    public String getPassword() {
    	return this.password;
    }

    public boolean getState() {
    	return this.isActive;
    }

    public String getAccesLevel() {
        return this.accesLevel;
    }

    // Setter methods.
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmailAddress(String emailAddress) {
    	this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public void setIsActive(boolean isActive) {
    	this.isActive = isActive;
    }

    public void setAccesLevel(String accesLevel) {
        this.accesLevel = accesLevel;
    }
}