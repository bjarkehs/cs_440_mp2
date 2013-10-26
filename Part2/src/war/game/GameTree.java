package war.game;

import war.game.Main.Agent;
import war.game.Main.Move;

public class GameTree {
    
	public int maxNodesBlue;
	public int maxNodesGreen;
    
    public GameTreeNode minimax(Agent ag, GameTreeNode node, boolean getMax, int maxDepth) {
//    	System.out.println("Depth: "+node.depth);
    	Agent enemy;
    	if (ag == Agent.BLUE) {
    		enemy = Agent.GREEN;
    	} else {
    		enemy = Agent.BLUE;
    	}

    	if (node.depth >= maxDepth) {
    		// No more recursion. This is a leaf node. 
    		node.value = calculateScore(ag, enemy, node, getMax);
//    		System.out.println(node.value);
    		return node;
    	}
    	
    	GameTreeNode currentMinMax = null;
//    	System.out.println("Printing the node's map");
//    	node.wg.printOccupiedMap();
    	for (int i = 0; i < node.wg.map.length; i++) {
    		for (int j = 0; j < node.wg.map[i].length; j++) {
    			Move mv = checkWhichMove(ag, node, i, j);
    			if (mv == Move.NONE) {
    				continue;
    			}
    	    	GameTreeNode child = new GameTreeNode(node, false);
    	    	child.move = mv;
    	    	child.moveRow = i;
    	    	child.moveCol = j;
    			child = performMove(ag, child);
//    			System.out.println("Printing the child's map");
//    			child.wg.printOccupiedMap();
//    			System.out.println("Printing the child parent's map");
//    			child.parent.wg.printOccupiedMap();
//    			System.out.println("Node's value: "+ node.value);
    			minimax(enemy, child, !getMax, maxDepth);
				if (currentMinMax == null || (getMax && currentMinMax.value < child.value) || (!getMax && currentMinMax.value > child.value)) {
//					if (currentMinMax != null) {
//						System.out.println("Setting child because its value is " + child.value + " which is larger/smaller(true/false)[" + getMax + "] than " + currentMinMax.value);
//					}
					currentMinMax = child;
				}
    		}
    	}
    	
//    	System.out.println("Returning from non-leaf");
    	
    	if (currentMinMax != null) {
	    	node.value = currentMinMax.value;
	    	node.move = currentMinMax.move;
	    	node.moveRow = currentMinMax.moveRow;
	    	node.moveCol = currentMinMax.moveCol;
	    	node = performMove(ag, node);
    	}
    	// minimax(enemy, node, !getMax, maxDepth--)
    	return node;
    }

    private Move checkWhichMove(Agent ag, GameTreeNode node, int row, int col) {
    	if (node.wg.occupied[row][col] != Agent.NEUTRAL) {
    		return Move.NONE;
    	} else {
    		if (row != 0) {
    			if (node.wg.occupied[row-1][col] == ag) {
    				return Move.BLITZ;
    			}
    		}
    		if (row < node.wg.map.length-1) {
    			if (node.wg.occupied[row+1][col] == ag) {
    				return Move.BLITZ;
    			}
    		}
    		if (col != 0) {
    			if (node.wg.occupied[row][col-1] == ag) {
    				return Move.BLITZ;
    			}
    		}
    		if (col < node.wg.map[row].length-1) {
    			if (node.wg.occupied[row][col+1] == ag) {
    				return Move.BLITZ;
    			}
    		}
    	}
    	return Move.PARADROP;
    }
    
    private GameTreeNode performMove(Agent ag, GameTreeNode node) {
    	Move mv = node.move;
    	int row = node.moveRow;
    	int col = node.moveCol;
    	Agent enemy;
    	if (ag == Agent.BLUE) {
    		enemy = Agent.GREEN;
    	} else {
    		enemy = Agent.BLUE;
    	}

		node.wg.occupied[row][col] = ag;
		boolean hasAlly = false;
    	if (mv == Move.BLITZ) {
    		if (row != 0) {
    			if (node.wg.occupied[row-1][col] == ag) {
    				hasAlly = true;
    			}
    		}
    		if (row < node.wg.map.length-1) {
    			if (node.wg.occupied[row+1][col] == ag) {
    				hasAlly = true;
    			}
    		}
    		if (col != 0) {
    			if (node.wg.occupied[row][col-1] == ag) {
    				hasAlly = true;
    			}
    		}
    		if (col < node.wg.map[row].length-1) {
    			if (node.wg.occupied[row][col+1] == ag) {
    				hasAlly = true;
    			}
    		}
    		if (hasAlly) {
	    		if (row != 0) {
	    			if (node.wg.occupied[row-1][col] == enemy) {
	    				node.wg.occupied[row-1][col] = ag;
	    			}
	    		}
	    		if (row < node.wg.map.length-1) {
	    			if (node.wg.occupied[row+1][col] == enemy) {
	    				node.wg.occupied[row+1][col] = ag;
	    			}
	    		}
	    		if (col != 0) {
	    			if (node.wg.occupied[row][col-1] == enemy) {
	    				node.wg.occupied[row][col-1] = ag;
	    			}
	    		}
	    		if (col < node.wg.map[row].length-1) {
	    			if (node.wg.occupied[row][col+1] == enemy) {
	    				node.wg.occupied[row][col+1] = ag;
	    			}
	    		}
    		}
    	}
    	
    	return node;
    }
    
    public int calculateScore(Agent ag, Agent enemy, GameTreeNode node, boolean getMax) {
    	int greenScore = 0;
    	int blueScore = 0;
    	for (int i = 0; i < node.wg.map.length; i++) {
    		for (int j = 0; j < node.wg.map[i].length; j++) {
    			if (node.wg.occupied[i][j] == Agent.BLUE) {
    				blueScore += node.wg.map[i][j];
    			}
    			else if (node.wg.occupied[i][j] == Agent.GREEN) {
    				greenScore += node.wg.map[i][j];
    			}
    		}
    	}
    	
    	Agent player;
    	if (!getMax) {
    		player = ag;
    	} else {
    		player = enemy;
    	}
    	
    	if (player == Agent.BLUE) {
    		return blueScore;
    	} else {
    		return greenScore;
    	}
    }
    
//    public int[] calculateFinalScores(GameTreeNode node) {
//    	int greenScore = 0;
//    	int blueScore = 0;
//    	for (int i = 0; i < node.wg.map.length; i++) {
//    		for (int j = 0; i < node.wg.map[i].length; j++) {
//    			if (node.wg.occupied[i][j] == Agent.BLUE) {
//    				blueScore += node.wg.map[i][j];
//    			}
//    			else if (node.wg.occupied[i][j] == Agent.GREEN) {
//    				greenScore += node.wg.map[i][j];
//    			}
//    		}
//    	}
//    	
//    	if (ag == Agent.BLUE) {
//    		return blueScore;
//    	} else {
//    		return greenScore;
//    	}
//    }
}
