import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Scanner;

/**
 * This program demonstrates how to use a URL connection to communicate with a
 * web server. It connects to the hours table of Parks Library at Iowa State
 * University, and print the hours of the date user specifies.
 */
public class URLGetParksHoursINCLASS {
	public static void main(String[] args) throws IOException {
		// Get command line arguments
		String urlString = "http://app.lib.iastate.edu/libhours.php?location_name=parks";

		// Open connection
		URL u = new URL(urlString);
		URLConnection connection = u.openConnection();

		// Check if response code is HTTP_OK (200)
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int code = httpConnection.getResponseCode();
		if (code != HttpURLConnection.HTTP_OK) {
			return;
		}

		// Receiving the date number user want to check
		Scanner console = new Scanner(System.in);
		System.out.println("Parks Library Hours");
		System.out.print("Enter the date (number) you want to know: ");
		String date_to_search = console.next();
		console.close();

		// Filtering invalid inputs
		int currentmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (currentmonth == 1 || currentmonth == 3 || currentmonth == 5 || currentmonth == 7 || currentmonth == 8
				|| currentmonth == 10 || currentmonth == 12) {
			// 31 days in this category
			if (Integer.parseInt(date_to_search) > 31 || Integer.parseInt(date_to_search) < 0) {
				System.out.println("Incorrect date!!");
				return;
			}
		} else {
			if (currentmonth == 2) { // 28 days in this category
				if (Integer.parseInt(date_to_search) > 28 || Integer.parseInt(date_to_search) < 0) {
					System.out.println("Incorrect date!!");
					return;
				}
			} else { // 30 days in this category
				if (Integer.parseInt(date_to_search) > 30 || Integer.parseInt(date_to_search) < 0) {
					System.out.println("Incorrect date!!");
					return;
				}
			}
		}

		// Read server response
		InputStream instream = connection.getInputStream();
		Scanner in = new Scanner(instream);

		while (in.hasNextLine()) {
			String input = in.nextLine();
			if (input.contains("<b>" + date_to_search + "</b>")) {
				int info_start = input.indexOf("<b>" + date_to_search + "</b>");
				String input2 = input.substring(info_start, input.length());

				int date_start = 3;
				int date_end = input2.indexOf("</b>");
				
				int hours_start = input2.indexOf("<br/>") + 5;
				int hours_end = input2.indexOf("</td>");
				
				String date = input2.substring(date_start, date_end);
				String hours = input2.substring(hours_start, hours_end);
				
				System.out.println("Date: March " + date);
			}

		}

		while (in.hasNextLine()) // read each line
		{
			String input = in.nextLine();
			System.out.println(input);
		}

		in.close();
	}
}
