import bookreservation.*;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.io.IOException;

public class BookSystemTest {
	// for creating a CatalogedBook 
	private String isbn = "1234253", title = "title", author = "author";
	private String desc = "book description", category = "fiction", location = "here";
	private String status = "Available", reservedUser = "username";
	private int number = 42;
	private boolean reserved = true;

	/* checkBookInfo
	   creates a CatalogedBook, makes sure initial values are set and
	   the 'set' functions work
	 */
	@Test
	public void checkBookInfo() {
		CatalogedBook b = new CatalogedBook(isbn, title, author, desc,
											category, location, status, number);
		// check default values all work
		assertEquals(b.getISBN(), isbn);
		assertEquals(b.getTitle(), title);
		assertEquals(b.getAuthor(), author);
		assertEquals(b.getDesc(), desc);
		assertEquals(b.getCat(), category);
		assertEquals(b.getLoc(), location);
		assertEquals(b.getStatus(), status);
		assertEquals(b.getNumber(), number);
		assertEquals(b.getReserved(), false); // default set to false
		assertEquals(b.getReservedName(), ""); // default empty string

		// update values
		b.setReserved(reserved);
		b.setReservedUser(reservedUser);
		assertEquals(b.getReserved(), reserved);
		assertEquals(b.getReservedName(), reservedUser);
	}

	/* checkReservations
	   creates CatalogedBook and sets it to reserved
	 */
	@Test
	public void checkReservations() throws IOException {
		CatalogedBook b = new CatalogedBook(isbn, title, author, desc,
											category, location, status, number);
		CatalogedBook b2 = new CatalogedBook(isbn, "new title", author, desc,
											category, location, status, 43);
		ArrayList<CatalogedBook> books = new ArrayList<CatalogedBook>();
		books.add(b);
		books.add(b2);

		// reserve 1 book
		Reservation.Manager.makeReservation(b, reservedUser); // create reservation
		assertEquals(b.getReserved(), true); 
		assertEquals(Reservation.Manager.viewReservations(reservedUser, books).size(), 1);

		// reserve 2nd book
		Reservation.Manager.makeReservation(b2, reservedUser);
		assertEquals(b2.getReserved(), true); 
		assertEquals(Reservation.Manager.viewReservations(reservedUser, books).size(), 2);

		// cancel reservations
		Reservation.Manager.cancelReservation(b);
		Reservation.Manager.cancelReservation(b2);
		assertEquals(b.getReserved(), false);
		assertEquals(b2.getReserved(), false);
		assertEquals(Reservation.Manager.viewReservations(reservedUser, books).size(), 0);
	}
	
}
