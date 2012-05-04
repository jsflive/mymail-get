package at.irian.service;

import at.irian.dao.MailRepository;
import at.irian.domain.Mail;
import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MailService {
    @Inject
    private MailRepository mailRepository;

    @Transactional
    public void fetchNewMail() {
        for (int i = 0; i < 3; i++) {
            Mail mail = MailBuilder.buildMail();
            mailRepository.save(mail);
        }
    }

    @Transactional
    public void save(Mail mail) {
        mailRepository.save(mail);
    }

    @Transactional
    public void delete(Mail mail) {
        mailRepository.delete(mail);
    }

    @Transactional
    public List<Mail> findAll() {
        return mailRepository.findAll();
    }

    @Transactional
    public Mail findById(long id) {
        return mailRepository.findById(id);
    }

}
