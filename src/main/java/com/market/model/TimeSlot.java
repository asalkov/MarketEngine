package com.market.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue
    private long id;

    private Date start;
    private Date stop;
    private boolean isOpen;

    @ManyToOne
    private User user;

    public void setOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    public void setStartTime(Date start) {
        this.start = start;
    }

    public void setStopTime(Date stop){
        this.stop = stop;
    }

    public void setUser(User user){
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (start != null ? !start.equals(timeSlot.start) : timeSlot.start != null) return false;
        if (stop != null ? !stop.equals(timeSlot.stop) : timeSlot.stop != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (stop != null ? stop.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "start=" + start +
                ", stop=" + stop +
                ", isOpen=" + isOpen +
                '}';
    }
}
