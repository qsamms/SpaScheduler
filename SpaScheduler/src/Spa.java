
import java.util.ArrayList;
import java.util.HashMap;

public class Spa{
	private String name;
	private String location;
	
	private ArrayList<Appointment> schedule;
	private ArrayList<Treatment> treatments;
	private ArrayList<Therapist> therapists;
	private ArrayList<Customer> customers;
	
	public Spa(String n, String l) {
		name = n;
		location = l;
		
		therapists = new ArrayList<Therapist>();
		customers = new ArrayList<Customer>();
		schedule = new ArrayList<Appointment>();
		
		treatments = new ArrayList<Treatment>();
		Treatment Massage = new Treatment("Massage",50);
		Treatment Facial = new Treatment("Facial",30);
		Treatment Waxing = new Treatment("Waxing",50);
		
		treatments.add(Massage);
		treatments.add(Facial);
		treatments.add(Waxing);
	}
	
	public void printSchedule() {
		HashMap<Integer,String> trav = new HashMap<Integer,String>();
		
		trav.put(0,"Sun");
		trav.put(1,"Mon");
		trav.put(2,"Tue");
		trav.put(3,"Wed");
		trav.put(4,"Thu");
		trav.put(5,"Fri");
		trav.put(6,"Sat");
		
		System.out.println("");
		for(int i = 0;i<7;i++) {
			for(int j = 0;j<schedule.size();j++) {
				System.out.println(trav.get(i) + ": ");
				System.out.println("");
				if(schedule.get(j).getDay().equals(trav.get(i))){
					Appointment a = schedule.get(j);
					System.out.println("Appointment: ");
					System.out.println("Therapist: " + a.getTherapist().name);
					System.out.println("Customer: " + a.getCustomer().name);
					System.out.println("Treatment: " + a.getTreatment());
					System.out.println("");
					
				}
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setAppointment(Appointment a) {
		schedule.add(a);
	}
	
	public void addTherapist(Therapist t) {
		therapists.add(t);
	}
	
	public void removeTherapist(Therapist t) {
		therapists.remove(t);
	}
	
	
	public void addCustomer(Customer c) {
		customers.add(c);
	}
	
	public void removeCustomer(Customer c) {
		customers.remove(c);
	}
	
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}
	
	public Treatment getTreatment(String treatment) {
		
		for(int i = 0;i<treatments.size();i++) {
			if(treatments.get(i).getName().equals(treatment)) {
				return treatments.get(i);
			}
		}
		
		return null;
	}
	
	public ArrayList<Therapist> getTherapists() {
		return therapists;
	}
	
	public String[] getTherapistsAsString() {
		String[] ther = new String[therapists.size()];
		
		for(int i =0 ;i<therapists.size();i++) {
			ther[i] = therapists.get(i).name;
		}
		
		return ther;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public String[] getCustomersAsString() {
		String[] custs = new String[customers.size()];
		
		for(int i =0 ;i<customers.size();i++) {
			custs[i] = customers.get(i).name;
		}
		
		return custs;
	}
	
	Therapist getTherapist(String name) {
		
		for(int i = 0;i<therapists.size();i++) {
			if(therapists.get(i).name == name) {
				return therapists.get(i);
			}
		}
		
		return null;
	}
	
	Customer getCustomer(String name) {
		
		for(int i = 0;i<customers.size();i++) {
			if(customers.get(i).name == name) {
				return customers.get(i);
			}
		}
		
		return null;
	}
	
}