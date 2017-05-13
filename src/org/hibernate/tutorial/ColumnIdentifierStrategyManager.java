package org.hibernate.tutorial;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.IdentityIdentifier;
import org.hibernate.tutorial.domain.NativeIdentifier;
import org.hibernate.tutorial.domain.SequenceIdentifier;
import org.hibernate.tutorial.domain.TableSequenceIdentifier;
import org.hibernate.tutorial.util.HibernateUtil;

import java.util.List;

/**
 * Demonstrate Hibernate's support for different identifier generating strategies.
 *
 * @see https://vladmihalcea.com/2014/07/08/hibernate-identity-sequence-and-table-sequence-generator/
 */
public class ColumnIdentifierStrategyManager {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Native Strategy (default specified by the underlying db)
        for (int i = 0; i < 10; i++) {
            session.persist(new NativeIdentifier());
        }
        session.flush();
        List<NativeIdentifier> result = session.createQuery("from NativeIdentifier").list();
        for (NativeIdentifier entity : result) {
            System.out.println(entity.getId());
        }

        // Identity Strategy (increment the identity column)
        for (int i = 0; i < 10; i++) {
            session.persist(new IdentityIdentifier());
        }
        session.flush();
        List<IdentityIdentifier> result2 = session.createQuery("from IdentityIdentifier").list();
        for (IdentityIdentifier entity : result2) {
            System.out.println(entity.getId());
        }

        // Sequence Strategy (external sequence object)
        for (int i = 0; i < 10; i++) {
            session.persist(new SequenceIdentifier());
        }
        session.flush();
        List<SequenceIdentifier> result3 = session.createQuery("from SequenceIdentifier").list();
        for (SequenceIdentifier entity : result3) {
            System.out.println(entity.getId());
        }

        // Table Strategy (external table sequence)
        for (int i = 0; i < 10; i++) {
            session.persist(new TableSequenceIdentifier());
        }
        session.flush();
        List<TableSequenceIdentifier> result4 = session.createQuery("from TableSequenceIdentifier").list();
        for (TableSequenceIdentifier entity : result4) {
            System.out.println(entity.getId());
        }

        HibernateUtil.getSessionFactory().close();
    }


}