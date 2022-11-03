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
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/hotelReservationSystem")
	private DataSource dataSource;
	
	private RoomDAO roomDAO;
	private ReservationDAO reservationDAO;

	@Override
	public void init() throws ServletException {
		roomDAO = new RoomDAO(dataSource);
		reservationDAO = new ReservationDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
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

        List<Room> availableRooms = roomDAO.findAllRoom();
        List<Reservation> reservation = reservationDAO.findAllReservation();

        for (int i = 0; i < reservation.size(); i++) {
            if (
                    (isDateAfterThan(checkInDate, reservation.get(i).getCheckInDate().toString()) && 
                    isDateAfterThan(reservation.get(i).getCheckOutDate().toString(), checkOutDate))
                    ||
                    (isDateAfterThan(reservation.get(i).getCheckInDate().toString(), checkInDate) && 
                    isDateAfterThan(checkOutDate, reservation.get(i).getCheckOutDate().toString()))
                    ||
                    (isDateAfterThan(checkInDate, reservation.get(i).getCheckInDate().toString()) && 
                    isDateAfterThan(checkOutDate, reservation.get(i).getCheckOutDate().toString()))
                    ||
                    (isDateAfterThan(reservation.get(i).getCheckInDate().toString(), checkInDate) && 
                    isDateAfterThan(reservation.get(i).getCheckOutDate().toString(), checkOutDate))
                ) {
                for(int j = 0; j < availableRooms.size(); j++) {
                    if(reservation.get(i).getRoom_id() == availableRooms.get(j).getId()) {
                        availableRooms.remove(j);
                        break;
                    }
                }
            }
        }

        request.setAttribute("availableRooms", availableRooms);

        RequestDispatcher rd=request.getRequestDispatcher("available_rooms.jsp");
		rd.forward(request, response);
	}

	private boolean isDateAfterThan(String firstDate, String secondDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(firstDate);
            Date date2 = sdf.parse(secondDate);
            return date1.compareTo(date2) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
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
