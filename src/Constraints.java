import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Constraints {
	
	public int numOfMeetings;
	public int numOfEmployees;
	public int numOfTimeSlots;
	public Employee[] employees;
	
	public Constraints(String filePath) {
		try {
			File aFile = new File(filePath);
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			
			try {
				Pattern pMeetings = Pattern.compile("Number of meetings: (\\d*)");
				Pattern pEmployees = Pattern.compile("Number of employees: (\\d*)");
				Pattern pTimeSlots = Pattern.compile("Number of time slots: (\\d*)");
				Pattern pCheckMeetings = Pattern.compile("(\\d+): (.*)");
				Pattern pNumberMeetings = Pattern.compile("\\d+");
				Matcher matcher;
				String line = null;
				int y = 0;
				boolean checkingMeetings = false;
				boolean checkingTravel = false;
				while((line = input.readLine()) != null) {
					if (y == 0) {
						matcher = pMeetings.matcher(line);
						if (matcher.find()) {
							numOfMeetings = Integer.parseInt(matcher.group(1));
						}
					}
					else if (y == 1) {
						matcher = pEmployees.matcher(line);
						if (matcher.find()) {
							numOfEmployees = Integer.parseInt(matcher.group(1));
							employees = new Employee[numOfEmployees+1];
						}
					}
					else if (y == 2) {
						matcher = pTimeSlots.matcher(line);
						if (matcher.find()) {
							numOfTimeSlots = Integer.parseInt(matcher.group(1));
						}
					}
					
					if (line.contains("Meetings each employee must attend")) {
						checkingMeetings = true;
					}
					if (line.contains("Travel time between meetings")) {
						checkingMeetings = false;
						checkingTravel = true;
					}
					
					if (checkingMeetings) {
						matcher = pCheckMeetings.matcher(line);
						if (matcher.find()) {
							int employee = Integer.parseInt(matcher.group(1));
							Matcher m = pNumberMeetings.matcher(matcher.group(2));
							List<Integer> numbers = new ArrayList<Integer>();
							while (m.find()) {
								numbers.add(Integer.parseInt(m.group()));
							}
							employees[employee] = new Employee(numbers);
						}
					}
					
					if (y < 3) {
						y++;
					}
				}
			}
			finally {
				input.close();
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
	    }
	}
}
