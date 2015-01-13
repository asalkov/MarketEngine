package com.market.business;

import com.github.javafaker.Faker;
import com.market.model.ServiceProvider;
import com.market.model.TimeSlot;
import com.market.model.User;
import org.jfairy.Fairy;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Date;
import java.util.Random;

// hours from 8 to 20
// minutes 0, 30

public class FakeDataRun {
    public static void main(String[] args){
        Fairy fairy = Fairy.create();
        DateTime d = fairy.dateProducer().randomDateBetweenYearAndNow(2014);
        d = d.withHourOfDay(new Random().nextInt(9)+8);
        d = d.withMinuteOfHour(0);
        d = d.withSecondOfMinute(0);
        d = d.withMillisOfSecond(0);
        System.out.println(d);

        ServiceProvider sp = new ServiceProvider();
        sp.setEmail(fairy.person().email());
        sp.setPasswordHash(fairy.textProducer().word());

        TimeSlot ts = new TimeSlot();
        ts.setUser(sp);
        ts.setStartTime(d.toDate());
        d = d.plusHours(1);
        ts.setStopTime(d.toDate());
        ts.setOpen(true);
        System.out.println(sp);
        System.out.println(ts);


        System.out.println(fairy.person().email());
        System.out.println(fairy.person().email());


    }
}
