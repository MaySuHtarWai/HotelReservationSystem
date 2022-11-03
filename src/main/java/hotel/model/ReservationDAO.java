package hotel.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ReservationDAO {

private final DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public ReservationDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	private void close() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Reservation> findAllReservation(){
		List<Reservation> reservation = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt=connection.createStatement();
			rs = stmt.executeQuery("select * from reservation;");
			
			
			while (rs.next()) {
				reservation.add(new Reservation(
						rs.getInt("id"), 
						rs.getInt("room_id"),
						rs.getDate("checkInDate"),
						rs.getDate("checkOutDate"), 
						rs.getString("name"), 
						rs.getString("email"), 
						rs.getString("phone")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return reservation;
	}
	
	public Reservation findReservation(int id) {
		Reservation reservation = null;
		try {
			connection = dataSource.getConnection();
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select * from reservation "
					+ "where id='"+id+"';");
			while(rs.next()) {
				reservation = new Reservation(
						rs.getInt("id"), 
						rs.getInt("room_id"),
						rs.getDate("checkInDate"),
						rs.getDate("checkOutDate"), 
						rs.getString("name"), 
						rs.getString("email"), 
						rs.getString("phone"));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return reservation;
		
	}
	
	public int createReservation(Reservation reservation) {
		int rowEffected=0;
		try {
			connection=dataSource.getConnection();
			pStmt=connection.prepareStatement("INSERT INTO `reservation` "
					+ "(`room_id, `checkInDate`, `checkOutDate`, `name`, `email`, `phone`) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
			
			pStmt.setInt(1, reservation.getRoom_id());
			pStmt.setDate(2, (Date) reservation.getCheckInDate());
			pStmt.setDate(3, (Date) reservation.getCheckOutDate());
			pStmt.setString(4, reservation.getName());
			pStmt.setString(5, reservation.getEmail());
			pStmt.setString(6, reservation.getPhone());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	public int editReservation(final Reservation reservation) {
		int rowEffected=0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("UPDATE `reservation` SET "
					+ "`room_id` = ?,"
					+ " `checkInDate` = ?, "
					+ "`checkOutDate` = ?, "
					+ "`name` = ?, "
					+ "`email` = ?, "
					+ "`phone` = ? "
					+ "WHERE (`id` = ?);");


			pStmt.setInt(1, reservation.getRoom_id());
			pStmt.setDate(2, (Date) reservation.getCheckInDate());
			pStmt.setDate(3, (Date) reservation.getCheckOutDate());
			pStmt.setString(4, reservation.getName());
			pStmt.setString(5, reservation.getEmail());
			pStmt.setString(6, reservation.getPhone());
			pStmt.setInt(7, reservation.getId());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();		
		}
		return rowEffected;
	}
	
	public int removeReservation(int id) {
		int rowEffected= 0;
		try {
			connection=dataSource.getConnection();
			pStmt=connection.prepareStatement("delete from reservation where id = ?;");
			pStmt.setInt(1, id);
			rowEffected=pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}

}
