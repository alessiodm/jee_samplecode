package org.alessiodm.j2ee.samplecode.articles.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.alessiodm.j2ee.samplecode.articles.business.ArticlesEJB;
import org.alessiodm.j2ee.samplecode.articles.dto.ArticleDto;
import org.alessiodm.j2ee.samplecode.articles.exception.ArticleException;

/**
 * EJB services exposed via REST
 * 
 * @author alessio
 */
@Path(value="/articles")
public class ArticlesRest {
    
    @EJB ArticlesEJB ejb;
    
    // CDI in order to exploit dependency injection (e.g., @EJB)
    @Inject public ArticlesRest(){}
    
    @GET
    @Path("/listArticles")
    @Produces("application/json")
    public List<ArticleDto> listArticles(){
        return ejb.listArticles();
    }
    
    @POST
    @Path("/createArticle")
    @Consumes("application/json")
    @Produces("application/json")
    public Boolean createArticle(ArticleDto adto){
        if (adto == null) {
            return false;
        }
        
        try {
            ejb.createArticle(adto);
            return true;
        } catch (ArticleException ex) {
            return false;
        }
    }
    
    @POST
    @Path("/createCode")
    @Consumes("application/json")
    @Produces("application/json")
    public Boolean createCode(String code){
        try {
            ejb.createCode(code);
            return true;
        } catch (ArticleException ex) {
            return false;
        }
    }
}
