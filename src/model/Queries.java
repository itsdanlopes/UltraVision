package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

	connection conn;

	public class movieClass {
		connection conn = new connection();

		public boolean newMovie(Movies movie) {

			boolean newMovie = false;
			// boolean rented=false;

			String query = "INSERT INTO music (yearOfRelease, title, genre, rented, director, price, format) "
					+ "VALUES ( '" + movie.getYearOfRelease() + "', '" + movie.getTitle() + "', '" + movie.getGenre()
					+ "', '" + movie.rented + "', '" + movie.getDirector() + "', '" + movie.getPrice() + "' , '"
					+ movie.getFormat() + "');";

			newMovie = conn.ExecuteSet(query);

			return newMovie;
		}

	}

	public class newMusic {
		connection conn= new connection();

		public boolean insertNewMusic(Music music) {
			

			boolean newMusic = false;
			

			String query = "INSERT INTO music (yearOfRelease, title, genre, rented, singer, price, format) "
					+ "VALUES ( '" + music.getYearOfRelease() + "', '" + music.getTitle() + "', '" + music.getGenre()
					+ "', '" + music.rented + "', '" + music.getSinger() + "', '" + music.getPrice() + "' , '"
					+ music.getFormat() + "');";

			newMusic = conn.ExecuteSet(query);

			return newMusic;
		}

	}

	public class newCustomer {

		connection conn = new connection();

		public boolean insertNewCustomer(Customer customer) {

			boolean newCustomer = false;

			String query = "INSERT INTO customer (fName, lName, email, cardNumber, plan, phoneNumber) " + "VALUES ( '"
					+ customer.getfName() + "', '" + customer.getlName() + "', '" + customer.getEmail() + "', '"
					+ customer.getCardNumber() + "' , '" + customer.getPlan() + "', '" + customer.getPhoneNumber()
					+ "');";

			newCustomer = conn.ExecuteSet(query);

			return newCustomer;

		}

		public void newLoyaltyCard(Customer customer) {

			loyaltyCard mermbershipCard = new loyaltyCard(customer);
			int cardPoints = 0;
			int id = 0;

			String query = "SELECT customerID FROM customer WHERE email = '" + customer.getEmail() + "'";
			ResultSet rs = conn.executeQuery(query);

			try {
				while (rs.next()) {
					id = rs.getInt("customerID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			query = "INSERT INTO loyaltyCard (numberOfPoints, customerID) " + "VALUES ( '" + cardPoints + "', '" + id
					+ "');";

			conn.ExecuteSet(query);

		}
	}

	public class loyaltyCard {

		Customer c; // instance of customer
		protected int points;
		protected boolean freeRentAllowed;

		public loyaltyCard(Customer c) {
			// passing the object of c (customer) to attach it to the new card.
			this.c = c;
			points = 0; // zero value because it is a brand new card.

		}

		public void addPoints(int points) {

			this.points += points;

		}

		public boolean availFreeRent() {
			if (this.isfreeRentAllowed()) {
				this.points -= 100;
				setRentAllowed();
				return true;
			} else {
				return false;
			}
		}

		private void setRentAllowed() {
			if (this.points >= 100) {
				this.freeRentAllowed = true;
			} else {
				this.freeRentAllowed = false;
			}
		}

		public int getNumberOfPoints() {
			return points;
		}

		public boolean isfreeRentAllowed() {
			return freeRentAllowed;

		}

	}

	public class updateCustomer {

		connection conn = new connection();

		public boolean updateCustomerDetails(Customer customer, String userID) {

			boolean updatedCustomer = false;

			String query = "UPDATE customer SET fName = '" + customer.getfName() + "', lName = '" + customer.getlName()
					+ "'," + "email = '" + customer.getEmail() + "', cardNumber = '" + customer.getCardNumber()
					+ "', plan = '" + customer.getPlan() + "', phoneNumber = '" + customer.getPhoneNumber() + "'"
					+ "WHERE customerID='" + userID + "';";

			updatedCustomer = conn.ExecuteSet(query);

			return updatedCustomer;

		}

	}

}