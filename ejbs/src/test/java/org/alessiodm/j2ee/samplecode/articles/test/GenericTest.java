package org.alessiodm.j2ee.samplecode.articles.test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.alessiodm.j2ee.samplecode.articles.business.ArticlesEJB;
import org.alessiodm.j2ee.samplecode.articles.dto.ArticleDto;
import org.alessiodm.j2ee.samplecode.articles.model.Article;
import org.alessiodm.j2ee.samplecode.articles.model.Code;
import org.junit.Before;
import org.junit.Test;

/**
 * Test is a must!
 * 
 * @author alessio
 */
public class GenericTest {
     
    ArticlesEJB ejb;
    EntityManager mockem;
    Query mockq;
    
    @Before
    public void setUp() {
        ejb = new ArticlesEJB();
        mockem = createMock(EntityManager.class);
        mockq = createMock(Query.class);
        ejb.setEntityManager(mockem);
    }

    @Test
    public void testArticleList() throws Exception {
        
        Article a1 = new Article();
        Article a2 = new Article();
        Code c12 = new Code();
        c12.setCode("123");
        a1.setName("a1");
        a1.setCode(c12);
        a2.setName("a2");
        a2.setCode(c12);

        List<Article> alist = new ArrayList<Article>();
        alist.add(a1);
        alist.add(a2);
        
        expect(mockem.createQuery("select a from Article a")).andReturn(mockq);
        expect(mockq.getResultList()).andReturn(alist);
        
        // Start mock testing mode
        replay(mockem);
        replay(mockq);
        
        List<ArticleDto> result = ejb.listArticles();
        assertEquals("List.size() ", 2, result.size());
        
        // Verify mock behavior
        verify(mockem);
        verify(mockq);
    }
    
}
