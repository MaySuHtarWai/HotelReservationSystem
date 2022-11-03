package test;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import hotel.model.Room;

/**
 * Servlet implementation class DBTest
 */
public class DBTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/hotelReservationSystem")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		Connection connection =null;
		Statement stmt = null;
		ResultSet rs=null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs=stmt.executeQuery("select * from room;");
			
			while (rs.next()) {
				Room room = new Room(
						rs.getInt("id"), 
						rs.getString("description"), 
						rs.getInt("noOfPerson"), 
						rs.getDouble("price"));
					out.print(room+"\n" );
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null);
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
