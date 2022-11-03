package hotel.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import hotel.model.Reservation;
import hotel.model.ReservationDAO;
import hotel.model.Room;
import hotel.model.RoomDAO;

/**
 * Servlet implementation class RoomController
 */
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/hotelReservationSystem")
	private DataSource dataSource;
	
	private RoomDAO roomDAO;

	@Override
	public void init() throws ServletException {
		roomDAO = new RoomDAO(dataSource);
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        request.setAttribute("checkInDate", checkInDate);
        request.setAttribute("checkOutDate", checkOutDate);
        request.setAttribute("roomId", request.getParameter("roomId"));
        request.setAttribute("price", request.getParameter("price"));
    
        RequestDispatcher rd=request.getRequestDispatcher("reservation.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
