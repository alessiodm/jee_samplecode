package org.alessiodm.j2ee.samplecode.articles.dto;

/**
 * Simple DTO for transfer/serialization of Article data.
 * 
 * @author alessio
 */
public class ArticleDto {
    private String name;
    private String code;

    public ArticleDto(){
    }
    
    public ArticleDto(String name, String code){
        this.name = name;
        this.code = code;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
