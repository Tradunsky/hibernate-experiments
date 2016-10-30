package ua.tvv.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

import ua.tvv.hibernate.model.Hunter;
import ua.tvv.hibernate.model.Weapon;

import static ua.tvv.hibernate.OrmUtils.transactional;

/**
 * @author Tradunsky V.V.
 */
public class Experiment {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = getFactory();


        Session session = sessionFactory.openSession();
        final Integer[] deanInn = {null};
        final Integer[] samInn = {null};
        try {
            transactional(() -> {
                Hunter dean = new Hunter();
                dean.setLastName("Winchester");
                dean.setFirstName("Dean");
                Hunter sam = new Hunter();
                sam.setFirstName("Sam");
                sam.setLastName("Winchester");
                Weapon knife = new Weapon("Knife");
                session.persist(knife);
                dean.getWeapons().add(knife);
                session.persist(dean);
                sam.getWeapons().add(knife);
                session.persist(sam);
                deanInn[0] = dean.getInn();
                samInn[0] = sam.getInn();
            }, session);
        } finally {
            session.close();
//            sessionFactory.close();
        }

        Session session2 = sessionFactory.openSession();
        try {
            Objects.requireNonNull(session2.get(Weapon.class, "Knife"));
            Hunter deanFromDb = session2.get(Hunter.class, deanInn[0]);
            System.out.println("Dean's weapons: " + deanFromDb.getWeapons());
            Hunter samFromDb = session2.get(Hunter.class, samInn[0]);
            System.out.println("Sam's weapons: " + samFromDb.getWeapons());
        } finally {
            session2.close();
            sessionFactory.close();
        }
    }

    public static SessionFactory getFactory() {
        return new Configuration()
                .configure()
                .addAnnotatedClass(Hunter.class)
                .addAnnotatedClass(Weapon.class)
                .buildSessionFactory();
    }


}
