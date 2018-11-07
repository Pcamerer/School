public class Customer {
	public String name;
	public String PIN;
	protected String city;
	protected String state;
	protected String address;
	
	public Customer(String name, String PIN, String city, String state, String streetAddress)
	{
		this.name = name;
		this.PIN = PIN;
		this.city = city;
		this.state = state;
		this.address = streetAddress;
	}

	public String GetAddress()
	{
		return name + "\n " + address + "\n" + city + ", " + state;
	}
	
	public String ChangeAddress(String city, String state, String streetAddress)
	{
		if(city != null)
			this.city = city;
		if(state != null)
			this.state = state;
		if(streetAddress != null)
			this.address = streetAddress;
		return this.name + "\n " + this.address + "\n" + this.city + ", " + this.state;
	}
}