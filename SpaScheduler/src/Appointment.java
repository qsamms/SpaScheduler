public class Appointment{
	private Therapist t;
	private Customer c;
	private Treatment treatment;
	private String day;
	private int room;
	
	public Appointment(Therapist t, Customer c, String day, Treatment treatment, int room) {
		this.t = t;
		this.c = c;
		this.day = day;
		this.treatment = treatment;
		this.room = room;
	}
	
	public Therapist getTherapist() {
		return t;
	}
	
	public Customer getCustomer() {
		return c;
	}
	
	public Treatment getTreatment() {
		return treatment;
	}
	
	public String getDay() {
		return day;
	}
	
	public String toString() {
		return "Therapist: " + t + ", Customer: " + c + 
				", Treatment: " + treatment + ", Day: " + day + ", Room#: " + room;
	}
	
}