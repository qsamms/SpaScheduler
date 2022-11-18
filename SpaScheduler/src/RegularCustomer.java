
public class RegularCustomer extends Customer{
	
	public RegularCustomer(String name) {
		super(name, "Regular");
	}

	@Override
	public void payment(double price) {
		System.out.println("Payment Recieved!");
		
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nType: " + getType() + "\nTreatments: " + treatmentsTaken.toString();
	}
	
}