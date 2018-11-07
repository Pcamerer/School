import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Scanner;
import java.sql.ResultSet;

public class StoreDB {

	private Connection con;
	private Statement conStatement;
	final private String CREATE_MEDIA = "CREATE TABLE Media (Title varchar(255), Rating varchar(5),Genre varchar(255),QuantityAvailable int,QuantityRented int,NextAvailableDate date)";
	final private String CREATE_CUSTOMER = "CREATE TABLE Customer(Name varchar(255), PIN char(4), City Varchar(255), State varchar(255), StreetAddress varchar(255))";
	final private String CREATE_CnM = "CREATE TABLE CustomerRentals (CustomerPIN char(4), MediaTitle varchar(255), ReturnDate Date)";

	private PreparedStatement INSERT_MEDIA;
	private PreparedStatement UPDATE_MEDIA_AVAILABILITY;
	private PreparedStatement ALTER_MEDIA;
	private PreparedStatement GET_MEDIA;
	private PreparedStatement REMOVE_MEDIA;

	private PreparedStatement INSERT_CUSTOMER;
	private PreparedStatement UPDATE_CUSTOMER;
	private PreparedStatement GET_CUSTOMER;
	private PreparedStatement GET_CUSTOMER_RENTALS;

	private PreparedStatement GET_NEXT_RETURN;
	private PreparedStatement RENT;
	private PreparedStatement RETURN;

	// Generic DB Methods
	public StoreDB(String datebaseProperties) throws ClassNotFoundException, IOException, SQLException {
		// Create Tables
		SimpleDataSource.init(datebaseProperties);
		con = SimpleDataSource.getConnection();
		conStatement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		if (CreateMedia())
			System.out.println("Media Table Created");
		if (CreateCustomer())
			System.out.println("Customer Table Created");

		if (CreateCustomerRentals())
			System.out.println("CustomerRentals Table Created");

		// Set SQL method Strings
		try {
			INSERT_MEDIA = con.prepareStatement(
					"INSERT INTO Media (Title, Rating, Genre, QuantityAvailable, QuantityRented, NextAvailableDate) Values(?,?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			UPDATE_MEDIA_AVAILABILITY = con.prepareStatement(
					"UPDATE Media SET QuantityAvailable = ?, QuantityRented = ?, NextAvailableDate = ? WHERE Title = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ALTER_MEDIA = con.prepareStatement(
					"UPDATE Media SET Title = ?, Rating = ?, Genre = ?, QuantityAvailable = ?, QuantityRented = ? WHERE Title = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			GET_MEDIA = con.prepareStatement("SELECT * FROM Media WHERE Title = ? OR Rating = ? OR Genre = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			REMOVE_MEDIA = con.prepareStatement("DELETE FROM Media WHERE Title = ?", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			this.DropTable("Media");
			throw e;
		}
		try {
			INSERT_CUSTOMER = con.prepareStatement(
					"INSERT INTO Customer(Name, PIN, City, State, StreetAddress ) VALUES(?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			UPDATE_CUSTOMER = con.prepareStatement(
					"UPDATE Customer SET Name = ?, City = ?, State = ?, StreetAddress  = ? WHERE PIN = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			GET_CUSTOMER = con.prepareStatement(
					"SELECT * FROM Customer WHERE Name = ? OR PIN = ? OR City = ? OR State = ? OR StreetAddress = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			this.DropTable("Customer");
			throw e;
		}
		try {
			GET_CUSTOMER_RENTALS = con.prepareStatement(
					"SELECT * FROM CustomerRentals WHERE CustomerPIN = ? ORDER BY ReturnDate",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			GET_NEXT_RETURN = con.prepareStatement(
					"SELECT * FROM CustomerRentals WHERE MediaTitle = ? ORDER BY ReturnDate",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			RENT = con.prepareStatement(
					"INSERT INTO CustomerRentals (CustomerPIN, MediaTitle, ReturnDate) VALUES(?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			RETURN = con.prepareStatement("DELETE FROM CustomerRentals WHERE CustomerPIN = ? AND MediaTitle = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (SQLException e) {
			this.DropTable("CustomerRentals");
			throw e;
		}
	}

	public boolean DropTable(String tblName) {
		try {
			conStatement.executeUpdate("DROP TABLE " + tblName);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void close() throws SQLException {
		con.close();
	}

	public boolean CreateMedia(/* File media */) throws SQLException {
		try {
			conStatement.executeUpdate(CREATE_MEDIA);
			/*
			 * if (media.exists()) { Scanner in = new Scanner(new
			 * File("Media.txt")); while (in.hasNextLine()) { String
			 * scanned_Line = in.nextLine(); Scanner scan = new
			 * Scanner(scanned_Line); scan.useDelimiter(","); String Title =
			 * scan.next(); String Rating = scan.next(); String Genre =
			 * scan.next(); int QuantityAvailable = scan.nextInt();
			 * 
			 * if (AddMedia(new Media(Title, Rating, Genre, QuantityAvailable,
			 * 0))) scan.close(); } } else return false;
			 */

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean CreateCustomer() {
		try {
			conStatement.executeUpdate(CREATE_CUSTOMER);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean CreateCustomerRentals() {
		try {
			conStatement.executeUpdate(CREATE_CnM);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	// Media table interactions
	public boolean AddMedia(Media add) {
		try {
			INSERT_MEDIA.setString(1, add.title);
			INSERT_MEDIA.setString(2, add.rating);
			INSERT_MEDIA.setString(3, add.genre);
			INSERT_MEDIA.setInt(4, add.quantityAvailable);
			INSERT_MEDIA.setInt(5, 0);
			INSERT_MEDIA.setDate(6, add.GetAvailability());

			INSERT_MEDIA.executeUpdate();
		} catch (SQLException e) {
			return false;
		}

		return true;
	}

	public boolean UpdateMedia(Media update) {
		try {
			ResultSet mediaRS = GetMedia(new Media(update.title, null, null));
			if (mediaRS.first()) {
				if (update.rating == null)
					update.rating = mediaRS.getString("Rating");
				if (update.genre == null)
					update.genre = mediaRS.getString("Genre");
				if (update.quantityAvailable <= 0)
					update.quantityAvailable = mediaRS.getInt("QuantityAvailable");
				if (update.quantityRented <= 0)
					update.quantityRented = mediaRS.getInt("QuantityRented");

				ALTER_MEDIA.setString(1, update.title);
				ALTER_MEDIA.setString(2, update.rating);
				ALTER_MEDIA.setString(3, update.genre);
				ALTER_MEDIA.setInt(4, update.quantityAvailable);
				ALTER_MEDIA.setInt(5, update.quantityRented);
				ALTER_MEDIA.setString(6, update.title);

				ALTER_MEDIA.executeUpdate();
			} else {
				System.out.println(update.title + " is not part of our inventory.");
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean RemoveMedia(String title) {
		try {
			REMOVE_MEDIA.setString(1, title);
			REMOVE_MEDIA.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public ResultSet GetMedia(Media get) throws SQLException {
		if (get.title == null && get.rating == null && get.genre == null)
			return conStatement.executeQuery("SELECT * FROM Media");
		GET_MEDIA.setString(1, get.title);
		GET_MEDIA.setString(2, get.rating);
		GET_MEDIA.setString(3, get.genre);
		return GET_MEDIA.executeQuery();
	}

	// Customer interactions
	public boolean AddCustomer(Customer add) {

		try {
			INSERT_CUSTOMER.setString(1, add.name);
			INSERT_CUSTOMER.setString(2, add.PIN);
			INSERT_CUSTOMER.setString(3, add.city);
			INSERT_CUSTOMER.setString(4, add.state);
			INSERT_CUSTOMER.setString(5, add.address);

			INSERT_CUSTOMER.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean UpdateCustomer(Customer update) {
		try {
			ResultSet customerRS = GetCustomer(update.PIN);
			if (customerRS.first()) {
				if (update.name == null)
					update.name = customerRS.getString("Name");
				if (update.city == null)
					update.city = customerRS.getString("City");
				if (update.state == null)
					update.state = customerRS.getString("State");
				if (update.address == null)
					update.address = customerRS.getString("StreetAddress ");

				UPDATE_CUSTOMER.setString(1, update.name);
				UPDATE_CUSTOMER.setString(2, update.city);
				UPDATE_CUSTOMER.setString(3, update.state);
				UPDATE_CUSTOMER.setString(4, update.address);
				UPDATE_CUSTOMER.setString(5, update.PIN);

				UPDATE_CUSTOMER.executeUpdate();
			} else {
				System.out.println(update.name + " doesn't exists.");
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public ResultSet GetCustomer(Customer get) throws SQLException {
		if (get.name == null && get.city == null && get.state == null && get.address == null && get.PIN == null)
			return conStatement.executeQuery("SELECT * FROM Media");
		GET_MEDIA.setString(1, get.name);
		GET_MEDIA.setString(2, get.city);
		GET_MEDIA.setString(3, get.state);
		GET_MEDIA.setString(4, get.address);
		GET_MEDIA.setString(5, get.PIN);
		return GET_MEDIA.executeQuery();
	}

	public ResultSet GetCustomerRentals(String PIN) throws SQLException {
		GET_CUSTOMER_RENTALS.setString(1, PIN);
		return GET_CUSTOMER_RENTALS.executeQuery();
	}

	// Inventory Interactions
	public boolean Return(String title, String PIN) {
		try {
			ResultSet MediaRS = GetMedia(new Media(title, null, null));
			if (MediaRS.first()) {
				int qa = -1;
				if (MediaRS.getFetchSize() == 1)
					qa = MediaRS.getInt("QuantityAvailable");

				if (qa > -1) {

					RETURN.setString(1, PIN);
					RETURN.setString(2, title);

					ResultSet RentalRS = GetNextAvailableDate(title);
					if (RentalRS.first()) {
						try {
							int qr = MediaRS.getInt("QuantityRented");
							UPDATE_MEDIA_AVAILABILITY.setInt(1, qa + 1);
							UPDATE_MEDIA_AVAILABILITY.setInt(2, qr - 1);
							UPDATE_MEDIA_AVAILABILITY.setDate(3, RentalRS.getDate("RetrunDate"));
							UPDATE_MEDIA_AVAILABILITY.setString(4, title);

							UPDATE_MEDIA_AVAILABILITY.executeUpdate();
						} catch (SQLException e) {
							RENT.setString(1, PIN);
							RENT.setString(1, title);
							RENT.setDate(3, RentalRS.getDate("ReturnDate"));
						}
					}

				} else {
					System.out.println(title + " is not part of our inventory.");
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean Rent(String title, String PIN, int days) {
		try {
			ResultSet CustomerRS = GetCustomer(new Customer(null, PIN, null, null, null));
			if (CustomerRS.first()) {
				ResultSet MediaRS = GetMedia(new Media(title, null, null));
				if (MediaRS.first()) {
					int qa = -1;
					if (MediaRS.getFetchSize() == 1)
						qa = MediaRS.getInt("QuantityAvailable");
					if (qa > 0) {
						RENT.setString(1, PIN);
						RENT.setString(1, title);
						RENT.setDate(3, Date.valueOf(LocalDate.now().plusDays(days)));
						RENT.executeUpdate();

						ResultSet RentalRS = GetNextAvailableDate(title);
						if (RentalRS.first()) {
							try {
								int qr = MediaRS.getInt("QuantityRented");
								UPDATE_MEDIA_AVAILABILITY.setInt(1, qa - 1);
								UPDATE_MEDIA_AVAILABILITY.setInt(2, qr + 1);
								UPDATE_MEDIA_AVAILABILITY.setDate(3, RentalRS.getDate("RetrunDate"));
								UPDATE_MEDIA_AVAILABILITY.setString(4, title);
								UPDATE_MEDIA_AVAILABILITY.executeUpdate();
							} catch (SQLException e) {
								RETURN.setString(1, PIN);
								RETURN.setString(2, title);
								RETURN.executeUpdate();

								return false;
							}
						}
					} else {
						return false;
					}
				}
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/*
	 * public ResultSet GetCustomerRental(String PIN) { return null; }
	 */

	public ResultSet GetNextAvailableDate(String title) throws SQLException {
		GET_NEXT_RETURN.setString(1, title);
		return GET_NEXT_RETURN.executeQuery();
	}
}