import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Therapist extends Person{
	private ArrayList<Treatment> treatmentsOffered;
	private ArrayList<String> daysWorked;
	private Map<String,Integer> roomsFilled;
	private ArrayList<Appointment> apps;
	
	public Therapist(String name, ArrayList<Treatment> treatsOffered, ArrayList<String> days) {
		super(name, "Therapist");
		treatmentsOffered = treatsOffered;
		daysWorked = days;
		roomsFilled = new LinkedHashMap<String,Integer>();
		apps = new ArrayList<Appointment>();
		
		for(int i = 0 ;i<days.size();i++) {
			roomsFilled.put(days.get(i),0);
		}
	}
	
	public ArrayList<Treatment> getTreatments(){
		return treatmentsOffered;
	}
	
	private void setAppointment(Appointment a) {
		int i = roomsFilled.get(a.getDay());
		i++;
		roomsFilled.put(a.getDay(),i);
		apps.add(a);
	}
	
	public void createAppointment(Spa s, Customer c, String day, Treatment treatment) {	
		Appointment a = new Appointment(this, c, day, treatment, roomsFilled.get(day));
		setAppointment(a);
		c.addTreatment(treatment);
		s.setAppointment(a);
	}
	
	public String toString() {
		return "Name: " + name + "\nTreatments: " + treatmentsOffered.toString() 
			+ "\nDaysWorked: " + daysWorked.toString() + "\nRoomsFilled(Max 3): " + roomsFilled.toString();
	}
	
	public String getType() {
		return type;
	}
	
	public ArrayList<String> getAvailibility() {
		ArrayList<String> days = new ArrayList<String>();
		
		for(int i =0 ;i<daysWorked.size();i++) {
			if(roomsFilled.get(daysWorked.get(i)) < 3) {
				days.add(daysWorked.get(i));
			}
		}
		
		return days;
	}
	
}