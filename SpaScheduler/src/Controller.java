import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller{
	private static Spa s;
	private static Scanner in;
	private static String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri"};
	private static ArrayList<String> workdays = new ArrayList<String>(Arrays.asList(weekdays));

	
	public Controller() {
		s = new Spa("Quinn's Spa", "Santa Clara");
		in = new Scanner(System.in);
		in.useDelimiter(System.lineSeparator());
		createCustomers(s);
		createTherapists(s);
	}
	
	public static void main(String[] args) {
		new Controller();
		boolean loop = true;
		int input;
		
		while(loop) {
			System.out.println("Welcome to " +  s.getName() + ", at " + s.getLocation());
			System.out.println("What would you like to do? ");
			System.out.println("(1)add a customer");
			System.out.println("(2)schdule an appointment");
			System.out.println("(3)view schedule");
			System.out.println("(4)exit");
			input = in.nextInt();
			
			switch(input) {
				case 1: 
					s.addCustomer(addCustomer());
					break;
				case 2: 
					scheduleAppointment();
					break;
				case 3: 
					s.printSchedule();
					break;
				case 4: 
					loop = false;
					break;
				default:
					System.out.println("Invalid input.");
					break;
			}
		}
	}
	
	public static void scheduleAppointment() {
		ArrayList<Customer> customers = s.getCustomers();
		ArrayList<Therapist> therapists = s.getTherapists();
		int customerinput,therapistinput,dayinput,treatinput;
		
		System.out.println("Which Customer?");
		for(int i = 0;i<customers.size();i++) {
			System.out.println("(" + i  + ")" + customers.get(i).toString() + "\n");
		}
		
		customerinput = in.nextInt();
		
		for(int i = 0;i<therapists.size();i++) {
			System.out.println("(" + i  + ")" + therapists.get(i).toString() + "\n");
		}
		
		therapistinput = in.nextInt();
		
		ArrayList<String> availibility = therapists.get(therapistinput).getAvailibility();
		String[] array = new String[availibility.size()];
		
		for(int i = 0;i<array.length;i++) {
			array[i] = availibility.get(i);
		}
		
		System.out.println(therapists.get(therapistinput).getName() + "'s availibility");
		for(int i = 0;i<array.length;i++) {
			System.out.println("(" + i + ")" + array[i]);
		}
		
		dayinput = in.nextInt();
		
		
		System.out.println("Treatment? ");
		
		ArrayList<Treatment> treats = therapists.get(therapistinput).getTreatments();
		for(int i = 0;i<treats.size();i++) {
			System.out.println("(" + i + ")" + treats.get(i).getName());
		}
		
		treatinput = in.nextInt();
		
		therapists.get(therapistinput).createAppointment(s,customers.get(customerinput),array[dayinput],treats.get(treatinput));
	}
	
	public static Customer addCustomer() {
		Customer c = null;
		ArrayList<Treatment> favTreats = new ArrayList<Treatment>();
		
		System.out.println("Customer Name? ");
		String name = in.next();
		
		System.out.println("Customer Type? 1) Preferred 2) Regular");
		int type = in.nextInt();
		
		
		if(type == 1) {
			 System.out.println("Preferred Therapist? ");
			 String prefTherapist = in.next();
				
			 System.out.println("Favored Treatments? Options: [Massage, Facial, Sauna] Type 'done' when finished");
			 String read = "";
			 while(!read.equals("done")) {
				 read = in.next();
				 if(!read.equals("done")) favTreats.add(s.getTreatment(read));
			 }
			 c = new PreferredCustomer(name,prefTherapist,favTreats);
			 
		}else{
			 c = new RegularCustomer(name);
		}
		
		return c;
	}
	
	public static void createTherapists(Spa s) {
		ArrayList<Treatment> htreats = new ArrayList<Treatment>();
		htreats.add(s.getTreatment("Massage"));
		htreats.add(s.getTreatment("Facial"));
		Therapist hallie = new Therapist("Hallie", htreats, workdays);
		s.addTherapist(hallie);
		
		ArrayList<Treatment> dtreats = new ArrayList<Treatment>(htreats);
		dtreats.add(s.getTreatment("Waxing"));
		Therapist dani = new Therapist("Dani",dtreats,workdays);
		s.addTherapist(dani);
		
	}
	
	public static void createCustomers(Spa s) {
		ArrayList<Treatment> samFavs = new ArrayList<Treatment>();
		samFavs.add(s.getTreatment("Massage"));
		PreferredCustomer sam = new PreferredCustomer("Sam","Dani",samFavs);
		RegularCustomer mark = new RegularCustomer("Mark");
		RegularCustomer john = new RegularCustomer("John");
		s.addCustomer(sam);
		s.addCustomer(mark);
		s.addCustomer(john);
	}
}