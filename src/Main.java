import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constraints problem = new Constraints("example.txt");
		//System.out.println(problem.employees[10].meetings.toString());
		
		BTTree btt = new BTTree(problem.numOfTimeSlots,problem.meetings);		
		
		boolean result = btt.backTracking(btt.root,problem);
		
		if (result) {
			System.out.println("Meetings Arranged");
			for (int i=1; i<= problem.numOfMeetings; i++) {
				if (btt.solution.timeSlots.get(i) != null) {
					List<Meeting> temp = btt.solution.timeSlots.get(i);
					for (int j=0; j < temp.size(); j++) {
						Meeting m = temp.get(j);
						System.out.println("Meeting at time slot "+i+" is : "+m.name);
					}
					
				}
			}
		}
		System.out.println("DONE");
	}
}
