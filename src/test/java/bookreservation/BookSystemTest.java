import bookreservation.*;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class BookSystemTest {
	@Test
	public void checkBookInfo() {
		String isbn = "1234253", title = "title", author = "author";
		String desc = "book description", category = "fiction", location = "here";
		String status = "Available", reservedUser = "username";
		int number = 42;
		boolean reserved = true;
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
		assertEquals(b.getReserved(), false);
		assertEquals(b.getReservedName(), "");

		// update values
		b.setReserved(reserved);
		b.setReservedUser(reservedUser);
		assertEquals(b.getReserved(), reserved);
		assertEquals(b.getReservedName(), reservedUser);
	}

	
}
