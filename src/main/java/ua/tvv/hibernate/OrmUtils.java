package ua.tvv.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Supplier;

import lombok.SneakyThrows;

/**
 * @author Tradunsky V.V.
 */
public final class OrmUtils {
    private OrmUtils() {
    }

    @SneakyThrows
    public static <T> T transactional(Supplier<T> consumer, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T result = consumer.get();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @SneakyThrows
    public static void transactional(Runnable runnable, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            runnable.run();
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus().canRollback()) transaction.rollback();
            throw e;
        }
    }
}
