import java.util.Comparator;

public class TimeSlotComparator implements Comparator<TimeSlot> {
	public int compare(TimeSlot a, TimeSlot b) {
    	if (a.conflictingMeetings.size() > b.conflictingMeetings.size()) {
	        return -1;
	    }
	    if (a.conflictingMeetings.size() < b.conflictingMeetings.size()) {
	        return 1;
	    }
	    return 0;
    }
}
