import java.util.Comparator;

public class MeetingsComparator implements Comparator<Meeting> {
    public int compare(Meeting a, Meeting b) {
    	if (a.name < b.name) {
	        return -1;
	    }
	    if (a.name > b.name) {
	        return 1;
	    }
	    return 0;
    }
}