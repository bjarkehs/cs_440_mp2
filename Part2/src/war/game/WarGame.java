package war.game;

import java.io.*;
import java.util.regex.*;
import war.game.Main.Agent;

public class WarGame {
	
	public int[][] map;
	public Agent[][] occupied;

    /**
     * @param args the command line arguments
     */
	
	public WarGame(WarGame wg) {
		this.map = wg.map;
		this.occupied = new Agent[6][6];
		for (int i = 0; i < wg.occupied.length; i++) {
			for (int j = 0; j < wg.occupied[i].length; j++) {
				this.occupied[i][j] = wg.occupied[i][j];
			}
		}
	}
	
    public WarGame(String filePath) {
        this.map = new int[6][6];
        this.occupied = new Agent[6][6];
        
        try {
	        FileReader f = new FileReader(filePath);
	        BufferedReader b = new BufferedReader(f);
	        String s = null;
	        int j = 0;
	        int k = 0;
	        Pattern p = Pattern.compile("\\d+");
	        Matcher m;
	        while ((s = b.readLine()) != null) {
	            m = p.matcher(s);
	            while (m.find()) {
	                map[j][k] = Integer.parseInt(m.group()); // Returns the number as a string. So use Integer.parseInt(m.group()) to get the integer
	                k++;
	                if (k >= 6) {
	                    break;
	                }
	            }
	            j++;
	            k = 0;
	        }
	        f.close();
        }
		catch (Exception ex){
			ex.printStackTrace();
	    }
        
        for (int i = 0; i < 6; i++){
            for (int l = 0; l < 6; l++){
            	this.occupied[i][l] = Agent.NEUTRAL;
            }
        }
    }
    
    public void printMap() {
    	String niceOutput = null;
        for (int i = 0; i < 6; i++){
            for (int l = 0; l < 6; l++){
            	niceOutput = String.format("%1$2s ", map[i][l]);
                System.out.print(niceOutput);
            }
            System.out.println();
        }
    }
    
    public void printOccupiedMap() {
    	String niceOutput = null;
        for (int i = 0; i < 6; i++){
            for (int l = 0; l < 6; l++){
            	String agent = null;
            	switch (occupied[i][l]) {
            		case NEUTRAL:
            			agent = "n";
            			break;
            		case BLUE:
            			agent = "b";
            			break;
            		case GREEN:
            			agent = "g";
            			break;
            	}
            	niceOutput = String.format("%1$2s ", agent);
                System.out.print(niceOutput);
            }
            System.out.println();
        }
    }
    
    public void printScores() {
    	int greenScore = 0;
    	int blueScore = 0;
    	for (int i = 0; i < this.occupied.length; i++) {
    		for (int j = 0; j < this.occupied[i].length; j++) {
    			if (this.occupied[i][j] == Agent.BLUE) {
    				blueScore += this.map[i][j];
    			}
    			else if (this.occupied[i][j] == Agent.GREEN) {
    				greenScore += this.map[i][j];
    			}
    		}
    	}
    	
    	System.out.println("Blue: " + blueScore + ", Green: " + greenScore);
    }
}
