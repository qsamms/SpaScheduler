
import java.util.ArrayList;

public class PreferredCustomer extends Customer{
	private String prefTherapist;
	private ArrayList<Treatment> favoredTreatments;
	
	public PreferredCustomer(String name, String prefTherapist, ArrayList<Treatment> favTreatments) {
		super(name,"Preferred");
		this.prefTherapist = prefTherapist;
		this.favoredTreatments = favTreatments;
	}

	public String getPreferredTherapist() {
		return prefTherapist;
	}
	
	public ArrayList<Treatment> getfavoredTreatments(){
		return favoredTreatments;
	}
	
	@Override
	public void payment(double price) {
		System.out.println("Payment Recieved!");
	}

	@Override
	public String toString() {
		return "Type: " + getType() + "\nName: " + name + 
				"\nPreferred Therapist: " + prefTherapist + "\nFavored Treatments: " + favoredTreatments.toString()
				+ "\nTreatments: " + treatmentsTaken.toString();
	}
	
}