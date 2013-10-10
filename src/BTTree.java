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