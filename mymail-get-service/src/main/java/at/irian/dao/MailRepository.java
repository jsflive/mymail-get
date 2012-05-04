package at.irian.dao;

import at.irian.domain.Mail;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class MailRepository {

    @Inject
    private EntityManager em;

    public void save(Mail mail) {
        if (mail.isTransient()) {
            em.persist(mail);
        } else {
            em.merge(mail);
        }
    }

    public void delete(Mail mail) {
        mail = em.merge(mail);
        em.remove(mail);
    }

    public List<Mail> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Mail> query = builder.createQuery(Mail.class);
        Root<Mail> root = query.from(Mail.class);
        query.select(root).orderBy(builder.desc(root.get("subject")));
        return em.createQuery(query).getResultList();
    }

    public Mail findById(long id) {
        return em.find(Mail.class, id);
    }

}
