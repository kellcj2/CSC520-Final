package bookreservation;

public class CatalogedBook {
	String isbn, title, author, description;
	String category, location, status;
	int number;
	boolean reserved;
	String reservedUser;
	
	public CatalogedBook(String i, String t, String a, String d,
						 String c, String l, String s, int n) {
		isbn = i; title = t; author = a; description = d;
		category = c; location = l; status = s; number = n;
		reserved = false;
		reservedUser = "";
	}

	public String getISBN() { return isbn; }

	public String getTitle() { return title; }

	public String getAuthor() { return author; }

	public String getDesc() { return description; }

	public String getCat() { return category; }

	public String getLoc() { return location; }

	public String getStatus() { return (this.getReserved()) ? "Unavailable" : "Available" ; }

	public int getNumber() { return number; }

	public boolean getReserved() { return reserved; }

	public String getReservedName() { return reservedUser; }

	public String toString() { return number + ")\t'" + title + "', by " + author; }

	public void setReserved(boolean r) { reserved = r; }

	public void setReservedUser(String u) { reservedUser = u; }
}
