/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war.game;
import java.io.*;
import java.util.regex.*;
/**
 *
 * @author Steve
 * ***MAIN CLASS***
 * Progress so far:
 * WarGame.java: Reads a maze in and prints it out. Doesn't execute any game instances yet.
 * Agent.java: Paradrop method should be complete or close to complete. M1 Death Blitz needs work.
 * Piece.java: Class representing a game piece that belongs to a player.
 * Quadtree: Class representing a tree of the possible moves a player can make. Needs to be implemented.
 */
public class WarGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        int[][] map = new int[6][6];
        FileReader f = new FileReader("Sevastopol.txt");
        BufferedReader b = new BufferedReader(f);
        String s = "";
        int j = 0;
        int k = 0;
        Pattern p = Pattern.compile("\\d+");
        Matcher m;
        while ((s = b.readLine()) != null) {
            m = p.matcher(s);
            while (m.find()) {
                map[j][k] = Integer.parseInt(m.group()); // Returns the number as a string. So use Integer.parseInt(m.group()) to get the integer
                k++;
                if (k >= 6)
                    break;
            }
            j++;
            k = 0;
        }
        for (int i = 0;i < 6;i++){
            for (int l = 0;l < 6;l++){
                System.out.print(map[i][l] + " ");
            }
            System.out.println();
        }
        
        f.close();
        
        Agent blue = new Agent("blue");
        Agent green = new Agent("green");
        
        Quadtree gameTree = new Quadtree(0,0,map[0][0]);
        for (int i = 0;i < map.length;i++) {
            for (j = 0;j < map[0].length;j++) {
                gameTree.add(i, j, map[i][j]);
            }
        }
        
        int gameCount = 0;
        while (gameCount < 36) {
            gameCount++;
            break;
        }
    }
}
