import java.util.HashSet;
import java.util.Set;

public abstract class Customer extends Person{
	protected Set<Treatment> treatmentsTaken;
	
	public Customer(String name, String type) {
		super(name,type);
		treatmentsTaken = new HashSet<Treatment>();
		
	}
	
	public void addTreatment(Treatment treatment) {
		treatmentsTaken.add(treatment);
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	public abstract String toString();
	
	public abstract void payment(double price);
}