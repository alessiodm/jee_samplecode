package org.alessiodm.j2ee.samplecode.articles.business;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Dumb message driven bean.
 *
 * @author alessio
 */
@MessageDriven (
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
            }
    )
public class ArticlesMDB implements MessageListener {

    private static final Logger log = Logger.getLogger(ArticlesMDB.class.getName());
    
    @Resource
    private MessageDrivenContext mdc;

    public ArticlesMDB() {
    }

    public void onMessage(Message message) {
        TextMessage msg;

        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                log.log(Level.INFO, "***** Message received: {0}", msg.getText());
            } else {
                log.log(Level.WARNING, "***** Wrong type: {0}", message.getClass().getName());
            }
        } catch (JMSException e) {
            log.severe(e.getMessage());
            mdc.setRollbackOnly();
        } catch (Throwable t) {
            log.severe(t.getMessage());
        }
    }
}
