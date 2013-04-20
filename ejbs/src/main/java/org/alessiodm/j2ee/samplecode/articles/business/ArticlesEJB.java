package org.alessiodm.j2ee.samplecode.articles.business;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.alessiodm.j2ee.samplecode.articles.dto.ArticleDto;
import org.alessiodm.j2ee.samplecode.articles.exception.ArticleException;
import org.alessiodm.j2ee.samplecode.articles.model.Article;
import org.alessiodm.j2ee.samplecode.articles.model.Code;

/**
 * Main component of business logic: a simple stateless ejb.
 * 
 * @author alessio
 */
@Stateless
public class ArticlesEJB 
{
    @PersistenceContext(unitName="ejbs-unit")
    protected EntityManager em;
    
    public ArticlesEJB(){}
    
    /**
     * Same transaction: rollback here -> rollback callee transaction
     * (i.e., I can't create article without any code).
     * We cannot create 'XXX' codes: just to do an example of transaction
     * rollback with checked exception.
     * 
     * @param code Code string
     * @return Code entity
     */
    @TransactionAttribute(value=TransactionAttributeType.REQUIRED)
    public Code createCode(String code) throws ArticleException {
        
        Code c = new Code();
        c.setCode(code);
        em.persist(c);

        // Example of checked exception with rollback
        if (c.getCode().equals("XXX")){
            sendNotification("Error: code XXX is the catastrophic code!");
            throw new ArticleException("Got the wrong code!");
        }
        
        sendNotification("Code " + code + " created.");
        return c;
    }
    
    /**
     * Article creation.
     * 
     * @param name Article name
     * @param code Article code
     * @return Created article
     * @throws ArticleException Just in case I'm not able to create code
     */
    @TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
    public Article createArticle(ArticleDto dto) throws ArticleException {
        
        List<Code> l = (List<Code>) em.createQuery("select c from Code c where c.code = ?")
                        .setParameter(1, dto.getCode()).getResultList();
        
        // EJB transactional method call
        Code c = (l.size() > 0 ? l.get(0) : createCode(dto.getCode()));
        
        Article a = new Article();
        a.setCode(c);
        a.setName(dto.getName());
        em.persist(a);
        
        return a;
        
    }
    
    
    /**
     * Returns the list of available articles.
     * 
     * @return 
     */
    @TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
    public List<ArticleDto> listArticles(){
        List<ArticleDto> dtos = new ArrayList<ArticleDto>(); 
        List<Article> alist = em.createQuery("select a from Article a").getResultList();
        for (Article a : alist){
            ArticleDto dto = new ArticleDto(a.getName(), a.getCode().getCode());
            dtos.add(dto);
        }
        return dtos;
    }
    
    
    //--------------------------------------------
    // Miss partial class from C#...
    //--------------------------------------------
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName="java:/queue/test")
    private Queue queue;
    
    private void sendNotification(String str){
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText(str);
            producer.send(message);
        } catch (JMSException ex) {
            return;
        }
        finally{
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (JMSException ex) {
                return;
            }
        }
    }
    
    public EntityManager getEntityManager(){
        return em;
    }
    
    public void setEntityManager(EntityManager em){
        this.em = em;
    }
}