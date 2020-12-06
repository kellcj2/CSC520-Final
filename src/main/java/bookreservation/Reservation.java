package bookreservation;

import bookreservation.CatalogedBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reservation {
    public static class Manager {
        public static void makeReservation(CatalogedBook book) throws IOException {
            System.out.println("--------------------------------------------");
            System.out.println("Enter a name for the book reservation: "); 

            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            String response = b.readLine();

            book.setReserved(true);
            book.setReservedUser(response);
        }

        public static void cancelReservation(CatalogedBook book) {
              book.setReserved(false);
              book.setReservedUser(""); 
        }

        public static ArrayList<CatalogedBook> viewReservations(String username, ArrayList<CatalogedBook> books) throws IOException {
            ArrayList<CatalogedBook> result = new ArrayList<CatalogedBook>();

            // find all books with authors or titles containing user response
            for(int i=0; i < books.size(); i++) {
                if(books.get(i).getReservedName().toLowerCase().equals(username.toLowerCase()) && 
                   books.get(i).getReserved() )
                    result.add(books.get(i));
            }

            return result;
        }
    }
}
