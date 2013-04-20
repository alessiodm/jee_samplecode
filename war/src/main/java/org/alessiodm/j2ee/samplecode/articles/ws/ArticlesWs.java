package org.alessiodm.j2ee.samplecode.articles.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.alessiodm.j2ee.samplecode.articles.business.ArticlesEJB;
import org.alessiodm.j2ee.samplecode.articles.dto.ArticleDto;

/**
 * EJB services exposed via WebServices.
 * 
 * @author alessio
 */
@WebService(name="ArticlesWs", 
            portName="ArticlesServicePort",
            serviceName="ArticlesService",
            targetNamespace="org.alessiodm.j2ee.samplecode.articles.services.ws")
public class ArticlesWs {
 
    @EJB ArticlesEJB ejb;
    
    @Inject public ArticlesWs(){}
    
    @WebMethod
    public List<ArticleDto> listArticles(){
        return ejb.listArticles();
    }
    
}
