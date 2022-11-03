package hotel.model;

import java.util.Date;

public class Reservation {

	private int id;
	private int room_id;
	private Date checkInDate;
	private Date checkOutDate;
	private String name;
	private String email;
	private String phone;
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(int room_id, Date checkInDate, Date checkOutDate, String name, String email, String phone) {
		super();
		this.room_id = room_id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Reservation(int id, int room_id, Date checkInDate, Date checkOutDate, String name, String email,
			String phone) {
		super();
		this.id = id;
		this.room_id = room_id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", room_id=" + room_id + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}

	
	
}
