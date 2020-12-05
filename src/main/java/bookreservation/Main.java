import bookreservation.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	private static String lineBreak = "--------------------------------------------";
	private static Catalog catalog;

	public static void main(String [] args) throws IOException {
		System.out.println("Reading Book Catalog...");
		catalog = new Catalog("Book Catalog.csv");
		System.out.println("Book Catalog Ready");

		showMenu();
	}

	private static void showMenu() throws IOException {
		String response = "-1";
		while(!response.contains("Q") && !response.contains("q")) {
			System.out.println(lineBreak);
			System.out.println("(1)  Browse Catalog");
			System.out.println("(2)  Search For Books");
			System.out.println("(Q)  Exit");
			
			System.out.print("Enter Choice: ");
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			response = b.readLine();
			
			switch(response) {
			case "1":
				browse(catalog.browseCatalog());
				break;
			case "2":
				search();
				break;
			case "Q":
			case "q":
				System.out.println("Goodbye :)");
				break;
			default:
				System.out.println("Not a valid response");
				break;
			}
		}				
	}

	/* browse
	   display all books in parameter, prompt user to select one
	   - ArrayList<CatalogedBook> books: all books to show to the user
	 */
	private static void browse(ArrayList<CatalogedBook> books) throws IOException {
		String response = "-1";
		while(true) {
			for(int i=0; i < books.size(); i++)
				if(!books.get(i).getReserved())
					System.out.println(books.get(i));

			System.out.println(lineBreak);

			System.out.println("Enter the Book's Number to select it: ");
			System.out.println("G)\tGo Back");
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			response = b.readLine();

			if(response.toUpperCase().equals("G")) return;
			
			try {
				int bookNum = Integer.parseInt(response);
				for(int i=0; i < books.size(); i++) {
					if(bookNum == books.get(i).getNumber()) {
						viewBook(bookNum);
						break;
					}
				}
				System.out.println("Not a valid response");
			
			} catch(NumberFormatException e) { // non-integer entered
				System.out.println("Not a valid response");
			}
		}
		
	}

	/* search
	   prompt user for search term and display all books containing term
	*/
	private static void search() throws IOException {
		System.out.println(lineBreak);
		System.out.println("Enter search term (Title or Author): ");
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		String response = b.readLine().toLowerCase();
		System.out.println(lineBreak);

		ArrayList<CatalogedBook> result = new ArrayList<CatalogedBook>();
		ArrayList<CatalogedBook> books = catalog.browseCatalog();
		
		// find all books with authors or titles containing user response		
		for(int i=0; i < books.size(); i++) {
			if(books.get(i).getTitle().toLowerCase().contains(response) ||
			   books.get(i).getAuthor().toLowerCase().contains(response))
				result.add(books.get(i));
		}

		if(result.size() > 0) // display books containing result
			browse(result);
		else
			System.out.println("No books found");
		
	}

	/* viewBook
	   Displays info for a single book. Prompts user for response
	   - int bookNum: index of book to view
	*/
	private static void viewBook(int bookNum) throws IOException {
		CatalogedBook book = catalog.browseCatalog().get(bookNum);
		String response = "";
		
		while(true) {
			System.out.println(lineBreak);
			System.out.println("ISBN:\t" + book.getISBN());
			System.out.println("Title:\t" + book.getTitle());
			System.out.println("Author:\t" + book.getAuthor());
			System.out.println("Description:\t" + book.getDesc());
			System.out.println("Category:\t" + book.getCat());
			System.out.println("Location:\t" + book.getLoc());
			System.out.println("Status:\t" + book.getStatus());
			System.out.println(lineBreak);
			
			System.out.println("0)\tMake Reservation");
			System.out.println("G)\tGo Back");

			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			response = b.readLine();
			System.out.println(lineBreak);
			
			if(response.toUpperCase().equals("G")) return;

			switch(response) { // reservation stuff not implemented
			case "0":
				System.out.println("Making reservation...");
				// book.setReserved(true);
				break;
			default:
				System.out.println("Not a valid response");
				break;
			}
		}
	}
	

}
