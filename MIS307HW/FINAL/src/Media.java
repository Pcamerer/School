import java.sql.Date;

public class Media {
	public static final String PIN = null;
	public String title;
	public String rating;
	public String genre;
	public int quantityAvailable;
	public int quantityRented;
	private Date nextAvailableDate;
	public boolean name;


	public Media(String title, String rating, String genre, int quantityAvailable, int quantityRented)
	{
		this.title = title;
		this.rating = rating;
		this.genre = genre;
		this.quantityAvailable = quantityAvailable;
		this.quantityRented = quantityRented;
		this.nextAvailableDate = null;
	}
	
	public Media(String title, String rating, String genre)
	{
		this.title = title;
		this.rating = rating;
		this.genre = genre;
	}
	
	public Date GetAvailability()
	{
		return nextAvailableDate;
	}
	
	public int GetTotal()
	{
		return (this.quantityAvailable + this.quantityRented);
	}
}