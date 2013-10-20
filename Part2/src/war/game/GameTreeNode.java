package war.game;

import war.game.Main.Move;

public class GameTreeNode {
    
    public int value;
    public int[] loc;
    public WarGame wg;
    public Move move;
    public GameTreeNode parent;
    
    public GameTreeNode(GameTreeNode p) {
    	this(p.wg);
    	this.parent = p;
    }
    
    public GameTreeNode(WarGame w) {
    	this.wg = new WarGame(w);
    }
    
}
