/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war.game;
import java.util.Comparator;
/**
 *
 * @author Steve
 */
public class QuadtreeComparator implements Comparator<QuadtreeNode>{
    public int compare(QuadtreeNode a, QuadtreeNode b) {
        if (a.value < b.value)
            return -1;
        if (a.value > b.value)
            return 1;
        return 0;
    }
}