import java.util.ArrayList;
import java.util.HashMap;

/** lab1 drawing with basic java 2D classes
 * 
 * @author quinnsamms
 *
 */
public class Spa{
	/**
	 * general information about spa
	 *
	 */
	private String name;
	private String location;
	
	/**
	 * 
	 * ArrayLists to hold instances of classes
	 */
	private ArrayList<Appointment> schedule;
	private ArrayList<Treatment> treatments;
	private ArrayList<Therapist> therapists;
	private ArrayList<Customer> customers;
	
	/**
	 * 
	 * @param n
	 * @param l
	 * constuctor for spa, takes a name and location for spa 
	 */
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
	
	/**
	 * prints info about the spa onto the terminal
	 */
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
			System.out.println(trav.get(i) + ": ");
			System.out.println("");
			for(int j = 0;j<schedule.size();j++) {
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
	
	
	/** 
	 * returns the name of the spa 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns location of spa
	 * @return
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * adds an apopintment to appointment arraylist
	 * @param a
	 */
	public void setAppointment(Appointment a) {
		schedule.add(a);
	}
	
	/**
	 * adds a therapist to therapist arrayList
	 * @param t
	 */
	public void addTherapist(Therapist t) {
		therapists.add(t);
	}
	
	/**
	 * removes therapist from therapist arraylist
	 * @param t
	 */
	public void removeTherapist(Therapist t) {
		therapists.remove(t);
	}
	
	/**
	 * adds customer to customer arraylist
	 * @param c
	 */
	public void addCustomer(Customer c) {
		customers.add(c);
	}
	
	/**
	 * removes customer from customer arraylist
	 * @param c
	 */
	public void removeCustomer(Customer c) {
		customers.remove(c);
	}
	
	/**
	 * returns all treatments as arraylist
	 * @return
	 */
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}
	
	/**
	 * returns specific treatment instance from a given name, returns null if not found
	 * @param treatment
	 * @return
	 */
	public Treatment getTreatment(String treatment) {
		
		for(int i = 0;i<treatments.size();i++) {
			if(treatments.get(i).getName().equals(treatment)) {
				return treatments.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * returns all therapists as arraylist
	 * @return
	 */
	public ArrayList<Therapist> getTherapists() {
		return therapists;
	}
	
	/**
	 * retuns all therapists as a string array
	 * @return
	 */
	public String[] getTherapistsAsString() {
		String[] ther = new String[therapists.size()];
		
		for(int i =0 ;i<therapists.size();i++) {
			ther[i] = therapists.get(i).name;
		}
		
		return ther;
	}
	
	/**
	 * returns all customers as arraylist
	 * @return
	 */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * returns all customers as a string
	 * @return
	 */
	public String[] getCustomersAsString() {
		String[] custs = new String[customers.size()];
		
		for(int i =0 ;i<customers.size();i++) {
			custs[i] = customers.get(i).name;
		}
		
		return custs;
	}
	
	/**
	 * returns specific therapist instance from a given name, returns null otherwise
	 * @param name
	 * @return
	 */
	Therapist getTherapist(String name) {
		
		for(int i = 0;i<therapists.size();i++) {
			if(therapists.get(i).name == name) {
				return therapists.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * returns specific customer instance from a given name, returns null otherwise
	 * @param name
	 * @return
	 */
	Customer getCustomer(String name) {
		
		for(int i = 0;i<customers.size();i++) {
			if(customers.get(i).name == name) {
				return customers.get(i);
			}
		}
		
		return null;
	}
	
}