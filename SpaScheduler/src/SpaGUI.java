
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SpaGUI{
	private static Spa s;
	private JFrame window;
	private static String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri"};
	private static ArrayList<String> workdays = new ArrayList<String>(Arrays.asList(weekdays));
	
	public SpaGUI(Spa s) {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600,600);
		window.setTitle(s.getName() + ", " + s.getLocation());
		
		JPanel topPanel = new JPanel(new GridLayout(3,1));
		JPanel title = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel(new FlowLayout());
		JPanel customerInput = new JPanel(new BorderLayout());
		JPanel therapistInput = new JPanel(new BorderLayout());
		JPanel treatmentInput = new JPanel(new BorderLayout());
		JPanel submitPanel = new JPanel();
		JPanel textPanel = new JPanel(new FlowLayout());
		JPanel customerInfoPanel = new JPanel(new BorderLayout());
		JPanel therapistInfoPanel = new JPanel(new BorderLayout());
		
		JLabel customerLabel = new JLabel("Customer: ");
		JLabel therLabel = new JLabel("Therapist: ");
		JLabel titleLable = new JLabel("Schedule an appointment: ");
		JLabel treatmentLabel = new JLabel("Treatment: ");
		JLabel customerInfo = new JLabel("Customer Info");
		JLabel therapistInfo = new JLabel("Therapist Info");
		
		JButton addCustomer = new JButton("Add Customer");
		JButton submitButton = new JButton("Create Appointment");
		
		JTextArea custInfo = new JTextArea(5,30);
		JTextArea therInfo = new JTextArea(5,35);
		
		topPanel.add(title);
		title.add(titleLable,BorderLayout.CENTER);
		title.add(inputPanel,BorderLayout.SOUTH);
		topPanel.add(textPanel);
		topPanel.add(submitPanel);
		
		
		inputPanel.add(customerInput);
		inputPanel.add(therapistInput);
		inputPanel.add(treatmentInput);
		
		
		ArrayList<Treatment> treatmentsOffered = s.getTreatments();
		
		//defining Customers
		createCustomers(s);
		
		//defining Therapists
		createTherapists(s);
		
		//Customer Dropdown
		String[] customers = s.getCustomersAsString();
		JComboBox<String> customerCB = new JComboBox<String>(customers);
		customerInput.add(customerLabel,BorderLayout.NORTH);
		customerInput.add(addCustomer,BorderLayout.SOUTH);
		customerInput.add(customerCB,BorderLayout.CENTER);
		
		//Therapist Drowdown
		String[] therapists = s.getTherapistsAsString();
		JComboBox<String> therapistCB = new JComboBox<String>(therapists);
		therapistInput.add(therLabel,BorderLayout.NORTH);
		therapistInput.add(therapistCB,BorderLayout.CENTER);
		
		//Treatment Dropdown
		String[] treatmentsString = new String[treatmentsOffered.size()];
		for(int i = 0;i<treatmentsString.length;i++) {
			treatmentsString[i] = treatmentsOffered.get(i).getName();
		}
		JComboBox<String> treatmentCB = new JComboBox<String>(treatmentsString);
		treatmentInput.add(treatmentLabel,BorderLayout.NORTH);
		treatmentInput.add(treatmentCB,BorderLayout.CENTER);
		
		textPanel.add(customerInfoPanel);
		textPanel.add(therapistInfoPanel);
		
		customerInfoPanel.add(custInfo,BorderLayout.CENTER);
		customerInfoPanel.add(customerInfo,BorderLayout.NORTH);
		
		therapistInfoPanel.add(therInfo,BorderLayout.CENTER);
		therapistInfoPanel.add(therapistInfo,BorderLayout.NORTH);
		
		
		submitPanel.add(submitButton);
		
		addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer c = addCustomer();
				s.addCustomer(c);
				customerCB.addItem(c.name);
			}
		} );
		
		therapistCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ther = (String) therapistCB.getSelectedItem();
				Therapist t = s.getTherapist(ther);
				ArrayList<Treatment> treats = t.getTreatments();
				updateTreatments(treats,treatmentCB);
				treatmentInput.add(treatmentCB);
				
				String ther2 = (String) therapistCB.getSelectedItem();
				Therapist t2 = s.getTherapist(ther2);
				
				therInfo.setText(t2.toString());
				
			}
		} );
		
		customerCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cust = (String) customerCB.getSelectedItem();
				Customer c = s.getCustomer(cust);
				
				custInfo.setText(c.toString());
			}
		});
		
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cust = (String) customerCB.getSelectedItem();
				Customer c = s.getCustomer(cust);
				
				String ther = (String) therapistCB.getSelectedItem();
				Therapist t = s.getTherapist(ther);
				
				String treat = (String) treatmentCB.getSelectedItem();
				Treatment tr = s.getTreatment(treat);
				
				ArrayList<String> availibility = t.getAvailibility ();
				String[] array = new String[availibility.size()];
				
				for(int i = 0;i<array.length;i++) {
					array[i] = availibility.get((array.length-1)-i);
				}
				
				int dayidx = JOptionPane.showOptionDialog(window,"Hallie's available days:", 
						"Create an Appointment with " + t.name, JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,
						null,array,null);
					
				int confirm = JOptionPane.showConfirmDialog(window,"Conform Appointment with " + c.getName() +
						" on " + array[dayidx] + "?\n" + "Treatment: " + tr.getName(), "Confirm", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						
				if(confirm == JOptionPane.YES_OPTION) {
					t.createAppointment(s,c,array[dayidx],tr);
				}else {
					System.out.println("Appointment cancelled!");
				}
				
				therInfo.setText(t.toString());
			}
		} );
		
		custInfo.setText(s.getCustomers().get(0).toString());
		therInfo.setText(s.getTherapists().get(0).toString());
		
		window.setContentPane(topPanel);
		window.pack();
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		s = new Spa("Quinn's Spa", "Santa Clara");
		new SpaGUI(s);
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
	
	public static void updateTreatments(ArrayList<Treatment> treats, JComboBox<String> cb) {
		cb.removeAllItems();
		
		for(int i = 0;i<treats.size();i++) {
			cb.addItem(treats.get(i).getName());
		}
	}
	
	public static Customer addCustomer() {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(System.lineSeparator());
		Customer c = null;
		ArrayList<Treatment> favTreats = new ArrayList<Treatment>();
		
		System.out.println("Customer Name? ");
		String name = scanner.next();
		
		System.out.println("Customer Type? 1) Preferred 2) Regular");
		int type = scanner.nextInt();
		
		
		if(type == 1) {
			 System.out.println("Preferred Therapist? ");
			 String prefTherapist = scanner.next();
				
			 System.out.println("Favored Treatments? Options: [Massage, Facial, Sauna] Type 'done' when finished");
			 String read = "";
			 while(!read.equals("done")) {
				 read = scanner.next();
				 if(!read.equals("done")) favTreats.add(s.getTreatment(read));
			 }
			 c = new PreferredCustomer(name,prefTherapist,favTreats);
			 
		}else{
			 c = new RegularCustomer(name);
		}
		
		scanner.close();
		return c;
	}
	
}