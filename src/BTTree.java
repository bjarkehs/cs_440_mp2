import java.util.*;

public class BTTree {
	public BTTreeNode root = new BTTreeNode();
	public BTTreeNode solution = new BTTreeNode();
	
	public BTTree(int t, PriorityQueue<Integer> m) {
		//System.out.println("t = "+t+" , m = "+m);
		this.root.timeSlots = new ArrayList<List<Integer>>();
		//create timeSlots of a specific size
		for (int i = 0; i < t; i++) {
			this.root.timeSlots.add(0);
		}
		this.root.meetings = m;
	}
	
	public boolean checkAssignment(int m, int loc, List<List<Integer>> ts, Constraints c) {
		for (Meeting tmpM : c.meetings) {
			if (tmpM.name == m) {
				checkAssignmentForMeeting(tmpM, loc, ts, c);
			}
		}
		return false;
	}
	
	private boolean checkAssignmentForMeeting(Meeting m, int loc, List<List<Integer>> ts, Constraints c) {
		Set<Meeting> setOfMeetings = new HashSet<Meeting>();
		for (Employee emp : m.employees) {
			setOfMeetings.addAll(emp.meetings);
		}
		
		for (int i = (loc-2); i <= loc+2; i++) {
			for (Meeting mt : ts.get(i)) {
				if (setOfMeetings.contains(mt) && c.traveltimes[m.name][mt.name] > Math.abs(loc-i)) {
					;
				}
			}
		}
		
		return false;
	}

	public boolean backTracking(BTTreeNode n, Constraints c) {
		if (n.meetings.isEmpty()) {
			this.solution = n;
			return true;
		}
		int m = n.meetings.poll();
		//System.out.println(m);
		for (int i = 1; i<=n.timeSlots.size(); i++) {
			if (checkAssignment(m,i,n.timeSlots,c)) {
				Integer[] temp = n.timeSlots.get()
				n.timeSlots.add
			}
		}
		
		return false;
	}
}