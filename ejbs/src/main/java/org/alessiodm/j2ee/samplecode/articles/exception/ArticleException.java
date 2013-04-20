package org.alessiodm.j2ee.samplecode.articles.exception;

import javax.ejb.ApplicationException;

/**
 * Checked exception... BUT WE DO ROLLBACK.
 * Unchecked exceptions automatically rollback...
 * 
 * @author alessio
 */
@ApplicationException(rollback=true)
public class ArticleException extends Exception {
    
    public ArticleException(){
        super();
    }
    
    public ArticleException (String msg) {
        super(msg);
    }
    
}
