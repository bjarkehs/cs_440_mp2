package war.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
	
	public enum Move {
		NONE, PARADROP, BLITZ
	}
	
	public enum Agent {
		NEUTRAL, BLUE, GREEN
	}

	public static void main(String[] args) {
		String wargame = args[0];
		String blueGame = args[1];
		String greenGame = args[2];
		int maxDepthAlpha = 7;
		int maxDepthMinimax = 3;
		
		WarGame wg = new WarGame("maps"+File.separator+wargame+".txt");
		
		String resultfile = wargame + "-" + blueGame + "_vs_" + greenGame + ".txt";
		File f = new File("game_results"+File.separator+resultfile);
		try {
			f.createNewFile();
			PrintStream out = new PrintStream(new FileOutputStream("game_results"+File.separator+resultfile));
			System.setOut(out);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
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
		if (blueGame.equalsIgnoreCase("alpha")) {
			gt.alpha_beta(currentAgent, oldNode, true, maxDepthAlpha, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		} else {
			gt.minimax(currentAgent, oldNode, true, maxDepthMinimax, 0);
		}
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		totalBlue += gt.maxNodes;
		
		System.out.println(currentAgent+": "+oldNode.move+" "+column.charAt(oldNode.moveCol)+(oldNode.moveRow+1));
		System.out.println("Map after move:");
		oldNode.wg.printOccupiedMap();
		currentAgent = Agent.GREEN;
		
		int amountOfMoves = wg.size*wg.size;
		for (int i = 1; i < amountOfMoves; i++) {
			System.out.println("Turn: " + (i+1));
			GameTreeNode newNode = new GameTreeNode(oldNode, true);
			gt = new GameTree();
			startTime = System.nanoTime();
			if (currentAgent == Agent.BLUE) {
				if (blueGame.equalsIgnoreCase("alpha")) {
					gt.alpha_beta(currentAgent, newNode, true, maxDepthAlpha, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
				} else { 
					gt.minimax(currentAgent, newNode, true, maxDepthMinimax, i);
				}
				totalBlue += gt.maxNodes;
				currentAgent = Agent.GREEN;
			} else {
				if (greenGame.equalsIgnoreCase("alpha")) {
					gt.alpha_beta(currentAgent, newNode, true, maxDepthAlpha, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
				} else { 
					gt.minimax(currentAgent, newNode, true, maxDepthMinimax, i);
				}
				totalGreen += gt.maxNodes;
				currentAgent = Agent.BLUE;
			}
			endTime = System.nanoTime();
			totalTime += endTime - startTime;
			oldNode = newNode;
			System.out.println(currentAgent+": "+oldNode.move+" "+column.charAt(oldNode.moveCol)+(oldNode.moveRow+1));
			System.out.println("Map after move:");
			oldNode.wg.printOccupiedMap();
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
