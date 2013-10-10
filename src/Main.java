public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constraints problem = new Constraints("problem2.txt");
		//System.out.println(problem.employees[10].meetings.toString());
		
		BTTree btt = new BTTree(problem.numOfTimeSlots,problem.meetings);		
		
		boolean result = btt.backTracking(btt.root,problem);
		
		if (result) {
			System.out.println("Meetings Arranged");
		}
		System.out.println("DONE");
	}
}