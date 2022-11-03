package hotel.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import hotel.model.Reservation;
import hotel.model.ReservationDAO;
import hotel.model.RoomDAO;

/**
 * Servlet implementation class ReservationController
 */
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/hotelReservationSystem")
	private DataSource dataSource;
	
	private ReservationDAO reservationDAO;

	@Override
	public void init() throws ServletException {
		reservationDAO = new ReservationDAO(dataSource);
	}
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int room_id = Integer.parseInt(request.getParameter("room_id"));
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        Reservation reservation = new Reservation();
        reservation.setRoom_id(room_id);
        reservation.setName(name);
        reservation.setEmail(email);
        reservation.setPhone(phone);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            reservation.setCheckOutDate(sdf.parse(checkOutDate));
            reservation.setCheckInDate(sdf.parse(checkInDate));
        } catch(Exception e) {
            e.printStackTrace();
        }
                
        reservationDAO.createReservation(reservation);
        
        RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
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
