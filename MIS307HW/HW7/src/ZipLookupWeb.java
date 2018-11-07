import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Code for HW7
 * @author Preston Camerer
 */
/**
 * This program provides lookups by city and zip code
 */
public class ZipLookupWeb {
	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		boolean more = true;
		while (more) {
			System.out.println("Lookup Z)ip, C)ity name, Q)uit?");
			String cmd = in.nextLine();

			if (cmd.equalsIgnoreCase("Q")) {
				System.out.print("Exiting the Program!");
				more = false;
			} else if (cmd.equalsIgnoreCase("Z")) {
				System.out.println("Enter Zipcode:");
				String n = in.nextLine();
				String pageurl = "http://www.melissadata.com/lookups/ZipCityPhone.asp?InData=" + n;
				URL u = new URL(pageurl);
				URLConnection connection = u.openConnection();

				InputStream inStream = connection.getInputStream();
				Scanner ins = new Scanner(inStream);

				String statename = "";
				String cityname = "";

				// read each line, detect if the line contains city name or
				// state name,
				// extract and store the information
				while (ins.hasNextLine()) {
					String line = ins.nextLine();
					int index1, index2;
					if (line.indexOf("<td class='columresult'>State</td>") != -1) {
						index1 = line.indexOf("<b>");
						index2 = line.indexOf("</b>");
						statename = line.substring(index1 + 3, index2).trim();
					} else if (line.indexOf("<td class='columresult'>USPS Preferred City Name</td>") != -1) {
						index1 = line.indexOf("<b>");
						index2 = line.indexOf("</b>");
						cityname = line.substring(index1 + 3, index2).trim();
					}
				}
				System.out.println(cityname + ", " + statename);
				ins.close();

			} else if (cmd.equalsIgnoreCase("C")) {
				System.out.println("Enter city name:");
				String ct = in.nextLine();
				System.out.println("Enter State name:");
				String st = in.nextLine();

				String pageurl = "http://www.melissadata.com/lookups/ZipCityPhone.asp?InData=" + ct + "+" + st;

				URL u = new URL(pageurl);
				URLConnection connection = u.openConnection();

				InputStream inStream = connection.getInputStream();
				Scanner ins = new Scanner(inStream);

				// read each line, detect if the line contains zip code,
				// extract zip code and print
				while (ins.hasNextLine()) {
					String line = ins.nextLine();
					int index1 = line.indexOf("zip=");
					int index2;
					if (index1 != -1) {
						index2 = line.indexOf('"', index1);
						System.out.println(line.substring(index1 + 4, index2).trim());
					}
				}
				ins.close();
			}
		} // end while

		in.close();
	}
}
