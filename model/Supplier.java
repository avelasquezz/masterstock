package model;

public class Supplier {
	private int id;
	private String name;
    private String address;
    private String phoneNumber;

	public Supplier(int id, String name, String address, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
        this.address = address;
	}

	// Getter methods.
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

    public String getAddress() {
        return this.address;
    }

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	// Setter methods.
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setAddress(String address) {
        this.address = address;
    }

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}