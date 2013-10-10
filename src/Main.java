import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constraints problem = new Constraints("problem2.txt");
		//System.out.println(problem.employees[10].meetings.toString());
		
		Comparator<Meeting> comparator = new MeetingsComparator();
		PriorityQueue<Meeting> m = new PriorityQueue<Meeting>(30,comparator);
		
		BTTree btt = new BTTree(problem.numOfTimeSlots,m);		
		
		boolean result = btt.backTracking(btt.root,problem);
		
		if (result) {
			
		}
		System.out.println("DONE");
	}
}
