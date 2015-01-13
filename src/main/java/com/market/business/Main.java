package com.market.business;

import com.market.model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Calendar;
import java.util.List;


public class Main {
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
        final Session session = getSession();
        /*
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
        */

        long start = System.currentTimeMillis();
        session.beginTransaction();

        TimeSlot slot = new TimeSlot();
        slot.setOpen(true);
        slot.setStartTime(Calendar.getInstance().getTime());
        slot.setStopTime(Calendar.getInstance().getTime());

        User user = new User();
        user.setEmail("test@test.ru");
        user.setPasswordHash("hash");
        slot.setUser(user);

        session.save(user);
        session.save(slot);
        session.getTransaction().commit();
        session.close();
        /*

        Client client = new Client();
        client.setEmail("salkov.andrey@gmail.com");
        client.setPasswordHash("hash");
        Account a = new Account();
        a.setAmount(1000.0);
        //a.setUser(client);
        client.setAccount(a);


        TimeSlot ts = new TimeSlot();
        ts.setOpen(false);
        client.addTimeSlot(ts);


        session.save(client);

        session.getTransaction().commit();
        session.close();

        System.out.println(client.getId());

        Session s = getSession();
        */





    }
}
