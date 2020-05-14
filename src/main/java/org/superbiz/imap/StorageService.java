package org.superbiz.imap;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Lock(LockType.READ)
public class StorageService {

    @PersistenceContext(unitName = "lead-pu")
    private EntityManager em;

    public void persistLead(final Lead lead) {
        em.persist(lead);
    }
}
