import java.util.*; 

public class BTTreeNode {
	public List<List<Integer>> timeSlots;
	public PriorityQueue<Integer> meetings;
	public BTTreeNode parent;
	
	public BTTreeNode() {
		this.timeSlots = new ArrayList<List<Integer>>();
		this.meetings = new PriorityQueue<Integer>();
	}
	
	public BTTreeNode(List<List<Integer>> ts, PriorityQueue<Integer> m, BTTreeNode p) {
		this.timeSlots = new ArrayList<List<Integer>>(ts);
		this.meetings = new PriorityQueue<Integer>(m);
		this.parent = p;
	}
}