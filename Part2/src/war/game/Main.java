package war.game;

public class Main {
	
	public enum Move {
		NONE, PARADROP, BLITZ
	}
	
	public enum Agent {
		NEUTRAL, BLUE, GREEN
	}

	public static void main(String[] args) {
		WarGame wg = new WarGame("Sevastopol.txt");
		
		System.out.println("The map:");
		wg.printMap();
		
		System.out.println("Occupation of the map:");
		wg.printOccupiedMap();
		
		Agent currentAgent = Agent.BLUE;
		GameTree gt = new GameTree();
		GameTreeNode oldNode = null;
		GameTreeNode newNode = new GameTreeNode(wg); 
		for (int i = 0; i < 36; i++) {
			oldNode = newNode;
			if (i == 0) {
				gt.minimax(currentAgent, newNode, true, 3);
				currentAgent = Agent.GREEN;
				continue;
			}
			newNode = new GameTreeNode(oldNode);
			gt.minimax(currentAgent, newNode, true, 3);
			if (currentAgent == Agent.BLUE) {
				currentAgent = Agent.GREEN;
			} else {
				currentAgent = Agent.BLUE;
			}
			if (i > 4) {
				break;
			}
		}
		
		System.out.println("Occupation of the map:");
		wg.printOccupiedMap();
	}

}
