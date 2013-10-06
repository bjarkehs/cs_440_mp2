import java.util.*; 

public class BTTreeNode {
	public List<Integer[]> timeSlots;
	public List<Integer> meetings;
	public BTTreeNode parent;
	
	public BTTreeNode(List<Integer[]> ts, List<Integer> m, BTTreeNode p) {
		this.timeSlots = new ArrayList<Integer[]>(ts);
		this.meetings = new ArrayList<Integer>(m);
		this.parent = p;
	}
}