/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war.game;

/**
 *
 * @author Steve
 */
public class Piece {
    public int xpos;
    public int ypos;
    public Agent owner;
    
    public Piece(int x, int y, Agent theAgent) {
        xpos = x;
        ypos = y;
        owner = theAgent;
    }
    
}
