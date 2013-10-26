package war.game;

public class Main {
	
	public enum Move {
		NONE, PARADROP, BLITZ
	}
	
	public enum Agent {
		NEUTRAL, BLUE, GREEN
	}

	public static void main(String[] args) {
		WarGame wg = new WarGame("maps/Keren-small.txt");
		int maxDepth = 7;
		
		System.out.println("The map:");
		wg.printMap();
		
		System.out.println("Occupation of the map:");
		wg.printOccupiedMap();
		
		String column = "ABCDEF";
		
		Agent currentAgent = Agent.BLUE;
		GameTreeNode oldNode = new GameTreeNode(wg);
		
		int totalGreen = 0;
		int totalBlue = 0;
		System.out.println("Starting game");
		System.out.println("Turn: 1");
		
		GameTree gt = new GameTree();
		long startTime = System.nanoTime();
		gt.minimax(currentAgent, oldNode, true, maxDepth, 0);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		totalBlue += gt.maxNodes;
//		System.out.println("Played as: " + currentAgent);
		System.out.println(currentAgent+": "+oldNode.move+" "+column.charAt(oldNode.moveCol)+oldNode.moveRow);
		System.out.println("Map after move:");
		oldNode.wg.printOccupiedMap();
		currentAgent = Agent.GREEN;
		
//		System.out.println("Size:" + wg.size);
//		System.out.println("Moves:" + wg.size*wg.size);
		int amountOfMoves = wg.size*wg.size;
		for (int i = 1; i < amountOfMoves; i++) {
			System.out.println("Turn: " + (i+1));
			GameTreeNode newNode = new GameTreeNode(oldNode, true);
//			System.out.println("NEW MOVE");
//			System.out.println("Player is: "+currentAgent);
//			newNode.wg.printOccupiedMap();
			gt = new GameTree();
			startTime = System.nanoTime();
			gt.minimax(currentAgent, newNode, true, maxDepth, i);
			endTime = System.nanoTime();
			totalTime += endTime - startTime;
//			System.out.println("After move:");
//			newNode.wg.printOccupiedMap();
			oldNode = newNode;
			System.out.println(currentAgent+": "+oldNode.move+" "+column.charAt(oldNode.moveCol)+oldNode.moveRow);
			System.out.println("Map after move:");
			oldNode.wg.printOccupiedMap();
			if (currentAgent == Agent.BLUE) {
				totalBlue += gt.maxNodes;
				currentAgent = Agent.GREEN;
			} else {
				totalGreen += gt.maxNodes;
				currentAgent = Agent.BLUE;
			}
		}
		
		System.out.println("End-game map: ");
		oldNode.wg.printOccupiedMap();
		oldNode.wg.printScores();
		System.out.println("Total time for all moves: " + (float)totalTime/1000000000 + " second(s)");
		System.out.println("Nodes expanded by Blue: " + totalBlue + ", and by Green: " + totalGreen + ", giving a total of: " + (totalBlue+totalGreen));
		System.out.println("The average amount of nodes per move: " + ((totalBlue+totalGreen)/amountOfMoves));
		System.out.println("The average amount of time per move: " + ((float)totalTime/1000000000/amountOfMoves) + " second(s)");
	}

}
