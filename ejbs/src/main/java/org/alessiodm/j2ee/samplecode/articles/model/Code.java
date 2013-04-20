package org.alessiodm.j2ee.samplecode.articles.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Code entity.
 * @author alessio
 */
@Entity
@Table(name = "CODES", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Code implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private long id;
	@Column(unique=true, nullable=false, name="CODE")  private String code;
	@OneToMany (mappedBy="code") private List<Article> articles;
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public List<Article> getArticles(){
		return articles;
	}
	
	/*
	// Should avoid on container objects
	public void setArticles(List<Articles> articles){
		this.articles = articles;
	}
	*/
}