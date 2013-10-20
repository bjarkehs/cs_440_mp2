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
public class Quadtree {
    
    public QuadtreeNode root;
    public boolean[][] added;
    public int[][] map;
    
    public Quadtree() {
        root = null;
        added = null;
        map = null;
    }
    
    public Quadtree(int x, int y, int value) {
        root = new QuadtreeNode(x, y, value);
        for (int i = 0;i < 6;i++) {
            for (int j = 0;j < 6;j++) {
                added[i][j] = false;
            }
        }
        added[x][y] = true;
    }
    
    public void build() {
        build(root, 0, 0);
    }
    
    public void build(QuadtreeNode n, int x, int y) {
        if (n == root) { //change \
            int[] rootXY = root.myXY;
            int x1 = rootXY[0];
            int y1 = rootXY[1];
            build(n.northChild, x1, y1-1);
            build(n.westChild, x1-1, y1);
            build(n.southChild, x1, y1+1);
            build(n.eastChild, x1+1, y1);
        }
        else {
            if (validXY(x,y)) {
                //n = new QuadtreeNode();
            }
        }
    }
    
    public boolean validXY(int x, int y) {
        return (x >= 0 && y >= 0) && (x < 6 && y < 6) && !added[x][y];
    }
    
    public void add(int i, int j, int value) {
        
    }
    
    public int[] getXYOfBestMove() {
        int[] ret = new int[2];
        return ret;
    }
    
    //Run the wrapper, not the algorithm itself
    public int minimaxWrapper(int depth) {
        return minimax(root, depth, true);
    }
    
    public int minimax(QuadtreeNode search, int depth, boolean maxPlayer) {
        if ((depth == 0) || search.hasNoChildren())
            return search.value;
        int bestValue;
        if (maxPlayer) {
            bestValue = Integer.MIN_VALUE;
            if (search.northChild != null) {
                int val = minimax(search.northChild, depth-1, false);
                bestValue = Math.max(val, bestValue);
            }
            if (search.westChild != null) {
                int val = minimax(search.westChild, depth-1, false);
                bestValue = Math.max(val, bestValue);
            }
            if (search.southChild != null) {
                int val = minimax(search.southChild, depth-1, false);
                bestValue = Math.max(val, bestValue);
            }
            if (search.eastChild != null) {
                int val = minimax(search.eastChild, depth-1, false);
                bestValue = Math.max(val, bestValue);
            }
            return bestValue;
        }
        else {
            bestValue = Integer.MAX_VALUE;
            if (search.northChild != null) {
                int val = minimax(search.northChild, depth-1, true);
                bestValue = Math.min(val, bestValue);
            }
            if (search.westChild != null) {
                int val = minimax(search.westChild, depth-1, true);
                bestValue = Math.min(val, bestValue);
            }
            if (search.southChild != null) {
                int val = minimax(search.southChild, depth-1, true);
                bestValue = Math.min(val, bestValue);
            }
            if (search.eastChild != null) {
                int val = minimax(search.eastChild, depth-1, true);
                bestValue = Math.min(val, bestValue);
            }
            return bestValue;
        }
        //return -1;
    }
    //Run the wrapper, not the algorithm itself
    public int ABwrapper(int depth) {
        return alphabeta(root, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }
    
    public int alphabeta(QuadtreeNode search, int depth, int alpha, 
            int beta, boolean maxPlayer) {
        if ((depth == 0) || search.hasNoChildren())
            return search.value;
        if (maxPlayer) {
            if (search.northChild != null) {
                alpha = Math.max(alpha, alphabeta(search.northChild, depth-1, alpha, beta, false));
                if (beta <= alpha)
                    return alpha;
            }
            if (search.westChild != null) {
                alpha = Math.max(alpha, alphabeta(search.westChild, depth-1, alpha, beta, false));
                if (beta <= alpha)
                    return alpha;
            }
            if (search.southChild != null) {
                alpha = Math.max(alpha, alphabeta(search.southChild, depth-1, alpha, beta, false));
                if (beta <= alpha)
                    return alpha;
            }
            if (search.eastChild != null) {
                alpha = Math.max(alpha, alphabeta(search.eastChild, depth-1, alpha, beta, false));
                if (beta <= alpha)
                    return alpha;
            }
        }
        else {
            if (search.northChild != null) {
                beta = Math.min(beta, alphabeta(search.northChild, depth-1, alpha, beta, true));
                if (beta <= alpha)
                    return beta;
            }
            if (search.westChild != null) {
                beta = Math.min(beta, alphabeta(search.westChild, depth-1, alpha, beta, true));
                if (beta <= alpha)
                    return beta;
            }
            if (search.southChild != null) {
                beta = Math.min(beta, alphabeta(search.southChild, depth-1, alpha, beta, true));
                if (beta <= alpha)
                    return beta;
            }
            if (search.eastChild != null) {
                beta = Math.min(beta, alphabeta(search.eastChild, depth-1, alpha, beta, true));
                if (beta <= alpha)
                    return beta;
            }
        }
        return -1; //If the program gets to this point, this algorithm is garbage...
    }
    
}
