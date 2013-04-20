package org.alessiodm.j2ee.samplecode.articles.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Article entity.
 * 
 * @author alessio
 */
@Entity
@Table(name = "ARTICLES", uniqueConstraints = @UniqueConstraint(columnNames = {"NAME", "CODE_ID"}))
public class Article implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
        private long id;
	
        @Column(name="NAME", unique=true, nullable=false)  
        private String name;
        
	@ManyToOne (cascade=CascadeType.ALL) 
        @JoinColumn(name="CODE_ID") 
        private Code code;
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Code getCode(){
		return code;
	}
	
	public void setCode(Code code){
		this.code = code;
	}
}
