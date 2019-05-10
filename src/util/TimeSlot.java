package util;

import java.time.LocalTime;

public class TimeSlot implements Comparable<TimeSlot> {

	private LocalTime startTime;
	private LocalTime endTime;
	private long durationMinutes;
	
	public TimeSlot(LocalTime startTime, long durationMinutes) {
		this.startTime = startTime;
		endTime = startTime.plusMinutes(durationMinutes);
		this.durationMinutes = durationMinutes;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public double getDurationMinutes() {
		return durationMinutes;
	}
	
	public String toString() {
		return startTime.toString() + " " + durationMinutes;
	}
	
	public boolean canFit(long durationMinutes) {
		return this.durationMinutes - durationMinutes >= 0;
	}
	
    @Override
    public boolean equals(Object o) { 
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof TimeSlot)) { 
            return false; 
        } 
          
        TimeSlot c = (TimeSlot) o; 
          
        return startTime.equals(c.getStartTime()) && durationMinutes == c.durationMinutes;
    }

	@Override
	public int compareTo(TimeSlot o) {
		return startTime.compareTo(o.startTime);
//		return o.startTime.compareTo(startTime);
	}


}
