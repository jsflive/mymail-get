package at.irian.gui.beans;

import at.irian.domain.Mail;
import at.irian.service.MailService;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewAccessScoped
public class MailBean implements Serializable {

    @Inject
    private MailService mailService;
    @Inject
    private FacesContext facesContext;

    private long id;
    private Mail mail;

    public void preRenderView(ComponentSystemEvent event) {
        if (!facesContext.isValidationFailed()) {
            mail = mailService.findById(id);
            if (mail == null) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error loading", null));
            }
        }
    }

    public String save() {
        mailService.save(mail);
        return "mailList?faces-redirect=true";
    }

    public String switchMailRead() {
        mail.setRead(!mail.isRead());
        mailService.save(mail);
        return "showMail?faces-redirect=true&faces-include-view-params=true";
    }

    public String cancel() {
        mailService.save(mail);
        return "mailList?faces-redirect=true";
    }

    // Generated code


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mail getMail() {
        return mail;
    }

}
