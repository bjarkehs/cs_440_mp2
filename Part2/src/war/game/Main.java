package war.game;

public class Main {
	
	public enum Move {
		PARADROP, BLITZ
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
				gt.minimax(currentAgent, newNode, true);
				currentAgent = Agent.GREEN;
				continue;
			}
			newNode = new GameTreeNode(oldNode);
			gt.minimax(currentAgent, newNode, true);
			if (currentAgent == Agent.BLUE) {
				currentAgent = Agent.GREEN;
			} else {
				currentAgent = Agent.BLUE;
			}
			if (i > 4) {
				break;
			}
		}
        
//      Agent blue = new Agent("blue");
//      Agent green = new Agent("green");
//      
//      Quadtree gameTree = new Quadtree(0,0,map[0][0]);
//      for (int i = 0;i < map.length; i++) {
//          for (int j = 0; j < map[0].length; j++) {
//              gameTree.add(i, j, map[i][j]);
//          }
//      }
//      
//      int gameCount = 0;
//      while (gameCount < 36) {
//          gameCount++;
//          break;
//      }
	}

}
