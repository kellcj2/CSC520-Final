package bookreservation;

import bookreservation.CatalogedBook;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Catalog {
	private ArrayList<CatalogedBook> books;
	
	public Catalog(String bookfile) {
		books = new ArrayList<CatalogedBook>();
		loadCatalog(bookfile);
	}

	/* loadCatalog
	   reads csv file and loads books into catalog
	   - String bookfile: name of file
	*/
	private void loadCatalog(String bookfile) {
		try {
			// file located in maven resources folder
			InputStream is = getClass().getClassLoader().getResourceAsStream(bookfile);
			InputStreamReader input = new InputStreamReader(is, "UTF-8");
			BufferedReader b = new BufferedReader(input);
			int bookNum = 0;
			String line = b.readLine(); // first line, ignore it
			
			while((line = b.readLine()) != null) { // read 1 line from file. for 1 book
				String [] book = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); // fix problem with commas in book description
				book[3] = book[3].substring
					(2, book[3].length() - 2); // clean up description entry

				// create book and save it
				books.add(new CatalogedBook(book[0], book[1], book[2], book[3],
											book[4], book[5], book[6], bookNum++));
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Error reading book database '" + bookfile + "'");
		}

	} // end loadCatalog

	public ArrayList<CatalogedBook> browseCatalog() {
		return books;
	}

	public ArrayList<CatalogedBook> searchCatalog(String term) {
		ArrayList<CatalogedBook> result = new ArrayList<CatalogedBook>();
		for(CatalogedBook cb : books) {
			if(cb.getTitle().toLowerCase().contains(term) ||
			   cb.getAuthor().toLowerCase().contains(term))
				result.add(cb);
		}

		return result;
	}
}
