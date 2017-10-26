package ua.tvv.hibernate.temporary.session.monitoring.collection.type;

import org.hibernate.HibernateException;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.usertype.UserCollectionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import ua.tvv.hibernate.temporary.session.monitoring.collection.MonitorablePersistentBag;

public class MonitorableBagType implements UserCollectionType {
//    @SuppressWarnings("WeakerAccess")
//    public MonitorableBagType(TypeFactory.TypeScope typeScope, String role, String propertyRef) {
//        super(typeScope, role, propertyRef);
//    }
//
//    @Override
//    public PersistentCollection instantiate(SharedSessionContractImplementor session, CollectionPersister persister, Serializable key)
//            throws HibernateException {
//        return new MonitorablePersistentBag(session);
//    }
//
//    @Override
//    public Class getReturnedClass() {
//        return java.util.Collection.class;
//    }
//
//    @Override
//    public PersistentCollection wrap(SharedSessionContractImplementor session, Object collection) {
//        return new MonitorablePersistentBag(session, (Collection) collection);
//    }
//
//    @Override
//    public Object instantiate(int anticipatedSize) {
//        return anticipatedSize <= 0 ? new ArrayList() : new ArrayList(anticipatedSize + 1);
//    }


    @Override
    public PersistentCollection instantiate(SharedSessionContractImplementor session, CollectionPersister persister) throws HibernateException {
        return new MonitorablePersistentBag(session);
    }

    @Override
    public PersistentCollection wrap(SharedSessionContractImplementor session, Object collection) {
        return new MonitorablePersistentBag(session, (Collection) collection);
    }

    @Override
    public Iterator getElementsIterator(Object collection) {
        return ((MonitorablePersistentBag)collection).iterator();
    }

    @Override
    public boolean contains(Object collection, Object entity) {
        return ((MonitorablePersistentBag)collection).contains(entity);
    }

    @Override
    public Object indexOf(Object collection, Object entity) {
        return ((MonitorablePersistentBag)collection).indexOf(entity);
    }

    @Override
    public Object replaceElements(Object original, Object target, CollectionPersister persister, Object owner, Map copyCache, SharedSessionContractImplementor session) throws HibernateException {
        MonitorablePersistentBag originalPersistentBag = (MonitorablePersistentBag) original;
        MonitorablePersistentBag targetPersistentBag = (MonitorablePersistentBag) target;
        targetPersistentBag.retainAll(originalPersistentBag);
        return target;
    }

    @Override
    public Object instantiate(int anticipatedSize) {
        return anticipatedSize <= 0 ? new ArrayList() : new ArrayList(anticipatedSize + 1);
    }
}
