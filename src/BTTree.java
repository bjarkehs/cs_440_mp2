import java.util.*;

public class BTTree {
	public BTTreeNode root = new BTTreeNode();
	public BTTreeNode solution = new BTTreeNode();
	
	public BTTree(int t, PriorityQueue<Integer> m) {
		//System.out.println("t = "+t+" , m = "+m);
		this.root.timeSlots = new ArrayList<Integer[]>(t);
		this.root.meetings = m;
	}

	public boolean backTracking(BTTreeNode n, Constraints c) {
		if (n.meetings.isEmpty()) {
			this.solution = n;
			return true;
		}
		int m = n.meetings.poll();
		//System.out.println(m);
		
		
		
		return false;
	}
}