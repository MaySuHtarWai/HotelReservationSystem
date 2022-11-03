package hotel.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class RoomDAO {
	private final DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public RoomDAO(DataSource dataSource) {
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
	
	public List<Room> findAllRoom(){
		List<Room> room = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt=connection.createStatement();
			rs = stmt.executeQuery("select * from room;");
			
			
			while (rs.next()) {
				room.add(new Room(
						rs.getInt("id"), 
						rs.getString("description"), 
						rs.getInt("noOfPerson"), 
						rs.getDouble("price")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return room;
	}
	
	public Room findRoom(int id) {
		Room room = null;
		try {
			connection = dataSource.getConnection();
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select * from room "
					+ "where id='"+id+"';");
			while(rs.next()) {
				room = new Room(
						rs.getInt("id"), 
						rs.getString("description"), 
						rs.getInt("noOfPerson"), 
						rs.getDouble("price"));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return room;
		
	}
	
	public int createRoom(Room room) {
		int rowEffected=0;
		try {
			connection=dataSource.getConnection();
			pStmt=connection.prepareStatement("INSERT INTO `room` "
					+ "(`description`, `noOfPerson`, `price`) "
					+ "VALUES (?, ?, ?);");
			pStmt.setString(1, room.getDescription());
			pStmt.setInt(2, room.getNoOfPerson());
			pStmt.setDouble(3, room.getPrice());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	public int editRoom(final Room room) {
		int rowEffected=0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("UPDATE `room` SET "
					+ "`description` = ?, "
					+ "`noOfPerson` = ?, "
					+ "`price` = ? WHERE (`id` = ?);");
			pStmt.setString(1, room.getDescription());
			pStmt.setInt(2, room.getNoOfPerson());
			pStmt.setDouble(3, room.getPrice());
			pStmt.setInt(4, room.getId());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();		
		}
		return rowEffected;
	}
	
	public int removeRoom(int id) {
		int rowEffected= 0;
		try {
			connection=dataSource.getConnection();
			pStmt=connection.prepareStatement("delete from room where id = ?;");
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
