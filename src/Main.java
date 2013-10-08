import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constraints problem = new Constraints("problem2.txt");
		//System.out.println(problem.employees[10].meetings.toString());
		
		Comparator<Integer> comparator = new MeetingsComparator();
		PriorityQueue<Integer> m = new PriorityQueue<Integer>(30,comparator);
		for (int i = 1; i<= problem.numOfMeetings; i++) {
			m.add(i);
		}
		
		BTTree btt = new BTTree(problem.numOfTimeSlots,m);		
		
		boolean result = btt.backTracking(btt.root,problem);
		
		if (result) {
			
		}
		System.out.println("DONE");
	}
}