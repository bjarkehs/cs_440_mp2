import java.util.*;

public class BTTree {
	public BTTreeNode root = new BTTreeNode();
	public BTTreeNode solution = new BTTreeNode();
	private Constraints c;
	
	public BTTree(Constraints c) {
		//System.out.println("t = "+t+" , m = "+m);
		this.root.timeSlots = new ArrayList<List<Meeting>>();
		//create timeSlots of a specific size
		for (int i = 0; i <= c.numOfTimeSlots; i++) {
			this.root.timeSlots.add(new ArrayList<Meeting>());
		}
		this.root.meetings = c.meetings;
		
		this.c = c;
	}
	
	public boolean checkAssignment(Meeting m, int loc, List<List<Meeting>> ts) {
		//System.out.println("Meeting: "+ m.name);
		Set<Meeting> setOfMeetings = new HashSet<Meeting>();
		for (Employee emp : m.employees) {
			setOfMeetings.addAll(emp.meetings);
		}
		
		//System.out.println("Conflicting meetings: " + setOfMeetings);
		
		Set<Meeting> timeSlotMeetings = new HashSet<Meeting>();
		
		for (List<Meeting> lm : ts) {
			timeSlotMeetings.addAll(lm);
		}
		
		if (timeSlotMeetings.contains(m)) {
			System.out.println("Already there");
			return false;
		}
		
		//System.out.println("Meetings: " +ts.get(loc) + " at timeslot: " +loc);
		for (int i = (loc-2); i <= loc+2; i++) {
			if (i <= 0 || i >= ts.size()) {
				continue;
			}
			for (Meeting mt : ts.get(i)) {
				//System.out.println("Given meeting: "+m);
				//System.out.println(setOfMeetings);
				//System.out.println("Current checked meeting: "+mt);
				//System.out.println("Contains: " + setOfMeetings.contains(mt));
				//System.out.println("Travel time: "+ c.traveltimes[m.name][mt.name] +" > Distance: "+Math.abs(loc-i));
				//System.out.println("Traveltime clause: " + (c.traveltimes[m.name][mt.name] > Math.abs(loc-i)));
				if (setOfMeetings.contains(mt) && (c.traveltimes[m.name][mt.name] >= Math.abs(loc-i))) {
					//System.out.println("Cannot be in this");
					return false;
				}
			}
		}
		return true;
	}

	public boolean backTracking(BTTreeNode n) {
		//System.out.println("Meetings to assign: " + n.meetings);
		if (n.meetings.isEmpty()) {
			this.solution = n;
			return true;
		}
		Meeting m = n.meetings.poll();
		//System.out.println("Trying meeting : " + m.name);
		//System.out.println(m);
		for (int i = 1; i < n.timeSlots.size(); i++) {
			if (checkAssignment(m, i, n.timeSlots)) {
				BTTreeNode child = new BTTreeNode(n.meetings,n);
				List<Meeting> temp = child.timeSlots.get(i);
				temp.add(m);
				if (backTracking(child)) {
					return true;
				}
			}
		}
		System.out.println("Meetings to assign: " + n.meetings);
		return false;
	}
	
	public void printSolution() {
		System.out.println("Meetings Arranged");
		int[] meetings = new int[c.numOfMeetings+1];
		for (int i=1; i < this.solution.timeSlots.size(); i++) {
			if (this.solution.timeSlots.get(i) != null) {
				for (Meeting m : this.solution.timeSlots.get(i)) {
					meetings[m.name] = i;
				}
			}
		}
		for (int k = 1; k < meetings.length; k++) {
			System.out.println("Meeting " + k + " is scheduled at timeslot: " + meetings[k]);
		}
	}
}
