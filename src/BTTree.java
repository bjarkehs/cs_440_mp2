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
		return false;
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