import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Rental_exe {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		StoreDB BlockBuster = new StoreDB("database.properties");
		Scanner input = new Scanner(System.in);
		boolean ask = true;
		System.out.println("Welcome to the movie rental database. Please chose from an option below.");
		while (ask) {
			System.out.println("Welcome to the movie rental database. Please chose from an option below.");
			System.out.println("(A) Exit: Exit the program");
			System.out.println("(B) Movie options");
			System.out.println("(C) Rent a movie");
			System.out.println("(D) Return a movie");
			System.out.println("(E) Customer Options");
			System.out.println("(X) Print movie database to text file");
			String choice = input.next().toUpperCase();

			switch (choice) {
			case "A":
				if (choice.equals("A")) {
					BlockBuster.close();
					input.close();
					System.out.println();
					System.out.println("Exiting the program now.");
					System.exit(0);
				}
				break;
			case "B":
				if (choice.equals("B")) {

					String movieOption;
					do {
						System.out.println("*******************");
						System.out.println("***MOVIE OPTIONS***");
						System.out.println("*******************");
						System.out.println("[1] Add Movie ");
						System.out.println("[2] Print All");
						System.out.println("[3] Search by:");
						System.out.println("[4] Check Availabillity");
						System.out.println("[5] Main Menu");
						System.out.println();
						System.out.print("Select Movie Option: ");
						movieOption = input.next().toUpperCase();
						System.out.println();

						switch (movieOption) {
						case "1":
							if (movieOption.equals("1")) {
								// Movie variables: Title, Rating, Genre
								System.out.println("*****ADD MOVIE*****");
								System.out.println();

								System.out.print("Enter Title: ");
								String title = "";
								if (input.hasNext()) {
									title = input.next();
								}
								// RATING
								System.out.println(); // space
								System.out.println("RATING");
								System.out.println("\t[1] G");
								System.out.println("\t[2] PG");
								System.out.println("\t[3] PG-13");
								System.out.println("\t[4] R");
								System.out.println("\t[5] TV-MA");
								System.out.print("Choice rating: ");
								String rating = input.next();
								switch (rating) {
								case "1":
									rating = "G";
									break;
								case "2":
									rating = "PG";
									break;
								case "3":
									rating = "PG-13";
									break;
								case "4":
									rating = "R";
									break;
								case "5":
									rating = "TV-MA";
									break;
								}
								System.out.println();

								// GENRE
								System.out.println("GENRE");
								System.out.println("\t[1] Action");
								System.out.println("\t[2] Drama");
								System.out.println("\t[3] Adventure");
								System.out.println("\t[4] Kids");
								System.out.println("\t[5] Horror");
								System.out.println("\t[6] Documentary");
								System.out.print("Choose genre: ");
								String genre = input.next();
								switch (genre) {
								case "1":
									genre = "Action";
									break;
								case "2":
									genre = "Drama";
									break;
								case "3":
									genre = "Adventure";
									break;
								case "4":
									genre = "Kids";
									break;
								case "5":
									genre = "Horror";
									break;
								case "6":
									genre = "Documentary";
									break;
								}
								System.out.println();

								System.out.print("Enter number available: ");
								int available = input.nextInt();

								if (BlockBuster.AddMedia(new Media(title, rating, genre, available, 0))) {
									System.out.println("Media added Successfully");
								} else {
									System.out.println("Failed to add Media. Check log for errors");
								}
							}
							break;

						case "2": // Print
							if (movieOption.equals("2")) {
								System.out.println("******PRINTING******");
								ResultSet rs = BlockBuster.GetMedia(new Media(null, null, null));
								int colCount = rs.getMetaData().getColumnCount();
								while (rs.next()) {
									for (int i = 1; i < colCount - 2; i++)
										System.out.print(rs.getString(i) + ", ");
									System.out.print("#Available: " + rs.getInt(colCount - 2) + ", ");
									System.out.print("#Out: " + rs.getInt(colCount - 1) + ", ");
									System.out.print(rs.getDate(colCount) + "\n");
								}
							}
							break;

						case "3":
							if (movieOption.contentEquals("3")) {
								System.out.println("*****SEARCH BY*****");
								System.out.println("\t[1]Title");
								System.out.println("\t[2]Rating");
								System.out.println("\t[3]Genre");
								System.out.println("CHOOSE SEARCH TYPE: ");
								String search = input.next().toUpperCase();

								switch (search) {
								case "1": // TITLE
									if (search.equals("1")) {
										System.out.print("Enter Title: ");
										String title = "";
										if (input.hasNext()) {
											title = input.next();
										}
										ResultSet rs = BlockBuster.GetMedia(new Media(title, null, null));
										int colCount = rs.getMetaData().getColumnCount();
										while (rs.next()) {
											for (int i = 1; i < colCount - 2; i++)
												System.out.print(rs.getString(i) + ", ");
											System.out.print("#Available: " + rs.getInt(colCount - 2) + ", ");
											System.out.print("#Rented: " + rs.getInt(colCount - 1) + ", ");
											System.out.print(rs.getDate(colCount) + "\n");
										}
									}
									break;

								case "2": // RATING
									if (search.equals("2")) {
										System.out.print("Enter Rating: ");
										String rating = "";
										if (input.hasNext()) {
											rating = input.next();
										}
										ResultSet rs = BlockBuster.GetMedia(new Media(null, rating, null));
										int colCount = rs.getMetaData().getColumnCount();
										while (rs.next()) {
											for (int i = 1; i < colCount - 2; i++)
												System.out.print(rs.getString(i) + ", ");
											System.out.print("#in: " + rs.getInt(colCount - 2) + ", ");
											System.out.print("#out: " + rs.getInt(colCount - 1) + ", ");
											System.out.print(rs.getDate(colCount) + "\n");
										}
									}
									break;

								case "3": // GENRE
									if (search.equals("3")) {
										System.out.print("Enter Genre: ");
										String genre = "";
										if (input.hasNext()) {
											genre = input.next();
										}
										ResultSet rs = BlockBuster.GetMedia(new Media(null, null, genre));
										int colCount = rs.getMetaData().getColumnCount();
										while (rs.next()) {
											for (int i = 1; i < colCount - 2; i++)
												System.out.print(rs.getString(i) + ", ");
											System.out.print("#in: " + rs.getInt(colCount - 2) + ", ");
											System.out.print("#out: " + rs.getInt(colCount - 1) + ", ");
											System.out.print(rs.getDate(colCount) + "\n");
										}
									}

								}
							}
							break;

						case "4": // CHECK AVAILABILITY
							System.out.println("****AVAILABILITY****");
							if (movieOption.equals("4")) {
								System.out.print("Enter Title: ");
								String title = "";
								if (input.hasNext()) {
									title = input.next();
								}

								ResultSet rs = BlockBuster.GetNextAvailableDate(title);
								int colCount = rs.getMetaData().getColumnCount();
								if (!rs.first())
									System.out.println("Available now!");
								while (rs.next()) {
									for (int i = 1; i < colCount - 2; i++)
										System.out.print(rs.getString(i) + ", ");
									System.out.print("#in: " + rs.getInt(colCount - 2) + ", ");
									System.out.print("#out: " + rs.getInt(colCount - 1) + ", ");
									System.out.print(rs.getDate(colCount) + "\n");
								}
							}
							break;

						case "5": // MAIN MENU
							break;
						}

					} while (!movieOption.equals("5"));

				}
			case "C":
				if (choice.equals("C")) {
					System.out.println("Please enter your pin.");
					String PIN = input.next();
					boolean loop = false;
					if (BlockBuster.GetCustomer(new Customer(null, PIN, null, null, null)) != null)
						;
					loop = true;

					while (loop) {
						System.out.print("What are you checking returning? (Title): ");
						String title = input.nextLine();
						if (BlockBuster.Return(title, "" + PIN))
							System.out.println("Confirmed");
						else
							System.out.println("Failed check error logs.");

						System.out.println("Make another return? (Y/N)");
						title = input.next();
						if (title.equalsIgnoreCase("Y"))
							continue;
						else
							loop = false;
					}

					break;
				}
			case "D":
				if (choice.equals("D")) {
					System.out.println("Please enter your pin.");
					String PIN = input.next();
					boolean loop = false;
					if (BlockBuster.GetCustomer(new Customer(null, PIN, null, null, null)) != null)
						;
					loop = true;

					while (loop) {
						System.out.print("What are you renting? (Title): ");
						String title = input.nextLine();
						if (BlockBuster.Return(title, "" + PIN))
							System.out.println("Confirmed");
						else
							System.out.println("Failed check error logs.");

						System.out.println("Make another return? (Y/N)");
						title = input.next();
						if (title.equalsIgnoreCase("Y"))
							continue;
						else
							loop = false;
					}
					break;
				}
			case "E":
				if (choice.equals("E")) {

					String customerOption;
					do {
						System.out.println("*******************");
						System.out.println("*CUSTOMER OPTIONS**");
						System.out.println("*******************");
						System.out.println("[1] Add Customer ");
						System.out.println("[2] Print All");
						System.out.println("[3] Search by:");
						System.out.println("[4] Main Menu");
						System.out.println();
						System.out.print("Select Customer Option: ");
						customerOption = input.next().toUpperCase();
						System.out.println();

						switch (customerOption) {
						case "1":
							if (customerOption.equals("1")) {
								// Customer Variables: name, PIN, City, State,
								// Street Address
								System.out.println("*****ADD Customer*****");
								System.out.println();

								System.out.print("Enter Name: ");
								String name = "";
								if (input.hasNext()) {
									name = input.next();
								}
								// PIN
								System.out.print("Set PIN: ");
								String PIN = "";
								if (input.hasNext()) {
									PIN = input.next();
								}
								System.out.println();

								// City
								System.out.print("Enter City: ");
								String city = "";
								if (input.hasNext()) {
									city = input.next();
								}
								System.out.println();

								// State
								System.out.print("Enter State: ");
								String state = "";
								if (input.hasNext()) {
									state = input.next();
								}
								System.out.println();

								// Street Address
								System.out.print("Enter Street Address: ");
								String streetAddress = "";
								if (input.hasNext()) {
									streetAddress = input.next();
								}

								if (BlockBuster.AddCustomer(new Customer(name, PIN, city, state, streetAddress))) {
									System.out.println("Customer added Successfully");
								} else {
									System.out.println("Failed to add Customer. Check log for errors");
								}
							}
							break;

						case "2": // Print
							if (customerOption.equals("2")) {
								System.out.println("******PRINTING******");
								ResultSet rs = BlockBuster.GetCustomer(new Customer(null, null, null, null, null));
								int colCount = rs.getMetaData().getColumnCount();
								while (rs.next()) {
									for (int i = 1; i < colCount - 2; i++)
										System.out.print(rs.getString(i) + ", ");
								}
							}
							break;

						case "3":
							if (customerOption.contentEquals("3")) {
								System.out.println("*****SEARCH BY*****");
								System.out.println("\t[1]Name");
								System.out.println("\t[2]PIN");
								System.out.println("\t[3]Address");
								System.out.println("CHOOSE SEARCH TYPE: ");
								String search = input.next().toUpperCase();

								switch (search) {
								case "1": // NAME
									if (search.equals("1")) {
										System.out.print("Enter Name: ");
										String name = "";
										if (input.hasNext()) {
											name = input.next();
										}
										ResultSet rs = BlockBuster
												.GetCustomer(new Customer(name, null, null, null, null));
										int colCount = rs.getMetaData().getColumnCount();
										while (rs.next()) {
											for (int i = 1; i < colCount - 2; i++)
												System.out.print(rs.getString(i) + ", ");
											System.out.print("#Available: " + rs.getInt(colCount - 2) + ", ");
											System.out.print("#Rented: " + rs.getInt(colCount - 1) + ", ");
											System.out.print(rs.getDate(colCount) + "\n");
										}
									}
									break;

								case "2": // PIN
									if (search.equals("2")) {
										System.out.print("Enter Pin: ");
										String PIN = "";
										if (input.hasNext()) {
											PIN = input.next();
										}
										ResultSet rs = BlockBuster
												.GetCustomer(new Customer(null, PIN, null, null, null));
										int colCount = rs.getMetaData().getColumnCount();
										while (rs.next()) {
											for (int i = 1; i < colCount - 2; i++)
												System.out.print(rs.getString(i) + ", ");
											System.out.print("#in: " + rs.getInt(colCount - 2) + ", ");
											System.out.print("#out: " + rs.getInt(colCount - 1) + ", ");
											System.out.print(rs.getDate(colCount) + "\n");
										}
									}
									break;

								case "3": // ADDRESS
									if (search.equals("3")) {
										System.out.print("Enter Street Address: ");
										String streetAddress = "";
										if (input.hasNext()) {
											streetAddress = input.next();
										}
										ResultSet rs = BlockBuster
												.GetCustomer(new Customer(null, null, null, null, streetAddress));
										int colCount = rs.getMetaData().getColumnCount();
										while (rs.next()) {
											for (int i = 1; i < colCount - 2; i++)
												System.out.print(rs.getString(i) + ", ");
											System.out.print("#in: " + rs.getInt(colCount - 2) + ", ");
											System.out.print("#out: " + rs.getInt(colCount - 1) + ", ");
											System.out.print(rs.getDate(colCount) + "\n");
										}
									}

								}
							}
							break;

						case "4": // MAIN MENU
							break;
						}

					} while (!customerOption.equals("4"));

				}
				break;
			case "X":
				if (choice.equals("X")) {
					System.out.println("Writing Movie database to text file");
					System.out.println("Enter a name for text file: ");
					String outputFile = input.next();
					PrintWriter output = new PrintWriter(outputFile);

					ResultSet rs = BlockBuster.GetMedia(new Media(null, null, null));
					int colCount = rs.getMetaData().getColumnCount();
					while (rs.next()) {
						for (int i = 1; i < colCount - 2; i++)
							output.write(rs.getString(i) + " ");
					}

					System.out.println();
					System.out.println("File created!");
					output.close();
					break;
				}

			default:
				System.out.println();
				System.out.println("Please select a different option.");
				System.out.println();

			}
		}

	}
}