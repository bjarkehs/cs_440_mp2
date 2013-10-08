import java.util.*; 

public class BTTreeNode {
	public List<Integer[]> timeSlots;
	public PriorityQueue<Integer> meetings;
	public BTTreeNode parent;
	
	public BTTreeNode() {
		this.timeSlots = new ArrayList<Integer[]>();
		this.meetings = new PriorityQueue<Integer>();
	}
	
	public BTTreeNode(List<Integer[]> ts, PriorityQueue<Integer> m, BTTreeNode p) {
		this.timeSlots = new ArrayList<Integer[]>(ts);
		this.meetings = new PriorityQueue<Integer>(m);
		this.parent = p;
	}
}