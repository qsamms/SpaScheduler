

public class Treatment{
	private String name;
	private double price;
	
	public Treatment(String tName, double tPrice) {
		name=tName;
		price=tPrice;
	}

	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name + ": $" + price;
	}
	
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Treatment)) {
			return false;
		}
		
		Treatment t = (Treatment) o;
		
		return this.name.equals(t.name) && this.price == t.price;
	}
	
	public int hashCode() {
		return (int) price % 7;
	}
	
}