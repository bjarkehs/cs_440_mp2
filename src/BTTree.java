import java.util.*;

public class BTTree {
	public BTTreeNode root = new BTTreeNode();
	public BTTreeNode solution = new BTTreeNode();
	
	public BTTree(int t, PriorityQueue<Meeting> m) {
		//System.out.println("t = "+t+" , m = "+m);
		this.root.timeSlots = new ArrayList<List<Meeting>>();
		//create timeSlots of a specific size
		for (int i = 0; i <= t; i++) {
			this.root.timeSlots.add(new ArrayList<Meeting>());
		}
		this.root.meetings = m;
	}
	
	public boolean checkAssignment(Meeting m, int loc, List<List<Meeting>> ts, Constraints c) {
		Set<Meeting> setOfMeetings = new HashSet<Meeting>();
		for (Employee emp : m.employees) {
			setOfMeetings.addAll(emp.meetings);
		}
		
		for (int i = (loc-2); i <= loc+2; i++) {
			if (i <= 0 || i >= ts.size()) {
				continue;
			}
			for (Meeting mt : ts.get(i)) {
				if (mt == m) {
					continue;
				}
				if (!(setOfMeetings.contains(mt) && c.traveltimes[m.name][mt.name] > Math.abs(loc-i))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean backTracking(BTTreeNode n, Constraints c) {
		if (n.meetings.isEmpty()) {
			this.solution = n;
			return true;
		}
		Meeting m = n.meetings.poll();
		//System.out.println(m);
		for (int i = 1; i<=n.timeSlots.size(); i++) {
			if (checkAssignment(m,i,n.timeSlots,c)) {
				BTTreeNode child = new BTTreeNode(n.meetings,n);
				List<Meeting> temp = child.timeSlots.get(i);
				temp.add(m);
				if (backTracking(child,c)) {
					return true;
				}
				else continue;
				
			}
		}
		n.meetings.add(m);
		return false;
	}
}
