package at.irian.gui.beans;

import at.irian.domain.Mail;
import at.irian.service.MailService;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class MailListBean implements Serializable {

    @Inject
    private MailService mailService;
    private List<Mail> mails;

    @PostConstruct
    void init() {
        // Initially run search
        findMails();
    }

    public String fetchMails() {
        mailService.fetchNewMail();
        findMails();
        return null;
    }

    public String delete(Mail mail) {
        mailService.delete(mail);
        findMails();
        return null;
    }

    public void switchMailRead(Mail mail) {
        mail.setRead(!mail.isRead());
        mailService.save(mail);
        findMails();
    }

    private void findMails() {
        mails = mailService.findAll();
    }

    // Generated code

    public List<Mail> getMails() {
        return mails;
    }

}
