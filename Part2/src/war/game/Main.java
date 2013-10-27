package war.game;

import java.io.File;
import java.io.FileNotFoundException;
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
		int maxDepthAlpha = Integer.parseInt(args[3]);
		int maxDepthMinimax = Integer.parseInt(args[4]);
		
		WarGame wg = new WarGame("maps"+File.separator+wargame+".txt");
		
		int blueDepth;
		if (blueGame.equalsIgnoreCase("alpha")) {
			blueGame = "alpha-beta";
			blueDepth = maxDepthAlpha;
		} else {
			blueGame = "minimax";
			blueDepth = maxDepthMinimax;
		}
		
		int greenDepth;
		if (greenGame.equalsIgnoreCase("alpha")) {
			greenGame = "alpha-beta";
			greenDepth = maxDepthAlpha;
		} else {
			greenGame = "minimax";
			greenDepth = maxDepthMinimax;
		}
		String resultfile = wargame + "-" + blueGame + blueDepth + "_vs_" + greenGame + greenDepth + ".txt";
		
		try
		{
			FileOutputStream fout= new FileOutputStream("game_results"+File.separator+resultfile);
			
			MultiOutputStream multiOut= new MultiOutputStream(System.out, fout);
			
			PrintStream stdout= new PrintStream(multiOut);
			
			System.setOut(stdout);
		}
		catch (FileNotFoundException ex)
		{
			//Could not create/open the file
		}
		
		System.out.println("Alpha-beta Depth: " + maxDepthAlpha+".");
		System.out.println("Minimax Depth: " + maxDepthMinimax+".");
		System.out.println();
		System.out.println("Blue is using: " + blueGame);
		System.out.println("Green is using: " + greenGame);
		System.out.println();
		System.out.println("The map, " + wargame + ":");
		wg.printMap();
		System.out.println();
		
//		System.out.println("Occupation of the map:");
//		wg.printOccupiedMap();
		
		String column = "ABCDEF";
		
		Agent currentAgent = Agent.BLUE;
		GameTreeNode oldNode = new GameTreeNode(wg);
		
		long totalGreen = 0;
		long totalBlue = 0;
		System.out.println("Starting game");
//		System.out.println("Turn: 1");
		
		GameTree gt = new GameTree();
		long startTime = System.nanoTime();
		if (blueGame.equalsIgnoreCase("alpha-beta")) {
			gt.alpha_beta(currentAgent, oldNode, true, maxDepthAlpha, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		} else {
			gt.minimax(currentAgent, oldNode, true, maxDepthMinimax, 0);
		}
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		totalBlue += gt.maxNodes;
		
		System.out.println(currentAgent+": "+oldNode.move+" "+column.charAt(oldNode.moveCol)+(oldNode.moveRow+1));
//		System.out.println("Map after move:");
//		oldNode.wg.printOccupiedMap();
		currentAgent = Agent.GREEN;
		
		int amountOfMoves = wg.size*wg.size;
		for (int i = 1; i < amountOfMoves; i++) {
//			System.out.println("Turn: " + (i+1));
			GameTreeNode newNode = new GameTreeNode(oldNode, true);
			gt = new GameTree();
			startTime = System.nanoTime();
			Agent oldAgent = currentAgent;
			if (currentAgent == Agent.BLUE) {
				if (blueGame.equalsIgnoreCase("alpha-beta")) {
					gt.alpha_beta(currentAgent, newNode, true, maxDepthAlpha, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
				} else { 
					gt.minimax(currentAgent, newNode, true, maxDepthMinimax, i);
				}
				totalBlue += gt.maxNodes;
				currentAgent = Agent.GREEN;
			} else {
				if (greenGame.equalsIgnoreCase("alpha-beta")) {
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
			System.out.println(oldAgent+": "+oldNode.move+" "+column.charAt(oldNode.moveCol)+(oldNode.moveRow+1));
//			System.out.println("Map after move:");
//			oldNode.wg.printOccupiedMap();
		}
		System.out.println();
		System.out.println("End-game map: ");
		oldNode.wg.printOccupiedMap();
		System.out.println();
		oldNode.wg.printScores();
		System.out.println();
		System.out.println("Total time for all moves: " + (float)totalTime/1000000000 + "s");
		System.out.println();
		System.out.println("Nodes expanded by Blue: " + totalBlue + ",\nand by Green: " + totalGreen + ",\ngiving a total of: " + (totalBlue+totalGreen));
		System.out.println();
		System.out.println("Avg amount of nodes/move: " + ((totalBlue+totalGreen)/amountOfMoves));
		System.out.println();
		System.out.println("Avg amount of time/move: " + ((float)totalTime/1000000000/amountOfMoves) + "s");
	}

}
