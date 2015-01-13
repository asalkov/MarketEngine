package com.market.business;

import com.market.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jfairy.Fairy;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Random;

public class FakeDataGenerator {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(ServiceProvider.class);
            configuration.addAnnotatedClass(TimeSlot.class);

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {

        Fairy fairy = Fairy.create();

        Session session = getSession();
        session.beginTransaction();

        //generate data in tables.

        //create 10 users with time slots

        int userNum = 100;
        int maxTimeSlotNumber = 8;

        DateTime start = new DateTime();
        start.withDate(2014, 3, 3);
        DateTime stop = new DateTime();
        stop.withDate(2014, 3, 7);


        for (int userIndex = 0; userIndex < userNum; userIndex++){
            ServiceProvider sp = new ServiceProvider();
            sp.setServicePrice(100);
            sp.setEmail(fairy.person().email());

            int timeSlotNumber = new Random().nextInt(maxTimeSlotNumber+1);
            for (int timeSlotIndex = 0; timeSlotIndex < timeSlotNumber; timeSlotIndex++){
                TimeSlot timeSlot = new TimeSlot();
                DateTime d = fairy.dateProducer().randomDateBetweenNowAndFuturePeriod(Period.days(5));
                System.out.println(d);
                d = d.withHourOfDay(new Random().nextInt(9)+8);
                d = d.withMinuteOfHour(0);
                d = d.withSecondOfMinute(0);
                d = d.withMillisOfSecond(0);
                timeSlot.setStartTime(d.toDate());
                timeSlot.setStopTime(d.plusHours(1).toDate());
                timeSlot.setOpen(true);
                timeSlot.setUser(sp);
                sp.addTimeSlot(timeSlot);
                System.out.println(timeSlot);
            }
            session.save(sp);
        }
        session.getTransaction().commit();
    }
}
