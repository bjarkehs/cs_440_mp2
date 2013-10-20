import java.util.Comparator;

public class MeetingsComparator implements Comparator<Meeting> {
    public int compare(Meeting a, Meeting b) {
    	if (a.conflictingMeetings.size() > b.conflictingMeetings.size()) {
	        return -1;
	    }
	    if (a.conflictingMeetings.size() < b.conflictingMeetings.size()) {
	        return 1;
	    }
	    return 0;
    }
}