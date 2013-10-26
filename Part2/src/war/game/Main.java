package war.game;

public class Main {
	
	public enum Move {
		NONE, PARADROP, BLITZ
	}
	
	public enum Agent {
		NEUTRAL, BLUE, GREEN
	}

	public static void main(String[] args) {
		WarGame wg = new WarGame("maps/Westerplatte.txt");
		int maxDepth = 3;
		
		System.out.println("The map:");
		wg.printMap();
		
		System.out.println("Occupation of the map:");
		wg.printOccupiedMap();
		
		Agent currentAgent = Agent.BLUE;
		GameTree gt = new GameTree();
		GameTreeNode oldNode = new GameTreeNode(wg);
		GameTreeNode resultNode = null;
		System.out.println("Starting game");
		System.out.println("Turn: 1");
		gt.minimax(currentAgent, oldNode, true, maxDepth);
//		System.out.println("Played as: " + currentAgent);
		currentAgent = Agent.GREEN;
		for (int i = 1; i < 36; i++) {
			System.out.println("Turn: " + (i+1));
			GameTreeNode newNode = new GameTreeNode(oldNode, true);
//			System.out.println("NEW MOVE");
//			System.out.println("Player is: "+currentAgent);
//			newNode.wg.printOccupiedMap();
			gt.minimax(currentAgent, newNode, true, maxDepth);
//			System.out.println("After move:");
//			newNode.wg.printOccupiedMap();
			oldNode = newNode;
			if (currentAgent == Agent.BLUE) {
				currentAgent = Agent.GREEN;
			} else {
				currentAgent = Agent.BLUE;
			}
			resultNode = newNode;
		}
		
		System.out.println("End-game map: ");
		resultNode.wg.printOccupiedMap();
		resultNode.wg.printScores();
	}

}
