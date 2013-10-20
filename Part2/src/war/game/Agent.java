/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war.game;
import java.util.*;
/**
 *
 * @author Steve
 */
public class Agent {
    public String name;
    public boolean[][] conquered;
    public int score;
    public List<Piece> pieces;
    
    public Agent(){
        name = "";
        conquered = new boolean[6][6];
        score = 0;
        pieces = null;
    }
    
    public Agent(String name){
        this.name = name;
        conquered = new boolean[6][6];
        score = 0;
        pieces = null;
    }
    
    public boolean hasConquered(int x, int y) {
        return conquered[x][y];
    }
    
    public boolean paraDrop(int x, int y, Agent other) {
        if (other.hasConquered(x, y) || this.hasConquered(x, y)) //Already conquered
            return false;
        Piece p = new Piece(x, y, this);
        this.pieces.add(p);
        this.conquered[x][y] = true;
        return true;
    }
    
    //Change-only conquers enemy pieces around the point caputred, doesn't conquer
    //an enemy piece if it's at the point trying to be captured
    public boolean m1Blitz(int x, int y, Agent other) {
        if (!canTakeover(x,y,other))
            return false;
        for (int i = 0;i < other.pieces.size();i++) {
            Piece thePiece = other.pieces.get(i);
            if ((thePiece.xpos == x) && (thePiece.ypos == y)) {
                other.pieces.remove(i);
                other.conquered[x][y] = false;
            }
        }
        Piece p = new Piece(x,y,this);
        this.pieces.add(p);
        this.conquered[x][y] = true;
        return true;
    }
    
    public boolean canTakeover(int x, int y, Agent other) {
        //Check if adjacent and not a diagonal move
        return false;
    }
}
