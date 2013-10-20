/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war.game;

/**
 *
 * @author Steve
 */
public class QuadtreeNode {
    
    public int value;
    public int[] myXY;
    
    public QuadtreeNode northChild;
    public QuadtreeNode westChild;
    public QuadtreeNode southChild;
    public QuadtreeNode eastChild;
    
    //public QuadtreeNode() {
        //myXY = new int[2];
        //northChild = westChild = southChild = eastChild = null;
    //}
    
    public QuadtreeNode(int x, int y, int value) {
        this.value = value;
        myXY = new int[2];
        myXY[0] = x;
        myXY[1] = y;
        northChild = westChild = southChild = eastChild = null;
    }
    
    public boolean hasNoChildren() {
        return (northChild == null) && (westChild == null)
                && (southChild == null) && (eastChild == null);
    }
}
