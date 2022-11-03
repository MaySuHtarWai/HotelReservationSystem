package hotel.model;

public class Room {
	private int id;
	private String description;
	private int noOfPerson;
	private double price;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	public Room(String description, int noOfPerson, double price) {
		super();
		this.description = description;
		this.noOfPerson = noOfPerson;
		this.price = price;
	}

	public Room(int id, String description, int noOfPerson, double price) {
		super();
		this.id = id;
		this.description = description;
		this.noOfPerson = noOfPerson;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfPerson() {
		return noOfPerson;
	}

	public void setNoOfPerson(int noOfPerson) {
		this.noOfPerson = noOfPerson;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", description=" + description + ", noOfPerson=" + noOfPerson + ", price=" + price
				+ "]";
	}
	
	

}
